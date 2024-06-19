package com.kkkcut.e20j.ui.fragment.engraving;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.ViewCompat;
import butterknife.BindView;
import butterknife.OnClick;
import com.kkkcut.e20j.AlignTextView;
import com.kkkcut.e20j.DbBean.userDB.KeyMarkingChild;
import com.kkkcut.e20j.DbBean.userDB.KeyMarkingTemplate;
import com.kkkcut.e20j.SpKeys;
import com.kkkcut.e20j.androidquick.tool.SPUtils;
import com.kkkcut.e20j.androidquick.tool.ToastUtil;
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter;
import com.kkkcut.e20j.customView.KeyMarkingE9Layout;
import com.kkkcut.e20j.customView.OnDragTouchListener;
import com.kkkcut.e20j.dao.UserDataDaoManager;
import com.kkkcut.e20j.ui.dialog.EditDialog;
import com.kkkcut.e20j.ui.dialog.RemindDialog;
import com.kkkcut.e20j.ui.fragment.BaseBackFragment;
import com.kkkcut.e20j.ui.fragment.engraving.EngraveE9PathGen;
import com.kkkcut.e20j.us.R;
import com.kkkcut.e20j.utils.BitmapUtil;
import com.liying.core.Command;
import com.liying.core.CuttingMachine;
import com.liying.core.KeyAlignInfo;
import com.liying.core.OperateType;
import com.liying.core.bean.StepBean;
import com.liying.core.clamp.Clamp;
import com.liying.core.clamp.ClampManager;
import com.liying.core.clamp.MachineData;
import com.liying.core.communication.OperationManager;
import com.liying.core.error.ErrorBean;
import com.liying.core.operation.cut.DataParam;
import com.liying.core.utils.AssetsJsonUtils;
import com.liying.core.utils.DecoderPositionUtils;
import com.liying.core.utils.UnitConvertUtil;
import io.reactivex.Observable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class KeyMarkingE9Fragment extends BaseBackFragment {
    private static final int FRAME_HEITHT = 300;
    private static final int FRAME_WIDTH = 300;
    private static final int LEFT_SPACE = 0;
    private static final int SELECT_IMAGE = 1;
    private static final int SELECT_TEMPLATE = 2;
    private static final float letterSpace = 0.05f;

    @BindView(R.id.cb_auto_center)
    CheckBox cbAutoCenter;
    private View currentView;
    private boolean decode;
    private Bitmap engraveingBitmap;

    @BindView(R.id.fl_container)
    FrameLayout flContainer;
    private Typeface fontFace;

    @BindView(R.id.kmfl)
    KeyMarkingE9Layout kmfl;

    @BindView(R.id.rb_high_speed)
    RadioButton rbHighSpeed;

    @BindView(R.id.rb_low_speed)
    RadioButton rbLowSpeed;

    @BindView(R.id.rb_middle_speed)
    RadioButton rbMiddleSpeed;
    private int rightSpace;

    @BindView(R.id.tv_depth_value)
    TextView tvDepthValue;
    private int cutDepth = 10;
    private Point origin = new Point();
    DataParam dataParam = new DataParam();
    private OnDragTouchListener.OnDraggableClickListener onDraggableClickListener = new OnDragTouchListener.OnDraggableClickListener() { // from class: com.kkkcut.e20j.ui.fragment.engraving.KeyMarkingE9Fragment.1
        @Override // com.kkkcut.e20j.customView.OnDragTouchListener.OnDraggableClickListener
        public void onDragged(View view, int i, int i2) {
        }

        @Override // com.kkkcut.e20j.customView.OnDragTouchListener.OnDraggableClickListener
        public void onClick(View view) {
            KeyMarkingE9Fragment.this.changeCurrentView(view);
        }
    };

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_key_marking_e9;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changeCurrentView(View view) {
        if (view instanceof TextView) {
            changeCurrentText((TextView) view);
        } else {
            changeCurrentImage((ImageView) view);
        }
    }

    private void changeCurrentText(TextView textView) {
        View view = this.currentView;
        if (view != null && (view instanceof TextView)) {
            ((TextView) view).setTextColor(ViewCompat.MEASURED_STATE_MASK);
        }
        this.currentView = textView;
        textView.setTextColor(SupportMenu.CATEGORY_MASK);
    }

    private void changeCurrentImage(ImageView imageView) {
        View view = this.currentView;
        if (view != null && (view instanceof TextView)) {
            ((TextView) view).setTextColor(ViewCompat.MEASURED_STATE_MASK);
        }
        this.currentView = imageView;
    }

    public static KeyMarkingE9Fragment newInstance() {
        Bundle bundle = new Bundle();
        KeyMarkingE9Fragment keyMarkingE9Fragment = new KeyMarkingE9Fragment();
        keyMarkingE9Fragment.setArguments(bundle);
        return keyMarkingE9Fragment;
    }

    @Override // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    public String setTitleStr() {
        return getString(R.string.key_marking);
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        this.fontFace = ResourcesCompat.getFont(getContext(), R.font.thin);
        int i = SPUtils.getInt(SpKeys.ENGRAVING_SPEED, 15);
        if (i == 1) {
            this.rbLowSpeed.setChecked(true);
        } else if (i == 15) {
            this.rbMiddleSpeed.setChecked(true);
        } else if (i == 25) {
            this.rbHighSpeed.setChecked(true);
        }
        initParam();
    }

    private void initParam() {
        this.dataParam.setClamp(Clamp.E9S5);
        this.dataParam.setDecoderSize(100);
    }

    @OnClick({R.id.iv_up, R.id.iv_down, R.id.iv_left, R.id.iv_right, R.id.bt_cut, R.id.font_size_add, R.id.font_size_reduce, R.id.tv_select_template, R.id.iv_depth_reduce, R.id.iv_depth_add, R.id.tv_add_text, R.id.tv_edit_text, R.id.tv_delete, R.id.tv_add_pic, R.id.tv_save, R.id.rb_low_speed, R.id.rb_middle_speed, R.id.rb_high_speed, R.id.bt_decode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_cut /* 2131361921 */:
                if (ClampManager.getInstance().checkHasCalibrated(Clamp.E9S5) && this.flContainer.getChildCount() != 0) {
                    this.dataParam.setCutSpeed(SPUtils.getInt(SpKeys.ENGRAVING_SPEED, 15));
                    this.dataParam.setCutDepth(this.cutDepth);
                    checkDecoderStateOrLocation();
                    return;
                }
                return;
            case R.id.bt_decode /* 2131361922 */:
                if (ClampManager.getInstance().checkHasCalibrated(Clamp.E9S5)) {
                    this.decode = true;
                    checkDecoderStateOrLocation();
                    return;
                }
                return;
            case R.id.font_size_add /* 2131362224 */:
                View view2 = this.currentView;
                if (view2 != null) {
                    if (view2 instanceof TextView) {
                        TextView textView = (TextView) view2;
                        textView.setTextSize(0, textView.getTextSize() + 1.0f);
                        return;
                    }
                    ImageView imageView = (ImageView) view2;
                    int width = imageView.getWidth();
                    int height = imageView.getHeight();
                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) imageView.getLayoutParams();
                    int i = layoutParams.leftMargin + (width / 2);
                    int i2 = layoutParams.topMargin + (height / 2);
                    int i3 = (int) (width * 1.1d);
                    layoutParams.width = i3;
                    int i4 = (int) (height * 1.1d);
                    layoutParams.height = i4;
                    if (i3 > 300 || i4 > 300) {
                        return;
                    }
                    int i5 = i - (layoutParams.width / 2);
                    if (i5 < 0) {
                        i5 = 0;
                    }
                    int i6 = i2 - (layoutParams.height / 2);
                    layoutParams.setMargins(i5, i6 >= 0 ? i6 : 0, i + (layoutParams.width / 2), i2 + (layoutParams.height / 2));
                    imageView.setLayoutParams(layoutParams);
                    return;
                }
                return;
            case R.id.font_size_reduce /* 2131362225 */:
                View view3 = this.currentView;
                if (view3 != null) {
                    if (view3 instanceof TextView) {
                        TextView textView2 = (TextView) view3;
                        float textSize = textView2.getTextSize();
                        if (textSize > 24.0f) {
                            textView2.setTextSize(0, textSize - 1.0f);
                            return;
                        }
                        return;
                    }
                    ImageView imageView2 = (ImageView) view3;
                    int width2 = imageView2.getWidth();
                    int height2 = imageView2.getHeight();
                    FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) imageView2.getLayoutParams();
                    int i7 = layoutParams2.leftMargin + (width2 / 2);
                    int i8 = layoutParams2.topMargin + (height2 / 2);
                    layoutParams2.width = (int) (width2 * 0.9d);
                    layoutParams2.height = (int) (height2 * 0.9d);
                    layoutParams2.setMargins(i7 - (layoutParams2.width / 2), i8 - (layoutParams2.height / 2), i7 + (layoutParams2.width / 2), i8 + (layoutParams2.height / 2));
                    imageView2.setLayoutParams(layoutParams2);
                    return;
                }
                return;
            case R.id.iv_depth_add /* 2131362293 */:
                int i9 = this.cutDepth;
                if (i9 < 15) {
                    this.cutDepth = i9 + 1;
                    this.tvDepthValue.setText((this.cutDepth / 100.0f) + "mm");
                    return;
                }
                return;
            case R.id.iv_depth_reduce /* 2131362295 */:
                int i10 = this.cutDepth;
                if (i10 > 1) {
                    this.cutDepth = i10 - 1;
                    this.tvDepthValue.setText((this.cutDepth / 100.0f) + "mm");
                    return;
                }
                return;
            case R.id.iv_down /* 2131362297 */:
                View view4 = this.currentView;
                if (view4 != null) {
                    int bottom = view4.getBottom() + 5;
                    Log.i(TAG, "bottom: " + bottom);
                    if (bottom > this.flContainer.getMeasuredHeight() - this.flContainer.getPaddingBottom()) {
                        return;
                    }
                    Log.i(TAG, "getWidth: " + this.currentView.getWidth() + "-getHeight: " + this.currentView.getHeight());
                    View view5 = this.currentView;
                    view5.layout(view5.getLeft(), this.currentView.getTop() + 5, this.currentView.getRight(), bottom);
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.currentView.getLayoutParams();
                    marginLayoutParams.setMargins(this.currentView.getLeft(), this.currentView.getTop(), 0, 0);
                    this.currentView.setLayoutParams(marginLayoutParams);
                    return;
                }
                if (this.flContainer.getChildCount() > 0) {
                    changeCurrentView(this.flContainer.getChildAt(0));
                    return;
                }
                return;
            case R.id.iv_left /* 2131362312 */:
                View view6 = this.currentView;
                if (view6 != null) {
                    int left = view6.getLeft() - 5;
                    if (left < 0) {
                        return;
                    }
                    View view7 = this.currentView;
                    view7.layout(left, view7.getTop(), this.currentView.getRight() - 5, this.currentView.getBottom());
                    ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) this.currentView.getLayoutParams();
                    marginLayoutParams2.setMargins(this.currentView.getLeft(), this.currentView.getTop(), 0, 0);
                    this.currentView.setLayoutParams(marginLayoutParams2);
                    return;
                }
                if (this.flContainer.getChildCount() > 0) {
                    changeCurrentView(this.flContainer.getChildAt(0));
                    return;
                }
                return;
            case R.id.iv_right /* 2131362329 */:
                View view8 = this.currentView;
                if (view8 != null) {
                    int right = view8.getRight() + 5;
                    if (right > (this.flContainer.getMeasuredWidth() - this.flContainer.getPaddingRight()) - 0) {
                        return;
                    }
                    View view9 = this.currentView;
                    view9.layout(view9.getLeft() + 5, this.currentView.getTop(), right, this.currentView.getBottom());
                    ViewGroup.MarginLayoutParams marginLayoutParams3 = (ViewGroup.MarginLayoutParams) this.currentView.getLayoutParams();
                    marginLayoutParams3.setMargins(this.currentView.getLeft(), this.currentView.getTop(), 0, 0);
                    this.currentView.setLayoutParams(marginLayoutParams3);
                    return;
                }
                if (this.flContainer.getChildCount() > 0) {
                    changeCurrentView(this.flContainer.getChildAt(0));
                    return;
                }
                return;
            case R.id.iv_up /* 2131362357 */:
                View view10 = this.currentView;
                if (view10 != null) {
                    int top = view10.getTop() - 5;
                    if (top < 0) {
                        return;
                    }
                    View view11 = this.currentView;
                    view11.layout(view11.getLeft(), top, this.currentView.getRight(), this.currentView.getBottom() - 5);
                    ViewGroup.MarginLayoutParams marginLayoutParams4 = (ViewGroup.MarginLayoutParams) this.currentView.getLayoutParams();
                    marginLayoutParams4.setMargins(this.currentView.getLeft(), this.currentView.getTop(), 0, 0);
                    this.currentView.setLayoutParams(marginLayoutParams4);
                    return;
                }
                if (this.flContainer.getChildCount() > 0) {
                    changeCurrentView(this.flContainer.getChildAt(0));
                    return;
                }
                return;
            case R.id.rb_high_speed /* 2131362616 */:
                SPUtils.put(SpKeys.ENGRAVING_SPEED, 25);
                return;
            case R.id.rb_low_speed /* 2131362628 */:
                SPUtils.put(SpKeys.ENGRAVING_SPEED, 1);
                return;
            case R.id.rb_middle_speed /* 2131362631 */:
                SPUtils.put(SpKeys.ENGRAVING_SPEED, 15);
                return;
            case R.id.tv_add_pic /* 2131362881 */:
                startForResult(UsbImageSelectFragment.newInstance(), 1);
                return;
            case R.id.tv_add_text /* 2131362882 */:
                showAddTextDialog();
                return;
            case R.id.tv_delete /* 2131362924 */:
                View view12 = this.currentView;
                if (view12 != null) {
                    this.flContainer.removeView(view12);
                    if (this.flContainer.getChildCount() > 0) {
                        changeCurrentView(this.flContainer.getChildAt(0));
                        return;
                    } else {
                        this.currentView = null;
                        return;
                    }
                }
                return;
            case R.id.tv_edit_text /* 2131362933 */:
                if (this.currentView != null) {
                    showEditDialog();
                    return;
                }
                return;
            case R.id.tv_save /* 2131362989 */:
                if (this.flContainer.getChildCount() == 0) {
                    return;
                }
                showSaveTemplateDialog();
                return;
            case R.id.tv_select_template /* 2131362992 */:
                startForResult(TemplateSelectFragment.newInstance(), 2);
                return;
            default:
                return;
        }
    }

    private void checkDecoderStateOrLocation() {
        if (SPUtils.getBoolean(SpKeys.Not_detect_decoder_position)) {
            decoderLocation();
        } else {
            checkDecoderState(OperateType.CHECK_DECODER_STATE_BEFORE_DECODER_LOCATION);
        }
    }

    private void decoderLocation() {
        showLoadingDialog(getString(R.string.waitting), true);
        OperationManager.getInstance().start(this.dataParam, AssetsJsonUtils.getCommonSteps(getContext(), "keyblank/decoder_e9/S5.json"), OperateType.ENGRAVE_CUT_LOCATION);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkDecoderState(OperateType operateType) {
        OperationManager.getInstance().sendOrder(Command.queryDecoderPosition(), operateType);
    }

    private void showSaveTemplateDialog() {
        EditDialog editDialog = new EditDialog(getContext());
        editDialog.setEditTextContent("");
        editDialog.setTip(getString(R.string.enter_remarks));
        editDialog.setDialogBtnCallback(new EditDialog.DialogInputFinishCallBack() { // from class: com.kkkcut.e20j.ui.fragment.engraving.KeyMarkingE9Fragment$$ExternalSyntheticLambda1
            @Override // com.kkkcut.e20j.ui.dialog.EditDialog.DialogInputFinishCallBack
            public final void onDialogButClick(String str) {
                KeyMarkingE9Fragment.this.m53xae3c4e4d(str);
            }
        });
        editDialog.setContentNullable(true);
        editDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: saveTemplate, reason: merged with bridge method [inline-methods] */
    public void m53xae3c4e4d(String str) {
        KeyMarkingTemplate keyMarkingTemplate = new KeyMarkingTemplate();
        keyMarkingTemplate.setWidth(this.flContainer.getWidth());
        keyMarkingTemplate.setHeight(this.flContainer.getHeight());
        keyMarkingTemplate.setMarginLeft(this.flContainer.getLeft() - 45);
        keyMarkingTemplate.setMarginTop(this.flContainer.getTop() - 45);
        keyMarkingTemplate.setDescription(str);
        keyMarkingTemplate.setTime(new Date().getTime());
        UserDataDaoManager.getInstance(getContext()).saveKeyMarkingTemplate(keyMarkingTemplate);
        for (int i = 0; i < this.flContainer.getChildCount(); i++) {
            View childAt = this.flContainer.getChildAt(i);
            KeyMarkingChild keyMarkingChild = new KeyMarkingChild();
            if (childAt instanceof TextView) {
                TextView textView = (TextView) childAt;
                String trim = textView.getText().toString().trim();
                if (!TextUtils.isEmpty(trim)) {
                    float textSize = textView.getTextSize();
                    keyMarkingChild.setType(1);
                    keyMarkingChild.setFontSize(textSize);
                    keyMarkingChild.setText(trim);
                    keyMarkingChild.setMarginLeft(textView.getLeft());
                    keyMarkingChild.setMarginTop(textView.getTop());
                    keyMarkingChild.setParentId(keyMarkingTemplate.getId());
                    UserDataDaoManager.getInstance(getContext()).saveKeyMarkingTemplateChild(keyMarkingChild);
                }
            } else {
                ImageView imageView = (ImageView) childAt;
                keyMarkingChild.setType(2);
                keyMarkingChild.setImageByte(BitmapUtil.bitmapToByte(((BitmapDrawable) imageView.getDrawable()).getBitmap()));
                keyMarkingChild.setWidth(imageView.getWidth());
                keyMarkingChild.setHeight(imageView.getHeight());
                keyMarkingChild.setMarginLeft(imageView.getLeft());
                keyMarkingChild.setMarginTop(imageView.getTop());
                keyMarkingChild.setParentId(keyMarkingTemplate.getId());
                UserDataDaoManager.getInstance(getContext()).saveKeyMarkingTemplateChild(keyMarkingChild);
            }
        }
        ToastUtil.showToast(R.string.saved_successfully);
    }

    private ImageView getImageViewFromFile(File file) {
        ImageView imageView = new ImageView(getContext());
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getPath(), options);
        int i = options.outWidth;
        int i2 = options.outHeight;
        float f = i;
        float f2 = (f * 1.0f) / 300.0f;
        float f3 = i2;
        float f4 = (f3 * 1.0f) / 300.0f;
        if (f2 <= 1.0f && f4 <= 1.0f) {
            options.inJustDecodeBounds = false;
            Bitmap decodeFile = BitmapFactory.decodeFile(file.getPath(), options);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(i, i2);
            layoutParams.setMargins((300 - i) / 2, (300 - i2) / 2, (i + 300) / 2, (i2 + 300) / 2);
            imageView.setLayoutParams(layoutParams);
            imageView.setImageBitmap(decodeFile);
        } else {
            options.inJustDecodeBounds = false;
            float max = Math.max(f2, f4);
            options.inSampleSize = (int) max;
            Bitmap scaleBitmap = BitmapUtil.scaleBitmap(BitmapFactory.decodeFile(file.getPath(), options), max);
            int i3 = (int) (f / max);
            int i4 = (int) (f3 / max);
            FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(i3, i4);
            layoutParams2.setMargins((300 - i3) / 2, (300 - i4) / 2, (i3 + 300) / 2, (i4 + 300) / 2);
            imageView.setLayoutParams(layoutParams2);
            imageView.setImageBitmap(scaleBitmap);
        }
        return imageView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void detectCutterHigh() {
        addDisposable(Observable.fromCallable(new Callable<Rect>() { // from class: com.kkkcut.e20j.ui.fragment.engraving.KeyMarkingE9Fragment.5
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Rect call() throws Exception {
                return KeyMarkingE9Fragment.this.getMarkingRectRealScreen();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).map(new Function<Rect, Bitmap>() { // from class: com.kkkcut.e20j.ui.fragment.engraving.KeyMarkingE9Fragment.4
            @Override // io.reactivex.functions.Function
            public Bitmap apply(Rect rect) throws Exception {
                return KeyMarkingE9Fragment.this.getScreenBitmap(rect);
            }
        }).observeOn(Schedulers.io()).map(new Function<Bitmap, List<StepBean>>() { // from class: com.kkkcut.e20j.ui.fragment.engraving.KeyMarkingE9Fragment.3
            @Override // io.reactivex.functions.Function
            public List<StepBean> apply(Bitmap bitmap) throws Exception {
                EngraveE9PathGen.EngraveParam engraveParam = KeyMarkingE9Fragment.this.getEngraveParam();
                KeyMarkingE9Fragment.this.engraveingBitmap = bitmap;
                return EngraveE9PathGen.detectCutterHigh(bitmap, engraveParam);
            }
        }).subscribe(new Consumer<List<StepBean>>() { // from class: com.kkkcut.e20j.ui.fragment.engraving.KeyMarkingE9Fragment.2
            @Override // io.reactivex.functions.Consumer
            public void accept(List<StepBean> list) throws Exception {
                OperationManager.getInstance().start(KeyMarkingE9Fragment.this.dataParam, list, OperateType.ENGRAVE_CUT_CUTTER_HIGH);
            }
        }));
    }

    private void startEngraving() {
        if (this.engraveingBitmap == null) {
            ToastUtil.showToast("no bitmap");
            dismissLoadingDialog();
        } else {
            addDisposable(Observable.fromCallable(new Callable<List<StepBean>>() { // from class: com.kkkcut.e20j.ui.fragment.engraving.KeyMarkingE9Fragment.7
                @Override // java.util.concurrent.Callable
                public List<StepBean> call() throws Exception {
                    return EngraveE9PathGen.bitmapToPath(KeyMarkingE9Fragment.this.engraveingBitmap, KeyMarkingE9Fragment.this.getEngraveParam());
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).subscribe(new Consumer<List<StepBean>>() { // from class: com.kkkcut.e20j.ui.fragment.engraving.KeyMarkingE9Fragment.6
                @Override // io.reactivex.functions.Consumer
                public void accept(List<StepBean> list) throws Exception {
                    OperationManager.getInstance().start(KeyMarkingE9Fragment.this.dataParam, list, OperateType.ENGRAVE_CUT_EXECUTE);
                }
            }));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public EngraveE9PathGen.EngraveParam getEngraveParam() {
        KeyAlignInfo keyAlignInfo = OperationManager.getInstance().getKeyAlignInfo();
        int tip = keyAlignInfo.getTip();
        int centerY = ClampManager.getInstance().getE9S5().getCenterY() - (UnitConvertUtil.cmm2StepY(this.flContainer.getWidth() * 10) / 2);
        int i = this.origin.x * 10;
        int i2 = this.origin.y * 10;
        int i3 = ClampManager.getInstance().getDC().getxDistance();
        int i4 = ClampManager.getInstance().getDC().getyDistance();
        int keyFace = keyAlignInfo.getKeyFace();
        String trim = this.tvDepthValue.getText().toString().trim();
        return new EngraveE9PathGen.EngraveParam(tip, centerY, i, i2, i3, i4, keyFace, UnitConvertUtil.cmm2StepZ(!TextUtils.isEmpty(trim) ? Math.round(Float.parseFloat(trim.replace("mm", "")) * 100.0f) : 5), SPUtils.getInt(SpKeys.ENGRAVING_SPEED, 15));
    }

    public static String substring(String str, int i, int i2) {
        if (i > str.length()) {
            return null;
        }
        if (i2 > str.length()) {
            return str.substring(i, str.length());
        }
        return str.substring(i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bitmap getScreenBitmap(Rect rect) {
        this.kmfl.setMarkLineVisible(false);
        View decorView = getActivity().getWindow().getDecorView();
        decorView.setDrawingCacheEnabled(true);
        decorView.buildDrawingCache();
        Bitmap createBitmap = Bitmap.createBitmap(decorView.getDrawingCache(), rect.left, rect.top, rect.width(), rect.height());
        decorView.setDrawingCacheEnabled(false);
        this.kmfl.setMarkLineVisible(true);
        return createBitmap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Rect getMarkingRectRealScreen() {
        Rect rect = new Rect(Integer.MAX_VALUE, Integer.MAX_VALUE, 0, 0);
        int childCount = this.flContainer.getChildCount();
        if (childCount == 0) {
            return null;
        }
        for (int i = 0; i < childCount; i++) {
            View childAt = this.flContainer.getChildAt(i);
            int[] iArr = new int[2];
            childAt.getLocationOnScreen(iArr);
            rect.left = Math.min(rect.left, iArr[0]);
            rect.top = Math.min(rect.top, iArr[1]);
            rect.right = Math.max(rect.right, iArr[0] + childAt.getWidth());
            rect.bottom = Math.max(rect.bottom, iArr[1] + childAt.getHeight());
        }
        int[] iArr2 = new int[2];
        this.flContainer.getLocationInWindow(iArr2);
        this.origin.x = Math.abs(rect.left - iArr2[0]);
        this.origin.y = Math.abs(rect.top - iArr2[1]);
        return rect;
    }

    private void showEditDialog() {
        View view = this.currentView;
        if (view == null || !(view instanceof TextView)) {
            return;
        }
        EditDialog editDialog = new EditDialog(getContext());
        editDialog.setDialogBtnCallback(new EditDialog.DialogInputFinishCallBack() { // from class: com.kkkcut.e20j.ui.fragment.engraving.KeyMarkingE9Fragment.8
            @Override // com.kkkcut.e20j.ui.dialog.EditDialog.DialogInputFinishCallBack
            public void onDialogButClick(String str) {
                ((TextView) KeyMarkingE9Fragment.this.currentView).setText(str);
            }
        });
        editDialog.setTip(getString(R.string.edit_text));
        editDialog.setEditTextContent(((TextView) this.currentView).getText().toString().trim());
        if (editDialog.isShowing()) {
            return;
        }
        editDialog.show();
    }

    private void showAddTextDialog() {
        EditDialog editDialog = new EditDialog(getContext());
        editDialog.setDialogBtnCallback(new EditDialog.DialogInputFinishCallBack() { // from class: com.kkkcut.e20j.ui.fragment.engraving.KeyMarkingE9Fragment$$ExternalSyntheticLambda0
            @Override // com.kkkcut.e20j.ui.dialog.EditDialog.DialogInputFinishCallBack
            public final void onDialogButClick(String str) {
                KeyMarkingE9Fragment.this.m52x7d5ccc69(str);
            }
        });
        editDialog.setEditTextContent("");
        editDialog.setTip(getString(R.string.add_text));
        if (editDialog.isShowing()) {
            return;
        }
        editDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: addText, reason: merged with bridge method [inline-methods] */
    public void m52x7d5ccc69(String str) {
        AlignTextView alignTextView = new AlignTextView(getContext());
        alignTextView.setElevation(1.0f);
        alignTextView.setIncludeFontPadding(false);
        alignTextView.setTextColor(SupportMenu.CATEGORY_MASK);
        alignTextView.setTextSize(0, 30.0f);
        alignTextView.setText(str);
        alignTextView.setLetterSpacing(letterSpace);
        alignTextView.setIncludeFontPadding(false);
        int width = this.flContainer.getWidth();
        int height = this.flContainer.getHeight();
        OnDragTouchListener onDragTouchListener = new OnDragTouchListener(width, height);
        onDragTouchListener.setOnDraggableClickListener(this.onDraggableClickListener);
        alignTextView.setOnTouchListener(onDragTouchListener);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(30.0f);
        textPaint.setLetterSpacing(letterSpace);
        String[] split = str.split("\n");
        String str2 = "";
        for (int i = 0; i < split.length; i++) {
            if (split[i].length() > str2.length()) {
                str2 = split[i];
            }
        }
        int measureText = (int) textPaint.measureText(str2);
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float f = fontMetrics.descent - fontMetrics.ascent;
        int i2 = 0;
        for (char c : str.toCharArray()) {
            if (c == '\n') {
                i2++;
            }
        }
        if (measureText < width) {
            int i3 = (width / 2) - (measureText / 2);
            float f2 = (i2 + 1) * f;
            if (f2 > height) {
                ToastUtil.showToast("Exceeded number of rows");
                return;
            }
            layoutParams.setMargins(i3, (int) ((height / 2) - (f2 / 2.0f)), 0, 0);
        } else {
            float ceil = f * (((int) Math.ceil((measureText * 1.0f) / width)) + i2);
            if (ceil > height) {
                ToastUtil.showToast("Exceeded number of rows");
                return;
            } else {
                layoutParams.setMargins(0, (int) ((height / 2) - (ceil / 2.0f)), 0, 0);
                layoutParams.width = width;
            }
        }
        this.flContainer.addView(alignTextView, layoutParams);
        changeCurrentText(alignTextView);
    }

    @Override // com.kkkcut.e20j.base.BaseFFragment, me.yokeyword.fragmentation.ISupportFragment
    public void onFragmentResult(int i, int i2, Bundle bundle) {
        File file;
        if (i == 1) {
            if (bundle == null || (file = (File) bundle.getSerializable("imageFile")) == null) {
                return;
            }
            ImageView imageViewFromFile = getImageViewFromFile(file);
            this.flContainer.addView(imageViewFromFile);
            OnDragTouchListener onDragTouchListener = new OnDragTouchListener(this.flContainer.getWidth(), this.flContainer.getHeight());
            onDragTouchListener.setOnDraggableClickListener(this.onDraggableClickListener);
            imageViewFromFile.setOnTouchListener(onDragTouchListener);
            this.currentView = imageViewFromFile;
            return;
        }
        if (i != 2 || bundle == null) {
            return;
        }
        KeyMarkingTemplate keyMarkingTemplate = (KeyMarkingTemplate) bundle.getSerializable("template");
        this.flContainer.removeAllViews();
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(keyMarkingTemplate.getWidth(), -1);
        layoutParams.setMargins(keyMarkingTemplate.getMarginLeft() + 45, keyMarkingTemplate.getMarginTop() + 45, 0, 10);
        this.flContainer.setLayoutParams(layoutParams);
        for (KeyMarkingChild keyMarkingChild : keyMarkingTemplate.getChildrenBeanList()) {
            if (keyMarkingChild.getType() == 1) {
                AlignTextView alignTextView = new AlignTextView(getContext());
                alignTextView.setElevation(1.0f);
                alignTextView.setTextSize(0, keyMarkingChild.getFontSize());
                alignTextView.setText(keyMarkingChild.getText());
                alignTextView.setIncludeFontPadding(false);
                alignTextView.setLetterSpacing(letterSpace);
                alignTextView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-2, -2);
                layoutParams2.setMargins(keyMarkingChild.getMarginLeft(), keyMarkingChild.getMarginTop(), 0, 0);
                this.flContainer.addView(alignTextView, layoutParams2);
                OnDragTouchListener onDragTouchListener2 = new OnDragTouchListener(this.flContainer.getWidth(), this.flContainer.getHeight());
                onDragTouchListener2.setOnDraggableClickListener(this.onDraggableClickListener);
                alignTextView.setOnTouchListener(onDragTouchListener2);
            } else {
                ImageView imageView = new ImageView(getContext());
                imageView.setImageBitmap(BitmapUtil.bytes2Bimap(keyMarkingChild.getImageByte()));
                FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(keyMarkingChild.getWidth(), keyMarkingChild.getHeight());
                layoutParams3.setMargins(keyMarkingChild.getMarginLeft(), keyMarkingChild.getMarginTop(), 0, 0);
                this.flContainer.addView(imageView, layoutParams3);
                OnDragTouchListener onDragTouchListener3 = new OnDragTouchListener(this.flContainer.getWidth(), this.flContainer.getHeight());
                onDragTouchListener3.setOnDraggableClickListener(this.onDraggableClickListener);
                imageView.setOnTouchListener(onDragTouchListener3);
            }
        }
    }

    @Override // com.kkkcut.e20j.base.BaseFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void onEventComing(EventCenter eventCenter) {
        if (isVisible()) {
            int eventCode = eventCenter.getEventCode();
            if (eventCode == 47) {
                showLoadingDialog(((Integer) eventCenter.getData()).intValue() + "%", true);
                return;
            }
            if (eventCode != 51) {
                switch (eventCode) {
                    case 32:
                        handleOperationFinish(eventCenter);
                        return;
                    case 33:
                        this.decode = false;
                        showError(eventCenter);
                        return;
                    case 34:
                        showLoadingDialog(getString(R.string.waitting));
                        return;
                    default:
                        return;
                }
            }
            if (DecoderPositionUtils.isDownPosition(((Integer) eventCenter.getData()).intValue())) {
                decoderLocation();
            } else {
                putDownDecoderRemindDialog();
            }
        }
    }

    private void showError(EventCenter eventCenter) {
        dismissLoadingDialog();
        showErrorDialog(getContext(), (ErrorBean) eventCenter.getData());
    }

    private void handleOperationFinish(EventCenter eventCenter) {
        OperateType operateType = (OperateType) eventCenter.getData();
        if (operateType == OperateType.ENGRAVE_CUT_LOCATION) {
            ClampManager.getInstance().getE9S5().getCenterY();
            int right = OperationManager.getInstance().getKeyAlignInfo().getRight();
            if (right > UnitConvertUtil.cmm2StepY(MachineData.getyAxisMax())) {
                right = UnitConvertUtil.cmm2StepY(MachineData.getyAxisMax());
            }
            int left = OperationManager.getInstance().getKeyAlignInfo().getLeft();
            if (left < 0) {
                left = 0;
            }
            int step2CmmKeyY = UnitConvertUtil.step2CmmKeyY(Math.abs(right - left));
            this.rightSpace = step2CmmKeyY;
            int i = step2CmmKeyY / 10;
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(i, -1);
            layoutParams.setMargins(195 - (i / 2), 45, 0, 10);
            this.flContainer.setLayoutParams(layoutParams);
            this.kmfl.showBorder(this.rightSpace / 10);
            if (this.decode) {
                center(i);
                dismissLoadingDialog();
                this.decode = false;
                return;
            } else {
                if (this.cbAutoCenter.isChecked()) {
                    center(i);
                }
                new Handler().postDelayed(new Runnable() { // from class: com.kkkcut.e20j.ui.fragment.engraving.KeyMarkingE9Fragment.9
                    @Override // java.lang.Runnable
                    public void run() {
                        KeyMarkingE9Fragment.this.detectCutterHigh();
                    }
                }, 100L);
                return;
            }
        }
        if (operateType == OperateType.ENGRAVE_CUT_CUTTER_HIGH) {
            startEngraving();
        } else if (operateType == OperateType.ENGRAVE_CUT_EXECUTE) {
            addDisposable(Observable.timer(500L, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() { // from class: com.kkkcut.e20j.ui.fragment.engraving.KeyMarkingE9Fragment.10
                @Override // io.reactivex.functions.Consumer
                public void accept(Long l) throws Exception {
                    KeyMarkingE9Fragment.this.dismissLoadingDialog();
                }
            }));
            showLoadingDialog("100%", true);
        }
    }

    public void putDownDecoderRemindDialog() {
        if (CuttingMachine.getInstance().isE9()) {
            RemindDialog remindDialog = new RemindDialog(getContext());
            remindDialog.setRemindImg(R.drawable.pull_decoder_down);
            remindDialog.setRemindMsg(getString(R.string.pull_the_decoder_down));
            remindDialog.setDialogBtnCallback(new RemindDialog.DialogBtnCallBack() { // from class: com.kkkcut.e20j.ui.fragment.engraving.KeyMarkingE9Fragment.11
                @Override // com.kkkcut.e20j.ui.dialog.RemindDialog.DialogBtnCallBack
                public void onDialogButClick(boolean z) {
                    if (z) {
                        KeyMarkingE9Fragment.this.checkDecoderState(OperateType.CHECK_DECODER_STATE_BEFORE_DECODER_LOCATION);
                    } else {
                        KeyMarkingE9Fragment.this.dismissLoadingDialog();
                    }
                }
            });
            remindDialog.show();
        }
    }

    private void center(int i) {
        int childCount = this.flContainer.getChildCount();
        int i2 = Integer.MAX_VALUE;
        int i3 = 0;
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt = this.flContainer.getChildAt(i4);
            i2 = Math.min(i2, childAt.getLeft());
            i3 = Math.max(i3, childAt.getLeft() + childAt.getWidth());
        }
        if (i3 - i2 > i) {
            for (int i5 = 0; i5 < childCount; i5++) {
                View childAt2 = this.flContainer.getChildAt(i5);
                if (childAt2.getWidth() > i) {
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) childAt2.getLayoutParams();
                    marginLayoutParams.width = i;
                    marginLayoutParams.setMargins(0, childAt2.getTop(), 0, 0);
                    childAt2.setLayoutParams(marginLayoutParams);
                } else {
                    int left = childAt2.getLeft() + childAt2.getWidth();
                    if (left > i) {
                        int i6 = left - i;
                        childAt2.layout(childAt2.getLeft() - i6, childAt2.getTop(), childAt2.getRight() - i6, childAt2.getBottom());
                        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) childAt2.getLayoutParams();
                        marginLayoutParams2.setMargins(childAt2.getLeft(), childAt2.getTop(), 0, 0);
                        childAt2.setLayoutParams(marginLayoutParams2);
                    }
                }
                OnDragTouchListener onDragTouchListener = new OnDragTouchListener(i, this.flContainer.getHeight());
                onDragTouchListener.setOnDraggableClickListener(this.onDraggableClickListener);
                childAt2.setOnTouchListener(onDragTouchListener);
            }
            return;
        }
        int i7 = i / 2;
        Log.i(TAG, "centerKey: " + i7);
        int i8 = (i2 + i3) / 2;
        Log.i(TAG, "centerContent: " + i8);
        int i9 = i7 - i8;
        for (int i10 = 0; i10 < childCount; i10++) {
            View childAt3 = this.flContainer.getChildAt(i10);
            if (childAt3.getLeft() + i9 < 0) {
                i9 = -childAt3.getLeft();
            }
            childAt3.layout(childAt3.getLeft() + i9, childAt3.getTop(), childAt3.getRight() + i9, childAt3.getBottom());
            ViewGroup.MarginLayoutParams marginLayoutParams3 = (ViewGroup.MarginLayoutParams) childAt3.getLayoutParams();
            marginLayoutParams3.setMargins(childAt3.getLeft(), childAt3.getTop(), 0, 0);
            childAt3.setLayoutParams(marginLayoutParams3);
            OnDragTouchListener onDragTouchListener2 = new OnDragTouchListener(i, this.flContainer.getHeight());
            onDragTouchListener2.setOnDraggableClickListener(this.onDraggableClickListener);
            childAt3.setOnTouchListener(onDragTouchListener2);
        }
    }
}
