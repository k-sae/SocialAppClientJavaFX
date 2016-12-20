package SocialAppClient;

import SocialAppGeneral.Command;
import SocialAppGeneral.RegisterInfo;
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
import java.time.LocalDate;

import static javax.swing.text.html.HTML.Tag.HEAD;

/**
 * Created by billy on 2016-11-28.
 */
class EditInfo extends GridPane{
    private ImageViewer profilePicture;
    private UserInfo userInfo;
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

        profilePicture = Utility.getCircularImage(userInfo.getProfileImage(),50);

        Button uploadImg = new Button("Change Photo");
        uploadImg.setStyle(Styles.BLACK_BUTTON);
        uploadImg.setOnMouseEntered(event -> uploadImg.setStyle(Styles.BLACK_BUTTON_HOVER));
        uploadImg.setOnMouseExited(event -> uploadImg.setStyle(Styles.BLACK_BUTTON));

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
        removePP.setStyle(Styles.BLACK_BUTTON);
        removePP.setOnMouseEntered(event -> removePP.setStyle(Styles.BLACK_BUTTON_HOVER));
        removePP.setOnMouseExited(event -> removePP.setStyle(Styles.BLACK_BUTTON));

        removePP.setOnAction(event -> {
            userInfo.setProfileImage("default");
            bufferedImage[0] = null;
            }
        );

        pictureOption.getChildren().addAll(uploadImg,removePP);
        pictureOption.setSpacing(5);
        pictureOption.setAlignment(Pos.CENTER);

        Label FnameLBL = new Label("Full Name:");
        FnameLBL.setFont(Font.font(26));

        TextField FnameTXT = new TextField();
        FnameTXT.setText(userInfo.getFullName());
/*
        Label passwordLBL = new Label("Password:");
        passwordLBL.setFont(Font.font(26));

        TextField passwordTXT = new TextField();
*/
        Label birthDateLBL = new Label("Birth Date");
        birthDateLBL.setFont(Font.font(26));
        DatePicker datePicker = new DatePicker(LocalDate.parse(userInfo.getBirthDate()));

        Label genderLBL = new Label("Gender:");
        genderLBL.setFont(Font.font(26));

        ToggleGroup genderGroup = new ToggleGroup();
        RadioButton male = new RadioButton("Male");
        male.setToggleGroup(genderGroup);
        RadioButton female = new RadioButton("Female");
        female.setToggleGroup(genderGroup);

        HBox genderHbox = new HBox(5, male, female);
        genderHbox.setAlignment(Pos.CENTER);

        if(userInfo.getGender().equals("Male"))
            male.setSelected(true);
        else
            female.setSelected(true);

        Button saveBtn = new Button("save");
        saveBtn.setStyle(Styles.BLACK_BUTTON);
        saveBtn.setOnMouseEntered(event -> saveBtn.setStyle(Styles.BLACK_BUTTON_HOVER));
        saveBtn.setOnMouseExited(event -> saveBtn.setStyle(Styles.BLACK_BUTTON));

        saveBtn.setOnMouseClicked(event -> {
            UserInfo userInfo = new UserInfo();
            userInfo.setProfileImage(EditInfo.this.userInfo.getProfileImage());
            userInfo.setFullName(FnameTXT.getText());
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
            MainWindow.navigateTo(new ProfilePage(MainWindow.id));
        });

        Button deactivate = new Button("Deactivate");
        deactivate.setStyle(Styles.BLACK_BUTTON);
        deactivate.setOnMouseEntered(event -> deactivate.setStyle(Styles.BLACK_BUTTON_HOVER));
        deactivate.setOnMouseExited(event -> deactivate.setStyle(Styles.BLACK_BUTTON));
        deactivate.setOnMouseClicked(event -> {
            MainWindow.clientLoggedUser.deactivate();
        });

        info.getChildren().addAll(title,new Separator(), profilePicture, pictureOption,FnameLBL,FnameTXT/*,passwordLBL,passwordTXT*/,birthDateLBL,datePicker,genderLBL,genderHbox,saveBtn,deactivate);

        add(info,1,0);
    }
}
