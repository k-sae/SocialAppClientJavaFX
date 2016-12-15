package SocialAppClient;

import SocialAppGeneral.Command;
import SocialAppGeneral.Post;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.shape.Circle;
import javafx.stage.StageStyle;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import static SocialAppClient.SocialAppImages.UPLOADIMAGE;

/**
 * Created by khaled hesham on 11/24/2016.
 */
public class Utility {
    public static Optional<String> createWindow(String  Nameenter,String  titel){/*
    this function return optional.empty if user press cancel otherwise return input
    pre !check.equals(Optional.empty())*/
        TextInputDialog Create = new TextInputDialog();
        Create.setHeaderText(null);
        Create.setTitle(titel);
        Create.setContentText(Nameenter+" : ");
        Create.initStyle(StageStyle.UTILITY);
        Optional<String> result = Create.showAndWait();
        return result;
    }
    public static void errorWindow(String error){//show error window
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(null);
        alert.setHeaderText(error);
        alert.initStyle(StageStyle.UTILITY);
        alert.showAndWait();
    }
    public static String uploadImage(BufferedImage bufferedImage) {
       final String[] id = new String[1];
        try {
            //TODO: #Config
            new ServerConnection("127.0.0.1", 6010) {
                @Override
                public void startConnection() {
                    try {
                        DataOutputStream dataOutputStream = new DataOutputStream(connectionSocket.getOutputStream());
                        Command command = new Command();
                        command.setKeyWord(UPLOADIMAGE);
                        dataOutputStream.writeUTF(command.toString());
                        connectionSocket.setSoTimeout(100000);
                        DataInputStream dataInputStream = new DataInputStream(connectionSocket.getInputStream());
                       command = Command.fromString( dataInputStream.readUTF());
                        id[0] = command.getObjectStr();
                        ImageIO.write(bufferedImage,"jpg", connectionSocket.getOutputStream());
                        connectionSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id[0];
    }
    public static int checkID(Post post) {
        int i = 0;
        int check = -1;
        if (post.getLike().size() != 0) {
            do {
                if (post.getLike().get(i).getOwnerID() == Long.parseLong(MainWindow.id)) {
                    check = i;
                }
                i++;
            }
            while (i < post.getLike().size() && post.getLike().get(i).getOwnerID() != Long.parseLong(MainWindow.id));
        }
        return check;
    }

    public static ImageViewer getCircularImage(String imgid, double size){

        ImageViewer img = new ImageViewer(imgid);
        img.setFitWidth(size*2);
        img.setFitHeight(size*2);
        img.setSmooth(true);
        img.setCache(true);
        img.setClip(new Circle(img.getFitWidth()/2,img.getFitHeight()/2,img.getFitWidth()/2));

        return img;
    }

    public static boolean sendMail(String from,String pass,String to,String subject,String body)
    {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props,null);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress toAddress = new InternetAddress(to);

            message.addRecipient(Message.RecipientType.TO, toAddress);
            message.setSubject(subject);
            message.setText(body);

            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            return true;
        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
        return false;
    }
}


