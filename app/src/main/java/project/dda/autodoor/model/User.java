package project.dda.autodoor.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Duy Anh on 12/15/2018
 **/

public class User {
    public Integer Id;
    public String UserName;
    public Integer Level;
    public String Token;

    public User() {
    }

    public User(JSONObject object) {
        try {
            this.Id = object.getInt("Id");
            this.UserName = object.getString("UserName");
            this.Level = object.getInt("Level");
            this.Token = object.getString("Token");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void setId(Integer id) {
        Id = id;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setLevel(Integer level) {
        Level = level;
    }

    public void setToken(String token) {
        Token = token;
    }

    public Integer getId() {

        return Id;
    }

    public String getUserName() {
        return UserName;
    }

    public Integer getLevel() {
        return Level;
    }

    public String getToken() {
        return Token;
    }
}
