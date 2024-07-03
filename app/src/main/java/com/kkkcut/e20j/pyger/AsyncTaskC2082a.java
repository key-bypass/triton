package com.kkkcut.e20j.pyger;

import android.os.AsyncTask;
import com.pgyersdk.p012c.Strings;
import com.pgyersdk.utils.LogUtils;
import com.pgyersdk.utils.Utils;
import com.pgyersdk.update.UpdateManagerListener;
import com.pgyersdk.update.javabean.AppBean;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: CheckUpdateTask.java */
/* renamed from: com.pgyersdk.update.a */
/* loaded from: classes2.dex */
public class AsyncTaskC2082a extends AsyncTask<Void, String, HashMap<String, String>> {

    /* renamed from: a */
    protected UpdateManagerListener f710a;

    /* renamed from: b */
    private boolean f711b;

    public AsyncTaskC2082a(UpdateManagerListener updateManagerListener) {
        this.f710a = updateManagerListener;
    }

    @Override // android.os.AsyncTask
    /* renamed from: a */
    public HashMap<String, String> doInBackground(Void... voidArr) {
        if (!Utils.m249b()) {
            this.f710a.checkUpdateFailed(new Exception(Strings.m151a(518)));
            LogUtils.m216a("PgyerSDK", "Check update failed");
        }
        return m371a();
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0089  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.util.HashMap<java.lang.String, java.lang.String> m371a() {
        /*
            r7 = this;
            java.lang.String r0 = "response"
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            r2 = 0
            java.util.HashMap r3 = new java.util.HashMap     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L8f
            r3.<init>()     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L8f
            java.lang.String r4 = "agKey"
            java.lang.String r5 = com.pgyersdk.p012c.C2022a.f474l     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L8f
            r3.put(r4, r5)     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L8f
            java.lang.String r4 = "version"
            java.lang.String r5 = com.pgyersdk.p012c.C2022a.f466d     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L8f
            r3.put(r4, r5)     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L8f
            java.lang.String r4 = "build"
            java.lang.String r5 = com.pgyersdk.p012c.C2022a.f464b     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L8f
            r3.put(r4, r5)     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L8f
            java.lang.String r4 = "sdkapi"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L8f
            r5.<init>()     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L8f
            int r6 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L8f
            r5.append(r6)     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L8f
            java.lang.String r6 = ""
            r5.append(r6)     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L8f
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L8f
            r3.put(r4, r5)     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L8f
            java.lang.String r4 = "_api_key"
            java.lang.String r5 = "305092bc73c180b55c26012a94809131"
            r3.put(r4, r5)     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L8f
            com.pgyersdk.f.d r4 = new com.pgyersdk.f.d     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L8f
            java.lang.String r5 = "http://www.pgyer.com/apiv1/update/check"
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L8f
            java.lang.String r5 = "POST"
            com.pgyersdk.f.d r4 = r4.m207a(r5)     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L8f
            android.content.Context r5 = com.pgyersdk.PgyerProvider.f436a     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L8f
            com.pgyersdk.f.d r3 = r4.m209a(r3, r5, r2, r2)     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L8f
            java.net.HttpURLConnection r2 = r3.m210a()     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L8f
            int r3 = r2.getResponseCode()     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L8f
            r4 = 202(0xca, float:2.83E-43)
            if (r3 == r4) goto L67
            r4 = 200(0xc8, float:2.8E-43)
            if (r3 != r4) goto L65
            goto L67
        L65:
            r3 = 0
            goto L68
        L67:
            r3 = 1
        L68:
            r7.f711b = r3     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L8f
            java.lang.String r3 = "status"
            int r4 = r2.getResponseCode()     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L8f
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L8f
            r1.put(r3, r4)     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L8f
            java.lang.String r3 = com.pgyersdk.p016f.C2045j.m234a(r2)     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L8f
            r1.put(r0, r3)     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L8f
            java.lang.Object r3 = r1.get(r0)     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L8f
            java.lang.String r3 = (java.lang.String) r3     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L8f
            com.pgyersdk.p016f.C2041f.m219c(r0, r3)     // Catch: java.lang.Throwable -> L8d java.lang.Exception -> L8f
            if (r2 == 0) goto L9c
            r2.disconnect()
            goto L9c
        L8d:
            r0 = move-exception
            goto L9d
        L8f:
            r0 = move-exception
            com.pgyersdk.update.UpdateManagerListener r3 = r7.f710a     // Catch: java.lang.Throwable -> L8d
            if (r3 == 0) goto L97
            r3.checkUpdateFailed(r0)     // Catch: java.lang.Throwable -> L8d
        L97:
            if (r2 == 0) goto L9c
            r2.disconnect()
        L9c:
            return r1
        L9d:
            if (r2 == 0) goto La2
            r2.disconnect()
        La2:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kkkcut.e20j.pyger.AsyncTaskC2082a.m371a():java.util.HashMap");
    }

    @Override // android.os.AsyncTask
    /* renamed from: a */
    public void onPostExecute(HashMap<String, String> hashMap) {
        if (!this.f711b) {
            UpdateManagerListener updateManagerListener = this.f710a;
            if (updateManagerListener != null) {
                updateManagerListener.checkUpdateFailed(new RuntimeException(Strings.m151a(517)));
                LogUtils.m216a("PgyerSDK", "Check update failed");
                return;
            }
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(hashMap.get("response")).getJSONObject("data");
            if (jSONObject.getBoolean("updateDeny")) {
                this.f710a.checkUpdateFailed(new RuntimeException(Strings.m151a(517)));
                LogUtils.m216a("PgyerSDK", "Check update failed. Please go to wwww.pgyer.com to get more detail");
                return;
            }
            if (jSONObject.getBoolean("haveNewVersion")) {
                AppBean m370a = m370a(jSONObject);
                UpdateManagerListener updateManagerListener2 = this.f710a;
                if (updateManagerListener2 != null) {
                    updateManagerListener2.onUpdateAvailable(m370a);
                }
                LogUtils.m216a("PgyerSDK", "There is a new version");
                return;
            }
            UpdateManagerListener updateManagerListener3 = this.f710a;
            if (updateManagerListener3 != null) {
                updateManagerListener3.onNoUpdateAvailable();
            }
            LogUtils.m216a("PgyerSDK", "It's the latest version");
        } catch (JSONException e) {
            e.printStackTrace();
            UpdateManagerListener updateManagerListener4 = this.f710a;
            if (updateManagerListener4 != null) {
                updateManagerListener4.checkUpdateFailed(e);
            }
            LogUtils.m217a("PgyerSDK", "JSONException", e);
        } catch (Exception e2) {
            UpdateManagerListener updateManagerListener5 = this.f710a;
            if (updateManagerListener5 != null) {
                updateManagerListener5.checkUpdateFailed(e2);
            }
            LogUtils.m217a("PgyerSDK", "Please check you config of PGY SDK. ", e2);
        }
    }

    /* renamed from: a */
    private AppBean m370a(JSONObject jSONObject) throws JSONException {
        AppBean appBean = new AppBean();
        appBean.setDownloadURL(jSONObject.getString("downloadURL"));
        appBean.setVersionName(jSONObject.getString("version"));
        appBean.setReleaseNote(jSONObject.getString("releaseNote"));
        appBean.setVersionCode(jSONObject.getString("build"));
        appBean.setShouldForceToUpdate(Boolean.parseBoolean(jSONObject.getString("needForceUpdate")));
        return appBean;
    }
}
