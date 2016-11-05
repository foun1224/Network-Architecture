package minato.networkarchitecture;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by soar on 2016/10/25.
 */

public class NetworkManager {

    private RequestQueue requestQueue ;

    private ApiResponse apiResponse = new ApiResponse() {
        @Override
        public void onSuccess(Object o) {

        }

        @Override
        public void onNetworkError(VolleyError error) {

        }
    };

    public NetworkManager(Context context) {
        requestQueue = Volley.newRequestQueue(context);
    }

    public NetworkManager(Context context, HttpStack httpStack) {
        requestQueue = Volley.newRequestQueue(context,httpStack);
    }

    public void sendRequest() {

    }



}
