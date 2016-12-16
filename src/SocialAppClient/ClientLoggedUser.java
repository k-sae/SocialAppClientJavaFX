package SocialAppClient;

import SocialAppGeneral.*;
import SocialAppGeneral.LoggedUser;
import javafx.application.Platform;

import java.util.ArrayList;

/**
 * Created by kemo on 10/12/2016.
 */

public class ClientLoggedUser extends LoggedUser {
    public ClientLoggedUser(String id) {
        super(id);
    }

    //TODO #khaled
    //>>>>>>>>>>>>>>>>>>>>>>>>
    @Override
    public void createGroup(String check) {
        Command command = new Command();
        command.setKeyWord(Group.CREATE_GROUP);
        Group group=new Group(check);
        group.setAdminId(Long.parseLong(MainWindow.id));
        command.setSharableObject(group.convertToJsonString());

        CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket, command) {


            @Override
            void analyze(Command cmd) {
                if (cmd.getKeyWord().equals(Group.CREATE_GROUP)) {
                    Group group1 = Group.fromJsonString(cmd.getObjectStr());
                    //TODO #Fix
                    //fix error on threading
                    groupsId.add(""+group1.getId());
                    SocialArrayList socialArrayList = new SocialArrayList();
                    socialArrayList.getItems().addAll(groupsId);
                 //   inorder to recieve in server
                   //@SuppressWarnings("unchecked") ArrayList<String>s=(ArrayList<String>)(ArrayList<?>) socialArrayList.getItems();
                    Platform.runLater(() -> MainWindow.navigateTo(new GroupPage(group1)));

                }
            }
        };
        CommandsExecutor.getInstance().add(commandRequest);


    }

    @Override
    public void joinGroup() {

    }

    @Override
    public void exitGroup() {

    }
    //<<<<<<<<<<<<<<<<<<<<<<<<

    //TODO #kareem
    //>>>>>>>>>>>>>>>>>>>>>>>>>
    @Override
    public void setFriends() {
        
    }

    @Override
    public void getFriends() {

    }

    @Override
    public void getNotfications() {

    }

    @Override
    public void getgroup() {
        Command command = new Command();
        command.setKeyWord(Group.LOAD_GROUP);
        CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket, command) {
            @Override
                //TODO #ُERORE
            void analyze(Command cmd) {
                if (cmd.getKeyWord().equals(Group.LOAD_GROUP)) {
                    SocialArrayList list=SocialArrayList.convertFromJsonString(cmd.getObjectStr());
                    for(int i=0;i<list.getItems().size();i++) {
                        getGroups().add(Group.fromJsonString((String)list.getItems().get(i)));
                    }
                }
            }

        };
        CommandsExecutor.getInstance().add(commandRequest);

    }
    public ArrayList<Group> loadGroups(){
        getgroup();
        return  getGroups();
    }
    abstract class GetGroups
    {
        GetGroups()
        {
            Command command = new Command();
            command.setKeyWord(Group.LOAD_GROUPS);
            CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket, command) {
                @Override
                    //TODO #ُERORE
                void analyze(Command cmd) {
                    if (cmd.getKeyWord().equals(Group.LOAD_GROUPS)) {
                        getGroups().clear();
                        SocialArrayList list=SocialArrayList.convertFromJsonString(cmd.getObjectStr());
                        for(int i=0;i<list.getItems().size();i++) {
                         getGroups().add(Group.fromJsonString((String)list.getItems().get(i)));
                        }
                        onFinish(getGroups());
                    }
                }

            };
            CommandsExecutor.getInstance().add(commandRequest);
        }

        abstract void onFinish(ArrayList<Group> groups);
    }
    //<<<<<<<<<<<<<<<<<<<<<<<<<<
    //Using inner abstract class to get results
    //as these functions run in another threads
    abstract class GetFriendReq
    {
        GetFriendReq()
        {
            Command command = new Command();
            command.setKeyWord(LoggedUser.FETCH_REQS);
            CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket,command) {
                @Override
                void analyze(Command cmd) {
                    onFinish(cmd);
                }
            };
            CommandsExecutor.getInstance().add(commandRequest);
        }
        abstract void onFinish(Command cmd);
    }
        abstract class GetStatus
        {
            GetStatus(String id)
            {
                Command command = new Command();
                command.setKeyWord(LoggedUser.GET_RELATION_STATUS);
                command.setSharableObject(id);
                CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket,command) {
                    @Override
                    void analyze(Command cmd) {
                        onFinish(cmd.getObjectStr());
                    }
                };
            CommandsExecutor.getInstance().add(commandRequest);
            }
            abstract void onFinish(String s);
        }

        abstract class AcceptFriendReq
        {
            AcceptFriendReq(String id)
            {
                Command command = initialize(id);
                command.setKeyWord(LoggedUser.ACCEPT_FRIEND);
                CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket, command) {
                    @Override
                    void analyze(Command commandFromServer) {
                        onFinish(commandFromServer);
                    }
                };
                CommandsExecutor.getInstance().add(commandRequest);
            }
            abstract void onFinish(Command cmd);
        }

        abstract class DeclineFriendReq
        {
            DeclineFriendReq(String id)
            {
                Command command = initialize(id);
                command.setKeyWord(LoggedUser.DECLINE_FRIEND);
                CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket, command) {
                    @Override
                    void analyze(Command commandFromServer) {
                      onFinish(commandFromServer);
                    }
                };
                CommandsExecutor.getInstance().add(commandRequest);
            }
            abstract void onFinish(Command cmd);
        }
        abstract class RemoveFriend
        {
            RemoveFriend(String id)
            {
                Command command = initialize(id);
                command.setKeyWord(LoggedUser.REMOVE_FRIEND);
                CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket, command) {
                    @Override
                    void analyze(Command commandFromServer) {
                        onFinish(commandFromServer);
                    }
                };
                CommandsExecutor.getInstance().add(commandRequest);
            }
            abstract void onFinish(Command cmd);
        }
        abstract class CancelFriendReq
        {
            CancelFriendReq(String id)
            {
                Command command = initialize(id);
                command.setKeyWord(LoggedUser.CANCEL_FRIEND_REQ);
                CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket, command) {
                    @Override
                    void analyze(Command commandFromServer) {
                        onFinish(commandFromServer);
                    }
                };
                CommandsExecutor.getInstance().add(commandRequest);
            }
            abstract void onFinish(Command cmd);
        }
    abstract class addFriend
    {
        addFriend(String id)
        {
            Command command = initialize(id);
            command.setKeyWord(LoggedUser.ADD_FRIEND);
            CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket, command) {
                @Override
                void analyze(Command commandFromServer) {
                   onFinish(commandFromServer);
                }
            };
            CommandsExecutor.getInstance().add(commandRequest);
        }
        abstract void onFinish(Command cmd);
    }
    private Command initialize(String id)
    {
        Command command = new Command();
        command.setSharableObject(id);
        return command;
    }

    public void savePostUser(String relation, String text){
        Post post=new Post();
        post.setOwnerId(Long.parseLong(MainWindow.id));
        post.setContent(text);
        post.setPostPos(Long.parseLong(MainWindow.id));
        Command command = new Command();
        command.setKeyWord(Post.SAVE_POST_USER);
        command.setSharableObject(post.convertToJsonString());
        CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket,command) {
            @Override
            void analyze(Command cmd) {
                if (cmd.getKeyWord().equals(Post.SAVE_POST_USER)) {
                    if(relation.equals(Relations.HOME_PAGE.toString()))
                        Platform.runLater(() -> MainWindow.navigateTo(new HomePage(MainWindow.id)));
                    else
                        Platform.runLater(() -> MainWindow.navigateTo(new ProfilePage(""+post.getPostPos())));

                }
            }
        };
        CommandsExecutor.getInstance().add(commandRequest);
    }
    public void savePostGroup(String text, String id){
        Post post=new Post();
        post.setOwnerId(Long.parseLong(MainWindow.id));
        post.setContent(text);
        post.setPostPos(Long.parseLong(id));
        Command command = new Command();
        command.setKeyWord(Post.SAVE_POST_GROUP);
        command.setSharableObject(post.convertToJsonString());
        CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket,command) {
            @Override
            void analyze(Command cmd) {
                if (cmd.getKeyWord().equals(Post.SAVE_POST_GROUP)) {
                    Platform.runLater(() -> MainWindow.navigateTo(new GroupPage(Long.parseLong(id))));

                }
            }
        };
        CommandsExecutor.getInstance().add(commandRequest);
    }

    public void editPostUser(long postid, long postpos, String text) {
        Post post1 = new Post();
        post1.setId(postid);
        post1.setPostPos(postpos);
        post1.setContent(text);
        Command command = new Command();
        command.setKeyWord(Post.EDITE_POST_USERS);
        command.setSharableObject(post1.convertToJsonString());
        CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket, command) {
            @Override
            void analyze(Command cmd) {
                if (cmd.getKeyWord().equals(Post.EDITE_POST_USERS)) {

                    Post b = Post.fromJsonString(cmd.getObjectStr());
                    if (b.getId() ==0) {
                        Platform.runLater(() -> Utility.errorWindow("please refresh window"));


                    }
                }
            }
        };
        CommandsExecutor.getInstance().add(commandRequest);
    }
    public void editPostGroup(long postid, long postpos, String text) {
        Post post1 = new Post();
        post1.setId(postid);
        post1.setPostPos(postpos);
        post1.setContent(text);
        Command command = new Command();
        command.setKeyWord(Post.EDITE_POST_GROUPS);
        command.setSharableObject(post1.convertToJsonString());
        CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket, command) {
            @Override
            void analyze(Command cmd) {
                if (cmd.getKeyWord().equals(Post.EDITE_POST_GROUPS)) {

                    Post b = Post.fromJsonString(cmd.getObjectStr());
                    if (b.getId() ==0) {
                        Platform.runLater(() -> Utility.errorWindow("please refresh window"));
                    }
                }
            }
        };
        CommandsExecutor.getInstance().add(commandRequest);
    }

    public void deletePostUser(Post post){
        Command command = new Command();
        command.setKeyWord(Post.DELETE_POST_USERS);
        command.setSharableObject(post.convertToJsonString());
        CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket, command) {
            @Override
            void analyze(Command cmd) {
                if (cmd.getKeyWord().equals(Post.DELETE_POST_USERS)) {

                }
            }
        };
        CommandsExecutor.getInstance().add(commandRequest);
    }
    public void deletePostGroup(Post post){
        Command command = new Command();
        command.setKeyWord(Post.DELETE_POST_GROUPS);
        command.setSharableObject(post.convertToJsonString());
        CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket, command) {
            @Override
            void analyze(Command cmd) {
                if (cmd.getKeyWord().equals(Post.DELETE_POST_GROUPS)) {

                }
            }
        };
        CommandsExecutor.getInstance().add(commandRequest);
    }

    public void setCommentCommendUser(int show, String text, long commentid, long postid, long postPos){
        Comment comment=new Comment();
        comment.setCommentcontent(text);
        comment.setOwnerID(Long.parseLong(MainWindow.id));
        comment.setShow(show);
        comment.setCommentId(commentid);
        Post post1 = new Post();
        post1.setId(postid);
        post1.setPostPos(postPos);
        post1.addcomment(comment);
        Command command = new Command();
        command.setKeyWord(Post.EDITE_POST_USERS);
        command.setSharableObject(post1.convertToJsonString());
        CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket,command) {
            @Override
            void analyze(Command cmd) {
                if (cmd.getKeyWord().equals(Post.EDITE_POST_USERS)) {
                    Post b= Post.fromJsonString(cmd.getObjectStr());
                    if(b.getId() !=0) {
                        Platform.runLater(() -> PostContainer.reloadPostDetails(b));
                    }
                    else{
                        Platform.runLater(() ->  Utility.errorWindow("please refresh window"));
                    }
                }
            }
        };
        CommandsExecutor.getInstance().add(commandRequest);
    }

    public void setCommentCommendGroup(int show, String text, long commentid, long postid, long postPos){
        Comment comment=new Comment();
        comment.setCommentcontent(text);
        comment.setOwnerID(Long.parseLong(MainWindow.id));
        comment.setShow(show);
        comment.setCommentId(commentid);
        Post post1 = new Post();
        post1.setId(postid);
        post1.setPostPos(postPos);
        post1.addcomment(comment);
        Command command = new Command();
        command.setKeyWord(Post.EDITE_POST_GROUPS);
        command.setSharableObject(post1.convertToJsonString());
        CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket,command) {
            @Override
            void analyze(Command cmd) {
                if (cmd.getKeyWord().equals(Post.EDITE_POST_GROUPS)) {
                    Post b= Post.fromJsonString(cmd.getObjectStr());
                    if(b.getId() !=0) {
                        Platform.runLater(() -> PostContainer.reloadPostDetails(b));
                    }
                    else{
                        Platform.runLater(() ->  Utility.errorWindow("please refresh window"));
                    }
                }
            }
        };
        CommandsExecutor.getInstance().add(commandRequest);
    }

    public void setLikecommendUsers(int i, Post post) {
        Like like = new Like();
        like.setLike(i);
        like.setOwnerID(Long.parseLong(MainWindow.id));
        Post post1 = new Post();
        post1.setId(post.getId());
        post1.setPostPos(post.getPostPos());
        post1.addlike(like);
        Command command = new Command();
        command.setKeyWord(Post.EDITE_POST_USERS);
        command.setSharableObject(post1.convertToJsonString());
        CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket, command) {
            @Override
            void analyze(Command cmd) {
                if (cmd.getKeyWord().equals(Post.EDITE_POST_USERS)) {
                    int check = Utility.checkID(post);
                    Post b= Post.fromJsonString(cmd.getObjectStr());
                    if (b.getId() !=0) {
                        if (check == -1) {
                            post.getLike().add(like);
                        } else {
                            post.getLike().get(check).setLike(i);
                        }

                    } else {

                        Platform.runLater(() ->  Utility.errorWindow("please refresh window"));


                    }

                }
            }
        };
        CommandsExecutor.getInstance().add(commandRequest);
    }
    public void setLikecommendGroup(int i, Post post) {
        Like like = new Like();
        like.setLike(i);
        like.setOwnerID(Long.parseLong(MainWindow.id));
        Post post1 = new Post();
        post1.setId(post.getId());
        post1.setPostPos(post.getPostPos());
        post1.addlike(like);
        Command command = new Command();
        command.setKeyWord(Post.EDITE_POST_GROUPS);
        command.setSharableObject(post1.convertToJsonString());
        CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket, command) {
            @Override
            void analyze(Command cmd) {
                if (cmd.getKeyWord().equals(Post.EDITE_POST_GROUPS)) {
                    int check = Utility.checkID(post);
                    Post b= Post.fromJsonString(cmd.getObjectStr());
                    if (b.getId() !=0) {
                        if (check == -1) {
                            post.getLike().add(like);
                        } else {
                            post.getLike().get(check).setLike(i);
                        }

                    } else {

                        Platform.runLater(() ->  Utility.errorWindow("please refresh window"));


                    }

                }
            }
        };
        CommandsExecutor.getInstance().add(commandRequest);
    }
    public void loadMorePostsUser(ArraylistPost posts, int number){
        posts.getPosts().clear();
        posts.setNumberpost(number);
        Command command1 = new Command();
        command1.setKeyWord(Post.LOAD_POST_USERS);
        command1.setSharableObject(posts.convertToJsonString());
        CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket, command1) {
            @Override
            void analyze(Command cmd) {
                if (cmd.getKeyWord().equals(Post.LOAD_POST_USERS)) {
                    ArraylistPost posts = (ArraylistPost.fromJsonString(cmd.getObjectStr()));
                    if (!posts.getPosts().isEmpty()) {
                        Platform.runLater(() -> PostContainer.addPosts(posts, number));
                    }

                }
            }
        };
        CommandsExecutor.getInstance().add(commandRequest);
    }
    public void loadMorePostsGroup(ArraylistPost posts, int number){
        posts.getPosts().clear();
        posts.setNumberpost(number);
        Command command1 = new Command();
        command1.setKeyWord(Post.LOAD_POST_GROUPS);
        command1.setSharableObject(posts.convertToJsonString());
        CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket, command1) {
            @Override
            void analyze(Command cmd) {
                if (cmd.getKeyWord().equals(Post.LOAD_POST_GROUPS)) {
                    ArraylistPost posts = (ArraylistPost.fromJsonString(cmd.getObjectStr()));
                    if (!posts.getPosts().isEmpty()) {
                        Platform.runLater(() -> PostContainer.addPosts(posts, number));
                    }

                }
            }
        };
        CommandsExecutor.getInstance().add(commandRequest);
    }

    public void deactivate(UserInfo userInfo){
        Command command = new Command();
        command.setKeyWord(LoggedUser.DEACTIVATE);
        command.setSharableObject(userInfo.convertToJsonString());
        CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket, command) {
            @Override
            void analyze(Command commandFromServer) {

            }
        };
        CommandsExecutor.getInstance().add(commandRequest);
    }
}
