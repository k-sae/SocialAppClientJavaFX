package SocialAppClient;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * Created by kemo on 09/11/2016.
 */
public class MainWindow extends GridPane {
    Pane mainFrame;
    public MainWindow(long id)
    {
        mainFrame = new Pane();
        setWindowConstrain();
        setPanels();
    }
    private void setWindowConstrain()
    {
        //set columns
        ColumnConstraints columnConstraints0 = new ColumnConstraints(200);
        ColumnConstraints columnConstraints1 = new ColumnConstraints();
        columnConstraints1.setPercentWidth(100);
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
        GridPane friendList = new GridPane();
        GridPane.setConstraints(friendList,0,1);
        friendList.setBackground(new Background(new BackgroundFill(Color.YELLOW,CornerRadii.EMPTY, Insets.EMPTY)));
        getChildren().addAll(navBar,mainFrame,friendList);
        navigateTo(new HomePage());
    }
    public void navigateTo(Pane frame)
    {
        getChildren().remove(mainFrame);
        mainFrame = frame;
        GridPane.setConstraints(mainFrame,1,1);
        getChildren().add(mainFrame);
    }
}
