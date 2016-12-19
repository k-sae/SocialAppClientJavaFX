package SocialAppClient;

import SocialAppGeneral.*;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

/**
 * Created by billy on 2016-11-26.
 */
public class GroupInfoViewer extends InfoViewer{
    protected Button RelationBTN;
    protected ListView List;
    protected Button approveBtn;
    protected Button Edit;
    private long id;
    private Group group;
    public GroupInfoViewer(Group group){
        this.group = group;
        RelationBTN = new Button();
        List=new ListView();
        approveBtn =new Button("approve");
    }
//    public void search(){
//        ComboBox Search = new ComboBox();
//        Search.setPromptText("Search...");
//        Search.setEditable(true);
//
//        ImageView searchImg = new ImageView(new Image("file:Resources/search.png"));
//        searchImg.setFitWidth(17);
//        searchImg.setPreserveRatio(true);
//        Button searchBtn = new Button("", searchImg);
//        searchBtn.setOnAction(e-> {
//                });
            /** Add an item when you clicked on the menu */
//            Command command = new Command();
//            command.setKeyWord("Search_In_Friends");
//            command.setSharableObject(Search.getEditor().getText());
//            CommandRequest commandRequest = new CommandRequest(MainServerConnection.mainConnectionSocket, command) {
//                @Override
//                void analyze(Command cmd) {
//                    SocialAppGeneral.SocialArrayList socialArrayList = SocialAppGeneral.SocialArrayList.convertFromJsonString(cmd.getObjectStr());
//                    Search.getItems().clear();
//                    for (Object o: socialArrayList.getItems()) {
//                        Search.getItems().addAll(new FriendView((String)o));
//                        Search.setOnAction(e->{
//                            /** EL EVENT LAMA TDOS 3ALA EL RESULT */
//                            new GroupMember.AddMemberGroup(Long.toString(group.getId())) {
//                                @Override
//                                void onFinish(String s) {
//                                    System.out.print(s);
//                                }
//                            };
//                            Platform.runLater(() -> MainWindow.navigateTo(new ProfilePage((String)o)));
//                            Search.setPromptText("Search...");
//                        });
//                        // SearchMenu.getItems().addAll(new MenuItem((String)o));
//                        //   addFriendRequest((String)o);
//                    }
//                }
//            };
//            CommandsExecutor.getInstance().add(commandRequest);
//
//        });

        //getChildren().addAll(Search , searchBtn);
    //}

