package minato.lib.scanner;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;

import minato.lib.component.Beacon;

/**
 * Created by Soar on 2015/9/10.
 */
public class BeaconScanner extends BaseScanner{

    private static BeaconScanner instance;
    private String TAG = "Beacon Scanner";
    private boolean isStartScan = false;
    private BluetoothAdapter bluetoothAdapter;

    private int scanPeriod = 5000;
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

    private BluetoothAdapter.LeScanCallback leScanCallback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
            Beacon beacon = BeaconParser.scanData(device,rssi,scanRecord);
            if(beacon!=null) {
                String key = beacon.macAddress;
                if(devices.containsKey(key)) {
                    Beacon bef_beacon = (Beacon)devices.get(key);
                    beacon.rssi = (beacon.rssi+bef_beacon.rssi)/2;
                }
                devices.put(key,beacon);
            }
        }
    };

    public static BeaconScanner getInstance(Context context) {

        if(instance==null) {
            instance = new BeaconScanner(context.getApplicationContext());
        }
        return instance;
    }

    private BeaconScanner(Context context) {

        BluetoothManager bluetoothManager = (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
        this.bluetoothAdapter = bluetoothManager.getAdapter();
    }

    @Override
    public void startscan() {
        if(scandoneCallback!=null) {
            devices.clear();
            this.bluetoothAdapter.stopLeScan(this.leScanCallback);
            this.bluetoothAdapter.startLeScan(this.leScanCallback);
            this.timer.start();
        }
        else {
            Log.d(TAG,"ScanDoneCallBack is Null");
        }

    }

    @Override
    public void stopscan() {
        this.timer.cancel();
        isStartScan = false;
    }
}
