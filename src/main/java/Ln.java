import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;

public class Ln {

    public double calculateLn(double x, double eps) {
        if (Double.isNaN(x) || x <= 0) {
            return Double.NaN;
        }

        double sum = 0, term = (x - 1) / (x + 1);
        double constant = term * term, factor = term;
        int n = 1;

        while (Math.abs(term) > eps) {
            sum += term;
            n += 2;
            term = factor * (n - 2) * constant / n;
        }

        return 2 * sum;
    }

    public double saveLnToCSV(double x, double eps, Writer out) {
        double result = calculateLn(x, eps);
        try (CSVPrinter printer = CSVFormat.DEFAULT.print(out)) {
            printer.printRecord(x, result);
        } catch (IOException e) {
            System.out.println("Error writing to CSV");
        }
        return result;
    }
}