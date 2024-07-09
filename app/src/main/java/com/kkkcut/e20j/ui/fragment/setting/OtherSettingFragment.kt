package com.kkkcut.e20j.ui.fragment.setting

import android.app.AlertDialog
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Typeface
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbManager
import android.os.Bundle
import android.os.Environment
import android.os.Parcelable
import android.os.Process
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.cutting.machine.Command
import com.cutting.machine.CoreConstant
import com.cutting.machine.CuttingMachine
import com.cutting.machine.MachineInfo
import com.cutting.machine.OperateType
import com.cutting.machine.clamp.MachineData
import com.cutting.machine.communication.OperationManager
import com.cutting.machine.error.ErrorBean
import com.kkkcut.e20j.DbBean.userDB.CollectionDataDao
import com.kkkcut.e20j.DbBean.userDB.CustomKeyDao
import com.kkkcut.e20j.DbBean.userDB.CutHistoryDataDao
import com.kkkcut.e20j.MyApplication
import com.kkkcut.e20j.SpKeys
import com.kkkcut.e20j.androidquick.network.RetrofitManager
import com.kkkcut.e20j.androidquick.tool.AppUtil
import com.kkkcut.e20j.androidquick.tool.FileUtil
import com.kkkcut.e20j.androidquick.tool.LogUtil
import com.kkkcut.e20j.androidquick.tool.SPUtils
import com.kkkcut.e20j.androidquick.tool.ToastUtil
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter
import com.kkkcut.e20j.dao.UserDataDaoManager
import com.kkkcut.e20j.ui.dialog.EditDialog
import com.kkkcut.e20j.ui.dialog.EditDialog.DialogInputFinishCallBack
import com.kkkcut.e20j.ui.dialog.RemindDialog
import com.kkkcut.e20j.ui.dialog.WarningDialog
import com.kkkcut.e20j.ui.fragment.BaseBackFragment
import com.kkkcut.e20j.ui.fragment.setting.ExcelToSQLite.ImportListener
import com.kkkcut.e20j.ui.fragment.setting.SQLiteToExcel.ExportListener
import com.kkkcut.e20j.us.R
import me.jahnen.libaums.core.UsbMassStorageDevice
import me.jahnen.libaums.core.fs.UsbFile
import me.jahnen.libaums.core.fs.UsbFileInputStream
import me.jahnen.libaums.core.fs.UsbFileOutputStream
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request.Builder
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.Response
import org.greenrobot.eventbus.EventBus
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.nio.file.FileSystem
import java.util.concurrent.TimeUnit


/* loaded from: classes.dex */
class OtherSettingFragment() : BaseBackFragment() {
    private var actionType: Int = 0

    var btContinueMove: Button? = null

    var btDispaleySetting: TextView? = null

    var btOk: Button? = null

    var btStartMove: LinearLayout? = null

    var cbBarCode: CheckBox? = null

    var cbChineseCar: CheckBox? = null

    var cbDecoderPositionDetect: CheckBox? = null

    var cbSafetyDoor: CheckBox? = null

    var etMoveTime: EditText? = null

    var etRatio: EditText? = null

    var etX: EditText? = null

    var etY: EditText? = null

    var etZ: EditText? = null
    private var hasMove: Boolean = false

    var llDatabaseExport: LinearLayout? = null

    var llMove: LinearLayout? = null

    var llRatioSetup: LinearLayout? = null

    var rb150: RadioButton? = null

