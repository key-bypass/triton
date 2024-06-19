package com.pgyersdk.p016f;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* compiled from: FileUtils.java */
/* renamed from: com.pgyersdk.f.c */
/* loaded from: classes2.dex */
public class C2038c {

    /* renamed from: a */
    private static C2038c f515a;

    /* renamed from: b */
    private String f516b = null;

    /* renamed from: c */
    private String f517c = "pgySdk";

    /* renamed from: d */
    private String f518d = "feedback";

    /* renamed from: e */
    private String f519e = "crash";

    /* renamed from: f */
    private String f520f = "downloadApk";

    /* renamed from: g */
    public final String f521g = ".jpg";

    /* renamed from: h */
    public final String f522h = ".log";

    /* renamed from: i */
    public final String f523i = ".txt";

    /* renamed from: j */
    public final String f524j = ".zip";

    /* renamed from: k */
    public final String f525k = "bug.properties";

    /* renamed from: a */
    public static C2038c m196a() {
        if (f515a == null) {
            f515a = new C2038c();
        }
        return f515a;
    }

    /* renamed from: b */
    public String m203b(Context context, String str) {
        return m205d(context) + File.separator + (new SimpleDateFormat("yyyyMMddHHmmss", Locale.US).format(new Date()) + str);
    }

    /* renamed from: c */
    public String m204c(Context context) {
        String str = m200a(context) + File.separator + this.f520f;
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return str;
    }

    /* renamed from: d */
    public String m205d(Context context) {
        String str = m200a(context) + File.separator + this.f518d;
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return str;
    }

    /* renamed from: e */
    public String m206e(Context context) {
        return m203b(context, ".jpg");
    }

    /* renamed from: a */
    public String m200a(Context context) {
        File m197a = m197a(context, this.f517c);
        if (m197a == null) {
            m197a = m199c(context, this.f517c);
        }
        if (m197a == null) {
            Log.e("PgyerSDK", "getCacheDirectory fail ,the reason is mobile phone unknown exception !");
        } else if (!m197a.exists() && !m197a.mkdirs()) {
            Log.e("PgyerSDK", "getCacheDirectory fail ,the reason is make directory fail !");
        }
        return m197a.getAbsolutePath();
    }

    /* renamed from: b */
    public String m202b(Context context) {
        String str = m200a(context) + File.separator + this.f519e;
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return str;
    }

    /* renamed from: c */
    public static File m199c(Context context, String str) {
        File file;
        if (TextUtils.isEmpty(str)) {
            file = context.getCacheDir();
        } else {
            file = new File(context.getFilesDir(), str);
        }
        if (!file.exists() && !file.mkdirs()) {
            Log.e("PgyerSDK", "getInternalDirectory fail ,the reason is make directory fail !");
        }
        return file;
    }

    /* renamed from: b */
    public static boolean m198b(File file) {
        if (file == null) {
            return false;
        }
        File file2 = new File(file.getParent() + File.separator + System.currentTimeMillis());
        file.renameTo(file2);
        return file2.delete();
    }

    /* renamed from: a */
    public void m201a(File file) {
        if (file.isFile()) {
            m198b(file);
            return;
        }
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length != 0) {
                for (File file2 : listFiles) {
                    m201a(file2);
                }
                m198b(file);
                return;
            }
            m198b(file);
        }
    }

    /* renamed from: a */
    public static File m197a(Context context, String str) {
        File externalFilesDir;
        if ("mounted".equals(Environment.getExternalStorageState())) {
            if (TextUtils.isEmpty(str)) {
                externalFilesDir = context.getExternalCacheDir();
            } else {
                externalFilesDir = context.getExternalFilesDir(str);
            }
            if (externalFilesDir == null) {
                externalFilesDir = new File(Environment.getExternalStorageDirectory(), "Android/data/" + context.getPackageName() + "/cache/" + str);
            }
            if (externalFilesDir == null) {
                Log.e("PgyerSDK", "getExternalDirectory fail ,the reason is sdCard unknown exception !");
                return externalFilesDir;
            }
            if (externalFilesDir.exists() || externalFilesDir.mkdirs()) {
                return externalFilesDir;
            }
            Log.e("PgyerSDK", "getExternalDirectory fail ,the reason is make directory fail !");
            return externalFilesDir;
        }
        Log.e("PgyerSDK", "getExternalDirectory fail ,the reason is sdCard nonexistence or sdCard mount fail !");
        return null;
    }
}
