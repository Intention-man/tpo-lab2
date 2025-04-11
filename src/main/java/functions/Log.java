package functions;

public class Log implements Function {

    private final Ln ln;
    private final double base;

    public Log(Ln ln, double base) {
        this.ln = ln;
        this.base = base;
    }

    public Log(double base) {
        this.ln = new Ln();
        this.base = base;
    }

    @Override
    public double calculate(double x, double eps) {
        validatePrecision(eps);
        if (Double.isNaN(x) || x < (double) 0) {
            throw new IllegalArgumentException();
        } else if (x == Double.POSITIVE_INFINITY) {
            return Double.POSITIVE_INFINITY;
        } else if (x == 0.0) {
            return Double.NEGATIVE_INFINITY;
        }
        double baseLn = ln.calculate(base, eps);
        double valueLn = ln.calculate(x, eps);
        return valueLn / baseLn;
    }
}