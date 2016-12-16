package SocialAppGeneral;

import com.google.gson.Gson;

/**
 * Created by kemo on 14/12/2016.
 */
public class Message implements Shareable {
    public final static String FETCH_MESSAGES = "fetch_messages";
    public final static String SEND_MESSAGE = "send_message";
    public final static String RECEIVE_MESSAGE = "receive_message";
    public final static String NEW_NOTIFICATION = "new_notification";
    private String Sender;
    private String Message;
    private String Receiver;
    @Override
    public String convertToJsonString() {
        return new Gson().toJson(this);
    }
    public static SocialAppGeneral.Message FromJson(String Json)
    {
        return new Gson().fromJson(Json, SocialAppGeneral.Message.class);
    }

    public String getSender() {
        return Sender;
    }

    public void setSender(String sender) {
        Sender = sender;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getReceiver() {
        return Receiver;
    }

    public void setReceiver(String receiver) {
        Receiver = receiver;
    }
}
