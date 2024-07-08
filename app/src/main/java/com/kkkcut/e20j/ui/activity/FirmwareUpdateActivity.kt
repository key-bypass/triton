package com.kkkcut.e20j.ui.activity

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.ProgressBar
import com.cutting.machine.Command
import com.cutting.machine.OperateType
import com.cutting.machine.communication.OperationManager
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter
import com.kkkcut.e20j.base.BaseFActivity
import com.kkkcut.e20j.us.R
import com.kkkcut.e20j.us.databinding.ActivityFirmwareUpdateBinding
import com.kkkcut.e20j.utils.AssetVersionUtil
import java.io.IOException
import java.io.InputStream

/* loaded from: classes.dex */
class FirmwareUpdateActivity : BaseFActivity() {
    var data: ArrayList<ByteArray> = ArrayList()
    private var index: Int = 0

    var binding: ActivityFirmwareUpdateBinding? = null

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        this.binding = ActivityFirmwareUpdateBinding.inflate(layoutInflater)
        setContentView(binding!!.getRoot())
    }

    override fun getContentViewLayoutID(): Int {
        return R.layout.activity_firmware_update
    }

    override fun onBackPressedSupport() {
    }

    override fun initViewsAndEvents() {
        binding!!.tvCurrentVersion.text = intent.getStringExtra(CURRENT_VERSION)
        binding!!.tvNewVersion.text = intent.getStringExtra(NEW_VERSION)
        object : Thread() {
            override fun run() {
                try {
                    val firmwareDir: String = AssetVersionUtil.getFirmwareDir()
                    val str: String =
                        assets.list(firmwareDir)!![0]
                    val open: InputStream = assets.open("$firmwareDir/$str")
                    val bArr = ByteArray(1024)
                    while (true) {
                        val read: Int = open.read(bArr)
                        if (read == -1) {
                            break
                        }
                        data.add(bArr.copyOf(read))
                    }
                    val result = data[data.size - 1]
                    if (result.size == 1024) {
                        data.add(ByteArray(0))
                    }
                    OperationManager.getInstance()
                        .sendOrder(Command.readyUpgradeFirmware(), OperateType.FIRMWARE_UPDATE)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }.start()
    }

    fun setProgress(f: Float) {
        var f: Float = f
        if (f > 100.0f) {
            f = 100.0f
        }
        val progressBar: ProgressBar = binding!!.progress
        progressBar.progress = f.toInt()
        binding!!.tvProgress.text = String.format("%.1f%%", f)
    }

    override fun onEventComing(eventCenter: EventCenter<*>) {
        val eventCode: Int = eventCenter.eventCode
        if (eventCode == 16) {
            setProgress((this.index * 100.0f) / data.size)
            if (this.index > data.size) {
                showAlert(R.string.firmware_upgrade_successful)
                return
            }
            if (this.index == 0) {
                OperationManager.getInstance()
                    .sendOrder("U".toByteArray(), OperateType.FIRMWARE_UPDATE)
            } else {
                OperationManager.getInstance().sendOrder(
                    Command.upgradeFirmware(
                        this.data, this.index - 1
                    ), OperateType.FIRMWARE_UPDATE
                )
            }
            index++
            return
        }
        if (eventCode == 33 && this.index <= data.size) {
            showAlert(R.string.firmware_upgrade_failed)
        }
    }

    private fun showAlert(i: Int) {
        AlertDialog.Builder(this).setCancelable(false).setMessage(i)
            .setPositiveButton(R.string.ok, object : DialogInterface.OnClickListener {
                // from class: com.kkkcut.e20j.ui.activity.FirmwareUpdateActivity.2
                // android.content.DialogInterface.OnClickListener
                override fun onClick(dialogInterface: DialogInterface, i2: Int) {
                    OperationManager.getInstance()
                        .sendOrder(Command.QueryFirmwareVersion(), OperateType.READ_FIRMWARE)
                    this@FirmwareUpdateActivity.finish()
                }
            }).show()
    }

    companion object {
        private val CURRENT_VERSION: String = "currentVersion"
        private val NEW_VERSION: String = "newVersion"
        fun start(context: Context, str: String?, str2: String?) {
            val intent = Intent(context, FirmwareUpdateActivity::class.java as Class<*>?)
            intent.putExtra(CURRENT_VERSION, str)
            intent.putExtra(NEW_VERSION, str2)
            context.startActivity(intent)
        }
    }
}
