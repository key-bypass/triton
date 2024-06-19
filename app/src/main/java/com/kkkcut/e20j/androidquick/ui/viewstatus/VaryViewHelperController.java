package com.kkkcut.e20j.androidquick.ui.viewstatus;

import android.view.View;
import android.widget.TextView;
import com.kkkcut.e20j.androidquick.tool.StringUtil;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class VaryViewHelperController {
    private IVaryViewHelper helper;

    public VaryViewHelperController(View view) {
        this(new VaryViewHelper(view));
    }

    public VaryViewHelperController(IVaryViewHelper iVaryViewHelper) {
        this.helper = iVaryViewHelper;
    }

    public void showNetworkError(View.OnClickListener onClickListener) {
        View inflate = this.helper.inflate(R.layout.view_status);
        ((TextView) inflate.findViewById(R.id.message_info)).setText(this.helper.getContext().getResources().getString(R.string.error_occurred));
        if (onClickListener != null) {
            inflate.setOnClickListener(onClickListener);
        }
        this.helper.showLayout(inflate);
    }

    public void showError(String str, View.OnClickListener onClickListener) {
        View inflate = this.helper.inflate(R.layout.view_status);
        TextView textView = (TextView) inflate.findViewById(R.id.message_info);
        if (!StringUtil.isEmpty(str)) {
            textView.setText(str);
        } else {
            textView.setText(this.helper.getContext().getResources().getString(R.string.error_occurred));
        }
        if (onClickListener != null) {
            inflate.setOnClickListener(onClickListener);
        }
        this.helper.showLayout(inflate);
    }

    public void showEmpty(String str, View.OnClickListener onClickListener) {
        View inflate = this.helper.inflate(R.layout.view_status);
        TextView textView = (TextView) inflate.findViewById(R.id.message_info);
        if (!StringUtil.isEmpty(str)) {
            textView.setText(str);
        } else {
            textView.setText(this.helper.getContext().getResources().getString(R.string.not_null));
        }
        if (onClickListener != null) {
            inflate.setOnClickListener(onClickListener);
        }
        this.helper.showLayout(inflate);
    }

    public void showLoading(String str) {
        View inflate = this.helper.inflate(R.layout.view_status);
        if (!StringUtil.isEmpty(str)) {
            ((TextView) inflate.findViewById(R.id.message_info)).setText(str);
        }
        this.helper.showLayout(inflate);
    }

    public void restore() {
        this.helper.restoreView();
    }
}
