package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;
import java.io.File;
import java.util.Scanner;
import java.util.TreeMap;

import javafx.scene.paint.Color;

public class Main extends Application {

    File weather;
    TreeMap<String, Double> weatherMap;
    double total = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        weather = new File("src\\sample\\weatherwarnings-2015.csv");
        weatherMap = new TreeMap<>();
        weatherMap.put("FLASH FLOOD", 0.0);
        weatherMap.put("SEVERE THUNDERSTORM", 0.0);
        weatherMap.put("SPECIAL MARINE", 0.0);
        weatherMap.put("TORNADO", 0.0);

        Scanner scan = new Scanner(weather);
        while (scan.hasNext()) {
            String token = scan.nextLine();
            String[] array = token.split(",");
            for (String s : array) {
                if (weatherMap.containsKey(s)) {
                    weatherMap.put(s, weatherMap.get(s) + 1);
                    total++;
                }
            }
        }

        Group root = new Group();
        Canvas canvas = new Canvas( 700, 300);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        primaryStage.setTitle("Lab 7");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        drawGraph(gc);
    }


    public static void main(String[] args) {
        launch(args);
    }

    public void drawGraph(GraphicsContext gc) {

        gc.setFill(Color.AQUA);
        gc.fillRect(20, 20, 60, 40);
        gc.fillArc(400, 50, 200, 200, 0, weatherMap.get("FLASH FLOOD")/total*360, ArcType.ROUND);

        gc.setFill(Color.GOLD);
        gc.fillRect(20, 80, 60, 40);
        gc.fillArc(400, 50, 200, 200, weatherMap.get("FLASH FLOOD")/total*360, weatherMap.get("SEVERE THUNDERSTORM")/total*360, ArcType.ROUND);

        gc.setFill(Color.DARKORANGE);
        gc.fillRect(20, 140, 60, 40);
        gc.fillArc(400, 50, 200, 200, (weatherMap.get("FLASH FLOOD")+weatherMap.get("SEVERE THUNDERSTORM"))/total*360, weatherMap.get("SPECIAL MARINE")/total*360, ArcType.ROUND);

        gc.setFill(Color.DARKSALMON);
        gc.fillRect(20, 200, 60, 40);
        gc.fillArc(400, 50, 200, 200, (weatherMap.get("FLASH FLOOD")+weatherMap.get("SEVERE THUNDERSTORM")+weatherMap.get("SPECIAL MARINE"))/total*360, weatherMap.get("TORNADO")/total*360, ArcType.ROUND);

        gc.setFill(Color.BLACK);
        gc.strokeText("FLASH FLOOD", 90, 40);
        gc.strokeText("SEVERE THUNDERSTORM", 90, 100);
        gc.strokeText("SPECIAL MARINE", 90, 160);
        gc.strokeText("TORNADO", 90, 220);
        gc.strokeRect(20, 20, 60, 40);
        gc.strokeRect(20, 80, 60, 40);
        gc.strokeRect(20, 140, 60, 40);
        gc.strokeRect(20, 200, 60, 40);
        gc.strokeOval(400, 50, 200, 200);
        gc.strokeLine(500, 150, 500+100*Math.cos(0), 150-100*Math.sin(0));
        double var1 = (weatherMap.get("FLASH FLOOD"))/total*360;
        double var2 = (weatherMap.get("FLASH FLOOD")+weatherMap.get("SEVERE THUNDERSTORM"))/total*360;
        double var3 = (weatherMap.get("FLASH FLOOD")+weatherMap.get("SEVERE THUNDERSTORM")+weatherMap.get("SPECIAL MARINE"))/total*360;
        gc.strokeLine(500, 150, 500+100*Math.cos(Math.toRadians(var1)), 150-100*Math.sin(Math.toRadians(var1)));
        gc.strokeLine(500, 150, 500+100*Math.cos(Math.toRadians(var2)), 150-100*Math.sin(Math.toRadians(var2)));
        gc.strokeLine(500, 150, 500+100*Math.cos(Math.toRadians(var3)), 150-100*Math.sin(Math.toRadians(var3)));

    }
}
