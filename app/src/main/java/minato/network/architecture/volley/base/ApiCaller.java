package minato.network.architecture.volley.base;

import com.android.volley.Response;

/**
 * Created by soar on 2016/12/10.
 */

public abstract class ApiCaller<T> {

    public Response.Listener successListener = new Response.Listener() {
        @Override
        public void onResponse(Object response) {

            onSuccess((T)response);
        }
    };

//    public Response.ErrorListener errorListener = new Response.ErrorListener() {
//        @Override
//        public void onErrorResponse(VolleyError error) {
//            onNetworkError(error);
//        }
//    };


    public abstract void onSuccess(Object t);
//    public abstract void onNetworkError(VolleyError error);
}
