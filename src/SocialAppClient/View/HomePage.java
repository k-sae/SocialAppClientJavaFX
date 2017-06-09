package SocialAppClient.View;

import SocialAppClient.Control.UserPicker;
import SocialAppClient.Control.Utility;
import SocialAppClient.Main;
import SocialAppClient.SocialAppGeneral.Group;
import SocialAppClient.SocialAppGeneral.Post;
import SocialAppClient.SocialAppGeneral.Relations;
import SocialAppClient.SocialAppGeneral.UserInfo;
import javafx.application.Platform;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Created by kemo on 10/11/2016.
 */
public class HomePage extends GridPane {
    private String id;
    private UserInfo userInfo;
    public HomePage(String id) {
        this.id = id;
        //setBackground(new Background(new BackgroundFill(Color.web(ClientTheme.BackGround, 1), CornerRadii.EMPTY, Insets.EMPTY)));
        setStyle(Styles.DEFAULT_BACKGROUND);
        setGridLinesVisible(true);
        setConstraint();
        new UserPicker().new InfoPicker(id) {
            @Override
            public void pick(UserInfo userInfo) {
                if (userInfo.getProfileImage().equals("unknown"))
                {
                    Platform.runLater(() -> {
                       Optional<ButtonType> button = Utility.confirmationMessage("error", null,"its seems that this account is deactivated\n" +
                                "would u like to reactivate it" );
                        //noinspection OptionalGetWithoutIsPresent
                        if (button.get().equals(ButtonType.OK))
                        {
                            MainWindow.clientLoggedUser.new ReActivate() {
                                @Override
                                public void onFinish(String result) {
                                    if (result.equals("false"))
                                    {
                                        Platform.runLater(() -> {
                                            Utility.alertWindow("Error","error happened while reactivating" +
                                                " pls contact us for more info");
                                            Main.logout();
                                        });
                                    }
                                    else
                                    {
                                        Platform.runLater(() -> MainWindow.navigateTo(new HomePage(MainWindow.clientLoggedUser.getID())));
                                    }
                                }
                            };
                        }
                        else
                        {
                            Main.logout();
                        }
                    });
                }
                HomePage.this.userInfo = userInfo;
                Platform.runLater(() -> setPanels());
            }
        };
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
        Info.setButtons();

        ScrollPane scrollPane = new ScrollPane(Info);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        add(Info,0,0);

        MainWindow.clientLoggedUser.new GetGroups() {
            @Override
            public void onFinish(ArrayList<Group> groups) {
                Platform.runLater(() -> Info.setGroupsBtn(groups));

            }
        };
        Info.CreateGroupBtn.setOnMouseClicked(event -> {

            Optional<String> check =  Utility.createWindow("Group Name", Group.CREATE_GROUP);
                    if (!check.equals(Optional.empty())) {
                        //noinspection OptionalGetWithoutIsPresent
                        if (check.get().equals("") || !Pattern.matches("[A-Za-z]{1,10}+(\\s[A-Za-z]{1,10}+)?",check.get())) {
                            Platform.runLater(() -> Utility.errorWindow("not correct name"));
                        } else {
                            //noinspection OptionalGetWithoutIsPresent
                            MainWindow.clientLoggedUser.createGroup(check.get());
                        }

                }});

        Content content = new Content(Relations.HOME_PAGE.toString());
        content.postWriter.SavePost(Relations.HOME_PAGE.toString(), id);

        MainWindow.clientLoggedUser.new GetPosts(1){
            @Override
            public void onFinish(ArrayList<Post> posts) {
                Platform.runLater(() -> content.postContainer.addPosts(posts,MainWindow.id));
            }
        };

        add(content,1,0);

        ScrollPane sp = new ScrollPane(content);
        sp.setFitToWidth(true);
        add(sp,1,0);

    }
}


