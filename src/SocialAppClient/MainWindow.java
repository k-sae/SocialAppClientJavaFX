package SocialAppClient;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * Created by kemo on 09/11/2016.
 */
public class MainWindow extends GridPane {
    public MainWindow()
    {
        setWindowConstrain();
        setPanels();
        MainServerConnection mainServerConnection = new MainServerConnection();
    }
    private void setWindowConstrain()
    {
        //set columns
        ColumnConstraints columnConstraints0 = new ColumnConstraints();
        columnConstraints0.setPercentWidth(100);
        ColumnConstraints columnConstraints1 = new ColumnConstraints(200);
        getColumnConstraints().addAll(columnConstraints1,columnConstraints0);
        //set rows
        RowConstraints rowConstraints0 = new RowConstraints(80);
        RowConstraints rowConstraints1 = new RowConstraints();
        rowConstraints1.setPercentHeight(100);
        getRowConstraints().addAll(rowConstraints0, rowConstraints1);
    }
    private void setPanels()
    {
        //mainframe
        Pane mainframe = new HomePage(this);
        GridPane.setConstraints(mainframe,1,1);
        Pane navBar = new NavBar(mainframe, this);
        mainframe.setBackground(new Background(new BackgroundFill(Color.GREEN,CornerRadii.EMPTY, Insets.EMPTY)));
        GridPane friendList = new GridPane();
        GridPane.setConstraints(friendList,0,1);
        friendList.setBackground(new Background(new BackgroundFill(Color.YELLOW,CornerRadii.EMPTY, Insets.EMPTY)));
        getChildren().addAll(navBar,mainframe,friendList);
    }
}
