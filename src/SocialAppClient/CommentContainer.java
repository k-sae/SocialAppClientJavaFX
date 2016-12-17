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
        setPadding(new Insets(0,0,10,0));
        setStyle(Styles.DEFAULT_BACKGROUND);
        commentText.setPromptText("Write a comment...");

        for (Comment comment: comments) {
            CommentViewer commentViewer = new CommentViewer(comment);
            getChildren().add(commentViewer);

        }
        setMaxWidth(450);
        setMargin(commentText, new Insets(0,20,0,20));

        commentText.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)){
                PostDetails.setCommentCommend(1, commentText.getText(),0);
                commentText.setText("");
            }
        });

        getChildren().add(commentText);
    }
}
