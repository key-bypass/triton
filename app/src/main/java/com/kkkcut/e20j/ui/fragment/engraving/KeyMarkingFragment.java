package com.kkkcut.e20j.ui.fragment.engraving;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
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
import com.kkkcut.e20j.customView.KeyMarkingFramelayout;
import com.kkkcut.e20j.customView.OnDragTouchListener;
import com.kkkcut.e20j.dao.UserDataDaoManager;
import com.kkkcut.e20j.ui.dialog.EditDialog;
import com.kkkcut.e20j.ui.dialog.WarningDialog;
import com.kkkcut.e20j.ui.fragment.BaseBackFragment;
import com.kkkcut.e20j.ui.fragment.engraving.EngravePathGen;
import com.kkkcut.e20j.us.R;
import com.kkkcut.e20j.utils.BitmapUtil;
import com.liying.core.OperateType;
import com.liying.core.ToolSizeManager;
import com.liying.core.bean.StepBean;
import com.liying.core.clamp.Clamp;
import com.liying.core.clamp.ClampManager;
import com.liying.core.clamp.MachineData;
import com.liying.core.communication.OperationManager;
import com.liying.core.error.ErrorBean;
import com.liying.core.error.ErrorCode;
import com.liying.core.operation.cut.DataParam;
import com.liying.core.utils.AssetsJsonUtils;
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
public class KeyMarkingFragment extends BaseBackFragment {
    private static final int FRAME_HEITHT = 302;
    private static final int FRAME_WIDTH = 302;
    private static final int LEFT_SPACE = 0;
    private static final int SELECT_IMAGE = 1;
    private static final int SELECT_TEMPLATE = 2;

    @BindView(R.id.cb_auto_center)
    CheckBox cbAutoCenter;
    private View currentView;
    private boolean decode;
    private Bitmap engraveingBitmap;

    @BindView(R.id.fl_container)
    FrameLayout flContainer;
    private Typeface fontFace;

    @BindView(R.id.kmfl)
    KeyMarkingFramelayout kmfl;

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
    private OnDragTouchListener.OnDraggableClickListener onDraggableClickListener = new OnDragTouchListener.OnDraggableClickListener() { // from class: com.kkkcut.e20j.ui.fragment.engraving.KeyMarkingFragment.1
        @Override // com.kkkcut.e20j.customView.OnDragTouchListener.OnDraggableClickListener
        public void onDragged(View view, int i, int i2) {
        }

        @Override // com.kkkcut.e20j.customView.OnDragTouchListener.OnDraggableClickListener
        public void onClick(View view) {
            KeyMarkingFragment.this.changeCurrentView(view);
        }
    };

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_key_marking;
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

    public static KeyMarkingFragment newInstance() {
        Bundle bundle = new Bundle();
        KeyMarkingFragment keyMarkingFragment = new KeyMarkingFragment();
        keyMarkingFragment.setArguments(bundle);
        return keyMarkingFragment;
    }

