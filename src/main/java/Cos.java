import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;

public class Cos {
    private final Sin sin;

    public Cos(Sin sin) {
        this.sin = sin;
    }

    public Cos() {
        this.sin = new Sin();
    }

    public double calculateCos(double x, double eps) {
        x = sin.normalizeAngle(x);
        double result = Math.sqrt(1 - Math.pow(sin.calculateSin(x, eps), 2));
        return adjustCosSign(x, result);
    }

    private double adjustCosSign(double x, double result) {
        if (x > Math.PI / 2 || x < -Math.PI / 2) result = -result;
        return (Math.abs(result) > 1) ? Double.NaN : result;
    }

    public double saveCosToCSV(double x, double eps, Writer out) {
        double result = calculateCos(x, eps);
        try (CSVPrinter printer = CSVFormat.DEFAULT.print(out)) {
            printer.printRecord(x, result);
        } catch (IOException e) {
            System.out.println("Error writing to CSV");
        }
        return result;
    }
}