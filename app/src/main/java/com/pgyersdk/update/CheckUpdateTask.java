package com.pgyersdk.update;

import android.os.AsyncTask;
import android.os.Build;

import com.pgyersdk.PgyerProvider;
import com.pgyersdk.p012c.Constants;
import com.pgyersdk.p012c.Strings;
import com.pgyersdk.update.javabean.AppBean;
import com.pgyersdk.utils.HttpURLConnectionBuilder;
import com.pgyersdk.utils.LogUtils;
import com.pgyersdk.utils.SteamToStringUtil;
import com.pgyersdk.utils.Utils;

import java.net.HttpURLConnection;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;


/* compiled from: CheckUpdateTask.java */
/* renamed from: com.pgyersdk.update.a */
/* loaded from: classes2.dex */
public class CheckUpdateTask extends AsyncTask<Void, String, HashMap<String, String>> {

    /* renamed from: a */
    protected UpdateManagerListener f710a;

    /* renamed from: b */
    private boolean f711b;

    public CheckUpdateTask(UpdateManagerListener updateManagerListener) {
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

    private HashMap<String, String> m371a() {
        int responseCode = 0;
        boolean z;
        HashMap<String, String> hashMap = new HashMap<>();
        HttpURLConnection httpURLConnection = null;
        try {
            try {
                HashMap hashMap2 = new HashMap();
                hashMap2.put("agKey", Constants.f474l);
                hashMap2.put("version", Constants.f466d);
                hashMap2.put("build", Constants.f464b);
                StringBuilder sb = new StringBuilder();
                sb.append(Build.VERSION.SDK_INT);
                sb.append("");
                hashMap2.put("sdkapi", sb.toString());
                hashMap2.put("_api_key", "305092bc73c180b55c26012a94809131");
                httpURLConnection = new HttpURLConnectionBuilder("http://www.pgyer.com/apiv1/update/check").m207a("POST").m209a(hashMap2, PgyerProvider.f436a, null, null).m210a();
                responseCode = httpURLConnection.getResponseCode();
            } catch (Exception e) {
                UpdateManagerListener updateManagerListener = this.f710a;
                if (updateManagerListener != null) {
                    updateManagerListener.checkUpdateFailed(e);
                }
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
            }
            if (responseCode != 202 && responseCode != 200) {
                z = false;
                this.f711b = z;
                hashMap.put("status", String.valueOf(httpURLConnection.getResponseCode()));
                hashMap.put("response", SteamToStringUtil.m234a(httpURLConnection));
                LogUtils.m219c("response", hashMap.get("response"));
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                return hashMap;
            }
            z = true;
            this.f711b = z;
            hashMap.put("status", String.valueOf(httpURLConnection.getResponseCode()));
            hashMap.put("response", SteamToStringUtil.m234a(httpURLConnection));
            LogUtils.m219c("response", hashMap.get("response"));
            if (httpURLConnection != null) {
            }
            return hashMap;
        } catch (Throwable th) {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
        return hashMap;
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
