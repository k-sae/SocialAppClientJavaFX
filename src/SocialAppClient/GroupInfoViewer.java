package SocialAppClient;

import SocialAppGeneral.*;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by billy on 2016-11-26.
 */
public class GroupInfoViewer extends InfoViewer{
    protected Button RelationBTN;
    protected Button Edit;
    private long id;
    private Group group;
    public GroupInfoViewer(Group group){
        this.group = group;
        RelationBTN = new Button();
    }
    public void search(){
        ComboBox Search = new ComboBox();
        Search.setPromptText("Search...");
        Search.setEditable(true);

        ImageView searchImg = new ImageView(new Image("file:Resources/search.png"));
        searchImg.setFitWidth(17);
        searchImg.setPreserveRatio(true);
        Button searchBtn = new Button("", searchImg);
        searchBtn.setOnAction(e->{

            /** Add an item when you clicked on the menu */
            Command command = new Command();
            command.setKeyWord("Search");
            command.setSharableObject(Search.getEditor().getText());
            CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket, command) {
                @Override
                void analyze(Command cmd) {
                    SocialAppGeneral.SocialArrayList socialArrayList = SocialAppGeneral.SocialArrayList.convertFromJsonString(cmd.getObjectStr());
                    Search.getItems().clear();
                    for (Object o: socialArrayList.getItems()) {
                        Search.getItems().addAll(new FriendView((String)o));
                        Search.setOnAction(e->{
                            /** EL EVENT LAMA TDOS 3ALA EL RESULT */
                            Platform.runLater(() -> MainWindow.navigateTo(new ProfilePage((String)o)));
                            Search.setPromptText("Search...");
                        });
                        // SearchMenu.getItems().addAll(new MenuItem((String)o));
                        //   addFriendRequest((String)o);
                    }
                }
            };
            CommandsExecutor.getInstance().add(commandRequest);

        });

        getChildren().addAll(Search , searchBtn);
    }

    @Override
    public void setButtons() {
        for(long i : group.getMember()) {
            if (MainWindow.id.equals(""+i)){
                RelationBTN.setText("LEAVE");
                RelationBTN.setStyle(Styles.NAV_BUTTON);
                RelationBTN.setOnMouseEntered(event -> RelationBTN.setStyle(Styles.NAV_BUTTON_HOVER));
                RelationBTN.setOnMouseExited(event -> RelationBTN.setStyle(Styles.NAV_BUTTON));
                getChildren().add(RelationBTN);

            }else{
                RelationBTN.setText("JOIN");
                RelationBTN.setStyle(Styles.NAV_BUTTON);
                RelationBTN.setOnMouseEntered(event -> RelationBTN.setStyle(Styles.NAV_BUTTON_HOVER));
                RelationBTN.setOnMouseExited(event -> RelationBTN.setStyle(Styles.NAV_BUTTON));
                getChildren().add(RelationBTN);
            }
        }
        /*
        Edit = new Button("Edit");
        Edit.setStyle(Styles.NAV_BUTTON);
        Edit.setOnMouseEntered(event -> Edit.setStyle(Styles.NAV_BUTTON_HOVER));
        Edit.setOnMouseExited(event -> Edit.setStyle(Styles.NAV_BUTTON));
*/
    }
}