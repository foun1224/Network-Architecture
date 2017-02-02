package minato.component;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.WeakReference;

/**
 * Created by soar on 2017/2/1.
 */

public class ProgressControlReceiver {

    private WeakReference<Context> mWeakReference;
    private ProgressDialog mProgressDialog;

    public ProgressControlReceiver(Context context) {
        if(context instanceof Activity) {
            EventBus.getDefault().register(this);
            mWeakReference = new WeakReference<>(context);
        }
    }

    public void onResume() {
        if(mWeakReference.get() != null) {
            if(!EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().register(this);
            }
        }
    }

    public void onPause() {
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ProgressState state) {

        if(mWeakReference != null) {
            if(state.isDisplay) {
                Context context = mWeakReference.get();
                if (context != null) {
                    mProgressDialog = ProgressDialog.show(context, state.title, state.message);
                }
            }
            else if(mProgressDialog != null){
                mProgressDialog.cancel();
                mProgressDialog = null;
            }
        }


    }


}
