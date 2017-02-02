package minato.network.architecture.volley;

import com.android.volley.Request;

import minato.network.architecture.volley.base.CommonRequest;
import minato.network.architecture.volley.IApiSuccessAction;

/**
 * Created by soar on 2016/12/4.
 */

public class ApiProxy {

    public static final int sGET = Request.Method.GET;
    public static final int sPost = Request.Method.POST;
    public static final int API_GET =1;
    public static void testWeatherApi(String s,IApiSuccessAction action) {
        //Taipei City code 1668341
        //url
        CommonRequest request = new CommonRequest(sGET, "", null, new IApiSuccessAction() {

            @Override
            public void action(Object o) {

            }
        });
    }




}
