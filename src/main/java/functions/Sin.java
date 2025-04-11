package functions;

public class Sin implements Function {

    public double calculate(double x, double eps) {
        validatePrecision(eps);
        x = round(x, 5);
        if (Double.isInfinite(x)) {
            return Double.NaN;
        }

        x = normalizeAngle(x);
        double term = x, result = 0;
        int n = 1;

        while (Math.abs(term) > eps) {
            result += term;
            n += 2;
            term *= -x * x / ((n - 1) * n);
        }

        return (Math.abs(result) > 1) ? Double.NaN : result;
    }

    double normalizeAngle(double x) {
        x %= 2 * Math.PI;
        while (x < -Math.PI) x += 2 * Math.PI;
        while (x > Math.PI) x -= 2 * Math.PI;
        return x;
    }
}