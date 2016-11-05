package minato.networkarchitecture;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.Objects;

/**
 * Created by soar on 2016/10/25.
 */

public abstract class ApiResponse<T> {


    public Response.ErrorListener errorListener =  new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            onNetworkError(error);
        }
    };

    public abstract void onSuccess(T t);
    public abstract void onNetworkError(VolleyError error);

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
