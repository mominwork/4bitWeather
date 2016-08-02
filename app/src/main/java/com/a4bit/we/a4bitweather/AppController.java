package com.a4bit.we.a4bitweather;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by home on 8/2/2016.
 */
public class AppController extends Application {

    private static AppController instance;
    private RequestQueue requestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
    }

    public static synchronized AppController getInstance(){
        return instance;
    }

    private RequestQueue getRequestQueue(){

        if (requestQueue==null){
            requestQueue= Volley.newRequestQueue(this);
        }
        return requestQueue;
    }

    public  void addToRequestQueue(Request request){

        getRequestQueue().add(request);
    }
}
