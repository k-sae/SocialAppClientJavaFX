package SocialAppClient;

import SocialAppGeneral.*;
import SocialAppGeneral.SocialArrayList;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by billy on 2016-12-11.
 */
public class AdminApprovalPage extends VBox {
    private ArrayList<String> registerInfos;
    public AdminApprovalPage(){
        setLayout();
        ((ClientAdmin) MainWindow.clientLoggedUser).new FetchRequests() {
            @Override
            public void onRetrieve(SocialArrayList list) {
                registerInfos=(ArrayList<String>)(ArrayList<?>)list.getItems();
                Platform.runLater(() -> set(registerInfos));
            }
        };

    }
    private void setLayout(){
        setAlignment(Pos.TOP_CENTER);
        setPadding(new Insets(10,10,10,10));
        setSpacing(2);
        Label title = new Label("USERS TO APPROVE");
        title.setFont(Font.font(30));

        getChildren().addAll(title, new Separator());

    }

    private void set(ArrayList<String> names){
        for (String Name:names) {
            HBox container = new HBox();
            container.setStyle(Styles.WHITE_BACKGROUND);
            Label name = new Label(Name);
            name.setWrapText(true);
            name.setFont(Font.font(20));

            Button approveBtn = new Button("Approve");
            approveBtn.setStyle(Styles.BLACK_BUTTON);
            approveBtn.setOnMouseEntered(event -> approveBtn.setStyle(Styles.BLACK_BUTTON_HOVER));
            approveBtn.setOnMouseExited(event -> approveBtn.setStyle(Styles.BLACK_BUTTON));
            approveBtn.setOnAction(event -> {
                ((ClientAdmin)MainWindow.clientLoggedUser).approve(Name);
                Platform.runLater(() -> Content.navigateTo(new AdminApprovalPage()));

            });

            Button approveAsAdminBtn = new Button("Accept as admin");
            approveAsAdminBtn.setStyle(Styles.BLACK_BUTTON);
            approveAsAdminBtn.setOnMouseEntered(event -> approveAsAdminBtn.setStyle(Styles.BLACK_BUTTON_HOVER));
            approveAsAdminBtn.setOnMouseExited(event -> approveAsAdminBtn.setStyle(Styles.BLACK_BUTTON));
            approveAsAdminBtn.setOnAction(e->{
                ((ClientAdmin)MainWindow.clientLoggedUser).approveAsAdmin(Name);
                Platform.runLater(() -> Content.navigateTo(new AdminApprovalPage()));
            });

            Button rejectBtn = new Button("Reject");
            rejectBtn.setStyle(Styles.BLACK_BUTTON);
            rejectBtn.setOnMouseEntered(event -> rejectBtn.setStyle(Styles.BLACK_BUTTON_HOVER));
            rejectBtn.setOnMouseExited(event -> rejectBtn.setStyle(Styles.BLACK_BUTTON));
            rejectBtn.setOnAction(e->{
                ((ClientAdmin)MainWindow.clientLoggedUser).reject(Name);
                Platform.runLater(() -> Content.navigateTo(new AdminApprovalPage()));
            });

            HBox btns = new HBox(10);
            btns.setAlignment(Pos.CENTER_RIGHT);
            btns.getChildren().addAll(approveBtn,approveAsAdminBtn, rejectBtn);

            container.setHgrow(btns, Priority.ALWAYS);
            container.setPadding(new Insets(5,10,5,10));
            container.getChildren().addAll(name, btns);

            getChildren().add(container);
        }
    }

}
