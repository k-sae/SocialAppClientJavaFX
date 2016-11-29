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
        option.setSpacing(150);
        option.setAlignment(Pos.CENTER);
        option.setBackground(new Background(new BackgroundFill(Color.web("#ffffff"), CornerRadii.EMPTY, Insets.EMPTY)));
        option.getChildren().addAll(addImage,postBtn);
        option.setMaxSize(400,30);
        setPadding(new Insets(30,0,30,0));

        getChildren().addAll(postText, option);
    }
    public String getPostText(){
        return postText.getText();
    }
}
