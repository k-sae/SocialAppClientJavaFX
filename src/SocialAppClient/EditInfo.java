package SocialAppClient;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by billy on 2016-11-28.
 */
public class EditInfo extends GridPane{
    ImageView profilePicture;
    public EditInfo(){

        setConstraint();
        setLayout();
    }

    private void setConstraint(){
        ColumnConstraints col0 = new ColumnConstraints();
        col0.setPercentWidth(30);
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(40);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(30);

        getColumnConstraints().addAll(col0,col1,col2);
    }


    private void setLayout(){
        setPadding(new Insets(30,0,20,0));
        VBox info = new VBox();
        HBox pictureOption = new HBox();
        info.setAlignment(Pos.CENTER);
        info.setSpacing(5);

        Label title = new Label("INFO EDIT");
        title.setFont(Font.font(36));

        profilePicture = new ImageView("file:C:\\Users\\bolla\\Pictures\\me.jpg");
        profilePicture.setFitWidth(100);
        profilePicture.setPreserveRatio(true);
        profilePicture.setSmooth(true);
        profilePicture.setCache(true);
        profilePicture.setClip(new Circle(profilePicture.getFitWidth()/2,profilePicture.getFitWidth()/2,profilePicture.getFitWidth()/2));

        Button uploadImg = new Button("Change Photo");
        uploadImg.setStyle("-fx-font: 12 arial; -fx-background-color: #000000; -fx-text-fill: #eeeeee;");
        uploadImg.setOnMouseEntered(event -> uploadImg.setStyle("-fx-font: 12 arial; -fx-background-color: #999999; -fx-text-fill: #000000;"));
        uploadImg.setOnMouseExited(event -> uploadImg.setStyle("-fx-font: 12 arial; -fx-background-color: #000000; -fx-text-fill: #eeeeee;"));

        uploadImg.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();

            FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
            fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

            File file = fileChooser.showOpenDialog(null);

            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                profilePicture.setImage(image);
            } catch (IOException ex) {
            }

        });

        Button removePP = new Button("Remove");
        removePP.setStyle("-fx-font: 12 arial; -fx-background-color: #000000; -fx-text-fill: #eeeeee;");
        removePP.setOnMouseEntered(event -> removePP.setStyle("-fx-font: 12 arial; -fx-background-color: #999999; -fx-text-fill: #000000;"));
        removePP.setOnMouseExited(event -> removePP.setStyle("-fx-font: 12 arial; -fx-background-color: #000000; -fx-text-fill: #eeeeee;"));

        removePP.setOnAction(event -> profilePicture.setImage(new Image("file:Resources/avatar.jpg")));

        pictureOption.getChildren().addAll(uploadImg,removePP);
        pictureOption.setSpacing(5);
        pictureOption.setAlignment(Pos.CENTER);

        Label FnameLBL = new Label("First Name:");
        FnameLBL.setFont(Font.font(26));

        TextField FnameTXT = new TextField();

        Label LnameLBL = new Label("Last Name:");
        LnameLBL.setFont(Font.font(26));

        TextField LnameTXT = new TextField();

        Label passwordLBL = new Label("Password:");
        passwordLBL.setFont(Font.font(26));

        TextField passwordTXT = new TextField();

        Button saveBtn = new Button("save");
        saveBtn.setStyle("-fx-font: 20 arial; -fx-background-color: #000000; -fx-text-fill: #eeeeee;");
        saveBtn.setOnMouseEntered(event -> saveBtn.setStyle("-fx-font: 20 arial; -fx-background-color: #999999; -fx-text-fill: #000000;"));
        saveBtn.setOnMouseExited(event -> saveBtn.setStyle("-fx-font: 20 arial; -fx-background-color: #000000; -fx-text-fill: #eeeeee;"));

        info.getChildren().addAll(title,new Separator(), profilePicture, pictureOption,FnameLBL,FnameTXT,LnameLBL,LnameTXT,passwordLBL,passwordTXT,saveBtn);

        add(info,1,0);
    }
}
