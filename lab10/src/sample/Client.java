package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Application {

    @FXML Button send;
    @FXML Button exit;
    @FXML TextField username;
    @FXML TextField message;
    Socket s;
    PrintWriter out;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("client.fxml"));
        primaryStage.setTitle("Bulletin Board - Client");
        primaryStage.setScene(new Scene(root, 300, 200));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void stop() {
        System.exit(0);
    }

    public void exit() {
        System.exit(0);
    }

    public void sendMessage() {
        try {
            s = new Socket("localhost", 1234);
            out = new PrintWriter(s.getOutputStream(), true);
            out.println(username.getText() + ": " + message.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
