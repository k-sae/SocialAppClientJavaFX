package SocialAppClient;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import sun.plugin.dom.core.Text;
import javax.swing.*;
import javax.xml.validation.Validator;
import java.util.regex.Pattern;


/**
 * Created by mosta on 30-Oct-16.
 */
public class RegisterPage extends GridPane {
 public RegisterPage(){
     this.setBackground(new Background(new BackgroundImage(new Image("file:Resources/background.jpg", true),BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
    BackgroundSize.DEFAULT)));
    setRow(this);
    setCol(this);
     layoutEditor(this);
    // this.setMinHeight(600);
    // this.setMinWidth(800);
}
    private void setCol(GridPane gridPane)
    {
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(10);
        ColumnConstraints columnConstraints1 = new ColumnConstraints();
        columnConstraints1.setPercentWidth(70);
        ColumnConstraints col3=new ColumnConstraints();
        col3.setPercentWidth(20);
        gridPane.getColumnConstraints().addAll(columnConstraints,columnConstraints1);

    }
    private void setRow(GridPane gridPane)
    {
        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setPercentHeight(10);
        RowConstraints rowConstraints1 = new RowConstraints();
        rowConstraints.setPercentHeight(80);
        RowConstraints rowConstraints2 = new RowConstraints();
        rowConstraints.setPercentHeight(10);
        gridPane.getRowConstraints().addAll(rowConstraints,rowConstraints1,rowConstraints2);
    }

    public HBox navbarCreator(){
        HBox Hb=new HBox(10);
       // Hb.setMinWidth(300);
        Button B = createRoundButtons();
        //TextField tf=textFieldCreator(true);

        Label Lb=createTxt("BTATS SOCIAL NETWORK",20,true);
        B.setPrefWidth(Double.MAX_VALUE);
        HBox.setHgrow(B, Priority.ALWAYS);
        Hb.getChildren().addAll(B,Lb);


       // Lb.setAlignment(Pos.CENTER);
        //B.setAlignment(Pos.TOP_LEFT);
        //Lb.setAlignment(Pos.TOP_CENTER);
        return Hb;
    }
    public Button createRoundButtons(){
        Button b=new Button();
        b.setMinWidth(60);
        b.setMinHeight(60);
        b.setShape(new Circle(30));
        b.setMinSize(60, 60);
        b.setMaxSize(60, 60);
        b.setBackground(new Background(new BackgroundImage(new Image("file:Resources/btatsya.png",60,60,false,false),BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
       // b.setBackground(new Background(new BackgroundFill(Color.RED,CornerRadii.EMPTY, Insets.EMPTY)));

        return b;
    }
    public TextField textFieldCreator(boolean visbility){
        TextField Tf=new TextField();
        Tf.setMaxWidth(200);
        Tf.setMinHeight(30);
        Tf.setVisible(visbility);
        return Tf;

    }
    public VBox verticalBoxCreator(){

        VBox vb= new VBox();
        vb.setMinHeight(100);
        vb.setMinWidth(200);
        vb.setStyle("-fx-background-color:#ff0");
        return vb;
    }
    public void layoutEditor(GridPane gridPane){
        HBox pane = navbarCreator();
//        pane.setBackground(new Background(new BackgroundFill(Color.RED,CornerRadii.EMPTY, Insets.EMPTY)));
        VBox vb= textFieldArea();
        GridPane.setConstraints(vb,1,1);
        GridPane.setConstraints(pane,0,0);
        GridPane.setColumnSpan(pane,2);
        gridPane.getChildren().addAll(pane,vb);
    }
    public Label createTxt(String text, int fontSize,boolean Visbility){
        Label lb=new Label();
        lb.setText(text);
        lb.setTextFill(Color.WHITE);
        lb.setFont(new Font("Yu Gothic UI",fontSize));
        lb.setVisible(Visbility);
        return lb;
    }
    public VBox textFieldArea(){
        VBox vb=new VBox();
        Label l=createTxt("name",20,true);
        TextField Tf=textFieldCreator(true);
        Label lb = createTxt("name must contain just letters", 14, true);
        Label l1=createTxt("password",20,true);
        PasswordField Tf1 = new PasswordField();;
        Tf1.setMaxWidth(200);
        Label passverify=createTxt("password from 1 to 9 or charcter and contain 8 charcters",14,true);
        Label l3=createTxt("email",20,true);
Label emailverfiy=createTxt("email must be an email ass hole",14,true);
        TextField Tf2=textFieldCreator(true);
        ChoiceBox cbd=new ChoiceBox();
        cbd.getItems().addAll("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31");
        ComboBox cbm=new ComboBox<>();
        cbm.getItems().addAll("1","2","3","4","5","6","7","8","9","10","11","12");


        Button B=new Button("Register");
        B.setOnAction(e->{
            boolean name= Pattern.matches("[a-zA-Z]{1,10}",Tf.getText());
            boolean password=Pattern.matches("[a-zA-Z1-9]{8,18}",Tf1.getText());
            boolean email =Pattern.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$",Tf2.getText());
            if(name==false) {
                lb.setText("fuck off");
            }
            else {
                lb.setText("i am ok ");
            }
            if(password==true){
                passverify.setText("pass is ok");
            }else{
                passverify.setText("fuck off");
            }
            if(email==true){
                emailverfiy.setText("email is ok");
            }else{
                emailverfiy.setText("fuck off");
            }
        });


        vb.getChildren().addAll(l,Tf,lb,l1,Tf1,passverify,l3,Tf2,emailverfiy,cbd,cbm,B);
        return vb;
    }
}
