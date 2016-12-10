package SocialAppClient;

import SocialAppGeneral.Comment;
import SocialAppGeneral.Post;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;

/**
 * Created by billy on 2016-12-07.
 */
public class CommentContainer extends VBox{
    protected TextField commentText;
    private ArrayList<Comment> comments;
    public CommentContainer(ArrayList<Comment> comments){
        this.comments = comments;
        commentText = new TextField();
        setLayout();
    }
    private void setLayout(){

        //setAlignment(Pos.TOP_CENTER);
        setSpacing(5);
        setPadding(new Insets(10,0,20,0));
        setStyle(Styles.WHITE_BACKGROUND);
        commentText.setPromptText("Write a comment...");
        commentText.setFont(Font.font(14));

        for (Comment comment: comments) {
            CommentViewer commentViewer = new CommentViewer(comment);
            getChildren().add(commentViewer);

        }
        setMaxWidth(450);
        setMargin(commentText, new Insets(0,20,0,20));

        getChildren().add(commentText);
    }
}
