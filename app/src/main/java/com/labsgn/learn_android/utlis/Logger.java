package com.labsgn.learn_android.utlis;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by rhony on 01/03/16.
 */
public class Logger {

    public static void log_i(String message) {
        Log.i("TAG", " "+message);
    }

    public static void log_e(String message) {
        Log.e("TAG", " "+message);
    }

    public static void toast(Context context, String message) {
        Toast.makeText(context, " "+message , Toast.LENGTH_SHORT).show();
    }


}
