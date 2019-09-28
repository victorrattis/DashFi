package com.vhra.dashfi.utils;

import android.os.Handler;
import android.os.Looper;

public class CallbackHandler<Data> implements Callback<Data> {
    private final Callback<Data> mCallback;
    private final Handler mCallerHandler;

    private CallbackHandler(Callback<Data> callback) {
        mCallback = callback;
        mCallerHandler = new Handler();
    }

    // TODO: rename this method name.
    public static <T> Callback<T> getCallbackHandler(Callback<T> callback) {
        return (Looper.myLooper() != null) ? new CallbackHandler<>(callback) : callback;
    }

    @Override
    public void onComplete(Data data) {
        if (mCallback == null) {
            return;
        }
        mCallerHandler.post(() -> mCallback.onComplete(data));
    }
}
