package com.kkkcut.e20j.ui.fragment.engraving;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kkkcut.e20j.DbBean.userDB.KeyMarkingTemplate;
import com.kkkcut.e20j.adapter.KeyMarkingTemplateAdapter;
import com.kkkcut.e20j.dao.UserDataDaoManager;
import com.kkkcut.e20j.ui.dialog.RemindDialog;
import com.kkkcut.e20j.ui.fragment.BaseBackFragment;
import com.kkkcut.e20j.us.R;
import io.reactivex.Observable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
public class TemplateSelectFragment extends BaseBackFragment implements BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.bt_delete_all)
    Button btDeleteAll;

    @BindView(R.id.et_search)
    EditText etSearch;
    private KeyMarkingTemplateAdapter keyMarkingTemplateAdapter;

    @BindView(R.id.rv_template_list)
    RecyclerView rvTemplateList;

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
        KeyMarkingTemplateAdapter keyMarkingTemplateAdapter = new KeyMarkingTemplateAdapter();
        this.keyMarkingTemplateAdapter = keyMarkingTemplateAdapter;
        keyMarkingTemplateAdapter.setOnItemChildClickListener(this);
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
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<KeyMarkingTemplate>>() { // from class: com.kkkcut.e20j.ui.fragment.engraving.TemplateSelectFragment.1
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

    @OnClick({R.id.bt_delete_all})
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

    @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemChildClickListener
    public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        UserDataDaoManager.getInstance(getContext()).deleteSingleTemplate((KeyMarkingTemplate) baseQuickAdapter.getData().get(i));
        baseQuickAdapter.remove(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @OnTextChanged(callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED, value = {R.id.et_search})
    public void afterTextChanged(final Editable editable) {
        Disposable subscribe = Observable.fromCallable(new Callable<List<KeyMarkingTemplate>>() { // from class: com.kkkcut.e20j.ui.fragment.engraving.TemplateSelectFragment.6
            @Override // java.util.concurrent.Callable
            public List<KeyMarkingTemplate> call() throws Exception {
                return UserDataDaoManager.getInstance(TemplateSelectFragment.this.getContext()).fuzzyQueryTemplates(editable.toString());
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<KeyMarkingTemplate>>() { // from class: com.kkkcut.e20j.ui.fragment.engraving.TemplateSelectFragment.5
            @Override // io.reactivex.functions.Consumer
            public void accept(List<KeyMarkingTemplate> list) throws Exception {
                TemplateSelectFragment.this.keyMarkingTemplateAdapter.setNewData(list);
            }
        });
        clearDisposable();
        addDisposable(subscribe);
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("template", (KeyMarkingTemplate) baseQuickAdapter.getData().get(i));
        setFragmentResult(0, bundle);
        onBack();
    }
}
