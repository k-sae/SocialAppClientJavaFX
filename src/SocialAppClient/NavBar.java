package SocialAppClient;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import static javafx.scene.layout.GridPane.setColumnSpan;
import static javafx.scene.layout.GridPane.setConstraints;

/**
 * Created by kemo on 09/11/2016.
 */
public class NavBar extends HBox {
    Pane mainFrame;
    MainWindow mainWindow;
    public NavBar(Pane mainFrame, MainWindow mainWindow)
    {
        this.mainWindow = mainWindow;
        this.mainFrame = mainFrame;
        setLayout();
        setNavButtons();
    }
    private void setLayout()
    {
        setConstraints(this,0,0);
        setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        setColumnSpan(this, 2);
    }
    private void setNavButtons()
    {

        Button homeBtn = new Button("home");
        homeBtn.setOnMouseClicked(event -> {
          mainWindow.getChildren().remove(mainFrame);
            mainFrame = new HomePage(mainFrame);
            setConstraints(mainFrame, 1,1);
            mainWindow.getChildren().add(mainFrame);
        });
        Button profileBtn = new Button("profile");
        profileBtn.setOnMouseClicked(event -> {
            mainWindow.getChildren().remove(mainFrame);
            mainFrame = new ProfilePage();
            setConstraints(mainFrame, 1,1);
            mainWindow.getChildren().add(mainFrame);
        });
        Button groupsBtn = new Button("groups");
        groupsBtn.setOnMouseClicked(event -> {
            mainWindow.getChildren().remove(mainFrame);
            mainFrame = new GroupPage();
            setConstraints(mainFrame, 1,1);
            mainWindow.getChildren().add(mainFrame);
        });
        getChildren().addAll(homeBtn, profileBtn, groupsBtn);
    }
}
