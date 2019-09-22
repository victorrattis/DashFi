package com.vhra.dashfi.utils;

import android.util.Log;

public class LogUtils implements ILog {
    private static final String TAG = "DashFi";

    @Override
    public void d(String tag, String log) {
        Log.d(TAG, "[" + tag + "] " + log);
    }

    @Override
    public void e(String tag, String log) {
        Log.e(TAG, "[" + tag + "] " + log);
    }

    @Override
    public void e(String tag, String log, Throwable e) {
        Log.e(TAG, "[" + tag + "] " + log, e);
    }
}
