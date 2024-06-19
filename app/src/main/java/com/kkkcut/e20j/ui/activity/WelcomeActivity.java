package com.kkkcut.e20j.ui.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.ImageView;
import com.kkkcut.e20j.androidquick.tool.AppUtil;
import com.kkkcut.e20j.androidquick.tool.SPUtils;
import com.kkkcut.e20j.base.HideStatusActivity;
import com.kkkcut.e20j.presenter.SplashPresenter;
import com.kkkcut.e20j.us.R;
import com.kkkcut.e20j.view.SplashView;

/* loaded from: classes.dex */
public class WelcomeActivity extends HideStatusActivity implements SplashView {
    private SplashPresenter splashPresenter;

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickActivity
    protected int getContentViewLayoutID() {
        return R.layout.activity_lanuch;
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickActivity
    protected void initViewsAndEvents() {
        Resources resources;
        int identifier;
        this.splashPresenter = new SplashPresenter(this, this);
        String packageName = AppUtil.getPackageName(this);
        ImageView imageView = (ImageView) findViewById(R.id.iv_splash);
        if (packageName.endsWith(".us")) {
            imageView.setImageResource(R.drawable.welcome_spl);
            return;
        }
        if (packageName.endsWith(".neutral")) {
            imageView.setImageResource(R.drawable.welcome);
            return;
        }
        String string = SPUtils.getString("welcome");
        if (TextUtils.isEmpty(string) || (resources = getResources()) == null || (identifier = resources.getIdentifier(string, "drawable", getPackageName())) == 0) {
            return;
        }
        imageView.setImageResource(identifier);
    }

    @Override // com.kkkcut.e20j.view.SplashView
    /* renamed from: goMain, reason: merged with bridge method [inline-methods] */
    public void m25lambda$goMainDelay$0$comkkkcute20juiactivityWelcomeActivity() {
        startActivity(new Intent(this, (Class<?>) FrameActivity.class));
        finish();
    }

    @Override // com.kkkcut.e20j.view.SplashView
    public void goMainDelay(long j) {
        new Handler().postDelayed(new Runnable() { // from class: com.kkkcut.e20j.ui.activity.WelcomeActivity$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                WelcomeActivity.this.m25lambda$goMainDelay$0$comkkkcute20juiactivityWelcomeActivity();
            }
        }, j);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.splashPresenter.onDetach();
        this.splashPresenter = null;
    }
}
