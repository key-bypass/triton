package com.kkkcut.e20j.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.kkkcut.e20j.DbBean.KeyResource;
import com.kkkcut.e20j.SpKeys;
import com.kkkcut.e20j.androidquick.autolayout.utils.AutoUtils;
import com.kkkcut.e20j.androidquick.tool.SPUtils;
import com.kkkcut.e20j.dao.ResourceDaoManager;
import com.kkkcut.e20j.ui.fragment.clampswitch.ClampCreator;
import com.kkkcut.e20j.us.R;
import com.kkkcut.e20j.utils.ResUpdateUtils;
import com.kkkcut.e20j.utils.ThemeUtils;
import com.kkkcut.e20j.utils.UnitUtils;
import com.cutting.machine.MachineInfo;
import com.cutting.machine.bean.KeyInfo;
import com.cutting.machine.error.ErrorCode;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.HashMap;

/* loaded from: classes.dex */
public class KeyInfoFragment extends BaseBackFragment {
    public static final String KEY_INFO = "keyinfo";

    ImageView ivClamp;

    ImageView ivPlayVideo;

    ImageView ivQrcode;

    ImageView ivRealKey;
    private KeyResource keyResource;

    LinearLayout llDepth;

    LinearLayout llIndex;

    LinearLayout llKeyBlanks;

    LinearLayout llSpace;

    TextView tvAlign;

    TextView tvDetail;

    TextView tvKeyNumbering;

    TextView tvKeySeries;

    TextView tvKeyThickness;

    TextView tvKeyWidth;

    TextView tvQrcode;

    TextView tvTitleDepth;

    TextView tvTitleSpace;

    TextView tvTitleThickness;

