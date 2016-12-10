package SocialAppClient;

import SocialAppGeneral.Comment;
import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
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
        postComments.setFont(Font.font(18));
        postComments.setStyle(Styles.WHITE_BACKGROUND);
        postComments.setEditable(false);
        postComments.setWrapText(true);
        postComments.setPrefHeight(postComments.getText().length());
        postComments.setOnKeyTyped(event -> postComments.setPrefHeight(postComments.getText().length()));

        ChoiceBox<String> edit = new ChoiceBox<>();
        edit.setStyle(Styles.TRANSPARENT_BACKGROUND);
        edit.setPrefWidth(1);
        edit.getItems().addAll("Edit","Delete");

        edit.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if(newValue.equals("Edit")){
                    postComments.setEditable(true);
                    postComments.requestFocus();
                    postComments.setStyle(null);
                    postComments.setOnKeyPressed(event -> {
                        if (event.getCode().equals(KeyCode.ENTER)) {
                            ((CallBack) getParent().getParent()).setCommentCommend(0,postComments.getText(),comment.getCommentId());
                            postComments.setEditable(false);
                            postComments.setStyle(Styles.WHITE_BACKGROUND);
                        }
                    });
                }else if(newValue.equals("Delete")){
                    ((CallBack) getParent().getParent()).setCommentCommend(-1,postComments.getText(),comment.getCommentId());
                }
        });

        if(!MainWindow.id.equals(""+comment.getOwnerID()))
            edit.setVisible(false);
        setMargin(postComments, new Insets(0,20,0,20));
        setMargin(edit, new Insets(0,20,0,400));
        getChildren().addAll(new Separator(),edit, new FriendView("" + comment.getOwnerID()), postComments);

    }
}
