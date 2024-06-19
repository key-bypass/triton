package com.pgyersdk.feedback.p017a;

import android.R;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.pgyersdk.p008b.C2019e;
import com.pgyersdk.p012c.C2022a;
import com.pgyersdk.p012c.C2023b;
import com.pgyersdk.p016f.C2037b;
import com.pgyersdk.p016f.C2038c;
import com.pgyersdk.p016f.C2041f;
import com.pgyersdk.p016f.C2043h;
import com.pgyersdk.p016f.C2046k;
import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/* compiled from: PgyerDialogBuilder.java */
/* renamed from: com.pgyersdk.feedback.a.m */
/* loaded from: classes2.dex */
public class AlertDialogBuilderC2062m extends AlertDialog.Builder implements View.OnClickListener {

    /* renamed from: a */
    private static String f579a = "#ffffff";

    /* renamed from: b */
    private static String f580b = "#2E2D2D";

    /* renamed from: c */
    private static String f581c = "#56bc94";

    /* renamed from: d */
    private static String f582d = "#cccccc";

    /* renamed from: A */
    private boolean f583A;

    /* renamed from: B */
    private int f584B;

    /* renamed from: C */
    private int f585C;

    /* renamed from: D */
    private Context f586D;

    /* renamed from: E */
    private Handler f587E;

    /* renamed from: F */
    View.OnTouchListener f588F;

    /* renamed from: G */
    View.OnTouchListener f589G;

    /* renamed from: e */
    private TextView f590e;

    /* renamed from: f */
    private EditText f591f;

    /* renamed from: g */
    private EditText f592g;

    /* renamed from: h */
    private CheckBox f593h;

    /* renamed from: i */
    private C2052c f594i;

    /* renamed from: j */
    C2066q f595j;

    /* renamed from: k */
    private LinearLayout f596k;

    /* renamed from: l */
    private C2055f f597l;

    /* renamed from: m */
    private LinearLayout f598m;

    /* renamed from: n */
    private int f599n;

    /* renamed from: o */
    private MediaRecorder f600o;

    /* renamed from: p */
    private MediaPlayer f601p;

    /* renamed from: q */
    public File f602q;

    /* renamed from: r */
    private long f603r;

    /* renamed from: s */
    private long f604s;

    /* renamed from: t */
    private long f605t;

    /* renamed from: u */
    private boolean f606u;

    /* renamed from: v */
    PopupWindow f607v;

    /* renamed from: w */
    C2063n f608w;

    /* renamed from: x */
    Timer f609x;

    /* renamed from: y */
    TimerTask f610y;

    /* renamed from: z */
    private int f611z;

    public AlertDialogBuilderC2062m(Context context) {
        this(context, 3);
    }

    /* renamed from: l */
    private void m297l() {
        try {
            File file = new File(C2038c.m196a().m205d(this.f586D));
            if (!file.exists()) {
                file.mkdir();
            }
            if (this.f600o == null) {
                this.f600o = new MediaRecorder();
            }
            this.f600o.setAudioSource(1);
            this.f600o.setOutputFormat(2);
            this.f600o.setAudioEncoder(3);
            this.f600o.setMaxDuration(this.f599n);
            File createTempFile = File.createTempFile("recorder_", ".wav", file);
            this.f602q = createTempFile;
            this.f600o.setOutputFile(createTempFile.getAbsolutePath());
        } catch (Exception unused) {
        }
    }

    /* renamed from: m */
    private void m298m() {
        this.f608w = new C2063n(this.f586D);
        PopupWindow popupWindow = new PopupWindow(this.f608w);
        this.f607v = popupWindow;
        popupWindow.setWidth(C2037b.m195a(this.f586D, 80.0f));
        this.f607v.setHeight(C2037b.m195a(this.f586D, 80.0f));
        if (this.f586D.getResources().getConfiguration().orientation % 2 == 1) {
            this.f607v.showAtLocation(this.f591f, 48, 0, C2037b.m195a(this.f586D, 115.0f));
        } else {
            this.f607v.showAtLocation(this.f591f, 48, 0, C2037b.m195a(this.f586D, 70.0f));
        }
        m303r();
    }

    /* renamed from: n */
    private void m299n() {
        this.f587E.removeMessages(20005);
        PopupWindow popupWindow = this.f607v;
        if (popupWindow == null || !popupWindow.isShowing()) {
            return;
        }
        this.f607v.dismiss();
    }

