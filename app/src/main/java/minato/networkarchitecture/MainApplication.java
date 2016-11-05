package minato.networkarchitecture;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by soar on 2016/10/15.
 */

public class MainApplication extends Application {

    private RequestQueue mRequestQueue;

    public void onCreate() {
        mRequestQueue = Volley.newRequestQueue(this);
    }


}
