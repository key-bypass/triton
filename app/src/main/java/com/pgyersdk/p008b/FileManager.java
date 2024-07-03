package com.pgyersdk.p008b;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import com.pgyersdk.utils.FileUtils;
import com.pgyersdk.utils.LogUtils;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* compiled from: FileManager.java */
/* renamed from: com.pgyersdk.b.e */
/* loaded from: classes2.dex */
public class FileManager {

    /* compiled from: FileManager.java */
    /* renamed from: com.pgyersdk.b.e$a */
    /* loaded from: classes2.dex */
    public interface a {
        /* renamed from: a */
        void mo108a(Uri uri);

        /* renamed from: a */
        void mo109a(Throwable th);
    }

    /* renamed from: a */
    public static void m130a(Bitmap bitmap, Context context, a aVar) {
        File file;
        try {
            file = File.createTempFile("feedback_", ".jpg", new File(FileUtils.m196a().m205d(context)));
        } catch (IOException e) {
            e.printStackTrace();
            file = null;
        }
        if (file != null) {
            LogUtils.m220d("PgyerSDK", "saveScreeenShot image path:" + file.toString());
        } else {
            LogUtils.m220d("PgyerSDK", "saveScreeenShot image path: NULL");
        }
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bufferedOutputStream);
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            Uri fromFile = Uri.fromFile(file);
            if (file != null) {
                aVar.mo108a(fromFile);
            } else {
                aVar.mo109a(new Throwable("ScreenShot Uri equal null"));
            }
        } catch (IOException e2) {
            aVar.mo109a(e2);
        }
    }

    /* renamed from: a */
    public static void m132a(String str) {
        if (str == null) {
            return;
        }
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            File[] listFiles = file.listFiles();
            for (File file2 : listFiles) {
                m131a(file2);
            }
            return;
        }
        if (file.exists()) {
            m131a(file);
        }
    }

    /* renamed from: a */
    public static void m131a(File file) {
        if (file == null || !file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                m131a(file2);
            }
        }
        file.delete();
    }
}
