package minato.lib.component;

/**
 * Created by Soar on 2015/9/11.
 */
public abstract class Device {

    public int type;//beacon or WiFi
    public int rssi;


    public Device(int type) {
        this.type = type;
    }

    public abstract String getUniqueID() ;

    @Override
    public String toString() {
        return "Rssi: "+rssi;
    }
}
