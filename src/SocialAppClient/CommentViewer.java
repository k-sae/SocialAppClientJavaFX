package SocialAppClient;

import SocialAppGeneral.Comment;
import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * Created by billy on 2016-12-08.
 */
public class CommentViewer extends VBox {
    private Comment comment;
    private TextField postComments;
    public CommentViewer(Comment comment){
        this.comment = comment;
        postComments = new TextField(this.comment.getCommentcontent());
        setLayout();
    }
    private void setLayout(){
        postComments.setFont(Font.font(18));
//        postComments.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        postComments.setEditable(false);

        ChoiceBox<String> edit = new ChoiceBox<>();
        edit.setStyle("-fx-background-color: transparent");
        edit.setPrefWidth(1);
        edit.getItems().addAll("Edit","Delete");

        edit.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if(newValue.equals("Edit")){
                    postComments.setEditable(true);
                    postComments.requestFocus();
                    postComments.setOnKeyPressed(event -> {
                        if (event.getCode().equals(KeyCode.ENTER)) {
                            ((CallBack) getParent().getParent()).commentEdit(postComments.getText());
                            postComments.setEditable(false);
                        }
                    });
                }
        });

        setMargin(postComments, new Insets(0,20,0,20));
        getChildren().addAll(new Separator(),edit, new FriendView("" + comment.getOwnerID()), postComments);

    }
}
