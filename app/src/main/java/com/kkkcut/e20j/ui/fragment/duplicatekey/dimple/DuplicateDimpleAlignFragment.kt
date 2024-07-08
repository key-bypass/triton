package com.kkkcut.e20j.ui.fragment.duplicatekey.dimple;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cutting.machine.bean.ClampBean;
import com.cutting.machine.bean.KeyInfo;
import com.kkkcut.e20j.DbBean.userDB.DuplicateDimple;
import com.kkkcut.e20j.adapter.DimpleDataAdapter;
import com.kkkcut.e20j.androidquick.tool.ToastUtil;
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter;
import com.kkkcut.e20j.dao.UserDataDaoManager;
import com.kkkcut.e20j.ui.dialog.EditDialog;
import com.kkkcut.e20j.ui.fragment.BaseBackFragment;
import com.kkkcut.e20j.us.R;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

/* loaded from: classes.dex */
public class DuplicateDimpleAlignFragment extends BaseBackFragment implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {
    DimpleDataAdapter adapter;

    CheckBox cbSide;

    CheckBox cbZiMuZhu;

    ImageView ivClamp;
    private KeyInfo keyInfo;

    RadioButton rbShoulder;

    RadioButton rbTip;

    RecyclerView rvData;
    private boolean side;

    TextView tvNumber;

