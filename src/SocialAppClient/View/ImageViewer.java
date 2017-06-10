package SocialAppClient.View;

import Connections.Client.ServerConnection;
import Connections.Command;
import Connections.Connection;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by kemo on 01/12/2016.
 */
public class ImageViewer extends ImageView implements SocialAppImages {
    String id;
    public ImageViewer(String id)
    {
        this.id = id;
        Thread thread  = new Thread()
        {
            @Override
            public void run() {
                super.run();
                try {
                    //TODO: #Config
                    new ServerConnection("127.0.0.1", 6010) {
                        @Override
                        public void startConnection() {
                            try {
                                Socket connectionSocket = getConnectionSocket();
                                sendCommand(connectionSocket);
                                connectionSocket.setSoTimeout(100000);
                               //TODO #hazem
                                //          1- read from server byte array
                                //          2- convert byte array to Javafx image
                            } catch (IOException e) {
                                Platform.runLater(() ->  setImage(new Image("file:Resources/avatar.jpg", true)));
                            }
                        }
                    };
                } catch (Exception e) {
                    System.out.println("Image cant be loaded");
                    Platform.runLater(() ->  setImage(new Image("file:Resources/avatar.jpg", true)));
                }
            }
        };
        thread.start();

    }
    private void sendCommand(Socket socket)
    {
        try {
            DataOutputStream  dataOutputStream = new DataOutputStream(socket.getOutputStream());
            Command command = new Command();
            command.setKeyWord(DOWNLOADIMAGE);
            command.setSharableObject(id);
            dataOutputStream.writeUTF(command.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
