package SocialAppClient;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * Created by kemo on 10/11/2016.
 */
public class HomePage extends GridPane {
    public HomePage()
    {
        //setBackground(new Background(new BackgroundFill(Color.web(ClientTheme.BackGround, 1), CornerRadii.EMPTY, Insets.EMPTY)));
        setBackground(new Background(new BackgroundFill(Color.web("#eeeeee"), CornerRadii.EMPTY, Insets.EMPTY)));

        setGridLinesVisible(true);
        setConstraint();
        setPanels();
    }

    private void setConstraint(){

        ColumnConstraints columnConstraints0 = new ColumnConstraints();
        columnConstraints0.setPercentWidth(25);
        ColumnConstraints columnConstraints1 = new ColumnConstraints();
        columnConstraints1.setPercentWidth(75);

        getColumnConstraints().addAll(columnConstraints0,columnConstraints1);

        RowConstraints rowConstraints0 = new RowConstraints();
        rowConstraints0.setPercentHeight(100);
        getRowConstraints().addAll(rowConstraints0);

    }

    private void setPanels(){

        HomePageInfoViewer Info = new HomePageInfoViewer();

        /**PUT THE PICTURE PATH*/
        //Info.setPicture();
        /**PUT SOME INFO AS STRING*/
        //Info.setLabel();
        Info.setButtons();

        add(Info,0,0);

        Content content = new Content();

        add(content,1,0);
        ScrollPane sp = new ScrollPane(content);
        sp.setFitToWidth(true);
        add(sp,1,0);

    }
}
