package SocialAppClient.Control;

import java.util.regex.Pattern;

/**
 * Created by mosta on 09-Dec-16.
 */
public class validator {
    public static Boolean valdiateName(String name){
        return Pattern.matches("[a-zA-Z]{1,10}",name);
    }
    public static Boolean valdiatePass(String pass){
        return Pattern.matches("[a-zA-Z0-9]{8,18}",pass);
    }

    public static Boolean valdiateEmail(String email){
        if(Pattern.matches("^[a-zA-Z0-9._]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$",email))
        {
            return !email.contains("..");
        }
        return false;
    }
    public static boolean datecheck(String date){
        return(Integer.parseInt(date.substring(0,4))<2016&&Integer.parseInt(date.substring(0,4))>1916);
    }
}
