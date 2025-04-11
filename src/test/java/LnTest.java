import functions.Ln;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class LnTest {

    private static Ln lnFunction;

    @BeforeAll
    public static void init() {
        lnFunction = new Ln();
    }

    @Test
    public void testCalculateZero() {
        assertEquals(Double.NEGATIVE_INFINITY, lnFunction.calculate(0.0, 0.001), 0.001);
    }

    @Test
    public void testCalculateNegativeInput() {
        assertThrows(IllegalArgumentException.class, () -> {
            lnFunction.calculate(-1.0, 0.001);
        });
    }

    @Test
    public void testCalculateOne() {
        assertEquals(0.0, lnFunction.calculate(1.0, 0.001), 0.001);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/mocks/ln.csv")
    public void testCalculateLnUsingCSV(double x, double expected) {
        double result = lnFunction.calculate(x, 0.001);
        assertEquals(expected, result, 0.001);
    }
}
