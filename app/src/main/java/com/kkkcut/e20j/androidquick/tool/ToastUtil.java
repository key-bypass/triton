package com.kkkcut.e20j.androidquick.tool;

import android.content.Context;
import android.widget.Toast;

/* loaded from: classes.dex */
public class ToastUtil {
    public static Context mContext;
    private static Toast toast;

    public static void register(Context context) {
        mContext = context;
    }

    public static void showToast(int i) {
        Toast toast2 = toast;
        if (toast2 == null) {
            toast = Toast.makeText(mContext, i, 0);
        } else {
            toast2.setText(i);
        }
        toast.show();
    }

    public static void showToast(String str) {
        Toast toast2 = toast;
        if (toast2 == null) {
            toast = Toast.makeText(mContext, str, 0);
        } else {
            toast2.setText(str);
        }
        toast.show();
    }
}
