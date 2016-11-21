package SocialAppGeneral;

import com.google.gson.Gson;

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
            Gson gson = new Gson();
            RegisterInfo registerInfo = gson.fromJson(s,RegisterInfo.class);
        }



    public String convertToJsonString() {
        FormedLine regFormedLined = new FormedLine();
        regFormedLined.AddPartition("userInfo", UserInfo.convertToJsonString());
        regFormedLined.AddPartition("loginInfo", loginInfo.convertToJsonString());
        return regFormedLined.getLine();

    }
}
