package minato.network.architecture.volley.base;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;

import org.greenrobot.eventbus.EventBus;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Map;

import minato.network.architecture.volley.IApiSuccessAction;

/**
 * Created by soar on 2016/10/25.
 */

public class CommonRequest extends Request {

    protected static final String PROTOCOL_CHARSET = "utf-8";
    private int DefaultTimeoutMS = 30000;
    private IApiSuccessAction mApiResponse;
    private Map<String,String> mPostData;

    public CommonRequest (int method, String url, Map<String,String> postData, IApiSuccessAction apiResponse) {
        this(method, url, postData, apiResponse, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                EventBus.getDefault().post(error);
            }
        });
    }

    public CommonRequest (int method, String url, Map<String,String> postData, IApiSuccessAction apiResponse, Response.ErrorListener errorListener) {
        super(method,url,errorListener);
        this.mApiResponse = apiResponse;
        this.mPostData = postData;
        this.setRetryPolicy(new DefaultRetryPolicy(DefaultTimeoutMS,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return mPostData;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return Collections.emptyMap();
    }

    //===========================================================================================
    /*
    call back function, run in UI Thread.
     */
    @Override
    protected void deliverResponse(Object response) {
        if (mApiResponse != null) {
            mApiResponse.action(response);
        }
    }

//============================================================================================
    /*
       Default  use String type to parse response
       this function is not run in UI thread,so we can parse response here. This will not effect the UI performance.
     */

    @Override
    protected Response parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers,PROTOCOL_CHARSET));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
    }

//============================================================================================


}
