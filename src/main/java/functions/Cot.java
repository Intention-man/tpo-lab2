package functions;

public class Cot implements Function {
    private final Sin sin;
    private final Cos cos;

    public Cot(Sin sin, Cos cos) {
        this.sin = sin;
        this.cos = cos;
    }

    public Cot() {
        this.sin = new Sin();
        this.cos = new Cos();
    }

    public double calculate(double x, double eps) {
        x = round(x, 5);
        validatePrecision(eps);
        if (x == 0){
            return Double.POSITIVE_INFINITY;
        }
        double sinValue = sin.calculate(x, eps);
        double cosValue = cos.calculate(x, eps);
        if (Double.isNaN(sinValue) || Double.isNaN(cosValue)) throw new ArithmeticException();
        return cosValue / sinValue;
    }
}