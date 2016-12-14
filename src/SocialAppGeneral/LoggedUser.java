package SocialAppGeneral;

import java.util.ArrayList;

/**
 * Created by mosta on 27-Nov-16.
 */
public abstract class LoggedUser extends AppUser {
    public static final String ADD_FRIEND="add_Friend";
    public static final String FRIEND_REQ = "friend_req";
    public static final String FETCH_REQS = "fetch_reqs";
    public static final String GET_RELATION_STATUS = "get_relation_status";
    public static final String ACCEPT_FRIEND = "accept_friend_req";
    public static final String DECLINE_FRIEND = "decline_friend_req";
    public static final String REMOVE_FRIEND = "remove_friend";
    public static final String CANCEL_FRIEND_REQ = "cancel_friend_req";
    //TO DO
    // may change later check with kareem
    protected ArrayList<Integer> friends;
    protected ArrayList<Integer> requests;
    protected ArrayList<String> notifactions;
    protected ArrayList<Group> groups;
    protected ArrayList<String> groupsId;
    public LoggedUser(String id) {
        friends = new ArrayList<>();
        requests = new ArrayList<>();
        notifactions = new ArrayList<>();
        groups = new ArrayList<>();
        groupsId = new ArrayList<>();
        setID(id);
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    public abstract void createGroup(String name);
    public abstract void joinGroup();
    public abstract void exitGroup();
    public abstract void setFriends();
    public abstract void getFriends();
    public abstract void getNotfications();
    public abstract void getgroup();
}
