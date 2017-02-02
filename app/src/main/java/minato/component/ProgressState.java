package minato.component;

/**
 * Created by soar on 2017/2/1.
 */

public class ProgressState {

    public boolean isDisplay = true;
    public String title, message;

    public ProgressState(boolean isDisplay) {
        this.isDisplay = isDisplay;
    }

    public ProgressState(boolean isDisplay, String title, String message) {
        this.isDisplay = isDisplay;
        this.title = title;
        this.message = message;
    }

}
