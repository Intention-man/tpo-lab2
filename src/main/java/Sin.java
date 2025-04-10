import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;

public class Sin {

    public double calculateSin(double x, double eps) {
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
        if (x < -Math.PI) x += 2 * Math.PI;
        if (x > Math.PI) x -= 2 * Math.PI;
        return x;
    }

    public double saveSinToCSV(double x, double eps, Writer out) {
        double result = calculateSin(x, eps);
        try (CSVPrinter printer = CSVFormat.DEFAULT.print(out)) {
            printer.printRecord(x, result);
        } catch (IOException e) {
            System.out.println("Error writing to CSV");
        }
        return result;
    }
}