    /* renamed from: o */
    private void m300o() {
        if (!C2046k.m235a(C2043h.m222a(this.f586D, "selfmail"))) {
            this.f592g.setText(C2043h.m222a(this.f586D, "selfmail"));
        }
        if (!C2046k.m235a(C2043h.m222a(this.f586D, "feedback_des"))) {
            this.f591f.setText(C2043h.m222a(this.f586D, "feedback_des"));
        }
        if (C2046k.m235a(C2043h.m222a(this.f586D, "voicefile"))) {
            return;
        }
        File file = new File(C2043h.m222a(this.f586D, "voicefile"));
        this.f602q = file;
        if (file.exists()) {
            this.f594i.setVisibility(8);
            this.f596k.setVisibility(0);
            this.f595j.setText(C2043h.m222a(this.f586D, "voiceTime"));
        } else {
            this.f602q = null;
            C2043h.m224a("voicefile", "");
            C2043h.m224a("voiceTime", "");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: p */
    public void m301p() {
        try {
            m297l();
            MediaRecorder mediaRecorder = this.f600o;
            if (mediaRecorder == null || this.f606u) {
                return;
            }
            mediaRecorder.prepare();
            this.f600o.start();
            this.f603r = new Date().getTime();
            this.f606u = true;
            m298m();
        } catch (Exception e) {
            C2041f.m217a("PgyerSDK", "starting record error ", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: q */
    public void m302q() {
        MediaRecorder mediaRecorder;
        try {
            if (!this.f606u || (mediaRecorder = this.f600o) == null) {
                return;
            }
            mediaRecorder.reset();
            this.f606u = false;
            this.f604s = new Date().getTime();
            C2066q c2066q = this.f595j;
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%.0f", Double.valueOf(Math.ceil((this.f604s - this.f603r) / 1000.0d))));
            sb.append("\"");
            c2066q.setText(sb.toString());
            this.f587E.removeMessages(20006);
            m299n();
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: r */
    public void m303r() {
        int maxAmplitude = this.f600o.getMaxAmplitude();
        if (maxAmplitude < 800) {
            maxAmplitude = 0;
            this.f611z = 1;
        } else if (maxAmplitude >= 800 && maxAmplitude < 2000) {
            this.f611z = 2;
        } else if (maxAmplitude >= 2000 && maxAmplitude < 4000) {
            this.f611z = 3;
        } else if (maxAmplitude >= 4000 && maxAmplitude < 7000) {
            this.f611z = 4;
        } else if (maxAmplitude >= 7000 && maxAmplitude < 10000) {
            this.f611z = 5;
        } else if (maxAmplitude >= 10000 && maxAmplitude < 15000) {
            this.f611z = 6;
        } else {
            this.f611z = 7;
        }
        if (maxAmplitude > 32768) {
            this.f611z = 7;
        }
        Message message = new Message();
        message.what = 20005;
        message.obj = Integer.valueOf(this.f611z);
        this.f587E.sendMessageDelayed(message, 100L);
    }

    @Override // android.app.AlertDialog.Builder
    public AlertDialog create() {
        setView(m286f());
        return super.create();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if ("tagBtnPlay".equals(view.getTag().toString())) {
            m309e();
        }
        if ("tagBtnDelete".equals(view.getTag().toString())) {
            this.f596k.setVisibility(8);
            this.f594i.setVisibility(0);
            C2019e.m131a(this.f602q);
            C2043h.m224a("voicefile", "");
            C2043h.m224a("voiceTime", "");
            this.f602q = null;
        }
    }

    @Override // android.app.AlertDialog.Builder
    public AlertDialogBuilderC2062m setCustomTitle(View view) {
        return this;
    }

    @Override // android.app.AlertDialog.Builder
    public AlertDialogBuilderC2062m setTitle(CharSequence charSequence) {
        return this;
    }

    public AlertDialogBuilderC2062m(Context context, int i) {
        super(context, i);
        this.f599n = 120000;
        this.f606u = false;
        this.f611z = 1;
        this.f584B = Color.rgb(245, 245, 245);
        this.f585C = Color.rgb(255, 255, 255);
        this.f587E = new HandlerC2056g(this, Looper.getMainLooper());
        this.f588F = new ViewOnTouchListenerC2057h(this);
        this.f589G = new ViewOnTouchListenerC2058i(this);
        this.f586D = new ContextThemeWrapper(context, R.style.Theme.Holo.Light);
    }

    /* renamed from: c */
    private void m282c(LinearLayout linearLayout) {
        LinearLayout.LayoutParams m295k = m295k();
        linearLayout.addView(m291i(), m295k);
        linearLayout.addView(m294j(), m295k);
        linearLayout.addView(m289h(), m295k);
        m279b(linearLayout);
        m300o();
    }

    /* renamed from: f */
    private View m286f() {
        this.f598m = new LinearLayout(this.f586D);
        LinearLayout.LayoutParams m295k = m295k();
        this.f598m.setOrientation(1);
        this.f598m.setBackgroundColor(-1);
        this.f598m.setOnTouchListener(this.f588F);
        if (!this.f583A) {
            this.f590e = (TextView) m273a((CharSequence) C2023b.m151a(1062));
            TextView textView = (TextView) m273a((CharSequence) C2023b.m151a(1062));
            this.f590e = textView;
            this.f598m.addView(textView, m295k);
        }
        m282c(this.f598m);
        if (!this.f583A) {
            m272a(this.f598m);
        }
        return this.f598m;
    }

    /* renamed from: g */
    private void m287g() {
        this.f610y = new C2061l(this);
        Timer timer = new Timer();
        this.f609x = timer;
        timer.schedule(this.f610y, 0L, 400L);
    }

    /* renamed from: h */
    private View m289h() {
        EditText editText = new EditText(this.f586D);
        this.f591f = editText;
        editText.setHint(C2023b.m151a(1044));
        this.f591f.setPadding(C2037b.m195a(this.f586D, 20.0f), C2037b.m195a(this.f586D, 10.0f), C2037b.m195a(this.f586D, 20.0f), 0);
        this.f591f.setHintTextColor(Color.parseColor(f582d));
        if (this.f586D.getResources().getConfiguration().orientation == 1) {
            this.f591f.setMinLines(8);
        } else {
            this.f591f.setMinLines(2);
        }
        this.f591f.setTextSize(14.0f);
        this.f591f.setGravity(51);
        this.f591f.setBackgroundColor(this.f585C);
        return this.f591f;
    }

    /* renamed from: i */
    private View m291i() {
        EditText editText = new EditText(this.f586D);
        this.f592g = editText;
        editText.setHint(C2023b.m151a(1045));
        this.f592g.setSingleLine(true);
        this.f592g.setPadding(C2037b.m195a(this.f586D, 20.0f), C2037b.m195a(this.f586D, 10.0f), C2037b.m195a(this.f586D, 20.0f), C2037b.m195a(this.f586D, 10.0f));
        this.f592g.setHintTextColor(Color.parseColor(f582d));
        this.f592g.setMinLines(1);
        this.f592g.setTextSize(14.0f);
        this.f592g.setGravity(19);
        this.f592g.setBackgroundColor(this.f585C);
        this.f592g.setFocusable(true);
        this.f592g.setFocusableInTouchMode(true);
        this.f592g.requestFocus();
        if (!C2046k.m235a("selfmail")) {
            this.f592g.setText(C2043h.m222a(this.f586D, "selfmail"));
        }
        return this.f592g;
    }

    /* renamed from: j */
    private TextView m294j() {
        TextView textView = new TextView(this.f586D);
        textView.setBackgroundColor(Color.parseColor("#f0f0f0"));
        textView.setHeight(C2037b.m195a(this.f586D, 1.0f));
        return textView;
    }

    /* renamed from: k */
    private LinearLayout.LayoutParams m295k() {
        return new LinearLayout.LayoutParams(-1, -2);
    }

    /* renamed from: d */
    public EditText m308d() {
        return this.f592g;
    }

    /* renamed from: e */
    public void m309e() {
        Timer timer = this.f609x;
        if (timer != null) {
            timer.cancel();
        }
        TimerTask timerTask = this.f610y;
        if (timerTask != null) {
            timerTask.cancel();
        }
        if (this.f601p == null) {
            MediaPlayer mediaPlayer = new MediaPlayer();
            this.f601p = mediaPlayer;
            mediaPlayer.setOnCompletionListener(new C2059j(this));
            this.f601p.setOnPreparedListener(new C2060k(this));
        }
        if (this.f601p.isPlaying()) {
            this.f601p.reset();
        }
        this.f601p.setAudioStreamType(2);
        if (this.f602q.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(this.f602q);
                this.f601p.reset();
                this.f601p.setDataSource(fileInputStream.getFD());
                this.f601p.prepare();
            } catch (Exception unused) {
            }
            this.f601p.start();
            m287g();
        }
    }

    @Override // android.app.AlertDialog.Builder
    public AlertDialogBuilderC2062m setCancelable(boolean z) {
        super.setCancelable(z);
        return this;
    }

    /* renamed from: b */
    public static void m280b(String str) {
        f580b = str;
    }

    /* renamed from: a */
    public static void m276a(String str) {
        f579a = str;
    }

    /* renamed from: b */
    private void m279b(LinearLayout linearLayout) {
        this.f596k = new LinearLayout(this.f586D);
        LinearLayout.LayoutParams m295k = m295k();
        m295k.setMargins(C2037b.m195a(this.f586D, 20.0f), C2037b.m195a(this.f586D, 20.0f), C2037b.m195a(this.f586D, 20.0f), C2037b.m195a(this.f586D, 20.0f));
        this.f596k.setGravity(16);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, C2037b.m195a(this.f586D, 40.0f));
        layoutParams.weight = 1.0f;
        layoutParams.setMargins(0, 0, C2037b.m195a(this.f586D, 20.0f), 0);
        C2066q c2066q = new C2066q(this.f586D);
        this.f595j = c2066q;
        c2066q.setPadding(0, 0, C2037b.m195a(this.f586D, 10.0f), 0);
        this.f595j.setGravity(21);
        this.f595j.setOnClickListener(this);
        this.f595j.setTag("tagBtnPlay");
        this.f596k.addView(this.f595j, layoutParams);
        C2055f c2055f = new C2055f(this.f586D);
        this.f597l = c2055f;
        c2055f.setTag("tagBtnDelete");
        this.f597l.setOnClickListener(this);
        LinearLayout.LayoutParams m295k2 = m295k();
        m295k2.width = C2037b.m195a(this.f586D, 30.0f);
        m295k2.height = C2037b.m195a(this.f586D, 30.0f);
        this.f596k.addView(this.f597l, m295k2);
        this.f596k.setVisibility(8);
        linearLayout.addView(this.f596k, m295k);
        LinearLayout.LayoutParams m295k3 = m295k();
        m295k3.height = C2037b.m195a(this.f586D, 40.0f);
        m295k3.setMargins(C2037b.m195a(this.f586D, 20.0f), C2037b.m195a(this.f586D, 20.0f), C2037b.m195a(this.f586D, 20.0f), C2037b.m195a(this.f586D, 20.0f));
        C2052c c2052c = new C2052c(this.f586D);
        this.f594i = c2052c;
        c2052c.setText(C2023b.m151a(1072));
        this.f594i.setOnTouchListener(this.f589G);
        linearLayout.addView(this.f594i, m295k3);
    }

    /* renamed from: a */
    public AlertDialogBuilderC2062m m304a(boolean z) {
        this.f583A = z;
        return this;
    }

    /* renamed from: a */
    private View m272a(LinearLayout linearLayout) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 3;
        layoutParams.setMargins(C2037b.m195a(this.f586D, 15.0f), 0, C2037b.m195a(this.f586D, 20.0f), C2037b.m195a(this.f586D, 20.0f));
        CheckBox checkBox = new CheckBox(this.f586D);
        this.f593h = checkBox;
        checkBox.setText(C2023b.m151a(1064));
        this.f593h.setTextColor(Color.parseColor(f581c));
        this.f593h.setChecked(true);
        linearLayout.addView(this.f593h, layoutParams);
        TextView textView = new TextView(this.f586D);
        textView.setText(C2023b.m151a(1065) + C2022a.f469g + "\t" + C2022a.f468f + "（" + C2022a.f467e + "）");
        textView.setTextColor(Color.parseColor(f581c));
        textView.setTextSize(12.0f);
        ViewGroup.LayoutParams m295k = m295k();
        textView.setPadding(C2037b.m195a(this.f586D, 20.0f), 0, C2037b.m195a(this.f586D, 20.0f), C2037b.m195a(this.f586D, 20.0f));
        linearLayout.addView(textView, m295k);
        return linearLayout;
    }

    /* renamed from: c */
    public EditText m307c() {
        return this.f591f;
    }

    /* renamed from: a */
    private View m273a(CharSequence charSequence) {
        TextView textView = new TextView(this.f586D);
        this.f590e = textView;
        textView.setText(charSequence.toString());
        this.f590e.setTextSize(22.0f);
        this.f590e.setTextColor(Color.parseColor(f579a));
        this.f590e.setPadding(30, 20, 0, 20);
        this.f590e.setBackgroundColor(Color.parseColor(f580b));
        this.f590e.setGravity(17);
        this.f590e.setSingleLine(true);
        return this.f590e;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m274a(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) this.f586D.getSystemService("input_method");
        if (Build.VERSION.SDK_INT >= 3) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 2);
        }
    }

    /* renamed from: a */
    public void m305a() {
        try {
            this.f598m.setOnTouchListener(null);
            this.f594i.setOnTouchListener(null);
            this.f597l.setOnClickListener(null);
            this.f595j.setOnClickListener(null);
            MediaPlayer mediaPlayer = this.f601p;
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                this.f601p.stop();
                this.f601p.release();
            }
            MediaRecorder mediaRecorder = this.f600o;
            if (mediaRecorder != null) {
                mediaRecorder.release();
            }
            if (this.f586D != null) {
                this.f586D = null;
            }
        } catch (Exception unused) {
        }
    }

    /* renamed from: b */
    public CheckBox m306b() {
        return this.f593h;
    }
}
