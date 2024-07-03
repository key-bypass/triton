package com.pgyersdk.feedback;

import android.content.Context;
import android.hardware.SensorListener;
import android.hardware.SensorManager;

import com.cutting.machine.error.ErrorCode;
import com.pgyersdk.p012c.Strings;
import com.pgyersdk.utils.LogUtils;

/* compiled from: ShakeListener.java */
/* renamed from: com.pgyersdk.feedback.n */
/* loaded from: classes2.dex */
public class ShakeListener implements SensorListener {

    /* renamed from: a */
    public static int f689a = 950;

    /* renamed from: f */
    private SensorManager f694f;

    /* renamed from: j */
    private long f698j;

    /* renamed from: k */
    private a f699k;

    /* renamed from: l */
    private Context f700l;

    /* renamed from: n */
    private long f702n;

    /* renamed from: o */
    private long f703o;

    /* renamed from: b */
    private int f690b = 110;

    /* renamed from: c */
    private int f691c = ErrorCode.keyCuttingError;

    /* renamed from: d */
    private int f692d = 1000;

    /* renamed from: e */
    private int f693e = 4;

    /* renamed from: g */
    private float f695g = -1.0f;

    /* renamed from: h */
    private float f696h = -1.0f;

    /* renamed from: i */
    private float f697i = -1.0f;

    /* renamed from: m */
    private int f701m = 0;

    /* compiled from: ShakeListener.java */
    /* renamed from: com.pgyersdk.feedback.n$a */
    /* loaded from: classes2.dex */
    public interface a {
        /* renamed from: a */
        void mo363a() throws IllegalAccessException;
    }

    public ShakeListener(Context context) {
        this.f700l = context;
        m365a();
    }

    /* renamed from: a */
    public void m366a(a aVar) {
        this.f699k = aVar;
    }

    /* renamed from: b */
    public void m367b() {
        SensorManager sensorManager = this.f694f;
        if (sensorManager != null) {
            sensorManager.unregisterListener(this, 2);
            this.f694f = null;
        }
    }

    @Override // android.hardware.SensorListener
    public void onAccuracyChanged(int i, int i2) {
    }

    @Override // android.hardware.SensorListener
    public void onSensorChanged(int i, float[] fArr) {
        if (i != 2) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.f703o > this.f691c) {
            this.f701m = 0;
        }
        long j = currentTimeMillis - this.f698j;
        if (j > this.f690b) {
            if ((Math.abs(((((fArr[0] + fArr[1]) + fArr[2]) - this.f695g) - this.f696h) - this.f697i) / ((float) j)) * 10000.0f > f689a) {
                int i2 = this.f701m + 1;
                this.f701m = i2;
                if (i2 >= this.f693e && currentTimeMillis - this.f702n > this.f692d) {
                    this.f702n = currentTimeMillis;
                    this.f701m = 0;
                    a aVar = this.f699k;
                    if (aVar != null) {
                        try {
                            aVar.mo363a();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
                this.f703o = currentTimeMillis;
            }
            this.f698j = currentTimeMillis;
            this.f695g = fArr[0];
            this.f696h = fArr[1];
            this.f697i = fArr[2];
        }
    }

    /* renamed from: a */
    public void m365a() {
        if (this.f694f == null) {
            SensorManager sensorManager = (SensorManager) this.f700l.getSystemService("sensor");
            this.f694f = sensorManager;
            if (sensorManager == null) {
                LogUtils.m216a("PgyerSDK", Strings.m151a(1060));
            }
            if (this.f694f.registerListener(this, 2, 1)) {
                return;
            }
            this.f694f.unregisterListener(this, 2);
            LogUtils.m216a("PgyerSDK", Strings.m151a(1060));
        }
    }
}
