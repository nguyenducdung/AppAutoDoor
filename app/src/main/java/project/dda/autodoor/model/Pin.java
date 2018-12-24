package project.dda.autodoor.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Duy Anh on 12/17/2018
 **/
public class Pin {
    public String Command;
    public Boolean Disabled;
    public String Group;
    public String Name;
    public Integer Value;
    public String Description;
    public String UserControlFlag;
    public Integer Level;
    public Integer StatusIndex; // vi tri byte thu may
    public Integer StatusMask; // vi tri bit thu may
    public Boolean IsHigh;

    public Pin(JSONObject object) {
        try {
            this.Command = object.getString("Command");
            this.Disabled = object.getBoolean("Disabled");
            this.Group = object.getString("Group");
            this.Name = object.getString("Name");
            this.Value = object.getInt("Value");
            this.Description = object.getString("Description");
            this.UserControlFlag = object.getString("UserControlFlag");
            this.Level = object.getInt("Level");
            this.StatusIndex = object.getInt("StatusIndex");
            this.StatusMask = object.getInt("StatusMask");
            this.IsHigh = object.getBoolean("IsHigh");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void setHigh(Boolean high) {
        IsHigh = high;
    }

    public Boolean getHigh() {

        return IsHigh;
    }

    public void setCommand(String command) {
        Command = command;
    }

    public void setDisabled(Boolean disabled) {
        Disabled = disabled;
    }

    public void setGroup(String group) {
        Group = group;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setValue(Integer value) {
        Value = value;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setUserControlFlag(String userControlFlag) {
        UserControlFlag = userControlFlag;
    }

    public void setLevel(Integer level) {
        Level = level;
    }

    public void setStatusIndex(Integer statusIndex) {
        StatusIndex = statusIndex;
    }

    public void setStatusMask(Integer statusMask) {
        StatusMask = statusMask;
    }

    public String getCommand() {

        return Command;
    }

    public Boolean getDisabled() {
        return Disabled;
    }

    public String getGroup() {
        return Group;
    }

    public String getName() {
        return Name;
    }

    public Integer getValue() {
        return Value;
    }

    public String getDescription() {
        return Description;
    }

    public String getUserControlFlag() {
        return UserControlFlag;
    }

    public Integer getLevel() {
        return Level;
    }

    public Integer getStatusIndex() {
        return StatusIndex;
    }

    public Integer getStatusMask() {
        return StatusMask;
    }
}
