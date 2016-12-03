package SocialAppClient;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

/**
 * Created by kemo on 09/11/2016.
 */
public class MainWindow extends GridPane {
   static Pane mainFrame;
   static Pane mainWindow;
    public MainWindow(long id)
    {
        mainFrame = new Pane();
        mainWindow = this;
        setWindowConstrain();
        setPanels();
        new Thread(() -> {
            try {
                new NotificationConnection("0");
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
        Pane navBar = new NavBar(null);
//        mainFrame.setBackground(new Background(new BackgroundFill(Color.GREEN,CornerRadii.EMPTY, Insets.EMPTY)));
        FriendList friendList = new FriendList();
        GridPane.setConstraints(friendList,1,1);
        //friendList.setBackground(new Background(new BackgroundFill(Color.YELLOW,CornerRadii.EMPTY, Insets.EMPTY)));
        getChildren().addAll(navBar,mainFrame,friendList);
        navigateTo(new HomePage());
    }
    static void navigateTo(Pane frame)
    {
        mainWindow.getChildren().remove(mainFrame);
        mainFrame = frame;
        GridPane.setConstraints(mainFrame,0,1);
       mainWindow.getChildren().add(mainFrame);
    }
}
