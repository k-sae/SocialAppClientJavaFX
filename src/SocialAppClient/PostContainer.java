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
    int number = 1;

    public PostContainer() {

        setLayout();
    }

    private void setLayout() {
        setAlignment(Pos.TOP_CENTER);
        setSpacing(20);
        setPadding(new Insets(30, 0, 30, 0));
    }

    public void addPosts(ArraylistPost posts) {


        for (int i = 0; i < posts.getPosts().size(); i++) {
            PostViewer postViewer = new PostViewer(posts.getPosts().get(i));
            getChildren().add(postViewer);
        }
        if(posts.getPosts().size() == 10) {
            loadPostBtn = new Button("LOAD MORE");
            loadPostBtn.setStyle(Styles.BLACK_BUTTON);
            loadPostBtn.setOnMouseEntered(event -> loadPostBtn.setStyle(Styles.BLACK_BUTTON_HOVER));
            loadPostBtn.setOnMouseExited(event -> loadPostBtn.setStyle(Styles.BLACK_BUTTON));
            getChildren().add(loadPostBtn);
            loadPostBtn.setOnMouseClicked(event -> {
                number++;
                getChildren().remove(loadPostBtn);
                posts.getPosts().clear();
                posts.setNumberpost(number);
                Command command1 = new Command();
                command1.setKeyWord(Post.LOAD_POST_USERS);
                command1.setSharableObject(posts.convertToJsonString());
                System.out.println(posts.convertToJsonString());
                CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket, command1) {
                    @Override
                    void analyze(Command cmd) {
                        if (cmd.getKeyWord().equals(Post.LOAD_POST_USERS)) {
                            ArraylistPost posts = (ArraylistPost.fromJsonString(cmd.getObjectStr()));
                            if (!posts.getPosts().isEmpty()) {
                                Platform.runLater(() -> addPosts(posts));
                            }

                        }
                    }
                };
                CommandsExecutor.getInstance().add(commandRequest);
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
