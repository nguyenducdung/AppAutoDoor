package project.dda.autodoor.wifi;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;

/**
 * Created by Duy Anh on 12/12/2018
 **/
public class Wifi implements IConnectionWifi {
    static final Integer WIFI_STATE_DISABLED = 1;
    static final Integer WIFI_STATE_DISABLING = 0;
    static final Integer WIFI_STATE_ENABLED = 3;
    static final Integer WIFI_STATE_ENABLING = 2;
    static final Integer WIFI_STATE_UNKNOWN = 4;

    public static String SSID = "18fe34f85476";
    public static String PASS = "12345678";

//    static String SSID = "DEMO";
//    static String PASS = "12345678";

    WifiManager wifiManager;
    WifiConfiguration configuration;

    public Wifi(Context context) {
        wifiManager = (WifiManager) context.getSystemService(context.WIFI_SERVICE);
        Configuration();
    }

    @Override
    public Integer States() {
        if (wifiManager != null) {
            return wifiManager.getWifiState();
        }
        return null;
    }

    @Override
    public void Configuration() {
        configuration = new WifiConfiguration();
        configuration.SSID = "\"" + SSID + "\"";
        configuration.preSharedKey = "\"" + PASS + "\"";
        configuration.status = WIFI_STATE_ENABLED;
        configuration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
        configuration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
        configuration.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
        configuration.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
        configuration.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
        configuration.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
    }

    @Override
    public void Connect() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (States() == WIFI_STATE_DISABLED) {
                    wifiManager.setWifiEnabled(true);
                }
                Integer network = wifiManager.addNetwork(configuration);
                wifiManager.disconnect();
                wifiManager.enableNetwork(network, true);
                wifiManager.reconnect();
            }
        }).start();

    }

    @Override
    public void Disconnect() {
        if (States() == WIFI_STATE_ENABLED) wifiManager.disconnect();
        wifiManager.setWifiEnabled(false);
    }
}
