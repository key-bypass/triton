package com.pgyersdk.crash;

import android.os.AsyncTask;
import com.pgyersdk.PgyerProvider;
import com.pgyersdk.p007a.Api;
import com.pgyersdk.utils.FileUtils;
import com.pgyersdk.utils.HttpURLConnectionBuilder;
import com.pgyersdk.utils.Util;
import com.pgyersdk.utils.Utils;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;

/* compiled from: ReportAsynTask.java */
/* renamed from: com.pgyersdk.crash.f */
/* loaded from: classes2.dex */
public class ReportAsynTask extends AsyncTask<Void, Void, Object> {

    /* renamed from: a */
    HttpURLConnection f491a;

    /* renamed from: b */
    StringBuffer f492b;

    /* renamed from: c */
    boolean f493c;

    public ReportAsynTask() {
        this.f493c = false;
    }

    private Boolean m159b(StringBuffer stringBuffer, boolean z) throws IOException {
        Map<String, String> m101b = Api.m101b();
        m101b.put("crashLog", stringBuffer.toString());
        m101b.put("isException", z ? "2" : "1");
        HttpURLConnection m210a = new HttpURLConnectionBuilder("http://www.pgyer.com/apiv1/crash/add").m207a("POST").m209a(m101b, PgyerProvider.f436a, null, null).m210a();
        this.f491a = m210a;
        int responseCode = m210a.getResponseCode();
        return Boolean.valueOf(responseCode == 202 || responseCode == 200);
    }

    @Override
    public Object doInBackground(Void... voidArr) {
        StringBuffer stringBuffer;
        if (this.f493c && (stringBuffer = this.f492b) != null) {
            m163a(stringBuffer, true);
            m158a();
            return null;
        }
        m158a();
        return null;
    }

    public ReportAsynTask(StringBuffer stringBuffer, boolean z) {
        this.f492b = stringBuffer;
        this.f493c = z;
    }

