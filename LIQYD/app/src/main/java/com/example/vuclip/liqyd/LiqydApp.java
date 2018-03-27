package com.example.vuclip.liqyd;

import android.app.Application;
import android.util.Log;

/**
 * Created by Banty on 17/01/18.
 */

public class LiqydApp extends Application {
    private static final String TAG = "LiqydApp";
    private static LiqydApp instance;

    public static LiqydApp getAppInstance() {
        if (instance == null) {
            RuntimeException runtimeException = new RuntimeException("Application not initialized.");
            Log.e(TAG, "", runtimeException);
            throw runtimeException;
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

}
