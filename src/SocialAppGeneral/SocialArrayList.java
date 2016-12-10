package SocialAppGeneral;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kemo on 04/12/2016.
 */
public class SocialArrayList {
    private Object owner;
    private  Object target;
    private List<Object> items;
    public SocialArrayList(List<Object> items)
    {
        this.items = items;
    }
    public SocialArrayList()
    {
        items = new ArrayList<>();
    }

    public Object getOwner() {
        return owner;
    }

    public void setOwner(Object owner) {
        this.owner = owner;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public String convertToJsonString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
    public static SocialArrayList convertFromJsonString(String jsonStr)
    {
        Gson gson = new Gson();
        return gson.fromJson(jsonStr, SocialArrayList.class);
    }

    public List<Object> getItems() {
        return items;
    }

    public void setItems(List<Object> items) {
        this.items = items;
    }

}
