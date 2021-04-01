package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

public class Main extends Application {

    int windowLength = 600;
    int windowHeight = 600;

    int period1 = 1262322000;
    int period2 = 1451538000;
    String interval = "1mo";
    String events = "history";
    boolean includeAdjustedClose = true;
    String stockSymbol = "GOOG";
    //String[] tokenArr;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Group root = new Group();
        Canvas canvas = new Canvas( 600, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);

        downloadStockPrices(gc, stockSymbol);

        primaryStage.setTitle("Lab 9");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public void downloadStockPrices(GraphicsContext gc, String stockTickerSymbol) throws IOException {
        // https://query1.finance.yahoo.com/v7/finance/download/GOOG?period1=1262322000&period2=1451538000&interval=1mo&events=history&includeAdjustedClose=true
        String query = "https://query1.finance.yahoo.com/v7/finance/download/" + stockTickerSymbol +
                "?period1=" + period1 +
                "&period2=" + period2 +
                "&interval=" + interval +
                "&events=" + events +
                "&includeAdjustedClose=" + includeAdjustedClose;

        URL queryURL = new URL(query);
        URLConnection conn = queryURL.openConnection();
        conn.setDoOutput(false);
        conn.setDoInput(true);

        InputStream inStream = conn.getInputStream();
        Scanner scan = new Scanner(inStream);

        ArrayList<Float> arrList1 = new ArrayList<>();
        ArrayList<Float> arrList2 = new ArrayList<>();

        String token = scan.nextLine();
        while (scan.hasNext()) {
            token = scan.nextLine(); // has a whole line from csv
            String[] tokenArr = token.split(",");
            arrList1.add(Float.parseFloat(tokenArr[4]));
            System.out.println(token);
        }
        stockTickerSymbol = "AAPL";
        String query2 = "https://query1.finance.yahoo.com/v7/finance/download/" + stockTickerSymbol +
                "?period1=" + period1 +
                "&period2=" + period2 +
                "&interval=" + interval +
                "&events=" + events +
                "&includeAdjustedClose=" + includeAdjustedClose;
        URL queryURL2 = new URL(query2);
        URLConnection conn2 = queryURL2.openConnection();
        conn2.setDoOutput(false);
        conn2.setDoInput(true);

        InputStream inStream2 = conn2.getInputStream();
        Scanner scan2 = new Scanner(inStream2);

        System.out.print("SUS");
        token = scan2.nextLine();
        while (scan2.hasNext()) {
            token = scan2.nextLine(); // has a whole line from csv
            String[] tokenArr = token.split(",");
            arrList2.add(Float.parseFloat(tokenArr[4]));
            System.out.println(token);
        }

        inStream.close();

        float[] arr1 = new float[arrList1.size()];
        float[] arr2 = new float[arrList2.size()];
        for (int i=0; i<arrList1.size(); i++) {
            arr1[i] = arrList1.get(i);
        }
        for (int i=0; i<arrList2.size(); i++) {
            arr2[i] = arrList2.get(i);
        }
        drawLinePlot(gc, arr1, arr2);
    }

    public void drawLinePlot(GraphicsContext gc, float[] arr1, float[] arr2) {
        gc.setFill(Color.BLACK);
        gc.strokeLine(50, 50, 50, gc.getCanvas().getHeight()-50);
        gc.strokeLine(50, gc.getCanvas().getHeight()-50, gc.getCanvas().getWidth()-50, gc.getCanvas().getHeight()-50);

        plotLine(arr1, gc); // Stonk 1
        plotLine(arr2, gc); // Stonk 2
    }

    public void plotLine(float[] arr, GraphicsContext gc) {
        gc.setFill(Color.BLUE);
        for (int i=0; i<arr.length-1; i++) {
            gc.strokeLine(50+i*((windowLength-100)/(arr.length-1)), windowHeight-50-(arr[i]/2),50+(1+i)*((windowLength-100)/(arr.length-1)),windowHeight-50-(arr[i+1]/2));
        }
    }
}