    var rbInch: RadioButton? = null
    var tableNameStr: String? = null
    private var usbMassStorageDevice: UsbMassStorageDevice? = null
    private var xStr: String? = null
    private var yStr: String? = null
    private var zStr: String? = null
    private var currentIndex: Int = 1
    private var totalTimes: Int = 1
    private val usbReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        // from class: com.kkkcut.e20j.ui.fragment.setting.OtherSettingFragment.1
        // android.content.BroadcastReceiver
        override fun onReceive(context: Context, intent: Intent) {
            val action: String? = intent.action
            if ((ACTION_USB_PERMISSION == action)) {
                val usbDevice: UsbDevice? =
                    intent.getParcelableExtra<Parcelable>("device") as UsbDevice?
                if (!intent.getBooleanExtra("permission", false) || usbDevice == null) {
                    return
                }
                Log.i(TAG, "ACTION_USB_PERMISSION: ")
                try {
                    usbMassStorageDevice!!.init()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                if ((this@OtherSettingFragment.usbMassStorageDevice == null) || (usbMassStorageDevice!!.partitions == null) || (usbMassStorageDevice!!.partitions.get(
                        0
                    ) == null) || (usbMassStorageDevice!!.partitions.get(0).fileSystem == null)
                ) {
                    ToastUtil.showToast("Does not support this USB flash drive")
                }
                val fileSystem: FileSystem =
                    usbMassStorageDevice!!.partitions.get(0).fileSystem as FileSystem
                try {
                    if (this@OtherSettingFragment.actionType == 1) {
                        this@OtherSettingFragment.export2usb(fileSystem)
                    } else {
                        this@OtherSettingFragment.import2Sql(fileSystem)
                    }
                    return
                } catch (e2: IOException) {
                    e2.printStackTrace()
                    return
                }
            }
            if (("android.hardware.usb.action.USB_DEVICE_ATTACHED" == action)) {
                Log.d(TAG, "USB device attached")
            } else if (("android.hardware.usb.action.USB_DEVICE_DETACHED" == action)) {
                Log.d(TAG, "USB device detached")
            }
        }
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_other_setting
    }

    // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    override fun setTitleStr(): String? {
        return getString(R.string.others)
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
        cbSafetyDoor!!.setChecked(SPUtils.getBoolean(SpKeys.COVER, true))
        if (MyApplication.getInstance().isShowRealDepth()) {
            llMove!!.setVisibility(0)
            btStartMove!!.setVisibility(0)
        }
        if (MachineInfo.isChineseMachine() || MachineInfo.isE20Us(getContext())) {
            cbChineseCar!!.setVisibility(8)
        } else {
            cbChineseCar!!.setVisibility(0)
            cbChineseCar!!.setChecked(
                SPUtils.getBoolean(
                    SpKeys.SHOW_CHINESE_CAR,
                    MachineInfo.isE20Standard(getContext())
                )
            )
        }
        if (MachineInfo.isE20Us(getContext())) {
            btDispaleySetting!!.setVisibility(0)
            cbBarCode!!.setVisibility(0)
            cbBarCode!!.setChecked(SPUtils.getBoolean("bar_code", true))
        } else {
            btDispaleySetting!!.setVisibility(8)
            cbBarCode!!.setVisibility(8)
        }
        if (MachineInfo.isE9Standard(getContext())) {
            cbDecoderPositionDetect!!.setVisibility(0)
            cbDecoderPositionDetect!!.setChecked(SPUtils.getBoolean(SpKeys.Not_detect_decoder_position))
        } else {
            cbDecoderPositionDetect!!.setVisibility(8)
        }
        val font: Typeface? = ResourcesCompat.getFont((getContext())!!, R.font.pingfang_sc_regular)
        cbChineseCar!!.setTypeface(font)
        cbSafetyDoor!!.setTypeface(font)
        val i: Int = SPUtils.getInt(SpKeys.TOTAL_MOVE_TIMES)
        val i2: Int = SPUtils.getInt(SpKeys.CURRENT_MOVE_TIMES)
        if ((i == -1) || (i2 == -1) || (i2 == i)) {
            btContinueMove!!.setVisibility(8)
        } else {
            btContinueMove!!.setVisibility(0)
        }
        val intentFilter: IntentFilter = IntentFilter(ACTION_USB_PERMISSION)
        intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_ATTACHED")
        intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED")
        getContext()!!.registerReceiver(this.usbReceiver, intentFilter)
        if (SPUtils.getInt(SpKeys.DOUBLE_KEY_CUTTER, 200) == 150) {
            rb150!!.setChecked(true)
        }
        if (AppUtil.isApkInDebug(getContext())) {
            llRatioSetup!!.setVisibility(0)
            etRatio!!.setText(MachineData.getXRatio().toString())
        }
        if (MachineInfo.isE9()) {
            etX!!.setText("10600")
            etY!!.setText("2080")
            etZ!!.setText("2200")
        } else {
            etX!!.setText("6300")
            etY!!.setText("5200")
            etZ!!.setText("2400")
        }
        if (SPUtils.getBoolean(SpKeys.UNIT_INCH, false)) {
            rbInch!!.setChecked(true)
        }
    }

    fun onCheckedChanged(compoundButton: CompoundButton, z: Boolean) {
        when (compoundButton.getId()) {
            R.id.cb_bar_code -> {
                SPUtils.put("bar_code", z)
                EventBus.getDefault().post(EventCenter<Any?>(54, z))
                return
            }

            R.id.cb_chinese_car -> {
                SPUtils.put(SpKeys.SHOW_CHINESE_CAR, cbChineseCar!!.isChecked())
                EventBus.getDefault().post(EventCenter<Any?>(45))
                return
            }

            R.id.cb_decoder_position_detect -> {
                CuttingMachine.getInstance().setNotDetectDecoder(z)
                SPUtils.put(SpKeys.Not_detect_decoder_position, z)
                return
            }

            R.id.cb_move -> {
                llMove!!.setVisibility(if (z) 0 else 8)
                return
            }

            R.id.cb_safety_door -> if (z) {
                OperationManager.getInstance()
                    .sendOrder(Command.OpenDoorCuttingSettings(1), OperateType.SAVETY_DOOR)
                SPUtils.put(SpKeys.COVER, true)
                return
            } else {
                OperationManager.getInstance()
                    .sendOrder(Command.OpenDoorCuttingSettings(0), OperateType.SAVETY_DOOR)
                SPUtils.put(SpKeys.COVER, false)
                return
            }

            R.id.rb_150 -> {
                if (z) {
                    SPUtils.put(SpKeys.DOUBLE_KEY_CUTTER, 150)
                    return
                }
                return
            }

            R.id.rb_200 -> {
                if (z) {
                    SPUtils.put(SpKeys.DOUBLE_KEY_CUTTER, 200)
                    return
                }
                return
            }

            R.id.rb_inch -> {
                if (z) {
                    SPUtils.put(SpKeys.UNIT_INCH, true)
                    return
                }
                return
            }

            R.id.rb_mm -> {
                if (z) {
                    SPUtils.put(SpKeys.UNIT_INCH, false)
                    return
                }
                return
            }

            else -> return
        }
    }

    // com.kkkcut.e20j.base.BaseFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun onEventComing(eventCenter: EventCenter<*>) {
        val eventCode: Int = eventCenter.getEventCode()
        if (eventCode != 32) {
            if (eventCode != 33) {
                return
            }
            dismissLoadingDialog()
            this.hasMove = false
            this.currentIndex = 0
            showErrorDialog(getContext(), eventCenter.getData() as ErrorBean?)
            return
        }
        if ((eventCenter.getData()) === OperateType.MOVE_XYZ) {
            if (this.hasMove) {
                this.hasMove = false
                OperationManager.getInstance()
                    .sendOrder(Command.DecoderOperation(1, 0, 1, 1, 1, ""), OperateType.MOVE_XYZ)
                return
            }
            SPUtils.put(SpKeys.CURRENT_MOVE_TIMES, this.currentIndex)
            val i: Int = this.currentIndex
            if (i != this.totalTimes) {
                val i2: Int = i + 1
                this.currentIndex = i2
                showLoadingDialog(i2.toString(), true)
                this.hasMove = true
                OperationManager.getInstance().sendOrder(
                    Command.DecoderOperation(
                        1,
                        0,
                        (xStr!!.toInt() / MachineData.dXScale).toInt(),
                        (yStr!!.toInt() / MachineData.dYScale).toInt(),
                        (zStr!!.toInt() / MachineData.dZScale).toInt(),
                        ""
                    ), OperateType.MOVE_XYZ
                )
                return
            }
            btContinueMove!!.setVisibility(8)
            dismissLoadingDialog()
        }
    }

    private fun resetStatus() {
        this.hasMove = false
        dismissLoadingDialog()
        OperationManager.getInstance().setOperateType(OperateType.NONE)
    }

    private fun showEditDialog() {
        val editDialog: EditDialog = EditDialog(getContext())
        editDialog.setContentNullable(true)
        editDialog.setDialogBtnCallback(object : DialogInputFinishCallBack {
            // from class: com.kkkcut.e20j.ui.fragment.setting.OtherSettingFragment.2
            // com.kkkcut.e20j.ui.dialog.EditDialog.DialogInputFinishCallBack
            override fun onDialogButClick(str: String) {
                if (("88888888" == str)) {
                    MyApplication.getInstance().setShowRealDepth(true)
                    llMove!!.setVisibility(0)
                    btStartMove!!.setVisibility(0)
                    EventBus.getDefault().post(EventCenter<Any?>(21))
                    return
                }
                ToastUtil.showToast(R.string.password_is_incorrect)
            }
        })
        if (editDialog.isShowing()) {
            return
        }
        editDialog.show()
    }

    fun onViewClicked(view: View) {
        when (view.getId()) {
            R.id.bt_conductivity_test -> {
                OperationManager.getInstance().sendOrder(Command.OnductiveTestOperation())
                val remindDialog: RemindDialog = RemindDialog(getContext())
                remindDialog.show()
                remindDialog.setCancleBtnVisibility(false)
                remindDialog.setRemindImg(R.drawable.conductivity_test)
                remindDialog.setRemindMsg(getString(R.string.conductivity_test_remind))
                remindDialog.setDialogBtnCallback(object : RemindDialog.DialogBtnCallBack {
                    // from class: com.kkkcut.e20j.ui.fragment.setting.OtherSettingFragment.4
                    // com.kkkcut.e20j.ui.dialog.RemindDialog.DialogBtnCallBack
                    override fun onDialogButClick(z: Boolean) {
                        OperationManager.getInstance()
                            .sendOrder(Command.TurnOffSpindle(), OperateType.NONE)
                    }
                })
                return
            }

            R.id.bt_continue_move -> {
                this.totalTimes = SPUtils.getInt(SpKeys.TOTAL_MOVE_TIMES, 1)
                val i: Int = SPUtils.getInt(SpKeys.CURRENT_MOVE_TIMES, 1)
                this.currentIndex = i
                showLoadingDialog(i.toString(), true)
                this.hasMove = true
                this.yStr = SPUtils.getString(SpKeys.MOVE_Y, "5200")
                this.xStr = SPUtils.getString(SpKeys.MOVE_X, "6300")
                this.zStr = SPUtils.getString(SpKeys.MOVE_Z, "2200")
                OperationManager.getInstance().sendOrder(
                    Command.DecoderOperation(
                        1,
                        0,
                        (xStr!!.toInt() / MachineData.dXScale).toInt(),
                        (yStr!!.toInt() / MachineData.dYScale).toInt(),
                        (zStr!!.toInt() / MachineData.dZScale).toInt(),
                        ""
                    ), OperateType.MOVE_XYZ
                )
                return
            }

            R.id.bt_dispaley_setting -> {
                (getParentFragment() as SettingFragment?)!!.start(BrandVisiBilitySettingFragment.Companion.newInstance())
                return
            }

            R.id.bt_export -> {
                showMultiDialog()
                return
            }

            R.id.bt_import -> {
                UserDataDaoManager.getInstance(getContext())
                findUDisk(0)
                return
            }

            R.id.bt_ok -> {
                val trim: String = etRatio!!.getText().toString().trim({ it <= ' ' })
                if (TextUtils.isEmpty(trim)) {
                    return
                }
                val parseFloat: Float = trim.toFloat()
                MachineData.dXScale = parseFloat
                MachineData.dYScale = parseFloat
                ToastUtil.showToast(R.string.successful_setup)
                return
            }

            R.id.bt_start_move -> {
                val trim2: String = etX!!.getText().toString().trim({ it <= ' ' })
                this.xStr = trim2
                if (TextUtils.isEmpty(trim2)) {
                    this.xStr = "6300"
                }
                val trim3: String = etY!!.getText().toString().trim({ it <= ' ' })
                this.yStr = trim3
                if (TextUtils.isEmpty(trim3)) {
                    this.yStr = "5200"
                }
                val trim4: String = etZ!!.getText().toString().trim({ it <= ' ' })
                this.zStr = trim4
                if (TextUtils.isEmpty(trim4)) {
                    this.zStr = "2200"
                }
                if (("0" == this.xStr) && ("0" == this.yStr) && ("0" == this.zStr)) {
                    return
                }
                var trim5: String = etMoveTime!!.getText().toString().trim({ it <= ' ' })
                if (TextUtils.isEmpty(trim5)) {
                    trim5 = "1"
                }
                SPUtils.put(SpKeys.MOVE_X, this.xStr)
                SPUtils.put(SpKeys.MOVE_Y, this.yStr)
                SPUtils.put(SpKeys.MOVE_Z, this.zStr)
                val parseInt: Int = trim5.toInt()
                this.totalTimes = parseInt
                SPUtils.put(SpKeys.TOTAL_MOVE_TIMES, parseInt)
                this.currentIndex = 1
                btContinueMove!!.setVisibility(0)
                showLoadingDialog(currentIndex.toString(), true)
                this.hasMove = true
                OperationManager.getInstance().sendOrder(
                    Command.DecoderOperation(
                        1,
                        0,
                        (xStr!!.toInt() / MachineData.dXScale).toInt(),
                        (yStr!!.toInt() / MachineData.dYScale).toInt(),
                        (zStr!!.toInt() / MachineData.dZScale).toInt(),
                        ""
                    ), OperateType.MOVE_XYZ
                )
                return
            }

            R.id.tv_reset -> {
                val warningDialog: WarningDialog = WarningDialog(getContext())
                warningDialog.setRemind(getString(R.string.reset_warning))
                warningDialog.setCancelBtVisible(true)
                warningDialog.show()
                warningDialog.setDialogBtnCallback(object : WarningDialog.DialogBtnCallBack {
                    // from class: com.kkkcut.e20j.ui.fragment.setting.OtherSettingFragment.3
                    // com.kkkcut.e20j.ui.dialog.WarningDialog.DialogBtnCallBack
                    override fun onDialogButClick(z: Boolean) {
                        if (z) {
                            SPUtils.clear()
                            FileUtil.deleteFile(
                                "/data/data/" + MyApplication.getInstance().getPackageName()
                            )
                            val launchIntentForPackage: Intent? =
                                getContext()!!.getPackageManager().getLaunchIntentForPackage(
                                    getContext()!!.getPackageName()
                                )
                            launchIntentForPackage!!.addFlags(67108864)
                            this@OtherSettingFragment.startActivity((launchIntentForPackage))
                            Process.killProcess(Process.myPid())
                        }
                    }
                })
                return
            }

            R.id.tv_upload_log -> {
                uploadLog()
                return
            }

            else -> return
        }
    }


    private fun uploadLog() {
        var file: File?
        val okHttpClient = OkHttpClient()
        var builder = MultipartBody.Builder()
        val listFiles = MyApplication.getInstance().getExternalFilesDir("")!!
            .listFiles()
        val length = listFiles.size
        var i = 0
        while (true) {
            if (i >= length) {
                file = null
                break
            }
            file = listFiles[i]
            if (file.name.startsWith(CoreConstant.SERIAL_FILE_NAME) && file.isFile) {
                break
            } else {
                i++
            }
        }
        if (file == null) {
            ToastUtil.showToast(getString(R.string.file_does_not_exist))
            return
        }
        builder.addFormDataPart("flie", file.getName(), file.asRequestBody("multipart/form-data".toMediaTypeOrNull()));
        val hashMap = HashMap<String, String>()
        val string = SPUtils.getString("series")
        if (TextUtils.isEmpty(string)) {
            ToastUtil.showToast(R.string.serial_not_empty)
            return
        }
        hashMap["LotSN"] = string
        for (str in hashMap.keys) {
            val result = hashMap[str]
            if (result != null ) {
                builder.addFormDataPart(str, result)
            }
        }
        showLoadingDialog(getString(R.string.waitting))
        okHttpClient.newBuilder().readTimeout(20L, TimeUnit.SECONDS).build().newCall(
            Builder().url(RetrofitManager.getInstance().logUrl)
                .addHeader("Content-Type", "application/json;charset=UTF-8").post(builder.build())
                .build()
        ).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                LogUtil.e(TAG, "onFailure: " + e.message)
                this@OtherSettingFragment.dismissLoadingDialog()
                ToastUtil.showToast("onFailure: " + e.message)
            }

            override fun onResponse(call: Call, response: Response) {
                this@OtherSettingFragment.dismissLoadingDialog()
                if (response.isSuccessful) {
                    this@OtherSettingFragment.activity!!.runOnUiThread {
                        ToastUtil.showToast(
                            R.string.finish
                        )
                    }
                } else {
                    this@OtherSettingFragment.activity!!.runOnUiThread {
                        try {
                            ToastUtil.showToast("Failed:" + response.body!!.string())
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                }
            }

        })
    }
    fun findUDisk(i: Int) {
        this.actionType = i
        val massStorageDevices: Array<UsbMassStorageDevice> =
            UsbMassStorageDevice.getMassStorageDevices(
                (getContext())!!
            )
        if (massStorageDevices.size < 1) {
            ToastUtil.showToast(R.string.u_disk_is_not_detected)
            return
        }
        val usbMassStorageDevice: UsbMassStorageDevice = massStorageDevices.get(0)
        try {
            val usbManager: UsbManager = getContext()!!.getSystemService("usb") as UsbManager
            this.usbMassStorageDevice = usbMassStorageDevice
            if (usbManager.hasPermission(usbMassStorageDevice.usbDevice)) {
                usbMassStorageDevice.init()
                if (usbMassStorageDevice.partitions.get(0) == null) {
                    ToastUtil.showToast("Does not support this USB flash drive")
                    return
                }
                val fileSystem: FileSystem =
                    usbMassStorageDevice.partitions.get(0).fileSystem as FileSystem
                if (i == 1) {
                    export2usb(fileSystem)
                    return
                } else {
                    import2Sql(fileSystem)
                    return
                }
            }
            usbManager.requestPermission(
                usbMassStorageDevice.usbDevice, PendingIntent.getBroadcast(
                    getContext(), 0, Intent(
                        ACTION_USB_PERMISSION
                    ), 0
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun showMultiDialog() {
        val zArr: BooleanArray = booleanArrayOf(true, true, true)
        val strArr: Array<String> = arrayOf(
            CutHistoryDataDao.TABLENAME,
            CollectionDataDao.TABLENAME,
            CustomKeyDao.TABLENAME
        )
        val strArr2: Array<String> = arrayOf(
            getString(R.string.cut_history), getString(R.string.favorites), getString(
                R.string.my_key_info
            )
        )
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setTitle(R.string.please_select_the_data_you_want_to_export)
        builder.setMultiChoiceItems(strArr2, zArr
        ) { dialogInterface, i, z ->
            zArr[i] = z
        }
        val onClickListener =
        builder.setNegativeButton(R.string.cancel, null)
        builder.setPositiveButton(R.string.ok) { dialogInterface: DialogInterface, i: Int ->
            this@OtherSettingFragment.tableNameStr = ""
            var i2: Int = 0
            while (true) {
                val zArr2: BooleanArray = zArr
                if (i2 >= zArr2.size) {
                    break
                }
                if (zArr2.get(i2)) {
                    val sb: StringBuilder = StringBuilder()
                    val otherSettingFragment: OtherSettingFragment =
                        this@OtherSettingFragment
                    sb.append(otherSettingFragment.tableNameStr)
                    sb.append(strArr.get(i2))
                    sb.append(",")
                    otherSettingFragment.tableNameStr = sb.toString()
                }
                i2++
            }
            if (TextUtils.isEmpty(this@OtherSettingFragment.tableNameStr)) {
                return@setPositiveButton
            }
            this@OtherSettingFragment.findUDisk(1)
        }

        val create: AlertDialog = builder.create()
        create.show()
        create.setCanceledOnTouchOutside(true)
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Throws(IOException::class)
    fun export2usb(fileSystem: FileSystem) {
        if (TextUtils.isEmpty(this.tableNameStr)) {
            return
        }
        val str: String =
            "/data/data/" + MyApplication.getInstance().getPackageName() + "/databases/userData.db"
        val path: String = Environment.getExternalStorageDirectory().getPath()
        val rootDirectory: UsbFile = fileSystem.getRootDirectories().iterator().next() as UsbFile
        SQLiteToExcel.Builder(getContext()).setDataBase(str)
            .setTables(*tableNameStr!!.split(",".toRegex()).dropLastWhile({ it.isEmpty() })
                .toTypedArray()
            ).setOutputFileName("userDb.xls").setOutputPath(path).start(object : ExportListener {
            // from class: com.kkkcut.e20j.ui.fragment.setting.OtherSettingFragment.8
            // com.kkkcut.e20j.ui.fragment.setting.SQLiteToExcel.ExportListener
            override fun onStart() {
                Log.d(TAG, "onStart() called")
                val otherSettingFragment: OtherSettingFragment = this@OtherSettingFragment
                otherSettingFragment.showLoadingDialog(otherSettingFragment.getString(R.string.waitting))
            }

            // com.kkkcut.e20j.ui.fragment.setting.SQLiteToExcel.ExportListener
            override fun onCompleted(str2: String) {
                Log.d(TAG, "onCompleted() called with: filePath = [" + str2 + "]")
                this@OtherSettingFragment.saveSDFileToUsb(File(str2), rootDirectory)
                this@OtherSettingFragment.dismissLoadingDialog()
                ToastUtil.showToast(R.string.finish)
            }

            // com.kkkcut.e20j.ui.fragment.setting.SQLiteToExcel.ExportListener
            override fun onError(exc: Exception) {
                Log.d(TAG, "onError() called with: e = [" + exc + "]")
                this@OtherSettingFragment.dismissLoadingDialog()
                ToastUtil.showToast(R.string.error_occurred)
            }
        })
    }

    fun saveSDFileToUsb(file: File, usbFile: UsbFile): Boolean {
        var usbFile2: UsbFile? = null
        try {
            var z: Boolean = false
            for (usbFile3: UsbFile in usbFile.listFiles()) {
                if ((usbFile3.name == file.getName())) {
                    usbFile2 = usbFile3
                    z = true
                }
            }
            if (z) {
                usbFile2!!.delete()
            }
            val createFile: UsbFile = usbFile.createFile(file.getName())
            val fileInputStream: FileInputStream = FileInputStream(file)
            val usbFileOutputStream: UsbFileOutputStream = UsbFileOutputStream(createFile)
            val bArr: ByteArray = ByteArray(8192)
            while (true) {
                val read: Int = fileInputStream.read(bArr)
                if (read != -1) {
                    usbFileOutputStream.write(bArr, 0, read)
                } else {
                    usbFileOutputStream.flush()
                    fileInputStream.close()
                    usbFileOutputStream.close()
                    return true
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }

    fun import2Sql(fileSystem: FileSystem) {
        val str: String =
            "/data/data/" + MyApplication.getInstance().getPackageName() + "/databases/userData.db"
        val rootDirectory: UsbFile = fileSystem.getRootDirectories().iterator().next() as UsbFile
        val file: File = File(Environment.getExternalStorageDirectory(), "userDb.xls")
        try {
            val search: UsbFile? = rootDirectory.search("/userDb.xls")
            if (search != null && !saveUSbFileToLocal(search, file.getPath())) {
                Log.i(TAG, "import2Sql: 拷贝excel至本地失败")
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        ExcelToSQLite.Builder(getContext()).setDataBasePath(str).setExcelPath(file.getPath())
            .start(object : ImportListener {
                // from class: com.kkkcut.e20j.ui.fragment.setting.OtherSettingFragment.9
                // com.kkkcut.e20j.ui.fragment.setting.ExcelToSQLite.ImportListener
                override fun onStart() {
                    Log.d(TAG, "onStart() called")
                    val otherSettingFragment: OtherSettingFragment = this@OtherSettingFragment
                    otherSettingFragment.showLoadingDialog(otherSettingFragment.getString(R.string.waitting))
                }

                // com.kkkcut.e20j.ui.fragment.setting.ExcelToSQLite.ImportListener
                override fun onCompleted(str2: String?) {
                    Log.d(TAG, "onCompleted() called with: result = [" + str2 + "]")
                    this@OtherSettingFragment.dismissLoadingDialog()
                    ToastUtil.showToast(R.string.finish)
                }

                // com.kkkcut.e20j.ui.fragment.setting.ExcelToSQLite.ImportListener
                override fun onError(exc: Exception) {
                    Log.d(TAG, "onError() called with: e = [" + exc + "]")
                    this@OtherSettingFragment.dismissLoadingDialog()
                    ToastUtil.showToast(R.string.error_occurred)
                }
            })
    }

    fun saveUSbFileToLocal(usbFile: UsbFile?, str: String?): Boolean {
        try {
            val usbFileInputStream: UsbFileInputStream = UsbFileInputStream((usbFile)!!)
            val fileOutputStream: FileOutputStream = FileOutputStream(str)
            val bArr: ByteArray = ByteArray(1024)
            while (true) {
                val read: Int = usbFileInputStream.read(bArr)
                if (read != -1) {
                    fileOutputStream.write(bArr, 0, read)
                } else {
                    fileOutputStream.flush()
                    usbFileInputStream.close()
                    fileOutputStream.close()
                    return true
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }

    // com.kkkcut.e20j.base.BaseFFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment, androidx.fragment.app.Fragment
    override fun onDestroyView() {
        super.onDestroyView()
        getContext()!!.unregisterReceiver(this.usbReceiver)
    }

    fun onLongClick(): Boolean {
        showEditDialog()
        return true
    }

    companion object {
        private val ACTION_USB_PERMISSION: String = "com.kkkcut.e20.USB_PERMISSION"
        private val EXPORT: Int = 1
        private val IMPORT: Int = 0
        val TAG: String = "OtherSettingFragment"
        fun newInstance(): OtherSettingFragment {
            val bundle: Bundle = Bundle()
            val otherSettingFragment: OtherSettingFragment = OtherSettingFragment()
            otherSettingFragment.setArguments(bundle)
            return otherSettingFragment
        }
    }
}
