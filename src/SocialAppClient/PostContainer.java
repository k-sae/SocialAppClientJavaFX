package SocialAppClient;

import SocialAppGeneral.ArraylistPost;
import SocialAppGeneral.Post;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

/**
 * Created by billy on 2016-11-26.
 */
public class PostContainer extends VBox implements CallBack {
    Button loadPostBtn;
    PostViewer postViewer;
    public PostContainer(){

        setLayout();
    }

    private void setLayout(){
        setAlignment(Pos.TOP_CENTER);
        setSpacing(20);
        setPadding(new Insets(30,0,30,0));
    }

    public void addPosts(ArraylistPost posts){

         for (int i=0; i<posts.getPosts().size(); i++ ) {
             PostViewer postViewer = new PostViewer(posts.getPosts().get(i));
             getChildren().add(postViewer);
         }
         //setPostPage();
        loadPostBtn = new Button("LOAD MORE");
        loadPostBtn.setOnMouseClicked(event -> {
            getChildren().remove(loadPostBtn);
            /********/
            getChildren().addAll(loadPostBtn);
        });

        /*

        PostViewer postViewer = new PostViewer();
        getChildren().addAll(postViewer);

        loadPostBtn = new Button("LOAD MORE");
        loadPostBtn.setOnMouseClicked(event -> {
            getChildren().remove(loadPostBtn);
            for(int i=0; i<10; i++)
                getChildren().add(new PostViewer());
            getChildren().addAll(loadPostBtn);
        });

        getChildren().addAll(loadPostBtn);*/
    }



    @Override
    public void showPostDetails(Post post) {

        getChildren().clear();
        ((CallBack)getParent()).removePostWriter();
        getChildren().addAll(new PostDetails(post));
    }
    public void removePostWriter(){
    }

    @Override
    public void setCommentCommend(int show, String text, long id) {

    }


}
