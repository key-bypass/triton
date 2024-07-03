package com.kkkcut.e20j.pyger;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;

import com.pgyersdk.p008b.p011c.RootViewInfo;

import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/* compiled from: ScreenshotTaker.java */
/* renamed from: com.pgyersdk.b.c.e */
/* loaded from: classes2.dex */
class C2015e {
    /* renamed from: a */
    public static Bitmap m120a(Activity activity, View[] viewArr) {
        if (activity != null) {
            List<RootViewInfo> m114a = C2011a.m114a(activity);
            View decorView = activity.getWindow().getDecorView();
            try {
                Bitmap createBitmap = Bitmap.createBitmap(decorView.getWidth(), decorView.getHeight(), Bitmap.Config.ARGB_8888);
                m125a(m114a, createBitmap, viewArr);
                return createBitmap;
            } catch (IllegalArgumentException unused) {
                return null;
            }
        }
        throw new IllegalArgumentException("Parameter activity cannot be null");
    }

    /* renamed from: a */
    private static void m125a(List<RootViewInfo> list, Bitmap bitmap, View[] viewArr) {
        Iterator<RootViewInfo> it = list.iterator();
        while (it.hasNext()) {
            m124a(it.next(), bitmap, viewArr);
        }
    }

    /* renamed from: a */
    private static void m124a(RootViewInfo rootViewInfo, Bitmap bitmap, View[] viewArr) {
        if ((rootViewInfo.m115a().flags & 2) == 2) {
            new Canvas(bitmap).drawARGB((int) (rootViewInfo.m115a().dimAmount * 255.0f), 0, 0, 0);
        }
        Canvas canvas = new Canvas(bitmap);
        canvas.translate(rootViewInfo.m116b(), rootViewInfo.m117c());
        int[] iArr = viewArr != null ? new int[viewArr.length] : null;
        if (viewArr != null) {
            for (int i = 0; i < viewArr.length; i++) {
                if (viewArr[i] != null) {
                    iArr[i] = viewArr[i].getVisibility();
                    viewArr[i].setVisibility(4);
                }
            }
        }
        rootViewInfo.m118d().draw(canvas);
        m121a(rootViewInfo.m118d(), canvas);
    }

    /* renamed from: a */
    private static ArrayList<View> m121a(View view, Canvas canvas) {
        if (!(view instanceof ViewGroup)) {
            ArrayList<View> arrayList = new ArrayList<>();
            arrayList.add(view);
            return arrayList;
        }
        ArrayList<View> arrayList2 = new ArrayList<>();
        ViewGroup viewGroup = (ViewGroup) view;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            ArrayList arrayList3 = new ArrayList();
            arrayList3.add(view);
            arrayList3.addAll(m121a(childAt, canvas));
            if (Build.VERSION.SDK_INT >= 14 && (childAt instanceof TextureView)) {
                m123a((TextureView) childAt, canvas);
            }
            if (childAt instanceof GLSurfaceView) {
                m122a((GLSurfaceView) childAt, canvas);
            }
            arrayList2.addAll(arrayList3);
        }
        return arrayList2;
    }

    /* renamed from: a */
    private static void m123a(TextureView textureView, Canvas canvas) {
        textureView.getLocationOnScreen(new int[2]);
        Bitmap bitmap = textureView.getBitmap();
        if (bitmap != null) {
            Paint paint = new Paint();
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP));
            canvas.drawBitmap(bitmap, r0[0], r0[1], paint);
            bitmap.recycle();
        }
    }

    /* renamed from: a */
    private static void m122a(GLSurfaceView gLSurfaceView, Canvas canvas) {
        if (gLSurfaceView.getWindowToken() != null) {
            gLSurfaceView.getLocationOnScreen(new int[2]);
            int width = gLSurfaceView.getWidth();
            int height = gLSurfaceView.getHeight();
            int[] iArr = new int[(height + 0) * width];
            IntBuffer wrap = IntBuffer.wrap(iArr);
            wrap.position(0);
            CountDownLatch countDownLatch = new CountDownLatch(1);
            gLSurfaceView.queueEvent(new RunnableC2014d(width, height, wrap, countDownLatch));
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int[] iArr2 = new int[width * height];
            int i = 0;
            int i2 = 0;
            while (i < height) {
                for (int i3 = 0; i3 < width; i3++) {
                    int i4 = iArr[(i * width) + i3];
                    iArr2[(((height - i2) - 1) * width) + i3] = (i4 & (-16711936)) | ((i4 << 16) & 16711680) | ((i4 >> 16) & 255);
                }
                i++;
                i2++;
            }
            Bitmap createBitmap = Bitmap.createBitmap(iArr2, width, height, Bitmap.Config.ARGB_8888);
            Paint paint = new Paint();
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP));
            canvas.drawBitmap(createBitmap, r0[0], r0[1], paint);
            createBitmap.recycle();
        }
    }
}
