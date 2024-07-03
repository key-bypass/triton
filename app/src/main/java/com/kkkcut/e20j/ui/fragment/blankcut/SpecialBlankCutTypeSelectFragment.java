package com.kkkcut.e20j.ui.fragment.blankcut;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kkkcut.e20j.ui.fragment.BaseBackFragment;
import com.kkkcut.e20j.ui.fragment.blankcut.paramset.BlankCutCreateGrooveFragment;
import com.kkkcut.e20j.ui.fragment.blankcut.paramset.BlankCutDrillingFragment;
import com.kkkcut.e20j.ui.fragment.blankcut.paramset.BlankCutHeadFragment;
import com.kkkcut.e20j.ui.fragment.blankcut.paramset.BlankCutKeyTipFragment;
import com.kkkcut.e20j.ui.fragment.blankcut.paramset.BlankCutLXP90To80KFragment;
import com.kkkcut.e20j.ui.fragment.blankcut.paramset.BlankCutLeftGrooveFragment;
import com.kkkcut.e20j.ui.fragment.blankcut.paramset.BlankCutLengthFragment;
import com.kkkcut.e20j.ui.fragment.blankcut.paramset.BlankCutRightGrooveFragment;
import com.kkkcut.e20j.ui.fragment.blankcut.paramset.BlankCutSideGrooveFragment;
import com.cutting.machine.MachineInfo;
import com.kkkcut.e20j.us.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class SpecialBlankCutTypeSelectFragment extends BaseBackFragment implements BaseQuickAdapter.OnItemChildClickListener {
    BlankCutAdapter blankCutAdapter;
    private BlankCutType blankCutType;

    Button btOk;

    Button btPreset;

    ImageView ivShow;

    RecyclerView rvBlankCut;

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_modify_key_blank_type;
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        this.btPreset.setVisibility(8);
        ArrayList arrayList = new ArrayList();
        if (MachineInfo.isChineseMachine()) {
            s1Clamp(arrayList);
        } else {
            s8Clamp(arrayList);
        }
        BlankCutAdapter blankCutAdapter = new BlankCutAdapter(arrayList);
        this.blankCutAdapter = blankCutAdapter;
        blankCutAdapter.setOnItemChildClickListener(this);
        this.rvBlankCut.setLayoutManager(new GridLayoutManager(getContext(), 2));
        this.rvBlankCut.setAdapter(this.blankCutAdapter);
        this.ivShow.setImageResource(R.drawable.blank_cut_width_big);
    }

    private void s8Clamp(List<BlankCutBean> list) {
        BlankCutBean blankCutBean = new BlankCutBean(BlankCutType.Toyota80K, "Repair Width Of 80K-Series Keys", R.drawable.blank_cut_width_big);
        blankCutBean.setChecked(true);
        list.add(blankCutBean);
        list.add(new BlankCutBean(BlankCutType.k9ToLxp90, "Create K9 From LXP90", R.drawable.blank_cut_width_big));
        list.add(new BlankCutBean(BlankCutType.K4080K, getString(R.string.key_40k_80k), R.drawable.blank_cut_k40k80));
        list.add(new BlankCutBean(BlankCutType.HY18, "Create HY18 From KK12", R.drawable.blank_cut_hy18));
        list.add(new BlankCutBean(BlankCutType.HY18R, "Create HY18R From KK12", R.drawable.blank_cut_hy18));
        list.add(new BlankCutBean(BlankCutType.KW16ToKW15, "Create KW15 From KW16", R.drawable.blank_cut_kw16_kw15));
        list.add(new BlankCutBean(BlankCutType.KW14ToKW15, "Create KW14 From KW15", R.drawable.blank_cut_kw16_kw15));
    }

    private void s1Clamp(List<BlankCutBean> list) {
        BlankCutBean blankCutBean = new BlankCutBean(BlankCutType.THICKNESS, getString(R.string.create_thickness), R.drawable.blank_cut_thickness_big);
        blankCutBean.setChecked(true);
        list.add(blankCutBean);
        list.add(new BlankCutBean(BlankCutType.WIDTH, getString(R.string.create_width), R.drawable.blank_cut_width_big));
        list.add(new BlankCutBean(BlankCutType.LEFT_GROOVE, getString(R.string.left_groove), R.drawable.blank_cut_left_groove));
        list.add(new BlankCutBean(BlankCutType.RIGHT_GROOVE, getString(R.string.right_groove), R.drawable.blank_cut_right_groove));
    }

    public static SpecialBlankCutTypeSelectFragment newInstance() {
        Bundle bundle = new Bundle();
        SpecialBlankCutTypeSelectFragment specialBlankCutTypeSelectFragment = new SpecialBlankCutTypeSelectFragment();
        specialBlankCutTypeSelectFragment.setArguments(bundle);
        return specialBlankCutTypeSelectFragment;
    }

    public void onclick(View view) {
        BlankCutAdapter blankCutAdapter;
        if (view.getId() == R.id.bt_ok && (blankCutAdapter = this.blankCutAdapter) != null) {
            for (BlankCutBean blankCutBean : blankCutAdapter.getData()) {
                if (blankCutBean.isChecked()) {
                    switch (AnonymousClass1.$SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType[blankCutBean.getBlankCutType().ordinal()]) {
                        case 1:
                            start(BlankCutHeadFragment.newInstance(blankCutBean));
                            break;
                        case 2:
                            start(BlankCutHeadFragment.newInstance(blankCutBean));
                            break;
                        case 3:
                        case 4:
                        case 5:
                            start(BlankCutHeadFragment.newInstance(blankCutBean));
                            break;
                        case 6:
                            start(BlankCutDrillingFragment.newInstance(blankCutBean));
                            break;
                        case 7:
                            start(BlankCutLeftGrooveFragment.newInstance(blankCutBean));
                            break;
                        case 8:
                            start(BlankCutRightGrooveFragment.newInstance(blankCutBean));
                            break;
                        case 9:
                            start(BlankCutKeyTipFragment.newInstance(blankCutBean));
                            break;
                        case 10:
                        case 11:
                        case 12:
                        case 13:
                            start(BlankCutCreateGrooveFragment.newInstance(blankCutBean));
                            break;
                        case 14:
                            start(BlankCutSideGrooveFragment.newInstance(blankCutBean));
                            break;
                        case 15:
                        case 16:
                            start(BlankCutLengthFragment.newInstance(blankCutBean));
                            break;
                        case 17:
                            start(BlankCutLXP90To80KFragment.newInstance(blankCutBean));
                            break;
                    }
                }
            }
        }
    }

    /* renamed from: com.kkkcut.e20j.ui.fragment.blankcut.SpecialBlankCutTypeSelectFragment$1, reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType;

        static {
            int[] iArr = new int[BlankCutType.values().length];
            $SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType = iArr;
            try {
                iArr[BlankCutType.KEY_HEAD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType[BlankCutType.THICKNESS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType[BlankCutType.WIDTH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType[BlankCutType.k9ToLxp90.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType[BlankCutType.Toyota80K.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType[BlankCutType.DRILLING.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType[BlankCutType.LEFT_GROOVE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType[BlankCutType.RIGHT_GROOVE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType[BlankCutType.KEY_TIP.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType[BlankCutType.HY18.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType[BlankCutType.HY18R.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType[BlankCutType.CREATE_GROOVE.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType[BlankCutType.K4080K.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType[BlankCutType.SIDE_GROOVE.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType[BlankCutType.KW16ToKW15.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType[BlankCutType.KW14ToKW15.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType[BlankCutType.LXP90To80K.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
        }
    }

    @Override // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    public String setTitleStr() {
        return getString(R.string.blank_cut);
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemChildClickListener
    public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        List data = baseQuickAdapter.getData();
        Iterator it = data.iterator();
        while (it.hasNext()) {
            ((BlankCutBean) it.next()).setChecked(false);
        }
        BlankCutBean blankCutBean = (BlankCutBean) data.get(i);
        blankCutBean.setChecked(true);
        baseQuickAdapter.setNewData(data);
        this.ivShow.setImageResource(blankCutBean.getDrawRes());
    }
}
