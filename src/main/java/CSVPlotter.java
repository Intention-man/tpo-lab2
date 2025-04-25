import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVPlotter {

    private static final String INPUT_DIR = "src/main/resources/csv/generated";
    private static final String OUTPUT_DIR = "src/main/resources/plots";

    public static void main(String[] args) throws Exception {
        Files.createDirectories(Paths.get(OUTPUT_DIR));

        File[] csvFiles = new File(INPUT_DIR).listFiles(
                (dir, name) -> name.toLowerCase().endsWith(".csv")
        );

        if (csvFiles == null || csvFiles.length == 0) {
            System.err.println("No CSV files found in " + INPUT_DIR);
            return;
        }

        for (File csvFile : csvFiles) {
            List<Double> xData = new ArrayList<>();
            List<Double> yData = new ArrayList<>();


            try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                String line;
                boolean headerSkipped = false;

                while ((line = br.readLine()) != null) {
                    if (!headerSkipped) {
                        headerSkipped = true;
                        continue;
                    }

                    String[] values = line.split(",");
                    double x = Double.parseDouble(values[0]);
                    double y = Double.parseDouble(values[1]);
                    if (Double.isInfinite(y) || Double.isNaN(y)) continue;
                    xData.add(x);
                    yData.add(y);
                }
            }

            XYChart chart = new XYChartBuilder()
                    .width(1600)
                    .height(1200)
                    .title(csvFile.getName())
                    .xAxisTitle("Input")
                    .yAxisTitle("Result")
                    .theme(Styler.ChartTheme.Matlab)
                    .build();

            chart.getStyler()
                    .setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Scatter)
                    .setMarkerSize(5);

            chart.addSeries("Data", xData, yData);

            String outputFileName = csvFile.getName().replace(".csv", ".png");
            Path outputPath = Paths.get(OUTPUT_DIR, outputFileName);
            BitmapEncoder.saveBitmap(chart, outputPath.toString(), BitmapEncoder.BitmapFormat.PNG);

            System.out.println("Saved plot: " + outputPath);
        }
    }
}
