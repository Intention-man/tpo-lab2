import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;

public class Sec {
    private final Cos cos;

    public Sec(Cos cos) {
        this.cos = cos;
    }

    public Sec() {
        this.cos = new Cos();
    }

    public double calculateSec(double x, double eps) {
        double cosValue = cos.calculateCos(x, eps);
        if (Double.isNaN(cosValue) || cosValue == 0) return Double.NaN;
        return 1 / cosValue;
    }

    public double saveSecToCSV(double x, double eps, Writer out) {
        double result = calculateSec(x, eps);
        try (CSVPrinter printer = CSVFormat.DEFAULT.print(out)) {
            printer.printRecord(x, result);
        } catch (IOException e) {
            System.out.println("Error writing to CSV");
        }
        return result;
    }
}