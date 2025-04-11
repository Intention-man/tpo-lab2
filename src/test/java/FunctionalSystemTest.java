import functions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FunctionalSystemTest {

    FunctionValuesList list = new FunctionValuesList();
    List<FunctionValues> data = list.getTestData();


    @Test
    public void testCalculateWithLnReal() {
        Cot cotMock = mock(Cot.class);
        Sec secMock = mock(Sec.class);
        Csc cscMock = mock(Csc.class);
        Ln lnReal = new Ln();
        Log log2Mock = mock(Log.class);
        Log log5Mock = mock(Log.class);
        Cos cosMock = mock(Cos.class);
        Sin sinMock = mock(Sin.class);
        Tan tanMock = mock(Tan.class);

        for (FunctionValues values : data) {
            double x = values.getX();
            if (x <= 0) {
                when(sinMock.calculate(x, 0.001)).thenReturn(values.getSinVal());
                when(cosMock.calculate(x, 0.001)).thenReturn(values.getCosVal());
                when(cotMock.calculate(x, 0.001)).thenReturn(values.getCotVal());
                when(secMock.calculate(x, 0.001)).thenReturn(values.getSecVal());
                when(cscMock.calculate(x, 0.001)).thenReturn(values.getCscVal());
                when(tanMock.calculate(x, 0.001)).thenReturn(values.getTanVal());
            } else {
                when(log2Mock.calculate(x, 0.001)).thenReturn(values.getLog2Val());
                when(log5Mock.calculate(x, 0.001)).thenReturn(values.getLog5Val());
            }
            FunctionalSystem functionalSystem = new FunctionalSystem(cotMock, cosMock, sinMock, secMock, cscMock, tanMock, log2Mock, log5Mock, 3);

            double result = functionalSystem.calculate(x, 0.001);
            double expected = values.getSystemValue();
            assertEquals(expected, result, 0.001);
        }
    }

    @Test
    public void testCalculateWithLnLog2Log5Real() {
        Cot cotMock = mock(Cot.class);
        Sec secMock = mock(Sec.class);
        Csc cscMock = mock(Csc.class);
        Ln lnReal = new Ln();
        Log log2Real = new Log(new Ln(), 2);
        Log log5Real = new Log(new Ln(), 5);
        Cos cosMock = mock(Cos.class);
        Sin sinMock = mock(Sin.class);
        Tan tanMock = mock(Tan.class);

        for (FunctionValues values : data) {
            double x = values.getX();
            if (x <= 0) {
                when(sinMock.calculate(x, 0.001)).thenReturn(values.getSinVal());
                when(cosMock.calculate(x, 0.001)).thenReturn(values.getCosVal());
                when(cotMock.calculate(x, 0.001)).thenReturn(values.getCotVal());
                when(secMock.calculate(x, 0.001)).thenReturn(values.getSecVal());
                when(cscMock.calculate(x, 0.001)).thenReturn(values.getCscVal());
                when(tanMock.calculate(x, 0.001)).thenReturn(values.getTanVal());
            }

            FunctionalSystem functionalSystem = new FunctionalSystem(cotMock, cosMock, sinMock, secMock, cscMock, tanMock, log2Real, log5Real, 3);

            double result = functionalSystem.calculate(x, 0.001);
            double expected = values.getSystemValue();
            assertEquals(expected, result, 0.001);
        }
    }

    @Test
    public void testCalculateWithLnLog2Log5SinReal() {
        Cot cotMock = mock(Cot.class);
        Sec secMock = mock(Sec.class);
        Csc cscMock = mock(Csc.class);
        Ln lnReal = new Ln();
        Log log2Real = new Log(new Ln(), 2);
        Log log5Real = new Log(new Ln(), 5);
        Cos cosMock = mock(Cos.class);
        Sin sinReal = new Sin();
        Tan tanMock = mock(Tan.class);

        for (FunctionValues values : data) {
            double x = values.getX();
            if (x <= 0) {
                when(cosMock.calculate(x, 0.001)).thenReturn(values.getCosVal());
                when(cotMock.calculate(x, 0.001)).thenReturn(values.getCotVal());
                when(secMock.calculate(x, 0.001)).thenReturn(values.getSecVal());
                when(cscMock.calculate(x, 0.001)).thenReturn(values.getCscVal());
                when(tanMock.calculate(x, 0.001)).thenReturn(values.getTanVal());
            }

            FunctionalSystem functionalSystem = new FunctionalSystem(cotMock, cosMock, sinReal, secMock, cscMock, tanMock, log2Real, log5Real, 3);

            double result = functionalSystem.calculate(x, 0.001);
            double expected = values.getSystemValue();
            assertEquals(expected, result, 0.001);
        }
    }

    @Test
    public void testCalculateWithLnLog2Log5SinCosReal() {
        Cot cotMock = mock(Cot.class);
        Sec secMock = mock(Sec.class);
        Csc cscMock = mock(Csc.class);
        Ln lnReal = new Ln();
        Log log2Real = new Log(new Ln(), 2);
        Log log5Real = new Log(new Ln(), 5);
        Cos cosReal = new Cos();
        Sin sinReal = new Sin();
        Tan tanMock = mock(Tan.class);

        for (FunctionValues values : data) {
            double x = values.getX();
            if (x <= 0) {
                when(cotMock.calculate(x, 0.00001)).thenReturn(values.getCotVal());
                when(secMock.calculate(x, 0.00001)).thenReturn(values.getSecVal());
                when(cscMock.calculate(x, 0.00001)).thenReturn(values.getCscVal());
                when(tanMock.calculate(x, 0.00001)).thenReturn(values.getTanVal());
            }

            FunctionalSystem functionalSystem = new FunctionalSystem(cotMock, cosReal, sinReal, secMock, cscMock, tanMock, log2Real, log5Real, 5);

            double result = functionalSystem.calculate(x, 0.00001);
            double expected = values.getSystemValue();
            assertEquals(expected, result, 0.01);
        }
    }

    @Test
    public void testCalculateWithAllReal() {
        Cot cotReal = new Cot();
        Sec secReal = new Sec();
        Csc cscReal = new Csc();
        Ln lnReal = new Ln();
        Log log2Real = new Log(new Ln(), 2);
        Log log5Real = new Log(new Ln(), 5);
        Cos cosReal = new Cos();
        Sin sinReal = new Sin();
        Tan tanReal = new Tan();

        for (FunctionValues values : data) {
            double x = values.getX();

            FunctionalSystem functionalSystem = new FunctionalSystem(cotReal, cosReal, sinReal, secReal, cscReal, tanReal, log2Real, log5Real, 4);

            double result = functionalSystem.calculate(x, 0.000000001);
            double expected = values.getSystemValue();
            assertEquals(expected, result, 0.1);
        }
    }
}
