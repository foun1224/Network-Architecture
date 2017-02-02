package minato.component;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by soar on 2017/2/1.
 */

public class ProgressController {


    public void display(String title, String message) {
        ProgressState displayProgress = new ProgressState(true,title,message);
        EventBus.getDefault().post(displayProgress);
    }

    public void dimiss() {
        ProgressState cancelProgress = new ProgressState(false);
        EventBus.getDefault().post(cancelProgress);
    }
}
