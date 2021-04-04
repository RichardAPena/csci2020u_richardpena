package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Application {

    private final int PORT = 1234;
    @FXML public Button exitButton;
    @FXML TextArea board;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("server.fxml"));
        primaryStage.setTitle("Bulletin Board - Server");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();

        // Accept connections
        Thread acceptConnections = new Thread(() -> {
            try {
                ArrayList<ServerConnection> connections = new ArrayList<>();
                ServerSocket ss = new ServerSocket(PORT);
                System.out.println("Waiting for clients...");
                while (true) {
                    Socket s = ss.accept();
                    ServerConnection sc = new ServerConnection(s);
                    sc.start();
                    connections.add(sc);
                    System.out.println("Client accepted: " + s.getInetAddress().toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        acceptConnections.start();

    }

    public static void main(String[] args) {
        launch(args);
    }

    public void stop() {
        System.exit(0);
    }

}
