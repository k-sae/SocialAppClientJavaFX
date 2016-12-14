package SocialAppGeneral;

import com.google.gson.Gson;

/**
 * Created by kemo on 14/12/2016.
 */
public class Message implements Shareable {
    public final static String FETCH_MESSAGES = "fetch_messages";
    @Override
    public String convertToJsonString() {
        return new Gson().toJson(this);
    }
}
