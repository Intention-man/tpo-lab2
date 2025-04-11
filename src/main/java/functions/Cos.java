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
        x = round(x, 5);
        double result = Math.sqrt(1 - Math.pow(sin.calculate(x, eps), 2));
        return adjustCosSign(x, result);
    }

    private double adjustCosSign(double x, double result) {
        if (x > Math.PI / 2 || x < -Math.PI / 2) result = -result;
        return (Math.abs(result) > 1) ? Double.NaN : result;
    }
}