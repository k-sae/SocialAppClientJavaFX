package SocialAppClient;

import SocialAppGeneral.Comment;
import SocialAppGeneral.Post;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.ArrayList;

/**
 * Created by billy on 2016-12-07.
 */
public class CommentViewer extends VBox{
    private Label postComments;
    protected TextField commentText;
    private ArrayList<Comment> comments;
    public CommentViewer(ArrayList<Comment> comments){
        this.comments = comments;
        commentText = new TextField();
        commentText.setPromptText("Write a comment...");
        setLayout();
    }
    private void setLayout(){

        //setAlignment(Pos.TOP_CENTER);
        setSpacing(5);
        setPadding(new Insets(10,0,20,0));
        setStyle("-fx-background-color: #ffffff;");

        for (Comment comment: comments) {
            postComments = new Label(comment.getCommentcontent());
            postComments.setFont(Font.font(18));
            postComments.setWrapText(true);
            setMargin(postComments, new Insets(0,20,0,20));
            getChildren().addAll(new Separator(), new FriendView("" + comment.getOwnerID()), postComments);

        }
        setMaxWidth(450);
        setMargin(commentText, new Insets(0,20,0,20));

        getChildren().add(commentText);
    }
}
