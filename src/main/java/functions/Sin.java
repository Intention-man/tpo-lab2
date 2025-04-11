package functions;

public class Sin implements Function {

    public double calculate(double x, double eps) {
        validatePrecision(eps);
        x = normalizeAngle(x);
        if (x == 0) {
            return 0;
        } else if (Math.abs(x - Math.PI / 2) < 0.001) {
            return 1;
        } else if (Math.abs(x + Math.PI / 2) < 0.001) {
            return -1;
        }

        if (Double.isInfinite(x)) {
            return Double.NaN;
        }

        double term = x, result = 0;
        int n = 1;

        while (Math.abs(term) > eps) {
            result += term;
            n += 2;
            term *= -x * x / ((n - 1) * n);
        }

        return (Math.abs(result) > 1) ? Double.NaN : round(result, 6);
    }

    double normalizeAngle(double x) {
        x %= 2 * Math.PI;
        while (x < -Math.PI) x += 2 * Math.PI;
        while (x > Math.PI) x -= 2 * Math.PI;
        return round(x, 6);
    }
}