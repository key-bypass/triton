package com.pgyersdk.feedback.p017a;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import com.pgyersdk.feedback.FeedbackActivity;
import com.pgyersdk.p012c.Constants;
import com.pgyersdk.p012c.Strings;
import com.pgyersdk.utils.StringUtil;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import org.json.JSONObject;

/* compiled from: SendFeedbackTask.java */
/* renamed from: com.pgyersdk.feedback.a.r */
/* loaded from: classes2.dex */
public class AsyncTaskC2067r extends AsyncTask<Void, Void, HashMap<String, String>> {

    /* renamed from: a */
    private Context f627a;

    /* renamed from: b */
    private Handler f628b;

    /* renamed from: c */
    private String f629c;

    /* renamed from: d */
    private String f630d;

    /* renamed from: e */
    private List<String> f631e;

    /* renamed from: f */
    private File f632f;

    /* renamed from: g */
    private ProgressDialog f633g;

    /* renamed from: h */
    private boolean f634h = true;

    /* renamed from: i */
    private String f635i;

    /* renamed from: j */
    private String f636j;

    public AsyncTaskC2067r(Context context, String str, String str2, String str3, List<String> list, File file, Handler handler, String str4) {
        this.f627a = context;
        this.f629c = str2;
        this.f630d = str3;
        this.f631e = list;
        this.f632f = file;
        this.f628b = handler;
        this.f635i = str;
        this.f636j = str4;
        if (context != null) {
            Constants.m145c(context);
        }
    }

    /* renamed from: a */
    public void m321a(boolean z) {
        this.f634h = z;
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        String m151a = Strings.m151a(1061);
        ProgressDialog progressDialog = this.f633g;
        if ((progressDialog == null || !progressDialog.isShowing()) && this.f634h) {
            this.f633g = ProgressDialog.show(this.f627a, "", m151a, true, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public HashMap<String, String> doInBackground(Void... voidArr) {
        return m318a();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(HashMap<String, String> hashMap) {
        ProgressDialog progressDialog = this.f633g;
        if (progressDialog != null) {
            try {
                progressDialog.dismiss();
                this.f633g = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Context context = this.f627a;
        if (context instanceof FeedbackActivity) {
            ((Activity) context).finish();
        }
        try {
            String str = hashMap.get("response");
            Message message = new Message();
            if (!StringUtil.isEmpty(str)) {
                if (new JSONObject(str).getInt("code") == 0) {
                    message.what = 20001;
                } else {
                    message.what = 20002;
                }
            } else {
                message.what = 20002;
            }
            this.f628b.sendMessage(message);
        } catch (Exception unused) {
            Message message2 = new Message();
            message2.what = 20002;
            this.f628b.sendMessage(message2);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0077, code lost:
    
        if (r1 != null) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0083, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0080, code lost:
    
        r1.disconnect();
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x007e, code lost:
    
        if (r1 == null) goto L28;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.util.HashMap<java.lang.String, java.lang.String> m318a() {
        /*
            r7 = this;
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            r1 = 0
            java.util.Map r2 = com.pgyersdk.p007a.C2001a.m101b()     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L7a
            java.lang.String r3 = "content"
            java.lang.String r4 = r7.f630d     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L7a
            r2.put(r3, r4)     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L7a
            java.lang.String r3 = "mail"
            java.lang.String r4 = r7.f635i     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L7a
            r2.put(r3, r4)     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L7a
            java.lang.String r3 = "moreParams"
            java.lang.String r4 = r7.f636j     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L7a
            r2.put(r3, r4)     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L7a
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L7a
            r3.<init>()     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L7a
            java.util.List<java.lang.String> r4 = r7.f631e     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L7a
            java.util.Iterator r4 = r4.iterator()     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L7a
        L2a:
            boolean r5 = r4.hasNext()     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L7a
            if (r5 == 0) goto L45
            java.lang.Object r5 = r4.next()     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L7a
            java.lang.String r5 = (java.lang.String) r5     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L7a
            java.io.File r6 = new java.io.File     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L7a
            r6.<init>(r5)     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L7a
            android.net.Uri r5 = android.net.Uri.fromFile(r6)     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L7a
            r3.add(r5)     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L7a
            goto L2a
        L43:
            r0 = move-exception
            goto L84
        L45:
            com.pgyersdk.f.d r4 = new com.pgyersdk.f.d     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L7a
            java.lang.String r5 = r7.f629c     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L7a
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L7a
            java.lang.String r5 = "POST"
            com.pgyersdk.f.d r4 = r4.m207a(r5)     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L7a
            android.content.Context r5 = r7.f627a     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L7a
            java.io.File r6 = r7.f632f     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L7a
            com.pgyersdk.f.d r2 = r4.m209a(r2, r5, r6, r3)     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L7a
            java.net.HttpURLConnection r1 = r2.m210a()     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L7a
            r1.connect()     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L7a
            java.lang.String r2 = "status"
            int r3 = r1.getResponseCode()     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L7a
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L7a
            r0.put(r2, r3)     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L7a
            java.lang.String r2 = "response"
            java.lang.String r3 = com.pgyersdk.p016f.C2045j.m234a(r1)     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L7a
            r0.put(r2, r3)     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L7a
            if (r1 == 0) goto L83
            goto L80
        L7a:
            r2 = move-exception
            r2.printStackTrace()     // Catch: java.lang.Throwable -> L43
            if (r1 == 0) goto L83
        L80:
            r1.disconnect()
        L83:
            return r0
        L84:
            if (r1 == 0) goto L89
            r1.disconnect()
        L89:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.pgyersdk.feedback.p017a.AsyncTaskC2067r.m318a():java.util.HashMap");
    }
}
