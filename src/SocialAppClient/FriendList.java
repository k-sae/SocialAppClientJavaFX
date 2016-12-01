package SocialAppClient;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Created by billy on 2016-12-01.
 */
public class FriendList extends VBox {
    public FriendList(){
        setLayout();
    }
    private void setLayout(){
        setAlignment(Pos.TOP_CENTER);
        setBackground(new Background(new BackgroundFill(Color.web("#eeeeee"), CornerRadii.EMPTY, Insets.EMPTY)));

        Label friendLabel = new Label("FRIENDS");
        friendLabel.setStyle("-fx-font: 30 verdana; -fx-text-fill: #666666;");
        friendLabel.setPadding(new Insets(30,0,30,0));

        ImageView friendImg = new ImageView(new Image("file:C:\\Users\\bolla\\Pictures\\k.jpg"));
        friendImg.setFitWidth(40);
        friendImg.setPreserveRatio(true);
        friendImg.setSmooth(true);
        friendImg.setCache(true);
        friendImg.setClip(new Circle(friendImg.getFitWidth()/2,friendImg.getFitWidth()/2,friendImg.getFitWidth()/2));

        Button friendBtn = new Button("Karim Salah", friendImg);

        friendBtn.setStyle("-fx-font: 20 arial; -fx-background-color: #eeeeee; -fx-text-fill: #000000;");
        friendBtn.setOnMouseEntered(event -> friendBtn.setStyle("-fx-font: 20 arial; -fx-background-color: #cccccc; -fx-text-fill: #000000;"));
        friendBtn.setOnMouseExited(event -> friendBtn.setStyle("-fx-font: 20 arial; -fx-background-color: #eeeeee; -fx-text-fill: #000000;"));


        ImageView friendImg1 = new ImageView(new Image("file:C:\\Users\\bolla\\Pictures\\m.jpg"));
        friendImg1.setFitWidth(40);
        friendImg1.setPreserveRatio(true);
        friendImg1.setSmooth(true);
        friendImg1.setCache(true);
        friendImg1.setClip(new Circle(friendImg.getFitWidth()/2,friendImg.getFitWidth()/2,friendImg.getFitWidth()/2));

        Button friendBtn1 = new Button("Mostafa Hazem", friendImg1);

        friendBtn1.setStyle("-fx-font: 20 arial; -fx-background-color: #eeeeee; -fx-text-fill: #000000;");
        friendBtn1.setOnMouseEntered(event -> friendBtn1.setStyle("-fx-font: 20 arial; -fx-background-color: #cccccc; -fx-text-fill: #000000;"));
        friendBtn1.setOnMouseExited(event -> friendBtn1.setStyle("-fx-font: 20 arial; -fx-background-color: #eeeeee; -fx-text-fill: #000000;"));



        ImageView friendImg2 = new ImageView(new Image("file:C:\\Users\\bolla\\Pictures\\h.jpg"));
        friendImg2.setFitWidth(40);
        friendImg2.setPreserveRatio(true);
        friendImg2.setSmooth(true);
        friendImg2.setCache(true);
        friendImg2.setClip(new Circle(friendImg.getFitWidth()/2,friendImg.getFitWidth()/2,friendImg.getFitWidth()/2));

        Button friendBtn2 = new Button("Khaled Hesham", friendImg2);

        friendBtn2.setStyle("-fx-font: 20 arial; -fx-background-color: #eeeeee; -fx-text-fill: #000000;");
        friendBtn2.setOnMouseEntered(event -> friendBtn2.setStyle("-fx-font: 20 arial; -fx-background-color: #cccccc; -fx-text-fill: #000000;"));
        friendBtn2.setOnMouseExited(event -> friendBtn2.setStyle("-fx-font: 20 arial; -fx-background-color: #eeeeee; -fx-text-fill: #000000;"));




        getChildren().addAll(friendLabel, friendBtn, friendBtn1, friendBtn2);
    }
}
