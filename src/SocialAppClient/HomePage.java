package SocialAppClient;

import SocialAppGeneral.*;
import SocialAppGeneral.Command;
import SocialAppGeneral.Like;
import SocialAppGeneral.Post;
import SocialAppGeneral.UserInfo;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by kemo on 10/11/2016.
 */
public class HomePage extends GridPane {
    private String id;
    private String Isadmin;
    private UserInfo userInfo;
    public HomePage(String id) {
        this.id = id;
        //setBackground(new Background(new BackgroundFill(Color.web(ClientTheme.BackGround, 1), CornerRadii.EMPTY, Insets.EMPTY)));
        setStyle(Styles.DEFAULT_BACKGROUND);
        setGridLinesVisible(true);
        setConstraint();

        Command command = new Command();
        command.setKeyWord(UserInfo.PICK_INFO);
        command.setSharableObject(id);
        CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket, command) {
            @Override
            void analyze(Command cmd) {
                userInfo = UserInfo.fromJsonString(cmd.getObjectStr());
                HomePage.this.userInfo = userInfo;
                Command command = new Command();
                command.setKeyWord("ADMIN_CHECK");
                command.setSharableObject(id);
                CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket, command) {
                    @Override
                    void analyze(Command cmd) {
                   System.out.print(cmd.getObjectStr());
                        Isadmin=cmd.getObjectStr();
                        Platform.runLater(() -> setPanels());
                    }
                };
                CommandsExecutor.getInstance().add(commandRequest);

            }
        };
        CommandsExecutor.getInstance().add(commandRequest);
    }
   // public  void setIsAdmin(Boolean IsAdmin){this.Isadmin=IsAdmin;}

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
        if(Isadmin.equals("true")){
            HBox hb =new HBox(10);
            Label email=new Label("blab blab blab");
            //Image I=new Image("C:\\Users\\mosta\\Desktop\\1245686792938124914raemi_Check_mark.svg.hi.png");
            Button B1 =new Button();
           // B1.setBackground(new Background(new BackgroundImage(I,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,BackgroundSize.DEFAULT)));
            Button B2 =new Button();
            Button B3= new Button();
            hb.getChildren().addAll(email,B1,B2,B3);
            ListView l=new ListView();
            Info .getChildren().addAll(l);
        }

        ScrollPane scrollPane = new ScrollPane(Info);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        add(scrollPane,0,0);
            ArrayList<Group> list=MainWindow.clientLoggedUser.loadGroups();
        if(list.size() !=0) {
            Info.setGroupsBtn(list);
        }
        
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
        content.postWriter.SavePost(Relations.USERS.toString(), id);
        add(content,1,0);
        ScrollPane sp = new ScrollPane(content);
        sp.setFitToWidth(true);
        add(sp,1,0);

    }
}


