import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;

public class Tan {
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

    public double calculateTan(double x, double eps) {
        double sinValue = sin.calculateSin(x, eps);
        double cosValue = cos.calculateCos(x, eps);
        if (Double.isNaN(sinValue) || Double.isNaN(cosValue) || cosValue == 0) return Double.NaN;
        return sinValue / cosValue;
    }

    public double saveTanToCSV(double x, double eps, Writer out) {
        double result = calculateTan(x, eps);
        try (CSVPrinter printer = CSVFormat.DEFAULT.print(out)) {
            printer.printRecord(x, result);
        } catch (IOException e) {
            System.out.println("Error writing to CSV");
        }
        return result;
    }
}