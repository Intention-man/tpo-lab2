import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;

public class Csc {
    private final Sin sin;

    public Csc(Sin sin) {
        this.sin = sin;
    }

    public Csc() {
        this.sin = new Sin();
    }

    public double calculateCsc(double x, double eps) {
        double sinValue = sin.calculateSin(x, eps);
        if (Double.isNaN(sinValue) || sinValue == 0) return Double.NaN;
        return 1 / sinValue;
    }

    public double saveCscToCSV(double x, double eps, Writer out) {
        double result = calculateCsc(x, eps);
        try (CSVPrinter printer = CSVFormat.DEFAULT.print(out)) {
            printer.printRecord(x, result);
        } catch (IOException e) {
            System.out.println("Error writing to CSV");
        }
        return result;
    }
}