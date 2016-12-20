package SocialAppClient;

import SocialAppGeneral.Command;
import SocialAppGeneral.Post;
import SocialAppGeneral.Relations;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by billy on 2016-11-26.
 */
public class PostWriter extends VBox{
    private TextArea postText;
//    private Button addImage;
    private Button postBtn;
    public PostWriter(){

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
//        addImage = new Button("Choose an image");
        postBtn = new Button("Post");/*
        addImage.setStyle(Styles.BLACK_BUTTON);
        addImage.setOnMouseEntered(event -> addImage.setStyle(Styles.BLACK_BUTTON_HOVER));
        addImage.setOnMouseExited(event -> addImage.setStyle(Styles.BLACK_BUTTON));

        addImage.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();

            FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
            fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

            File file = fileChooser.showOpenDialog(null);

            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            } catch (Exception ex) {
            }

        });
*/
        postBtn.setStyle(Styles.BLACK_BUTTON);
        postBtn.setOnMouseEntered(event -> postBtn.setStyle(Styles.BLACK_BUTTON_HOVER));
        postBtn.setOnMouseExited(event -> postBtn.setStyle(Styles.BLACK_BUTTON));


        option.setSpacing(150);
        option.setAlignment(Pos.CENTER);
        option.getChildren().addAll(/*addImage,*/postBtn);
        setPadding(new Insets(30,0,0,0));

        getChildren().addAll(postText, option);
    }

    public void SavePost(String relation, String id){
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
