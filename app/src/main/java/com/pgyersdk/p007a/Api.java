package com.pgyersdk.p007a;

import android.content.Context;
import android.os.AsyncTask;
import com.pgyersdk.PgyerProvider;
import com.pgyersdk.p012c.Constants;
import com.pgyersdk.p013d.DeviceHelper;
import com.pgyersdk.utils.AsyncTaskUtils;
import com.pgyersdk.utils.HttpURLConnectionBuilder;
import com.pgyersdk.utils.SteamToStringUtil;

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
        */
        public HashMap<String, String> doInBackground(Void... voidArr) {
            HttpURLConnection httpURLConnection;
            try {
                try {
                    Map<String, String> m101b = Api.m101b();
                    StringBuilder sb = new StringBuilder();
                    sb.append("http://www.pgyer.com/apiv1");
                    sb.append(Api.f437a);
                    HttpURLConnection m210a = new HttpURLConnectionBuilder(sb.toString()).m207a("POST").m209a(m101b, PgyerProvider.f436a, null, null).m210a();
                    this.f438a = m210a;
                    m210a.connect();
                    this.f439b.put("status", String.valueOf(this.f438a.getResponseCode()));
                    this.f439b.put("response", SteamToStringUtil.m234a(this.f438a));
                    httpURLConnection = this.f438a;
                    return (HashMap<String, String>) m101b;
                } catch (Exception e) {
                    e.printStackTrace();
                    httpURLConnection = this.f438a;
                }

            } catch (Throwable th) {
                HttpURLConnection httpURLConnection2 = this.f438a;
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                throw th;
            }
            return null;
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
