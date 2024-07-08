package com.kkkcut.e20j.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.kkkcut.e20j.androidquick.tool.FileUtil;
import com.kkkcut.e20j.us.R;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.io.File;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
public class SerialLogFragment extends BaseBackFragment {

    Button btClear;

    TextView tvLog;

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_serial_log;
    }

    @Override // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    public String setTitleStr() {
        return "日志";
    }

    public static SerialLogFragment newInstance() {
        Bundle bundle = new Bundle();
        SerialLogFragment serialLogFragment = new SerialLogFragment();
        serialLogFragment.setArguments(bundle);
        return serialLogFragment;
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        final File file = new File(getContext().getFilesDir(), "log");
        addDisposable(Observable.fromCallable(new Callable<String>() { // from class: com.kkkcut.e20j.ui.fragment.SerialLogFragment.2
            @Override // java.util.concurrent.Callable
            public String call() throws Exception {
                return FileUtil.readFileContent(file.getPath());
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() { // from class: com.kkkcut.e20j.ui.fragment.SerialLogFragment.1
            @Override // io.reactivex.functions.Consumer
            public void accept(String str) throws Exception {
                SerialLogFragment.this.tvLog.setText(str);
            }
        }));
        this.btClear.setOnClickListener(new View.OnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.SerialLogFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                file.delete();
                SerialLogFragment.this.tvLog.setText("");
            }
        });
    }
}
