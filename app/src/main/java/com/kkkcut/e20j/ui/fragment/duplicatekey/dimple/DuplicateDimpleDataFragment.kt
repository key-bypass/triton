package com.kkkcut.e20j.ui.fragment.duplicatekey.dimple;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.cutting.machine.Command;
import com.cutting.machine.KeyAlignInfo;
import com.cutting.machine.KeyBlankCutStepsGenerateUtil;
import com.cutting.machine.OperateType;
import com.cutting.machine.StepsGenerateUtil;
import com.cutting.machine.ToolSizeManager;
import com.cutting.machine.bean.ClampBean;
import com.cutting.machine.bean.KeyInfo;
import com.cutting.machine.bean.StepBean;
import com.cutting.machine.clamp.ClampManager;
import com.cutting.machine.clamp.ClampUtil;
import com.cutting.machine.clamp.MachineData;
import com.cutting.machine.communication.OperationManager;
import com.cutting.machine.error.ErrorBean;
import com.cutting.machine.error.ErrorCode;
import com.cutting.machine.operation.cut.DataParam;
import com.cutting.machine.utils.AssetsJsonUtils;
import com.jakewharton.rxbinding3.view.RxView;
import com.kkkcut.e20j.DbBean.userDB.DuplicateDimple;
import com.kkkcut.e20j.SpKeys;
import com.kkkcut.e20j.androidquick.tool.LogUtil;
import com.kkkcut.e20j.androidquick.tool.SPUtils;
import com.kkkcut.e20j.androidquick.tool.ToastUtil;
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter;
import com.kkkcut.e20j.dao.UserDataDaoManager;
import com.kkkcut.e20j.ui.dialog.AnglekeyTurningDialog;
import com.kkkcut.e20j.ui.dialog.CutDialog;
import com.kkkcut.e20j.ui.dialog.DecodeDialog;
import com.kkkcut.e20j.ui.dialog.DimpleSpaceSelectDialog;
import com.kkkcut.e20j.ui.dialog.EditDialog;
import com.kkkcut.e20j.ui.dialog.RemindDialog;
import com.kkkcut.e20j.ui.fragment.BaseBackFragment;
import com.kkkcut.e20j.ui.fragment.clampswitch.ClampCreator;
import com.kkkcut.e20j.us.R;
import com.kkkcut.e20j.utils.CutCountHelper;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.disposables.Disposable;

/* loaded from: classes.dex */
public class DuplicateDimpleDataFragment extends BaseBackFragment {
    private static final String KEY = "key";
    private static final String ROW_COUNT = "row_count";
    private static final String SPACE_COUNT = "space_count";
    private static final String ZIMUZHU = "zimuzhu";
    private boolean aligned;

    Button btDecode;

    Button btFindSpace;

    Button btLocation;

    Button btSave;
    private int dimpleCutIndex;

    GridLayout gridLayout;

    ImageView ivXAdd;

    ImageView ivXReduce;

    ImageView ivYAdd;

    ImageView ivYReduce;

    ImageView ivZAdd;

    ImageView ivZReduce;
    private KeyInfo keyInfo;
    private RadioButton lastRb;

    RadioButton rbRow;

    RadioButton rbStepX;

    RadioButton rbStepY;

    RadioButton rbStepZ;

    RadioGroup rgRowIndex;
    private int rowIndex;
    private List<Integer> rowList;
    private List<List<Integer>> spaceList;

    TextView tvSleep;

