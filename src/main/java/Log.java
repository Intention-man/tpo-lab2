import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;

public class Log {

    private final Ln ln;

    public Log(Ln ln) {
        this.ln = ln;
    }

    public Log() {
        this.ln = new Ln();
    }

    public double calculateLog(double base, double value, double eps) {
        double baseLn = ln.calculateLn(base, eps);
        double valueLn = ln.calculateLn(value, eps);
        return valueLn / baseLn;
    }

    public double saveLogToCSV(double base, double value, double eps, Writer out) {
        double result = calculateLog(base, value, eps);
        try (CSVPrinter printer = CSVFormat.DEFAULT.print(out)) {
            printer.printRecord(value, result);
        } catch (IOException e) {
            System.out.println("Error writing to CSV");
        }
        return result;
    }
}