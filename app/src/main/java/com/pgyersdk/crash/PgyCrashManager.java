package com.pgyersdk.crash;

import android.content.Context;

import com.pgyersdk.PgyerProvider;
import com.pgyersdk.p012c.Constants;
import com.pgyersdk.utils.AsyncTaskUtils;
import com.pgyersdk.utils.FileUtils;
import com.pgyersdk.utils.LogUtils;
import com.pgyersdk.utils.Util;
import com.pgyersdk.utils.Utils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/* loaded from: classes2.dex */
public class PgyCrashManager {

    /* renamed from: a */
    private static boolean f480a = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.pgyersdk.crash.PgyCrashManager$a */
    /* loaded from: classes2.dex */
    public enum EnumC2024a {
        EXCEPTION,
        CRASH
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m154a(StringBuffer stringBuffer, EnumC2024a enumC2024a) {
        if (Util.m244e()) {
            Date date = new Date();
            try {
                File m153a = m153a(enumC2024a);
                StringBuilder sb = new StringBuilder();
                sb.append("create Crash File  path:");
                sb.append(m153a.getAbsolutePath());
                LogUtils.m216a("PgyerSDK", sb.toString());
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Writing unhandled exception to: ");
                sb2.append(m153a.getAbsolutePath());
                LogUtils.m216a("PgyerSDK", sb2.toString());
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(m153a));
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Package: ");
                sb3.append(Constants.f465c);
                sb3.append("\n");
                bufferedWriter.write(sb3.toString());
                StringBuilder sb4 = new StringBuilder();
                sb4.append("Version Code: ");
                sb4.append(Constants.f464b);
                sb4.append("\n");
                bufferedWriter.write(sb4.toString());
                StringBuilder sb5 = new StringBuilder();
                sb5.append("Version Name: ");
                sb5.append(Constants.f466d);
                sb5.append("\n");
                bufferedWriter.write(sb5.toString());
                StringBuilder sb6 = new StringBuilder();
                sb6.append("Android: ");
                sb6.append(Constants.f467e);
                sb6.append("\n");
                bufferedWriter.write(sb6.toString());
                StringBuilder sb7 = new StringBuilder();
                sb7.append("Manufacturer: ");
                sb7.append(Constants.f469g);
                sb7.append("\n");
                bufferedWriter.write(sb7.toString());
                StringBuilder sb8 = new StringBuilder();
                sb8.append("Model: ");
                sb8.append(Constants.f468f);
                sb8.append("\n");
                bufferedWriter.write(sb8.toString());
                if (Constants.f470h != null) {
                    StringBuilder sb9 = new StringBuilder();
                    sb9.append("CrashReporter Key: ");
                    sb9.append(Constants.f470h);
                    sb9.append("\n");
                    bufferedWriter.write(sb9.toString());
                }
                StringBuilder sb10 = new StringBuilder();
                sb10.append("Date: ");
                sb10.append(date);
                sb10.append("\n");
                bufferedWriter.write(sb10.toString());
                bufferedWriter.write("\n");
                bufferedWriter.write(stringBuffer.toString());
                bufferedWriter.flush();
                bufferedWriter.close();
            } catch (Exception e) {
                LogUtils.m217a("PgyerSDK", "Error saving exception stacktrace!\n", e);
            }
        }
    }

    public static boolean isIsIgnoreDefaultHander() {
        return f480a;
    }

    @Deprecated
    public static void register(Context context) {
        register();
    }

    @Deprecated
    public static void reportCaughtException(Context context, Exception exc) {
        reportCaughtException(exc);
    }

    public static void setIsIgnoreDefaultHander(boolean z) {
        f480a = z;
    }

    public static void unregister() {
    }

    public static void register() {
        if (Util.m236a()) {
            PgyerCrashObservable.get().m156a();
        }
    }

    public static void reportCaughtException(Exception exc) {
        AsyncTaskUtils.m194a(new ReportAsynTask(Utils.m246a(exc), true));
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0033, code lost:

        r3 = null;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    class C2027b {

        /* renamed from: a */
        static final /* synthetic */ int[] f489a;

        static {
            int[] iArr = new int[PgyCrashManager.EnumC2024a.values().length];
            f489a = iArr;
            try {
                iArr[PgyCrashManager.EnumC2024a.CRASH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f489a[PgyCrashManager.EnumC2024a.EXCEPTION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private static File m153a(EnumC2024a enumC2024a) {
        File file;
        int i;
        File file2 = new File(FileUtils.m196a().m202b(PgyerProvider.f436a));
        try {
            i = C2027b.f489a[enumC2024a.ordinal()];

            if (i != 1) {
                if (i == 2) {
                    file = File.createTempFile("exception-", ".stacktrace", file2);
                }
                file = null;
            } else {
                file = File.createTempFile("crash-", ".stacktrace", file2);
            }
            return file;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}