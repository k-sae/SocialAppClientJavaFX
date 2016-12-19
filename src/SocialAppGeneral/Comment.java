package SocialAppGeneral;

/**
 * Created by khaled hesham on 12/2/2016.
 */
public class Comment extends PostAtachmment {
    private static final long serialVersionUID = 6529685098267757690L;
    private String commentContent;
    private  long CommentId;
     private Relations show;


    public Relations getShow() {
        return show;
    }

    public void setShow(Relations show) {
        this.show = show;
    }

    public String getCommentcontent() {
        return commentContent;
    }

    public void setCommentcontent(String commentcontent) {
        this.commentContent = commentcontent;
    }

    public long getCommentId() {
        return CommentId;
    }

    public void setCommentId(long commentId) {
        CommentId = commentId;
    }
}

