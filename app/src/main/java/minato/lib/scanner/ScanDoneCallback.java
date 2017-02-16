package minato.lib.scanner;

import java.util.ArrayList;

import minato.lib.component.Device;

/**
 * Created by Soar on 2015/9/11.
 */
public interface ScanDoneCallback {

     void onScanDone(ArrayList<Device> devices);
}
