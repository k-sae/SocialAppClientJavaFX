package SocialAppClient;

import SocialAppGeneral.AppUser;
import SocialAppGeneral.Command;
import SocialAppGeneral.Group;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.Optional;

import static javafx.scene.layout.GridPane.setColumnSpan;
import static javafx.scene.layout.GridPane.setConstraints;

/**
 * Created by kemo on 09/11/2016.
 */
public class NavBar extends HBox {

    public NavBar(AppUser appUser)
    {

        setLayout();
        setNavComponent();
        setNavButtons();
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
        TextField Search = new TextField();
        Search.setPromptText("Search...");

        /** Search Button with Icon */
        ImageView searchImg = new ImageView(new Image("file:Resources/search.png"));
        searchImg.setFitWidth(17);
        searchImg.setPreserveRatio(true);

        Button searchBtn = new Button("", searchImg);


        /** Friend request menu icon */
        ImageView FRIcon = new ImageView(new Image("file:Resources/FR.png"));
        FRIcon.setFitWidth(25);
        FRIcon.setPreserveRatio(true);

        Menu friendRequestes = new Menu("",FRIcon);
        /** Add an item when you clicked on the menu */
        friendRequestes.getItems().addAll(new MenuItem("Belal sent you friend request"));

        /** messages menu icon */
        ImageView msgIcon = new ImageView(new Image("file:Resources/msg.png"));
        msgIcon.setFitWidth(25);
        msgIcon.setPreserveRatio(true);


        Menu msg = new Menu("",msgIcon);
        /** Add an item when you clicked on the menu */
        msg.getItems().addAll(new MenuItem("Belal sent you a message"));

        /** Notification menu icon */
        ImageView notiIcon = new ImageView(new Image("file:Resources/noti.png"));
        notiIcon.setFitWidth(25);
        notiIcon.setPreserveRatio(true);


        Menu notification = new Menu("",notiIcon);
        /** Add an item when you clicked on the menu */
        notification.getItems().addAll(new MenuItem("Belal liked your photo"));

        /** Add a menu bar to contain all menus */
        MenuBar notificationsBar = new MenuBar(friendRequestes, msg, notification);
        notificationsBar.setBackground(null);
        notificationsBar.setPadding(new Insets(0,0,0,10));

        getChildren().addAll(img, Title, Search, searchBtn, notificationsBar);
    }

    private void setNavButtons()
    {

        Button homeBtn = new Button("Home");

        homeBtn.setStyle("-fx-font: 20 arial; -fx-background-color: #000000; -fx-text-fill: #eeeeee;");
        homeBtn.setOnMouseEntered(event -> homeBtn.setStyle("-fx-font: 20 arial; -fx-background-color: #ffffff; -fx-text-fill: #000000;"));
        homeBtn.setOnMouseExited(event -> homeBtn.setStyle("-fx-font: 20 arial; -fx-background-color: #000000; -fx-text-fill: #eeeeee;"));


        homeBtn.setOnMouseClicked(event -> {
            MainWindow.navigateTo(new HomePage());
        });


        Button profileBtn = new Button("Profile");

        profileBtn.setStyle("-fx-font: 20 arial; -fx-background-color: #000000; -fx-text-fill: #eeeeee;");
        profileBtn.setOnMouseEntered(event -> profileBtn.setStyle("-fx-font: 20 arial; -fx-background-color: #ffffff; -fx-text-fill: #000000;"));
        profileBtn.setOnMouseExited(event -> profileBtn.setStyle("-fx-font: 20 arial; -fx-background-color: #000000; -fx-text-fill: #eeeeee;"));

        profileBtn.setOnMouseClicked(event -> {
            MainWindow.navigateTo(new ProfilePage(MainWindow.id));
        });

/*
        Button groupsBtn = new Button("Groups");

        groupsBtn.setStyle("-fx-font: 20 arial; -fx-background-color: #0000cc; -fx-text-fill: #eeeeee;");
        groupsBtn.setOnMouseEntered(event -> groupsBtn.setStyle("-fx-font: 20 arial; -fx-background-color: #ffffff; -fx-text-fill: #000000;"));
        groupsBtn.setOnMouseExited(event -> groupsBtn.setStyle("-fx-font: 20 arial; -fx-background-color: #0000cc; -fx-text-fill: #eeeeee;"));

        groupsBtn.setOnMouseClicked(event -> {
            ((MainWindow)getParent()).navigateTo(new GroupPage());
        });
*/

        Button logoutBtn = new Button("Logout");

        logoutBtn.setStyle("-fx-font: 20 arial; -fx-background-color: #000000; -fx-text-fill: #eeeeee;");
        logoutBtn.setOnMouseEntered(event -> logoutBtn.setStyle("-fx-font: 20 arial; -fx-background-color: #999999; -fx-text-fill: #000000;"));
        logoutBtn.setOnMouseExited(event -> logoutBtn.setStyle("-fx-font: 20 arial; -fx-background-color: #000000; -fx-text-fill: #eeeeee;"));

        logoutBtn.setOnMouseClicked(event -> {
            getScene().getWindow().hide();
        });
        getChildren().addAll(homeBtn, profileBtn, /*groupsBtn,*/ logoutBtn);
    }
}
