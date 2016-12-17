package SocialAppClient;

import SocialAppGeneral.ArraylistPost;
import SocialAppGeneral.Command;
import SocialAppGeneral.Post;
import SocialAppGeneral.Relations;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

/**
 * Created by billy on 2016-11-26.
 */
public class PostContainer extends VBox implements CallBack {
    private static Button loadPostBtn;
    private static String relation;
    private static Pane mainWindow;
    public PostContainer(String relation) {
        this.relation = relation;
        mainWindow = this;
        setLayout();
    }

    private void setLayout() {
        setAlignment(Pos.TOP_CENTER);
        setSpacing(20);
        setPadding(new Insets(30, 0, 30, 0));
    }

    public static void addPosts(ArraylistPost posts, int number) {

        for (int i = 0; i < posts.getPosts().size(); i++) {
            PostViewer postViewer = new PostViewer(relation, posts.getPosts().get(i));
            mainWindow.getChildren().add(postViewer);
        }
        if(posts.getPosts().size() == 10) {
            loadPostBtn = new Button("LOAD MORE");
            loadPostBtn.setStyle(Styles.BLACK_BUTTON);
            loadPostBtn.setOnMouseEntered(event -> loadPostBtn.setStyle(Styles.BLACK_BUTTON_HOVER));
            loadPostBtn.setOnMouseExited(event -> loadPostBtn.setStyle(Styles.BLACK_BUTTON));
            mainWindow.getChildren().add(loadPostBtn);
            loadPostBtn.setOnMouseClicked(event -> {
                int loadMoreNum = number;
                loadMoreNum++;
                mainWindow.getChildren().remove(loadPostBtn);
                if(relation.equals(Relations.PROFILE_PAGE.toString()))
                    MainWindow.clientLoggedUser.loadMorePostsUser(posts,loadMoreNum);
                else if(relation.equals(Relations.GROUP.toString()))
                    MainWindow.clientLoggedUser.loadMorePostsGroup(posts,loadMoreNum);

            });
        }
    }

    @Override
    public void showPostDetails(Post post) {

        getChildren().clear();
        ((CallBack)getParent()).removePostWriter();
        getChildren().addAll(new PostDetails(relation,post));
    }
    public void removePostWriter(){
    }

    @Override
    public void setCommentCommend(int show, String text, long id) {

    }
    public static void reloadPostDetails(Post post){
        mainWindow.getChildren().clear();
        mainWindow.getChildren().addAll(new PostDetails(relation,post));
    }

}
