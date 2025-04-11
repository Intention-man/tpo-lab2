import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

import functions.Tan;
import functions.Sin;
import functions.Cos;
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
public class TanTest {

    private static Sin mockSinFunction;
    private static Cos mockCosFunction;
    private Tan tanFunction;

    @BeforeAll
    public static void init() throws Exception {
        mockSinFunction = Mockito.mock(Sin.class);
        mockCosFunction = Mockito.mock(Cos.class);

        try (Reader in = new FileReader("src/main/resources/csv/mocks/sin.csv")) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
            for (CSVRecord record : records) {
                double x = Double.parseDouble(record.get(0));
                double sinValue = Double.parseDouble(record.get(1));
                Mockito.when(mockSinFunction.calculate(x, 0.001)).thenReturn(sinValue);
            }
        }

        try (Reader in = new FileReader("src/main/resources/csv/mocks/cos.csv")) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
            for (CSVRecord record : records) {
                double x = Double.parseDouble(record.get(0));
                double cosValue = Double.parseDouble(record.get(1));
                Mockito.when(mockCosFunction.calculate(x, 0.001)).thenReturn(cosValue);
            }
        }
    }

    @BeforeEach
    public void setUp() {
        tanFunction = new Tan(mockSinFunction, mockCosFunction);
    }

    @Test
    public void testCalculateTanZero() {
        assertEquals(0, tanFunction.calculate(0, 0.001));
    }

    @Test
    public void testCalculatePi() {
        assertEquals(0, tanFunction.calculate(Math.PI, 0.001), 0.001);
    }

    @Test
    public void testCalculateNegativePi() {
        assertEquals(0, tanFunction.calculate(-Math.PI, 0.001), 0.001);
    }

    @Test
    public void testCalculateBoundaryValues() {
        assertEquals(Double.POSITIVE_INFINITY, tanFunction.calculate(Math.PI / 2, 0.001));
        assertEquals(Double.NEGATIVE_INFINITY, tanFunction.calculate(-Math.PI / 2, 0.001));
    }

    @Test
    public void testCalculateInvalidPrecisionTooLow() {
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            tanFunction.calculate(Math.PI / 4, -0.1);
        });
        assertEquals("precision must be more than 0 and less than 1", exception.getMessage());
    }

    @Test
    public void testCalculateInvalidPrecisionTooHigh() {
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            tanFunction.calculate(Math.PI / 4, 1.1);
        });
        assertEquals("precision must be more than 0 and less than 1", exception.getMessage());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/mocks/tan.csv")
    public void testCalculateTanUsingCSV(double x, double expected) {
        double result = tanFunction.calculate(x, 0.001);
        assertEquals(expected, result, 0.001);
    }
}
