package com.pgyersdk.crash;

import android.content.Context;
import com.pgyersdk.p012c.C2022a;
import com.pgyersdk.p016f.C2036a;
import com.pgyersdk.p016f.C2041f;
import com.pgyersdk.p016f.C2047l;
import com.pgyersdk.p016f.C2048m;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
        if (C2047l.m244e()) {
            Date date = new Date();
            try {
                File m153a = m153a(enumC2024a);
                StringBuilder sb = new StringBuilder();
                sb.append("create Crash File  path:");
                sb.append(m153a.getAbsolutePath());
                C2041f.m216a("PgyerSDK", sb.toString());
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Writing unhandled exception to: ");
                sb2.append(m153a.getAbsolutePath());
                C2041f.m216a("PgyerSDK", sb2.toString());
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(m153a));
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Package: ");
                sb3.append(C2022a.f465c);
                sb3.append("\n");
                bufferedWriter.write(sb3.toString());
                StringBuilder sb4 = new StringBuilder();
                sb4.append("Version Code: ");
                sb4.append(C2022a.f464b);
                sb4.append("\n");
                bufferedWriter.write(sb4.toString());
                StringBuilder sb5 = new StringBuilder();
                sb5.append("Version Name: ");
                sb5.append(C2022a.f466d);
                sb5.append("\n");
                bufferedWriter.write(sb5.toString());
                StringBuilder sb6 = new StringBuilder();
                sb6.append("Android: ");
                sb6.append(C2022a.f467e);
                sb6.append("\n");
                bufferedWriter.write(sb6.toString());
                StringBuilder sb7 = new StringBuilder();
                sb7.append("Manufacturer: ");
                sb7.append(C2022a.f469g);
                sb7.append("\n");
                bufferedWriter.write(sb7.toString());
                StringBuilder sb8 = new StringBuilder();
                sb8.append("Model: ");
                sb8.append(C2022a.f468f);
                sb8.append("\n");
                bufferedWriter.write(sb8.toString());
                if (C2022a.f470h != null) {
                    StringBuilder sb9 = new StringBuilder();
                    sb9.append("CrashReporter Key: ");
                    sb9.append(C2022a.f470h);
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
                C2041f.m217a("PgyerSDK", "Error saving exception stacktrace!\n", e);
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
        if (C2047l.m236a()) {
            PgyerCrashObservable.get().m156a();
        }
    }

    public static void reportCaughtException(Exception exc) {
        C2036a.m194a(new AsyncTaskC2031f(C2048m.m246a(exc), true));
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0033, code lost:
    
        r3 = null;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.io.File m153a(com.pgyersdk.crash.PgyCrashManager.EnumC2024a r3) {
        /*
            java.io.File r0 = new java.io.File
            com.pgyersdk.f.c r1 = com.pgyersdk.p016f.C2038c.m196a()
            android.content.Context r2 = com.pgyersdk.PgyerProvider.f436a
            java.lang.String r1 = r1.m202b(r2)
            r0.<init>(r1)
            int[] r1 = com.pgyersdk.crash.C2027b.f489a     // Catch: java.io.IOException -> L2e
            int r3 = r3.ordinal()     // Catch: java.io.IOException -> L2e
            r3 = r1[r3]     // Catch: java.io.IOException -> L2e
            r1 = 1
            java.lang.String r2 = ".stacktrace"
            if (r3 == r1) goto L27
            r1 = 2
            if (r3 == r1) goto L20
            goto L32
        L20:
            java.lang.String r3 = "exception-"
            java.io.File r3 = java.io.File.createTempFile(r3, r2, r0)     // Catch: java.io.IOException -> L2e
            goto L33
        L27:
            java.lang.String r3 = "crash-"
            java.io.File r3 = java.io.File.createTempFile(r3, r2, r0)     // Catch: java.io.IOException -> L2e
            goto L33
        L2e:
            r3 = move-exception
            r3.printStackTrace()
        L32:
            r3 = 0
        L33:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.pgyersdk.crash.PgyCrashManager.m153a(com.pgyersdk.crash.PgyCrashManager$a):java.io.File");
    }
}
