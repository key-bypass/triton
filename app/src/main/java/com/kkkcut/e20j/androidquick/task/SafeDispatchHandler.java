package com.kkkcut.e20j.androidquick.task;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

/* loaded from: classes.dex */
public class SafeDispatchHandler extends Handler {
    private static final String TAG = "SafeDispatchHandler";

    public SafeDispatchHandler(Looper looper) {
        super(looper);
    }

    public SafeDispatchHandler(Looper looper, Handler.Callback callback) {
        super(looper, callback);
    }

    public SafeDispatchHandler() {
    }

    public SafeDispatchHandler(Handler.Callback callback) {
        super(callback);
    }

    @Override // android.os.Handler
    public void dispatchMessage(Message message) {
        try {
            super.dispatchMessage(message);
        } catch (Error e) {
            Log.d(TAG, "dispatchMessage error " + message + " , " + e);
        } catch (Exception e2) {
            Log.d(TAG, "dispatchMessage Exception " + message + " , " + e2);
        }
    }
}
