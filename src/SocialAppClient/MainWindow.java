package SocialAppClient;

import SocialAppGeneral.Command;
import SocialAppGeneral.LoggedUser;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

import java.util.ArrayList;

/**
 * Created by kemo on 09/11/2016.
 */
public class MainWindow extends GridPane {

   private static Pane mainFrame;
   private static Pane mainWindow;
    private NavBar navBar;
    static String id;
    static ClientLoggedUser clientLoggedUser;
    public MainWindow(String id)
    {
        MainWindow.id = id;
        clientLoggedUser = new ClientLoggedUser(id);
        mainFrame = new Pane();
        mainWindow = this;
        setWindowConstrain();
        setPanels();
        startNotifications();
    }
    private void startNotifications()
    {
        new Thread(() -> {
            try {
                ReceiveServerNotification receiveServerCommand = new ReceiveServerNotification(
                        new NotificationConnection(id)
                                .getConnectionsocket()) {
                    @Override
                    public void Analyze(Command command) {
                        if (command.getKeyWord().equals(LoggedUser.FRIEND_REQ))
                        {
                            navBar.addFriendRequest(command.getObjectStr());
                        }
                    }
                };
                receiveServerCommand.start();
            } catch (Exception e) {
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
        ArrayList<String> strings = new ArrayList<>();
        for(int i = 0; i < 10; i++)
            strings.add("4");
        FriendList friendList = new FriendList(strings);

        //friendList.setBackground(new Background(new BackgroundFill(Color.YELLOW,CornerRadii.EMPTY, Insets.EMPTY)));

        ScrollPane scrollPane = new ScrollPane(friendList);
        scrollPane.setFitToWidth(true);
        GridPane.setConstraints(scrollPane,1,1);

        getChildren().addAll(navBar,mainFrame,scrollPane);
        navigateTo(new HomePage(MainWindow.id));
    }
    static void navigateTo(Pane frame)
    {
        mainWindow.getChildren().remove(mainFrame);
        mainFrame = frame;
        GridPane.setConstraints(mainFrame,0,1);
        mainWindow.getChildren().add(mainFrame);
        System.gc();
    }
}
