package SocialAppClient;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
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
    protected Button thumbsUp;
    protected Button thumbsDown;
    protected Button comment;
    protected Button share;
    public PostViewer(){
        /** IT WILL TAKE POST*/

        /** PUBLIC POSTVIEWER(Post post){
         * this.post = post;
         * setLayout();
         * }
         */
        setLayout();
    }

    /**
     private void setLayout(){
     setAlignment(Pos.TOP_CENTER);
     setPadding(new Insets(10,0,20,0));
     setStyle("-fx-background-color: #ffffff;");

     ImageView postOwnerImg = new ImageView(post.postOwner.getImage());
     Button postOwner = new Button(post.postOwner.getName, postOwnerImg);

     postOwner.setStyle("-fx-font: 20 arial; -fx-background-color: #ffffff; -fx-text-fill: #000000;");
     postOwner.setOnMouseEntered(event -> friendBtn.setStyle("-fx-font: 20 arial; -fx-background-color: #cccccc; -fx-text-fill: #000000;"));
     postOwner.setOnMouseExited(event -> friendBtn.setStyle("-fx-font: 20 arial; -fx-background-color: #ffffff; -fx-text-fill: #000000;"));

     postText = new Label();
     postText.setText(post.getText);
     postText.setFont(Font.font(16));
     postText.setWrapText(true);
     postText.setPadding(new Insets(0,0,10,0));

     ImageView img = new ImageView(post.getImage);
     img.setFitWidth(350);
     img.setPreserveRatio(true);
     img.setSmooth(true);
     img.setCache(true);

     ImageView TUicon = new ImageView("file:Resources/TU.png");
     TUicon.setFitWidth(15);
     TUicon.setPreserveRatio(true);

     thumbsUp = new Button("Thumb UP", TUicon);
     thumbsUp.setStyle("-fx-font: 12 arial; -fx-background-color: #ffffff; -fx-text-fill: #000000;");
     thumbsUp.setOnMouseEntered(event -> thumbsUp.setStyle("-fx-background-color: #999999; -fx-text-fill: #000000;"));
     thumbsUp.setOnMouseExited(event -> thumbsUp.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000;"));

     thumbsUp.setOnMousePressed(event -> thumbsUp.setStyle("-fx-background-color: #ffa500"));
     ImageView TDicon = new ImageView("file:Resources/TD.png");
     TDicon.setFitWidth(15);
     TDicon.setPreserveRatio(true);

     thumbsDown = new Button("Thumb Down", TDicon);
     thumbsDown.setStyle("-fx-font: 12 arial; -fx-background-color: #ffffff; -fx-text-fill: #000000;");
     thumbsDown.setOnMouseEntered(event -> thumbsDown.setStyle("-fx-background-color: #999999; -fx-text-fill: #000000;"));
     thumbsDown.setOnMouseExited(event -> thumbsDown.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000;"));

     ImageView commenticon = new ImageView("file:Resources/comment.png");
     commenticon.setFitWidth(15);
     commenticon.setPreserveRatio(true);

     comment = new Button("Comment", commenticon);
     comment.setStyle("-fx-font: 12 arial; -fx-background-color: #ffffff; -fx-text-fill: #000000;");
     comment.setOnMouseEntered(event -> comment.setStyle("-fx-background-color: #999999; -fx-text-fill: #000000;"));
     comment.setOnMouseExited(event -> comment.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000;"));

     ImageView shareicon = new ImageView("file:Resources/share.png");
     shareicon.setFitWidth(15);
     shareicon.setPreserveRatio(true);

     share = new Button("Share" ,shareicon);
     share.setStyle("-fx-font: 12 arial; -fx-background-color: #ffffff; -fx-text-fill: #000000;");
     share.setOnMouseEntered(event -> share.setStyle("-fx-background-color: #999999; -fx-text-fill: #000000;"));
     share.setOnMouseExited(event -> share.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000;"));

     HBox buttons = new HBox(thumbsUp, thumbsDown, comment, share);
     buttons.setAlignment(Pos.CENTER);

     setMaxWidth(400);
     setMargin(postText, new Insets(0,30,0,30));
     setMargin(img, new Insets(10,0,20,0));
     getChildren().addAll(friendBtn, postText, img, new Separator(), buttons);

     }
     */

    private void setLayout(){
        setAlignment(Pos.TOP_CENTER);
        setPadding(new Insets(10,0,20,0));
        setStyle("-fx-background-color: #ffffff;");


        postText = new Label();
        postText.setText("HELLO FROM THE OTHER SIDE!!");
        postText.setFont(Font.font(16));
        postText.setWrapText(true);
        postText.setPadding(new Insets(0,0,10,0));

        Image im = new Image("file:C:\\Users\\bolla\\Pictures\\me.jpg");
        ImageView img = new ImageView(im);
        img.setFitWidth(350);
        img.setPreserveRatio(true);
        img.setSmooth(true);
        img.setCache(true);

        ImageView TUicon = new ImageView("file:Resources/TU.png");
        TUicon.setFitWidth(15);
        TUicon.setPreserveRatio(true);

        thumbsUp = new Button("Thumb UP", TUicon);
        thumbsUp.setStyle("-fx-font: 12 arial; -fx-background-color: #ffffff; -fx-text-fill: #000000;");
        thumbsUp.setOnMouseEntered(event -> thumbsUp.setStyle("-fx-background-color: #999999; -fx-text-fill: #000000;"));
        thumbsUp.setOnMouseExited(event -> thumbsUp.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000;"));

        thumbsUp.setOnMousePressed(event -> thumbsUp.setStyle("-fx-background-color: #ffa500"));
        ImageView TDicon = new ImageView("file:Resources/TD.png");
        TDicon.setFitWidth(15);
        TDicon.setPreserveRatio(true);

        thumbsDown = new Button("Thumb Down", TDicon);
        thumbsDown.setStyle("-fx-font: 12 arial; -fx-background-color: #ffffff; -fx-text-fill: #000000;");
        thumbsDown.setOnMouseEntered(event -> thumbsDown.setStyle("-fx-background-color: #999999; -fx-text-fill: #000000;"));
        thumbsDown.setOnMouseExited(event -> thumbsDown.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000;"));

        ImageView commenticon = new ImageView("file:Resources/comment.png");
        commenticon.setFitWidth(15);
        commenticon.setPreserveRatio(true);

        comment = new Button("Comment", commenticon);
        comment.setStyle("-fx-font: 12 arial; -fx-background-color: #ffffff; -fx-text-fill: #000000;");
        comment.setOnMouseEntered(event -> comment.setStyle("-fx-background-color: #999999; -fx-text-fill: #000000;"));
        comment.setOnMouseExited(event -> comment.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000;"));

        ImageView shareicon = new ImageView("file:Resources/share.png");
        shareicon.setFitWidth(15);
        shareicon.setPreserveRatio(true);

        share = new Button("Share" ,shareicon);
        share.setStyle("-fx-font: 12 arial; -fx-background-color: #ffffff; -fx-text-fill: #000000;");
        share.setOnMouseEntered(event -> share.setStyle("-fx-background-color: #999999; -fx-text-fill: #000000;"));
        share.setOnMouseExited(event -> share.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000;"));

        HBox buttons = new HBox(thumbsUp, thumbsDown, comment, share);
        buttons.setAlignment(Pos.CENTER);

        setMaxWidth(400);
        setMargin(postText, new Insets(0,30,0,30));
        setMargin(img, new Insets(10,0,20,0));
        getChildren().addAll(new FriendView("1"), postText, img, new Separator(), buttons);

    }
}
