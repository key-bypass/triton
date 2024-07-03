package com.kkkcut.e20j.ui.fragment.setting;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.core.content.res.ResourcesCompat;

import com.kkkcut.e20j.DbBean.userDB.CollectionDataDao;
import com.kkkcut.e20j.DbBean.userDB.CustomKeyDao;
import com.kkkcut.e20j.DbBean.userDB.CutHistoryDataDao;
import com.kkkcut.e20j.MyApplication;
import com.kkkcut.e20j.SpKeys;
import com.kkkcut.e20j.androidquick.network.RetrofitManager;
import com.kkkcut.e20j.androidquick.tool.AppUtil;
import com.kkkcut.e20j.androidquick.tool.FileUtil;
import com.kkkcut.e20j.androidquick.tool.LogUtil;
import com.kkkcut.e20j.androidquick.tool.SPUtils;
import com.kkkcut.e20j.androidquick.tool.ToastUtil;
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter;
import com.kkkcut.e20j.dao.UserDataDaoManager;
import com.kkkcut.e20j.ui.dialog.EditDialog;
import com.kkkcut.e20j.ui.dialog.RemindDialog;
import com.kkkcut.e20j.ui.dialog.WarningDialog;
import com.kkkcut.e20j.ui.fragment.BaseBackFragment;
import com.cutting.machine.Command;
import com.cutting.machine.CoreConstant;
import com.cutting.machine.CuttingMachine;
import com.cutting.machine.MachineInfo;
import com.cutting.machine.OperateType;
import com.cutting.machine.clamp.MachineData;
import com.cutting.machine.communication.OperationManager;
import com.cutting.machine.error.ErrorBean;
import com.kkkcut.e20j.us.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import me.jahnen.libaums.core.UsbMassStorageDevice;
import me.jahnen.libaums.core.fs.UsbFile;
import me.jahnen.libaums.core.fs.UsbFileInputStream;
import me.jahnen.libaums.core.fs.UsbFileOutputStream;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.greenrobot.eventbus.EventBus;

/* loaded from: classes.dex */
public class OtherSettingFragment extends BaseBackFragment {
    private static final String ACTION_USB_PERMISSION = "com.kkkcut.e20.USB_PERMISSION";
    private static final int EXPORT = 1;
    private static final int IMPORT = 0;
    public static final String TAG = "OtherSettingFragment";
    private int actionType;

    Button btContinueMove;

    TextView btDispaleySetting;

    Button btOk;

    LinearLayout btStartMove;

    CheckBox cbBarCode;

    CheckBox cbChineseCar;

    CheckBox cbDecoderPositionDetect;

    CheckBox cbSafetyDoor;

    EditText etMoveTime;

    EditText etRatio;

    EditText etX;

    EditText etY;

    EditText etZ;
    private boolean hasMove;

    LinearLayout llDatabaseExport;

    LinearLayout llMove;

    LinearLayout llRatioSetup;

    RadioButton rb150;

