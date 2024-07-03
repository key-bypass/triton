package com.pgyersdk.p014e.p015a;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import java.lang.reflect.Method;

/* compiled from: SystemBarTintManager.java */
/* renamed from: com.pgyersdk.e.a.b */
/* loaded from: classes2.dex */
public class SystemBarTintManager {

    /* renamed from: a */
    private static String f499a;

    /* renamed from: b */
    private final a f500b;

    /* renamed from: c */
    private boolean f501c;

    /* renamed from: d */
    private boolean f502d;

    /* renamed from: e */
    private boolean f503e;

    /* renamed from: f */
    private View f504f;

    /* renamed from: g */
    private View f505g;

    /* compiled from: SystemBarTintManager.java */
    /* renamed from: com.pgyersdk.e.a.b$a */
    /* loaded from: classes2.dex */
    public static class a {

        /* renamed from: a */
        private final boolean f506a;

        /* renamed from: b */
        private final boolean f507b;

        /* renamed from: c */
        private final int f508c;

        /* renamed from: d */
        private final int f509d;

        /* renamed from: e */
        private final boolean f510e;

        /* renamed from: f */
        private final int f511f;

        /* renamed from: g */
        private final int f512g;

        /* renamed from: h */
        private final boolean f513h;

        /* renamed from: i */
        private final float f514i;

        /* renamed from: a */
        private int m184a(Context context) {
            if (Build.VERSION.SDK_INT < 14) {
                return 0;
            }
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(R.attr.actionBarSize, typedValue, true);
            return TypedValue.complexToDimensionPixelSize(typedValue.data, context.getResources().getDisplayMetrics());
        }

        /* renamed from: b */
        private int m186b(Context context) {
            Resources resources = context.getResources();
            if (Build.VERSION.SDK_INT < 14 || !m188d(context)) {
                return 0;
            }
            return m185a(resources, this.f513h ? "navigation_bar_height" : "navigation_bar_height_landscape");
        }

        /* renamed from: c */
        private int m187c(Context context) {
            Resources resources = context.getResources();
            if (Build.VERSION.SDK_INT < 14 || !m188d(context)) {
                return 0;
            }
            return m185a(resources, "navigation_bar_width");
        }

        /* renamed from: d */
        private boolean m188d(Context context) {
            Resources resources = context.getResources();
            int identifier = resources.getIdentifier("config_showNavigationBar", "bool", "android");
            if (identifier != 0) {
                boolean z = resources.getBoolean(identifier);
                if ("1".equals(SystemBarTintManager.f499a)) {
                    return false;
                }
                if ("0".equals(SystemBarTintManager.f499a)) {
                    return true;
                }
                return z;
            }
            return !ViewConfiguration.get(context).hasPermanentMenuKey();
        }

        /* renamed from: e */
        public boolean m193e() {
            return this.f514i >= 600.0f || this.f513h;
        }

        private a(Activity activity, boolean z, boolean z2) {
            Resources resources = activity.getResources();
            this.f513h = resources.getConfiguration().orientation == 1;
            this.f514i = m183a(activity);
            this.f508c = m185a(resources, "status_bar_height");
            this.f509d = m184a((Context) activity);
            int m186b = m186b(activity);
            this.f511f = m186b;
            this.f512g = m187c(activity);
            this.f510e = m186b > 0;
            this.f506a = z;
            this.f507b = z2;
        }

        /* renamed from: a */
        private int m185a(Resources resources, String str) {
            int identifier = resources.getIdentifier(str, "dimen", "android");
            if (identifier > 0) {
                return resources.getDimensionPixelSize(identifier);
            }
            return 0;
        }

        /* renamed from: c */
        public int m191c() {
            return this.f508c;
        }

        /* renamed from: b */
        public int m190b() {
            return this.f512g;
        }

        /* renamed from: a */
        private float m183a(Activity activity) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            if (Build.VERSION.SDK_INT >= 16) {
                activity.getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
            } else {
                activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            }
            float f = displayMetrics.widthPixels;
            float f2 = displayMetrics.density;
            return Math.min(f / f2, displayMetrics.heightPixels / f2);
        }

        /* renamed from: d */
        public boolean m192d() {
            return this.f510e;
        }

        /* renamed from: a */
        public int m189a() {
            return this.f511f;
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 19) {
            try {
                Method declaredMethod = Class.forName("android.os.SystemProperties").getDeclaredMethod("get", String.class);
                declaredMethod.setAccessible(true);
                Object[] objArr = new Object[1];
                objArr[0] = "qemu.hw.mainkeys";
                f499a = (String) declaredMethod.invoke(null, objArr);
            } catch (Throwable unused) {
                f499a = null;
            }
        }
    }

    public SystemBarTintManager(Activity activity) {
        Window window = activity.getWindow();
        ViewGroup viewGroup = (ViewGroup) window.getDecorView();
        if (Build.VERSION.SDK_INT >= 19) {
            TypedArray obtainStyledAttributes = activity.obtainStyledAttributes(new int[]{R.attr.windowTranslucentStatus, R.attr.windowTranslucentNavigation});
            try {
                this.f501c = obtainStyledAttributes.getBoolean(0, false);
                this.f502d = obtainStyledAttributes.getBoolean(1, false);
                obtainStyledAttributes.recycle();
                WindowManager.LayoutParams attributes = window.getAttributes();
                if ((attributes.flags & 67108864) != 0) {
                    this.f501c = true;
                }
                if ((attributes.flags & 134217728) != 0) {
                    this.f502d = true;
                }
            } catch (Throwable th) {
                obtainStyledAttributes.recycle();
                throw th;
            }
        }
        a aVar = new a(activity, this.f501c, this.f502d);
        this.f500b = aVar;
        if (!aVar.m192d()) {
            this.f502d = false;
        }
        if (this.f501c) {
            m180b(activity, viewGroup);
        }
        if (this.f502d) {
            m179a(activity, viewGroup);
        }
    }

    /* renamed from: b */
    private void m180b(Context context, ViewGroup viewGroup) {
        this.f504f = new View(context);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, this.f500b.m191c());
        layoutParams.gravity = 48;
        if (this.f502d && !this.f500b.m193e()) {
            layoutParams.rightMargin = this.f500b.m190b();
        }
        this.f504f.setLayoutParams(layoutParams);
        this.f504f.setBackgroundColor(-1728053248);
        this.f504f.setVisibility(8);
        viewGroup.addView(this.f504f);
    }

    /* renamed from: a */
    public void m182a(boolean z) {
        this.f503e = z;
        if (this.f501c) {
            this.f504f.setVisibility(z ? 0 : 8);
        }
    }

    /* renamed from: a */
    public void m181a(int i) {
        if (this.f501c) {
            this.f504f.setBackgroundColor(i);
        }
    }

    /* renamed from: a */
    private void m179a(Context context, ViewGroup viewGroup) {
        FrameLayout.LayoutParams layoutParams;
        this.f505g = new View(context);
        if (this.f500b.m193e()) {
            layoutParams = new FrameLayout.LayoutParams(-1, this.f500b.m189a());
            layoutParams.gravity = 80;
        } else {
            layoutParams = new FrameLayout.LayoutParams(this.f500b.m190b(), -1);
            layoutParams.gravity = 5;
        }
        this.f505g.setLayoutParams(layoutParams);
        this.f505g.setBackgroundColor(-1728053248);
        this.f505g.setVisibility(8);
        viewGroup.addView(this.f505g);
    }
}
