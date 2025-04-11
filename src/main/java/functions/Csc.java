package functions;

public class Csc implements Function {
    private final Sin sin;

    public Csc(Sin sin) {
        this.sin = sin;
    }

    public Csc() {
        this.sin = new Sin();
    }

    public double calculate(double x, double eps) {
        validatePrecision(eps);
        x = round(x, 5);
        double sinValue = sin.calculate(x, eps);
        if (Double.isNaN(sinValue) || sinValue == 0) throw new ArithmeticException();
        return 1 / sinValue;
    }
}