package SocialAppClient;

import SocialAppGeneral.Post;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

/**
 * Created by billy on 2016-12-07.
 */
public class PostDetails extends VBox{
    protected PostViewer postViewer;
    protected CommentViewer commentViewer;
    private Post post;
    public PostDetails(Post post){
        this.post = post;
        postViewer = new PostViewer(this.post);
        commentViewer = new CommentViewer(this.post.getComments());
        setLayout();
    }
    private void setLayout(){
        setAlignment(Pos.TOP_CENTER);
        postViewer.comment.setOnMouseClicked(null);

        getChildren().addAll(postViewer,commentViewer);
    }
}
