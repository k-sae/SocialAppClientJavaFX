package SocialAppClient.View;

import SocialAppClient.Control.GroupMember;
import SocialAppGeneral.*;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

/**
 * Created by billy on 2016-11-26.
 */
class GroupInfoViewer extends InfoViewer{
    private Button RelationBTN;
    private ListView List;
    private Button approveBtn;
    private Group group;
    GroupInfoViewer(Group group){
        this.group = group;
        RelationBTN = new Button();
        List=new ListView();
        approveBtn =new Button("approve");
    }

    @Override
    public void setButtons() {
        new GroupMember.GetStatus(Long.toString(group.getId())) {
             @Override
             public void onFinish(String s) {
                 if(s.equals(RelationGroup.MEMBER.toString())){
                     new GroupMember.FetchData(Long.toString(group.getId())) {
                         @Override
                         public void onFinish(String s) {
                             SocialArrayList socialArrayList = SocialArrayList.convertFromJsonString(s);
                             for (Object o: socialArrayList.getItems()) {
                              Platform.runLater(()->{
                                  HBox h = new HBox(5,new FriendView((String) o,15), approveBtn);
                                  h.setAlignment(Pos.CENTER_LEFT);
                                  //noinspection unchecked
                                  List.getItems().add(h);
                                  approveBtn.setStyle(Styles.BLACK_BUTTON);
                                  approveBtn.setOnMouseEntered(event -> approveBtn.setStyle(Styles.BLACK_BUTTON_HOVER));
                                  approveBtn.setOnMouseExited(event -> approveBtn.setStyle(Styles.BLACK_BUTTON));
                                  approveBtn.setOnAction(e-> new GroupMember.AcceptRequest(Long.toString(group.getId())+":"+ o) {
                                      @Override
                                      public void onFinish(String s) {
                                          System.out.print(s);
                                          Platform.runLater(() -> MainWindow.navigateTo(new GroupPage(group)));
                                      }
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
                         RelationBTN.setOnAction(e-> new GroupMember.LeaveGroup(Long.toString(group.getId())) {
                             @Override
                             public void onFinish(String s) {
                                 Platform.runLater(() -> MainWindow.navigateTo(new GroupPage(group)));

                             }
                         });
                         getChildren().add(RelationBTN);
                     } );
                 }else if(s.equals(RelationGroup.NOT_MEMBER.toString())){
                     Platform.runLater(() ->{
                         RelationBTN.setText("Request membership");
                         RelationBTN.setStyle(Styles.NAV_BUTTON);
                         RelationBTN.setOnMouseEntered(event -> RelationBTN.setStyle(Styles.NAV_BUTTON_HOVER));
                         RelationBTN.setOnMouseExited(event -> RelationBTN.setStyle(Styles.NAV_BUTTON));
                         RelationBTN.setOnAction(e-> new GroupMember.AddMemberGroup(Long.toString(group.getId())) {
                             @Override
                             public void onFinish(String s) {
                                 System.out.print(s);
                                 Platform.runLater(() -> MainWindow.navigateTo(new GroupPage(group)));
                             }
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
                         RelationBTN.setOnAction(e-> new GroupMember.cancelRequest(Long.toString(group.getId())) {
                             @Override
                             public void onFinish(String s) {

                                 System.out.print(s);
                                 Platform.runLater(() -> MainWindow.navigateTo(new GroupPage(group)));
                             }
                         });
                         getChildren().add(RelationBTN);
                     } );
                 }
             }
         };
    }
}