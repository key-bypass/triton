package com.kkkcut.e20j.ui.fragment.engraving

import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.kkkcut.e20j.adapter.UsbImgSelectAdapter
import com.kkkcut.e20j.androidquick.tool.FileUtil
import com.kkkcut.e20j.androidquick.tool.ToastUtil
import com.kkkcut.e20j.ui.activity.FrameActivity
import com.kkkcut.e20j.ui.fragment.BaseBackFragment
import com.kkkcut.e20j.us.R
import com.kkkcut.e20j.us.databinding.FragmentImageSelectBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.File
import java.util.concurrent.Callable

/* loaded from: classes.dex */
class UsbImageSelectFragment() : BaseBackFragment(), BaseQuickAdapter.OnItemChildClickListener {

    lateinit private var binding: FragmentImageSelectBinding
    private var selectFile: File? = null
    var usbImgSelectAdapter: UsbImgSelectAdapter? = null

    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_image_select
    }

    override fun onCreateView(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?,
        bundle: Bundle?
    ): View {
        super.onCreateView(layoutInflater, viewGroup, bundle)
        binding = FragmentImageSelectBinding.inflate(layoutInflater)
        return binding.getRoot()
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
        val gridLayoutManager: GridLayoutManager = GridLayoutManager(getContext(), 2)
        gridLayoutManager.setOrientation(1)
        binding.rvPics!!.setLayoutManager(gridLayoutManager)
        val usbImgSelectAdapter: UsbImgSelectAdapter = UsbImgSelectAdapter()
        this.usbImgSelectAdapter = usbImgSelectAdapter
        usbImgSelectAdapter.setOnItemChildClickListener(this)
        binding.rvPics!!.setAdapter(this.usbImgSelectAdapter)
        getPicsFromLocal()
    }


    private fun getPicsFromLocal() {
        showLoadingDialog(getString(R.string.waitting))
        val observable = Observable.fromCallable {
            getImageFile(
                File(
                    Environment.getExternalStorageDirectory(),
                    LOGO_IMAGE
                )
            )!!
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({ list ->
            usbImgSelectAdapter!!.setNewData(list)
            this@UsbImageSelectFragment.dismissLoadingDialog()
        }, {
            this@UsbImageSelectFragment.dismissLoadingDialog()
        })
                this.addDisposable(observable)

    }

    private fun getPicsFromUsb(file: File) {
        showLoadingDialog(getString(R.string.waitting))

        addDisposable(
            Observable.fromCallable { getImageFile(file)!! }.subscribeOn(Schedulers.io()).map { list: List<File> ->
                if (list == null || list.isEmpty()) {
                    throw Exception(this@UsbImageSelectFragment.getString(R.string.no_new_pictures_found))
                }
                for (file2 in list) {
                    val file3 =
                        File(Environment.getExternalStorageDirectory(), LOGO_IMAGE)
                    if (!file3.exists()) {
                        file3.mkdir()
                    }
                    FileUtil.copyFile(file2, File(file3, file2.getName()))
                }
                list
            }.map {
                getImageFile(
                    File(
                        Environment.getExternalStorageDirectory(), LOGO_IMAGE
                    )
                )!!
            }.observeOn(AndroidSchedulers.mainThread()).subscribe(
                { list: List<File> ->
                    this@UsbImageSelectFragment.dismissLoadingDialog()
                    usbImgSelectAdapter!!.setNewData(list)
                }, { th: Throwable ->
                    this@UsbImageSelectFragment.dismissLoadingDialog()
                    ToastUtil.showToast(th.message)
                }, { dismissLoadingDialog() }
            )
        )
    }

    // com.chad.library.adapter.base.BaseQuickAdapter.OnItemChildClickListener
    override fun onItemChildClick(baseQuickAdapter: BaseQuickAdapter<*, *>, view: View, i: Int) {
        val id: Int = view.getId()
        if (id == R.id.iv_delete) {
            val file: File = baseQuickAdapter.getData().get(i) as File
            this.selectFile = file
            file.delete()
            usbImgSelectAdapter!!.remove(i)
            return
        }
        if (id != R.id.ll_container) {
            return
        }
        val file2: File = baseQuickAdapter.getData().get(i) as File
        this.selectFile = file2
        usbImgSelectAdapter!!.addFrame(file2)
    }

    fun onViewClicked(view: View) {
        val frameActivity: FrameActivity? = getActivity() as FrameActivity?
        val id: Int = view.getId()
        if (id == R.id.bt_cancle) {
            checkNotNull(frameActivity)
            frameActivity.onBack()
            return
        }
        if (id == R.id.bt_confirm) {
            if (this.selectFile != null) {
                val bundle: Bundle = Bundle()
                bundle.putSerializable("imageFile", this.selectFile)
                setFragmentResult(1, bundle)
                frameActivity!!.onBack()
                return
            }
            ToastUtil.showToast(R.string.please_select_a_picture)
            return
        }
        if (id != R.id.tv_load_pics_from_usb) {
            return
        }
        val usbPath: File? = usbPath
        if (usbPath == null) {
            Toast.makeText(this._mActivity, getString(R.string.u_disk_is_not_detected), 0).show()
            return
        }
        val file: File = File(usbPath, LOGO_IMAGE)
        if (!file.exists()) {
            Toast.makeText(this._mActivity, getString(R.string.directory_not_found), 0).show()
        } else {
            getPicsFromUsb(file)
        }
    }

    private val usbPath: File?
        get() {
            for (file: File in File("/storage").listFiles()) {
                if (file.canRead() && !(file.getName() == Environment.getExternalStorageDirectory()
                        .getName())
                ) {
                    return file
                }
            }
            return null
        }

    // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    override fun setTitleStr(): String? {
        return getString(R.string.picture_selection)
    }

    companion object {
        private val LOGO_IMAGE: String = "LogoImage"

        fun newInstance(): UsbImageSelectFragment {
            val bundle: Bundle = Bundle()
            val usbImageSelectFragment: UsbImageSelectFragment = UsbImageSelectFragment()
            usbImageSelectFragment.setArguments(bundle)
            return usbImageSelectFragment
        }

        /* JADX INFO: Access modifiers changed from: private */
        fun getImageFile(file: File): List<File>? {
            val arrayList = ArrayList<File>()
            val listFiles: Array<out File> = file.listFiles() ?: return null
            for (file2: File in listFiles) {
                if (file2.isFile() && isImageFile(file2.path)) {
                    arrayList.add(file2)
                } else if (file2.isDirectory()) {
                    arrayList.addAll((getImageFile(file2))!!)
                }
            }
            return arrayList
        }

        fun isImageFile(str: String?): Boolean {
            val options: BitmapFactory.Options = BitmapFactory.Options()
            options.inJustDecodeBounds = true
            BitmapFactory.decodeFile(str, options)
            return options.outWidth != -1
        }
    }
}
