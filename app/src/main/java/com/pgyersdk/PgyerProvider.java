package com.pgyersdk;

import android.app.Application;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import com.pgyersdk.p007a.Api;
import com.pgyersdk.p012c.Constants;
import com.pgyersdk.utils.LogUtils;
import com.pgyersdk.utils.Util;

/* loaded from: classes2.dex */
public class PgyerProvider extends ContentProvider {

    /* renamed from: a */
    public static Context f436a;

    /* renamed from: a */
    private static String m96a(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null && applicationInfo.metaData != null) {
                return applicationInfo.metaData.getString(Constants.f475m);
            }
            return null;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    private void m97b(Context context) {
        if (context instanceof Application) {
            PgyerActivityManager.m95a((Application) context);
            return;
        }
        throw new Error("PGYER SDK init activity manager throw a Error");
    }

    /* renamed from: c */
    private void m98c(Context context) {
        String m96a = m96a(context);
        if (Constants.f474l.equals("")) {
            Constants.f474l = Util.m240c(m96a);
        }
        if (Util.m240c(Constants.f474l) == null) {
            LogUtils.m220d("PgyerSDK", "Please config AppId on Manifest.xml or use Pgyer.setAppId().");
        }
        Constants.m145c(context);
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        f436a = getContext();
        LogUtils.m216a("PgyerSDK", " PgyerProvider onCreate");
        LogUtils.m216a("PgyerSDK", " context is " + f436a.toString());
        m98c(f436a);
        Api.m100a(f436a);
        m97b(f436a);
        return true;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }
}
