package SocialAppClient.View;

import SocialAppGeneral.Comment;
import SocialAppGeneral.Relations;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * Created by billy on 2016-12-07.
 */
class CommentContainer extends VBox{
    private TextField commentText;
    private ArrayList<Comment> comments;
    CommentContainer(ArrayList<Comment> comments){
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
                //TODO ???
                // what is suppose to do ?????                     //what is this zero??
                PostDetails.setCommentCommend(Relations.ADD, commentText.getText(),0);
                commentText.setText("");
            }
        });

        getChildren().add(commentText);
    }
}
