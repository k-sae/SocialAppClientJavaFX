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
    public PostWriter(){

        setLayout();
    }

    private void setLayout(){
        setAlignment(Pos.CENTER);
        TextArea postText = new TextArea();
        postText.setPromptText("What's on your mind?!");
        postText.setMaxSize(400,80);

        HBox boxx = new HBox();
        Button addImage = new Button("Choose an image");
        Button postBtn = new Button("Post");
        boxx.setSpacing(150);
        boxx.setAlignment(Pos.CENTER);
        boxx.setBackground(new Background(new BackgroundFill(Color.web("#ffffff"), CornerRadii.EMPTY, Insets.EMPTY)));
        boxx.getChildren().addAll(addImage,postBtn);
        boxx.setMaxSize(400,30);
        setPadding(new Insets(30,0,30,0));

        getChildren().addAll(postText, boxx);
    }
}
