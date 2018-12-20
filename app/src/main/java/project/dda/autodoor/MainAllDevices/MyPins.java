package project.dda.autodoor.MainAllDevices;

import project.dda.autodoor.Model.Pin;

/**
 * Created by Duy Anh on 12/19/2018
 **/
public class MyPins {

    public MyPins(String name, Pin pin) {
        Name = name;
        Pin = pin;
    }

    public String Name;
    public Pin Pin;

    public void setName(String name) {
        Name = name;
    }

    public void setPin(project.dda.autodoor.Model.Pin pin) {
        Pin = pin;
    }

    public String getName() {

        return Name;
    }

    public project.dda.autodoor.Model.Pin getPin() {
        return Pin;
    }
}
