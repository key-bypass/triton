package com.kkkcut.e20j.androidquick.ui.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import com.kkkcut.e20j.androidquick.tool.LogUtil;
import com.kkkcut.e20j.androidquick.tool.NetUtil;
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter;
import org.greenrobot.eventbus.EventBus;

/* loaded from: classes.dex */
public class NetStateReceiver extends BroadcastReceiver {
    private static final String ANDROID_NET_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
    private static final String CUSTOM_ANDROID_NET_CHANGE_ACTION = "la.xiong.androidquick.net.conn.CONNECTIVITY_CHANGE";
    private static final String TAG = "NetStateReceiver";
    private static boolean isNetAvailable = false;
    private static BroadcastReceiver mBroadcastReceiver;
    private static NetUtil.NetType mNetType;

    private static BroadcastReceiver getReceiver() {
        if (mBroadcastReceiver == null) {
            synchronized (NetStateReceiver.class) {
                if (mBroadcastReceiver == null) {
                    mBroadcastReceiver = new NetStateReceiver();
                }
            }
        }
        return mBroadcastReceiver;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        mBroadcastReceiver = this;
        Log.i(TAG, "onReceive: " + intent.getAction());
        if (intent.getAction().equalsIgnoreCase(ANDROID_NET_CHANGE_ACTION) || intent.getAction().equalsIgnoreCase(CUSTOM_ANDROID_NET_CHANGE_ACTION)) {
            if (!NetUtil.isNetworkAvailable(context)) {
                LogUtil.i(TAG, "<--- network disconnected --->");
                isNetAvailable = false;
                EventBus.getDefault().post(new EventCenter(38));
            } else {
                LogUtil.i(TAG, "<--- network connected --->");
                isNetAvailable = true;
                mNetType = NetUtil.getAPNType(context);
                EventBus.getDefault().post(new EventCenter(37));
            }
        }
    }

    public static void registerNetworkStateReceiver(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(CUSTOM_ANDROID_NET_CHANGE_ACTION);
        intentFilter.addAction(ANDROID_NET_CHANGE_ACTION);
        context.getApplicationContext().registerReceiver(getReceiver(), intentFilter);
    }

    public static void checkNetworkState(Context context) {
        Intent intent = new Intent();
        intent.setAction(CUSTOM_ANDROID_NET_CHANGE_ACTION);
        context.sendBroadcast(intent);
    }

    public static void unRegisterNetworkStateReceiver(Context context) {
        if (mBroadcastReceiver != null) {
            try {
                context.getApplicationContext().unregisterReceiver(mBroadcastReceiver);
            } catch (Exception e) {
                LogUtil.e(TAG, e.getMessage());
            }
        }
    }

    public static boolean isNetworkAvailable() {
        return isNetAvailable;
    }

    public static NetUtil.NetType getAPNType() {
        return mNetType;
    }
}
