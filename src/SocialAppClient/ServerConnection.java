package SocialAppClient;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.*;
import java.net.Socket;

/**
 * Created by kemo on 22/10/2016.
 */
public class ServerConnection extends Thread {
    Pane scene;
    public ServerConnection(Pane scene) {
        //TODO
        this.scene = scene;
    }

    @Override
    public void run() {
        super.run();
        connect();
    }

    private void connect() {

        String[] args = {"LIP-LIP", "6060"};
        String serverName = args[0];
        int port = Integer.parseInt(args[1]);
        try {
            System.out.println("Connecting to " + serverName +
                    " on port " + port);
            Socket client = new Socket(serverName, port);
            System.out.println(" connected to "
                    + client.getRemoteSocketAddress());

            ReadInput readInput = new ReadInput();
            readInput.client = client;
            readInput.start();
//            Servertask servertask = new Servertask();
//            servertask.call();
            while (true) {
                OutputStream outToServer = client.getOutputStream();
                DataOutputStream out = new DataOutputStream(outToServer);
                //out.writeUTF(new Scanner(System.in).nextLine());
                out.writeUTF("a");
            }
            //  client.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class ReadInput extends Thread {
        public Socket client;

        @Override
        public void run() {
            super.run();
            int i = 0;
            while (i < 2000) {
                try {
                    InputStream inFromServer = client.getInputStream();
                    DataInputStream in =
                            new DataInputStream(inFromServer);
                   //scene.setLayoutY(in.read());
                    final Pane pane = new Pane();
                    pane.setMinSize(2, 20);
                    pane.setLayoutX(i);
                    pane.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                    Platform.runLater(new Runnable() {

                        public void run() {
                            scene.getChildren().add(pane);
                        }
                    });
                    System.out.println(in.read());
                    i+=2;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
