package com.kkkcut.e20j.ui.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter;
import com.kkkcut.e20j.base.BaseFActivity;
import com.kkkcut.e20j.us.R;
import com.kkkcut.e20j.utils.AssetVersionUtil;
import com.liying.core.Command;
import com.liying.core.OperateType;
import com.liying.core.communication.OperationManager;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public class FirmwareUpdateActivity extends BaseFActivity {
    private static final String CURRENT_VERSION = "currentVersion";
    private static final String NEW_VERSION = "newVersion";
    List<byte[]> data = new ArrayList();
    private int index;

    @BindView(R.id.progress)
    ProgressBar progressBar;

    @BindView(R.id.tv_current_version)
    TextView tvCurrentVersion;

    @BindView(R.id.tv_new_version)
    TextView tvNewVersion;

    @BindView(R.id.tv_progress)
    TextView tvProgress;

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickActivity
    protected int getContentViewLayoutID() {
        return R.layout.activity_firmware_update;
    }

    @Override // com.kkkcut.e20j.base.BaseFActivity, me.yokeyword.fragmentation.ISupportActivity
    public void onBackPressedSupport() {
    }

    public static void start(Context context, String str, String str2) {
        Intent intent = new Intent(context, (Class<?>) FirmwareUpdateActivity.class);
        intent.putExtra(CURRENT_VERSION, str);
        intent.putExtra(NEW_VERSION, str2);
        context.startActivity(intent);
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [com.kkkcut.e20j.ui.activity.FirmwareUpdateActivity$1] */
    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickActivity
    protected void initViewsAndEvents() {
        this.tvCurrentVersion.setText(getIntent().getStringExtra(CURRENT_VERSION));
        this.tvNewVersion.setText(getIntent().getStringExtra(NEW_VERSION));
        new Thread() { // from class: com.kkkcut.e20j.ui.activity.FirmwareUpdateActivity.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    String firmwareDir = AssetVersionUtil.getFirmwareDir();
                    String str = FirmwareUpdateActivity.this.getAssets().list(firmwareDir)[0];
                    InputStream open = FirmwareUpdateActivity.this.getAssets().open(firmwareDir + "/" + str);
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = open.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        FirmwareUpdateActivity.this.data.add(Arrays.copyOf(bArr, read));
                    }
                    if (FirmwareUpdateActivity.this.data.get(FirmwareUpdateActivity.this.data.size() - 1).length == 1024) {
                        FirmwareUpdateActivity.this.data.add(new byte[0]);
                    }
                    OperationManager.getInstance().sendOrder(Command.readyUpgradeFirmware(), OperateType.FIRMWARE_UPDATE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void setProgress(float f) {
        if (f > 100.0f) {
            f = 100.0f;
        }
        ProgressBar progressBar = this.progressBar;
        if (progressBar == null || this.tvProgress == null) {
            return;
        }
        progressBar.setProgress((int) f);
        this.tvProgress.setText(String.format("%.1f%%", Float.valueOf(f)));
    }

    @Override // com.kkkcut.e20j.base.BaseActivity, com.kkkcut.e20j.androidquick.ui.base.QuickActivity
    protected void onEventComing(EventCenter eventCenter) {
        int eventCode = eventCenter.getEventCode();
        if (eventCode == 16) {
            setProgress((this.index * 100.0f) / this.data.size());
            if (this.index > this.data.size()) {
                showAlert(R.string.firmware_upgrade_successful);
                return;
            }
            if (this.index == 0) {
                OperationManager.getInstance().sendOrder("U".getBytes(), OperateType.FIRMWARE_UPDATE);
            } else {
                OperationManager.getInstance().sendOrder(Command.upgradeFirmware(this.data, this.index - 1), OperateType.FIRMWARE_UPDATE);
            }
            this.index++;
            return;
        }
        if (eventCode == 33 && this.index <= this.data.size()) {
            showAlert(R.string.firmware_upgrade_failed);
        }
    }

    private void showAlert(int i) {
        new AlertDialog.Builder(this).setCancelable(false).setMessage(i).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.kkkcut.e20j.ui.activity.FirmwareUpdateActivity.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                OperationManager.getInstance().sendOrder(Command.QueryFirmwareVersion(), OperateType.READ_FIRMWARE);
                FirmwareUpdateActivity.this.finish();
            }
        }).show();
    }
}
