package minato.lib.scanner;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import minato.lib.component.Device;


/**
 * Created by Soar on 2015/9/14.
 */
public abstract class BaseScanner {

    private String TAG = "BaseScanner";
    public HashMap<String,Device> devices = new HashMap<>(100);
    public ScanDoneCallback scandoneCallback;
    private Comparator<Device> comparator = new Comparator<Device>() {
        @Override
        public int compare(Device lhs, Device rhs) {
            if(lhs.rssi<rhs.rssi) {
                return 1;
            }
            if(lhs.rssi>rhs.rssi) {
                return -1;
            }


            return 0;
        }
    };

    public void setScanDoneCallback(ScanDoneCallback scanDoneCallback){
        this.scandoneCallback = scanDoneCallback;
    }

    public void scanDoneAndCallBack() {
        if(scandoneCallback!=null) {
            ArrayList<Device> devicelist = new ArrayList<>(devices.values());
            Collections.sort(devicelist,comparator);
            scandoneCallback.onScanDone(devicelist);
        }
        else {
            Log.d(TAG, "ScanDoneCallBack is Null");
        }
    }


    abstract public void startscan();
    abstract public void stopscan();

}
