package com.pgyersdk.feedback;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import com.pgyersdk.PgyerActivityManager;
import com.pgyersdk.PgyerProvider;
import com.pgyersdk.feedback.PgyerFeedbackManager;
import com.pgyersdk.feedback.p017a.AlertDialogBuilderC2062m;
import com.pgyersdk.feedback.p017a.AsyncTaskC2067r;
import com.pgyersdk.p008b.C2018d;
import com.pgyersdk.p008b.C2019e;
import com.pgyersdk.p012c.C2023b;
import com.pgyersdk.p016f.C2036a;
import com.pgyersdk.p016f.C2043h;
import com.pgyersdk.p016f.C2046k;
import com.pgyersdk.p016f.C2047l;
import com.pgyersdk.p016f.C2048m;
import java.io.File;
import java.util.ArrayList;
import net.sqlcipher.database.SQLiteDatabase;

/* compiled from: PgyerFeedbackManagerDelegate.java */
/* renamed from: com.pgyersdk.feedback.k */
/* loaded from: classes2.dex */
public class C2077k {

    /* renamed from: a */
    private static String f677a = "";

    /* renamed from: b */
    private static File f678b;

    /* renamed from: c */
    private static Handler f679c;

    /* renamed from: d */
    private C2079m f680d;

    /* renamed from: e */
    private a f681e;

    /* renamed from: f */
    private PgyerFeedbackManager.TYPE f682f;

    /* renamed from: g */
    private String f683g;

    /* compiled from: PgyerFeedbackManagerDelegate.java */
    /* renamed from: com.pgyersdk.feedback.k$a */
    /* loaded from: classes2.dex */
    public interface a {
        /* renamed from: a */
        void mo354a();

        /* renamed from: b */
        void mo355b();
    }

    public C2077k(int i, PgyerFeedbackManager.TYPE type, String str) {
        this.f680d = new C2079m(i);
        this.f682f = type;
        this.f683g = str;
        m343h();
    }

    /* renamed from: g */
    public void m342g() {
        String str = f677a;
        if (str != null) {
            C2019e.m132a(str);
        }
        File file = f678b;
        if (file != null) {
            C2019e.m132a(file.getAbsolutePath());
        }
        C2043h.m224a("feedback_des", "");
        C2043h.m224a("voicefile", "");
        C2043h.m224a("voiceTime", "");
    }

    /* renamed from: h */
    private void m343h() {
        f679c = new HandlerC2076j(this, Looper.getMainLooper());
    }

    /* renamed from: i */
    private void m344i() {
        Intent intent = new Intent(PgyerProvider.f436a, (Class<?>) FeedbackActivity.class);
        intent.setFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
        intent.putExtra("imgFile", f677a);
        PgyerProvider.f436a.startActivity(intent);
    }

    /* renamed from: j */
    private void m345j() {
        AlertDialogBuilderC2062m cancelable = new AlertDialogBuilderC2062m(PgyerActivityManager.getInstance().getCurrentActivity()).m304a(false).setCancelable(false);
        cancelable.setPositiveButton(C2023b.m151a(1048), new DialogInterfaceOnClickListenerC2073g(this, cancelable));
        cancelable.setNegativeButton(C2023b.m151a(1049), new DialogInterfaceOnClickListenerC2074h(this, cancelable));
        AlertDialog create = cancelable.create();
        create.setOnDismissListener(new DialogInterfaceOnDismissListenerC2075i(this, cancelable));
        create.show();
    }

    /* renamed from: k */
    public void m346k() {
        if (this.f682f == PgyerFeedbackManager.TYPE.DIALOG_TYPE) {
            m345j();
        } else {
            m344i();
        }
    }

    /* renamed from: l */
    private void m347l() {
        C2018d.m127a(PgyerActivityManager.getInstance().getCurrentActivity(), new C2072f(this));
    }

    /* renamed from: c */
    public void m350c() {
        a aVar = this.f681e;
        if (aVar != null) {
            aVar.mo355b();
        }
    }

    /* renamed from: d */
    void m351d() {
        a aVar = this.f681e;
        if (aVar != null) {
            aVar.mo354a();
        }
    }

    /* renamed from: e */
    public void m352e() {
        if (PgyerActivityManager.getInstance().getCurrentActivity() == null) {
            return;
        }
        m351d();
        if (C2047l.m244e()) {
            m347l();
        } else {
            m350c();
        }
    }

    /* renamed from: f */
    public void m353f() {
        this.f680d.m364b();
    }

    public C2077k(PgyerFeedbackManager.TYPE type, String str) {
        this.f682f = type;
        this.f683g = str;
        m343h();
    }

    /* renamed from: a */
    public void m337a(AlertDialogBuilderC2062m alertDialogBuilderC2062m) {
        C2043h.m224a("selfmail", alertDialogBuilderC2062m.m308d().getText().toString().trim());
        C2043h.m224a("feedback_des", alertDialogBuilderC2062m.m307c().getText().toString().trim());
    }

    /* renamed from: a */
    public void m349a(String str, String str2, String str3, File file, Boolean bool) {
        if (C2048m.m249b()) {
            f678b = file;
            f677a = str3;
            if (C2047l.m238b()) {
                if (C2046k.m235a(str)) {
                    Toast.makeText(PgyerProvider.f436a, C2023b.m151a(1063), 0).show();
                    return;
                }
                if (!C2048m.m248a(str)) {
                    Toast.makeText(PgyerProvider.f436a, C2023b.m151a(1046), 0).show();
                    return;
                }
                ArrayList arrayList = new ArrayList();
                if (C2046k.m235a(f677a)) {
                    return;
                }
                if (this.f682f == PgyerFeedbackManager.TYPE.DIALOG_TYPE) {
                    if (bool.booleanValue()) {
                        arrayList.add(f677a);
                    }
                } else {
                    arrayList.add(f677a);
                }
                C2043h.m223a(PgyerProvider.f436a, "selfmail", str);
                AsyncTaskC2067r asyncTaskC2067r = new AsyncTaskC2067r(PgyerActivityManager.getInstance().getCurrentActivity(), str, "http://www.pgyer.com/apiv1/feedback/add", str2, arrayList, f678b, f679c, this.f683g);
                asyncTaskC2067r.m321a(true);
                C2036a.m194a(asyncTaskC2067r);
            }
        }
    }

    /* renamed from: a */
    public void m348a(a aVar) {
        this.f681e = aVar;
    }
}
