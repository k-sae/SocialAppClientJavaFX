package SocialAppClient;

import SocialAppGeneral.ArraylistPost;
import SocialAppGeneral.Command;
import SocialAppGeneral.Post;
import javafx.application.Platform;
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
    int c=0;
    public PostContainer(){

        setLayout();
    }

    private void setLayout(){
        setAlignment(Pos.TOP_CENTER);
        setSpacing(20);
        setPadding(new Insets(30,0,30,0));
    }

    public void addPosts(ArraylistPost posts){


        for (int i=0; i<10 && c<posts.getPosts().size(); i++ ) {
            PostViewer postViewer = new PostViewer(posts.getPosts().get(c));
            getChildren().add(postViewer);
            c++;
        }
        if(c<posts.getPosts().size()) {
            loadPostBtn = new Button("LOAD MORE");
            getChildren().add(loadPostBtn);
            loadPostBtn.setOnMouseClicked(event -> {
                getChildren().remove(loadPostBtn);/*
            posts.setOwnerPosts(Long.parseLong("1")) ;
            Command command = new Command();
            command.setKeyWord(Post.LOAD_POST_USERS);
            command.setSharableObject(posts.convertToJsonString());
            CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket,command) {
                @Override
                void analyze(Command cmd) {
                    if (cmd.getKeyWord().equals(Post.LOAD_POST_USERS)){
                        ArraylistPost posts = (ArraylistPost.fromJsonString(cmd.getObjectStr()));
                        if(!posts.getPosts().isEmpty()) {
                            Platform.runLater(() -> addPosts(posts));
                        }

                    }
                }
            };
            CommandsExecutor.getInstance().add(commandRequest);*/
                Platform.runLater(() -> addPosts(posts));
            });
        }
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
