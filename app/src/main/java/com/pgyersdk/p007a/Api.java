package com.pgyersdk.p007a;

import android.content.Context;
import android.os.AsyncTask;
import com.pgyersdk.PgyerProvider;
import com.pgyersdk.p012c.Constants;
import com.pgyersdk.p013d.DeviceHelper;
import com.pgyersdk.utils.AsyncTaskUtils;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

/* compiled from: Api.java */
/* renamed from: com.pgyersdk.a.a */
/* loaded from: classes2.dex */
public class Api {

    /* renamed from: a */
    private static String f437a = "/sdkstat/launch";

    /* renamed from: b */
    public static void m102b(Context context) {
        AsyncTaskUtils.m194a(new a());
    }

    /* renamed from: a */
    public static void m100a(Context context) {
        m102b(context);
    }

    /* renamed from: b */
    public static Map<String, String> m101b() {
        String str;
        HashMap hashMap = new HashMap();
        hashMap.put("agKey", Constants.f474l);
        hashMap.put("deviceId", Constants.f471i);
        hashMap.put("osType", "2");
        hashMap.put("from", Constants.f468f);
        hashMap.put("deviceName", Constants.f469g);
        hashMap.put("osVersion", Constants.f467e);
        hashMap.put("sdkVersion", Constants.f473k);
        hashMap.put("_api_key", "305092bc73c180b55c26012a94809131");
        hashMap.put("versionCode", Constants.f464b);
        hashMap.put("version", Constants.f466d);
        hashMap.put("deviceModel", Constants.f468f);
        hashMap.put("resolution", Constants.f472j);
        hashMap.put("jailBroken", Constants.m143a() ? "1" : "2");
        String[] m168a = DeviceHelper.m168a();
        hashMap.put("freeSapce", m168a[1] + " / " + m168a[0]);
        if (DeviceHelper.m172c()) {
            String[] m170b = DeviceHelper.m170b();
            hashMap.put("freeSdc", m170b[1] + " / " + m170b[0]);
        }
        String[] m174e = DeviceHelper.m174e(PgyerProvider.f436a);
        if (m174e.length == 2) {
            str = m174e[1] + " / " + m174e[0];
        } else {
            str = "";
        }
        hashMap.put("freeRam", str);
        hashMap.put("battery", DeviceHelper.m166a(PgyerProvider.f436a));
        hashMap.put("protrait", PgyerProvider.f436a.getResources().getConfiguration().orientation + "");
        Map<String, String> m173d = DeviceHelper.m173d(PgyerProvider.f436a);
        hashMap.put("network", m173d.containsKey("network_type") ? m173d.get("network_type") : "");
        hashMap.put("_appName", (String) PgyerProvider.f436a.getPackageManager().getApplicationLabel(PgyerProvider.f436a.getApplicationInfo()));
        hashMap.put("_packageName", PgyerProvider.f436a.getPackageName());
        hashMap.put("_language", PgyerProvider.f436a.getResources().getConfiguration().locale.getDisplayLanguage());
        return hashMap;
    }

    /* compiled from: Api.java */
    /* renamed from: com.pgyersdk.a.a$a */
    /* loaded from: classes2.dex */
    public static class a extends AsyncTask<Void, Void, HashMap<String, String>> {

        /* renamed from: a */
        HttpURLConnection f438a = null;

        /* renamed from: b */
        HashMap<String, String> f439b = new HashMap<>();

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Code restructure failed: missing block: B:12:0x0054, code lost:
        
            if (r4 != null) goto L20;
         */
        /* JADX WARN: Code restructure failed: missing block: B:14:0x0066, code lost:
        
            return r3.f439b;
         */
        /* JADX WARN: Code restructure failed: missing block: B:16:0x0061, code lost:
        
            r4.disconnect();
         */
        /* JADX WARN: Code restructure failed: missing block: B:21:0x005f, code lost:
        
            if (r4 == null) goto L21;
         */
        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.util.HashMap<java.lang.String, java.lang.String> doInBackground(java.lang.Void... r4) {
            /*
                r3 = this;
                java.util.Map r4 = com.pgyersdk.p007a.C2001a.m101b()     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
                com.pgyersdk.f.d r0 = new com.pgyersdk.f.d     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
                java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
                r1.<init>()     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
                java.lang.String r2 = "http://www.pgyer.com/apiv1"
                r1.append(r2)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
                java.lang.String r2 = com.pgyersdk.p007a.C2001a.m99a()     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
                r1.append(r2)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
                java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
                r0.<init>(r1)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
                java.lang.String r1 = "POST"
                com.pgyersdk.f.d r0 = r0.m207a(r1)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
                android.content.Context r1 = com.pgyersdk.PgyerProvider.f436a     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
                r2 = 0
                com.pgyersdk.f.d r4 = r0.m209a(r4, r1, r2, r2)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
                java.net.HttpURLConnection r4 = r4.m210a()     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
                r3.f438a = r4     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
                r4.connect()     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
                java.util.HashMap<java.lang.String, java.lang.String> r4 = r3.f439b     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
                java.lang.String r0 = "status"
                java.net.HttpURLConnection r1 = r3.f438a     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
                int r1 = r1.getResponseCode()     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
                java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
                r4.put(r0, r1)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
                java.util.HashMap<java.lang.String, java.lang.String> r4 = r3.f439b     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
                java.lang.String r0 = "response"
                java.net.HttpURLConnection r1 = r3.f438a     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
                java.lang.String r1 = com.pgyersdk.p016f.C2045j.m234a(r1)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
                r4.put(r0, r1)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
                java.net.HttpURLConnection r4 = r3.f438a
                if (r4 == 0) goto L64
                goto L61
            L57:
                r4 = move-exception
                goto L67
            L59:
                r4 = move-exception
                r4.printStackTrace()     // Catch: java.lang.Throwable -> L57
                java.net.HttpURLConnection r4 = r3.f438a
                if (r4 == 0) goto L64
            L61:
                r4.disconnect()
            L64:
                java.util.HashMap<java.lang.String, java.lang.String> r4 = r3.f439b
                return r4
            L67:
                java.net.HttpURLConnection r0 = r3.f438a
                if (r0 == 0) goto L6e
                r0.disconnect()
            L6e:
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.pgyersdk.p007a.C2001a.a.doInBackground(java.lang.Void[]):java.util.HashMap");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(HashMap<String, String> hashMap) {
            super.onPostExecute(hashMap);
            DeviceHelper.m176h(PgyerProvider.f436a);
        }
    }
}
