package minato.lib.component;

/**
 * Created by Soar on 2015/9/11.
 */
public class WiFiDevice extends Device {

    public String ssid,bssid;

    public WiFiDevice() {
        super(DeviceConfig.WIFI);
    }

    @Override
    public String getUniqueID() {
        return ssid+" "+bssid;
    }

    public WiFiDevice(String ssid,String bssid,int rssi) {
        this();
        this.ssid = ssid;
        this.bssid = bssid;
        this.rssi = rssi;
    }

    @Override
    public String toString() {
        return "SSID: "+ssid+"\n"+
               "Bssid: "+bssid+"\n"+
                super.toString();
    }
}
