package com.kkkcut.e20j.pyger;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Build;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.pgyersdk.PgyerProvider;
import com.pgyersdk.feedback.p017a.AlertDialogBuilderC2062m;
import com.pgyersdk.feedback.p017a.C2050a;
import com.pgyersdk.feedback.p017a.C2051b;
import com.pgyersdk.p008b.FileManager;
import com.pgyersdk.p012c.Constants;
import com.pgyersdk.p012c.Strings;
import com.pgyersdk.utils.ConvertUtil;
import com.pgyersdk.utils.FileUtils;
import com.pgyersdk.utils.SharedPreferenesManager;
import com.pgyersdk.utils.StringUtil;
import com.pgyersdk.utils.Utils;
import java.io.File;

/* compiled from: FeedbackAd.java */
/* renamed from: com.pgyersdk.feedback.d */
/* loaded from: classes2.dex */
public class ViewOnClickListenerC2070d extends RelativeLayout implements View.OnClickListener {

    /* renamed from: a */
    private static String f640a = "#ffffff";

    /* renamed from: b */
    public static String f641b = "#2E2D2D";

    /* renamed from: c */
    public static String f642c = "#2E2D2D";

    /* renamed from: d */
    public static String f643d = "#272828";

    /* renamed from: e */
    public static String f644e;

    /* renamed from: A */
    ColorDrawable f645A;

    /* renamed from: f */
    private String f646f;

    /* renamed from: g */
    public C2051b f647g;

    /* renamed from: h */
    C2054e f648h;

    /* renamed from: i */
    C2050a f649i;

    /* renamed from: j */
    C2050a f650j;

    /* renamed from: k */
    C2050a f651k;

    /* renamed from: l */
    private C2050a f652l;

    /* renamed from: m */
    private C2050a f653m;

    /* renamed from: n */
    public View f654n;

    /* renamed from: o */
    private View f655o;

    /* renamed from: p */
    private Context f656p;

    /* renamed from: q */
    int f657q;

    /* renamed from: r */
    int f658r;

    /* renamed from: s */
    ObjectAnimator f659s;

    /* renamed from: t */
    ObjectAnimator f660t;

    /* renamed from: u */
    TranslateAnimation f661u;

    /* renamed from: v */
    TranslateAnimation f662v;

    /* renamed from: w */
    private boolean f663w;

    /* renamed from: x */
    private ImageView f664x;

    /* renamed from: y */
    private String f665y;

    /* renamed from: z */
    LinearLayout.LayoutParams f666z;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FeedbackAd.java */
    /* renamed from: com.pgyersdk.feedback.d$a */
    /* loaded from: classes2.dex */
    public static class a {

        /* renamed from: a */
        String f667a;

        /* renamed from: b */
        ImageView f668b;

        a() {
        }
    }

    public ViewOnClickListenerC2070d(Context context, String str) {
        super(context);
        this.f663w = false;
        this.f665y = "#ED3A3A";
        this.f656p = context;
        this.f646f = str;
        if (context.getResources().getConfiguration().orientation == 1) {
            m331b(this.f656p);
        } else {
            m332c(this.f656p);
        }
    }

