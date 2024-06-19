package com.kkkcut.e20j.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.kkkcut.e20j.androidquick.tool.AppUtil;
import com.kkkcut.e20j.androidquick.tool.StringUtil;
import com.kkkcut.e20j.us.R;
import com.liying.core.Command;
import com.liying.core.OperateType;
import com.liying.core.communication.OperationManager;
import io.reactivex.Observable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.disposables.ListCompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class LoadingDialog extends Dialog implements View.OnClickListener {
    private static final String TAG = "LoadingDialog";
    private ListCompositeDisposable listCompositeDisposable;
    private LinearLayout mNoBgLinely;
    private String mTip;
    private TextView mTipTxt;
    private boolean showStop;
    private TextView tvStop;

    public LoadingDialog(Context context) {
        super(context, R.style.dialog_loading_view);
        this.listCompositeDisposable = new ListCompositeDisposable();
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_loading);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll_loading);
        this.mNoBgLinely = linearLayout;
        linearLayout.setOnClickListener(this);
        this.mTipTxt = (TextView) findViewById(R.id.tip);
        TextView textView = (TextView) findViewById(R.id.tv_stop);
        this.tvStop = textView;
        if (this.showStop) {
            textView.setVisibility(0);
        }
        if (!StringUtil.isEmpty(this.mTip)) {
            this.mTipTxt.setText(this.mTip);
        }
        setCancelable(false);
    }

    public void showStop(boolean z) {
        this.showStop = z;
        TextView textView = this.tvStop;
        if (textView != null) {
            if (z) {
                textView.setVisibility(0);
            } else {
                textView.setVisibility(8);
            }
        }
    }

    public void setTip(String str) {
        this.mTip = str;
        TextView textView = this.mTipTxt;
        if (textView != null) {
            textView.setText(str);
        }
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        clearDisposable();
    }

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

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != R.id.ll_loading) {
            return;
        }
        if (this.showStop) {
            OperationManager.getInstance().sendOrder(Command.TurnOffSpindle(), OperateType.STOP);
        }
        if (AppUtil.isApkInDebug(getContext())) {
            dismiss();
        } else {
            clearDisposable();
            addDisposable(Observable.timer(4000L, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() { // from class: com.kkkcut.e20j.ui.dialog.LoadingDialog.1
                @Override // io.reactivex.functions.Consumer
                public void accept(Long l) throws Exception {
                    if (LoadingDialog.this.isShowing()) {
                        LoadingDialog.this.dismiss();
                    }
                }
            }));
        }
    }
}
