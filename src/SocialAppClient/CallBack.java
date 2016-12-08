package SocialAppClient;

import SocialAppGeneral.Post;

/**
 * Created by billy on 2016-12-07.
 */
public interface CallBack {
    void showPostDetails(Post post);
    void removePostWriter();
    void commentedit(String text);
}
