package SocialAppClient;

import SocialAppGeneral.Command;
import SocialAppGeneral.UserInfo;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by billy on 2016-11-28.
 */
class EditInfo extends GridPane{
    private ImageViewer profilePicture;
    public UserInfo userInfo;
    EditInfo(UserInfo userInfo){
        this.userInfo = userInfo;
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

        profilePicture = new ImageViewer(userInfo.getProfileImage());
        profilePicture.setFitWidth(100);
        profilePicture.setPreserveRatio(true);
        profilePicture.setSmooth(true);
        profilePicture.setCache(true);
        profilePicture.setClip(new Circle(profilePicture.getFitWidth()/2,profilePicture.getFitWidth()/2,profilePicture.getFitWidth()/2));

        Button uploadImg = new Button("Change Photo");
        uploadImg.setStyle("-fx-font: 12 arial; -fx-background-color: #000000; -fx-text-fill: #eeeeee;");
        uploadImg.setOnMouseEntered(event -> uploadImg.setStyle("-fx-font: 12 arial; -fx-background-color: #999999; -fx-text-fill: #000000;"));
        uploadImg.setOnMouseExited(event -> uploadImg.setStyle("-fx-font: 12 arial; -fx-background-color: #000000; -fx-text-fill: #eeeeee;"));
        final BufferedImage[] bufferedImage = {null};
        uploadImg.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();

            FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
            fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
            File file = fileChooser.showOpenDialog(null);
            //TODO #due

            try {
              bufferedImage[0] = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage[0], null);
                profilePicture.setImage(image);
            } catch (Exception ignored) {
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

        Label birthDateLBL = new Label("Birth Date");
        birthDateLBL.setFont(Font.font(26));
        DatePicker datePicker = new DatePicker();

        Label genderLBL = new Label("Gender:");
        genderLBL.setFont(Font.font(26));

        ToggleGroup genderGroup = new ToggleGroup();
        RadioButton male = new RadioButton("Male");
        male.setToggleGroup(genderGroup);
        RadioButton female = new RadioButton("Female");
        female.setToggleGroup(genderGroup);
        HBox genderHbox = new HBox(5, male, female);
        genderHbox.setAlignment(Pos.CENTER);

        Button saveBtn = new Button("save");
        saveBtn.setStyle("-fx-font: 20 arial; -fx-background-color: #000000; -fx-text-fill: #eeeeee;");
        saveBtn.setOnMouseEntered(event -> saveBtn.setStyle("-fx-font: 20 arial; -fx-background-color: #999999; -fx-text-fill: #000000;"));
        saveBtn.setOnMouseExited(event -> saveBtn.setStyle("-fx-font: 20 arial; -fx-background-color: #000000; -fx-text-fill: #eeeeee;"));

        saveBtn.setOnMouseClicked(event -> {
            UserInfo  userInfo = new UserInfo();
            userInfo.setFullName(FnameTXT.getText() + " " + LnameTXT.getText());
            userInfo.setBirthDate(datePicker.getValue().toString());
            userInfo.setGender(((RadioButton) genderGroup.getSelectedToggle()).getText());
            //noinspection ConstantConditions
            if (bufferedImage[0] != null)
            {
                userInfo.setProfileImage( Utility.uploadImage(bufferedImage[0]));

            }
            Command command = new Command();
            command.setKeyWord(UserInfo.EDIT_INFO);
            command.setSharableObject(userInfo);
           CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket,command) {
               @Override
               void analyze(Command cmd) {
                System.out.println(cmd.getObjectStr());
               }
           };
           CommandsExecutor.getInstance().add(commandRequest);
            //TODO #belal
            //TODO: #kareem
            //send to server
        });

        info.getChildren().addAll(title,new Separator(), profilePicture, pictureOption,FnameLBL,FnameTXT,LnameLBL,LnameTXT,passwordLBL,passwordTXT,birthDateLBL,datePicker,genderLBL,genderHbox,saveBtn);

        add(info,1,0);
    }
}
