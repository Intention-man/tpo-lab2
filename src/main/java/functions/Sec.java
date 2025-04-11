package functions;

public class Sec implements Function {
    private final Cos cos;

    public Sec(Cos cos) {
        this.cos = cos;
    }

    public Sec() {
        this.cos = new Cos();
    }

    public double calculate(double x, double eps) {
        validatePrecision(eps);
        x = round(x, 5);
        double cosValue = cos.calculate(x, eps);
        if (Double.isNaN(cosValue)) return Double.NaN;
        return 1 / cosValue;
    }
}