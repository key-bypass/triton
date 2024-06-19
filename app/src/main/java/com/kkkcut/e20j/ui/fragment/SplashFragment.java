package com.kkkcut.e20j.ui.fragment;

import android.os.Handler;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.OnClick;
import com.kkkcut.e20j.base.BaseFFragment;
import com.kkkcut.e20j.presenter.SplashPresenter;
import com.kkkcut.e20j.us.R;
import com.kkkcut.e20j.view.SplashView;
import me.yokeyword.fragmentation.ISupportFragment;

/* loaded from: classes.dex */
public class SplashFragment extends BaseFFragment implements SplashView {

    @BindView(R.id.iv_splash)
    ImageView ivSplash;
    private SplashPresenter splashPresenter;

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.activity_lanuch;
    }

    @OnClick({R.id.iv_splash})
    public void onViewClicked() {
    }

    public static ISupportFragment newInstance() {
        return new SplashFragment();
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        this.splashPresenter = new SplashPresenter(getContext(), this);
    }

    @Override // com.kkkcut.e20j.view.SplashView
    public void goMain() {
        startWithPop(MainFragment.newInstance());
    }

    @Override // com.kkkcut.e20j.view.SplashView
    public void goMainDelay(long j) {
        new Handler().postDelayed(new Runnable() { // from class: com.kkkcut.e20j.ui.fragment.SplashFragment.1
            @Override // java.lang.Runnable
            public void run() {
                SplashFragment.this.goMain();
            }
        }, j);
    }

    @Override // com.kkkcut.e20j.base.BaseFFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.splashPresenter.onDetach();
    }
}