    /* renamed from: b */
    private void m331b(Context context) {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setBackgroundColor(-1);
        linearLayout.setOrientation(1);
        linearLayout.setFitsSystemWindows(true);
        linearLayout.setClipToPadding(false);
        LinearLayout.LayoutParams defaultLayoutParams = getDefaultLayoutParams();
        View m333d = m333d(context);
        this.f655o = m333d;
        linearLayout.addView(m333d, defaultLayoutParams);
        this.f666z = getDefaultLayoutParams();
        LinearLayout linearLayout2 = new LinearLayout(context);
        linearLayout2.setGravity(17);
        this.f666z.setMargins(ConvertUtil.m195a(context, 40.0f), 0, ConvertUtil.m195a(context, 40.0f), 0);
        this.f666z.gravity = 17;
        C2051b c2051b = new C2051b(context);
        this.f647g = c2051b;
        c2051b.setScaleType(ImageView.ScaleType.FIT_XY);
        this.f657q = context.getResources().getDisplayMetrics().widthPixels;
        this.f658r = context.getResources().getDisplayMetrics().heightPixels;
        this.f655o.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        this.f666z.height = (this.f658r - this.f655o.getMeasuredHeight()) - ConvertUtil.m195a(context, 70.0f);
        LinearLayout.LayoutParams layoutParams = this.f666z;
        layoutParams.width = (layoutParams.height * this.f657q) / this.f658r;
        this.f647g.setImageBitmap(C2040e.m212a(context, this.f646f, this.f666z.width, this.f666z.height));
        linearLayout2.addView(this.f647g, this.f666z);
        linearLayout.addView(linearLayout2, getDefaultLayoutParams1());
        addView(linearLayout);
        int m195a = ConvertUtil.m195a(context, 30.0f);
        int m195a2 = ConvertUtil.m195a(context, 120.0f) + (m195a * 6);
        RelativeLayout.LayoutParams relativeLayoutParams = getRelativeLayoutParams();
        relativeLayoutParams.addRule(12);
        relativeLayoutParams.width = m195a2;
        relativeLayoutParams.height = ConvertUtil.m195a(context, 45.0f);
        relativeLayoutParams.setMargins(ConvertUtil.m195a(context, 5.0f), 0, 0, 0);
        C2054e m324a = m324a(context, m195a);
        this.f648h = m324a;
        addView(m324a, relativeLayoutParams);
        RelativeLayout.LayoutParams relativeLayoutParams2 = getRelativeLayoutParams();
        relativeLayoutParams2.addRule(12);
        LinearLayout linearLayout3 = new LinearLayout(context);
        linearLayout3.setLayoutParams(getDefaultLayoutParams());
        linearLayout3.setOrientation(1);
        LinearLayout.LayoutParams defaultLayoutParams2 = getDefaultLayoutParams();
        defaultLayoutParams2.height = ConvertUtil.m195a(context, 45.0f);
        defaultLayoutParams2.gravity = 16;
        linearLayout3.addView(m322a(context), defaultLayoutParams2);
        addView(linearLayout3, relativeLayoutParams2);
        this.f654n = new View(context);
        RelativeLayout.LayoutParams relativeLayoutParams3 = getRelativeLayoutParams();
        relativeLayoutParams3.height = this.f658r;
        relativeLayoutParams3.width = this.f657q;
        this.f654n.setBackgroundColor(Color.parseColor("#9f000000"));
        addView(this.f654n, relativeLayoutParams3);
        this.f654n.setVisibility(8);
        if (Build.VERSION.SDK_INT >= 11) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.f648h, "translationY", 0.0f, -(defaultLayoutParams2.height + ConvertUtil.m195a(context, 2.0f)));
            this.f659s = ofFloat;
            ofFloat.setDuration(300L);
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.f648h, "translationY", -(defaultLayoutParams2.height + ConvertUtil.m195a(context, 2.0f)), 0.0f);
            this.f660t = ofFloat2;
            ofFloat2.setDuration(300L);
            return;
        }
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, -ConvertUtil.m195a(context, 45.0f));
        this.f661u = translateAnimation;
        translateAnimation.setDuration(300L);
        this.f661u.setFillAfter(true);
        this.f661u.setFillEnabled(true);
        TranslateAnimation translateAnimation2 = new TranslateAnimation(0.0f, 0.0f, ConvertUtil.m195a(context, 45.0f), 0.0f);
        this.f662v = translateAnimation2;
        translateAnimation2.setDuration(300L);
        this.f662v.setFillAfter(true);
        this.f662v.setFillEnabled(true);
    }

    /* renamed from: c */
    private void m332c(Context context) {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setBackgroundColor(-1);
        linearLayout.setOrientation(1);
        linearLayout.setFitsSystemWindows(true);
        linearLayout.setClipToPadding(false);
        LinearLayout.LayoutParams defaultLayoutParams = getDefaultLayoutParams();
        defaultLayoutParams.width = context.getResources().getDisplayMetrics().widthPixels;
        View m333d = m333d(context);
        this.f655o = m333d;
        linearLayout.addView(m333d, defaultLayoutParams);
        this.f666z = getDefaultLayoutParams();
        LinearLayout linearLayout2 = new LinearLayout(context);
        linearLayout2.setGravity(17);
        this.f666z.setMargins(ConvertUtil.m195a(context, 40.0f), 0, ConvertUtil.m195a(context, 40.0f), 0);
        this.f666z.gravity = 49;
        C2051b c2051b = new C2051b(context);
        this.f647g = c2051b;
        c2051b.setScaleType(ImageView.ScaleType.FIT_XY);
        this.f657q = context.getResources().getDisplayMetrics().widthPixels;
        this.f658r = context.getResources().getDisplayMetrics().heightPixels;
        this.f655o.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        this.f666z.height = (this.f658r - this.f655o.getMeasuredHeight()) - ConvertUtil.m195a(context, 70.0f);
        LinearLayout.LayoutParams layoutParams = this.f666z;
        layoutParams.width = (layoutParams.height * this.f657q) / this.f658r;
        this.f647g.setImageBitmap(C2040e.m212a(context, this.f646f, this.f666z.width, this.f666z.height));
        linearLayout2.addView(this.f647g, this.f666z);
        linearLayout.addView(linearLayout2, getDefaultLayoutParams1());
        addView(linearLayout);
        int m195a = ConvertUtil.m195a(context, 30.0f);
        int m195a2 = ConvertUtil.m195a(context, 120.0f) + (m195a * 6);
        RelativeLayout.LayoutParams relativeLayoutParams = getRelativeLayoutParams();
        relativeLayoutParams.addRule(12);
        relativeLayoutParams.width = m195a2;
        relativeLayoutParams.height = ConvertUtil.m195a(context, 45.0f);
        relativeLayoutParams.setMargins(ConvertUtil.m195a(context, 5.0f), 0, 0, 0);
        C2054e m324a = m324a(context, m195a);
        this.f648h = m324a;
        addView(m324a, relativeLayoutParams);
        RelativeLayout.LayoutParams relativeLayoutParams2 = getRelativeLayoutParams();
        relativeLayoutParams2.addRule(12);
        LinearLayout linearLayout3 = new LinearLayout(context);
        linearLayout3.setLayoutParams(getDefaultLayoutParams());
        linearLayout3.setOrientation(1);
        LinearLayout.LayoutParams defaultLayoutParams2 = getDefaultLayoutParams();
        defaultLayoutParams2.height = ConvertUtil.m195a(context, 45.0f);
        defaultLayoutParams2.gravity = 16;
        linearLayout3.addView(m322a(context), defaultLayoutParams2);
        addView(linearLayout3, relativeLayoutParams2);
        this.f654n = new View(context);
        RelativeLayout.LayoutParams relativeLayoutParams3 = getRelativeLayoutParams();
        relativeLayoutParams3.height = this.f658r;
        relativeLayoutParams3.width = this.f657q;
        this.f654n.setBackgroundColor(Color.parseColor("#9f000000"));
        addView(this.f654n, relativeLayoutParams3);
        this.f654n.setVisibility(8);
        if (Build.VERSION.SDK_INT >= 11) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.f648h, "translationY", 0.0f, -(defaultLayoutParams2.height + ConvertUtil.m195a(context, 2.0f)));
            this.f659s = ofFloat;
            ofFloat.setDuration(300L);
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.f648h, "translationY", -(defaultLayoutParams2.height + ConvertUtil.m195a(context, 2.0f)), 0.0f);
            this.f660t = ofFloat2;
            ofFloat2.setDuration(300L);
            return;
        }
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, -ConvertUtil.m195a(context, 45.0f));
        this.f661u = translateAnimation;
        translateAnimation.setDuration(300L);
        this.f661u.setFillAfter(true);
        this.f661u.setFillEnabled(true);
        TranslateAnimation translateAnimation2 = new TranslateAnimation(0.0f, 0.0f, ConvertUtil.m195a(context, 45.0f), 0.0f);
        this.f662v = translateAnimation2;
        translateAnimation2.setDuration(300L);
        this.f662v.setFillAfter(true);
        this.f662v.setFillEnabled(true);
    }

    /* renamed from: d */
    private View m333d(Context context) {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setBackgroundColor(Color.parseColor(f641b));
        C2050a c2050a = new C2050a(context);
        this.f652l = c2050a;
        c2050a.setImageBitmap(C2040e.m213a(C2040e.m214a("iVBORw0KGgoAAAANSUhEUgAAADwAAAA8BAMAAADI0sRBAAAAIVBMVEUAAAD////////////////////////////////////////PIev5AAAACnRSTlMA90eSsOpoKxoI1urS1QAAAJpJREFUOMvtz7sJhEAAhGE54R7phRedYAEmJoaGpjZgCZZgCbYgWqgwBrPs8hcgOtHOLt/CZHcumU8btnKOnp/L3+XxG2LefH0u1uT3/OAHrnxvDtjcGLgxcOOEGxMXRi7MXJi5MHNhTLfhkzZrO6VYtR1xlTPXZubaLI5YnLE4YnPA4oDNAZsDNk8w8lcftnqM+RSWd3bnpNkB6m8e8+TpDLMAAAAASUVORK5CYII="), ConvertUtil.m195a(context, 40.0f), ConvertUtil.m195a(context, 40.0f)));
        LinearLayout.LayoutParams defaultLayoutParams = getDefaultLayoutParams();
        defaultLayoutParams.width = ConvertUtil.m195a(context, 50.0f);
        defaultLayoutParams.height = defaultLayoutParams.width;
        defaultLayoutParams.setMargins(0, 0, 0, 0);
        defaultLayoutParams.gravity = 16;
        this.f652l.setOnClickListener(this);
        linearLayout.addView(this.f652l, defaultLayoutParams);
        LinearLayout.LayoutParams defaultLayoutParams1 = getDefaultLayoutParams1();
        TextView textView = new TextView(context);
        textView.setTextSize(22.0f);
        textView.setText(Strings.m151a(1062));
        textView.setTextColor(Color.parseColor(f640a));
        textView.setPadding(30, 20, 0, 20);
        textView.setBackgroundColor(Color.parseColor(f641b));
        textView.setGravity(17);
        linearLayout.addView(textView, defaultLayoutParams1);
        C2050a c2050a2 = new C2050a(context);
        this.f653m = c2050a2;
        c2050a2.setImageBitmap(C2040e.m213a(C2040e.m214a("iVBORw0KGgoAAAANSUhEUgAAADwAAAA8CAMAAAANIilAAAAApVBMVEUAAAD///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////+4/eNVAAAANnRSTlMA++b07hUN+N6lcDsJ1s0c8OqcSyjRwryqiXY0LAPbysW1sJiWaSQGBIN9ZWJgW1BEMBjioJBbXN1rAAABxUlEQVRIx+2Tx3LbQBAFZwmQyBBAMeecaSrY/f+f5tUaBcgHkQAvLqvYB/LNoWtRU/PkwYPvwmhG5041bDqg7nOPKcoiuUcdxhDZEFdX657C9iNqeJVd30b9bHSpdfErqkEE05EssVouw0rq20JRO4usUc875YwrqOO9hbMKRV4UAwmYVHCfXehsdGjZNEUGzEurT114v4im4ZrT6rEvqW77FlZ/Z3KMG+q/Ns/l3B/vMH8Sg0dqUkKjjNrqwOQlGw44gWg21MpUYOmQHPJvUJxNODG77Z5SVK+eX7VFXwwrVqUqEBRHkrLI4ozzdbVhKlDMYUS8y3KNTYkKSIFuQz2LDZKbFXiVT6ywRvm50b5yUAtFepKCP23Ihz29r2UHlqEUZG3ImevhS2ywm8VKsjYUTAiuLPowATU9msrmbcgZO2on1wgW1sfzLTNMTRtyhrhyg8YgAmLdgz62/i3wS5U56Fn2WN4cLvIZj76UIdSb+UVX/iLmImVxP4q/HklOQr20rCy9AId4vc1OiLS0W6emhZ4FiTcyrWZWSdaEfgS0/a3eQbOabHj1zPNtjlVlw9ZvA7Qqy/nz7lKqywX/g/zgwb/nN0WyL+YC26DcAAAAAElFTkSuQmCC"), ConvertUtil.m195a(context, 40.0f), ConvertUtil.m195a(context, 40.0f)));
        LinearLayout.LayoutParams defaultLayoutParams2 = getDefaultLayoutParams();
        defaultLayoutParams2.width = ConvertUtil.m195a(context, 50.0f);
        defaultLayoutParams2.height = defaultLayoutParams.width;
        defaultLayoutParams2.setMargins(0, 0, 0, 0);
        defaultLayoutParams2.gravity = 16;
        this.f653m.setOnClickListener(this);
        linearLayout.addView(this.f653m, defaultLayoutParams2);
        return linearLayout;
    }

    private FrameLayout.LayoutParams getDefaultFrameLayoutParams() {
        return new FrameLayout.LayoutParams(-1, -2);
    }

    private LinearLayout.LayoutParams getDefaultLayoutParams() {
        return new LinearLayout.LayoutParams(-1, -2);
    }

    private LinearLayout.LayoutParams getDefaultLayoutParams1() {
        return new LinearLayout.LayoutParams(-1, -2, 1.0f);
    }

    private RelativeLayout.LayoutParams getRelativeLayoutParams() {
        return new RelativeLayout.LayoutParams(-1, -2);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.equals(this.f649i)) {
            if (this.f663w) {
                ObjectAnimator objectAnimator = this.f660t;
                if (objectAnimator != null) {
                    objectAnimator.start();
                } else {
                    TranslateAnimation translateAnimation = this.f662v;
                    if (translateAnimation != null) {
                        this.f648h.setAnimation(translateAnimation);
                        this.f662v.start();
                    }
                }
            } else {
                ObjectAnimator objectAnimator2 = this.f659s;
                if (objectAnimator2 != null) {
                    objectAnimator2.start();
                } else {
                    TranslateAnimation translateAnimation2 = this.f661u;
                    if (translateAnimation2 != null) {
                        this.f648h.startAnimation(translateAnimation2);
                    }
                }
            }
            this.f663w = !this.f663w;
        }
        if (view.equals(this.f651k)) {
            this.f647g.m261a();
        }
        if (view.equals(this.f650j)) {
            this.f654n.setVisibility(0);
            m325a();
        }
        if (view.equals(this.f652l)) {
            FileManager.m132a(f644e);
            if (this.f656p instanceof Activity) {
                C2051b c2051b = this.f647g;
                if (c2051b != null) {
                    c2051b.m262b();
                }
                ((Activity) this.f656p).finish();
            }
        }
        if (view.equals(this.f653m)) {
            f644e = FileUtils.m196a().m206e(this.f656p);
            C2040e.m215a(this.f647g.m263c(), f644e);
            String m222a = SharedPreferenesManager.m222a(getContext(), "selfmail");
            String m222a2 = SharedPreferenesManager.m222a(getContext(), "feedback_des");
            File file = StringUtil.isEmpty(SharedPreferenesManager.m222a(getContext(), "voicefile")) ? null : new File(SharedPreferenesManager.m222a(getContext(), "voicefile"));
            if (StringUtil.isEmpty(m222a)) {
                m325a();
                Toast.makeText(PgyerProvider.f436a, Strings.m151a(1063), 0).show();
            } else if (!Utils.m248a(m222a)) {
                m325a();
                Toast.makeText(PgyerProvider.f436a, Strings.m151a(1046), 0).show();
            } else {
                PgyerFeedbackManager.getInstance().m257b().m349a(m222a, m222a2, f644e, file, null);
            }
        }
    }

    /* renamed from: a */
    private View m322a(Context context) {
        LinearLayout.LayoutParams defaultLayoutParams1 = getDefaultLayoutParams1();
        defaultLayoutParams1.gravity = 16;
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(0);
        linearLayout.setBackgroundColor(Color.parseColor(f642c));
        LinearLayout linearLayout2 = new LinearLayout(context);
        linearLayout2.setOrientation(1);
        LinearLayout.LayoutParams defaultLayoutParams = getDefaultLayoutParams();
        defaultLayoutParams.gravity = 17;
        C2050a c2050a = new C2050a(context);
        this.f649i = c2050a;
        c2050a.setImageBitmap(C2040e.m213a(C2040e.m214a("iVBORw0KGgoAAAANSUhEUgAAADwAAAA8CAMAAAANIilAAAAAq1BMVEUAAAD////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////Nr6iZAAAAOHRSTlMAMlEu/fKAVlPa0GJbLBv69+uRXjs2BwPVzLmklXjdoZtybEQ+JRgRtnVpKSEN5sjEv7CNh3sBqutKTB8AAAHpSURBVEjH7VbZdqowFAUUEESRQWYUB+rQ1jq2+/+/7ILQhtV7APvQp7IfkpBkrzNyTrgOHf4W3nkrCtSZ4m9NrfdD7lTy+iihqzH/ONPZewC8+c0ShOEtVACow/fHuMMDkMgbtrGR58BMe4D6fIQYl0yGJ6mP4KWNKwDmK3XwNMdk3aIyIHx99E6Rk8/jwt0aMGziDmCM2JcHLLLJFrEvQgA/H0c0dwQxvS/2Ozcbj4WoBDiU52OOkxGQXFeBfV8sADNXN9lxn5KZbiJt+QLXYhECb2w7t5n5s09r7YiGW94++d+uMG5NsmmQuGYMMKnh9o4YN3NliDmXtljJp6VydWvlZsZPZYc4+0CSmwvArrM3d9wJVJa+4ZaNGwP9lJar33U2QanuYXn/MSS70V6JVEzFssnPYhnsC3hS7biey3LjTNq8xSUbW3PDOFDBkKDWxpflxjNCsoQEsHh5FyXS6j9fseQ2sSIFpDpKfDjfc+MTr/qMozG6RMtVL7XP2FaKcJEbJUK01sEz1l+iJgZf9ZzPtUFgIV9VyyGPybiVfGVeqVo/1bFu7xiGQcXSYpW1UesFUbdD6GuuHTtYVMfYvnAPIO0jGmwqTI31qnYIPgAljPMuGc+LLun+Yn+mXwZB/jLo0KHDD/APMKg3IQLxv2kAAAAASUVORK5CYII="), ConvertUtil.m195a(context, 40.0f), ConvertUtil.m195a(context, 40.0f)));
        this.f649i.setOnClickListener(this);
        linearLayout2.addView(this.f649i, defaultLayoutParams);
        this.f645A = new ColorDrawable(Color.parseColor(this.f665y));
        LinearLayout.LayoutParams defaultLayoutParams2 = getDefaultLayoutParams();
        defaultLayoutParams2.setMargins(0, -ConvertUtil.m195a(context, 10.0f), 0, 0);
        defaultLayoutParams2.gravity = 1;
        defaultLayoutParams2.height = ConvertUtil.m195a(context, 4.0f);
        defaultLayoutParams2.width = ConvertUtil.m195a(context, 20.0f);
        ImageView imageView = new ImageView(context);
        this.f664x = imageView;
        imageView.setImageDrawable(this.f645A);
        this.f664x.setBackgroundColor(-1);
        this.f664x.setPadding(1, 1, 1, 1);
        linearLayout2.addView(this.f664x, defaultLayoutParams2);
        LinearLayout.LayoutParams defaultLayoutParams12 = getDefaultLayoutParams1();
        defaultLayoutParams12.gravity = 16;
        linearLayout.addView(linearLayout2, defaultLayoutParams12);
        LinearLayout linearLayout3 = new LinearLayout(context);
        C2050a c2050a2 = new C2050a(context);
        this.f650j = c2050a2;
        c2050a2.setImageBitmap(C2040e.m213a(C2040e.m214a(Constants.f477o), ConvertUtil.m195a(context, 40.0f), ConvertUtil.m195a(context, 40.0f)));
        this.f650j.setOnClickListener(this);
        linearLayout3.addView(this.f650j, defaultLayoutParams);
        linearLayout.addView(linearLayout3, defaultLayoutParams1);
        LinearLayout linearLayout4 = new LinearLayout(context);
        C2050a c2050a3 = new C2050a(context);
        this.f651k = c2050a3;
        c2050a3.setImageBitmap(C2040e.m213a(C2040e.m214a("iVBORw0KGgoAAAANSUhEUgAAADwAAAA8CAMAAAANIilAAAAAq1BMVEUAAAD////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////Nr6iZAAAAOHRSTlMAY1IE3JsX+uzo18GRVk80Eb25qH5oYFouDQj1x7CsiHNDJRry4tLNtqWgjEk9OCEcAe6FeW0pMjZ5GTkAAAF+SURBVEjH7ZXHkoJQEEVbJYNIUBFUkjln5/7/lw1VDkUpQ5i3mQ1n10UdoLr7vkcNDf+FPomY3c5OdFjdkQIMGN0QMNFiUmcW5LjHJnfn2Aiks8gGj3GXiNoM8mEB80xsch+4E73k7t/U5wTLHqVyaLiX+m4L0DppMUSCrGqtU60B+ZD0rPziN1OVQwIXzqrc/Ri+m+/9qGUBCNzSVQ6K19EYyJDKumfCFKgQLwRuVIgF9VwalBX44t++AQ8qw0JQ0rA5pgZlXDx6ZwW92BZsXNtZGSkcN/G1YZxO/oyxQcUMAc1LCwdzTkSCvIvSxzyVEKtQsvUckif0BlsZWL4GpeBUFYx+Kjs/vTzYwOpIRDoCKuW4gHr6jGTMv94pSVSOy0N0cnk+KJh2iEePKmhfYeeOIc/G5HJEvzIjwhrSM3cAasm3x+s9VfIA1E+Ztthw3J2qGS1/SZkJcaHVvG5yKXRlSFuqhdChTyKINjFjYc0ujxSf2HHa1NDQ0PDONzsAKUS0GwsiAAAAAElFTkSuQmCC"), ConvertUtil.m195a(context, 40.0f), ConvertUtil.m195a(context, 40.0f)));
        this.f651k.setOnClickListener(this);
        linearLayout4.addView(this.f651k, defaultLayoutParams);
        linearLayout.addView(linearLayout4, defaultLayoutParams1);
        return linearLayout;
    }

    /* renamed from: a */
    private C2054e m324a(Context context, int i) {
        C2054e c2054e = new C2054e(context, Color.parseColor(f643d));
        this.f648h = c2054e;
        c2054e.setGravity(16);
        this.f648h.setPadding(0, 0, 0, ConvertUtil.m195a(context, 5.0f));
        LinearLayout.LayoutParams defaultLayoutParams1 = getDefaultLayoutParams1();
        defaultLayoutParams1.setMargins(ConvertUtil.m195a(context, 10.0f), 0, ConvertUtil.m195a(context, 10.0f), 0);
        defaultLayoutParams1.gravity = 16;
        defaultLayoutParams1.height = ConvertUtil.m195a(context, 30.0f);
        for (int i2 = 0; i2 < 6; i2++) {
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            String str = "#ED3A3A";
            if (i2 != 0) {
                if (i2 == 1) {
                    str = "#006AA1";
                } else if (i2 == 2) {
                    str = "#5CC500";
                } else if (i2 == 3) {
                    str = "#F1B600";
                } else if (i2 == 4) {
                    str = "#F77A00";
                } else if (i2 == 5) {
                    str = "#E5E5E5";
                }
            }
            paint.setColor(Color.parseColor(str));
            a aVar = new a();
            aVar.f667a = str;
            this.f648h.addView(m323a(context, aVar, i, paint), defaultLayoutParams1);
        }
        return this.f648h;
    }

    /* renamed from: a */
    private View m323a(Context context, a aVar, int i, Paint paint) {
        ShapeDrawable shapeDrawable = new ShapeDrawable(new C2049a(this, paint, context, i));
        FrameLayout frameLayout = new FrameLayout(context);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(C2040e.m214a("iVBORw0KGgoAAAANSUhEUgAAADwAAAA8CAMAAAANIilAAAAAV1BMVEUAAAD///////////////////////////////////////////////////////////////////////////////////////////////////////////////+ORg7oAAAAHHRSTlMA+8MbFQ/vP8molVVIBtB88+S+QrSzrqVzcN99O8Pg/gAAASJJREFUSMe111mOgzAQBNC2wWbfIQtT9z/nSBESIiFgupT3X5J3d8s+VzZZ2rVA26VZUzoJFj2swYaxjygomkwGO8yUnEctvrLH8eiOQ/eDwRceJ3wh+6ocAfJqL+t6BLHxZzZOEaj+SLsawWr3Nt8eF/Tbeee4JN/sES4q1mzkcZFfT8sNl91kMUBhWMIWCna5SFBJXuEJKtPrXBqomFhEnlB6rsulWjJnoGSclFArpYHan2RQyySFWiod1DppodYKCEzYMMP2zILN1FZRh4Q6ntTFoK4k8xhQzxD7AMoIlZF69JnvhvromC+W+tyZsoIpaIhSylayEdeAqgRc0oosUbgSJTNRrBNtgqZBGeRMMu63RmPy66ZsbQdnb4yfD9rBf/ksMN7cBHqMAAAAAElFTkSuQmCC"));
        if (Build.VERSION.SDK_INT >= 16) {
            frameLayout.setBackground(bitmapDrawable);
        }
        TextView textView = new TextView(context);
        textView.setBackgroundDrawable(shapeDrawable);
        textView.setClickable(true);
        textView.setTag(aVar);
        textView.setOnClickListener(new ViewOnClickListenerC2068b(this));
        textView.setGravity(17);
        frameLayout.addView(textView);
        ImageView imageView = new ImageView(context);
        imageView.setImageBitmap(C2040e.m214a("iVBORw0KGgoAAAANSUhEUgAAADwAAAA8CAYAAAA6/NlyAAAAAXNSR0IArs4c6QAA ABxpRE9UAAAAAgAAAAAAAAAeAAAAKAAAAB4AAAAeAAABamLW3/kAAAE2SURBVGgF 1JlbC8IwDIWnvqgPCuJt4OX//8qZ8xAoQ9xMs5y0EDqVtvly2tR2XbdcWUvXO7Gj 2FnsLvYQe4m9xbphGMIN43qWjXR2EAMcoH5ZOCwC7FW20tF1AnAM3yQwQOeoOYbF 56aAMXWxLr+BzP2uGeC9gD4rYZtQeCWQJwdQnQGpFcYWc3OETa0w1mvvDJsWGMou AZsSGGvWexrr+k0J7JmgSlB9TpW0sPWoY0vVaYCRpDz22alApQG+BKiLYKQAxn/j KWW8fk8BbD0IWIJAB45UN8WU/vc8a1G1bENVGJm5dCbimQqMa5kIyHIMKnBkslJo GjAOCOpEZE0DxlVqJKiORQPGvbE6EVnTgGsv46xBogEzEhaCRAPG6w+rSjXtaMD6 rqfGeUtbGrDFWY82FOAPAAAA//+Fw8fAAAAA9UlEQVTl2ckOAjEIgGFcDupBE+O+ vv9TVn7j3F1aoNKETK9fmMLQERG5O4WUUsxDrXLLBr5kAx+ygTfZwKts4Hk28Dgb WL3iUbjMezB9f1hL3Vh/gLiCJ9nAZHpnjHbNMOBZNjBoy+LlnmHAllkOAQa91bCo 2GHAVOyrAToMWK2yyAYGvW6MDpVhwCONfUN0ODBoBotTI3RIMGiK2LEBOiwYNJmu /XqHBoPmTNcsZOHBoFm0rBp9uhswaM71r5d/XYFBs/j2/nbg6BL8VL/gn87TXYMH +FQ3XBe9k/W/AA9wnrQyroC592YCo5efNSh4/M9yAT8APkf75B/DphwAAAAASUVO RK5CYII="));
        ImageView imageView2 = new ImageView(context);
        FrameLayout.LayoutParams defaultFrameLayoutParams = getDefaultFrameLayoutParams();
        defaultFrameLayoutParams.width = ConvertUtil.m195a(context, 10.0f);
        defaultFrameLayoutParams.height = defaultFrameLayoutParams.width;
        defaultFrameLayoutParams.gravity = 17;
        imageView2.setImageBitmap(C2040e.m214a("iVBORw0KGgoAAAANSUhEUgAAACQAAAAcCAYAAAAJKR1YAAAAAXNSR0IArs4c6QAA AAlwSFlzAAAWJQAAFiUBSVIk8AAAABxpRE9UAAAAAgAAAAAAAAAOAAAAKAAAAA4A AAAOAAABhhAbPSQAAAFSSURBVFgJvJU9SgRBEIUnEhVFEQTBQDDxFN7BQMRANhED MRENxMTbGBgaiCAGIiIGipFgIhgIHmAx8f97sE962t6ZRpwJHjXVVfXqg53eKV66 3aJFDbBrHy3229k2zCEgX+gdLaeg2gIaYvkxEowlqA4qMZSSuPhP+TA+p8ggYfzg fC3c0zTQKMvO+8AY7JP6hqGaBBpjyWUNTAi1LaimgCYwv86EMZTibhXQAg0HSFe1 qi+uTdJ/i8JFuc97sZnzJQxfe6ZHxEHkWlWcou+uN5cL4b4d7UiZr1DQlXSj4gnS bUn1+2ya+j0K53Ke9VJv2dtmjqsUYhibnlEb8WAUZ8gfkHtzY+mGydMgiutIDVVm F9R1e8K5WfLHmrmU56//IPnaeJOkDsamV/SOaxjNoSfkWm7Ur9BB3v8T9aCXKdfI fTfMzKPnP8y+MZP8jnFefAMAAP//TmVJhgAAARVJREFUvZO9agJBFIV9DMFCsBGs RAkWEcRCBLEQi1RWaaxSWvl2PoWNYBEIBILKKv4gmu8WC7PLnXVm17X42Jk7955z mGUKhyCYw/1FnPEZQ8FGeDB7QaATHkNbkLAeBpLvF9wgj9s6oNsH009dx4vTHELt 0ey6hJGeeCDZf8IVnnFTO3TaoPmoNbWIwASyhtqi0fIJI722QFL/gAukuak/5pqQ pK+eqUVDaMRanqpPqF/664bGI4/IeWRjERlQP4JLqB/6ahYdF6/EX2YK9DCRp5sU 6pvzapYwMmuaPlp3GAhAC7WmXhHBrPgKvGMor8cMtWJfzhoknPcNJP1vsAEJtYQS pNFRZ9Sig0GDngUUHXq9PP4B62fQcqx0J7wAAAAASUVORK5CYII="));
        imageView2.setVisibility(8);
        ((a) textView.getTag()).f668b = imageView2;
        if (this.f665y.equals(((a) textView.getTag()).f667a)) {
            imageView2.setVisibility(0);
        }
        frameLayout.addView(imageView);
        frameLayout.addView(imageView2, defaultFrameLayoutParams);
        return frameLayout;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m326a(View view) {
        for (int i = 0; i < this.f648h.getChildCount(); i++) {
            FrameLayout frameLayout = (FrameLayout) this.f648h.getChildAt(i);
            for (int i2 = 0; i2 < frameLayout.getChildCount(); i2++) {
                View childAt = frameLayout.getChildAt(i2);
                if ((childAt instanceof TextView) && (childAt.getTag() instanceof a)) {
                    a aVar = (a) childAt.getTag();
                    if (childAt.equals(view)) {
                        aVar.f668b.setVisibility(0);
                        this.f665y = aVar.f667a;
                        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor(this.f665y));
                        this.f645A = colorDrawable;
                        this.f664x.setImageDrawable(colorDrawable);
                    } else {
                        aVar.f668b.setVisibility(8);
                    }
                }
            }
        }
    }

    /* renamed from: a */
    private void m325a() {
        AlertDialogBuilderC2062m cancelable = new AlertDialogBuilderC2062m(this.f656p).m304a(true).setCancelable(true);
        AlertDialog create = cancelable.create();
        create.setOnDismissListener(new DialogInterfaceOnDismissListenerC2069c(this, cancelable));
        create.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m327a(AlertDialogBuilderC2062m alertDialogBuilderC2062m) {
        SharedPreferenesManager.m224a("selfmail", alertDialogBuilderC2062m.m308d().getText().toString().trim());
        SharedPreferenesManager.m224a("feedback_des", alertDialogBuilderC2062m.m307c().getText().toString().trim());
    }
}
