package SocialAppClient;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * Created by billy on 2016-11-26.
 */
public class PostWriter extends VBox{
    private TextArea postText;
    public Button addImage;
    public Button postBtn;
    public PostWriter(){

        setLayout();
    }

    private void setLayout(){
        setAlignment(Pos.CENTER);
        postText = new TextArea();
        postText.setPromptText("What's on your mind?!");
        postText.setMaxSize(400,80);

        HBox option = new HBox();
        addImage = new Button("Choose an image");
        postBtn = new Button("Post");
        addImage.setStyle("-fx-font: 12 arial; -fx-background-color: #000000; -fx-text-fill: #eeeeee;");
        addImage.setOnMouseEntered(event -> addImage.setStyle("-fx-font: 12 arial; -fx-background-color: #999999; -fx-text-fill: #000000;"));
        addImage.setOnMouseExited(event -> addImage.setStyle("-fx-font: 12 arial; -fx-background-color: #000000; -fx-text-fill: #eeeeee;"));

        postBtn.setStyle("-fx-font: 12 arial; -fx-background-color: #000000; -fx-text-fill: #eeeeee;");
        postBtn.setOnMouseEntered(event -> postBtn.setStyle("-fx-font: 12 arial; -fx-background-color: #999999; -fx-text-fill: #000000;"));
        postBtn.setOnMouseExited(event -> postBtn.setStyle("-fx-font: 12 arial; -fx-background-color: #000000; -fx-text-fill: #eeeeee;"));


        option.setSpacing(150);
        option.setAlignment(Pos.CENTER);
        option.getChildren().addAll(addImage,postBtn);
        setPadding(new Insets(30,0,30,0));

        getChildren().addAll(postText, option);
    }
    public String getPostText(){
        return postText.getText();
    }
}
