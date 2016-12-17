package SocialAppClient;

import SocialAppGeneral.Comment;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * Created by billy on 2016-12-08.
 */
public class CommentViewer extends VBox{
    private Comment comment;
    private TextArea postComments;
    public CommentViewer(Comment comment){
        this.comment = comment;
        postComments = new TextArea(this.comment.getCommentcontent());
        setLayout();
    }
    private void setLayout(){
        postComments.setStyle(Styles.TEXT_AREA);
        postComments.setEditable(false);
        postComments.setWrapText(true);
        postComments.setPrefHeight(postComments.getText().length());
        postComments.setOnKeyTyped(event -> postComments.setPrefHeight(postComments.getText().length()));

        ChoiceBox<String> edit = new ChoiceBox<>();
        edit.setStyle(Styles.TRANSPARENT_BACKGROUND);
        edit.setPrefWidth(1);
        edit.getItems().addAll("Edit","Delete");

        edit.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if (newValue.equals("Edit")) {
                    postComments.setEditable(true);
                    postComments.requestFocus();
                    postComments.setStyle(null);
                    postComments.setOnKeyPressed(event -> {
                        if (event.getCode().equals(KeyCode.ENTER)) {
                            PostDetails.setCommentCommend(0, postComments.getText(), comment.getCommentId());
                            postComments.setEditable(false);
                            postComments.setStyle(Styles.TEXT_AREA);
                            edit.setValue(null);
                        }
                    });
                } else if (newValue.equals("Delete")) {
                    PostDetails.setCommentCommend(-1, postComments.getText(), comment.getCommentId());
                    edit.setValue(null);
                }
            }catch (NullPointerException e){}
        });

        if(!MainWindow.id.equals(""+comment.getOwnerID()))
            edit.setVisible(false);

        setMargin(postComments, new Insets(0,20,0,50));
        HBox h = new HBox(edit);
        h.setAlignment(Pos.TOP_RIGHT);
        HBox hbox = new HBox(new FriendView("" + comment.getOwnerID(),16),h);
        hbox.setHgrow(h, Priority.ALWAYS);
        getChildren().addAll(hbox, postComments);

    }
}
