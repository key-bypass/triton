package com.kkkcut.e20j.ui.fragment.engraving;

import static io.reactivex.rxjava3.schedulers.Schedulers.io;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kkkcut.e20j.DbBean.userDB.KeyMarkingTemplate;
import com.kkkcut.e20j.adapter.KeyMarkingTemplateAdapter;
import com.kkkcut.e20j.dao.UserDataDaoManager;
import com.kkkcut.e20j.ui.dialog.RemindDialog;
import com.kkkcut.e20j.ui.fragment.BaseBackFragment;
import com.kkkcut.e20j.us.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.disposables.Disposable;

/* loaded from: classes.dex */
public class TemplateSelectFragment extends BaseBackFragment implements BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.OnItemClickListener {

    Button btDeleteAll;

    EditText etSearch;

    RecyclerView rvTemplateList;

    KeyMarkingTemplateAdapter keyMarkingTemplateAdapter;

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_template_list;
    }

    public static TemplateSelectFragment newInstance() {
        Bundle bundle = new Bundle();
        TemplateSelectFragment templateSelectFragment = new TemplateSelectFragment();
        templateSelectFragment.setArguments(bundle);
        return templateSelectFragment;
    }

    @Override // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    public String setTitleStr() {
        return getString(R.string.template_selection);
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(1);
        this.rvTemplateList.setLayoutManager(linearLayoutManager);

        this.keyMarkingTemplateAdapter = new KeyMarkingTemplateAdapter();
        this.keyMarkingTemplateAdapter.setOnItemChildClickListener(this);
        this.keyMarkingTemplateAdapter.setOnItemClickListener(this);
        this.rvTemplateList.setAdapter(this.keyMarkingTemplateAdapter);
        getTemplates();
    }

    private void getTemplates() {
        addDisposable(Observable.fromCallable(new Callable<List<KeyMarkingTemplate>>() { // from class: com.kkkcut.e20j.ui.fragment.engraving.TemplateSelectFragment.3
            @Override // java.util.concurrent.Callable
            public List<KeyMarkingTemplate> call() throws Exception {
                return UserDataDaoManager.getInstance(TemplateSelectFragment.this.getContext()).getKeyMarkingTemplates();
            }
        }).subscribeOn(io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<KeyMarkingTemplate>>() { // from class: com.kkkcut.e20j.ui.fragment.engraving.TemplateSelectFragment.1
            @Override // io.reactivex.functions.Consumer
            public void accept(List<KeyMarkingTemplate> list) throws Exception {
                TemplateSelectFragment.this.keyMarkingTemplateAdapter.setNewData(list);
            }
        }, new Consumer<Throwable>() { // from class: com.kkkcut.e20j.ui.fragment.engraving.TemplateSelectFragment.2
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
            }
        }));
    }

    public void onViewClicked() {
        RemindDialog remindDialog = new RemindDialog(getContext());
        remindDialog.setRemindMsg(getString(R.string.delete_all_recordes));
        remindDialog.setDialogBtnCallback(new RemindDialog.DialogBtnCallBack() { // from class: com.kkkcut.e20j.ui.fragment.engraving.TemplateSelectFragment.4
            @Override // com.kkkcut.e20j.ui.dialog.RemindDialog.DialogBtnCallBack
            public void onDialogButClick(boolean z) {
                if (z) {
                    TemplateSelectFragment.this.keyMarkingTemplateAdapter.setNewData(new ArrayList());
                    UserDataDaoManager.getInstance(TemplateSelectFragment.this.getContext()).deleteAllTemplate();
                }
            }
        });
        remindDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void afterTextChanged(final Editable editable) {
        Disposable subscribe = Observable.fromCallable(new Callable<List<KeyMarkingTemplate>>() { // from class: com.kkkcut.e20j.ui.fragment.engraving.TemplateSelectFragment.6
            @Override // java.util.concurrent.Callable
            public List<KeyMarkingTemplate> call() throws Exception {
                return UserDataDaoManager.getInstance(TemplateSelectFragment.this.getContext()).fuzzyQueryTemplates(editable.toString());
            }
        }).subscribeOn(io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<KeyMarkingTemplate>>() { // from class: com.kkkcut.e20j.ui.fragment.engraving.TemplateSelectFragment.5
            @Override // io.reactivex.functions.Consumer
            public void accept(List<KeyMarkingTemplate> list) throws Exception {
                TemplateSelectFragment.this.keyMarkingTemplateAdapter.setNewData(list);
            }
        });
        clearDisposable();
        addDisposable(subscribe);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("template", (KeyMarkingTemplate) adapter.getData().get(position));
        setFragmentResult(0, bundle);
        onBack();
    }
}
