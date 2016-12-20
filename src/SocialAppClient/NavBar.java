package SocialAppClient;

import SocialAppGeneral.Command;
import SocialAppGeneral.Notification;
import SocialAppGeneral.SocialArrayList;
import SocialAppGeneral.UserInfo;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import static SocialAppClient.MainWindow.clientLoggedUser;
import static javafx.scene.layout.GridPane.setColumnSpan;
import static javafx.scene.layout.GridPane.setConstraints;

/**
 * Created by kemo on 09/11/2016.
 */
class NavBar extends HBox{

    NavBar(String id)
    {

        setLayout();
        setNavComponent();
        setNavButtons();
        requestServerFriendRequests();
    }
   //TODO
    //moheim
    private void requestServerFriendRequests()
    {
        clientLoggedUser.new GetFriendReq() {
            @Override
            void onFinish(Command cmd) {
                SocialArrayList socialArrayList = SocialArrayList.convertFromJsonString(cmd.getObjectStr());
                for (Object o: socialArrayList.getItems()
                        ) {
                    addFriendRequest((String)o);
                }
            }
        };
    }
    private void setLayout()
    {
        setAlignment(Pos.CENTER);
        setConstraints(this,0,0);
        setBackground(new Background(new BackgroundFill(Color.web("#000000"), CornerRadii.EMPTY, Insets.EMPTY)));
        setColumnSpan(this, 2);
    }

    private void setNavComponent(){
        /** logo */
        ImageView img = new ImageView(new Image("file:Resources/btatsya.png"));
        img.setFitWidth(50);
        img.setPreserveRatio(true);

        /** Title */
        Label Title = new Label("Btates");
        Title.setStyle("-fx-font: 30 verdana; -fx-text-fill: #eeeeee;");
        Title.setPadding(new Insets(0,10,0,5));

        /** Search Text */
        ComboBox<Object> Search = new ComboBox<>();
        Search.setPromptText("Search...");
        Search.setEditable(true);
        Search.setVisibleRowCount(5);
        /** Search Button with Icon */
        ImageView searchImg = new ImageView(new Image("file:Resources/search.png"));
        searchImg.setFitWidth(17);
        searchImg.setPreserveRatio(true);
        Button searchBtn = new Button("", searchImg);
        searchBtn.setOnAction(e->{

            /** Add an item when you clicked on the menu */
            Command command = new Command();
            command.setKeyWord("Search");
            command.setSharableObject(Search.getEditor().getText());
            if (Search.getEditor().getText().equals("")) return;
            CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket, command) {
                @Override
                void analyze(Command cmd) {
                    SocialArrayList socialArrayList = SocialArrayList.convertFromJsonString(cmd.getObjectStr());
                    Search.getItems().clear();
                    for (Object o: socialArrayList.getItems()) {

                            Platform.runLater(() ->{
                                {
                                    if (((String)o).contains("-")) {
                                         new GroupView(((String) o).substring(1))
                                        {
                                            @Override
                                            protected void onFinishSettingLayout(GroupView view) {
                                                super.onFinishSettingLayout(view);
                                                Search.getItems().add(view);
                                                view.setPadding(new Insets(10,0,10,0));
                                            }
                                        };

                                    }else {
                                        new FriendView((String) o)
                                        {
                                            @Override
                                            protected void onFinishSettingLayout(FriendView view) {
                                                super.onFinishSettingLayout(view);
                                                Search.getItems().add(view);
                                                view.setPadding(new Insets(10,0,10,0));
                                            }
                                        };


                                    }
                                    Search.show();
                                }

                            });
                    }
                    new Thread(() -> Platform.runLater(() -> {

                    })).start();

                }
            };
            CommandsExecutor.getInstance().add(commandRequest);
        });
        Search.setOnAction(e->{
            @SuppressWarnings("unchecked") ComboBox<Object> objectComboBox = (ComboBox<Object>) e.getSource();
            Object scene = objectComboBox.getSelectionModel().getSelectedItem();
            if (scene instanceof FriendView)
            {
                MainWindow.navigateTo(new ProfilePage(((FriendView) scene).id));
            }
            else if(scene instanceof GroupView) {
               MainWindow.navigateTo(new GroupPage((Long.parseLong(((GroupView)scene).id))));
            }

        });
        Search.setOnHiding(e-> Search.getItems().clear());

