package functions;

public class Cos implements Function {
    private final Sin sin;

    public Cos(Sin sin) {
        this.sin = sin;
    }

    public Cos() {
        this.sin = new Sin();
    }

    public double calculate(double x, double eps) {
        validatePrecision(eps);
        if (x == 0) {
            return 1;
        } else if (Math.abs(x - Math.PI / 2) < 0.001) {
            return 0;
        } else if (Math.abs(x + Math.PI / 2) < 0.001) {
            return 0;
        }
        double sinVal = round(sin.calculate(x, eps), 6);
        double result = Math.sqrt(1 - Math.pow(sinVal, 2));
        return adjustCosSign(x, result);
    }

    private double adjustCosSign(double x, double result) {
        if (x > Math.PI / 2 || x < -Math.PI / 2) result = -result;
        return (Math.abs(result) > 1) ? Double.NaN : result;
    }
}