    @Override
    public void setButtons() {
        new GroupMember.GetStatus(Long.toString(group.getId())) {
             @Override
             void onFinish(String s) {
                 if(s.equals(RelationGroup.MEMBER.toString())){
                     new GroupMember.FetchData(Long.toString(group.getId())) {
                         @Override
                         void onFinish(String s) {
                             SocialArrayList socialArrayList = SocialArrayList.convertFromJsonString(s);
                             for (Object o: socialArrayList.getItems()) {
                              Platform.runLater(()->{
                                  HBox h = new HBox(5,new FriendView((String) o,15), approveBtn);
                                  h.setAlignment(Pos.CENTER_LEFT);
                                  List.getItems().add(h);
                                  approveBtn.setStyle(Styles.BLACK_BUTTON);
                                  approveBtn.setOnMouseEntered(event -> approveBtn.setStyle(Styles.BLACK_BUTTON_HOVER));
                                  approveBtn.setOnMouseExited(event -> approveBtn.setStyle(Styles.BLACK_BUTTON));
                                  approveBtn.setOnAction(e->{
                                      new GroupMember.AcceptRequest(Long.toString(group.getId())+":"+(String)o) {
                                          @Override
                                          void onFinish(String s) {
                                              System.out.print(s);
                                              Platform.runLater(() -> MainWindow.navigateTo(new GroupPage(group)));
                                          }
                                      };
                                  });
                                  getChildren().add(List);
                                  //List.show();
                              });
                             }
                         }
                     };
                     Platform.runLater(() ->{
                         RelationBTN.setText("LEAVE");
                         RelationBTN.setStyle(Styles.NAV_BUTTON);
                         RelationBTN.setOnMouseEntered(event -> RelationBTN.setStyle(Styles.NAV_BUTTON_HOVER));
                         RelationBTN.setOnMouseExited(event -> RelationBTN.setStyle(Styles.NAV_BUTTON));
                         RelationBTN.setOnAction(e->{
                             new GroupMember.LeaveGroup(Long.toString(group.getId())) {
                                 @Override
                                 void onFinish(String s) {
                                     Platform.runLater(() -> MainWindow.navigateTo(new GroupPage(group)));

                                 }
                             };
                         });
                         getChildren().add(RelationBTN);
                     } );
                 }else if(s.equals(RelationGroup.NOT_MEMBER.toString())){
                     Platform.runLater(() ->{
                         RelationBTN.setText("Request membership");
                         RelationBTN.setStyle(Styles.NAV_BUTTON);
                         RelationBTN.setOnMouseEntered(event -> RelationBTN.setStyle(Styles.NAV_BUTTON_HOVER));
                         RelationBTN.setOnMouseExited(event -> RelationBTN.setStyle(Styles.NAV_BUTTON));
                         RelationBTN.setOnAction(e->{
                             new GroupMember.AddMemberGroup(Long.toString(group.getId())) {
                                 @Override
                                 void onFinish(String s) {
                                     System.out.print(s);
                                     Platform.runLater(() -> MainWindow.navigateTo(new GroupPage(group)));
                                 }
                             };
                         });
                         getChildren().add(RelationBTN);
                     } );
                 }
                 else if(s.equals(RelationGroup.PENDING_MEMBER.toString())){
                     Platform.runLater(() ->{
                         RelationBTN.setText("Cancel Request");
                         RelationBTN.setStyle(Styles.NAV_BUTTON);
                         RelationBTN.setOnMouseEntered(event -> RelationBTN.setStyle(Styles.NAV_BUTTON_HOVER));
                         RelationBTN.setOnMouseExited(event -> RelationBTN.setStyle(Styles.NAV_BUTTON));
                         RelationBTN.setOnAction(e->{
                             new GroupMember.cancelRequest(Long.toString(group.getId())) {
                                 @Override
                                 void onFinish(String s) {

                                     System.out.print(s);
                                     Platform.runLater(() -> MainWindow.navigateTo(new GroupPage(group)));
                                 }
                             };
                         });
                         getChildren().add(RelationBTN);
                     } );
                 }
             }
         };
        // for(long i : group.getMember()) {
         //   if (MainWindow.id.equals(""+i)){
           //     RelationBTN.setText("LEAVE");
             //   RelationBTN.setStyle(Styles.NAV_BUTTON);
               // RelationBTN.setOnMouseEntered(event -> RelationBTN.setStyle(Styles.NAV_BUTTON_HOVER));
                //RelationBTN.setOnMouseExited(event -> RelationBTN.setStyle(Styles.NAV_BUTTON));
                //getChildren().add(RelationBTN);

            //}else{
            //    RelationBTN.setText("JOIN");
              //  RelationBTN.setStyle(Styles.NAV_BUTTON);
               // RelationBTN.setOnMouseEntered(event -> RelationBTN.setStyle(Styles.NAV_BUTTON_HOVER));
                //RelationBTN.setOnMouseExited(event -> RelationBTN.setStyle(Styles.NAV_BUTTON));
                //getChildren().add(RelationBTN);
            //}
    //    }
        /*
        Edit = new Button("Edit");
        Edit.setStyle(Styles.NAV_BUTTON);
        Edit.setOnMouseEntered(event -> Edit.setStyle(Styles.NAV_BUTTON_HOVER));
        Edit.setOnMouseExited(event -> Edit.setStyle(Styles.NAV_BUTTON));
*/
    }
}