package minato.lib.component;

/**
 * Created by Soar on 2015/9/11.
 */
public class Beacon extends Device{

    public String uuid ,macAddress;
    public int major,minor, txPower,promixity;

    public Beacon () {
        super(DeviceConfig.BEACON);
    }

    @Override
    public String getUniqueID() {
        return major+":"+minor+" "+uuid;
    }

    public Beacon(String uuid,int major,int minor,int txPower,int promixity) {
        this();
        this.uuid = uuid;
        this.major = major;
        this.minor = minor;
        this.txPower = txPower;
        this.promixity = promixity;
    }

    @Override
    public String toString() {
        return "UUID: "+uuid+"\n"+
               "Major: "+major+"\n"+
               "Minor: "+minor+"\n"+
               "TxPower: "+ txPower +"\n"+
               "Promixity: "+promixity+"\n"+
                super.toString();
    }
}
