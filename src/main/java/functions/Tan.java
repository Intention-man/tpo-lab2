package functions;

public class Tan implements Function {
    private final Sin sin;
    private final Cos cos;

    public Tan(Sin sin, Cos cos) {
        this.sin = sin;
        this.cos = cos;
    }

    public Tan() {
        this.sin = new Sin();
        this.cos = new Cos();
    }

    public double calculate(double x, double eps) {
        validatePrecision(eps);
        x = round(x, 5);
        double sinValue = sin.calculate(x, eps);
        double cosValue = cos.calculate(x, eps);
        if (Double.isNaN(sinValue) || Double.isNaN(cosValue)) return Double.NaN;
        return sinValue / cosValue;
    }
}