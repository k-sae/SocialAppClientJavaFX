package SocialAppClient;

import SocialAppGeneral.Command;
import SocialAppGeneral.UserInfo;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * Created by kemo on 10/11/2016.
 */
public class ProfilePage extends GridPane {
    private String id;
    public ProfilePage(String id)
    {
        this.id = id;
        /**IT WILL TAKE AN ID IN THE CONSTRUCTOR*/
        setBackground(new Background(new BackgroundFill(Color.web("#eeeeee"), CornerRadii.EMPTY, Insets.EMPTY)));

        setGridLinesVisible(true);
        setConstraint();
        setPanels();

    }

    private void setConstraint(){

        ColumnConstraints columnConstraints0 = new ColumnConstraints();
        columnConstraints0.setPercentWidth(25);
        ColumnConstraints columnConstraints1 = new ColumnConstraints();
        columnConstraints1.setPercentWidth(75);

        getColumnConstraints().addAll(columnConstraints0,columnConstraints1);

        RowConstraints rowConstraints0 = new RowConstraints();
        rowConstraints0.setPercentHeight(100);
        getRowConstraints().addAll(rowConstraints0);

    }

    private void setPanels(){

        ProfileInfoViewer Info = new ProfileInfoViewer(id);
        /**ADD PICTURE*/
        //Info.setPicture();
        /**ADD INFO*/

        Command command = new Command();
        command.setKeyWord(UserInfo.PICK_INFO);
        command.setSharableObject(id);
        CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket,command) {
            @Override
            void analyze(Command cmd) {
                UserInfo userInfo = UserInfo.fromJsonString(cmd.getObjectStr());
                Platform.runLater(() -> Info.setLabel("Name: " +userInfo.getFullName(),
                        "BirthDate: " + userInfo.getBirthDate(),
                        "Gender: " + userInfo.getGender()));



            }
        };
        CommandsExecutor.getInstance().add(commandRequest);
        Content content = new Content();
        add(content,1,0);
        add(Info,0,0);
        ScrollPane sp = new ScrollPane(content);
        sp.setFitToWidth(true);
        add(sp,1,0);
        Info.setButtons();
        Info.Edit.setOnAction(event -> {
            getChildren().remove(content);
            /**AFTER CLICK ON EDIT IT WILL GO TO EDIT PAGE*/
            EditInfo editInfo = new EditInfo();
            add(editInfo,1,0);
            sp.setContent(editInfo);
        });
    }

}
