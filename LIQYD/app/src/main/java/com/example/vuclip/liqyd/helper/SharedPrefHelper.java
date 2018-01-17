package com.example.vuclip.liqyd.helper;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;

import com.example.vuclip.liqyd.LiqydApp;

/**
 * Created by Banty on 17/01/18.
 */

public class SharedPrefHelper {
    private static final String TAG = "SharedPrefHelper";

    public static void putPref(String key, String value) {
        SharedPreferences.Editor editor = getEditor();

        if (editor == null || TextUtils.isEmpty(key)) {
            return;
        }

        editor.putString(key, value);
        editor.commit();
        Log.d(TAG, "added in SharedPref [ " + key + " : " + value + " ]");

    }

    public static String getPref(String key, String defValue) {
        if (TextUtils.isEmpty(key))
            return defValue;

        SharedPreferences sp = getPreferences();
        if (sp == null)
            return defValue;

        String val;
        return sp.getString(key, defValue);
    }

    private static SharedPreferences.Editor getEditor() {
        SharedPreferences sp = getPreferences();
        if (sp != null)
            return sp.edit();

        return null;
    }

    public static SharedPreferences getPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(LiqydApp.getAppInstance().getApplicationContext());
    }

    public static boolean isTrue(String key, String defValue) {
        String input = getPref(key, defValue);
        boolean result = false;
        try {
            if (TextUtils.isEmpty(input)) {
                result = false;
            } else {
                input = input.toLowerCase();
                if (input.equals("t")
                        || input.equals("true")
                        || input.equals("y")
                        || input.equals("yes")
                        || input.equals("ok")
                        || input.equals("on")
                        || input.equals("1")) {
                    result = true;
                }
            }
        } catch (Exception e) {
            result = false;
        }
        Log.i(TAG, "input [" + input + "]   -- isTrue: " + result);

        return result;
    }

    public static final boolean isFalse(String key, String defValue) {
        return !isTrue(key, defValue);
    }
}
