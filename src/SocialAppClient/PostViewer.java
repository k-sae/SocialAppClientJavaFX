package SocialAppClient;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

/**
 * Created by billy on 2016-11-30.
 */
public class PostViewer extends VBox {
    protected Label postText;
    protected Button thumpsUp;
    protected Button thumpsDown;
    protected Button comment;
    protected Button share;
    public PostViewer(){
        setLayout();
    }

    private void setLayout(){
        setAlignment(Pos.TOP_CENTER);
        setPadding(new Insets(10,0,20,0));
        setStyle("-fx-background-color: #ffffff;");

        ImageView friendImg = new ImageView(new Image("file:C:\\Users\\bolla\\Pictures\\me.jpg"));
        friendImg.setFitWidth(40);
        friendImg.setPreserveRatio(true);
        friendImg.setSmooth(true);
        friendImg.setCache(true);
        friendImg.setClip(new Circle(friendImg.getFitWidth()/2,friendImg.getFitWidth()/2,friendImg.getFitWidth()/2));

        Button friendBtn = new Button("Belal Ibrahim", friendImg);

        friendBtn.setStyle("-fx-font: 20 arial; -fx-background-color: #ffffff; -fx-text-fill: #000000;");
        friendBtn.setOnMouseEntered(event -> friendBtn.setStyle("-fx-font: 20 arial; -fx-background-color: #cccccc; -fx-text-fill: #000000;"));
        friendBtn.setOnMouseExited(event -> friendBtn.setStyle("-fx-font: 20 arial; -fx-background-color: #ffffff; -fx-text-fill: #000000;"));


        postText = new Label();
        postText.setText("HELLO FROM THE OTHER SIDE!!");
        postText.setFont(Font.font(16));
        postText.setPadding(new Insets(0,0,10,0));

        Image im = new Image("file:C:\\Users\\bolla\\Pictures\\me.jpg");
        ImageView img = new ImageView(im);
        img.setFitWidth(350);
        img.setPreserveRatio(true);
        img.setSmooth(true);
        img.setCache(true);

        thumpsUp = new Button("Thump UP");
        thumpsDown = new Button("Thump Down");
        comment = new Button("Comment");
        share = new Button("Share");
        HBox buttons = new HBox(thumpsUp, thumpsDown, comment, share);
        buttons.setAlignment(Pos.CENTER);

        setMaxWidth(img.getFitWidth()+30);
        getChildren().addAll(friendBtn, postText, img, buttons);

    }
}
