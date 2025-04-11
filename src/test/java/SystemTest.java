import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import functions.*;
import functions.System;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

@ExtendWith(MockitoExtension.class)
public class SystemTest {

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

        when(sinMock.calculate(-1.0472, 0.001)).thenReturn(-0.866);
        when(cosMock.calculate(-1.0472, 0.001)).thenReturn(0.5);
        when(cotMock.calculate(-1.0472, 0.001)).thenReturn(-0.57737);
        when(secMock.calculate(-1.0472, 0.001)).thenReturn(2.001881);
        when(cscMock.calculate(-1.0472, 0.001)).thenReturn(-1.1543391);
        when(tanMock.calculate(-1.0472, 0.001)).thenReturn(-1.734222);
        lenient().when(log2Mock.calculate(-1.0472, 0.001)).thenReturn(Double.NaN);
        lenient().when(log5Mock.calculate(-1.0472, 0.001)).thenReturn(Double.NaN);

        System system = new System(cotMock, cosMock, sinMock, secMock, cscMock, tanMock, log2Mock, log5Mock);

        double result = system.calculate(-1.0472, 0.001);
        double expected = 4.72116;
        assertEquals(expected, result, 0.001);
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

        when(sinMock.calculate(-1.0472, 0.001)).thenReturn(-0.866);
        when(cosMock.calculate(-1.0472, 0.001)).thenReturn(0.5);
        when(cotMock.calculate(-1.0472, 0.001)).thenReturn(-0.57737);
        when(secMock.calculate(-1.0472, 0.001)).thenReturn(2.001881);
        when(cscMock.calculate(-1.0472, 0.001)).thenReturn(-1.1543391);
        when(tanMock.calculate(-1.0472, 0.001)).thenReturn(-1.734222);
        System system = new System(cotMock, cosMock, sinMock, secMock, cscMock, tanMock, log2Real, log5Real);

        double result = system.calculate(-1.0472, 0.001);
        double expected = 4.72116;
        assertEquals(expected, result, 0.001);
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

        when(cosMock.calculate(-1.0472, 0.001)).thenReturn(0.5);
        when(cotMock.calculate(-1.0472, 0.001)).thenReturn(-0.57737);
        when(secMock.calculate(-1.0472, 0.001)).thenReturn(2.001881);
        when(cscMock.calculate(-1.0472, 0.001)).thenReturn(-1.1543391);
        when(tanMock.calculate(-1.0472, 0.001)).thenReturn(-1.734222);
        System system = new System(cotMock, cosMock, sinReal, secMock, cscMock, tanMock, log2Real, log5Real);

        double result = system.calculate(-1.0472, 0.001);
        double expected = 4.72116;
        assertEquals(expected, result, 0.001);
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

        when(cotMock.calculate(-1.0472, 0.001)).thenReturn(-0.57737);
        when(secMock.calculate(-1.0472, 0.001)).thenReturn(2.001881);
        when(cscMock.calculate(-1.0472, 0.001)).thenReturn(-1.1543391);
        when(tanMock.calculate(-1.0472, 0.001)).thenReturn(-1.734222);

        System system = new System(cotMock, cosReal, sinReal, secMock, cscMock, tanMock, log2Real, log5Real);

        double result = system.calculate(-1.0472, 0.001);
        double expected = 4.72116;
        assertEquals(expected, result, 0.001);
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

        System system = new System(cotReal, cosReal, sinReal, secReal , cscReal , tanReal , log2Real, log5Real);

        double result = system.calculate(-1.0472, 0.001);
        double expected = 4.72116;
        assertEquals(expected, result, 0.001);
    }
}
