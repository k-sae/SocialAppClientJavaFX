package SocialAppClient.SocialAppGeneral;

public abstract class Relation {
    //add friend
    //remove friend
    //send req
    public abstract Relations getStatus(String id);

    public abstract void acceptFriendReq(String id);

    public abstract void declineFriendReq(String id);

    public abstract void removeFriend(String id);

    public abstract void cancelFriendReq(String id);
}