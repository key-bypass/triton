package com.pgyersdk.feedback;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import com.pgyersdk.feedback.p017a.C2050a;
import com.pgyersdk.p008b.FileManager;
import com.pgyersdk.p014e.p015a.SystemBarTintManager;
import com.pgyersdk.utils.OrientatingUtils;
import java.io.File;

/* loaded from: classes2.dex */
public class FeedbackActivity extends Activity {

    /* renamed from: a */
    private static boolean f543a = false;

    /* renamed from: b */
    private ViewOnClickListenerC2070d f544b;

    /* renamed from: c */
    private String f545c;

    /* renamed from: a */
    private void m254a(boolean z) {
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        if (z) {
            attributes.flags |= WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        } else {
            attributes.flags &= ~WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        }
        window.setAttributes(attributes);
    }

    public static void setBarBackgroundColor(String str) {
        ViewOnClickListenerC2070d.f642c = str;
        ViewOnClickListenerC2070d.f641b = str;
    }

    public static void setBarButtonPressedColor(String str) {
        C2050a.f552a = str;
    }

    public static void setBarImmersive(Boolean bool) {
        f543a = bool;
    }

    public static void setColorPickerBackgroundColor(String str) {
        ViewOnClickListenerC2070d.f643d = str;
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        m253a();
    }

    @Override
    protected void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        OrientatingUtils.m221a(this);
        super.onCreate(bundle);
        if (f543a) {
            m254a(true);
            SystemBarTintManager systemBarTintManager = new SystemBarTintManager(this);
            systemBarTintManager.m182a(true);
            systemBarTintManager.m181a(Color.parseColor(ViewOnClickListenerC2070d.f641b));
        }
        this.f545c = getIntent().getStringExtra("imgFile");
        ViewOnClickListenerC2070d viewOnClickListenerC2070d = new ViewOnClickListenerC2070d(this, this.f545c);
        this.f544b = viewOnClickListenerC2070d;
        setContentView(viewOnClickListenerC2070d);
    }

    /* renamed from: a */
    private void m253a() {
        this.f544b.f647g.m262b();
        this.f544b.f654n = null;
        this.f544b = null;
        FileManager.m131a(new File(this.f545c));
        PgyerFeedbackManager.getInstance().m257b().m350c();
    }
}
