package com.pgyersdk.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Base64;
import java.io.FileOutputStream;
import java.io.IOException;

/* compiled from: ImageUtil.java */
/* renamed from: com.pgyersdk.f.e */
/* loaded from: classes2.dex */
public class ImageUtil {
    /* renamed from: a */
    public static Bitmap m212a(Context context, String str, int i, int i2) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = m211a(context, str, i);
        return m213a(BitmapFactory.decodeFile(str, options), i, i2);
    }

    /* renamed from: a */
    private static int m211a(Context context, String str, int i) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        int i2 = 1;
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        while (options.outWidth / i2 >= i) {
            i2 *= 2;
        }
        return i2;
    }

    /* renamed from: a */
    public static Bitmap m213a(Bitmap bitmap, int i, int i2) {
        if (bitmap == null) {
            return null;
        }
        Matrix matrix = new Matrix();
        matrix.postScale(i / bitmap.getWidth(), i2 / bitmap.getHeight());
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    /* renamed from: a */
    public static Boolean m215a(Bitmap bitmap, String str) {
        FileOutputStream fileOutputStream = null;
        if (bitmap == null) {
            return false;
        }
        FileOutputStream fileOutputStream2 = null;
        try {
            fileOutputStream = new FileOutputStream(str);
        } catch (Exception e) {
            e = e;
        }
        try {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.close();
            return true;
        } catch (Exception e2) {
            var e = e2;
            fileOutputStream2 = fileOutputStream;
            LogUtils.m217a("PgyerSDK", "Could not save screenshot.", e);
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
            return false;
        }
    }

    /* renamed from: a */
    public static Bitmap m214a(String str) {
        try {
            byte[] decode = Base64.decode(str, 0);
            return BitmapFactory.decodeByteArray(decode, 0, decode.length);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
