package SocialAppGeneral;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * Created by billy on 2016-11-22.
 */
public class WritePost extends VBox {

    public WritePost(){
        setLayout();
    }

    private void setLayout(){
        TextArea postText = new TextArea();
        postText.setPromptText("What's on your mind?!");
        postText.setPrefSize(60,80);

        HBox boxx = new HBox();
        Button addImage = new Button("Choose an image");
        Button postBtn = new Button("Post");
        boxx.setSpacing(150);
        boxx.setPrefHeight(30);
        boxx.setAlignment(Pos.CENTER);
        boxx.setBackground(new Background(new BackgroundFill(Color.web("#ffffff"),CornerRadii.EMPTY, Insets.EMPTY)));
        boxx.getChildren().addAll(addImage,postBtn);

        getChildren().addAll(postText, boxx);
    }
}