    RadioButton rbInch;
    String tableNameStr;
    private UsbMassStorageDevice usbMassStorageDevice;
    private String xStr;
    private String yStr;
    private String zStr;
    private int currentIndex = 1;
    private int totalTimes = 1;
    private final BroadcastReceiver usbReceiver = new BroadcastReceiver() { // from class: com.kkkcut.e20j.ui.fragment.setting.OtherSettingFragment.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (OtherSettingFragment.ACTION_USB_PERMISSION.equals(action)) {
                UsbDevice usbDevice = (UsbDevice) intent.getParcelableExtra("device");
                if (!intent.getBooleanExtra("permission", false) || usbDevice == null) {
                    return;
                }
                Log.i(OtherSettingFragment.TAG, "ACTION_USB_PERMISSION: ");
                try {
                    OtherSettingFragment.this.usbMassStorageDevice.init();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (OtherSettingFragment.this.usbMassStorageDevice == null || OtherSettingFragment.this.usbMassStorageDevice.getPartitions() == null || OtherSettingFragment.this.usbMassStorageDevice.getPartitions().get(0) == null || OtherSettingFragment.this.usbMassStorageDevice.getPartitions().get(0).getFileSystem() == null) {
                    ToastUtil.showToast("Does not support this USB flash drive");
                }
                FileSystem fileSystem = (FileSystem) OtherSettingFragment.this.usbMassStorageDevice.getPartitions().get(0).getFileSystem();
                try {
                    if (OtherSettingFragment.this.actionType == 1) {
                        OtherSettingFragment.this.export2usb(fileSystem);
                    } else {
                        OtherSettingFragment.this.import2Sql(fileSystem);
                    }
                    return;
                } catch (IOException e2) {
                    e2.printStackTrace();
                    return;
                }
            }
            if ("android.hardware.usb.action.USB_DEVICE_ATTACHED".equals(action)) {
                Log.d(OtherSettingFragment.TAG, "USB device attached");
            } else if ("android.hardware.usb.action.USB_DEVICE_DETACHED".equals(action)) {
                Log.d(OtherSettingFragment.TAG, "USB device detached");
            }
        }
    };

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_other_setting;
    }

    public static OtherSettingFragment newInstance() {
        Bundle bundle = new Bundle();
        OtherSettingFragment otherSettingFragment = new OtherSettingFragment();
        otherSettingFragment.setArguments(bundle);
        return otherSettingFragment;
    }

    @Override // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    public String setTitleStr() {
        return getString(R.string.others);
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        this.cbSafetyDoor.setChecked(SPUtils.getBoolean(SpKeys.COVER, true));
        if (MyApplication.getInstance().isShowRealDepth()) {
            this.llMove.setVisibility(0);
            this.btStartMove.setVisibility(0);
        }
        if (MachineInfo.isChineseMachine() || MachineInfo.isE20Us(getContext())) {
            this.cbChineseCar.setVisibility(8);
        } else {
            this.cbChineseCar.setVisibility(0);
            this.cbChineseCar.setChecked(SPUtils.getBoolean(SpKeys.SHOW_CHINESE_CAR, MachineInfo.isE20Standard(getContext())));
        }
        if (MachineInfo.isE20Us(getContext())) {
            this.btDispaleySetting.setVisibility(0);
            this.cbBarCode.setVisibility(0);
            this.cbBarCode.setChecked(SPUtils.getBoolean("bar_code", true));
        } else {
            this.btDispaleySetting.setVisibility(8);
            this.cbBarCode.setVisibility(8);
        }
        if (MachineInfo.isE9Standard(getContext())) {
            this.cbDecoderPositionDetect.setVisibility(0);
            this.cbDecoderPositionDetect.setChecked(SPUtils.getBoolean(SpKeys.Not_detect_decoder_position));
        } else {
            this.cbDecoderPositionDetect.setVisibility(8);
        }
        Typeface font = ResourcesCompat.getFont(getContext(), R.font.pingfang_sc_regular);
        this.cbChineseCar.setTypeface(font);
        this.cbSafetyDoor.setTypeface(font);
        int i = SPUtils.getInt(SpKeys.TOTAL_MOVE_TIMES);
        int i2 = SPUtils.getInt(SpKeys.CURRENT_MOVE_TIMES);
        if (i == -1 || i2 == -1 || i2 == i) {
            this.btContinueMove.setVisibility(8);
        } else {
            this.btContinueMove.setVisibility(0);
        }
        IntentFilter intentFilter = new IntentFilter(ACTION_USB_PERMISSION);
        intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_ATTACHED");
        intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
        getContext().registerReceiver(this.usbReceiver, intentFilter);
        if (SPUtils.getInt(SpKeys.DOUBLE_KEY_CUTTER, 200) == 150) {
            this.rb150.setChecked(true);
        }
        if (AppUtil.isApkInDebug(getContext())) {
            this.llRatioSetup.setVisibility(0);
            this.etRatio.setText(String.valueOf(MachineData.getXRatio()));
        }
        if (MachineInfo.isE9()) {
            this.etX.setText("10600");
            this.etY.setText("2080");
            this.etZ.setText("2200");
        } else {
            this.etX.setText("6300");
            this.etY.setText("5200");
            this.etZ.setText("2400");
        }
        if (SPUtils.getBoolean(SpKeys.UNIT_INCH, false)) {
            this.rbInch.setChecked(true);
        }
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        switch (compoundButton.getId()) {
            case R.id.cb_bar_code /* 2131362000 */:
                SPUtils.put("bar_code", z);
                EventBus.getDefault().post(new EventCenter(54, Boolean.valueOf(z)));
                return;
            case R.id.cb_chinese_car /* 2131362002 */:
                SPUtils.put(SpKeys.SHOW_CHINESE_CAR, this.cbChineseCar.isChecked());
                EventBus.getDefault().post(new EventCenter(45));
                return;
            case R.id.cb_decoder_position_detect /* 2131362005 */:
                CuttingMachine.getInstance().setNotDetectDecoder(z);
                SPUtils.put(SpKeys.Not_detect_decoder_position, z);
                return;
            case R.id.cb_move /* 2131362027 */:
                this.llMove.setVisibility(z ? 0 : 8);
                return;
            case R.id.cb_safety_door /* 2131362031 */:
                if (z) {
                    OperationManager.getInstance().sendOrder(Command.OpenDoorCuttingSettings(1), OperateType.SAVETY_DOOR);
                    SPUtils.put(SpKeys.COVER, true);
                    return;
                } else {
                    OperationManager.getInstance().sendOrder(Command.OpenDoorCuttingSettings(0), OperateType.SAVETY_DOOR);
                    SPUtils.put(SpKeys.COVER, false);
                    return;
                }
            case R.id.rb_150 /* 2131362596 */:
                if (z) {
                    SPUtils.put(SpKeys.DOUBLE_KEY_CUTTER, 150);
                    return;
                }
                return;
            case R.id.rb_200 /* 2131362597 */:
                if (z) {
                    SPUtils.put(SpKeys.DOUBLE_KEY_CUTTER, 200);
                    return;
                }
                return;
            case R.id.rb_inch /* 2131362623 */:
                if (z) {
                    SPUtils.put(SpKeys.UNIT_INCH, true);
                    return;
                }
                return;
            case R.id.rb_mm /* 2131362632 */:
                if (z) {
                    SPUtils.put(SpKeys.UNIT_INCH, false);
                    return;
                }
                return;
            default:
                return;
        }
    }

    @Override // com.kkkcut.e20j.base.BaseFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void onEventComing(EventCenter<?> eventCenter) {
        int eventCode = eventCenter.getEventCode();
        if (eventCode != 32) {
            if (eventCode != 33) {
                return;
            }
            dismissLoadingDialog();
            this.hasMove = false;
            this.currentIndex = 0;
            showErrorDialog(getContext(), (ErrorBean) eventCenter.getData());
            return;
        }
        if ((eventCenter.getData()) == OperateType.MOVE_XYZ) {
            if (this.hasMove) {
                this.hasMove = false;
                OperationManager.getInstance().sendOrder(Command.DecoderOperation(1, 0, 1, 1, 1, ""), OperateType.MOVE_XYZ);
                return;
            }
            SPUtils.put(SpKeys.CURRENT_MOVE_TIMES, this.currentIndex);
            int i = this.currentIndex;
            if (i != this.totalTimes) {
                int i2 = i + 1;
                this.currentIndex = i2;
                showLoadingDialog(String.valueOf(i2), true);
                this.hasMove = true;
                OperationManager.getInstance().sendOrder(Command.DecoderOperation(1, 0, (int) (Integer.parseInt(this.xStr) / MachineData.dXScale), (int) (Integer.parseInt(this.yStr) / MachineData.dYScale), (int) (Integer.parseInt(this.zStr) / MachineData.dZScale), ""), OperateType.MOVE_XYZ);
                return;
            }
            this.btContinueMove.setVisibility(8);
            dismissLoadingDialog();
        }
    }

    private void resetStatus() {
        this.hasMove = false;
        dismissLoadingDialog();
        OperationManager.getInstance().setOperateType(OperateType.NONE);
    }

    private void showEditDialog() {
        EditDialog editDialog = new EditDialog(getContext());
        editDialog.setContentNullable(true);
        editDialog.setDialogBtnCallback(new EditDialog.DialogInputFinishCallBack() { // from class: com.kkkcut.e20j.ui.fragment.setting.OtherSettingFragment.2
            @Override // com.kkkcut.e20j.ui.dialog.EditDialog.DialogInputFinishCallBack
            public void onDialogButClick(String str) {
                if ("88888888".equals(str)) {
                    MyApplication.getInstance().setShowRealDepth(true);
                    OtherSettingFragment.this.llMove.setVisibility(0);
                    OtherSettingFragment.this.btStartMove.setVisibility(0);
                    EventBus.getDefault().post(new EventCenter(21));
                    return;
                }
                ToastUtil.showToast(R.string.password_is_incorrect);
            }
        });
        if (editDialog.isShowing()) {
            return;
        }
        editDialog.show();
    }

    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_conductivity_test /* 2131361916 */:
                OperationManager.getInstance().sendOrder(Command.OnductiveTestOperation());
                RemindDialog remindDialog = new RemindDialog(getContext());
                remindDialog.show();
                remindDialog.setCancleBtnVisibility(false);
                remindDialog.setRemindImg(R.drawable.conductivity_test);
                remindDialog.setRemindMsg(getString(R.string.conductivity_test_remind));
                remindDialog.setDialogBtnCallback(new RemindDialog.DialogBtnCallBack() { // from class: com.kkkcut.e20j.ui.fragment.setting.OtherSettingFragment.4
                    @Override // com.kkkcut.e20j.ui.dialog.RemindDialog.DialogBtnCallBack
                    public void onDialogButClick(boolean z) {
                        OperationManager.getInstance().sendOrder(Command.TurnOffSpindle(), OperateType.NONE);
                    }
                });
                return;
            case R.id.bt_continue_move /* 2131361918 */:
                this.totalTimes = SPUtils.getInt(SpKeys.TOTAL_MOVE_TIMES, 1);
                int i = SPUtils.getInt(SpKeys.CURRENT_MOVE_TIMES, 1);
                this.currentIndex = i;
                showLoadingDialog(String.valueOf(i), true);
                this.hasMove = true;
                this.yStr = SPUtils.getString(SpKeys.MOVE_Y, "5200");
                this.xStr = SPUtils.getString(SpKeys.MOVE_X, "6300");
                this.zStr = SPUtils.getString(SpKeys.MOVE_Z, "2200");
                OperationManager.getInstance().sendOrder(Command.DecoderOperation(1, 0, (int) (Integer.parseInt(this.xStr) / MachineData.dXScale), (int) (Integer.parseInt(this.yStr) / MachineData.dYScale), (int) (Integer.parseInt(this.zStr) / MachineData.dZScale), ""), OperateType.MOVE_XYZ);
                return;
            case R.id.bt_dispaley_setting /* 2131361926 */:
                ((SettingFragment) getParentFragment()).start(BrandVisiBilitySettingFragment.newInstance());
                return;
            case R.id.bt_export /* 2131361930 */:
                showMultiDialog();
                return;
            case R.id.bt_import /* 2131361939 */:
                UserDataDaoManager.getInstance(getContext());
                findUDisk(0);
                return;
            case R.id.bt_ok /* 2131361962 */:
                String trim = this.etRatio.getText().toString().trim();
                if (TextUtils.isEmpty(trim)) {
                    return;
                }
                float parseFloat = Float.parseFloat(trim);
                MachineData.dXScale = parseFloat;
                MachineData.dYScale = parseFloat;
                ToastUtil.showToast(R.string.successful_setup);
                return;
            case R.id.bt_start_move /* 2131361976 */:
                String trim2 = this.etX.getText().toString().trim();
                this.xStr = trim2;
                if (TextUtils.isEmpty(trim2)) {
                    this.xStr = "6300";
                }
                String trim3 = this.etY.getText().toString().trim();
                this.yStr = trim3;
                if (TextUtils.isEmpty(trim3)) {
                    this.yStr = "5200";
                }
                String trim4 = this.etZ.getText().toString().trim();
                this.zStr = trim4;
                if (TextUtils.isEmpty(trim4)) {
                    this.zStr = "2200";
                }
                if ("0".equals(this.xStr) && "0".equals(this.yStr) && "0".equals(this.zStr)) {
                    return;
                }
                String trim5 = this.etMoveTime.getText().toString().trim();
                if (TextUtils.isEmpty(trim5)) {
                    trim5 = "1";
                }
                SPUtils.put(SpKeys.MOVE_X, this.xStr);
                SPUtils.put(SpKeys.MOVE_Y, this.yStr);
                SPUtils.put(SpKeys.MOVE_Z, this.zStr);
                int parseInt = Integer.parseInt(trim5);
                this.totalTimes = parseInt;
                SPUtils.put(SpKeys.TOTAL_MOVE_TIMES, parseInt);
                this.currentIndex = 1;
                this.btContinueMove.setVisibility(0);
                showLoadingDialog(String.valueOf(this.currentIndex), true);
                this.hasMove = true;
                OperationManager.getInstance().sendOrder(Command.DecoderOperation(1, 0, (int) (Integer.parseInt(this.xStr) / MachineData.dXScale), (int) (Integer.parseInt(this.yStr) / MachineData.dYScale), (int) (Integer.parseInt(this.zStr) / MachineData.dZScale), ""), OperateType.MOVE_XYZ);
                return;
            case R.id.tv_reset /* 2131362983 */:
                WarningDialog warningDialog = new WarningDialog(getContext());
                warningDialog.setRemind(getString(R.string.reset_warning));
                warningDialog.setCancelBtVisible(true);
                warningDialog.show();
                warningDialog.setDialogBtnCallback(new WarningDialog.DialogBtnCallBack() { // from class: com.kkkcut.e20j.ui.fragment.setting.OtherSettingFragment.3
                    @Override // com.kkkcut.e20j.ui.dialog.WarningDialog.DialogBtnCallBack
                    public void onDialogButClick(boolean z) {
                        if (z) {
                            SPUtils.clear();
                            FileUtil.deleteFile("/data/data/" + MyApplication.getInstance().getPackageName());
                            Intent launchIntentForPackage = OtherSettingFragment.this.getContext().getPackageManager().getLaunchIntentForPackage(OtherSettingFragment.this.getContext().getPackageName());
                            launchIntentForPackage.addFlags(67108864);
                            OtherSettingFragment.this.startActivity(launchIntentForPackage);
                            Process.killProcess(Process.myPid());
                        }
                    }
                });
                return;
            case R.id.tv_upload_log /* 2131363046 */:
                uploadLog();
                return;
            default:
                return;
        }
    }

    private void uploadLog() {
        File file;
        OkHttpClient okHttpClient = new OkHttpClient();
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        File[] listFiles = MyApplication.getInstance().getExternalFilesDir("").listFiles();
        int length = listFiles.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                file = null;
                break;
            }
            file = listFiles[i];
            if (file.getName().startsWith(CoreConstant.SERIAL_FILE_NAME) && file.isFile()) {
                break;
            } else {
                i++;
            }
        }
        if (file == null) {
            ToastUtil.showToast(getString(R.string.file_does_not_exist));
            return;
        }
        builder.addFormDataPart("flie", file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file));
        var hashMap = new HashMap<String, String>();
        String string = SPUtils.getString("series");
        if (TextUtils.isEmpty(string)) {
            ToastUtil.showToast(R.string.serial_not_empty);
            return;
        }
        hashMap.put("LotSN", string);
        for (var str : hashMap.keySet()) {
            builder.addFormDataPart(str, (String) hashMap.get(str));
        }
        showLoadingDialog(getString(R.string.waitting));
        okHttpClient.newBuilder().readTimeout(20L, TimeUnit.SECONDS).build().newCall(new Request.Builder().url(RetrofitManager.getInstance().getLogUrl()).addHeader("Content-Type", "application/json;charset=UTF-8").post(builder.build()).build()).enqueue(new Callback() { // from class: com.kkkcut.e20j.ui.fragment.setting.OtherSettingFragment.5
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                LogUtil.i(OtherSettingFragment.TAG, "onFailure: " + iOException.getMessage());
                OtherSettingFragment.this.dismissLoadingDialog();
                ToastUtil.showToast("onFailure: " + iOException.getMessage());
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, final Response response) {
                OtherSettingFragment.this.dismissLoadingDialog();
                if (response.isSuccessful()) {
                    OtherSettingFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.kkkcut.e20j.ui.fragment.setting.OtherSettingFragment.5.1
                        @Override // java.lang.Runnable
                        public void run() {
                            ToastUtil.showToast(R.string.finish);
                        }
                    });
                } else {
                    OtherSettingFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.kkkcut.e20j.ui.fragment.setting.OtherSettingFragment.5.2
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                ToastUtil.showToast("Failed:" + response.body().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
    }

    public void findUDisk(int i) {
        this.actionType = i;
        UsbMassStorageDevice[] massStorageDevices = UsbMassStorageDevice.getMassStorageDevices(getContext());
        if (massStorageDevices.length < 1) {
            ToastUtil.showToast(R.string.u_disk_is_not_detected);
            return;
        }
        UsbMassStorageDevice usbMassStorageDevice = massStorageDevices[0];
        try {
            UsbManager usbManager = (UsbManager) getContext().getSystemService("usb");
            this.usbMassStorageDevice = usbMassStorageDevice;
            if (usbManager.hasPermission(usbMassStorageDevice.getUsbDevice())) {
                usbMassStorageDevice.init();
                if (usbMassStorageDevice.getPartitions().get(0) == null) {
                    ToastUtil.showToast("Does not support this USB flash drive");
                    return;
                }
                FileSystem fileSystem = (FileSystem) usbMassStorageDevice.getPartitions().get(0).getFileSystem();
                if (i == 1) {
                    export2usb(fileSystem);
                    return;
                } else {
                    import2Sql(fileSystem);
                    return;
                }
            }
            usbManager.requestPermission(usbMassStorageDevice.getUsbDevice(), PendingIntent.getBroadcast(getContext(), 0, new Intent(ACTION_USB_PERMISSION), 0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showMultiDialog() {
        final boolean[] zArr = {true, true, true};
        final String[] strArr = {CutHistoryDataDao.TABLENAME, CollectionDataDao.TABLENAME, CustomKeyDao.TABLENAME};
        String[] strArr2 = {getString(R.string.cut_history), getString(R.string.favorites), getString(R.string.my_key_info)};
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.please_select_the_data_you_want_to_export);
        builder.setMultiChoiceItems(strArr2, zArr, new DialogInterface.OnMultiChoiceClickListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.OtherSettingFragment.6
            @Override // android.content.DialogInterface.OnMultiChoiceClickListener
            public void onClick(DialogInterface dialogInterface, int i, boolean z) {
                zArr[i] = z;
            }
        });
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.OtherSettingFragment.7
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                OtherSettingFragment.this.tableNameStr = "";
                int i2 = 0;
                while (true) {
                    boolean[] zArr2 = zArr;
                    if (i2 >= zArr2.length) {
                        break;
                    }
                    if (zArr2[i2]) {
                        StringBuilder sb = new StringBuilder();
                        OtherSettingFragment otherSettingFragment = OtherSettingFragment.this;
                        sb.append(otherSettingFragment.tableNameStr);
                        sb.append(strArr[i2]);
                        sb.append(",");
                        otherSettingFragment.tableNameStr = sb.toString();
                    }
                    i2++;
                }
                if (TextUtils.isEmpty(OtherSettingFragment.this.tableNameStr)) {
                    return;
                }
                OtherSettingFragment.this.findUDisk(1);
            }
        };
        builder.setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null);
        builder.setPositiveButton(R.string.ok, onClickListener);
        AlertDialog create = builder.create();
        create.show();
        create.setCanceledOnTouchOutside(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void export2usb(FileSystem fileSystem) throws IOException {
        if (TextUtils.isEmpty(this.tableNameStr)) {
            return;
        }
        String str = "/data/data/" + MyApplication.getInstance().getPackageName() + "/databases/userData.db";
        String path = Environment.getExternalStorageDirectory().getPath();
        final UsbFile rootDirectory = (UsbFile)fileSystem.getRootDirectories().iterator().next();
        new SQLiteToExcel.Builder(getContext()).setDataBase(str).setTables(this.tableNameStr.split(",")).setOutputFileName("userDb.xls").setOutputPath(path).start(new SQLiteToExcel.ExportListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.OtherSettingFragment.8
            @Override // com.kkkcut.e20j.ui.fragment.setting.SQLiteToExcel.ExportListener
            public void onStart() {
                Log.d(OtherSettingFragment.TAG, "onStart() called");
                OtherSettingFragment otherSettingFragment = OtherSettingFragment.this;
                otherSettingFragment.showLoadingDialog(otherSettingFragment.getString(R.string.waitting));
            }

            @Override // com.kkkcut.e20j.ui.fragment.setting.SQLiteToExcel.ExportListener
            public void onCompleted(String str2) {
                Log.d(OtherSettingFragment.TAG, "onCompleted() called with: filePath = [" + str2 + "]");
                OtherSettingFragment.this.saveSDFileToUsb(new File(str2), rootDirectory);
                OtherSettingFragment.this.dismissLoadingDialog();
                ToastUtil.showToast(R.string.finish);
            }

            @Override // com.kkkcut.e20j.ui.fragment.setting.SQLiteToExcel.ExportListener
            public void onError(Exception exc) {
                Log.d(OtherSettingFragment.TAG, "onError() called with: e = [" + exc + "]");
                OtherSettingFragment.this.dismissLoadingDialog();
                ToastUtil.showToast(R.string.error_occurred);
            }
        });
    }

    public boolean saveSDFileToUsb(File file, UsbFile usbFile) {
        UsbFile usbFile2 = null;
        try {
            boolean z = false;
            for (UsbFile usbFile3 : usbFile.listFiles()) {
                if (usbFile3.getName().equals(file.getName())) {
                    usbFile2 = usbFile3;
                    z = true;
                }
            }
            if (z) {
                usbFile2.delete();
            }
            UsbFile createFile = usbFile.createFile(file.getName());
            FileInputStream fileInputStream = new FileInputStream(file);
            UsbFileOutputStream usbFileOutputStream = new UsbFileOutputStream(createFile);
            byte[] bArr = new byte[8192];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read != -1) {
                    usbFileOutputStream.write(bArr, 0, read);
                } else {
                    usbFileOutputStream.flush();
                    fileInputStream.close();
                    usbFileOutputStream.close();
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void import2Sql(FileSystem fileSystem) {
        String str = "/data/data/" + MyApplication.getInstance().getPackageName() + "/databases/userData.db";
        UsbFile rootDirectory = fileSystem.getRootDirectory();
        File file = new File(Environment.getExternalStorageDirectory(), "userDb.xls");
        try {
            UsbFile search = rootDirectory.search("/userDb.xls");
            if (search != null && !saveUSbFileToLocal(search, file.getPath())) {
                Log.i(TAG, "import2Sql: 拷贝excel至本地失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        new ExcelToSQLite.Builder(getContext()).setDataBasePath(str).setExcelPath(file.getPath()).start(new ExcelToSQLite.ImportListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.OtherSettingFragment.9
            @Override // com.kkkcut.e20j.ui.fragment.setting.ExcelToSQLite.ImportListener
            public void onStart() {
                Log.d(OtherSettingFragment.TAG, "onStart() called");
                OtherSettingFragment otherSettingFragment = OtherSettingFragment.this;
                otherSettingFragment.showLoadingDialog(otherSettingFragment.getString(R.string.waitting));
            }

            @Override // com.kkkcut.e20j.ui.fragment.setting.ExcelToSQLite.ImportListener
            public void onCompleted(String str2) {
                Log.d(OtherSettingFragment.TAG, "onCompleted() called with: result = [" + str2 + "]");
                OtherSettingFragment.this.dismissLoadingDialog();
                ToastUtil.showToast(R.string.finish);
            }

            @Override // com.kkkcut.e20j.ui.fragment.setting.ExcelToSQLite.ImportListener
            public void onError(Exception exc) {
                Log.d(OtherSettingFragment.TAG, "onError() called with: e = [" + exc + "]");
                OtherSettingFragment.this.dismissLoadingDialog();
                ToastUtil.showToast(R.string.error_occurred);
            }
        });
    }

    public boolean saveUSbFileToLocal(UsbFile usbFile, String str) {
        try {
            UsbFileInputStream usbFileInputStream = new UsbFileInputStream(usbFile);
            FileOutputStream fileOutputStream = new FileOutputStream(str);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = usbFileInputStream.read(bArr);
                if (read != -1) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.flush();
                    usbFileInputStream.close();
                    fileOutputStream.close();
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.kkkcut.e20j.base.BaseFFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        getContext().unregisterReceiver(this.usbReceiver);
    }

    public boolean onLongClick() {
        showEditDialog();
        return true;
    }
}
