package SocialAppClient;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Created by billy on 2016-11-30.
 */
public class PostViewer extends VBox {
    protected Label postText;
    //protected ImageViewer postImg;
    protected Button thumpsUp;
    protected Button thumpsDown;
    protected Button comment;
    protected Button share;
    public PostViewer(){
        setLayout();
    }

    private void setLayout(){
        setAlignment(Pos.TOP_CENTER);
        postText = new Label();
        postText.setText("HELLO FROM THE OTHER SIDE!!");
        postText.setFont(Font.font(16));
        postText.setPadding(new Insets(0,0,10,0));
        //postImg = new ImageViewer;
        ImageView img;
        Image im = new Image("file:C:\\Users\\bolla\\Pictures\\me.jpg");
        img = new ImageView(im);
        img.setFitWidth(300);
        img.setPreserveRatio(true);
        img.setSmooth(true);
        img.setCache(true);

        thumpsUp = new Button("Thump UP");
        thumpsDown = new Button("Thump Down");
        comment = new Button("Comment");
        share = new Button("Share");
        HBox buttons = new HBox(thumpsUp, thumpsDown, comment, share);
        buttons.setAlignment(Pos.CENTER);

        getChildren().addAll(postText, /**postImg,*/img, buttons);

    }
}
