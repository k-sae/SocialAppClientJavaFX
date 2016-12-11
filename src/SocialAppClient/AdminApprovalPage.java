package SocialAppClient;

import SocialAppGeneral.RegisterInfo;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.ArrayList;

/**
 * Created by billy on 2016-12-11.
 */
public class AdminApprovalPage extends VBox {
    private ArrayList<RegisterInfo> registerInfos;
    public AdminApprovalPage(ArrayList<RegisterInfo> registerInfos){
        this.registerInfos = registerInfos;
        setLayout();
    }
    public AdminApprovalPage(){

        setLayout();
    }
    private void setLayout(){
        setAlignment(Pos.TOP_CENTER);
        setPadding(new Insets(10,40,40,40));
        setSpacing(2);
        Label title = new Label("USERS TO APPROVE");
        title.setFont(Font.font(36));
        title.setPadding(new Insets(20,0,0,0));

        getChildren().addAll(title, new Separator());
        /**set();*/
        set("belal ibrahim", "mostafa hazem", "karim salah", "khaled hesham");
    }

    /**
     * private void set(){
     * for (RegisterInfo registerInfo:registerInfos) {
     * HBox container = new HBox();
     * container.setStyle(Styles.WHITE_BACKGROUND);
     * Label name = new Label(registerInfo.getUserInfo().getFullName());
     *
     */

    private void set(String... names){
        for (String Name:names) {
            HBox container = new HBox();
            container.setStyle(Styles.WHITE_BACKGROUND);
            Label name = new Label(Name);
            name.setFont(Font.font(20));

            Button approveBtn = new Button("Approve");
            approveBtn.setStyle(Styles.BLACK_BUTTON);
            approveBtn.setOnMouseEntered(event -> approveBtn.setStyle(Styles.BLACK_BUTTON_HOVER));
            approveBtn.setOnMouseExited(event -> approveBtn.setStyle(Styles.BLACK_BUTTON));

            Button rejectBtn = new Button("Reject");
            rejectBtn.setStyle(Styles.BLACK_BUTTON);
            rejectBtn.setOnMouseEntered(event -> rejectBtn.setStyle(Styles.BLACK_BUTTON_HOVER));
            rejectBtn.setOnMouseExited(event -> rejectBtn.setStyle(Styles.BLACK_BUTTON));

            HBox btns = new HBox(20);
            btns.setAlignment(Pos.CENTER_RIGHT);
            btns.getChildren().addAll(approveBtn, rejectBtn);

            container.setHgrow(btns, Priority.ALWAYS);
            container.setPadding(new Insets(5,50,5,50));
            container.getChildren().addAll(name, btns);

            getChildren().add(container);
        }
    }
    /**
     *
     * private void set(){
     * for (RegisterInfo registerInfo:registerInfos) {
     * VBox container = new VBox();
     * container.setStyle(Styles.WHITE_BACKGROUND);
     * Label name = new Label(registerInfo.getUserInfo().getFullName());
     *
     */

    /*
    private void set(String... names){
        for (String Name:names) {
            VBox container = new VBox(20);
            container.setAlignment(Pos.CENTER);
            container.setStyle(Styles.WHITE_BACKGROUND);
            Label name = new Label(name);
            name.setFont(Font.font(30));

            Button approveBtn = new Button("Approve");
            approveBtn.setStyle(Styles.BLACK_BUTTON);
            approveBtn.setOnMouseEntered(event -> approveBtn.setStyle(Styles.BLACK_BUTTON_HOVER));
            approveBtn.setOnMouseExited(event -> approveBtn.setStyle(Styles.BLACK_BUTTON));

            Button rejectBtn = new Button("Reject");
            rejectBtn.setStyle(Styles.BLACK_BUTTON);
            rejectBtn.setOnMouseEntered(event -> rejectBtn.setStyle(Styles.BLACK_BUTTON_HOVER));
            rejectBtn.setOnMouseExited(event -> rejectBtn.setStyle(Styles.BLACK_BUTTON));

            HBox btns = new HBox(20);
            btns.setAlignment(Pos.CENTER);
            btns.getChildren().addAll(approveBtn, rejectBtn);
            
            container.setVgrow(btns, Priority.ALWAYS);
            container.setPadding(new Insets(5,50,5,50));
            container.getChildren().addAll(name, btns);

            getChildren().add(container);
        }
    }*/
}
