package com.labsgn.learn_android;

import android.content.Context;

/**
 * Created by rhony on 02/03/16.
 * Todo 1. Setup instance application agar bisa di akses oleh VolleySingleton
 */
public class Application extends android.app.Application {
    private static Application Instance;

    @Override
    public void onCreate() {
        super.onCreate();
        Instance = this;
    }

    public static Application getAppInstance(){
        return Instance;
    }

    public static Context getAppContext(){
        return Instance.getApplicationContext();
    }
}
