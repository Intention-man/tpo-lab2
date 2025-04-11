import functions.*;

public class Main {
    public static void main(String[] args) {
        double start = -5.0;
        double end = 5.0;
        double step = 0.01;
        double precision = 0.01;

        ResultsCsvWriter.writeToCsv(new Sin(), start, end, step, precision, "sin.csv");
        ResultsCsvWriter.writeToCsv(new Cos(), start, end, step, precision, "cos.csv");
        ResultsCsvWriter.writeToCsv(new Sec(), start, end, step, precision, "sec.csv");
        ResultsCsvWriter.writeToCsv(new Csc(), start, end, step, precision, "csc.csv");
        ResultsCsvWriter.writeToCsv(new Cot(), start, end, step, precision, "cot.csv");
        ResultsCsvWriter.writeToCsv(new Tan(), start, end, step, precision, "tan.csv");

        ResultsCsvWriter.writeToCsv(new Ln(), 0.01, 5.0, step, precision, "ln.csv");
        ResultsCsvWriter.writeToCsv(new Log(2), 0.01, 5.0, step, precision, "log2.csv");
        ResultsCsvWriter.writeToCsv(new Log(5), 0.01, 5.0, step, precision, "log5.csv");
    }
}
