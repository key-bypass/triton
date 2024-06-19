package com.kkkcut.e20j.ui.dialog.base;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.kkkcut.e20j.us.R;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.ListCompositeDisposable;

/* loaded from: classes.dex */
public abstract class BottomInDialog {
    private Activity activity;
    private Unbinder bind;
    private ViewGroup contentContainer;
    private ViewGroup decorView;
    private Animation inAnim;
    private boolean isDismissing;
    private OnDismissListener onDismissListener;
    private Animation outAnim;
    private ViewGroup rootView;
    private boolean cancelable = true;
    private ListCompositeDisposable listCompositeDisposable = new ListCompositeDisposable();
    private final View.OnTouchListener onCancelableTouchListener = new View.OnTouchListener() { // from class: com.kkkcut.e20j.ui.dialog.base.BottomInDialog.2
        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() != 0 || !BottomInDialog.this.cancelable) {
                return false;
            }
            BottomInDialog.this.dismiss();
            return false;
        }
    };

    /* loaded from: classes.dex */
    public interface OnDismissListener {
        void onDismiss();
    }

    private int getAnimationResource(boolean z) {
        return z ? R.anim.bottom_in : R.anim.bottom_out;
    }

    public abstract int getContentLayoutID();

    public abstract void initView();

    protected void addDisposable(Disposable disposable) {
        if (disposable == null || disposable.isDisposed()) {
            return;
        }
        this.listCompositeDisposable.add(disposable);
    }

    protected void clearDisposable() {
        if (this.listCompositeDisposable.isDisposed()) {
            return;
        }
        this.listCompositeDisposable.clear();
    }

    public Activity getActivity() {
        return this.activity;
    }

    public void setCancelable(boolean z) {
        this.cancelable = z;
    }

    public Activity getContext() {
        return this.activity;
    }

    public BottomInDialog(Activity activity) {
        this.activity = activity;
        LayoutInflater from = LayoutInflater.from(activity);
        ViewGroup viewGroup = (ViewGroup) activity.getWindow().getDecorView().findViewById(android.R.id.content);
        this.decorView = viewGroup;
        ViewGroup viewGroup2 = (ViewGroup) from.inflate(R.layout.base_container, viewGroup, false);
        this.rootView = viewGroup2;
        this.contentContainer = (ViewGroup) viewGroup2.findViewById(R.id.dialogplus_content_container);
        this.outAnim = AnimationUtils.loadAnimation(activity, getAnimationResource(false));
        this.inAnim = AnimationUtils.loadAnimation(activity, getAnimationResource(true));
        initContentView(from);
        initCancelable();
    }

    private void initContentView(LayoutInflater layoutInflater) {
        View inflate = layoutInflater.inflate(getContentLayoutID(), (ViewGroup) null);
        inflate.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.contentContainer.addView(inflate);
        this.bind = ButterKnife.bind(this, inflate);
    }

    private void initCancelable() {
        this.rootView.findViewById(R.id.dialogplus_outmost_container).setOnTouchListener(this.onCancelableTouchListener);
    }

    public void show() {
        initView();
        if (isShowing()) {
            return;
        }
        onAttached(this.rootView);
    }

    public boolean isShowing() {
        return this.decorView.findViewById(R.id.dialogplus_outmost_container) != null;
    }

    public View findViewById(int i) {
        return this.contentContainer.findViewById(i);
    }

    public void dismiss() {
        if (this.isDismissing) {
            return;
        }
        this.outAnim.setAnimationListener(new Animation.AnimationListener() { // from class: com.kkkcut.e20j.ui.dialog.base.BottomInDialog.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                BottomInDialog.this.decorView.post(new Runnable() { // from class: com.kkkcut.e20j.ui.dialog.base.BottomInDialog.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        BottomInDialog.this.decorView.removeView(BottomInDialog.this.rootView);
                        BottomInDialog.this.bind.unbind();
                        BottomInDialog.this.isDismissing = false;
                        if (BottomInDialog.this.onDismissListener != null) {
                            BottomInDialog.this.onDismissListener.onDismiss();
                        }
                    }
                });
            }
        });
        this.contentContainer.startAnimation(this.outAnim);
        this.isDismissing = true;
    }

    private void onAttached(View view) {
        this.decorView.addView(view);
        this.contentContainer.startAnimation(this.inAnim);
        clearDisposable();
    }

    public void setOnDismissListener(OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }

    public void onBackPressed(BottomInDialog bottomInDialog) {
        dismiss();
    }
}
