package com.kkkcut.e20j.ui.activity;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class FirmwareUpdateActivity_ViewBinding implements Unbinder {
    private FirmwareUpdateActivity target;

    public FirmwareUpdateActivity_ViewBinding(FirmwareUpdateActivity firmwareUpdateActivity) {
        this(firmwareUpdateActivity, firmwareUpdateActivity.getWindow().getDecorView());
    }

    public FirmwareUpdateActivity_ViewBinding(FirmwareUpdateActivity firmwareUpdateActivity, View view) {
        this.target = firmwareUpdateActivity;
        firmwareUpdateActivity.tvCurrentVersion = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_current_version, "field 'tvCurrentVersion'", TextView.class);
        firmwareUpdateActivity.tvNewVersion = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_new_version, "field 'tvNewVersion'", TextView.class);
        firmwareUpdateActivity.progressBar = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.progress, "field 'progressBar'", ProgressBar.class);
        firmwareUpdateActivity.tvProgress = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_progress, "field 'tvProgress'", TextView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        FirmwareUpdateActivity firmwareUpdateActivity = this.target;
        if (firmwareUpdateActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        firmwareUpdateActivity.tvCurrentVersion = null;
        firmwareUpdateActivity.tvNewVersion = null;
        firmwareUpdateActivity.progressBar = null;
        firmwareUpdateActivity.tvProgress = null;
    }
}
