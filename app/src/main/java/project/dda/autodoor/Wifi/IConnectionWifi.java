package project.dda.autodoor.Wifi;

import android.net.wifi.WifiManager;

/**
 * Created by Duy Anh on 12/12/2018
 **/
public interface IConnectionWifi {
    Integer States();
    void Configuration();
    void Connect();
    void Disconnect();
}
