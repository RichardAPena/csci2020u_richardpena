package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerConnection extends Thread {

    private final BufferedReader in;

    public ServerConnection(Socket s) throws IOException {
        in = new BufferedReader(new InputStreamReader(s.getInputStream()));
    }

    public void run() {
        try {
            String message = in.readLine();
            System.out.print(message);
            // TODO: make it so that it puts message in TextArea
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
