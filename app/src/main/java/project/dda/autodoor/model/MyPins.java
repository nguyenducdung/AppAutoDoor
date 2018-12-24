package project.dda.autodoor.model;

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

    public void setPin(project.dda.autodoor.model.Pin pin) {
        Pin = pin;
    }

    public String getName() {

        return Name;
    }

    public project.dda.autodoor.model.Pin getPin() {
        return Pin;
    }
}
