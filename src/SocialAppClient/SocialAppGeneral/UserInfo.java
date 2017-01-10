package SocialAppClient.SocialAppGeneral;


import com.google.gson.Gson;

/**
 * Created by kemo on 24/10/2016.
 */
//created it for debugging
public class UserInfo implements Shareable {
    private Boolean adminship;
    private String profileImage;
    private String fullName;
    private String birthDate;
    private String gender;



    public static transient final  String PICK_INFO = "pick_info";
    public static transient final  String EDIT_INFO = "edit_info";
    public UserInfo() {
        this.fullName = "";
        this.birthDate = "";
        this.gender = "";
        profileImage = "";
    }
    public void setAdminShip(Boolean adminship){this.adminship=adminship;}

    public boolean getAdminShip(){return adminship;}
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String convertToJsonString() {
        //TODO #hazem
        Gson gson = new Gson();
        return gson.toJson(this);
    }
    public static UserInfo fromJsonString(String jsonStr) {
        //TODO #prototype GSON
        //Read from JSON
        Gson gson = new Gson();
        return  gson.fromJson(jsonStr,UserInfo.class);
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}