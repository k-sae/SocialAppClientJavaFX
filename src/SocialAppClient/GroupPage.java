package SocialAppClient;

import SocialAppGeneral.Command;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * Created by kemo on 10/11/2016.
 */
public class GroupPage extends GridPane {
   public GroupPage()
    {
        setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        updateColor(this);

        setGridLinesVisible(true);
        setConstraint();
        setPanels();
    }
    synchronized static void updateColor(Pane pane)
    {
        //TODO #prototype
        //set ur new command
        Command command = new Command();
        command.setKeyWord("changeColor");
//        command.setSharableObject("null");
        CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket, command) {
            @Override
            void analyze(Command commandFromServer) {
                pane.setBackground(new Background(new BackgroundFill(Color.web(commandFromServer.getObjectStr()), CornerRadii.EMPTY, Insets.EMPTY)));
            }
        };
//        commandRequest.run();
       CommandsExecutor.getInstance().add(commandRequest);
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

        GroupInfoViewer Info = new GroupInfoViewer();

        /**PUT THE PICTURE PATH*/
        //Info.setPicture();
        /**PUT SOME INFO AS STRING*/
        //Info.setLabel();
        /**ADD JOIN AND EDIT BUTTON -- EDIT THEM IN GROUPINFOVIEWER*/
        Info.setButtons();

        add(Info,0,0);

        Content content = new Content();

        add(content,1,0);

        /**THE SCROLL BAR KEEPS TRACK THE CONTENT*/
        ScrollPane sp = new ScrollPane(content);
        sp.setFitToWidth(true);
        add(sp,1,0);

        Info.Edit.setOnAction(event -> {
            getChildren().remove(content);
            sp.setContent(null);
        });
    }
}
