package SocialAppClient.View;


import SocialAppClient.SocialAppGeneral.Post;
import SocialAppClient.SocialAppGeneral.Relations;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * Created by billy on 2016-11-26.
 */
class PostContainer extends VBox{
    private Button loadPostBtn;
    private static String relation;
    private long loadMoreNum;
    PostContainer(String relation) {
        PostContainer.relation = relation;
        loadMoreNum =1;
        setLayout();
    }

    private void setLayout() {
        setAlignment(Pos.TOP_CENTER);
        setSpacing(20);
        setPadding(new Insets(30, 0, 30, 0));
    }
    void addPosts(ArrayList<Post> posts, String id) {

        for (Post post : posts) {
            PostViewer postViewer = new PostViewer(relation, post);
            getChildren().add(postViewer);
        }

        if(!relation.equals(Relations.HOME_PAGE.toString())) {

            if (posts.size() == 10) {
                loadPostBtn = new Button("LOAD MORE");
                loadPostBtn.setStyle(Styles.BLACK_BUTTON);
                loadPostBtn.setOnMouseEntered(event -> loadPostBtn.setStyle(Styles.BLACK_BUTTON_HOVER));
                loadPostBtn.setOnMouseExited(event -> loadPostBtn.setStyle(Styles.BLACK_BUTTON));

                getChildren().add(loadPostBtn);
                loadPostBtn.setOnMouseClicked(event -> {
                    getChildren().remove(loadPostBtn);
                    loadMoreNum++;
                    if (relation.equals(Relations.PROFILE_PAGE.toString())) {
                        MainWindow.clientLoggedUser.new GetPostsProfile(loadMoreNum, id) {
                            @Override
                            public void onFinish(ArrayList<Post> posts) {
                                Platform.runLater(() -> addPosts(posts, id));
                            }
                        };
                    } else if (relation.equals(Relations.GROUP.toString())) {
                        MainWindow.clientLoggedUser.new GetPostsGroup(loadMoreNum, Long.parseLong(id)) {
                            @Override
                            public void onFinish(ArrayList<Post> posts) {
                                Platform.runLater(() -> addPosts(posts, id));
                            }
                        };
                    }
                });

            }
        }
    }

}
