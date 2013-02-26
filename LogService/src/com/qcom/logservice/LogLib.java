package com.qcom.logservice;

import android.util.Log;

public class LogLib {
    public static void logJ(int priority, String tag, String msg) {
        Log.println(priority, tag, msg);
    }

    static {
    		System.loadLibrary("LogNative");
    }
    public static native void logN(int priority, String tag, String msg);
}
