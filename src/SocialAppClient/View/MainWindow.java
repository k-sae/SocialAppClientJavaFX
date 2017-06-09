package SocialAppClient.View;

import SocialAppClient.Connections.*;
import SocialAppClient.Control.ClientAdmin;
import SocialAppClient.Control.ClientLoggedUser;
import SocialAppClient.Control.Utility;
import SocialAppClient.SocialAppGeneral.*;
import javafx.application.Platform;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by kemo on 09/11/2016.
 */
public class MainWindow extends GridPane {

   private static Pane mainFrame;
   private static Pane mainWindow;
    public static java.lang.String id;
    private NavBar navBar;
    static ClientLoggedUser clientLoggedUser;
    public MainWindow(String id)
    {
        MainWindow.id = id;
        //if not admin

        //if admin
        Command command = new Command();
        command.setKeyWord(Admin.KEYWORD);
        command.setSharableObject(id);
        CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket, command) {
            @Override
            public void analyze(Command commandFromServer) {
                if(commandFromServer.getObjectStr().equals("true"))
                    clientLoggedUser=new ClientAdmin(id);
                else
                    clientLoggedUser = new ClientLoggedUser(id);
            }
        };
        CommandsExecutor.getInstance().add(commandRequest);
        mainFrame = new Pane() ;
        mainWindow = this;
        setWindowConstrain();
        setPanels();
        startNotifications();
        startChat();
    }
    private void startNotifications()
    {
        final int PORT_NO = 6100;
        new Thread(() -> {
            try {
                ReceiveServerNotification receiveServerCommand = new ReceiveServerNotification(
                        new UtilityConnection(id, PORT_NO)
                        {
                            //TODO #Polymorphism #override
                            @Override
                            public void startConnection() {
                                super.startConnection();
                                clientLoggedUser.new LoadNotification() {
                                    @Override
                                    public void onFinish(SocialArrayList list) {
                                        for ( String o : list.getItems()
                                                ) {
                                            navBar.addNotification(Notification.fromJsonString(o));
                                        }
                                    }
                                };
                            }

                        }
                                .getConnectionSocket()) {
                    @Override
                    public void Analyze(Command command) {
                        if (command.getKeyWord().equals(LoggedUser.FRIEND_REQ))
                        {
                            navBar.addFriendRequest(command.getObjectStr());
                        }
                        else if (command.getKeyWord().equals(Notification.NEW_NOTIFICATION))
                        {
                            navBar.addNotification(Notification.fromJsonString(command.getObjectStr()));
                        }
                    }
                };
                receiveServerCommand.start();
            }catch (ServerNotFound e)
            {
             Utility.cantConnectMessage();

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
    private void startChat()
    {
        final int PORT_NO = 6030;
        new Thread(() -> {
            try {
                //i should rename this later
                ReceiveServerNotification receiveServerNotification =
                        new ReceiveServerNotification(
                                new UtilityConnection(id,PORT_NO)
                                        .getConnectionSocket()) {
                    @Override
                    public void Analyze(Command command) {
                        if(command.getKeyWord().equals(Message.FETCH_MESSAGES)){
                        SocialArrayList list = SocialArrayList.convertFromJsonString(command.getObjectStr());
                        Object[] objectArray = list.getItems().toArray();
                        String[] strings = Arrays.copyOf(objectArray, objectArray.length, String[].class);
                        navBar.addNewMessage(strings);
                        }
                        else if (command.getKeyWord().equals(Message.NEW_NOTIFICATION))
                        {
                            navBar.addNewMessage(command.getObjectStr());
                        }
                    }
                };
                receiveServerNotification.start();
            }catch (ServerNotFound e)
            {
                Utility.cantConnectMessage();

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
    private void setWindowConstrain()
    {
        //set columns
        ColumnConstraints columnConstraints0 = new ColumnConstraints();
        columnConstraints0.setPercentWidth(75);
        ColumnConstraints columnConstraints1 = new ColumnConstraints();
        columnConstraints1.setPercentWidth(25);
        getColumnConstraints().addAll(columnConstraints0,columnConstraints1);
        //set rows
        RowConstraints rowConstraints0 = new RowConstraints();
        rowConstraints0.setPercentHeight(15);
        RowConstraints rowConstraints1 = new RowConstraints();
        rowConstraints1.setPercentHeight(85);
        getRowConstraints().addAll(rowConstraints0, rowConstraints1);
    }
    private void setPanels()
    {
        //mainframe
        //TODO #kareem
        //on retrieve data from server send user to navBar
         navBar = new NavBar(id);
//        mainFrame.setBackground(new Background(new BackgroundFill(Color.GREEN,CornerRadii.EMPTY, Insets.EMPTY)));
        MainWindow.clientLoggedUser.new getFriends() {
            @Override
            public void onFinish(ArrayList<String> friends) {
                FriendList friendList = new FriendList(friends);
                ScrollPane scrollPane = new ScrollPane(friendList);
                scrollPane.setFitToWidth(true);
                GridPane.setConstraints(scrollPane,1,1);
                Platform.runLater(() -> MainWindow.this.getChildren().add(scrollPane));
            }
        };


        //friendList.setBackground(new Background(new BackgroundFill(Color.YELLOW,CornerRadii.EMPTY, Insets.EMPTY)));



        getChildren().addAll(navBar,mainFrame);
        navigateTo(new HomePage(MainWindow.id));
    }
    public static void navigateTo(Pane frame)
    {
        mainWindow.getChildren().remove(mainFrame);
        mainFrame = frame;
        GridPane.setConstraints(mainFrame,0,1);
        mainWindow.getChildren().add(mainFrame);
        System.gc();
    }
}
