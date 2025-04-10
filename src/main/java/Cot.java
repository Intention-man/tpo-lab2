import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;

public class Cot {
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

    public double calculateCot(double x, double eps) {
        double sinValue = sin.calculateSin(x, eps);
        double cosValue = cos.calculateCos(x, eps);
        if (Double.isNaN(sinValue) || Double.isNaN(cosValue) || sinValue == 0) return Double.NaN;
        return cosValue / sinValue;
    }

    public double saveCotToCSV(double x, double eps, Writer out) {
        double result = calculateCot(x, eps);
        try (CSVPrinter printer = CSVFormat.DEFAULT.print(out)) {
            printer.printRecord(x, result);
        } catch (IOException e) {
            System.out.println("Error writing to CSV");
        }
        return result;
    }
}