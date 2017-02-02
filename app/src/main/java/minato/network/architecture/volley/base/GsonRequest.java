package minato.network.architecture.volley.base;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Map;

import minato.network.architecture.volley.IApiSuccessAction;

/**
 * Created by soar on 2016/12/3.
 */

public class GsonRequest<T>  extends Request {
    private final Gson mGson = new Gson();
    private final Type mType;
    private final IApiSuccessAction mApiCaller;
    private final Map<String,String> mPostData;


    public GsonRequest(String url, Map<String,String> postData, Type type, IApiSuccessAction apiCaller, Response.ErrorListener errorListener) {
        super(Method.POST, url, errorListener);
        this.mType = type;
        this.mApiCaller = apiCaller;
        this.mPostData = postData;
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
        if (mApiCaller != null) {
            mApiCaller.action(response);
        }
    }

//============================================================================================
    /*
       this function is not run in UI thread,so we can parse response here. This will not effect the UI performance.
     */

    @Override
    protected Response parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            T responseObject = mGson.fromJson(json,mType);

            return Response.success(responseObject,HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

}
