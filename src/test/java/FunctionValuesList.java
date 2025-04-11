import java.util.Arrays;
import java.util.List;

public class FunctionValuesList {

    public List<FunctionValues> getTestData() {
        return Arrays.asList(
//                new FunctionValues(-Math.PI/2 - 2*Math.PI, -1, 0, Double.NaN, 0, Double.NaN, -1, Double.NaN, Double.NaN, Double.POSITIVE_INFINITY),
//
//                new FunctionValues(-Math.PI/3, -0.866296, 0.49953, -1.734222,-0.57737, 2.001881, -1.1543391, Double.NaN, Double.NaN, 4.721159974004619),
//                new FunctionValues(-Math.PI / 2, -1, 0, Double.NaN, 0, Double.NaN, -1, Double.NaN, Double.NaN, Double.POSITIVE_INFINITY),
//                new FunctionValues(-Math.PI, 0, -1, 0,  Double.NaN, -1,  Double.NaN, Double.NaN, Double.NaN, Double.NaN),
//
//                new FunctionValues(-Math.PI - 4*Math.PI, 0, -1, 0,  Double.NaN, -1,  Double.NaN, Double.NaN, Double.NaN, Double.NaN),

                new FunctionValues(0, 0, 1, 0, Double.NaN, 1, Double.NaN, Double.NEGATIVE_INFINITY,  Double.NEGATIVE_INFINITY, Double.NaN),
                new FunctionValues(2, -0.4161, 0.9093, -2.1850, -0.4577, -2.4028, 1.1006, 1, 0.4307, 0),
                new FunctionValues(5, 0.2837, -0.9589, -3.3805, -0.2958, 3.5253, -1.0427, 2.3219, 1, 5.392)
        );
    }
}