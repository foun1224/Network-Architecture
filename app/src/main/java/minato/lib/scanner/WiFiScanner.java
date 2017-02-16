package minato.lib.scanner;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.CountDownTimer;
import android.util.Log;

import java.util.List;

import minato.lib.component.WiFiDevice;


/**
 * Created by Soar on 2015/9/23.
 */
public class WiFiScanner extends BaseScanner {

    private static WiFiScanner instance ;
    private String TAG = "WiFiScanner";
    private WifiManager manager;
    private Context context;
    private boolean isRegister = false;

    private int scanPeriod = 3000;
    private CountDownTimer timer = new CountDownTimer(scanPeriod,1000) {
        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            scanDoneAndCallBack();
            startscan();
        }
    };


    public static WiFiScanner getInstance(Context context) {

        if(instance==null) {
            instance = new WiFiScanner(context);
        }
        return  instance;
    }

    private WiFiScanner(Context context) {
        manager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        this.context = context;

    }

    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            List<ScanResult> results = manager.getScanResults();
            for(ScanResult result : results) {
                WiFiDevice device;
                if(devices.containsKey(result.BSSID)) {
                    device  = (WiFiDevice) devices.get(result.BSSID);
                    device.rssi = (device.rssi+result.level)/2;
                }
                else {
                    device = new WiFiDevice(result.SSID, result.BSSID, result.level);
                }

                devices.put(result.BSSID,device);
            }
        }
    };


    @Override
    public void startscan() {
        if(scandoneCallback!=null) {
            if (!isRegister) {
                isRegister = true;
                context.registerReceiver(broadcastReceiver, new IntentFilter(new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)));
            }
            devices.clear();
            manager.startScan();
            this.timer.start();
        }
        else {
            Log.d(TAG, "ScanDoneCallBack is Null");
        }
    }

    @Override
    public void stopscan() {
        context.unregisterReceiver(broadcastReceiver);
        isRegister = false;
        this.timer.cancel();
    }
}
