package minato.networkarchitecture;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by soar on 2016/10/25.
 */

public class CommonRequest extends Request {

    protected static final String PROTOCOL_CHARSET = "utf-8";
    private int DefaultTimeoutMS = 30000;
    private ApiResponse mApiResponse;
    private Map<String,String> mPostData;

    public CommonRequest (int method, String url, Map<String,String> postData, ApiResponse apiResponse) {
        super(method,url,apiResponse.errorListener);
        this.mApiResponse = apiResponse;
        this.mPostData = postData;
        this.setRetryPolicy(new DefaultRetryPolicy(DefaultTimeoutMS,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return mPostData;
    }

    //===========================================================================================
    /*
    call back function, run in UI Thread.
     */
    @Override
    protected void deliverResponse(Object response) {
        if (mApiResponse != null) {
            mApiResponse.onSuccess(response);
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
