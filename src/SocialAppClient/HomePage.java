package SocialAppClient;

import SocialAppGeneral.*;
import SocialAppGeneral.Command;
import SocialAppGeneral.Like;
import SocialAppGeneral.Post;
import SocialAppGeneral.UserInfo;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by kemo on 10/11/2016.
 */
public class HomePage extends GridPane {
    private String id;
    private UserInfo userInfo;
    public HomePage(String id)
    {
        this.id = id;
        //setBackground(new Background(new BackgroundFill(Color.web(ClientTheme.BackGround, 1), CornerRadii.EMPTY, Insets.EMPTY)));
        setStyle(Styles.DEFAULT_BACKGROUND);
        setGridLinesVisible(true);
        setConstraint();

        Command command = new Command();
        command.setKeyWord(UserInfo.PICK_INFO);
        command.setSharableObject(id);
        CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket,command) {
            @Override
            void analyze(Command cmd) {
                userInfo = UserInfo.fromJsonString(cmd.getObjectStr());
                HomePage.this.userInfo = userInfo;
                Platform.runLater(() -> setPanels());

            }
        };
        CommandsExecutor.getInstance().add(commandRequest);

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

        HomePageInfoViewer Info = new HomePageInfoViewer();

        /**PUT THE PICTURE PATH*/
        Info.setPicture(userInfo.getProfileImage());
        /**PUT SOME INFO AS STRING*/
        Info.setLabel(userInfo.getFullName());
        //Info.setGroupsBtn(userInfo);
        Info.setButtons();

        add(Info,0,0);

        Info.CreateGroupBtn.setOnMouseClicked(event -> {
            Optional<String> check =  Utility.createWindow("Group Name", Group.CREATE_GROUP);
                    if (!check.equals(Optional.empty())) {
                        if (check.get().equals("") || !validator.valdiateName(check.get())) {
                            Platform.runLater(() -> Utility.errorWindow("not correct name"));
                        } else {
                            MainWindow.clientLoggedUser.createGroup(check.get());
                        }

                }});

        Content content = new Content();
        //to add post
        content.postWriter.SavePost(id);
        add(content,1,0);
        ScrollPane sp = new ScrollPane(content);
        sp.setFitToWidth(true);
        add(sp,1,0);

    }
}