    TextView tvRowNumber;
    private boolean ziMuZhu;
    private int bittingCount = 5;
    private int rowCount = 1;

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_duplicate_dimple_align;
    }

    @Override // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    public String setTitleStr() {
        return null;
    }

    public static DuplicateDimpleAlignFragment newInstance() {
        return new DuplicateDimpleAlignFragment();
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        KeyInfo keyInfo = new KeyInfo();
        this.keyInfo = keyInfo;
        keyInfo.setType(6);
        DimpleDataAdapter dimpleDataAdapter = new DimpleDataAdapter();
        this.adapter = dimpleDataAdapter;
        dimpleDataAdapter.setOnItemChildClickListener(this);
        this.rvData.setAdapter(this.adapter);
        this.adapter.setOnItemClickListener(this);
        this.rvData.setLayoutManager(new LinearLayoutManager(getContext()));
        initData();
    }

    private void initData() {
        addDisposable(Observable.fromCallable(new Callable<List<DuplicateDimple>>() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleAlignFragment.2
            @Override // java.util.concurrent.Callable
            public List<DuplicateDimple> call() throws Exception {
                return UserDataDaoManager.getInstance(DuplicateDimpleAlignFragment.this.getContext()).getDuplicateDimpleKeys();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<DuplicateDimple>>() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleAlignFragment.1
            @Override // io.reactivex.functions.Consumer
            public void accept(List<DuplicateDimple> list) throws Exception {
                DuplicateDimpleAlignFragment.this.adapter.setNewData(list);
            }
        }));
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        switch (compoundButton.getId()) {
            case R.id.cb_side /* 2131362032 */:
                this.side = z;
                if (z) {
                    if (this.keyInfo.getAlign() == 0) {
                        this.ivClamp.setImageResource(R.drawable.car_clamp_remind_b_shoulder_side);
                        return;
                    } else {
                        this.ivClamp.setImageResource(R.drawable.car_clamp_remind_b_tip_side);
                        return;
                    }
                }
                if (this.keyInfo.getAlign() == 0) {
                    this.ivClamp.setImageResource(R.drawable.car_clamp_remind_b_shoulder);
                    return;
                } else {
                    this.ivClamp.setImageResource(R.drawable.car_clamp_remind_b_tip);
                    return;
                }
            case R.id.cb_zimuzhu /* 2131362050 */:
                this.ziMuZhu = z;
                return;
            case R.id.rb_shoulder /* 2131362643 */:
                if (z) {
                    if (this.side) {
                        this.ivClamp.setImageResource(R.drawable.car_clamp_remind_b_shoulder_side);
                    } else {
                        this.ivClamp.setImageResource(R.drawable.car_clamp_remind_b_shoulder);
                    }
                    this.keyInfo.setAlign(0);
                    return;
                }
                return;
            case R.id.rb_tip /* 2131362660 */:
                if (z) {
                    if (this.side) {
                        this.ivClamp.setImageResource(R.drawable.car_clamp_remind_b_tip_side);
                    } else {
                        this.ivClamp.setImageResource(R.drawable.car_clamp_remind_b_tip);
                    }
                    this.keyInfo.setAlign(1);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.bt_ok /* 2131361962 */:
                this.keyInfo.setWidth(0);
                this.keyInfo.setThick(0);
                this.keyInfo.setRow_pos("");
                this.keyInfo.setSpaceStr("");
                this.keyInfo.setSpaceWidthStr("");
                this.keyInfo.setDepthStr("");
                this.keyInfo.setDepthName("");
                goDimpleData();
                return;
            case R.id.tv_number_add /* 2131362975 */:
                int i = this.bittingCount;
                if (i < 12) {
                    this.bittingCount = i + 1;
                }
                this.tvNumber.setText(String.valueOf(this.bittingCount));
                return;
            case R.id.tv_number_reduce /* 2131362976 */:
                int i2 = this.bittingCount;
                if (i2 > 1) {
                    this.bittingCount = i2 - 1;
                }
                this.tvNumber.setText(String.valueOf(this.bittingCount));
                return;
            case R.id.tv_row_add /* 2131362985 */:
                int i3 = this.rowCount;
                if (i3 < 4) {
                    this.rowCount = i3 + 1;
                }
                this.tvRowNumber.setText(String.valueOf(this.rowCount));
                return;
            case R.id.tv_row_reduce /* 2131362987 */:
                int i4 = this.rowCount;
                if (i4 > 1) {
                    this.rowCount = i4 - 1;
                }
                this.tvRowNumber.setText(String.valueOf(this.rowCount));
                return;
            default:
                return;
        }
    }

    private void goDimpleData() {
        ClampBean clampBean = new ClampBean();
        clampBean.setClampNum("S1");
        clampBean.setClampSide("B");
        clampBean.setClampSlot(this.side ? "1" : "0");
        this.keyInfo.setClampKeyBasicData(clampBean);
        this.keyInfo.setKeyToothCode("");
        start(DuplicateDimpleDataFragment.newInstance(this.keyInfo, this.bittingCount, this.ziMuZhu, this.rowCount));
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemChildClickListener
    public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, final int i) {
        final DuplicateDimple duplicateDimple = this.adapter.getData().get(i);
        int id = view.getId();
        if (id == R.id.iv_delete) {
            new AlertDialog.Builder(getContext()).setMessage(R.string.the_key_profile_will_be_deleted).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleAlignFragment.3
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    UserDataDaoManager.getInstance(DuplicateDimpleAlignFragment.this.getContext()).deleteDuplicateDimpleKey(duplicateDimple);
                    DuplicateDimpleAlignFragment.this.adapter.remove(i);
                }
            }).setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null).show();
        } else if (id == R.id.iv_edit) {
            showEditDialog(duplicateDimple, (TextView) ((View) view.getParent()).findViewById(R.id.title));
        } else {
            if (id != R.id.iv_export) {
                return;
            }
            new AlertDialog.Builder(getContext()).setMessage(R.string.import_this_data_into_the_custom_key).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleAlignFragment.4
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    UserDataDaoManager.getInstance(DuplicateDimpleAlignFragment.this.getContext()).saveCustomKeyFromDimpleDup(duplicateDimple);
                    ToastUtil.showToast(R.string.finish);
                }
            }).setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null).show();
        }
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        DuplicateDimple duplicateDimple = this.adapter.getData().get(i);
        if (duplicateDimple.getAlign() == 1) {
            this.rbTip.performClick();
        } else {
            this.rbShoulder.performClick();
        }
        String space = duplicateDimple.getSpace();
        if (!TextUtils.isEmpty(space)) {
            int length = space.split(";")[0].split(",").length;
            this.bittingCount = length;
            this.tvNumber.setText(String.valueOf(length));
        }
        this.keyInfo.setSpaceStr(space);
        this.keyInfo.setRow_pos(duplicateDimple.getRow_pos());
        String clampSlot = duplicateDimple.getClampSlot();
        if (!TextUtils.isEmpty(clampSlot) && clampSlot.equals("1")) {
            this.cbSide.setChecked(true);
        } else {
            this.cbSide.setChecked(false);
        }
        if (duplicateDimple.getSpace_width().contains("-")) {
            this.cbZiMuZhu.setChecked(true);
            int row_count = duplicateDimple.getRow_count();
            if (row_count == 0) {
                row_count = space.split(";").length / 2;
            }
            this.rowCount = row_count;
            this.tvRowNumber.setText(String.valueOf(row_count));
        } else {
            this.cbZiMuZhu.setChecked(false);
            int row_count2 = duplicateDimple.getRow_count();
            if (row_count2 == 0) {
                row_count2 = space.split(";").length;
            }
            this.rowCount = row_count2;
            this.tvRowNumber.setText(String.valueOf(row_count2));
        }
        goDimpleData();
    }

    private void showEditDialog(final DuplicateDimple duplicateDimple, final TextView textView) {
        final String keyname = duplicateDimple.getKeyname();
        EditDialog editDialog = new EditDialog(getContext());
        editDialog.setTip(getString(R.string.enter_remarks));
        if (!TextUtils.isEmpty(keyname)) {
            editDialog.setEditTextContent(keyname);
        }
        editDialog.setDialogBtnCallback(new EditDialog.DialogInputFinishCallBack() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleAlignFragment.5
            @Override // com.kkkcut.e20j.ui.dialog.EditDialog.DialogInputFinishCallBack
            public void onDialogButClick(String str) {
                if (TextUtils.isEmpty(str) || str.equals(keyname)) {
                    return;
                }
                textView.setText(str);
                duplicateDimple.setKeyname(str);
                UserDataDaoManager.getInstance(DuplicateDimpleAlignFragment.this.getContext()).saveDuplicateDimpleKey(duplicateDimple);
            }
        });
        editDialog.show();
    }

    @Override // com.kkkcut.e20j.base.BaseFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void onEventComing(EventCenter eventCenter) {
        if (eventCenter.getEventCode() == 46) {
            initData();
        }
    }
}
