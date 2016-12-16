package SocialAppClient;

import SocialAppGeneral.*;
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
    private UserInfo userInfo;
    ProfilePage(String id)
    {
        this.id = id;
        /**IT WILL TAKE AN ID IN THE CONSTRUCTOR*/
        setStyle(Styles.DEFAULT_BACKGROUND);
        setGridLinesVisible(true);
        setConstraint();
        new UserPicker().new InfoPicker(id) {
            @Override
            void pick(UserInfo userInfo) {
                ProfilePage.this.userInfo = userInfo;
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


        ProfileInfoViewer Info = new ProfileInfoViewer(id);
        /**ADD PICTURE*/
        Info.setPicture(userInfo.getProfileImage());
        /**ADD INFO*/
        Info.setLabel("Name: " +userInfo.getFullName(),
                "BirthDate: " + userInfo.getBirthDate(),
                "Gender: " + userInfo.getGender());
        Content content = new Content(Relations.PROFILE_PAGE.toString());


        ArraylistPost posts =new ArraylistPost();
        posts.setOwnerPosts(Long.parseLong(id)) ;
        posts.setNumberpost(1);
        Command command = new Command();
        command.setKeyWord(Post.LOAD_POST_USERS);
        command.setSharableObject(posts.convertToJsonString());
        CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket,command) {
            @Override
            void analyze(Command cmd) {
                if (cmd.getKeyWord().equals(Post.LOAD_POST_USERS)){
                    ArraylistPost posts = (ArraylistPost.fromJsonString(cmd.getObjectStr()));
                    int loadMoreNum = 1;
                    if(!posts.getPosts().isEmpty()) {
                        Platform.runLater(() -> content.postContainer.addPosts(posts,loadMoreNum));
                    }

                }
            }
        };

        CommandsExecutor.getInstance().add(commandRequest);

        content.postWriter.SavePost(Relations.PROFILE_PAGE.toString(), id);
        add(content,1,0);
        add(Info,0,0);
        ScrollPane sp = new ScrollPane(content);
        sp.setFitToWidth(true);
        add(sp,1,0);
        Info.setButtons();
        Info.Edit.setOnAction(event -> {
            getChildren().remove(content);
            /**AFTER CLICK ON EDIT IT WILL GO TO EDIT PAGE*/
            EditInfo editInfo = new EditInfo(userInfo);
            add(editInfo,1,0);
            sp.setContent(editInfo);
        });
    }

}
