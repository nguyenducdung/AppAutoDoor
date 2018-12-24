package project.dda.autodoor.model;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Duy Anh on 12/17/2018
 **/
public class Product {
    public String Model;
    public Integer Id;
    public String Name;
    public String Address;
    public Integer DongId;
    public Integer Offline;
    public String Status;
    public String Pins;

    public Product(JSONObject object) {

        try {
            this.Model = object.getString("Model");
            this.Id = object.getInt("Id");
            this.Name = object.getString("Name");
            this.Address = object.getString("Address");
            this.DongId = object.getInt("DongId");
            this.Offline = object.getInt("Offline");
            this.Status = object.getString("Status");
            this.Pins = object.getString("Pins");

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("Product"," E:: Error",e);
        }

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
    public void setModel(String model) {
        Model = model;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setDongId(Integer dongId) {
        DongId = dongId;
    }

    public void setOffline(Integer offline) {
        Offline = offline;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public void setPins(String pins) {
        Pins = pins;
    }

    public String getModel() {
        return Model;
    }

    public Integer getId() {
        return Id;
    }

    public String getAddress() {
        return Address;
    }

    public Integer getDongId() {
        return DongId;
    }

    public Integer getOffline() {
        return Offline;
    }

    public String getStatus() {
        return Status;
    }

    public String getPins() {
        return Pins;
    }


}
