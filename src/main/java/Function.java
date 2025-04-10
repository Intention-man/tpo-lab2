import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;

public class Function {
    Cot cot;
    Cos cos;
    Sin sin;
    Sec sec;
    Csc csc;
    Tan tan;
    Log log;

    public Function() {
        this.cot = new Cot();
        this.cos = new Cos();
        this.sin = new Sin();
        this.sec = new Sec();
        this.csc = new Csc();
        this.tan = new Tan();
        this.log = new Log();
    }

    public Function(Cot cot, Cos cos, Sin sin, Sec sec, Csc csc, Tan tan, Log log) {
        this.cot = cot;
        this.cos = cos;
        this.sin = sin;
        this.sec = sec;
        this.csc = csc;
        this.tan = tan;
        this.log = log;
    }

    public double evaluate(double x, double eps) {
        if (x <= 0) {
            double cotVal = cot.calculateCot(x, eps);
            double cosVal = cos.calculateCos(x, eps);
            double sinVal = sin.calculateSin(x, eps);
            double secVal = sec.calculateSec(x, eps);
            double cscVal = csc.calculateCsc(x, eps);
            double tanVal = tan.calculateTan(x, eps);

            double sumParts = cotVal - cosVal + 3 * sinVal + secVal;
            double squaredPart = Math.pow(sumParts, 2);
            double combinedCsc = cscVal - (tanVal * cscVal);
            double denominator = (1.0) / (cscVal / sinVal);;
            double sinCosCubed = Math.pow(sinVal / cosVal, 3);

            return ((squaredPart + combinedCsc) / denominator) - sinCosCubed;
        } else {
            double log5 = log.calculateLog(5, x, eps);
            double log2 = log.calculateLog(2, x, eps);
            double term = Math.pow(log5, 3);

            return Math.pow((term * log5) / (log5 / log2), 2);
        }
    }

    public double saveResultToCSV(double x, double eps, Writer out) {
        double res = evaluate(x, eps);
        try (CSVPrinter printer = CSVFormat.DEFAULT.print(out)) {
            printer.printRecord(x, res);
        } catch (IOException e) {
            System.out.println("Error writing to CSV");
        }
        return res;
    }
}
