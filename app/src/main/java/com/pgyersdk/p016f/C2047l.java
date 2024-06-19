package com.pgyersdk.p016f;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import com.pgyersdk.PgyerActivityManager;
import com.pgyersdk.PgyerProvider;
import com.pgyersdk.p012c.C2022a;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: Util.java */
/* renamed from: com.pgyersdk.f.l */
/* loaded from: classes2.dex */
public class C2047l {

    /* renamed from: a */
    private static final Pattern f540a = Pattern.compile("[0-9a-f]+", 2);

    /* renamed from: a */
    public static boolean m236a() {
        if (m240c(C2022a.f474l) != null) {
            return true;
        }
        C2041f.m218b("PgyerSDK", "Please configuration param correctly,otherwise PGYER SDK can not work");
        return false;
    }

    /* renamed from: b */
    public static boolean m238b() {
        return m237a("android.permission.INTERNET");
    }

    /* renamed from: c */
    public static String m240c(String str) {
        if (C2046k.m235a(str)) {
            return null;
        }
        String trim = str.trim();
        Matcher matcher = f540a.matcher(trim);
        if (trim.length() == 32 && matcher.matches()) {
            return trim;
        }
        return null;
    }

    /* renamed from: d */
    public static boolean m243d() {
        return m237a("android.permission.RECORD_AUDIO");
    }

    /* renamed from: e */
    public static boolean m244e() {
        return m237a("android.permission.WRITE_EXTERNAL_STORAGE");
    }

    /* renamed from: b */
    public static boolean m239b(String str) {
        boolean matches = str.matches("^#([0-9a-fA-F]{6}|[0-9a-fA-F]{3})$");
        if (!matches) {
            C2041f.m216a("PgyerSDK", "please check setting color format is correct");
        }
        return matches;
    }

    /* renamed from: d */
    private static void m242d(String str) {
        if (Build.VERSION.SDK_INT >= 23 && PgyerActivityManager.isSuccessSetInstance() && (PgyerActivityManager.getInstance().getCurrentActivity() instanceof Activity)) {
            PgyerActivityManager.getInstance().getCurrentActivity().requestPermissions(new String[]{str}, 111111110);
        }
    }

    /* renamed from: a */
    public static boolean m237a(String str) {
        Context context = PgyerProvider.f436a;
        if (context == null) {
            C2041f.m220d("PgyerSDK", "Init sdk failed, context is  null.\n");
            return false;
        }
        boolean z = context.getPackageManager().checkPermission(str, PgyerProvider.f436a.getPackageName()) == 0;
        if (!z) {
            if (!str.equals("android.permission.READ_PHONE_STATE")) {
                C2041f.m220d("PgyerSDK", "There is no " + str + "\n");
                C2041f.m220d("PgyerSDK", "Please grant  permission if you use Pgyer SDK feature. ");
            }
            m242d(str);
        }
        return z;
    }

    /* renamed from: c */
    public static boolean m241c() {
        return m237a("android.permission.READ_PHONE_STATE");
    }
}
