package SocialAppClient.View;

import SocialAppClient.SocialAppGeneral.Relations;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * Created by billy on 2016-11-26.
 */
class PostWriter extends VBox{
    private TextArea postText;
//    private Button addImage;
    private Button postBtn;
    PostWriter(){

        setLayout();
    }

    private void setLayout(){
        setAlignment(Pos.CENTER);
        postText = new TextArea();
        postText.setPromptText("What's on your mind?!");
        postText.setWrapText(true);
        postText.setFont(Font.font(18));
        postText.setMaxSize(450,100);

        HBox option = new HBox();
        postBtn = new Button("Post");
        postBtn.setStyle(Styles.BLACK_BUTTON);
        postBtn.setOnMouseEntered(event -> postBtn.setStyle(Styles.BLACK_BUTTON_HOVER));
        postBtn.setOnMouseExited(event -> postBtn.setStyle(Styles.BLACK_BUTTON));


        option.setSpacing(150);
        option.setAlignment(Pos.CENTER);
        option.getChildren().addAll(/*addImage,*/postBtn);
        setPadding(new Insets(30,0,0,0));

        getChildren().addAll(postText, option);
    }

    void SavePost(String relation, String id){
        postBtn.setOnAction(e->{
            if(relation.equals(Relations.HOME_PAGE.toString())||relation.equals(Relations.PROFILE_PAGE.toString())){
                MainWindow.clientLoggedUser.savePostUser(relation,postText.getText());
            }else if(relation.equals(Relations.GROUP.toString())){
                MainWindow.clientLoggedUser.savePostGroup(postText.getText(), id);
            }
            postText.setText("");

        });
    }
}
