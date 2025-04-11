import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

import functions.Cos;
import functions.Sin;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.Reader;

@ExtendWith(MockitoExtension.class)
public class CosTest {

    private static Sin mockSinFunction;
    private Cos cosFunction;

    @BeforeAll
    public static void init() throws Exception {
        mockSinFunction = Mockito.mock(Sin.class);

        try (Reader in = new FileReader("src/main/resources/csv/mocks/sin.csv")) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
            for (CSVRecord record : records) {
                double x = Double.parseDouble(record.get(0));
                double sinValue = Double.parseDouble(record.get(1));
                Mockito.when(mockSinFunction.calculate(x, 0.001)).thenReturn(sinValue);
            }
        }
    }

    @BeforeEach
    public void setUp() {
        cosFunction = new Cos(mockSinFunction);
    }

    @Test
    public void testCalculateWithZero() {
        double result = cosFunction.calculate(0.0, 0.001);
        assertEquals(1.0, result, 0.001);
    }

    @Test
    public void testCalculateWithPositiveAngle() {
        double result = cosFunction.calculate(0.7854, 0.001);
        assertEquals(Math.sqrt(2) / 2, result, 0.001);

        verify(mockSinFunction).calculate(0.7854, 0.001);
    }

    @Test
    public void testCalculateWithAnotherPositiveAngle() {
        double result = cosFunction.calculate(1.0472, 0.001);
        assertEquals(0.5, result, 0.001);

        verify(mockSinFunction).calculate(1.0472, 0.001);
    }

    @Test
    public void testCalculateWithNegativeAngle() {
        double result = cosFunction.calculate(-0.7854, 0.001);
        assertEquals(Math.sqrt(2) / 2, result, 0.001);

        verify(mockSinFunction).calculate(-0.7854, 0.001);
    }

    @Test
    public void testCalculateInvalidPrecisionTooLow() {
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            cosFunction.calculate(Math.PI / 4, -0.1);
        });
        assertEquals("precision must be more than 0 and less than 1", exception.getMessage());
    }

    @Test
    public void testCalculateInvalidPrecisionTooHigh() {
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            cosFunction.calculate(Math.PI / 4, 1.1);
        });
        assertEquals("precision must be more than 0 and less than 1", exception.getMessage());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/mocks/cos.csv")
    public void testCalculateCosUsingCSV(double x, double expected) {
        double result = cosFunction.calculate(x, 0.001);
        assertEquals(expected, result, 0.001);
    }
}