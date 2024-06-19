package com.kkkcut.e20j.androidquick.ui.base;

import android.app.Application;
import com.kkkcut.e20j.androidquick.autolayout.config.AutoLayoutConifg;

/* loaded from: classes.dex */
public class QuickApplication extends Application {
    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        AutoLayoutConifg.getInstance().setDesignSize(1080, 1920).useDeviceSize();
    }
}
