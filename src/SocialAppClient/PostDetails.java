package SocialAppClient;

import SocialAppGeneral.Command;
import SocialAppGeneral.Comment;
import SocialAppGeneral.Post;
import SocialAppGeneral.Relations;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

/**
 * Created by billy on 2016-12-07.
 */
public class PostDetails extends VBox{
    protected PostViewer postViewer;
    protected CommentContainer CommentContainer;
    private static Post post;
    private static String relation;
    public PostDetails(String relation, Post post){
        this.post = post;
        this.relation = relation;
        postViewer = new PostViewer(relation, this.post);
        postViewer.comment.setOnMouseClicked(null);

        CommentContainer = new CommentContainer(this.post.getComments());
        setLayout();
    }
    private void setLayout(){
        setAlignment(Pos.TOP_CENTER);

        getChildren().addAll(postViewer,CommentContainer);
    }
    public static void setCommentCommend(int show, String text, long id){
        if(relation.equals(Relations.HOME_PAGE.toString()) || relation.equals(Relations.PROFILE_PAGE.toString())) {
            MainWindow.clientLoggedUser.setCommentCommendUser(show, text, id, post.getId(), post.getPostPos());
        }else if(relation.equals(Relations.GROUP.toString())){
            MainWindow.clientLoggedUser.setCommentCommendGroup(show, text, id, post.getId(), post.getPostPos());
        }

    }

}
