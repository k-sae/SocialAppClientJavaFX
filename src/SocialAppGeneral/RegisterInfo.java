package SocialAppGeneral;

/**
 * Created by kemo on 30/10/2016.
 */
public class RegisterInfo implements Shareable {
    private LoginInfo loginInfo;
   private SocialAppGeneral.UserInfo UserInfo;

    public LoginInfo getLoginInfo() {
        return loginInfo;
    }

    public void setLoginInfo(LoginInfo loginInfo) {
        this.loginInfo = loginInfo;
    }

    public SocialAppGeneral.UserInfo getUserInfo() {
        return UserInfo;
    }

    public void setUserInfo(SocialAppGeneral.UserInfo userInfo) {
        UserInfo = userInfo;
    }


    public void fromJsonString(String s) {

    }


    public String convertToJsonString() {
        FormedLine regFormedLined = new FormedLine();
        regFormedLined.AddPartition("userInfo", UserInfo.convertToJsonString());
        regFormedLined.AddPartition("loginInfo", loginInfo.convertToJsonString());
        return regFormedLined.getLine();

    }
}