    WebView webviewDescription;

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_keyinfo;
    }

    public static KeyInfoFragment newInstance(KeyInfo keyInfo) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_INFO, keyInfo);
        KeyInfoFragment keyInfoFragment = new KeyInfoFragment();
        keyInfoFragment.setArguments(bundle);
        return keyInfoFragment;
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        String title = getKeyinfo().getTitle();
        this.tvDetail.setText(title + "{" + getKeyinfo().getCuts() + "}");
        String keyChinaNum = getKeyinfo().getKeyChinaNum();
        if (TextUtils.isEmpty(keyChinaNum)) {
            this.tvKeyNumbering.setVisibility(8);
        } else {
            this.tvKeyNumbering.setText(getString(R.string.china_key_number) + ":" + keyChinaNum);
        }
        if (getKeyinfo().getAlign() == 0) {
            this.tvAlign.setText(R.string.shoulder);
        } else {
            this.tvAlign.setText(R.string.tip);
        }
        int width = getKeyinfo().getWidth();
        if (width < 0) {
            width = 0;
        }
        if (SPUtils.getBoolean(SpKeys.UNIT_INCH)) {
            width = UnitUtils.mm2Inch(width);
        }
        this.tvKeyWidth.setText(String.valueOf(width));
        int thick = getKeyinfo().getThick();
        if (SPUtils.getBoolean(SpKeys.UNIT_INCH)) {
            thick = UnitUtils.mm2Inch(thick);
        }
        if (thick == 0) {
            this.tvTitleThickness.setVisibility(8);
            this.tvKeyThickness.setVisibility(8);
        } else {
            this.tvTitleThickness.setVisibility(0);
            this.tvKeyThickness.setVisibility(0);
            this.tvKeyThickness.setText(String.valueOf(thick));
        }
        if (!getKeyinfo().isCustomKey()) {
            ResUpdateUtils.showKeyImage(getContext(), getKeyinfo().getIcCard(), this.ivRealKey);
        }
        int seriesID = getKeyinfo().getSeriesID();
        if (seriesID != 0) {
            KeyResource keyResource = ResourceDaoManager.getInstance().getKeyResource(seriesID);
            this.keyResource = keyResource;
            if (keyResource != null && ((keyResource.getFK_LanguageID() == 2 && MachineInfo.isChineseMachine()) || (this.keyResource.getFK_LanguageID() == 1 && !MachineInfo.isChineseMachine()))) {
                String videoPath = this.keyResource.getVideoPath();
                if (!TextUtils.isEmpty(videoPath)) {
                    this.ivPlayVideo.setVisibility(View.VISIBLE);
                    this.tvQrcode.setVisibility(View.VISIBLE);
                    this.ivQrcode.setVisibility(View.VISIBLE);
                    this.ivQrcode.setImageBitmap(CodeUtils.createImage(videoPath, ErrorCode.keyDecodeFailed, ErrorCode.keyDecodeFailed, null));
                }
                String explainHtml = this.keyResource.getExplainHtml();
                if (!TextUtils.isEmpty(explainHtml)) {
                    WebSettings settings = this.webviewDescription.getSettings();
                    settings.setJavaScriptEnabled(true);
                    settings.setDefaultTextEncodingName("UTF-8");
                    this.webviewDescription.loadData(explainHtml, "text/html; charset=UTF-8", null);
                }
            }
        }
        this.ivClamp.setImageResource(ClampCreator.getDrawableRes(getKeyinfo()));
        String codeSeries = getKeyinfo().getCodeSeries();
        if (!TextUtils.isEmpty(codeSeries)) {
            this.tvKeySeries.setText(codeSeries);
        }
        HashMap<String, String> keyBlankMap = getKeyinfo().getKeyBlankMap();
        if (keyBlankMap != null) {
            for (String str : keyBlankMap.keySet()) {
                TextView textView = new TextView(getContext());
                textView.setTextSize(AutoUtils.getPercentHeightSize(24));
                textView.setTextColor(ThemeUtils.getColor(getContext(), R.attr.color_red_blueDark));
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                layoutParams.setMargins(0, 10, 0, 0);
                textView.setText(str);
                this.llKeyBlanks.addView(textView, layoutParams);
                TextView textView2 = new TextView(getContext());
                String str2 = keyBlankMap.get(str);
                textView2.setText(str2.substring(0, str2.length() - 1));
                textView2.setTextColor(ThemeUtils.getColor(getContext(), R.attr.textColor_ffffff_333333));
                textView2.setTextSize(AutoUtils.getPercentHeightSize(20));
                new LinearLayout.LayoutParams(-2, -2).setMargins(0, 10, 0, 0);
                this.llKeyBlanks.addView(textView2);
            }
        }
        if (getKeyinfo().getLocked() == 0) {
            initSpaceData();
            initDepth();
        }
    }

    private void initDepth() {
        this.tvTitleDepth.setVisibility(0);
        String[] split = getKeyinfo().getDepthStr().split(";");
        String[] split2 = getKeyinfo().getDepthName().split(";");
        int i = 0;
        for (String str : split) {
            i = Math.max(i, str.split(",").length);
        }
        for (int i2 = 0; i2 < split.length * 2; i2++) {
            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setOrientation(0);
            for (int i3 = 0; i3 < i; i3++) {
                TextView textView = null;
                int i4 = i2 / 2;
                String[] split3 = split[i4].split(",");
                String[] split4 = split2[i4].split(",");
                if (i3 < split3.length) {
                    if (i2 % 2 == 0) {
                        textView = getText(split4[i3], true);
                    } else {
                        textView = getText(split3[i3]);
                    }
                }
                if (textView == null) {
                    break;
                }
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(45, 20);
                layoutParams.setMargins(1, 1, 0, 0);
                linearLayout.addView(textView, layoutParams);
            }
            this.llDepth.addView(linearLayout);
        }
    }

    private void initSpaceData() {
        this.tvTitleSpace.setVisibility(0);
        String spaceStr = getKeyinfo().getSpaceStr();
        String spacenew = getKeyinfo().getSpacenew();
        if (!TextUtils.isEmpty(spacenew)) {
            spaceStr = spacenew;
        }
        String[] split = spaceStr.split(";");
        int i = 0;
        for (String str : split) {
            i = Math.max(i, str.split(",").length);
        }
        int i2 = 0;
        while (i2 < split.length + 1) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(30, 20);
            layoutParams.setMargins(0, 1, 0, 0);
            this.llIndex.addView(getText(i2 == 0 ? "" : String.valueOf(i2), true), layoutParams);
            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setOrientation(0);
            for (int i3 = 0; i3 < i; i3++) {
                TextView textView = null;
                if (i2 == 0) {
                    textView = getText(String.valueOf(i3 + 1), true);
                } else {
                    String[] split2 = split[i2 - 1].split(",");
                    if (i3 < split2.length) {
                        textView = getText(split2[i3]);
                    }
                }
                if (textView == null) {
                    break;
                }
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(45, 20);
                layoutParams2.setMargins(1, 1, 0, 0);
                linearLayout.addView(textView, layoutParams2);
            }
            this.llSpace.addView(linearLayout);
            i2++;
        }
    }

    private TextView getText(String str, boolean z) {
        TextView textView = new TextView(getContext());
        textView.setGravity(17);
        textView.setBackgroundResource(ThemeUtils.getResId(getContext(), R.attr.bg_customkey_input));
        if (z) {
            textView.setTextColor(getContext().getResources().getColor(R.color.color_ff205f));
        } else {
            if (SPUtils.getBoolean(SpKeys.UNIT_INCH)) {
                str = String.valueOf(UnitUtils.mm2Inch(Integer.parseInt(str)));
            }
            textView.setTextColor(-1);
        }
        textView.setText(str);
        textView.setTextSize(0, 15.0f);
        return textView;
    }

    private TextView getText(String str) {
        return getText(str, false);
    }

    private KeyInfo getKeyinfo() {
        return (KeyInfo) getArguments().getParcelable(KEY_INFO);
    }

    public void onViewClicked() {
        KeyResource keyResource = this.keyResource;
        if (keyResource != null) {
            String videoPath = keyResource.getVideoPath();
            if (TextUtils.isEmpty(videoPath)) {
                return;
            }
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse(videoPath)));
        }
    }

    @Override // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    public String setTitleStr() {
        return getString(R.string.key_information);
    }
}
