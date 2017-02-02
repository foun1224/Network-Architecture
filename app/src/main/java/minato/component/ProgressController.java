package minato.component;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by soar on 2017/2/1.
 */

public class ProgressController {


    public void displayProgress(String title, String message) {
        ProgressState progressState = new ProgressState(true,title,message);
        EventBus.getDefault().post(progressState);
    }

    public void dimiss() {
        ProgressState progressState = new ProgressState(false);
        EventBus.getDefault().post(progressState);
    }
}
