package SocialAppGeneral;

import java.io.Serializable;

/**
 * Created by kemo on 30/10/2016.
 */
public class RegisterInfo implements Shareable {
    private LoginInfo loginInfo;
   private UserInfo UserInfo;

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


    @Override
    public void setAttributesFromString(String s) {

    }

    @Override
    public String generateStringFromObject() {
        return null;
    }
}
