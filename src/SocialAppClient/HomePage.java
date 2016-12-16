package SocialAppClient;

import SocialAppGeneral.*;
import javafx.application.Platform;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.ArrayList;
import java.util.Optional;

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
            void pick(UserInfo userInfo) {
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
            void onFinish(ArrayList<Group> groups) {
                Platform.runLater(() -> Info.setGroupsBtn(groups));

            }
        };


        Info.CreateGroupBtn.setOnMouseClicked(event -> {

            Optional<String> check =  Utility.createWindow("Group Name", Group.CREATE_GROUP);
                    if (!check.equals(Optional.empty())) {
                        if (check.get().equals("") || !validator.valdiateName(check.get())) {
                            Platform.runLater(() -> Utility.errorWindow("not correct name"));
                        } else {
                            MainWindow.clientLoggedUser.createGroup(check.get());
                        }

                }});

        Content content = new Content(Relations.HOME_PAGE.toString());
        //to add post
        content.postWriter.SavePost(Relations.HOME_PAGE.toString(), id);

        /**************************************/
        MainWindow.clientLoggedUser.new GetPosts(1){
            @Override
            void onFinish(ArrayList<Post> posts) {
                for(int  i=0;i<1;i++){
                    System.out.println(posts.get(i).convertToJsonString());
                    Platform.runLater(() -> content.postContainer.addPosts(posts));

                }
            }
        };

        add(content,1,0);

        ScrollPane sp = new ScrollPane(content);
        sp.setFitToWidth(true);
        add(sp,1,0);

    }
}