    TextView tvSpace;
    private List<RadioButton> rbList = new ArrayList();
    private int x = 1;
    private int y = 1;
    private int z = 1500;
    private int sleep = 3;
    private DataParam dataParam = new DataParam();

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_duplicate_dimple_data;
    }

    @Override // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    public String setTitleStr() {
        return null;
    }

    static /* synthetic */ int access$2308(DuplicateDimpleDataFragment duplicateDimpleDataFragment) {
        int i = duplicateDimpleDataFragment.dimpleCutIndex;
        duplicateDimpleDataFragment.dimpleCutIndex = i + 1;
        return i;
    }

    public static DuplicateDimpleDataFragment newInstance(KeyInfo keyInfo, int i, boolean z, int i2) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY, keyInfo);
        bundle.putBoolean(ZIMUZHU, z);
        bundle.putInt(SPACE_COUNT, i);
        bundle.putInt(ROW_COUNT, i2);
        DuplicateDimpleDataFragment duplicateDimpleDataFragment = new DuplicateDimpleDataFragment();
        duplicateDimpleDataFragment.setArguments(bundle);
        return duplicateDimpleDataFragment;
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        int i;
        int i2;
        this.keyInfo = getArguments() != null ? (KeyInfo) getArguments().getParcelable(KEY) : null;
        int i3 = getArguments().getInt(ROW_COUNT);
        if (this.keyInfo != null) {
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            int i4 = ClampUtil.getClampMode(this.keyInfo) == 0 ? ErrorCode.keyDecodeFailed : 1000;
            for (int i5 = 0; i5 < i3; i5++) {
                if (getArguments().getBoolean(ZIMUZHU)) {
                    for (int i6 = 0; i6 < 2; i6++) {
                        int i7 = 0;
                        for (int i8 = i4; i8 > 0; i8 -= 50) {
                            if (i8 == 50) {
                                sb.append(i8);
                                sb.append(";");
                                i2 = i7 + 1;
                                sb2.append(i7);
                                sb2.append(";");
                            } else {
                                sb.append(i8);
                                sb.append(",");
                                i2 = i7 + 1;
                                sb2.append(i7);
                                sb2.append(",");
                            }
                            i7 = i2;
                        }
                    }
                } else {
                    int i9 = 0;
                    for (int i10 = i4; i10 > 0; i10 -= 50) {
                        if (i10 == 50) {
                            sb.append(i10);
                            sb.append(";");
                            i = i9 + 1;
                            sb2.append(i9);
                            sb2.append(";");
                        } else {
                            sb.append(i10);
                            sb.append(",");
                            i = i9 + 1;
                            sb2.append(i9);
                            sb2.append(",");
                        }
                        i9 = i;
                    }
                }
            }
            this.keyInfo.setDepthStr(sb.toString());
            this.keyInfo.setDepthName(sb2.toString());
        }
        int i11 = getArguments().getInt(SPACE_COUNT);
        List<List<Integer>> spaceList = this.keyInfo.getSpaceList();
        this.spaceList = spaceList;
        if (spaceList == null || spaceList.size() == 0) {
            this.spaceList = new ArrayList();
            for (int i12 = 0; i12 < i3; i12++) {
                ArrayList arrayList = new ArrayList();
                for (int i13 = 0; i13 < i11; i13++) {
                    arrayList.add(0);
                }
                this.spaceList.add(arrayList);
            }
        }
        for (int i14 = 0; i14 < i3; i14++) {
            View childAt = this.rgRowIndex.getChildAt(i14);
            if (childAt != null) {
                childAt.setVisibility(0);
            }
        }
        String row_pos = this.keyInfo.getRow_pos();
        this.rowList = new ArrayList();
        if (!TextUtils.isEmpty(row_pos)) {
            for (String str : row_pos.split(";")) {
                this.rowList.add(Integer.valueOf(Integer.parseInt(str)));
            }
        } else {
            for (int i15 = 0; i15 < getArguments().getInt(ROW_COUNT); i15++) {
                this.rowList.add(0);
            }
        }
        this.rbList.add(this.rbStepX);
        this.rbStepX.setOnClickListener(new CustomOnClickListener());
        this.rbList.add(this.rbStepY);
        this.rbStepY.setOnClickListener(new CustomOnClickListener());
        this.rbList.add(this.rbStepZ);
        this.rbStepZ.setOnClickListener(new CustomOnClickListener());
        this.rbList.add(this.rbRow);
        this.rbRow.setOnClickListener(new CustomOnClickListener());
        changeRow(0);
        addDisposable((Disposable) RxView.clicks(this.ivXAdd).throttleFirst(500L, TimeUnit.MILLISECONDS).subscribe(obj -> {

            if (!DuplicateDimpleDataFragment.this.aligned) {
                ToastUtil.showToast(DuplicateDimpleDataFragment.this.getString(R.string.please_locate_first));
                return;
            }
            DuplicateDimpleDataFragment duplicateDimpleDataFragment = DuplicateDimpleDataFragment.this;
            duplicateDimpleDataFragment.showLoadingDialog(duplicateDimpleDataFragment.getString(R.string.waitting), true);
            DuplicateDimpleDataFragment duplicateDimpleDataFragment2 = DuplicateDimpleDataFragment.this;
            int currentX = OperationManager.getInstance().getCurrentX();
            DuplicateDimpleDataFragment duplicateDimpleDataFragment3 = DuplicateDimpleDataFragment.this;
            duplicateDimpleDataFragment2.x = currentX + duplicateDimpleDataFragment3.getStep(duplicateDimpleDataFragment3.rbStepX);
            DuplicateDimpleDataFragment.this.y = OperationManager.getInstance().getCurrentY();
            DuplicateDimpleDataFragment.this.z = OperationManager.getInstance().getCurrentZ();
            OperationManager.getInstance().move(0, DuplicateDimpleDataFragment.this.x, DuplicateDimpleDataFragment.this.y, DuplicateDimpleDataFragment.this.z);

        }));
        addDisposable((Disposable) RxView.clicks(this.ivYAdd).throttleFirst(500L, TimeUnit.MILLISECONDS).subscribe(obj -> { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleDataFragment.2
            if (!DuplicateDimpleDataFragment.this.aligned) {
                ToastUtil.showToast(DuplicateDimpleDataFragment.this.getString(R.string.please_locate_first));
                return;
            }
            DuplicateDimpleDataFragment duplicateDimpleDataFragment = DuplicateDimpleDataFragment.this;
            duplicateDimpleDataFragment.showLoadingDialog(duplicateDimpleDataFragment.getString(R.string.waitting), true);
            DuplicateDimpleDataFragment duplicateDimpleDataFragment2 = DuplicateDimpleDataFragment.this;
            int currentY = OperationManager.getInstance().getCurrentY();
            DuplicateDimpleDataFragment duplicateDimpleDataFragment3 = DuplicateDimpleDataFragment.this;
            duplicateDimpleDataFragment2.y = currentY + duplicateDimpleDataFragment3.getStep(duplicateDimpleDataFragment3.rbStepY);
            DuplicateDimpleDataFragment.this.x = OperationManager.getInstance().getCurrentX();
            DuplicateDimpleDataFragment.this.z = OperationManager.getInstance().getCurrentZ();
            OperationManager.getInstance().move(0, DuplicateDimpleDataFragment.this.x, DuplicateDimpleDataFragment.this.y, DuplicateDimpleDataFragment.this.z);
        }));
        addDisposable((Disposable) RxView.clicks(this.ivZAdd).throttleFirst(500L, TimeUnit.MILLISECONDS).subscribe(obj -> { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleDataFragment.3
            if (!DuplicateDimpleDataFragment.this.aligned) {
                ToastUtil.showToast(DuplicateDimpleDataFragment.this.getString(R.string.please_locate_first));
                return;
            }
            DuplicateDimpleDataFragment duplicateDimpleDataFragment = DuplicateDimpleDataFragment.this;
            duplicateDimpleDataFragment.showLoadingDialog(duplicateDimpleDataFragment.getString(R.string.waitting), true);
            DuplicateDimpleDataFragment.this.x = OperationManager.getInstance().getCurrentX();
            DuplicateDimpleDataFragment.this.y = OperationManager.getInstance().getCurrentY();
            DuplicateDimpleDataFragment duplicateDimpleDataFragment2 = DuplicateDimpleDataFragment.this;
            int currentZ = OperationManager.getInstance().getCurrentZ();
            DuplicateDimpleDataFragment duplicateDimpleDataFragment3 = DuplicateDimpleDataFragment.this;
            duplicateDimpleDataFragment2.z = currentZ + duplicateDimpleDataFragment3.getStep(duplicateDimpleDataFragment3.rbStepZ);
            OperationManager.getInstance().move(0, DuplicateDimpleDataFragment.this.x, DuplicateDimpleDataFragment.this.y, DuplicateDimpleDataFragment.this.z);
        }));
        addDisposable((Disposable) RxView.clicks(this.ivXReduce).throttleFirst(500L, TimeUnit.MILLISECONDS).subscribe(obj -> { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleDataFragment.4
            if (!DuplicateDimpleDataFragment.this.aligned) {
                ToastUtil.showToast(DuplicateDimpleDataFragment.this.getString(R.string.please_locate_first));
                return;
            }
            DuplicateDimpleDataFragment duplicateDimpleDataFragment = DuplicateDimpleDataFragment.this;
            duplicateDimpleDataFragment.showLoadingDialog(duplicateDimpleDataFragment.getString(R.string.waitting), true);
            DuplicateDimpleDataFragment duplicateDimpleDataFragment2 = DuplicateDimpleDataFragment.this;
            int currentX = OperationManager.getInstance().getCurrentX();
            DuplicateDimpleDataFragment duplicateDimpleDataFragment3 = DuplicateDimpleDataFragment.this;
            duplicateDimpleDataFragment2.x = currentX - duplicateDimpleDataFragment3.getStep(duplicateDimpleDataFragment3.rbStepX);
            DuplicateDimpleDataFragment.this.y = OperationManager.getInstance().getCurrentY();
            DuplicateDimpleDataFragment.this.z = OperationManager.getInstance().getCurrentZ();
            OperationManager.getInstance().move(0, DuplicateDimpleDataFragment.this.x, DuplicateDimpleDataFragment.this.y, DuplicateDimpleDataFragment.this.z);
        }));
        addDisposable((Disposable) RxView.clicks(this.ivYReduce).throttleFirst(500L, TimeUnit.MILLISECONDS).subscribe(obj -> { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleDataFragment.5

                if (!DuplicateDimpleDataFragment.this.aligned) {
                    ToastUtil.showToast(DuplicateDimpleDataFragment.this.getString(R.string.please_locate_first));
                    return;
                }
                DuplicateDimpleDataFragment duplicateDimpleDataFragment = DuplicateDimpleDataFragment.this;
                duplicateDimpleDataFragment.showLoadingDialog(duplicateDimpleDataFragment.getString(R.string.waitting), true);
                DuplicateDimpleDataFragment duplicateDimpleDataFragment2 = DuplicateDimpleDataFragment.this;
                int currentY = OperationManager.getInstance().getCurrentY();
                DuplicateDimpleDataFragment duplicateDimpleDataFragment3 = DuplicateDimpleDataFragment.this;
                duplicateDimpleDataFragment2.y = currentY - duplicateDimpleDataFragment3.getStep(duplicateDimpleDataFragment3.rbStepY);
                DuplicateDimpleDataFragment.this.x = OperationManager.getInstance().getCurrentX();
                DuplicateDimpleDataFragment.this.z = OperationManager.getInstance().getCurrentZ();
                OperationManager.getInstance().move(0, DuplicateDimpleDataFragment.this.x, DuplicateDimpleDataFragment.this.y, DuplicateDimpleDataFragment.this.z);

        }));
        addDisposable((Disposable) RxView.clicks(this.ivZReduce).throttleFirst(1L, TimeUnit.SECONDS).subscribe(obj -> { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleDataFragment.6

                if (!DuplicateDimpleDataFragment.this.aligned) {
                    ToastUtil.showToast(DuplicateDimpleDataFragment.this.getString(R.string.please_locate_first));
                    return;
                }
                DuplicateDimpleDataFragment duplicateDimpleDataFragment = DuplicateDimpleDataFragment.this;
                duplicateDimpleDataFragment.showLoadingDialog(duplicateDimpleDataFragment.getString(R.string.waitting), true);
                DuplicateDimpleDataFragment.this.x = OperationManager.getInstance().getCurrentX();
                DuplicateDimpleDataFragment.this.y = OperationManager.getInstance().getCurrentY();
                DuplicateDimpleDataFragment duplicateDimpleDataFragment2 = DuplicateDimpleDataFragment.this;
                int currentZ = OperationManager.getInstance().getCurrentZ();
                DuplicateDimpleDataFragment duplicateDimpleDataFragment3 = DuplicateDimpleDataFragment.this;
                duplicateDimpleDataFragment2.z = currentZ - duplicateDimpleDataFragment3.getStep(duplicateDimpleDataFragment3.rbStepZ);
                OperationManager.getInstance().move(0, DuplicateDimpleDataFragment.this.x, DuplicateDimpleDataFragment.this.y, DuplicateDimpleDataFragment.this.z);

        }));
        addDisposable((Disposable) RxView.clicks(this.btSave).throttleFirst(1L, TimeUnit.SECONDS).subscribe(obj -> { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleDataFragment.7
                if (DuplicateDimpleDataFragment.this.aligned) {
                    if (!DuplicateDimpleDataFragment.this.dataNotComplete()) {
                        DuplicateDimpleDataFragment.this.showEditDialog();
                        return;
                    } else {
                        ToastUtil.showToast(R.string.please_complete_the_data);
                        return;
                    }
                }
                ToastUtil.showToast(DuplicateDimpleDataFragment.this.getString(R.string.please_locate_first));

        }));
        addDisposable((Disposable) RxView.clicks(this.btDecode).throttleFirst(1L, TimeUnit.SECONDS).subscribe(obj -> { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleDataFragment.8
            if (!DuplicateDimpleDataFragment.this.dataNotComplete()) {
                if (ClampManager.getInstance().checkHasCalibrated(DuplicateDimpleDataFragment.this.keyInfo)) {
                    new DecodeDialog(DuplicateDimpleDataFragment.this.getActivity(), DuplicateDimpleDataFragment.this.dataParam, true).show();
                    return;
                }
                return;
            }
            ToastUtil.showToast(R.string.please_complete_the_data);
        }));
        addDisposable((Disposable) RxView.clicks(this.btLocation).throttleFirst(1L, TimeUnit.SECONDS).subscribe(obj -> { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleDataFragment.9
            DuplicateDimpleDataFragment duplicateDimpleDataFragment = DuplicateDimpleDataFragment.this;
            duplicateDimpleDataFragment.showLoadingDialog(duplicateDimpleDataFragment.getString(R.string.waitting), true);
            OperationManager.getInstance().start(DuplicateDimpleDataFragment.this.dataParam, AssetsJsonUtils.getCommonSteps(DuplicateDimpleDataFragment.this.getContext(), AssetsJsonUtils.getKeyDecodeLocationJsonPath(DuplicateDimpleDataFragment.this.keyInfo)), OperateType.DIMPLE_DUPLICATE_LOCATION);
        }));
        addDisposable((Disposable) RxView.clicks(this.btFindSpace).throttleFirst(1L, TimeUnit.SECONDS).subscribe(obj -> { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleDataFragment.10
            int indexOf = 0;
            if (DuplicateDimpleDataFragment.this.aligned) {
                final int size = (DuplicateDimpleDataFragment.this.spaceList.get(DuplicateDimpleDataFragment.this.rowIndex)).size();
                int i16 = 0;
                if (DuplicateDimpleDataFragment.this.lastRb != null && DuplicateDimpleDataFragment.this.rbList.indexOf(DuplicateDimpleDataFragment.this.lastRb) - 4 >= 0) {
                    i16 = indexOf;
                }
                DimpleSpaceSelectDialog dimpleSpaceSelectDialog = new DimpleSpaceSelectDialog(DuplicateDimpleDataFragment.this.getContext(), size, i16);
                dimpleSpaceSelectDialog.show();
                dimpleSpaceSelectDialog.setOnConfirm(new DimpleSpaceSelectDialog.OnConfirmListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleDataFragment.10.1
                    @Override // com.kkkcut.e20j.ui.dialog.DimpleSpaceSelectDialog.OnConfirmListener
                    public void onConfirm(int i17) {
                        DuplicateDimpleDataFragment.this.rbList.get((DuplicateDimpleDataFragment.this.rbList.size() - size) + i17).performClick();
                        DuplicateDimpleDataFragment.this.showLoadingDialog(DuplicateDimpleDataFragment.this.getString(R.string.waitting), true);
                        int clampFace = OperationManager.getInstance().getKeyAlignInfo().getClampFace() + ClampManager.getInstance().getS1B().getHigh1();
                        ArrayList arrayList2 = new ArrayList();
                        arrayList2.add(new StepBean(0, DuplicateDimpleDataFragment.this.x, DuplicateDimpleDataFragment.this.y, DuplicateDimpleDataFragment.this.z, "", "C:5,X;C:5,Y;C:5,Z", false));
                        arrayList2.add(new StepBean(1, 0, 0, clampFace, "", "C:5,Z;DCDS:1," + (i17 + 1), false));
                        arrayList2.add(new StepBean(0, 0, 0, -400, "", "U:Z;"));
                        DuplicateDimpleDataFragment.this.dataParam.setPauseTime(DuplicateDimpleDataFragment.this.sleep);
                        OperationManager.getInstance().start(DuplicateDimpleDataFragment.this.dataParam, arrayList2, OperateType.DUPLICATE_DIMPLE_DECODE_SINGLE);
                    }
                });
                return;
            }
            ToastUtil.showToast(DuplicateDimpleDataFragment.this.getString(R.string.please_locate_first));
        }));
        this.tvSleep.setText(String.valueOf(this.sleep));
        initParam();
    }

    private void initParam() {
        this.dataParam.setKeyInfo(this.keyInfo);
        this.dataParam.setDecoderSize(100);
        this.dataParam.setClamp(ClampUtil.getClamp(this.keyInfo));
        this.dataParam.setClampMode(ClampUtil.getClampMode(this.keyInfo));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showEditDialog() {
        EditDialog editDialog = new EditDialog(getContext());
        editDialog.setTip(getString(R.string.enter_remarks));
        editDialog.setDialogBtnCallback(new EditDialog.DialogInputFinishCallBack() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleDataFragment$$ExternalSyntheticLambda0
            @Override // com.kkkcut.e20j.ui.dialog.EditDialog.DialogInputFinishCallBack
            public final void onDialogButClick(String str) {
                DuplicateDimpleDataFragment.this.m51x8c6c2cc7(str);
            }
        });
        editDialog.setContentNullable(true);
        editDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: collectKey, reason: merged with bridge method [inline-methods] */
    public void m51x8c6c2cc7(String str) {
        this.keyInfo.setWidth(OperationManager.getInstance().getKeyAlignInfo().getDecodeWidth());
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        StringBuilder sb3 = new StringBuilder();
        StringBuilder sb4 = new StringBuilder();
        int i = getArguments() != null ? getArguments().getInt(ROW_COUNT) : 1;
        for (int i2 = 0; i2 < i; i2++) {
            List<Integer> list = this.spaceList.get(i2);
            if (i2 == 0) {
                sb.append(list.size());
            } else {
                sb.append("-");
                sb.append(list.size());
            }
            if (getArguments().getBoolean(ZIMUZHU)) {
                for (int i3 = 0; i3 < 2; i3++) {
                    for (int i4 = 0; i4 < list.size(); i4++) {
                        sb2.append(list.get(i4));
                        if (i3 == 0) {
                            sb3.append("-200");
                        } else {
                            sb3.append("0");
                        }
                        if (i4 != list.size() - 1) {
                            sb2.append(",");
                            sb3.append(",");
                        } else {
                            sb2.append(";");
                            sb3.append(";");
                        }
                    }
                    sb4.append(this.rowList.get(i2));
                    sb4.append(";");
                }
            } else {
                for (int i5 = 0; i5 < list.size(); i5++) {
                    sb2.append(list.get(i5));
                    sb3.append("0");
                    if (i5 != list.size() - 1) {
                        sb2.append(",");
                        sb3.append(",");
                    } else {
                        sb2.append(";");
                        sb3.append(";");
                    }
                }
                sb4.append(this.rowList.get(i2));
                sb4.append(";");
            }
        }
        this.keyInfo.setCuts(sb.toString());
        this.keyInfo.setSpaceStr(sb2.toString());
        this.keyInfo.setSpaceWidthStr(sb3.toString());
        this.keyInfo.setRow_pos(sb4.toString());
        this.keyInfo.setType_specific_info("");
        DuplicateDimple duplicateDimple = new DuplicateDimple();
        duplicateDimple.setType(this.keyInfo.getType());
        duplicateDimple.setAlign(this.keyInfo.getAlign());
        duplicateDimple.setWidth(this.keyInfo.getWidth());
        duplicateDimple.setThick(this.keyInfo.getThick());
        duplicateDimple.setRow_pos(this.keyInfo.getRow_pos());
        duplicateDimple.setRow_count(getArguments() != null ? getArguments().getInt(ROW_COUNT) : 1);
        duplicateDimple.setSpace(this.keyInfo.getSpaceStr());
        duplicateDimple.setSpace_width(this.keyInfo.getSpaceWidthStr());
        duplicateDimple.setDepth(this.keyInfo.getDepthStr());
        duplicateDimple.setDepth_name(this.keyInfo.getDepthName());
        duplicateDimple.setParameter_info("");
        duplicateDimple.setKeyname(str);
        ClampBean clampBean = this.keyInfo.getClampBean();
        duplicateDimple.setClampNum(clampBean.getClampNum());
        duplicateDimple.setClampSide(clampBean.getClampSide());
        duplicateDimple.setClampSlot(clampBean.getClampSlot());
        duplicateDimple.setTime(new Date().getTime());
        UserDataDaoManager.getInstance(getContext()).saveDuplicateDimpleKey(duplicateDimple);
        ToastUtil.showToast(R.string.saved_successfully);
        EventBus.getDefault().post(new EventCenter(46));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean dataNotComplete() {
        if (TextUtils.isEmpty(this.rbRow.getText().toString().trim())) {
            rbFocus(this.rbRow);
            return true;
        }
        Iterator<List<Integer>> it = this.spaceList.iterator();
        while (it.hasNext()) {
            Iterator<Integer> it2 = it.next().iterator();
            while (it2.hasNext()) {
                if (it2.next().intValue() == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public void rbFocus(RadioButton radioButton) {
        this.lastRb.setChecked(false);
        radioButton.setChecked(true);
        this.lastRb = radioButton;
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x017d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onClick(View r6) {
        /*
            Method dump skipped, instructions count: 466
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleDataFragment.onClick(android.view.View):void");
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (z) {
            switch (compoundButton.getId()) {
                case R.id.rb_row_1 /* 2131362637 */:
                    changeRow(0);
                    return;
                case R.id.rb_row_2 /* 2131362638 */:
                    changeRow(1);
                    return;
                case R.id.rb_row_3 /* 2131362639 */:
                    changeRow(2);
                    return;
                case R.id.rb_row_4 /* 2131362640 */:
                    changeRow(3);
                    return;
                default:
                    return;
            }
        }
    }

    private void changeRow(int i) {
        this.rowIndex = i;
        int i2 = getArguments() != null ? getArguments().getInt(SPACE_COUNT) : 5;
        List<List<Integer>> list = this.spaceList;
        List<Integer> list2 = list != null ? list.get(i) : null;
        if (list2 != null && list2.size() > 0) {
            i2 = list2.size();
        }
        this.tvSpace.setText(String.valueOf(i2));
        this.gridLayout.removeAllViews();
        for (int i3 = 0; i3 < i2; i3++) {
            getLayoutInflater().inflate(R.layout.item_duplicate_dimple_space_input, this.gridLayout);
        }
        for (int i4 = 0; i4 < this.gridLayout.getChildCount(); i4++) {
            RadioButton radioButton = (RadioButton) this.gridLayout.getChildAt(i4);
            if (list2 != null && list2.size() > 0 && i4 < i2 && list2.get(i4).intValue() > 0) {
                radioButton.setText(String.valueOf(list2.get(i4)));
            } else {
                radioButton.setHint(String.valueOf(i4 + 1));
            }
        }
        Integer num = this.rowList.get(i);
        if (num.intValue() == 0) {
            this.rbRow.setText("");
            this.rbRow.setHint("0");
        } else {
            this.rbRow.setText(String.valueOf(num));
        }
        this.rbList = this.rbList.subList(0, 4);
        for (int i5 = 0; i5 < this.gridLayout.getChildCount(); i5++) {
            RadioButton radioButton2 = (RadioButton) this.gridLayout.getChildAt(i5);
            radioButton2.setOnClickListener(new CustomOnClickListener());
            this.rbList.add(radioButton2);
        }
        this.gridLayout.getChildAt(0).performClick();
    }

    @Override // com.kkkcut.e20j.base.BaseFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void onEventComing(EventCenter eventCenter) {
        float f;
        float f2;
        float tip;
        float f3;
        int eventCode = eventCenter.getEventCode();
        if (eventCode == 0) {
            Bundle bundle = (Bundle) eventCenter.getData();
            int i = bundle.getInt("row");
            int i2 = bundle.getInt("column");
            if (this.keyInfo.getType() == 6 || this.keyInfo.getType() == 8) {
                f = bundle.getInt("depth");
                f2 = MachineData.dZScale;
            } else {
                f = bundle.getInt("depth");
                f2 = MachineData.dXScale;
            }
            int i3 = (int) (f * f2);
            LogUtil.i(TAG, "depth: " + i3);
            KeyInfo keyInfo = this.keyInfo;
            this.keyInfo.setKeyToothCode(keyInfo.changeSingleDepth(i + (-1), i2 + (-1), (float) i3, false, keyInfo.getReadBittingRule()));
            return;
        }
        if (eventCode == 1) {
            this.dimpleCutIndex = 0;
            ToolSizeManager.setCutterSize(ToolSizeManager.DimpleCutterSize);
            ToolSizeManager.setDecoderSize(100);
            if (this.keyInfo.getType() == 6 && this.keyInfo.getSpaceWidthStr().contains("-")) {
                showDimpleOperationDialog();
                return;
            } else {
                showClearClampRemind(1);
                return;
            }
        }
        if (eventCode != 32) {
            if (eventCode != 33) {
                if (eventCode != 39) {
                    return;
                }
                ToolSizeManager.setDecoderSize(100);
                showClearClampRemind(39);
                return;
            }
            this.dimpleCutIndex = 0;
            dismissLoadingDialog();
            showErrorDialog(getContext(), (ErrorBean) eventCenter.getData());
            return;
        }
        OperateType operateType = (OperateType) eventCenter.getData();
        if (operateType == OperateType.MOVE_XYZ) {
            dismissLoadingDialog();
            return;
        }
        if (operateType == OperateType.DIMPLE_DUPLICATE_LOCATION) {
            dismissLoadingDialog();
            this.keyInfo.setWidth(OperationManager.getInstance().getKeyAlignInfo().getDecodeWidth());
            this.aligned = true;
            return;
        }
        if (operateType == OperateType.DUPLICATE_DIMPLE_DECODE_SINGLE) {
            KeyAlignInfo keyAlignInfo = OperationManager.getInstance().getKeyAlignInfo();
            int left = (int) (((keyAlignInfo.getLeft() - ToolSizeManager.getDecoderRadius2()) - this.x) * MachineData.dXScale);
            this.rbRow.setText(String.valueOf(Math.abs(left)));
            this.rowList.set(this.rowIndex, Integer.valueOf(left));
            if (this.keyInfo.getAlign() == 0) {
                tip = keyAlignInfo.getShoulder() - this.y;
                f3 = MachineData.dYScale;
            } else {
                tip = this.y - keyAlignInfo.getTip();
                f3 = MachineData.dYScale;
            }
            int i4 = (int) (tip * f3);
            RadioButton radioButton = this.lastRb;
            if (radioButton != null) {
                radioButton.setText(String.valueOf(i4));
                int indexOf = this.rbList.indexOf(this.lastRb) - 4;
                if (this.rowIndex < this.spaceList.size() && indexOf >= 0) {
                    this.spaceList.get(this.rowIndex).set(indexOf, Integer.valueOf(Integer.parseInt(this.lastRb.getText().toString().trim())));
                }
            }
            dismissLoadingDialog();
            return;
        }
        if (operateType == OperateType.KEY_BLANK_DECODE_LOCATION) {
            this.aligned = true;
            this.keyInfo.setWidth(OperationManager.getInstance().getKeyAlignInfo().getDecodeWidth());
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            StringBuilder sb3 = new StringBuilder();
            StringBuilder sb4 = new StringBuilder();
            int i5 = getArguments() != null ? getArguments().getInt(ROW_COUNT) : 1;
            for (int i6 = 0; i6 < i5; i6++) {
                List<Integer> list = this.spaceList.get(i6);
                if (i6 == 0) {
                    sb.append(list.size());
                } else {
                    sb.append("-");
                    sb.append(list.size());
                }
                if (getArguments().getBoolean(ZIMUZHU)) {
                    for (int i7 = 0; i7 < 2; i7++) {
                        for (int i8 = 0; i8 < list.size(); i8++) {
                            sb2.append(list.get(i8));
                            if (i7 == 0) {
                                sb3.append("-200");
                            } else {
                                sb3.append("0");
                            }
                            if (i8 != list.size() - 1) {
                                sb2.append(",");
                                sb3.append(",");
                            } else {
                                sb2.append(";");
                                sb3.append(";");
                            }
                        }
                        sb4.append(this.rowList.get(i6));
                        sb4.append(";");
                    }
                } else {
                    for (int i9 = 0; i9 < list.size(); i9++) {
                        sb2.append(list.get(i9));
                        sb3.append("0");
                        if (i9 != list.size() - 1) {
                            sb2.append(",");
                            sb3.append(",");
                        } else {
                            sb2.append(";");
                            sb3.append(";");
                        }
                    }
                    sb4.append(this.rowList.get(i6));
                    sb4.append(";");
                }
            }
            this.keyInfo.setCuts(sb.toString());
            this.keyInfo.setSpaceStr(sb2.toString());
            this.keyInfo.setSpaceWidthStr(sb3.toString());
            this.keyInfo.setRow_pos(sb4.toString());
            this.keyInfo.setType_specific_info("");
            this.dataParam.setPauseTime(this.sleep);
            OperationManager.getInstance().start(this.dataParam, AssetsJsonUtils.getKeyDecodePathSteps(this.keyInfo), OperateType.KEY_BLANK_DECODE_EXECUTE);
            return;
        }
        if (operateType == OperateType.KEY_BLANK_DECODE_EXECUTE) {
            dismissLoadingDialog();
            new CutDialog(getActivity(), this.dataParam, true).show();
            return;
        }
        if (operateType == OperateType.KEY_BLANK_CUT_LOCATION) {
            detectCutterHigh();
            return;
        }
        if (operateType == OperateType.KEY_BLANK_CUT_CUTTER_HIGH) {
            OperationManager.getInstance().startExecution(StepsGenerateUtil.getKeyBlanksCutSteps(this.keyInfo, OperationManager.getInstance().getKeyAlignInfo(), KeyBlankCutStepsGenerateUtil.getDestCutPoint(this.keyInfo, OperationManager.getInstance().getKeyAlignInfo(), "", this.dimpleCutIndex, SPUtils.getInt(SpKeys.SINGLEKEY_CUT_SHAPE, 1)), 1, SPUtils.getBoolean(SpKeys.DIMPLE_UP_DOWN_CUTTING)), OperateType.KEY_BLANK_CUT_EXECUTE);
            return;
        }
        if (operateType == OperateType.KEY_BLANK_CUT_EXECUTE) {
            dismissLoadingDialog();
            CutCountHelper.getInstance().addCutCount(this.keyInfo);
            if (this.keyInfo.getType() == 6 && this.keyInfo.getSpaceWidthStr().contains("-")) {
                this.dimpleCutIndex++;
                showDimpleOperationDialog();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDimpleOperationDialog() {
        int i = this.dimpleCutIndex;
        if (i == 0) {
            showDimpleKeyChangeCutterDialog();
            return;
        }
        if (i == 1) {
            showDimpleKeyTurnOverDialog();
            return;
        }
        if (i == 2) {
            showDimpleKeyChangeCutterDialog();
        } else if (i == 3) {
            showDimpleKeyTurnOverDialog();
        } else {
            this.dimpleCutIndex = 0;
        }
    }

    private void showDimpleKeyChangeCutterDialog() {
        AnglekeyTurningDialog anglekeyTurningDialog = new AnglekeyTurningDialog(getContext());
        if (this.dimpleCutIndex == 0) {
            anglekeyTurningDialog.setRemindMsg(getString(R.string.dimple_cut_remind_1));
        } else {
            anglekeyTurningDialog.setRemindMsg(getString(R.string.dimple_cut_remind_3));
        }
        if (this.dimpleCutIndex == 0) {
            anglekeyTurningDialog.setRemindImg(R.drawable.remind_cutter_dimple_2);
        } else {
            anglekeyTurningDialog.setRemindImg(R.drawable.remind_cutter_dimple_1);
        }
        anglekeyTurningDialog.setDialogBtnCallback(new AnglekeyTurningDialog.DialogBtnCallBack() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleDataFragment.11
            @Override // com.kkkcut.e20j.ui.dialog.AnglekeyTurningDialog.DialogBtnCallBack
            public void onDialogButClick(int i) {
                if (i == 0) {
                    DuplicateDimpleDataFragment.this.dimpleCutIndex = 0;
                    DuplicateDimpleDataFragment.this.dismissLoadingDialog();
                    OperationManager.getInstance().sendOrder(Command.TurnOffSpindle(), OperateType.STOP);
                } else if (i == 1) {
                    DuplicateDimpleDataFragment.access$2308(DuplicateDimpleDataFragment.this);
                    DuplicateDimpleDataFragment.this.showDimpleOperationDialog();
                } else {
                    DuplicateDimpleDataFragment duplicateDimpleDataFragment = DuplicateDimpleDataFragment.this;
                    duplicateDimpleDataFragment.showLoadingDialog(duplicateDimpleDataFragment.getString(R.string.cutting), true);
                    DuplicateDimpleDataFragment.this.startCut();
                }
            }
        });
        anglekeyTurningDialog.show();
    }

    private void showDimpleKeyTurnOverDialog() {
        AnglekeyTurningDialog anglekeyTurningDialog = new AnglekeyTurningDialog(getContext());
        if (this.dimpleCutIndex == 1) {
            anglekeyTurningDialog.setRemindMsg(getString(R.string.dimple_cut_remind_2));
            anglekeyTurningDialog.setRemindImg(R.drawable.turn_over_dimple_1);
        } else {
            anglekeyTurningDialog.setRemindMsg(getString(R.string.dimple_cut_remind_4));
            anglekeyTurningDialog.setRemindImg(R.drawable.turn_over_dimple_2);
        }
        anglekeyTurningDialog.setDialogBtnCallback(new AnglekeyTurningDialog.DialogBtnCallBack() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleDataFragment.12
            @Override // com.kkkcut.e20j.ui.dialog.AnglekeyTurningDialog.DialogBtnCallBack
            public void onDialogButClick(int i) {
                if (i == 0) {
                    DuplicateDimpleDataFragment.this.dimpleCutIndex = 0;
                    DuplicateDimpleDataFragment.this.dismissLoadingDialog();
                    OperationManager.getInstance().sendOrder(Command.TurnOffSpindle(), OperateType.STOP);
                } else if (i == 1) {
                    DuplicateDimpleDataFragment.access$2308(DuplicateDimpleDataFragment.this);
                    DuplicateDimpleDataFragment.this.showDimpleOperationDialog();
                } else {
                    DuplicateDimpleDataFragment duplicateDimpleDataFragment = DuplicateDimpleDataFragment.this;
                    duplicateDimpleDataFragment.showLoadingDialog(duplicateDimpleDataFragment.getString(R.string.cutting), true);
                    DuplicateDimpleDataFragment.this.startCut();
                }
            }
        });
        anglekeyTurningDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startDecode() {
        showLoadingDialog(getString(R.string.decoding), true);
        OperationManager.getInstance().start(this.dataParam, AssetsJsonUtils.getCommonSteps(getContext(), AssetsJsonUtils.getKeyDecodeLocationJsonPath(this.keyInfo)), OperateType.KEY_BLANK_DECODE_LOCATION);
    }

    private void detectCutterHigh() {
        OperationManager.getInstance().start(this.dataParam, AssetsJsonUtils.getCommonSteps(getContext(), AssetsJsonUtils.getKeyCutCutterHeightJsonPath(this.keyInfo)), OperateType.KEY_BLANK_CUT_CUTTER_HIGH);
    }

    private void showClearClampRemind(final int i) {
        RemindDialog remindDialog = new RemindDialog(getContext());
        remindDialog.setCancleBtnVisibility(false);
        remindDialog.setRemindImg(ClampCreator.getClearClampImg(this.keyInfo));
        remindDialog.setRemindMsg(getString(R.string.clean_the_groove_from_chips));
        remindDialog.setDialogBtnCallback(new RemindDialog.DialogBtnCallBack() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleDataFragment.13
            @Override // com.kkkcut.e20j.ui.dialog.RemindDialog.DialogBtnCallBack
            public void onDialogButClick(boolean z) {
                if (z) {
                    if (i == 39) {
                        DuplicateDimpleDataFragment.this.startDecode();
                    }
                    if (i == 1) {
                        DuplicateDimpleDataFragment.this.startCut();
                    }
                }
            }
        });
        if (remindDialog.isShowing()) {
            return;
        }
        remindDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startCut() {
        List<List<Integer>> depthList = this.keyInfo.getDepthList();
        List<List<String>> depthNameList = this.keyInfo.getDepthNameList();
        List<List<String>> toothCodeArray = this.keyInfo.getToothCodeArray();
        int i = 0;
        for (int i2 = 0; i2 < toothCodeArray.size(); i2++) {
            Iterator<String> it = toothCodeArray.get(i2).iterator();
            while (it.hasNext()) {
                i = Math.max(i, this.keyInfo.getDepthByCode(depthList.get(i2), depthNameList.get(i2), it.next()));
            }
        }
        this.keyInfo.setThick(i + 50);
        OperationManager.getInstance().start(this.dataParam, AssetsJsonUtils.getCommonSteps(getContext(), AssetsJsonUtils.getKeyCutLocationJsonPath(this.keyInfo)), OperateType.KEY_BLANK_CUT_LOCATION);
        showLoadingDialog(getString(R.string.cutting), true);
    }


    /* JADX INFO: Access modifiers changed from: private */
    public int getStep(RadioButton radioButton) {
        if (TextUtils.isEmpty(radioButton.getText().toString().trim())) {
            ToastUtil.showToast(R.string.please_complete_the_data);
            return 0;
        }
        int indexOf = this.rbList.indexOf(radioButton);
        var r0 = radioButton.getText().toString().trim();
        if (indexOf == 0) {
            return (int) (Integer.parseInt(r0) / MachineData.dXScale);
        }
        if (indexOf == 1) {
            return (int) (Integer.parseInt(r0) / MachineData.dYScale);
        }
        return (int) (Integer.parseInt(r0) / MachineData.dZScale);
    }


    private void auto() {
        List<Integer> list = this.spaceList.get(this.rowIndex);
        int size = list.size();
        if (size < 2) {
            return;
        }
        int i = -1;
        for (int size2 = this.rbList.size() - size; size2 < this.rbList.size(); size2++) {
            String trim = this.rbList.get(size2).getText().toString().trim();
            if (!TextUtils.isEmpty(trim)) {
                if (i != -1) {
                    int parseInt = Integer.parseInt(this.rbList.get(i).getText().toString().trim());
                    int parseInt2 = (parseInt - Integer.parseInt(trim)) / (i - size2);
                    for (int size3 = this.rbList.size() - size; size3 < this.rbList.size(); size3++) {
                        int i2 = ((size3 - i) * parseInt2) + parseInt;
                        this.rbList.get(size3).setText(String.valueOf(i2));
                        list.set(size3 - 4, Integer.valueOf(i2));
                    }
                    return;
                }
                i = size2;
            }
        }
    }

    private void changeLast() {
        int indexOf;
        RadioButton radioButton = this.lastRb;
        if (radioButton == null || (indexOf = this.rbList.indexOf(radioButton)) == 0) {
            return;
        }
        this.lastRb.setChecked(false);
        RadioButton radioButton2 = this.rbList.get(indexOf - 1);
        this.lastRb = radioButton2;
        radioButton2.setChecked(true);
    }

    private void changeNext() {
        int indexOf;
        RadioButton radioButton = this.lastRb;
        if (radioButton == null || (indexOf = this.rbList.indexOf(radioButton)) == this.rbList.size() - 1) {
            return;
        }
        this.lastRb.setChecked(false);
        RadioButton radioButton2 = this.rbList.get(indexOf + 1);
        this.lastRb = radioButton2;
        radioButton2.setChecked(true);
    }

    private void delete() {
        RadioButton radioButton = this.lastRb;
        if (radioButton != null) {
            CharSequence text = radioButton.getText();
            if (text.length() > 0) {
                this.lastRb.setText(text.subSequence(0, text.length() - 1));
            }
        }
    }

    private void inputNumb(String str) {
        RadioButton radioButton = this.lastRb;
        if (radioButton != null) {
            radioButton.append(str);
            int indexOf = this.rbList.indexOf(this.lastRb) - 4;
            if (this.rowIndex < this.spaceList.size() && indexOf >= 0) {
                this.spaceList.get(this.rowIndex).set(indexOf, Integer.valueOf(Integer.parseInt(this.lastRb.getText().toString().trim())));
            }
            if (indexOf == -1) {
                this.rowList.set(this.rowIndex, Integer.valueOf(Integer.parseInt(this.lastRb.getText().toString().trim())));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class CustomOnClickListener implements View.OnClickListener {
        private CustomOnClickListener() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (DuplicateDimpleDataFragment.this.lastRb != null) {
                DuplicateDimpleDataFragment.this.lastRb.setChecked(false);
            }
            if (view instanceof RadioButton) {
                RadioButton radioButton = (RadioButton) view;
                radioButton.setChecked(true);
                DuplicateDimpleDataFragment.this.lastRb = radioButton;
            }
        }
    }
}
