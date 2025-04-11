import static org.junit.jupiter.api.Assertions.*;

import functions.Sin;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SinTest {
    private final Sin sinFunction = new Sin();

    @Test
    public void testCalculateZero() {
        double result = sinFunction.calculate(0.0, 0.001);
        assertEquals(0.0, result, 0.001);
    }

    @Test
    public void testCalculateQuarterPi() {
        double result = sinFunction.calculate(0.7854, 0.001); // pi/4
        assertEquals(0.707, result, 0.001);
    }

    @Test
    public void testCalculateThirdPi() {
        double result = sinFunction.calculate(1.0472, 0.001); // pi/3
        assertEquals(0.866, result, 0.001);
    }

    @Test
    public void testCalculatePiOverTwo() {
        double result = sinFunction.calculate(1.5708, 0.001); // pi/2
        assertEquals(1.0, result, 0.001);
    }

    @Test
    public void testCalculateNegativeQuarterPi() {
        double result = sinFunction.calculate(-0.7854, 0.001); // -pi/4
        assertEquals(-0.707, result, 0.001);
    }

    @Test
    public void testCalculateNegativeThirdPi() {
        double result = sinFunction.calculate(-1.0472, 0.001); // -pi/3
        assertEquals(-0.866, result, 0.001);
    }

    @Test
    public void testCalculateNegativePiOverTwo() {
        double result = sinFunction.calculate(-1.5708, 0.001); // -pi/2
        assertEquals(-1.0, result, 0.001);
    }

    @Test
    public void testCalculateLargeValue() {
        double result = sinFunction.calculate(100 * Math.PI, 0.001);
        assertEquals(0.0, result, 0.001);
    }

    @Test
    public void testCalculateInvalidPrecisionTooLow() {
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            sinFunction.calculate(Math.PI / 4, -0.1);
        });
        assertEquals("precision must be more than 0 and less than 1", exception.getMessage());
    }

    @Test
    public void testCalculateInvalidPrecisionTooHigh() {
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            sinFunction.calculate(Math.PI / 4, 1.1);
        });
        assertEquals("precision must be more than 0 and less than 1", exception.getMessage());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/mocks/sin.csv")
    public void testCalculateSinUsingCSV(double x, double expected) {
        double result = sinFunction.calculate(x, 0.001);
        assertEquals(expected, result, 0.001);
    }
}