    @Override // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    public String setTitleStr() {
        return getString(R.string.key_marking);
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        int i = SPUtils.getInt(SpKeys.ENGRAVING_SPEED, 15);
        if (i == 1) {
            this.rbLowSpeed.setChecked(true);
        } else if (i == 15) {
            this.rbMiddleSpeed.setChecked(true);
        } else if (i == 25) {
            this.rbHighSpeed.setChecked(true);
        }
        initParam();
        showRemind();
    }

    private void showRemind() {
        WarningDialog warningDialog = new WarningDialog(getContext());
        warningDialog.show();
        warningDialog.setRemind("First to click MEASURE button to measure the engraving area of metal piece");
    }

    private void initParam() {
        this.dataParam.setClamp(Clamp.S5);
        this.dataParam.setDecoderSize(100);
    }

    @OnClick({R.id.iv_up, R.id.iv_down, R.id.iv_left, R.id.iv_right, R.id.bt_cut, R.id.font_size_add, R.id.font_size_reduce, R.id.tv_select_template, R.id.iv_depth_reduce, R.id.iv_depth_add, R.id.tv_add_text, R.id.tv_edit_text, R.id.tv_delete, R.id.tv_add_pic, R.id.tv_save, R.id.rb_low_speed, R.id.rb_middle_speed, R.id.rb_high_speed, R.id.bt_decode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_cut /* 2131361921 */:
                if (ClampManager.getInstance().checkHasCalibrated(Clamp.S5) && this.flContainer.getChildCount() != 0) {
                    showLoadingDialog(getString(R.string.waitting), true);
                    this.dataParam.setCutDepth(this.cutDepth);
                    this.dataParam.setCutSpeed(SPUtils.getInt(SpKeys.ENGRAVING_SPEED, 15));
                    OperationManager.getInstance().start(this.dataParam, AssetsJsonUtils.getCommonSteps(getContext(), "keyblank/decoder/S5.json"), OperateType.ENGRAVE_CUT_LOCATION);
                    return;
                }
                return;
            case R.id.bt_decode /* 2131361922 */:
                if (ClampManager.getInstance().checkHasCalibrated(Clamp.S5)) {
                    showLoadingDialog(getString(R.string.waitting), true);
                    this.decode = true;
                    OperationManager.getInstance().start(this.dataParam, AssetsJsonUtils.getCommonSteps(getContext(), "keyblank/decoder/S5.json"), OperateType.ENGRAVE_CUT_LOCATION);
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
                    if (i3 > 302 || i4 > 302) {
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

    private void showSaveTemplateDialog() {
        EditDialog editDialog = new EditDialog(getContext());
        editDialog.setEditTextContent("");
        editDialog.setTip(getString(R.string.enter_remarks));
        editDialog.setDialogBtnCallback(new EditDialog.DialogInputFinishCallBack() { // from class: com.kkkcut.e20j.ui.fragment.engraving.KeyMarkingFragment$$ExternalSyntheticLambda1
            @Override // com.kkkcut.e20j.ui.dialog.EditDialog.DialogInputFinishCallBack
            public final void onDialogButClick(String str) {
                KeyMarkingFragment.this.m55xac3257f9(str);
            }
        });
        editDialog.setContentNullable(true);
        editDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: saveTemplate, reason: merged with bridge method [inline-methods] */
    public void m55xac3257f9(String str) {
        KeyMarkingTemplate keyMarkingTemplate = new KeyMarkingTemplate();
        keyMarkingTemplate.setWidth(ErrorCode.RiskCutClampDownFaceS1a);
        keyMarkingTemplate.setHeight(ErrorCode.RiskCutClampDownFaceS1a);
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
        float f2 = (f * 1.0f) / 302.0f;
        float f3 = i2;
        float f4 = (f3 * 1.0f) / 302.0f;
        if (f2 <= 1.0f && f4 <= 1.0f) {
            options.inJustDecodeBounds = false;
            Bitmap decodeFile = BitmapFactory.decodeFile(file.getPath(), options);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(i, i2);
            layoutParams.setMargins((302 - i) / 2, (302 - i2) / 2, (i + ErrorCode.RiskCutClampDownFaceS1a) / 2, (i2 + ErrorCode.RiskCutClampDownFaceS1a) / 2);
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
            layoutParams2.setMargins((302 - i3) / 2, (302 - i4) / 2, (i3 + ErrorCode.RiskCutClampDownFaceS1a) / 2, (i4 + ErrorCode.RiskCutClampDownFaceS1a) / 2);
            imageView.setLayoutParams(layoutParams2);
            imageView.setImageBitmap(scaleBitmap);
        }
        return imageView;
    }

    private void detectCutterHigh() {
        addDisposable(Observable.fromCallable(new Callable<Rect>() { // from class: com.kkkcut.e20j.ui.fragment.engraving.KeyMarkingFragment.5
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Rect call() throws Exception {
                return KeyMarkingFragment.this.getMarkingRectRealScreen();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).map(new Function<Rect, Bitmap>() { // from class: com.kkkcut.e20j.ui.fragment.engraving.KeyMarkingFragment.4
            @Override // io.reactivex.functions.Function
            public Bitmap apply(Rect rect) throws Exception {
                return KeyMarkingFragment.this.getScreenBitmap(rect);
            }
        }).observeOn(Schedulers.io()).map(new Function<Bitmap, List<StepBean>>() { // from class: com.kkkcut.e20j.ui.fragment.engraving.KeyMarkingFragment.3
            @Override // io.reactivex.functions.Function
            public List<StepBean> apply(Bitmap bitmap) throws Exception {
                EngravePathGen.EngraveParam engraveParam = KeyMarkingFragment.this.getEngraveParam();
                KeyMarkingFragment.this.engraveingBitmap = bitmap;
                return EngravePathGen.detectCutterHigh(bitmap, engraveParam);
            }
        }).subscribe(new Consumer<List<StepBean>>() { // from class: com.kkkcut.e20j.ui.fragment.engraving.KeyMarkingFragment.2
            @Override // io.reactivex.functions.Consumer
            public void accept(List<StepBean> list) throws Exception {
                OperationManager.getInstance().start(KeyMarkingFragment.this.dataParam, list, OperateType.ENGRAVE_CUT_CUTTER_HIGH);
            }
        }));
    }

    private void startEngraving() {
        if (this.engraveingBitmap == null) {
            ToastUtil.showToast("no bitmap");
            dismissLoadingDialog();
        } else {
            addDisposable(Observable.fromCallable(new Callable<List<StepBean>>() { // from class: com.kkkcut.e20j.ui.fragment.engraving.KeyMarkingFragment.7
                @Override // java.util.concurrent.Callable
                public List<StepBean> call() throws Exception {
                    return EngravePathGen.bitmapToPath(KeyMarkingFragment.this.engraveingBitmap, KeyMarkingFragment.this.getEngraveParam());
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).subscribe(new Consumer<List<StepBean>>() { // from class: com.kkkcut.e20j.ui.fragment.engraving.KeyMarkingFragment.6
                @Override // io.reactivex.functions.Consumer
                public void accept(List<StepBean> list) throws Exception {
                    if (ClampManager.getInstance().checkHaveRiskCutClamp(list, ToolSizeManager.getCutterSize())) {
                        return;
                    }
                    OperationManager.getInstance().start(KeyMarkingFragment.this.dataParam, list, OperateType.ENGRAVE_CUT_EXECUTE);
                }
            }));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public EngravePathGen.EngraveParam getEngraveParam() {
        return new EngravePathGen.EngraveParam(ClampManager.getInstance().getS5().getX(), ClampManager.getInstance().getS5().getY(), this.origin.x * 10, this.origin.y * 10, ClampManager.getInstance().getDC().getxDistance(), ClampManager.getInstance().getDC().getyDistance(), OperationManager.getInstance().getKeyAlignInfo().getKeyFace(), (int) ((!TextUtils.isEmpty(this.tvDepthValue.getText().toString().trim()) ? Math.round(Float.parseFloat(r0.replace("mm", "")) * 100.0f) : 5) / MachineData.dZScale), SPUtils.getInt(SpKeys.ENGRAVING_SPEED, 15));
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
        this.origin.x = Math.abs(rect.left - iArr2[0]) + 30;
        this.origin.y = Math.abs(rect.top - iArr2[1]) + 30;
        return rect;
    }

    private void showEditDialog() {
        View view = this.currentView;
        if (view == null || !(view instanceof TextView)) {
            return;
        }
        EditDialog editDialog = new EditDialog(getContext());
        editDialog.setDialogBtnCallback(new EditDialog.DialogInputFinishCallBack() { // from class: com.kkkcut.e20j.ui.fragment.engraving.KeyMarkingFragment.8
            @Override // com.kkkcut.e20j.ui.dialog.EditDialog.DialogInputFinishCallBack
            public void onDialogButClick(String str) {
                ((TextView) KeyMarkingFragment.this.currentView).setText(str);
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
        editDialog.setDialogBtnCallback(new EditDialog.DialogInputFinishCallBack() { // from class: com.kkkcut.e20j.ui.fragment.engraving.KeyMarkingFragment$$ExternalSyntheticLambda0
            @Override // com.kkkcut.e20j.ui.dialog.EditDialog.DialogInputFinishCallBack
            public final void onDialogButClick(String str) {
                KeyMarkingFragment.this.m54x7e53ad15(str);
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
    public void m54x7e53ad15(String str) {
        AlignTextView alignTextView = new AlignTextView(getContext());
        if (Build.VERSION.SDK_INT >= 21) {
            alignTextView.setElevation(1.0f);
        }
        alignTextView.setIncludeFontPadding(false);
        alignTextView.setTextColor(SupportMenu.CATEGORY_MASK);
        alignTextView.setTextSize(30.0f);
        alignTextView.setText(str);
        alignTextView.setLetterSpacing(0.05f);
        alignTextView.setIncludeFontPadding(false);
        OnDragTouchListener onDragTouchListener = new OnDragTouchListener(this.flContainer.getWidth(), this.flContainer.getHeight());
        onDragTouchListener.setOnDraggableClickListener(this.onDraggableClickListener);
        alignTextView.setOnTouchListener(onDragTouchListener);
        this.flContainer.addView(alignTextView, new FrameLayout.LayoutParams(-2, -2));
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
        for (KeyMarkingChild keyMarkingChild : keyMarkingTemplate.getChildrenBeanList()) {
            if (keyMarkingChild.getType() == 1) {
                AlignTextView alignTextView = new AlignTextView(getContext());
                if (Build.VERSION.SDK_INT >= 21) {
                    alignTextView.setElevation(1.0f);
                }
                alignTextView.setTextSize(0, keyMarkingChild.getFontSize());
                alignTextView.setText(keyMarkingChild.getText());
                alignTextView.setIncludeFontPadding(false);
                alignTextView.setLetterSpacing(0.05f);
                alignTextView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
                layoutParams.setMargins(keyMarkingChild.getMarginLeft() + keyMarkingTemplate.getMarginLeft(), keyMarkingChild.getMarginTop() + keyMarkingTemplate.getMarginLeft(), 0, 0);
                this.flContainer.addView(alignTextView, layoutParams);
                OnDragTouchListener onDragTouchListener2 = new OnDragTouchListener(this.flContainer.getWidth(), this.flContainer.getHeight());
                onDragTouchListener2.setOnDraggableClickListener(this.onDraggableClickListener);
                alignTextView.setOnTouchListener(onDragTouchListener2);
            } else {
                ImageView imageView = new ImageView(getContext());
                imageView.setImageBitmap(BitmapUtil.bytes2Bimap(keyMarkingChild.getImageByte()));
                FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(keyMarkingChild.getWidth(), keyMarkingChild.getHeight());
                layoutParams2.setMargins(keyMarkingChild.getMarginLeft(), keyMarkingChild.getMarginTop(), 0, 0);
                this.flContainer.addView(imageView, layoutParams2);
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
            if (eventCode != 47) {
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
            showLoadingDialog(((Integer) eventCenter.getData()).intValue() + "%", true);
        }
    }

    private void showError(EventCenter eventCenter) {
        dismissLoadingDialog();
        showErrorDialog(getContext(), (ErrorBean) eventCenter.getData());
    }

    private void handleOperationFinish(EventCenter eventCenter) {
        OperateType operateType = (OperateType) eventCenter.getData();
        if (operateType == OperateType.ENGRAVE_CUT_LOCATION) {
            int abs = ((int) Math.abs((ClampManager.getInstance().getS5().getX() - OperationManager.getInstance().getKeyAlignInfo().getRight()) * MachineData.dXScale)) + 700;
            this.rightSpace = abs;
            int i = (abs / 10) - 30;
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(i, -1);
            layoutParams.setMargins(75, 75, 0, 10);
            this.flContainer.setLayoutParams(layoutParams);
            this.kmfl.showBorder(this.rightSpace / 10);
            if (this.decode) {
                dismissLoadingDialog();
                this.decode = false;
                return;
            }
            if (this.cbAutoCenter.isChecked()) {
                int childCount = this.flContainer.getChildCount();
                int i2 = Integer.MAX_VALUE;
                int i3 = 0;
                for (int i4 = 0; i4 < childCount; i4++) {
                    View childAt = this.flContainer.getChildAt(i4);
                    i2 = Math.min(i2, childAt.getLeft());
                    i3 = Math.max(i3, childAt.getLeft() + childAt.getWidth());
                }
                if (i3 - i2 <= i) {
                    int i5 = ((this.rightSpace / 10) + 30) / 2;
                    Log.i(TAG, "centerKey: " + i5);
                    int i6 = (i2 + i3) / 2;
                    Log.i(TAG, "centerContent: " + i6);
                    int i7 = i5 - i6;
                    for (int i8 = 0; i8 < childCount; i8++) {
                        View childAt2 = this.flContainer.getChildAt(i8);
                        if (childAt2.getLeft() + i7 < 0) {
                            i7 = 0 - childAt2.getLeft();
                        }
                        childAt2.layout((childAt2.getLeft() + i7) - 30, childAt2.getTop(), (childAt2.getRight() + i7) - 30, childAt2.getBottom());
                        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) childAt2.getLayoutParams();
                        marginLayoutParams.setMargins(childAt2.getLeft(), childAt2.getTop(), 0, 0);
                        childAt2.setLayoutParams(marginLayoutParams);
                        OnDragTouchListener onDragTouchListener = new OnDragTouchListener(layoutParams.width, this.flContainer.getHeight());
                        onDragTouchListener.setOnDraggableClickListener(this.onDraggableClickListener);
                        childAt2.setOnTouchListener(onDragTouchListener);
                    }
                } else {
                    for (int i9 = 0; i9 < childCount; i9++) {
                        View childAt3 = this.flContainer.getChildAt(i9);
                        if (childAt3.getWidth() > i) {
                            ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) childAt3.getLayoutParams();
                            marginLayoutParams2.width = i;
                            marginLayoutParams2.setMargins(0, childAt3.getTop(), 0, 0);
                            childAt3.setLayoutParams(marginLayoutParams2);
                        } else {
                            int left = childAt3.getLeft() + childAt3.getWidth();
                            if (left > i) {
                                int i10 = left - i;
                                childAt3.layout(childAt3.getLeft() - i10, childAt3.getTop(), childAt3.getRight() - i10, childAt3.getBottom());
                                ViewGroup.MarginLayoutParams marginLayoutParams3 = (ViewGroup.MarginLayoutParams) childAt3.getLayoutParams();
                                marginLayoutParams3.setMargins(childAt3.getLeft(), childAt3.getTop(), 0, 0);
                                childAt3.setLayoutParams(marginLayoutParams3);
                            }
                        }
                        OnDragTouchListener onDragTouchListener2 = new OnDragTouchListener(layoutParams.width, this.flContainer.getHeight());
                        onDragTouchListener2.setOnDraggableClickListener(this.onDraggableClickListener);
                        childAt3.setOnTouchListener(onDragTouchListener2);
                    }
                }
            }
            detectCutterHigh();
            return;
        }
        if (operateType == OperateType.ENGRAVE_CUT_CUTTER_HIGH) {
            startEngraving();
        } else if (operateType == OperateType.ENGRAVE_CUT_EXECUTE) {
            addDisposable(Observable.timer(500L, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() { // from class: com.kkkcut.e20j.ui.fragment.engraving.KeyMarkingFragment.9
                @Override // io.reactivex.functions.Consumer
                public void accept(Long l) throws Exception {
                    KeyMarkingFragment.this.dismissLoadingDialog();
                }
            }));
            showLoadingDialog("100%", true);
        }
    }
}
