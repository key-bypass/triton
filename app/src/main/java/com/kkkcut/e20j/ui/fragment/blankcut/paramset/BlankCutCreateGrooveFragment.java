package com.kkkcut.e20j.ui.fragment.blankcut.paramset;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.widget.EditText;
import butterknife.BindView;
import com.example.spl_key_sdklibrary.mdKeyBlankClass;
import com.kkkcut.e20j.ui.fragment.blankcut.BlankCutBean;
import com.kkkcut.e20j.ui.fragment.blankcut.BlankCutType;
import com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment;
import com.kkkcut.e20j.us.R;
import com.liying.core.clamp.ClampF;

/* loaded from: classes.dex */
public class BlankCutCreateGrooveFragment extends BaseBlankCutParamSetFragment {

    @BindView(R.id.et_groove_length)
    EditText etGrooveLength;

    @BindView(R.id.et_left_width)
    EditText etLeftWidth;

    @BindView(R.id.et_remaining_thickness)
    EditText etRemainingThickness;

    @BindView(R.id.et_right_width)
    EditText etRightWidth;
    private int grooveLength;
    private int remainingLeftWidth;
    private int remainingRightWidth;
    private int remainingThickness;

    @Override // com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment
    public boolean checkHaveRiskCutClamp(ClampF clampF) {
        return false;
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_blank_cut_40k_80k;
    }

    @Override // com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment
    public void onCutFinish() {
    }

    @Override // com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment
    public String setKeyLocationPath() {
        return "keyblank/decoder/S8-2.json";
    }

    public static BlankCutCreateGrooveFragment newInstance(BlankCutBean blankCutBean) {
        Bundle bundle = new Bundle();
        BlankCutCreateGrooveFragment blankCutCreateGrooveFragment = new BlankCutCreateGrooveFragment();
        bundle.putParcelable(BaseBlankCutParamSetFragment.BLANK_CUT, blankCutBean);
        blankCutCreateGrooveFragment.setArguments(bundle);
        return blankCutCreateGrooveFragment;
    }

    public static BlankCutCreateGrooveFragment newInstance(BlankCutBean blankCutBean, Param param) {
        Bundle bundle = new Bundle();
        BlankCutCreateGrooveFragment blankCutCreateGrooveFragment = new BlankCutCreateGrooveFragment();
        bundle.putParcelable(BaseBlankCutParamSetFragment.BLANK_CUT, blankCutBean);
        bundle.putParcelable(BaseBlankCutParamSetFragment.PARAM, param);
        blankCutCreateGrooveFragment.setArguments(bundle);
        return blankCutCreateGrooveFragment;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    public void initViewsAndEvents() {
        super.initViewsAndEvents();
        if (getBlankCutType() == BlankCutType.HY18) {
            this.etGrooveLength.setText("3630");
            this.etRemainingThickness.setText("180");
            this.etLeftWidth.setText("420");
            this.etRightWidth.setText("135");
            return;
        }
        if (getBlankCutType() == BlankCutType.HY18R) {
            this.etGrooveLength.setText("3630");
            this.etRemainingThickness.setText("180");
            this.etLeftWidth.setText("135");
            this.etRightWidth.setText("420");
            return;
        }
        if (getBlankCutType() == BlankCutType.K4080K) {
            this.etGrooveLength.setText("2350");
            this.etRemainingThickness.setText("190");
            this.etLeftWidth.setText("110");
            this.etRightWidth.setText("110");
        }
    }

    @Override // com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment
    public void setMdKeyBlankClass(mdKeyBlankClass mdkeyblankclass) {
        mdkeyblankclass.setRepairKeyBlakType(1);
        mdkeyblankclass.setCutFaceSettingType(0);
        mdkeyblankclass.setKeyBlankThick(this.remainingThickness);
        mdkeyblankclass.setKeyBlankLeftWidth(this.remainingLeftWidth);
        mdkeyblankclass.setKeyBlankRightWidth(this.remainingRightWidth);
        mdkeyblankclass.setKeyBlankGrooveLength(this.grooveLength);
    }

    @Override // com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment
    public boolean readyStartCut() {
        String trim = this.etGrooveLength.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            return false;
        }
        String trim2 = this.etRemainingThickness.getText().toString().trim();
        if (TextUtils.isEmpty(trim2)) {
            return false;
        }
        String trim3 = this.etLeftWidth.getText().toString().trim();
        if (TextUtils.isEmpty(trim3)) {
            return false;
        }
        String trim4 = this.etRightWidth.getText().toString().trim();
        if (TextUtils.isEmpty(trim4)) {
            return false;
        }
        try {
            this.grooveLength = Integer.parseInt(trim);
            this.remainingThickness = Integer.parseInt(trim2);
            this.remainingLeftWidth = Integer.parseInt(trim3);
            this.remainingRightWidth = Integer.parseInt(trim4);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class Param implements Parcelable {
        public final Parcelable.Creator<Param> CREATOR = new Parcelable.Creator<Param>() { // from class: com.kkkcut.e20j.ui.fragment.blankcut.paramset.BlankCutCreateGrooveFragment.Param.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Param createFromParcel(Parcel parcel) {
                return new Param(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Param[] newArray(int i) {
                return new Param[i];
            }
        };
        private int grooveLength;
        private int remainingLeftWidth;
        private int remainingRightWidth;
        private int remainingThickness;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public Param(int i, int i2, int i3, int i4) {
            this.grooveLength = i;
            this.remainingThickness = i2;
            this.remainingLeftWidth = i3;
            this.remainingRightWidth = i4;
        }

        public int getGrooveLength() {
            return this.grooveLength;
        }

        public int getRemainingThickness() {
            return this.remainingThickness;
        }

        public int getRemainingLeftWidth() {
            return this.remainingLeftWidth;
        }

        public int getRemainingRightWidth() {
            return this.remainingRightWidth;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.grooveLength);
            parcel.writeInt(this.remainingThickness);
            parcel.writeInt(this.remainingLeftWidth);
            parcel.writeInt(this.remainingRightWidth);
        }

        public void readFromParcel(Parcel parcel) {
            this.grooveLength = parcel.readInt();
            this.remainingThickness = parcel.readInt();
            this.remainingLeftWidth = parcel.readInt();
            this.remainingRightWidth = parcel.readInt();
        }

        protected Param(Parcel parcel) {
            this.grooveLength = parcel.readInt();
            this.remainingThickness = parcel.readInt();
            this.remainingLeftWidth = parcel.readInt();
            this.remainingRightWidth = parcel.readInt();
        }
    }
}
