public class FunctionValues {
    private double x;
    private double sinVal;
    private double cosVal;
    private double tanVal;
    private double cotVal;
    private double secVal;
    private double cscVal;
    private double log2Val;
    private double log5Val;
    private double systemValue;

    public FunctionValues(double x, double sinVal, double cosVal, double tanVal, double cotVal,
                          double secVal, double cscVal, double log2Val, double log5Val, double systemValue) {
        this.x = x;
        this.sinVal = sinVal;
        this.cosVal = cosVal;
        this.tanVal = tanVal;
        this.cotVal = cotVal;
        this.secVal = secVal;
        this.cscVal = cscVal;
        this.log2Val = log2Val;
        this.log5Val = log5Val;
        this.systemValue = systemValue;
    }

    public double getX() { return x; }
    public double getSinVal() { return sinVal; }
    public double getCosVal() { return cosVal; }
    public double getTanVal() { return tanVal; }
    public double getCotVal() { return cotVal; }
    public double getSecVal() { return secVal; }
    public double getCscVal() { return cscVal; }
    public double getLog2Val() { return log2Val; }
    public double getLog5Val() { return log5Val; }
    public double getSystemValue() { return systemValue; }
}