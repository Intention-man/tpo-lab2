import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

import functions.Cot;
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
public class CotTest {

    private static Sin mockSinFunction;
    private static Cos mockCosFunction;
    private Cot cotFunction;

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
        cotFunction = new Cot(mockSinFunction, mockCosFunction);
    }

    @Test
    public void testCalculateCotBoundaryPositive() {
        assertEquals(Double.POSITIVE_INFINITY, cotFunction.calculate(0.0, 0.001));
    }

    @Test
    public void testCalculateCotBoundaryNegative() {
        assertEquals(Double.NEGATIVE_INFINITY, cotFunction.calculate(Math.PI, 0.001));
    }

    @Test
    public void testCalculateCotZero() {
        assertEquals(0, cotFunction.calculate(Math.PI / 2, 0.001));
    }

    @Test
    public void testCalculateInvalidPrecisionTooLow() {
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            cotFunction.calculate(Math.PI / 4, -0.1);
        });
        assertEquals("precision must be more than 0 and less than 1", exception.getMessage());
    }

    @Test
    public void testCalculateInvalidPrecisionTooHigh() {
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            cotFunction.calculate(Math.PI / 4, 1.1);
        });
        assertEquals("precision must be more than 0 and less than 1", exception.getMessage());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/mocks/cot.csv")
    public void testCalculateCotUsingCSV(double x, double expected) {
        double result = cotFunction.calculate(x, 0.001);
        assertEquals(expected, result, 0.001);
    }
}
