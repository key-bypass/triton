package com.kkkcut.e20j.cockroach.compat;

import android.content.Intent;
import android.os.IBinder;
import android.os.Message;
import java.lang.reflect.Field;

/* loaded from: classes.dex */
public class ActivityKillerV21_V23 implements IActivityKiller {
    @Override // com.kkkcut.e20j.cockroach.compat.IActivityKiller
    public void finishLaunchActivity(Message message) {
        try {
            Object obj = message.obj;
            Field declaredField = obj.getClass().getDeclaredField("token");
            declaredField.setAccessible(true);
            finish((IBinder) declaredField.get(obj));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.kkkcut.e20j.cockroach.compat.IActivityKiller
    public void finishResumeActivity(Message message) {
        try {
            finish((IBinder) message.obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.kkkcut.e20j.cockroach.compat.IActivityKiller
    public void finishPauseActivity(Message message) {
        try {
            finish((IBinder) message.obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.kkkcut.e20j.cockroach.compat.IActivityKiller
    public void finishStopActivity(Message message) {
        try {
            finish((IBinder) message.obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void finish(IBinder iBinder) throws Exception {
        Object invoke = Class.forName("android.app.ActivityManagerNative").getDeclaredMethod("getDefault", new Class[0]).invoke(null, new Object[0]);
        invoke.getClass().getDeclaredMethod("finishActivity", IBinder.class, Integer.TYPE, Intent.class, Boolean.TYPE).invoke(invoke, iBinder, 0, null, false);
    }
}
