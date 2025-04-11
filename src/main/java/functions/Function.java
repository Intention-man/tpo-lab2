package functions;

public interface Function {
    double calculate(double x, double precision);

    default void validatePrecision(double precision) {
        if (precision <= 0 || precision >= 1) {
            throw new ArithmeticException("precision must be more than 0 and less than 1");
        }
    }

    default double round(double val, int signs) {
        double bigNumber = Math.pow(10, signs);
        double v1 = Math.round(val * bigNumber);
        return v1 / bigNumber;
    }
}