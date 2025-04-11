package functions;

public class System implements Function {
    Cot cot;
    Cos cos;
    Sin sin;
    Sec sec;
    Csc csc;
    Tan tan;
    Log log2;
    Log log5;

    public System() {
        this.cot = new Cot();
        this.cos = new Cos();
        this.sin = new Sin();
        this.sec = new Sec();
        this.csc = new Csc();
        this.tan = new Tan();
        this.log2 = new Log(2);
        this.log5 = new Log(5);
    }

    public System(Cot cot, Cos cos, Sin sin, Sec sec, Csc csc, Tan tan, Log log2, Log log5) {
        this.cot = cot;
        this.cos = cos;
        this.sin = sin;
        this.sec = sec;
        this.csc = csc;
        this.tan = tan;
        this.log2 = log2;
        this.log5 = log5;
    }

    public double calculate(double x, double eps) {
        if (x <= 0) {
            double cotVal = round(cot.calculate(x, eps), 3);
            double cosVal = round(cos.calculate(x, eps), 3);
            double sinVal = round(sin.calculate(x, eps), 3);
            double secVal = round(sec.calculate(x, eps), 3);
            double cscVal = round(csc.calculate(x, eps), 3);
            double tanVal = round(tan.calculate(x, eps), 3);

            double sumParts = cotVal - cosVal + 3 * sinVal + secVal;
            double squaredPart = Math.pow(sumParts, 2);
            double combinedCsc = cscVal - (tanVal * cscVal);
            double denominator = (1.0) / (cscVal / sinVal);
            double sinCosCubed = Math.pow(sinVal / cosVal, 3);

            return ((squaredPart + combinedCsc) / denominator) - sinCosCubed;
        } else {
            double log5Val = log5.calculate(x, eps);
            double log2Val = log2.calculate(x, eps);
            double term = Math.pow(log5Val, 3);

            return Math.pow((term * log5Val) / (log5Val / log2Val), 2);
        }
    }
}
