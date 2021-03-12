package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

import java.util.stream.IntStream;

public class Main extends Application {

    private static double[] avgHousingPricesByYear = { 247381.0, 264171.4, 287715.3, 294736.1, 308431.4, 322635.9, 340253.0, 363153.7};
    private static double[] avgCommercialPricesByYear = { 1121585.3, 1219479.5, 1246354.2, 1295364.8, 1335932.6, 1472362.0, 1583521.9, 1613246.3};

    private static String[] ageGroups = { "18-25", "26-35", "36-45", "46-55", "56-65", "65+"};
    private static int[] purchasesByAgeGroup = { 648, 1021, 2453, 3173, 1868, 2247 };
    private static Color[] pieColours = { Color.AQUA, Color.GOLD, Color.DARKORANGE, Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM};

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Group root = new Group();
        Canvas canvas = new Canvas(960, 720);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawGraph(gc); // Draw graph
        root.getChildren().add(canvas);
        primaryStage.setTitle("Lab 6");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void drawGraph(GraphicsContext gc) {
        double barScale = 0.0002;
        double barWidth = 20;
        double barGap = 50;
        double initialX = 20;
        double initialY = 300;

        // Draw bar graph
        gc.setFill(Color.RED);
        for (int i=0; i<avgHousingPricesByYear.length; i++) {
            gc.setFill(Color.RED);
            gc.fillRect(initialX+(barGap*i), initialY+(1613246.3-avgHousingPricesByYear[i])*barScale, barWidth, avgHousingPricesByYear[i]*barScale);
            gc.setFill(Color.BLUE);
            gc.fillRect(initialX+20+(barGap*i), initialY+(1613246.3-avgCommercialPricesByYear[i])*barScale, barWidth, avgCommercialPricesByYear[i]*barScale);
        }

        double total = IntStream.of(purchasesByAgeGroup).sum();
        double temp = 0;
        for (int i=0; i<purchasesByAgeGroup.length; i++) {
            gc.setFill(pieColours[i]);
            gc.fillArc(500, 400, 200, 200, temp, purchasesByAgeGroup[i]/total*360, ArcType.ROUND);
            temp+=purchasesByAgeGroup[i]/total*360;
        }

    }
}