        /** Friend request menu icon */
        FRIcon = new ImageView(new Image("file:Resources/FR.png"));
        FRIcon.setFitWidth(25);
        FRIcon.setPreserveRatio(true);

         friendRequests = new Menu("",FRIcon);
        /** Add an item when you clicked on the menu */

        /** messages menu icon */
        msgIcon = new ImageView(new Image("file:Resources/msg.png"));
        msgIcon.setFitWidth(25);
        msgIcon.setPreserveRatio(true);


        msg = new Menu("",msgIcon);
        /** Add an item when you clicked on the menu */
//        msg.getItems().addAll(new MenuItem("Belal sent you a message"));

        /** Notification menu icon */
        notiIcon = new ImageView(new Image("file:Resources/noti.png"));
        notiIcon.setFitWidth(25);
        notiIcon.setPreserveRatio(true);


         notification = new Menu("",notiIcon);
        /** Add an item when you clicked on the menu */
//        notification.getItems().addAll(new MenuItem("Belal liked your photo"));

        /** Add a menu bar to contain all menus */
        MenuBar notificationsBar = new MenuBar(friendRequests, msg, notification);
        notificationsBar.setBackground(null);
        notificationsBar.setPadding(new Insets(0,0,0,10));

        getChildren().addAll(img, Title, Search, searchBtn, notificationsBar);
    }

    private void setNavButtons()
    {

        Button homeBtn = new Button("Home");

        homeBtn.setStyle(Styles.NAVBAR_BUTTON);
        homeBtn.setOnMouseEntered(event -> homeBtn.setStyle(Styles.NAVBAR_BUTTON_HOVER));
        homeBtn.setOnMouseExited(event -> homeBtn.setStyle(Styles.NAVBAR_BUTTON));


        homeBtn.setOnMouseClicked(event -> MainWindow.navigateTo(new HomePage(MainWindow.id)));


        Button profileBtn = new Button("Profile");

        profileBtn.setStyle(Styles.NAVBAR_BUTTON);
        profileBtn.setOnMouseEntered(event -> profileBtn.setStyle(Styles.NAVBAR_BUTTON_HOVER));
        profileBtn.setOnMouseExited(event -> profileBtn.setStyle(Styles.NAVBAR_BUTTON));

        profileBtn.setOnMouseClicked(event -> MainWindow.navigateTo(new ProfilePage(MainWindow.id)));

/*
        Button groupsBtn = new Button("Groups");

        groupsBtn.setStyle(Styles.NAVBAR_BUTTON);
        groupsBtn.setOnMouseEntered(event -> groupsBtn.setStyle(Styles.NAVBAR_BUTTON_HOVER));
        groupsBtn.setOnMouseExited(event -> groupsBtn.setStyle(Styles.NAVBAR_BUTTON));

        groupsBtn.setOnMouseClicked(event -> {
            ((MainWindow)getParent()).navigateTo(new GroupPage());
        });
*/

        Button logoutBtn = new Button("Logout");

        logoutBtn.setStyle(Styles.NAVBAR_BUTTON);
        logoutBtn.setOnMouseEntered(event -> logoutBtn.setStyle(Styles.NAVBAR_BUTTON_HOVER));
        logoutBtn.setOnMouseExited(event -> logoutBtn.setStyle(Styles.NAVBAR_BUTTON));

        logoutBtn.setOnMouseClicked(event -> Main.logout());
        //TODO: hazem
        if (clientLoggedUser instanceof ClientAdmin)
        {
            Button approveBtn = new Button("Approves");
            approveBtn.setStyle(Styles.NAVBAR_BUTTON);
            approveBtn.setOnMouseEntered(event -> approveBtn.setStyle(Styles.NAVBAR_BUTTON_HOVER));
            approveBtn.setOnMouseExited(event -> approveBtn.setStyle(Styles.NAVBAR_BUTTON));

            approveBtn.setOnMouseClicked(event -> Platform.runLater(() -> Content.navigateTo(new AdminApprovalPage())));
/*

            Button logBtn = new Button("Log");
            logBtn.setStyle(Styles.NAVBAR_BUTTON);
            logBtn.setOnMouseEntered(event -> logBtn.setStyle(Styles.NAVBAR_BUTTON_HOVER));
            logBtn.setOnMouseExited(event -> logBtn.setStyle(Styles.NAVBAR_BUTTON));
            logBtn.setOnMouseClicked(event -> {
                MainWindow.clientLoggedUser.new GetLogs() {
                    @Override
                    void onFinish(ArrayList<Log> logs) {
                        Platform.runLater(() -> PostContainer.navigateTo(new LogHistory(logs)));

                    }
                };
            });*/

            getChildren().addAll(homeBtn, profileBtn, /*groupsBtn,*/ logoutBtn, approveBtn/*, logBtn */);
        }else
        getChildren().addAll(homeBtn, profileBtn, /*groupsBtn,*/ logoutBtn);
    }
    //////////////////////////start of my area

    private Menu friendRequests;
    private  ImageView FRIcon;
    private  Menu notification;
    private  ImageView notiIcon;
    private Menu msg;
    private  ImageView msgIcon;

    public void addFriendRequest(String... ids)
    {   FRIcon.setImage(new Image("file:Resources/FRN.png"));
        for (String id: ids
             ) {
            new UserPicker().new InfoPicker(id) {
                @Override
                void pick(UserInfo userInfo) {

                    MenuItem menuItem = createMenuItem(userInfo);
                    menuItem.setOnAction(event -> MainWindow.navigateTo(new ProfilePage(id)));
                    Platform.runLater(() -> friendRequests.getItems().add(menuItem));
                }
            };
        }
        friendRequests.setOnShowing(event ->FRIcon.setImage(new Image("file:Resources/FR.png")));

    }
    void addNewMessage(String... ids){
        if(ids.length>0)
            msgIcon.setImage(new Image("file:Resources/msgN.png"));
        for (String id: ids
                ) {
            new UserPicker().new InfoPicker(id) {
                @Override
                void pick(UserInfo userInfo) {
                    MenuItem menuItem = createMenuItem(userInfo);
                    menuItem.setText(menuItem.getText() + " Sent a message");
                    menuItem.setOnAction(event -> new ChatWindow(id));
                    Platform.runLater(() -> msg.getItems().add(0,menuItem));
                }
            };
        }
        msg.setOnShowing(event ->msgIcon.setImage(new Image("file:Resources/msg.png")));
    }
    private static MenuItem createMenuItem(UserInfo userInfo)
    {
        return new MenuItem(userInfo.getFullName(),Utility.getCircularImage(userInfo.getProfileImage(),10));
    }

    void addNotification(Notification... notifications){
        notiIcon.setImage(new Image("file:Resources/notiN.png"));
        for (Notification notification: notifications
             ){
            new UserPicker().new InfoPicker(notification.getIdSender()) {
                @Override
                void pick(UserInfo userInfo) {
                    MenuItem menuItem = createMenuItem(userInfo);
                    menuItem.setText(menuItem.getText() + " " + notification.getKeyword().toString().toLowerCase() + " on your post");
                    menuItem.setOnAction(event -> {
                        Platform.runLater(() -> Content.showPostDetails(notification.getPost()));
                    });
                    Platform.runLater(() -> NavBar.this.notification.getItems().add(menuItem));
                }
            };
        }
        notification.setOnShowing(event ->notiIcon.setImage(new Image("file:Resources/noti.png")));
    }
}
