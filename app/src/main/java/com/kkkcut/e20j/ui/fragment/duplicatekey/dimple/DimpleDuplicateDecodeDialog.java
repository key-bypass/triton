package com.kkkcut.e20j.ui.fragment.duplicatekey.dimple;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.cutting.machine.bean.KeyInfo;
import com.kkkcut.e20j.androidquick.tool.SPUtils;
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter;
import com.kkkcut.e20j.ui.dialog.base.BottomInDialog;
import com.kkkcut.e20j.ui.fragment.clampswitch.ClampCreator;

import org.greenrobot.eventbus.EventBus;

/* loaded from: classes.dex */
public class DimpleDuplicateDecodeDialog extends BottomInDialog {
    public static final String PARAM = "param";
    private static final String TAG = "DecodeDialog";

    TextView btCancle;

    TextView btDecode;
    boolean isRound;

    ImageView ivClamp;

    ImageView ivDecoder;
    private KeyInfo keyInfo;
    boolean roundBtVisible;

    CheckBox sbRound;
    private int timeValue;

    TextView tvDecoderSize;

    TextView tvRound;

    TextView tvTimeValue;

    public DimpleDuplicateDecodeDialog(Activity activity) {
        super(activity);
    }

    @Override
    public int getContentLayoutID() {
        return 0;
    }

    @Override
    public void initView() {

    }
}
