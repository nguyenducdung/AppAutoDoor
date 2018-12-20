package project.dda.autodoor.Model;

import org.json.JSONObject;

/**
 * Created by Duy Anh on 12/15/2018
 **/
public class BasicData {
    public String Code;
    public String Message;
    public String Data;
    public String Value;



    public void setValue(String value) {
        Value = value;
    }

    public String getValue() {

        return Value;
    }

    public void setCode(String code) {
        Code = code;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public void setData(String data) {
        Data = data;
    }

    public String getCode() {

        return Code;
    }

    public String getMessage() {
        return Message;
    }

    public String getData() {
        return Data;
    }
}
