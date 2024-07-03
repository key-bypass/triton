package com.pgyersdk.utils;

import android.app.Activity;
import android.os.Build;

/* compiled from: OrientatingUtils.java */
/* renamed from: com.pgyersdk.f.g */
/* loaded from: classes2.dex */
public class OrientatingUtils {
    /* renamed from: a */
    public static void m221a(Activity activity) {
        if (activity != null) {
            int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
            if (rotation == 0) {
                if (Build.VERSION.SDK_INT < 8) {
                    activity.setRequestedOrientation(1);
                    return;
                }
                int rotation2 = activity.getWindowManager().getDefaultDisplay().getRotation();
                if (rotation2 != 1 && rotation2 != 2) {
                    activity.setRequestedOrientation(1);
                    return;
                } else {
                    activity.setRequestedOrientation(9);
                    return;
                }
            }
            if (rotation == 1) {
                if (Build.VERSION.SDK_INT < 8) {
                    activity.setRequestedOrientation(1);
                    return;
                }
                int rotation3 = activity.getWindowManager().getDefaultDisplay().getRotation();
                if (rotation3 != 1 && rotation3 != 2) {
                    activity.setRequestedOrientation(1);
                    return;
                } else {
                    activity.setRequestedOrientation(9);
                    return;
                }
            }
            if (rotation == 2) {
                if (Build.VERSION.SDK_INT < 8) {
                    activity.setRequestedOrientation(0);
                    return;
                }
                int rotation4 = activity.getWindowManager().getDefaultDisplay().getRotation();
                if (rotation4 != 0 && rotation4 != 1) {
                    activity.setRequestedOrientation(8);
                    return;
                } else {
                    activity.setRequestedOrientation(0);
                    return;
                }
            }
            if (rotation != 3) {
                return;
            }
            if (Build.VERSION.SDK_INT < 8) {
                activity.setRequestedOrientation(0);
                return;
            }
            int rotation5 = activity.getWindowManager().getDefaultDisplay().getRotation();
            if (rotation5 != 0 && rotation5 != 1) {
                activity.setRequestedOrientation(8);
            } else {
                activity.setRequestedOrientation(0);
            }
        }
    }
}
