package com.kkkcut.e20j.ui.fragment.setting;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.OnClick;
import com.kkkcut.e20j.androidquick.tool.SPUtils;
import com.kkkcut.e20j.androidquick.tool.ToastUtil;
import com.kkkcut.e20j.ui.fragment.BaseBackFragment;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class SpaceWidthSetting extends BaseBackFragment {
    public static final String TAG = "SpaceWidthSetting";
    private String SPACE_WIDTH = "SPACE_WIDTH";

    @BindView(R.id.et_double_inside_key)
    EditText etDoubleInsideKey;

    @BindView(R.id.et_double_key)
    EditText etDoubleKey;

    @BindView(R.id.et_double_outside_key)
    EditText etDoubleOutsideKey;

    @BindView(R.id.et_single_inside_key)
    EditText etSingleInsideKey;

    @BindView(R.id.et_single_key)
    EditText etSingleKey;

    @BindView(R.id.et_single_outside_key)
    EditText etSingleOutsideKey;

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_space_width_setting;
    }

    @Override // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    public String setTitleStr() {
        return null;
    }

    public static SpaceWidthSetting newInstance() {
        Bundle bundle = new Bundle();
        SpaceWidthSetting spaceWidthSetting = new SpaceWidthSetting();
        spaceWidthSetting.setArguments(bundle);
        return spaceWidthSetting;
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        this.etSingleKey.setText(String.valueOf(SPUtils.getInt(this.SPACE_WIDTH + 1, 0)));
        this.etDoubleKey.setText(String.valueOf(SPUtils.getInt(this.SPACE_WIDTH + 0, 30)));
        this.etSingleInsideKey.setText(String.valueOf(SPUtils.getInt(this.SPACE_WIDTH + 5, 0)));
        this.etSingleOutsideKey.setText(String.valueOf(SPUtils.getInt(this.SPACE_WIDTH + 3, 0)));
        this.etDoubleInsideKey.setText(String.valueOf(SPUtils.getInt(this.SPACE_WIDTH + 2, 0)));
        this.etDoubleOutsideKey.setText(String.valueOf(SPUtils.getInt(this.SPACE_WIDTH + 4, 0)));
    }

    @OnClick({R.id.bt_save})
    public void onViewClicked() {
        save(this.etSingleKey, 1);
        save(this.etDoubleKey, 0);
        save(this.etSingleInsideKey, 5);
        save(this.etSingleOutsideKey, 3);
        save(this.etDoubleInsideKey, 2);
        save(this.etDoubleOutsideKey, 4);
        ToastUtil.showToast("保存成功");
    }

    private void save(EditText editText, int i) {
        String obj = editText.getText().toString();
        if (TextUtils.isEmpty(obj)) {
            return;
        }
        SPUtils.put(this.SPACE_WIDTH + i, Integer.parseInt(obj));
    }
}
