package SocialAppClient;

import SocialAppGeneral.Command;
import SocialAppGeneral.Comment;
import SocialAppGeneral.Post;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
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
        postViewer.comment.setOnMouseClicked(null);
        commentViewer = new CommentViewer(this.post.getComments());
        commentViewer.commentText.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)){
                setCommentCommend(1);
                commentViewer.commentText.setText("");
            }
        });
        setLayout();
    }
    private void setLayout(){
        setAlignment(Pos.TOP_CENTER);

        getChildren().addAll(postViewer,commentViewer);
    }
    private void setCommentCommend(int show){
        Comment comment=new Comment();
        comment.setCommentcontent(commentViewer.commentText.getText());
        comment.setOwnerID(Long.parseLong(MainWindow.id));
        comment.setShow(show);
        Post post1 = new Post();
        post1.setId(post.getId());
        post1.setPostPos(post.getPostPos());
        post1.addcomment(comment);
        Command command = new Command();
        command.setKeyWord(Post.EDIT_POST_USERS);
        command.setSharableObject(post1.convertToJsonString());
        CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket,command) {
            @Override
            void analyze(Command cmd) {
                if (cmd.getKeyWord().equals(Post.EDIT_POST_USERS)) {

                }
            }
        };
        CommandsExecutor.getInstance().add(commandRequest);
    }
}
