package SocialAppClient.View;

import SocialAppGeneral.Post;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/*
 * Created by billy on 2016-11-26.
 */
/**CONTENT IS A CONTAINER OF POSTWRITER AND POSTCONTAINER*/
public class Content extends VBox{
    PostWriter postWriter;
    PostContainer postContainer;
    private static Pane mainWindow;
    private static String relation;

    Content(String relation){
        mainWindow = this;
        Content.relation = relation;
        postWriter = new PostWriter();
        postContainer = new PostContainer(relation);
        setLayout();
    }

    private void setLayout(){
        setAlignment(Pos.TOP_CENTER);

        getChildren().addAll(postWriter, postContainer);
    }
    public static void navigateTo(Pane frame) {
        mainWindow.getChildren().clear();
        mainWindow.getChildren().addAll(frame);
    }
    public static void showPostDetails(Post post) {
        mainWindow.getChildren().clear();
        mainWindow.getChildren().addAll(new PostDetails(relation,post));
    }

}
