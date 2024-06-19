package com.kkkcut.e20j.ui.fragment.blankcut;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kkkcut.e20j.ui.fragment.BaseBackFragment;
import com.kkkcut.e20j.ui.fragment.blankcut.paramset.BlankCutCreateGrooveFragment;
import com.kkkcut.e20j.ui.fragment.blankcut.paramset.BlankCutDrillingFragment;
import com.kkkcut.e20j.ui.fragment.blankcut.paramset.BlankCutHeadFragment;
import com.kkkcut.e20j.ui.fragment.blankcut.paramset.BlankCutKeyTipFragment;
import com.kkkcut.e20j.ui.fragment.blankcut.paramset.BlankCutLeftGrooveFragment;
import com.kkkcut.e20j.ui.fragment.blankcut.paramset.BlankCutRightGrooveFragment;
import com.kkkcut.e20j.ui.fragment.blankcut.paramset.BlankCutSideGrooveFragment;
import com.kkkcut.e20j.us.R;
import com.liying.core.MachineInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class KeyBlankCutTypeSelectFragment extends BaseBackFragment implements BaseQuickAdapter.OnItemChildClickListener {
    BlankCutAdapter blankCutAdapter;
    private BlankCutType blankCutType;

    @BindView(R.id.bt_ok)
    Button btOk;

    @BindView(R.id.bt_preset)
    Button btPreset;

    @BindView(R.id.iv_show)
    ImageView ivShow;

    @BindView(R.id.rv_blank_cut)
    RecyclerView rvBlankCut;

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_modify_key_blank_type;
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        ArrayList arrayList = new ArrayList();
        if (MachineInfo.isChineseMachine()) {
            s1Clamp(arrayList);
            this.btPreset.setVisibility(8);
        } else {
            s8Clamp(arrayList);
        }
        BlankCutAdapter blankCutAdapter = new BlankCutAdapter(arrayList);
        this.blankCutAdapter = blankCutAdapter;
        blankCutAdapter.setOnItemChildClickListener(this);
        this.rvBlankCut.setLayoutManager(new GridLayoutManager(getContext(), 2));
        this.rvBlankCut.setAdapter(this.blankCutAdapter);
    }

    private void s8Clamp(List<BlankCutBean> list) {
        BlankCutBean blankCutBean = new BlankCutBean(BlankCutType.KEY_HEAD, getString(R.string.cut_blank_head), R.drawable.blank_cut_head_big);
        blankCutBean.setChecked(true);
        list.add(blankCutBean);
        list.add(new BlankCutBean(BlankCutType.THICKNESS, getString(R.string.create_thickness), R.drawable.blank_cut_thickness_big));
        list.add(new BlankCutBean(BlankCutType.WIDTH, getString(R.string.create_width), R.drawable.blank_cut_width_big));
        list.add(new BlankCutBean(BlankCutType.DRILLING, getString(R.string.key_head_drilling), R.drawable.blank_cut_drilling));
        list.add(new BlankCutBean(BlankCutType.LEFT_GROOVE, getString(R.string.left_groove), R.drawable.blank_cut_left_groove));
        list.add(new BlankCutBean(BlankCutType.RIGHT_GROOVE, getString(R.string.right_groove), R.drawable.blank_cut_right_groove));
        list.add(new BlankCutBean(BlankCutType.KEY_TIP, getString(R.string.key_tip), R.drawable.blank_cut_key_tip));
        list.add(new BlankCutBean(BlankCutType.CREATE_GROOVE, getString(R.string.creat_groove), R.drawable.blank_cut_k40k80));
        list.add(new BlankCutBean(BlankCutType.SIDE_GROOVE, getString(R.string.side_groove), R.drawable.blank_cut_side_groove));
    }

    private void s1Clamp(List<BlankCutBean> list) {
        BlankCutBean blankCutBean = new BlankCutBean(BlankCutType.THICKNESS, getString(R.string.thickness), R.drawable.blank_cut_thickness_big);
        blankCutBean.setChecked(true);
        list.add(blankCutBean);
        list.add(new BlankCutBean(BlankCutType.WIDTH, getString(R.string.width), R.drawable.blank_cut_width_big));
        list.add(new BlankCutBean(BlankCutType.LEFT_GROOVE, getString(R.string.left_groove), R.drawable.blank_cut_left_groove));
        list.add(new BlankCutBean(BlankCutType.RIGHT_GROOVE, getString(R.string.right_groove), R.drawable.blank_cut_right_groove));
    }

    public static KeyBlankCutTypeSelectFragment newInstance() {
        Bundle bundle = new Bundle();
        KeyBlankCutTypeSelectFragment keyBlankCutTypeSelectFragment = new KeyBlankCutTypeSelectFragment();
        keyBlankCutTypeSelectFragment.setArguments(bundle);
        return keyBlankCutTypeSelectFragment;
    }

    @OnClick({R.id.bt_ok, R.id.bt_preset})
    public void onclick(View view) {
        int id = view.getId();
        if (id != R.id.bt_ok) {
            if (id != R.id.bt_preset) {
                return;
            }
            start(SpecialBlankCutTypeSelectFragment.newInstance());
            return;
        }
        BlankCutAdapter blankCutAdapter = this.blankCutAdapter;
        if (blankCutAdapter != null) {
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
                            start(BlankCutHeadFragment.newInstance(blankCutBean));
                            break;
                        case 4:
                            start(BlankCutDrillingFragment.newInstance(blankCutBean));
                            break;
                        case 5:
                            start(BlankCutLeftGrooveFragment.newInstance(blankCutBean));
                            break;
                        case 6:
                            start(BlankCutRightGrooveFragment.newInstance(blankCutBean));
                            break;
                        case 7:
                            start(BlankCutKeyTipFragment.newInstance(blankCutBean));
                            break;
                        case 8:
                            start(BlankCutCreateGrooveFragment.newInstance(blankCutBean));
                            break;
                        case 9:
                        case 10:
                            start(BlankCutCreateGrooveFragment.newInstance(blankCutBean));
                            break;
                        case 11:
                            start(BlankCutSideGrooveFragment.newInstance(blankCutBean));
                            break;
                    }
                }
            }
        }
    }

    /* renamed from: com.kkkcut.e20j.ui.fragment.blankcut.KeyBlankCutTypeSelectFragment$1, reason: invalid class name */
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
                $SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType[BlankCutType.DRILLING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType[BlankCutType.LEFT_GROOVE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType[BlankCutType.RIGHT_GROOVE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType[BlankCutType.KEY_TIP.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType[BlankCutType.CREATE_GROOVE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType[BlankCutType.HY18.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType[BlankCutType.HY18R.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType[BlankCutType.SIDE_GROOVE.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
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
