package SocialAppClient.View;

import SocialAppClient.Control.GroupMember;
import SocialAppClient.Control.GroupPicker;
import SocialAppClient.SocialAppGeneral.Post;
import SocialAppClient.SocialAppGeneral.*;
import SocialAppClient.SocialAppGeneral.Relations;
import javafx.application.Platform;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.ArrayList;

/**
 * Created by kemo on 10/11/2016.
 */
public class GroupPage extends GridPane {
    private Group group;
   public GroupPage(Group group) {
        this.group = group;
        setStyle(Styles.DEFAULT_BACKGROUND);
        //updateColor(this);
        setGridLinesVisible(true);
        setConstraint();
        setPanels();
    }
    public GroupPage(long id) {
        setStyle(Styles.DEFAULT_BACKGROUND);
        //updateColor(this);
        setGridLinesVisible(true);
        setConstraint();
        new GroupPicker().new InfoPicker(id) {
            @Override
            public void pick(Group group) {
                GroupPage.this.group = group;
                Platform.runLater(() -> setPanels());
            }
        };
    }


    private void setConstraint(){

        ColumnConstraints columnConstraints0 = new ColumnConstraints();
        columnConstraints0.setPercentWidth(30);
        ColumnConstraints columnConstraints1 = new ColumnConstraints();
        columnConstraints1.setPercentWidth(70);

        getColumnConstraints().addAll(columnConstraints0,columnConstraints1);

        RowConstraints rowConstraints0 = new RowConstraints();
        rowConstraints0.setPercentHeight(100);
        getRowConstraints().addAll(rowConstraints0);

    }
    private void setPanels(){

        GroupInfoViewer Info = new GroupInfoViewer(group);

        /*PUT THE PICTURE PATH*/
        Info.setPicture("Group");
        /**PUT SOME INFO AS STRING*/
        Info.setLabel("GROUP NAME: "+group.getName());
        //Info.search();
        /**ADD JOIN AND EDIT BUTTON -- EDIT THEM IN GROUPINFOVIEWER*/
        Info.setButtons();

        add(Info,0,0);



        new GroupMember.GetStatus(Long.toString(group.getId())) {
            @Override
            public void onFinish(String s) {
                if (s.equals(RelationGroup.MEMBER.toString())) {
                    Platform.runLater(() -> {
                        Content content = new Content(Relations.GROUP.toString());
                        content.postWriter.SavePost(Relations.GROUP.toString(), "" + group.getId());
                        //add(content,1,0);
                        ScrollPane sp = new ScrollPane(content);
                        sp.setFitToWidth(true);
                        add(sp,1,0);


                        MainWindow.clientLoggedUser.new GetPostsGroup(1, group.getId()) {
                            @Override
                            public void onFinish(ArrayList<Post> posts) {
                                Platform.runLater(() -> content.postContainer.addPosts(posts, "" + group.getId()));
                            }
                        };
                    });
                }
            }
        };



        /**THE SCROLL BAR KEEPS TRACK THE CONTENT*/

/*
        Info.Edit.setOnAction(event -> {
            getChildren().remove(content);
            //TODO #khaled
            //send info
            add(new EditInfo(null),1,0);
            sp.setContent(null);
        });*/
    }
}