    /* renamed from: a */
    private void m158a() {
        File[] listFiles;
        if (Util.m244e() && Utils.m249b()) {
            File file = new File(FileUtils.m196a().m202b(PgyerProvider.f436a));
            if (file.exists() && file.isDirectory() && (listFiles = file.listFiles()) != null) {
                for (int i = 0; i < listFiles.length; i++) {
                    StringBuffer stringBuffer = new StringBuffer();
                    try {
                        Utils.m247a(stringBuffer, listFiles[i].getPath());
                        if (listFiles[i].getName().contains("crash")) {
                            m162a(stringBuffer, listFiles[i], false);
                        } else {
                            m162a(stringBuffer, listFiles[i], true);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /* renamed from: b */
    private void m160b() {
        HttpURLConnection httpURLConnection = this.f491a;
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x003c, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x002e, code lost:
    
        com.pgyersdk.p016f.C2038c.m196a().m201a(r7);
        com.pgyersdk.p016f.C2041f.m216a("PgyerSDK", "Transmission succeeded");
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x002c, code lost:
    
        if (r3.booleanValue() == false) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001c, code lost:
    
        if (r3.booleanValue() != false) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0039, code lost:
    
        com.pgyersdk.p016f.C2041f.m216a("PgyerSDK", "Transmission failed, will retry on next register() call");
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m162a(java.lang.StringBuffer r6, java.io.File r7, boolean r8) {
        /*
            r5 = this;
            java.lang.String r0 = "Transmission succeeded"
            java.lang.String r1 = "Transmission failed, will retry on next register() call"
            java.lang.String r2 = "PgyerSDK"
            r3 = 0
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)
            boolean r4 = com.pgyersdk.p016f.C2048m.m249b()     // Catch: java.lang.Throwable -> L1f java.lang.Exception -> L21
            if (r4 == 0) goto L15
            java.lang.Boolean r3 = r5.m159b(r6, r8)     // Catch: java.lang.Throwable -> L1f java.lang.Exception -> L21
        L15:
            r5.m160b()
            boolean r6 = r3.booleanValue()
            if (r6 == 0) goto L39
            goto L2e
        L1f:
            r6 = move-exception
            goto L3d
        L21:
            r6 = move-exception
            r6.printStackTrace()     // Catch: java.lang.Throwable -> L1f
            r5.m160b()
            boolean r6 = r3.booleanValue()
            if (r6 == 0) goto L39
        L2e:
            com.pgyersdk.f.c r6 = com.pgyersdk.p016f.C2038c.m196a()
            r6.m201a(r7)
            com.pgyersdk.p016f.C2041f.m216a(r2, r0)
            goto L3c
        L39:
            com.pgyersdk.p016f.C2041f.m216a(r2, r1)
        L3c:
            return
        L3d:
            r5.m160b()
            boolean r8 = r3.booleanValue()
            if (r8 == 0) goto L51
            com.pgyersdk.f.c r8 = com.pgyersdk.p016f.C2038c.m196a()
            r8.m201a(r7)
            com.pgyersdk.p016f.C2041f.m216a(r2, r0)
            goto L54
        L51:
            com.pgyersdk.p016f.C2041f.m216a(r2, r1)
        L54:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.pgyersdk.crash.AsyncTaskC2031f.m162a(java.lang.StringBuffer, java.io.File, boolean):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0035, code lost:
    
        if (r3.booleanValue() != false) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x004b, code lost:
    
        com.pgyersdk.crash.PgyCrashManager.m154a(r6, com.pgyersdk.crash.PgyCrashManager.EnumC2024a.EXCEPTION);
        com.pgyersdk.p016f.C2041f.m216a("PgyerSDK", "Transmission failed, will retry on next register() call");
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0053, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0047, code lost:
    
        com.pgyersdk.p016f.C2041f.m216a("PgyerSDK", "Transmission succeeded");
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0045, code lost:
    
        if (r3.booleanValue() == false) goto L62;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m163a(java.lang.StringBuffer r6, boolean r7) {
        /*
            r5 = this;
            java.lang.String r0 = "Transmission succeeded"
            java.lang.String r1 = "Transmission failed, will retry on next register() call"
            java.lang.String r2 = "PgyerSDK"
            boolean r3 = com.pgyersdk.p016f.C2048m.m249b()
            if (r3 != 0) goto L18
            boolean r7 = com.pgyersdk.p016f.C2047l.m244e()
            if (r7 == 0) goto L17
            com.pgyersdk.crash.PgyCrashManager$a r7 = com.pgyersdk.crash.PgyCrashManager.EnumC2024a.EXCEPTION
            com.pgyersdk.crash.PgyCrashManager.m154a(r6, r7)
        L17:
            return
        L18:
            boolean r3 = com.pgyersdk.p016f.C2047l.m236a()
            if (r3 != 0) goto L1f
            return
        L1f:
            r3 = 0
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)
            boolean r4 = com.pgyersdk.p016f.C2048m.m249b()     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L3a
            if (r4 == 0) goto L2e
            java.lang.Boolean r3 = r5.m159b(r6, r7)     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L3a
        L2e:
            r5.m160b()
            boolean r7 = r3.booleanValue()
            if (r7 == 0) goto L4b
            goto L47
        L38:
            r7 = move-exception
            goto L54
        L3a:
            r7 = move-exception
            r7.printStackTrace()     // Catch: java.lang.Throwable -> L38
            r5.m160b()
            boolean r7 = r3.booleanValue()
            if (r7 == 0) goto L4b
        L47:
            com.pgyersdk.p016f.C2041f.m216a(r2, r0)
            goto L53
        L4b:
            com.pgyersdk.crash.PgyCrashManager$a r7 = com.pgyersdk.crash.PgyCrashManager.EnumC2024a.EXCEPTION
            com.pgyersdk.crash.PgyCrashManager.m154a(r6, r7)
            com.pgyersdk.p016f.C2041f.m216a(r2, r1)
        L53:
            return
        L54:
            r5.m160b()
            boolean r3 = r3.booleanValue()
            if (r3 == 0) goto L61
            com.pgyersdk.p016f.C2041f.m216a(r2, r0)
            goto L69
        L61:
            com.pgyersdk.crash.PgyCrashManager$a r0 = com.pgyersdk.crash.PgyCrashManager.EnumC2024a.EXCEPTION
            com.pgyersdk.crash.PgyCrashManager.m154a(r6, r0)
            com.pgyersdk.p016f.C2041f.m216a(r2, r1)
        L69:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.pgyersdk.crash.AsyncTaskC2031f.m163a(java.lang.StringBuffer, boolean):void");
    }
}
