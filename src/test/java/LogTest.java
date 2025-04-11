import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import functions.Ln;
import functions.Log;
import functions.Sin;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import java.io.FileReader;
import java.io.Reader;

@ExtendWith(MockitoExtension.class)
public class LogTest {

    private static Ln mockLnFunction;
    private Log logFunctionBase2;
    private Log logFunctionBase5;

    @BeforeAll
    public static void init() throws Exception {
        mockLnFunction = Mockito.mock(Ln.class);

        try (Reader in = new FileReader("src/main/resources/csv/mocks/ln.csv")) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
            for (CSVRecord record : records) {
                double x = Double.parseDouble(record.get(0));
                double lnValue = Double.parseDouble(record.get(1));
                Mockito.when(mockLnFunction.calculate(x, 0.001)).thenReturn(lnValue);
            }
        }
    }

    @BeforeEach
    public void setUp() {
        logFunctionBase2 = new Log(mockLnFunction, 2.0);
        logFunctionBase5 = new Log(mockLnFunction, 5.0);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/mocks/log2.csv")
    public void testCalculateLog2UsingCSV(double x, double expected) {
        double result = logFunctionBase2.calculate(x, 0.001);
        assertEquals(expected, result, 0.001);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/mocks/log5.csv")
    public void testCalculateLog5UsingCSV(double x, double expected) {
        double result = logFunctionBase5.calculate(x, 0.001);
        assertEquals(expected, result, 0.001);
    }

    @Test
    public void testCalculateLogOne() {
        assertEquals(0.0, logFunctionBase2.calculate(1.0, 0.001), 0.001);
        assertEquals(0.0, logFunctionBase5.calculate(1.0, 0.001), 0.001);
    }

    @Test
    public void testCalculateLogInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> {
            logFunctionBase5.calculate(-1.0, 0.001);
        });
        assertEquals(Double.NEGATIVE_INFINITY, logFunctionBase5.calculate(0.0, 0.001), 0.001);
    }
}
