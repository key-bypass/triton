package com.kkkcut.e20j.ui.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.OnClick;
import com.kkkcut.e20j.DbBean.BittingCode;
import com.kkkcut.e20j.DbBean.GoOperatBean;
import com.kkkcut.e20j.DbBean.KeyBasicData;
import com.kkkcut.e20j.DbBean.KeyBasicDataItem;
import com.kkkcut.e20j.DbBean.userDB.CollectionData;
import com.kkkcut.e20j.DbBean.userDB.CustomKey;
import com.kkkcut.e20j.DbBean.userDB.CutHistoryData;
import com.kkkcut.e20j.MyApplication;
import com.kkkcut.e20j.SpKeys;
import com.kkkcut.e20j.androidquick.autolayout.utils.AutoUtils;
import com.kkkcut.e20j.androidquick.tool.AppUtil;
import com.kkkcut.e20j.androidquick.tool.FileUtil;
import com.kkkcut.e20j.androidquick.tool.LogUtil;
import com.kkkcut.e20j.androidquick.tool.SPUtils;
import com.kkkcut.e20j.androidquick.tool.ToastUtil;
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter;
import com.kkkcut.e20j.bean.AngleKeyStep;
import com.kkkcut.e20j.bean.Bitt;
import com.kkkcut.e20j.bean.CodeAndTooth;
import com.kkkcut.e20j.bean.eventbus.InputFinishBean;
import com.kkkcut.e20j.customView.MarqueeTextView;
import com.kkkcut.e20j.customView.drawKeyImg.AngleKey;
import com.kkkcut.e20j.customView.drawKeyImg.DimpleKey;
import com.kkkcut.e20j.customView.drawKeyImg.DoubleInsideGrooveKey;
import com.kkkcut.e20j.customView.drawKeyImg.DoubleKey;
import com.kkkcut.e20j.customView.drawKeyImg.DoubleOutsideKey;
import com.kkkcut.e20j.customView.drawKeyImg.Key;
import com.kkkcut.e20j.customView.drawKeyImg.Side3KsKey;
import com.kkkcut.e20j.customView.drawKeyImg.SideToothKey;
import com.kkkcut.e20j.customView.drawKeyImg.SigleInsideGrooveKey;
import com.kkkcut.e20j.customView.drawKeyImg.SingleKey;
import com.kkkcut.e20j.customView.drawKeyImg.SingleOutGrooveKey;
import com.kkkcut.e20j.customView.drawKeyImg.TubularKey;
import com.kkkcut.e20j.dao.KeyInfoDaoManager;
import com.kkkcut.e20j.dao.ToothCodeDaoManager;
import com.kkkcut.e20j.dao.UserDataDaoManager;
import com.kkkcut.e20j.ui.SizeAdjustFragment;
import com.kkkcut.e20j.ui.activity.LookPicActivity;
import com.kkkcut.e20j.ui.dialog.AnglekeyTurningDialog;
import com.kkkcut.e20j.ui.dialog.ChooseBittingNumbersDialog;
import com.kkkcut.e20j.ui.dialog.CutDialog;
import com.kkkcut.e20j.ui.dialog.DecodeDialog;
import com.kkkcut.e20j.ui.dialog.EditDialog;
import com.kkkcut.e20j.ui.dialog.RemindDialog;
import com.kkkcut.e20j.ui.fragment.clampswitch.ClampCreator;
import com.kkkcut.e20j.ui.fragment.clampswitch.ClampDisplayBean;
import com.kkkcut.e20j.ui.fragment.clampswitch.ClampSwitchPagerAdapter;
import com.kkkcut.e20j.us.R;
import com.kkkcut.e20j.utils.CutCountHelper;
import com.kkkcut.e20j.utils.DatabaseFileUtils;
import com.kkkcut.e20j.utils.ResUpdateUtils;
import com.liying.core.Command;
import com.liying.core.CuttingMachine;
import com.liying.core.MachineInfo;
import com.liying.core.OperateType;
import com.liying.core.ToolSizeManager;
import com.liying.core.bean.ClampBean;
import com.liying.core.bean.KeyInfo;
import com.liying.core.clamp.ClampManager;
import com.liying.core.clamp.MachineData;
import com.liying.core.communication.OperationManager;
import com.liying.core.error.ErrorBean;
import com.liying.core.error.ErrorCode;
import com.liying.core.operation.cut.DataParam;
import com.liying.core.utils.KeyDataUtils;
import io.reactivex.Observable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import org.greenrobot.eventbus.EventBus;

/* loaded from: classes.dex */
public class KeyOperateFragment extends BaseBackFragment {
    private static final String ANGLE_KEY_INIT = "ANGLE_KEY_INIT";
    private static final String ANGLE_KEY_TURN_OVER = "ANGLE_KEY_TURN_OVER";
    private static final String KEYINFO = "keyinfo";
    public static final String KEY_DATA = "keyData";
    public static final String TAG = "KeyOperateFragment";
    private int angleKeyCutIndex;
    private int angleKeyDepthCount;
    private List<AngleKeyStep> angleKeySteps;
    private ArrayList<CodeAndTooth> bittingCodes;

    @BindView(R.id.bt_change_sibling)
    Button btChangedSibling;

    @BindView(R.id.bt_cut)
    TextView btCut;

    @BindView(R.id.bt_decode)
    TextView btDecode;
    private RemindDialog clearClampRemind;
    private int cutCount;
    private int dimpleCutIndex;
    private boolean doorIgnition;
    private boolean doorToIgnition;

    @BindView(R.id.fl_key_view)
    FrameLayout flKeyview;
    private boolean isBarCodeScan;
    private boolean isSibling;

    @BindView(R.id.iv_real_key)
    ImageView ivRealKey;

    @BindView(R.id.iv_switch_last)
    ImageView ivSwitchLast;

    @BindView(R.id.iv_switch_next)
    ImageView ivSwitchNext;
    private KeyInfo ki;
    private ClampBean lastClamp;

    @BindView(R.id.tv_code_find_tooth)
    TextView llCodeFindTooth;

    @BindView(R.id.tv_input)
    TextView llInput;

    @BindView(R.id.tv_lack_tooth)
    TextView llLackTooth;
    private Key mKey;
    private int mainKeyID;
    private String mainKeyToothCode;

    @BindView(R.id.mtv)
    MarqueeTextView mtv;
    ViewPager.OnPageChangeListener onPageChangeListener;
    private boolean rounding;
    private String sideKeyToothCode;
    private String toothCodeLack;
    private int toothCount;

    @BindView(R.id.tv_adjust)
    TextView tvAdjust;

    @BindView(R.id.tv_blank)
    TextView tvBlank;

    @BindView(R.id.tv_code)
    TextView tvCode;

    @BindView(R.id.tv_collect)
    TextView tvCollect;

    @BindView(R.id.tv_cutter_size)
    TextView tvCutterSize;

    @BindView(R.id.tv_decoder_size)
    TextView tvDecoderSize;

    @BindView(R.id.tv_hint)
    TextView tvHint;

    @BindView(R.id.tv_info)
    TextView tvInfo;

    @BindView(R.id.tv_move)
    TextView tvMove;

    @BindView(R.id.tv_series)
    TextView tvSeries;

    @BindView(R.id.tv_side)
    TextView tvSide;

    @BindView(R.id.vp_clamp)
    ViewPager vpClamp;
    private boolean moveToRight = true;
    private DataParam dataParam = new DataParam();
    private Map<Integer, String> toothCodeMap = new HashMap();
    private ArrayList<Bitt> bittList = new ArrayList<>();

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_cut_key;
    }

    static /* synthetic */ int access$1308(KeyOperateFragment keyOperateFragment) {
        int i = keyOperateFragment.angleKeyCutIndex;
        keyOperateFragment.angleKeyCutIndex = i + 1;
        return i;
    }

    static /* synthetic */ int access$1310(KeyOperateFragment keyOperateFragment) {
        int i = keyOperateFragment.angleKeyCutIndex;
        keyOperateFragment.angleKeyCutIndex = i - 1;
        return i;
    }

    static /* synthetic */ int access$608(KeyOperateFragment keyOperateFragment) {
        int i = keyOperateFragment.dimpleCutIndex;
        keyOperateFragment.dimpleCutIndex = i + 1;
        return i;
    }

    public static KeyOperateFragment newInstance(GoOperatBean goOperatBean) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_DATA, goOperatBean);
        KeyOperateFragment keyOperateFragment = new KeyOperateFragment();
        keyOperateFragment.setArguments(bundle);
        return keyOperateFragment;
    }

    public static KeyOperateFragment newInstance() {
        return new KeyOperateFragment();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public GoOperatBean getKeyData() {
        return (GoOperatBean) getArguments().getParcelable(KEY_DATA);
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        initKey(getKeyData().getKeyID());
        String codeSeries = getKeyData().getCodeSeries();
        if (!TextUtils.isEmpty(codeSeries)) {
            this.tvSeries.setText(codeSeries);
        }
        String title = getKeyData().getTitle();
        this.tvHint.setText(title);
        this.isBarCodeScan = title.contains("Bar Code");
        checkKeyCollected();
        if (!getKeyData().isCustomkey()) {
            ResUpdateUtils.showKeyImage(getContext(), getKeyData().getKeyID(), this.ivRealKey);
            checkCodeDababaseExist(getKeyData().getSeriesID(), getKeyData().getKeyID());
        } else {
            this.tvInfo.setVisibility(8);
            this.llCodeFindTooth.setVisibility(8);
            this.llLackTooth.setVisibility(8);
        }
    }

    private void checkCodeDababaseExist(final int i, final int i2) {
        addDisposable(Observable.fromCallable(new Callable<Boolean>() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment.3
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Boolean call() throws Exception {
                if (i > 0) {
                    return Boolean.valueOf(KeyInfoDaoManager.getInstance().isChineseCar(i));
                }
                return false;
            }
        }).map(new Function<Boolean, Boolean>() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment.2
            @Override // io.reactivex.functions.Function
            public Boolean apply(Boolean bool) throws Exception {
                return Boolean.valueOf(DatabaseFileUtils.dataExist(String.valueOf(i2)));
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Boolean>() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment.1
            @Override // io.reactivex.functions.Consumer
            public void accept(Boolean bool) throws Exception {
                if (bool.booleanValue()) {
                    KeyOperateFragment.this.llLackTooth.setVisibility(0);
                    KeyOperateFragment.this.llCodeFindTooth.setVisibility(0);
                } else {
                    KeyOperateFragment.this.llLackTooth.setVisibility(8);
                    KeyOperateFragment.this.llCodeFindTooth.setVisibility(8);
                }
            }
        }));
    }

    private void initKey(int i) {
        addDisposable(getKeyInfoDisposable(i, getKeyData().isCustomkey()).subscribeOn(Schedulers.io()).doOnSubscribe(new Consumer() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment$$ExternalSyntheticLambda6
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                KeyOperateFragment.this.m29lambda$initKey$0$comkkkcute20juifragmentKeyOperateFragment((Disposable) obj);
            }
        }).doFinally(new Action() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment$$ExternalSyntheticLambda3
            @Override // io.reactivex.functions.Action
            public final void run() {
                KeyOperateFragment.this.m30lambda$initKey$1$comkkkcute20juifragmentKeyOperateFragment();
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment$$ExternalSyntheticLambda4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                KeyOperateFragment.this.m31lambda$initKey$2$comkkkcute20juifragmentKeyOperateFragment((KeyInfo) obj);
            }
        }, new Consumer() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment$$ExternalSyntheticLambda7
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                ToastUtil.showToast(((Throwable) obj).getMessage());
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$initKey$0$com-kkkcut-e20j-ui-fragment-KeyOperateFragment, reason: not valid java name */
    public /* synthetic */ void m29lambda$initKey$0$comkkkcute20juifragmentKeyOperateFragment(Disposable disposable) throws Exception {
        showLoadingDialog(getString(R.string.waitting));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$initKey$1$com-kkkcut-e20j-ui-fragment-KeyOperateFragment, reason: not valid java name */
    public /* synthetic */ void m30lambda$initKey$1$comkkkcute20juifragmentKeyOperateFragment() throws Exception {
        dismissLoadingDialog();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$initKey$2$com-kkkcut-e20j-ui-fragment-KeyOperateFragment, reason: not valid java name */
    public /* synthetic */ void m31lambda$initKey$2$comkkkcute20juifragmentKeyOperateFragment(KeyInfo keyInfo) throws Exception {
        initKeyinfo(keyInfo);
        initKeyView(keyInfo);
        initClamp(keyInfo);
        initParam(keyInfo);
        initSiblingKey(keyInfo);
        initButton(keyInfo);
        if (getKeyData().isCustomkey()) {
            return;
        }
        this.tvBlank.setText(keyInfo.getKeyBlanks());
        initRemind(keyInfo);
    }

    private Observable<KeyInfo> getKeyInfoDisposable(final int i, boolean z) {
        if (z) {
            return Observable.fromCallable(new Callable() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment$$ExternalSyntheticLambda2
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return KeyOperateFragment.this.m28x8f932e35(i);
                }
            }).map(new Function() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment$$ExternalSyntheticLambda10
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    KeyInfo keyInfo;
                    keyInfo = ((CustomKey) obj).toKeyInfo();
                    return keyInfo;
                }
            });
        }
        return Observable.fromCallable(new Callable() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment$$ExternalSyntheticLambda11
            @Override // java.util.concurrent.Callable
            public final Object call() {
                KeyBasicData basicData;
                basicData = KeyInfoDaoManager.getInstance().getBasicData(i);
                return basicData;
            }
        }).map(new Function() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment$$ExternalSyntheticLambda8
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                KeyInfo keyInfo;
                keyInfo = ((KeyBasicData) obj).toKeyInfo();
                return keyInfo;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getKeyInfoDisposable$4$com-kkkcut-e20j-ui-fragment-KeyOperateFragment, reason: not valid java name */
    public /* synthetic */ CustomKey m28x8f932e35(int i) throws Exception {
        return UserDataDaoManager.getInstance(getContext()).getCustomKeyByID(i);
    }

    private void initRemind(KeyInfo keyInfo) {
        String desc = keyInfo.getDesc();
        if (TextUtils.isEmpty(desc)) {
            return;
        }
        this.mtv.setText(desc);
    }

    private void initButton(KeyInfo keyInfo) {
        this.angleKeyDepthCount = this.ki.getDepthName().split(";")[0].split(",").length;
        if (keyInfo.getType() == 7) {
            this.btDecode.setVisibility(8);
        }
        if (keyInfo.getSpaceStr().split(";").length > 4) {
            this.btDecode.setVisibility(8);
            this.btCut.setVisibility(8);
        }
    }

    private void initKeySide(final int i) {
        addDisposable(Observable.fromCallable(new Callable() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment$$ExternalSyntheticLambda1
            @Override // java.util.concurrent.Callable
            public final Object call() {
                KeyBasicDataItem basicDataSide;
                basicDataSide = KeyInfoDaoManager.getInstance().getBasicDataSide(i);
                return basicDataSide;
            }
        }).map(new Function() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment$$ExternalSyntheticLambda9
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                KeyInfo keyInfo;
                keyInfo = ((KeyBasicDataItem) obj).toKeyInfo();
                return keyInfo;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment$$ExternalSyntheticLambda5
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                KeyOperateFragment.this.m32xce3e71f((KeyInfo) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$initKeySide$10$com-kkkcut-e20j-ui-fragment-KeyOperateFragment, reason: not valid java name */
    public /* synthetic */ void m32xce3e71f(KeyInfo keyInfo) throws Exception {
        initKeyinfo(keyInfo);
        initKeyView(keyInfo);
        initSiblingKey(keyInfo);
        initClamp(keyInfo);
        initCutDepth(keyInfo);
    }

    private void initKeyinfo(KeyInfo keyInfo) {
        this.ki = keyInfo;
        GoOperatBean keyData = getKeyData();
        boolean isDoorIgnition = keyData.isDoorIgnition();
        if (isDoorIgnition) {
            this.doorIgnition = isDoorIgnition;
            this.doorToIgnition = keyData.isDoorToIgnition();
            this.sideKeyToothCode = keyData.getToothCode();
        }
        if (TextUtils.isEmpty(this.ki.getKeyComb())) {
            if (!this.isSibling) {
                if (!TextUtils.isEmpty(this.mainKeyToothCode)) {
                    this.ki.setKeyToothCode(this.mainKeyToothCode);
                } else {
                    this.ki.setKeyToothCode(keyData.getToothCode());
                }
            } else {
                if (TextUtils.isEmpty(this.sideKeyToothCode)) {
                    this.sideKeyToothCode = getKeyData().getToothCodeSide();
                }
                this.ki.setKeyToothCode(this.sideKeyToothCode);
            }
        } else if (!TextUtils.isEmpty(this.toothCodeMap.get(Integer.valueOf(this.ki.getIcCard())))) {
            KeyInfo keyInfo2 = this.ki;
            keyInfo2.setKeyToothCode(this.toothCodeMap.get(Integer.valueOf(keyInfo2.getIcCard())));
        } else {
            String[] split = this.ki.getKeyComb().split(",");
            if (this.ki.getIcCard() == Integer.parseInt(split[0].split("-")[1])) {
                this.toothCodeMap.put(Integer.valueOf(this.ki.getIcCard()), getKeyData().getToothCode());
                this.ki.setKeyToothCode(getKeyData().getToothCode());
            }
            if (this.ki.getIcCard() == Integer.parseInt(split[1].split("-")[1]) || this.ki.getIcCard() == Integer.parseInt(split[2].split("-")[1])) {
                String toothCodeSide = getKeyData().getToothCodeSide();
                if (!TextUtils.isEmpty(toothCodeSide)) {
                    for (String str : toothCodeSide.split("\\|")) {
                        String[] split2 = str.split(":");
                        if (Integer.parseInt(split2[0]) == this.ki.getIcCard()) {
                            this.toothCodeMap.put(Integer.valueOf(Integer.parseInt(split2[0])), split2[1]);
                            this.ki.setKeyToothCode(split2[1]);
                        }
                    }
                }
            }
        }
        this.ki.setSeriesID(keyData.getSeriesID());
        this.ki.setCodeSeries(keyData.getCodeSeries());
        this.ki.setCuts(keyData.getCuts());
        this.ki.setTitle(keyData.getTitle());
        this.ki.setKeyChinaNum(keyData.getKeyChinaNum());
        String variableSpace = this.ki.getVariableSpace();
        String desc = this.ki.getDesc();
        if (!getKeyData().isCustomkey() && (!TextUtils.isEmpty(variableSpace) || !TextUtils.isEmpty(desc))) {
            new ChooseBittingNumbersDialog(getContext(), variableSpace, desc).show();
        }
        this.dataParam.setKeyInfo(keyInfo);
        if (MachineInfo.isE20Us(getContext()) && (keyInfo.getIcCard() == 252 || keyInfo.getIcCard() == 894)) {
            showAngleKeyBlankRemind();
        }
        if (this.ki.getType() == 7 || this.ki.getType() == 8 || this.ki.isDimpleMotherSonKey()) {
            this.tvAdjust.setVisibility(8);
        }
    }

    private void showAngleKeyBlankRemind() {
        RemindDialog remindDialog = new RemindDialog(getContext());
        remindDialog.show();
        remindDialog.setCancleBtnVisibility(false);
        remindDialog.setRemindImg(R.drawable.angle_key_remind);
    }

    private void initSiblingKey(KeyInfo keyInfo) {
        if (getKeyData().isCustomkey()) {
            this.btChangedSibling.setVisibility(8);
            return;
        }
        if (TextUtils.isEmpty(keyInfo.getSiblingProfile()) && !this.isSibling && keyInfo.getKeyitemid() == 0 && TextUtils.isEmpty(this.ki.getKeyComb())) {
            this.btChangedSibling.setVisibility(8);
            return;
        }
        this.tvSide.setVisibility(0);
        if (!TextUtils.isEmpty(this.ki.getKeyComb())) {
            for (String str : this.ki.getKeyComb().split(",")) {
                String[] split = str.split("-");
                if (split.length > 1 && this.ki.getIcCard() == Integer.parseInt(split[1])) {
                    this.tvSide.setText(getString(R.string.side) + " " + split[0]);
                }
            }
        } else if (!TextUtils.isEmpty(keyInfo.getSiblingProfile()) || keyInfo.getKeyitemid() != 0) {
            this.tvSide.setText(R.string.a_side);
        } else {
            this.tvSide.setText(R.string.b_side);
        }
        if (TextUtils.isEmpty(this.ki.getReadBittingRule()) || !"3".equals(this.ki.getReadBittingRule())) {
            return;
        }
        this.cutCount = 0;
    }

    private void initCutter(KeyInfo keyInfo) {
        int cutterSize = keyInfo.getCutterSize();
        if (cutterSize == 0) {
            if (this.ki.getType() == 5) {
                if (this.ki.getGroove() != 0 && this.ki.getGroove() < 200) {
                    cutterSize = this.ki.getGroove();
                }
                cutterSize = 200;
            } else if (this.ki.getType() == 0) {
                cutterSize = SPUtils.getInt(SpKeys.DOUBLE_KEY_CUTTER, 200);
            } else {
                if (this.ki.getType() == 6) {
                    cutterSize = ToolSizeManager.DimpleCutterSize;
                }
                cutterSize = 200;
            }
        }
        this.dataParam.setCutterSize(cutterSize);
        ToolSizeManager.setCutterSize(cutterSize);
        if (this.ki.getType() == 6 || this.ki.getType() == 92) {
            if (MachineInfo.isE20Us(getContext())) {
                if (this.ki.isDimpleMotherSonKey()) {
                    this.tvCutterSize.setText("D&E cutter");
                    return;
                } else {
                    this.tvCutterSize.setText("C cutter");
                    return;
                }
            }
            this.tvCutterSize.setText(R.string.dimple_cutter);
            return;
        }
        this.tvCutterSize.setText(String.format(Locale.US, "%.1fmm", Float.valueOf(cutterSize / 100.0f)));
    }

    private void initDecoder(KeyInfo keyInfo) {
        int i;
        String decoder = keyInfo.getDecoder();
        if (!TextUtils.isEmpty(decoder)) {
            if (decoder.contains(",")) {
                String[] split = decoder.split(",");
                this.tvDecoderSize.setText(split[1] + "mm");
                i = (int) (Float.parseFloat(split[1]) * 100.0f);
                this.dataParam.setDecoderSize(i);
                ToolSizeManager.setDecoderSize(i);
            }
        } else {
            this.tvDecoderSize.setText("1.0mm");
        }
        i = 100;
        this.dataParam.setDecoderSize(i);
        ToolSizeManager.setDecoderSize(i);
    }

    private void initParam(KeyInfo keyInfo) {
        initDecoder(keyInfo);
        initCutter(keyInfo);
        initCutDepth(keyInfo);
    }

    private void initCutDepth(KeyInfo keyInfo) {
        this.dataParam.setCutDepth(keyInfo.getCutDepth());
    }

    private void initClamp(KeyInfo keyInfo) {
        if (TextUtils.equals(keyInfo.getClampBean().getClampNum(), "S1") && MachineInfo.isE20Standard(getContext())) {
            String str = TextUtils.equals(keyInfo.getClampBean().getClampSide(), "A") ? "Please make sure the key is fixed on S1-A" : "";
            if (TextUtils.equals(keyInfo.getClampBean().getClampSide(), "C")) {
                str = "Make sure the key is fixed on S1-C";
            }
            if (!TextUtils.isEmpty(str)) {
                new AlertDialog.Builder(getContext()).setMessage(str).setPositiveButton(R.string.ok, (DialogInterface.OnClickListener) null).show();
            }
        }
        final List<ClampDisplayBean> clampBeanList = ClampCreator.getClampBeanList(keyInfo, this.lastClamp);
        this.ki.setClampKeyBasicData(clampBeanList.get(0).getClampBean());
        this.ivSwitchLast.setVisibility(8);
        if (clampBeanList.size() > 1) {
            this.ivSwitchNext.setVisibility(0);
        } else {
            this.ivSwitchNext.setVisibility(8);
        }
        this.vpClamp.setAdapter(new ClampSwitchPagerAdapter(clampBeanList, getContext()));
        ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment.4
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                KeyOperateFragment.this.ki.setClampKeyBasicData(((ClampDisplayBean) clampBeanList.get(i)).getClampBean());
                if (i == 0) {
                    KeyOperateFragment.this.ivSwitchLast.setVisibility(8);
                    KeyOperateFragment.this.ivSwitchNext.setVisibility(0);
                } else if (i == clampBeanList.size() - 1) {
                    KeyOperateFragment.this.ivSwitchLast.setVisibility(0);
                    KeyOperateFragment.this.ivSwitchNext.setVisibility(8);
                } else {
                    KeyOperateFragment.this.ivSwitchLast.setVisibility(0);
                    KeyOperateFragment.this.ivSwitchNext.setVisibility(0);
                }
            }
        };
        this.onPageChangeListener = onPageChangeListener;
        this.vpClamp.addOnPageChangeListener(onPageChangeListener);
    }

    /* loaded from: classes.dex */
    private class MyPagerAdapter extends PagerAdapter {
        List<ImageView> imageViewList = new ArrayList();
        int[] imgRes;

        @Override // androidx.viewpager.widget.PagerAdapter
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public MyPagerAdapter(int[] iArr) {
            this.imgRes = iArr;
            for (int i : iArr) {
                ImageView imageView = new ImageView(KeyOperateFragment.this.getContext());
                imageView.setImageResource(i);
                imageView.setPadding(5, 5, 5, 5);
                this.imageViewList.add(imageView);
            }
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            return this.imgRes.length;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) obj);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public float getPageWidth(int i) {
            if (this.imgRes.length > 1) {
                return 0.8f;
            }
            return super.getPageWidth(i);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public Object instantiateItem(ViewGroup viewGroup, int i) {
            ImageView imageView = this.imageViewList.get(i);
            viewGroup.addView(imageView);
            return imageView;
        }

        public View getViewByPosition(int i) {
            return this.imageViewList.get(i);
        }
    }

    private int[] getClampImg(KeyInfo keyInfo) {
        if (keyInfo.getIcCard() == 20131 || keyInfo.getIcCard() == 1915) {
            return new int[]{R.drawable.car_clamp_d_tip_20131};
        }
        if (keyInfo.getShoulderBlock() == 1) {
            return new int[]{R.drawable.car_clamp_d_shoulder_6620131};
        }
        ClampBean clampBean = this.ki.getClampBean();
        if ("S1".equals(clampBean.getClampNum())) {
            if ("A".equals(clampBean.getClampSide())) {
                if ("0".equals(clampBean.getClampSlot())) {
                    return keyInfo.getAlign() == 0 ? new int[]{R.drawable.car_clamp_a_shoulder, R.drawable.car_clamp_b_shoulder} : new int[]{R.drawable.car_clamp_a_tip, R.drawable.car_clamp_b_tip};
                }
                return null;
            }
            if ("B".equals(clampBean.getClampSide())) {
                return "0".equals(clampBean.getClampSlot()) ? keyInfo.getAlign() == 0 ? new int[]{R.drawable.car_clamp_b_shoulder, R.drawable.car_clamp_a_shoulder} : new int[]{R.drawable.car_clamp_b_tip, R.drawable.car_clamp_a_tip} : keyInfo.getAlign() == 0 ? new int[]{R.drawable.car_clamp_b_shoulder_side} : new int[]{R.drawable.car_clamp_b_tip_side};
            }
            if ("C".equals(clampBean.getClampSide())) {
                if ("0".equals(clampBean.getClampSlot())) {
                    return keyInfo.getAlign() == 0 ? new int[]{R.drawable.car_clamp_c_shoulder, R.drawable.car_clamp_d_shoulder} : Integer.parseInt(keyInfo.getSpaceStr().split(";")[0].split(",")[0]) + ErrorCode.keyCuttingError > 3000 ? new int[]{R.drawable.car_clamp_c_long_tip, R.drawable.car_clamp_d_tip} : new int[]{R.drawable.car_clamp_c_tip, R.drawable.car_clamp_d_tip};
                }
                return null;
            }
            if ("D".equals(clampBean.getClampSide()) && "0".equals(clampBean.getClampSlot())) {
                return keyInfo.getAlign() == 0 ? new int[]{R.drawable.car_clamp_d_shoulder, R.drawable.car_clamp_c_shoulder} : this.ki.getIcCard() == 852 ? new int[]{R.drawable.car_clamp_d_tip, R.drawable.sx9_clamp_side_a} : this.ki.getIcCard() == 1047 ? new int[]{R.drawable.car_clamp_d_tip, R.drawable.sx9_clamp_side_b} : new int[]{R.drawable.car_clamp_d_tip, R.drawable.car_clamp_c_tip};
            }
            return null;
        }
        if ("S2".equals(clampBean.getClampNum())) {
            return "A".equals(clampBean.getClampSide()) ? keyInfo.getAlign() == 0 ? new int[]{R.drawable.singlekey_clamp_a_shoulder, R.drawable.singlekey_clamp_b_shoulder} : new int[]{R.drawable.singlekey_clamp_a_tip, R.drawable.singlekey_clamp_b_tip} : keyInfo.getAlign() == 0 ? new int[]{R.drawable.singlekey_clamp_b_shoulder, R.drawable.singlekey_clamp_a_shoulder} : new int[]{R.drawable.singlekey_clamp_b_tip, R.drawable.singlekey_clamp_a_tip};
        }
        if ("S3".equals(clampBean.getClampNum())) {
            if ("A".equals(clampBean.getClampSide())) {
                return new int[]{R.drawable.tubular_clamp_s3_s7};
            }
            return null;
        }
        if ("S4".equals(clampBean.getClampNum())) {
            if ("A".equals(clampBean.getClampSide())) {
                return new int[]{R.drawable.anglekey_clamp};
            }
            return null;
        }
        if ("S6".equals(clampBean.getClampNum())) {
            return "A".equals(clampBean.getClampSide()) ? new int[]{R.drawable.sx9_clamp_side_a, R.drawable.car_clamp_d_tip} : new int[]{R.drawable.sx9_clamp_side_b, R.drawable.car_clamp_d_tip};
        }
        return null;
    }

    private void checkKeyCollected() {
        if (getKeyData().isCustomkey()) {
            this.tvCollect.setVisibility(8);
        }
    }

    private void initKeyView(KeyInfo keyInfo) {
        this.flKeyview.removeAllViews();
        int type = keyInfo.getType();
        if (type != 92) {
            switch (type) {
                case 0:
                    DoubleKey doubleKey = new DoubleKey(getContext(), keyInfo);
                    this.mKey = doubleKey;
                    this.flKeyview.addView(doubleKey);
                    break;
                case 1:
                    SingleKey singleKey = new SingleKey(getContext(), keyInfo);
                    this.mKey = singleKey;
                    this.flKeyview.addView(singleKey);
                    break;
                case 2:
                    DoubleInsideGrooveKey doubleInsideGrooveKey = new DoubleInsideGrooveKey(getContext(), keyInfo);
                    this.mKey = doubleInsideGrooveKey;
                    this.flKeyview.addView(doubleInsideGrooveKey);
                    break;
                case 3:
                    SingleOutGrooveKey singleOutGrooveKey = new SingleOutGrooveKey(getContext(), keyInfo);
                    this.mKey = singleOutGrooveKey;
                    this.flKeyview.addView(singleOutGrooveKey);
                    break;
                case 4:
                    DoubleOutsideKey doubleOutsideKey = new DoubleOutsideKey(getContext(), keyInfo);
                    this.mKey = doubleOutsideKey;
                    this.flKeyview.addView(doubleOutsideKey);
                    break;
                case 5:
                    SigleInsideGrooveKey sigleInsideGrooveKey = new SigleInsideGrooveKey(getContext(), keyInfo);
                    this.mKey = sigleInsideGrooveKey;
                    this.flKeyview.addView(sigleInsideGrooveKey);
                    break;
                case 6:
                    DimpleKey dimpleKey = new DimpleKey(getContext(), keyInfo);
                    this.mKey = dimpleKey;
                    this.flKeyview.addView(dimpleKey);
                    break;
                case 7:
                    AngleKey angleKey = new AngleKey(getContext(), keyInfo);
                    this.mKey = angleKey;
                    this.flKeyview.addView(angleKey);
                    this.btDecode.setEnabled(false);
                    break;
                case 8:
                    TubularKey tubularKey = new TubularKey(getContext(), keyInfo);
                    this.mKey = tubularKey;
                    this.flKeyview.addView(tubularKey);
                    break;
                case 9:
                    SideToothKey sideToothKey = new SideToothKey(getContext(), keyInfo);
                    this.mKey = sideToothKey;
                    this.flKeyview.addView(sideToothKey);
                    this.btDecode.setEnabled(false);
                    break;
            }
        } else {
            Side3KsKey side3KsKey = new Side3KsKey(getContext(), keyInfo);
            this.mKey = side3KsKey;
            this.flKeyview.addView(side3KsKey);
        }
        Key key = this.mKey;
        if (key != null) {
            key.setPadding(AutoUtils.getPercentWidthSize(20), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentWidthSize(20), AutoUtils.getPercentHeightSize(20));
        }
    }

    @OnClick({R.id.tv_input, R.id.tv_code_find_tooth, R.id.tv_lack_tooth, R.id.fl_key_view, R.id.iv_scale, R.id.iv_key_scale, R.id.iv_switch_last, R.id.iv_switch_next, R.id.bt_decode, R.id.bt_cut, R.id.tv_info, R.id.tv_collect, R.id.bt_change_sibling, R.id.tv_move, R.id.tv_adjust})
    public void onViewClicked(View view) {
        int i;
        String str;
        switch (view.getId()) {
            case R.id.bt_change_sibling /* 2131361914 */:
                this.lastClamp = this.ki.getClampBean();
                if (!TextUtils.isEmpty(this.ki.getKeyComb())) {
                    String[] split = this.ki.getKeyComb().split(",");
                    for (int i2 = 0; i2 < split.length; i2++) {
                        String[] split2 = split[i2].split("-");
                        if (split2.length > 1 && this.ki.getIcCard() == Integer.parseInt(split2[1])) {
                            if (i2 < split.length - 1) {
                                str = split[i2 + 1].split("-")[1];
                            } else {
                                str = split[0].split("-")[1];
                            }
                            this.toothCodeMap.put(Integer.valueOf(this.ki.getIcCard()), this.ki.getKeyToothCode());
                            initKey(Integer.parseInt(str));
                        }
                    }
                    return;
                }
                if (!this.isSibling) {
                    if (!this.doorIgnition && !TextUtils.isEmpty(this.ki.getReadBittingRule()) && "2".equals(this.ki.getReadBittingRule())) {
                        String keyToothCode = this.ki.getKeyToothCode();
                        if (!TextUtils.isEmpty(keyToothCode)) {
                            String[] split3 = keyToothCode.split(";")[0].split(",");
                            String str2 = "";
                            for (int i3 = 0; i3 < split3.length; i3++) {
                                String str3 = split3[i3];
                                String str4 = "1";
                                if (str3.contains("?")) {
                                    str3 = "1";
                                }
                                if (split3.length != 8 && ((i3 < 3 || split3.length != 9) && (i3 < 4 || split3.length != 10))) {
                                    str4 = str3;
                                }
                                str2 = i3 == split3.length - 1 ? str2 + str4 + ";" : str2 + str4 + ",";
                            }
                            this.sideKeyToothCode = str2;
                        }
                    }
                    this.isSibling = true;
                    this.mainKeyID = this.ki.getIcCard();
                    this.mainKeyToothCode = this.ki.getKeyToothCode();
                    if (!TextUtils.isEmpty(this.ki.getSiblingProfile())) {
                        int parseInt = Integer.parseInt(this.ki.getSiblingProfile());
                        initKey(parseInt);
                        ResUpdateUtils.showKeyImage(getContext(), parseInt, this.ivRealKey);
                    }
                    if (this.ki.getKeyitemid() != 0) {
                        initKeySide(this.ki.getKeyitemid());
                        return;
                    }
                    return;
                }
                this.isSibling = false;
                if (this.cutCount != 1) {
                    this.sideKeyToothCode = this.ki.getKeyToothCode();
                }
                if (!TextUtils.isEmpty(this.ki.getSiblingProfile())) {
                    i = Integer.parseInt(this.ki.getSiblingProfile());
                } else {
                    i = this.mainKeyID;
                }
                initKey(i);
                ResUpdateUtils.showKeyImage(getContext(), i, this.ivRealKey);
                return;
            case R.id.bt_cut /* 2131361921 */:
                if (AppUtil.isApkInDebug(getContext()) || ClampManager.getInstance().checkHasCalibrated(this.ki)) {
                    new CutDialog(getActivity(), this.dataParam).show();
                    return;
                }
                return;
            case R.id.bt_decode /* 2131361922 */:
                if (AppUtil.isApkInDebug(getContext()) || ClampManager.getInstance().checkHasCalibrated(this.ki)) {
                    new DecodeDialog(getActivity(), this.dataParam).show();
                    return;
                }
                return;
            case R.id.fl_key_view /* 2131362204 */:
                if (MyApplication.getInstance().isShowRealDepth() || AppUtil.isApkInDebug(getContext())) {
                    ArrayList<Bitt> arrayList = this.bittList;
                    if (arrayList == null || arrayList.size() == 0) {
                        ToastUtil.showToast(getString(R.string.no_decode_data));
                        return;
                    } else {
                        start(LookRealDepthFragment.newInstance(getKeyData().getKeyID(), this.bittList, getKeyData().getTitle(), this.ki.getDepthStr(), this.ki.getDepthName(), KeyDataUtils.getCutsBySpace(this.ki.getSpaceStr())));
                        return;
                    }
                }
                return;
            case R.id.iv_key_scale /* 2131362309 */:
                LookPicActivity.start(getContext(), this.ki.getIcCard(), true);
                return;
            case R.id.iv_scale /* 2131362330 */:
                LookPicActivity.start(getContext(), ClampCreator.getClampZoomImg(this.ki));
                return;
            case R.id.iv_switch_last /* 2131362348 */:
                int currentItem = this.vpClamp.getCurrentItem();
                if (currentItem > 0) {
                    this.vpClamp.setCurrentItem(currentItem - 1);
                    return;
                }
                return;
            case R.id.iv_switch_next /* 2131362349 */:
                int currentItem2 = this.vpClamp.getCurrentItem();
                if (currentItem2 < this.vpClamp.getAdapter().getCount() - 1) {
                    this.vpClamp.setCurrentItem(currentItem2 + 1);
                    return;
                }
                return;
            case R.id.tv_adjust /* 2131362883 */:
                start(SizeAdjustFragment.newInstance(this.ki));
                return;
            case R.id.tv_code_find_tooth /* 2131362897 */:
                start(CodeFindToothFragment.newInstance(getKeyData().getKeyID(), getKeyData().getCodeSeries(), getKeyData().getIsn()));
                return;
            case R.id.tv_collect /* 2131362898 */:
                showEditDialog();
                return;
            case R.id.tv_info /* 2131362939 */:
                start(KeyInfoFragment.newInstance(this.ki));
                return;
            case R.id.tv_input /* 2131362940 */:
                start(ToothCodeInputFragment.newInstance(this.ki));
                return;
            case R.id.tv_lack_tooth /* 2131362950 */:
                start(LackToothFragment.newInstance(getKeyData().getKeyID(), this.ki, this.mainKeyToothCode, this.sideKeyToothCode, getKeyData().getCodeSeries(), this.toothCodeLack, this.bittingCodes));
                return;
            case R.id.tv_move /* 2131362963 */:
                showLoadingDialog(getString(R.string.waitting));
                if (this.moveToRight) {
                    this.moveToRight = false;
                    OperationManager.getInstance().sendOrder(Command.DecoderOperation(1, 0, (int) (6300.0f / MachineData.dXScale), 0, 0, ""), OperateType.MOVE_XYZ);
                    return;
                } else {
                    this.moveToRight = true;
                    OperationManager.getInstance().sendOrder(Command.DecoderOperation(1, 0, 1, 1, 1, ""), OperateType.MOVE_XYZ);
                    return;
                }
            default:
                return;
        }
    }

    private void showEditDialog() {
        EditDialog editDialog = new EditDialog(getContext());
        editDialog.setTip(getString(R.string.enter_remarks));
        editDialog.setDialogBtnCallback(new EditDialog.DialogInputFinishCallBack() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment$$ExternalSyntheticLambda0
            @Override // com.kkkcut.e20j.ui.dialog.EditDialog.DialogInputFinishCallBack
            public final void onDialogButClick(String str) {
                KeyOperateFragment.this.m33x473e13c1(str);
            }
        });
        editDialog.setContentNullable(true);
        editDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void savaCutHistory() {
        final CutHistoryData cutHistoryData = new CutHistoryData(getKeyData());
        if (!TextUtils.isEmpty(this.ki.getKeyComb())) {
            Set<Integer> keySet = this.toothCodeMap.keySet();
            StringBuilder sb = new StringBuilder();
            for (Integer num : keySet) {
                String[] split = this.ki.getKeyComb().split(",");
                if (split.length > 0 && TextUtils.equals(split[0].split("-")[1], String.valueOf(num))) {
                    cutHistoryData.setToothCode(TextUtils.isEmpty(this.toothCodeMap.get(num)) ? "" : this.toothCodeMap.get(num));
                }
                if (split.length > 1 && TextUtils.equals(split[1].split("-")[1], String.valueOf(num))) {
                    sb.append(num);
                    sb.append(":");
                    sb.append(TextUtils.isEmpty(this.toothCodeMap.get(num)) ? "" : this.toothCodeMap.get(num));
                    sb.append("|");
                }
                if (split.length > 2 && TextUtils.equals(split[2].split("-")[1], String.valueOf(num))) {
                    sb.append(num);
                    sb.append(":");
                    sb.append(TextUtils.isEmpty(this.toothCodeMap.get(num)) ? "" : this.toothCodeMap.get(num));
                    sb.append("|");
                }
                cutHistoryData.setToothCodeSide(sb.toString());
            }
        } else if (this.isSibling) {
            if (this.cutCount == 0) {
                cutHistoryData.setToothCode(this.mainKeyToothCode);
                cutHistoryData.setToothCodeSide(this.ki.getKeyToothCode());
            }
        } else {
            cutHistoryData.setToothCode(this.ki.getKeyToothCode());
        }
        cutHistoryData.setDoorIgnition(this.doorIgnition ? 1 : 0);
        cutHistoryData.setDoorToIgnition(this.doorToIgnition ? 1 : 0);
        addDisposable(Observable.fromCallable(new Callable<Boolean>() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment.6
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Boolean call() throws Exception {
                return Boolean.valueOf(UserDataDaoManager.getInstance(KeyOperateFragment.this.getContext()).saveCutHistory(cutHistoryData));
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Boolean>() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment.5
            @Override // io.reactivex.functions.Consumer
            public void accept(Boolean bool) throws Exception {
                if (bool.booleanValue()) {
                    return;
                }
                ToastUtil.showToast(R.string.cut_history_save_failed);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: collectKey, reason: merged with bridge method [inline-methods] */
    public void m33x473e13c1(String str) {
        String variableSpace;
        GoOperatBean keyData = getKeyData();
        keyData.setToothCode(this.ki.getKeyToothCode());
        keyData.setRemark(str);
        final CollectionData collectionData = new CollectionData(keyData);
        if (TextUtils.isEmpty(keyData.getCuts())) {
            if (TextUtils.isEmpty(this.ki.getVariableSpace())) {
                String[] split = this.ki.getSpaceStr().split(";");
                variableSpace = "";
                for (int i = 0; i < split.length; i++) {
                    variableSpace = i == 0 ? variableSpace + split[i].split(",").length : variableSpace + "-" + split[i].split(",").length;
                }
            } else {
                variableSpace = this.ki.getVariableSpace();
            }
            collectionData.setCuts(variableSpace);
        }
        addDisposable(Observable.fromCallable(new Callable<Boolean>() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment.8
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Boolean call() throws Exception {
                return Boolean.valueOf(UserDataDaoManager.getInstance(KeyOperateFragment.this.getContext()).collectKey(collectionData));
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Boolean>() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment.7
            @Override // io.reactivex.functions.Consumer
            public void accept(Boolean bool) throws Exception {
                if (bool.booleanValue()) {
                    ToastUtil.showToast(R.string.collection_completed);
                } else {
                    ToastUtil.showToast(R.string.collection_failed);
                }
            }
        }));
    }

    private void cancleCollect() {
        addDisposable(Observable.fromCallable(new Callable<Boolean>() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment.10
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Boolean call() throws Exception {
                return Boolean.valueOf(UserDataDaoManager.getInstance(KeyOperateFragment.this.getContext()).cancleCollect(KeyOperateFragment.this.getKeyData().getSeriesID()));
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Boolean>() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment.9
            @Override // io.reactivex.functions.Consumer
            public void accept(Boolean bool) throws Exception {
                if (bool.booleanValue()) {
                    KeyOperateFragment.this.tvCollect.setCompoundDrawablesWithIntrinsicBounds(KeyOperateFragment.this.getResources().getDrawable(R.drawable.collect_default), (Drawable) null, (Drawable) null, (Drawable) null);
                    KeyOperateFragment.this.tvCollect.setCompoundDrawablePadding(10);
                    return;
                }
                ToastUtil.showToast(R.string.failed_to_remove_favorites);
            }
        }));
    }

    @Override // com.kkkcut.e20j.base.BaseFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void onEventComing(EventCenter eventCenter) {
        float f;
        float f2;
        int eventCode = eventCenter.getEventCode();
        if (eventCode == 0) {
            Bundle bundle = (Bundle) eventCenter.getData();
            int i = bundle.getInt("row");
            int i2 = bundle.getInt("column");
            if (this.ki.getType() == 6 || this.ki.getType() == 8) {
                f = bundle.getInt("depth");
                f2 = MachineData.dZScale;
            } else {
                f = bundle.getInt("depth");
                f2 = MachineData.dXScale;
            }
            int i3 = (int) (f * f2);
            LogUtil.i(TAG, "depth: " + i3);
            if (!AppUtil.isApkInDebug(getContext()) && this.ki.getType() != 6 && this.ki.getType() != 1 && this.ki.getIcCard() != 601287) {
                i3 = fixDepth(i - 1, i3);
            }
            if (this.ki.isAngleDimple()) {
                i = this.dimpleCutIndex + 1;
                i3 = (int) (i3 * Math.cos(Math.toRadians(14.0d)));
            }
            LogUtil.i(TAG, "depthFix: " + i3);
            int i4 = i + (-1);
            float f3 = (float) i3;
            String changeSingleDepth = this.mKey.changeSingleDepth(i4, i2 + (-1), f3, this.rounding, this.ki.getReadBittingRule());
            this.ki.setKeyToothCode(changeSingleDepth);
            Map<Integer, String> map = this.toothCodeMap;
            if (map != null) {
                map.put(Integer.valueOf(this.ki.getIcCard()), changeSingleDepth);
            }
            String valueOf = String.valueOf(i);
            String valueOf2 = String.valueOf(i2);
            String valueOf3 = String.valueOf(i3);
            Key key = this.mKey;
            this.bittList.add(new Bitt(valueOf, valueOf2, valueOf3, key.getCodeByDepth(key.getAllDepths().get(i4), this.mKey.getAllDepthNames().get(i4), f3, this.rounding)));
            return;
        }
        if (eventCode == 1) {
            savaCutHistory();
            this.dataParam = (DataParam) ((Bundle) eventCenter.getData()).getParcelable("param");
            if (this.ki.getType() != 6 && this.ki.getType() != 92) {
                this.tvCutterSize.setText(String.format(Locale.US, "%.1fmm", Float.valueOf(this.dataParam.getCutterSize() / 100.0f)));
            }
            this.tvDecoderSize.setText(String.format(Locale.US, "%.1fmm", Float.valueOf(this.dataParam.getDecoderSize() / 100.0f)));
            ToolSizeManager.setCutterSize(this.dataParam.getCutterSize());
            ToolSizeManager.setDecoderSize(this.dataParam.getDecoderSize());
            if (this.ki.getType() != 7 && this.dataParam.isPlastic()) {
                this.btDecode.setVisibility(8);
            }
            if (this.dataParam.isPlastic() && TextUtils.equals(this.ki.getClampBean().getClampSide(), "A")) {
                showErrorDialog(getString(R.string.please_use_s1b_jaw), R.drawable.error_1, null);
                return;
            }
            if (this.ki.getType() == 7) {
                this.angleKeySteps = initAngleKeyCutSteps();
                showTurningDialog();
                return;
            }
            if (this.ki.isPlasticKey() && this.ki.getAlign() == 1) {
                showPlasticLocationDialog();
                return;
            }
            if (this.ki.isDimpleMotherSonKey()) {
                showDimpleOperationDialog();
                return;
            } else if (this.ki.isAngleDimple()) {
                showAngleDimpleInitDialog(1);
                return;
            } else {
                showClearClampRemind(1);
                return;
            }
        }
        if (eventCode == 2) {
            InputFinishBean inputFinishBean = (InputFinishBean) eventCenter.getData();
            this.doorIgnition = inputFinishBean.isDoorAndIgnition();
            this.doorToIgnition = inputFinishBean.isDoorToIgnition();
            String toothCode = inputFinishBean.getToothCode();
            this.toothCodeLack = inputFinishBean.getToothCodeLack();
            this.bittingCodes = inputFinishBean.getBittingCodes();
            String spaceStr = this.ki.getSpaceStr();
            if (this.ki.isNewHonda()) {
                String[] split = toothCode.split(";");
                String[] split2 = spaceStr.split(";");
                if (split.length > split2.length) {
                    String str = "";
                    String str2 = "";
                    for (int i5 = 0; i5 < split.length; i5++) {
                        if (i5 < split2.length) {
                            str = str + split[i5];
                        } else {
                            str2 = str2 + split[i5];
                        }
                    }
                    if (!this.isSibling) {
                        if (!TextUtils.isEmpty(str)) {
                            this.mainKeyToothCode = str;
                            toothCode = str;
                        }
                        if (!TextUtils.isEmpty(str2)) {
                            this.sideKeyToothCode = str2;
                        }
                    } else {
                        if (!TextUtils.isEmpty(str)) {
                            this.mainKeyToothCode = str;
                        }
                        if (!TextUtils.isEmpty(str2)) {
                            this.sideKeyToothCode = str2;
                            toothCode = str2;
                        }
                    }
                }
            } else if ((this.isSibling && this.cutCount != 1) || this.doorIgnition) {
                this.sideKeyToothCode = toothCode;
            }
            this.ki.setKeyToothCode(toothCode);
            this.mKey.setToothCodeAndInvalidate(toothCode);
            Map<Integer, String> map2 = this.toothCodeMap;
            if (map2 != null) {
                map2.put(Integer.valueOf(this.ki.getIcCard()), toothCode);
                return;
            }
            return;
        }
        if (eventCode == 9) {
            int intValue = ((Integer) eventCenter.getData()).intValue();
            this.toothCount = intValue;
            this.ki.setSpaceStr(reduceCount(intValue, this.mKey.getAllSpaces()));
            this.ki.setSpaceWidthStr(reduceCount(this.toothCount, this.mKey.getAllSpaceWidths()));
            initKeyView(this.ki);
            return;
        }
        if (eventCode == 14) {
            dismissLoadingDialog();
            return;
        }
        if (eventCode == 39) {
            DataParam dataParam = (DataParam) ((Bundle) eventCenter.getData()).getParcelable("param");
            this.dataParam = dataParam;
            ToolSizeManager.setDecoderSize(dataParam.getDecoderSize());
            this.tvDecoderSize.setText(String.format(Locale.US, "%.1fmm", Float.valueOf(this.dataParam.getDecoderSize() / 100.0f)));
            this.rounding = this.dataParam.isRound();
            showClearClampRemind(39);
            return;
        }
        if (eventCode == 47) {
            showLoadingDialog(((Integer) eventCenter.getData()).intValue() + "%", true);
            return;
        }
        if (eventCode == 57) {
            initKeyView(this.ki);
            return;
        }
        if (eventCode == 49) {
            putUpDecoderRemindDialog();
            return;
        }
        if (eventCode != 50) {
            switch (eventCode) {
                case 32:
                    OperateType operateType = (OperateType) eventCenter.getData();
                    if (operateType == OperateType.KEY_BLANK_DECODE_LOCATION) {
                        this.bittList.clear();
                        if (this.ki.isAngleDimple()) {
                            showAngleDimpleTurnDialog(0);
                            return;
                        }
                        return;
                    }
                    if (operateType == OperateType.KEY_BLANK_DECODE_EXECUTE) {
                        if (this.isBarCodeScan && MachineInfo.isE20Us(getContext())) {
                            this.llInput.setVisibility(8);
                            this.llCodeFindTooth.setVisibility(8);
                            this.llLackTooth.setVisibility(8);
                            this.tvMove.setVisibility(8);
                        }
                        if (this.ki.getIcCard() == 601287) {
                            fix3KsKeyTooth();
                        }
                        if (this.ki.isAngleDimple()) {
                            this.dimpleCutIndex++;
                            showAngleDimpleTurnDialog(0);
                        }
                        savaCutHistory();
                        toothFindCode();
                        dismissLoadingDialog();
                        return;
                    }
                    if (operateType == OperateType.KEY_BLANK_CUT_LOCATION) {
                        return;
                    }
                    if (operateType == OperateType.KEY_BLANK_CUT_CUTTER_HIGH) {
                        if (this.ki.getType() == 7) {
                            showTurningDialog();
                            return;
                        } else {
                            if (this.ki.isAngleDimple()) {
                                showAngleDimpleTurnDialog(1);
                                return;
                            }
                            return;
                        }
                    }
                    if (operateType == OperateType.KEY_BLANK_CUT_EXECUTE) {
                        addDisposable(Observable.timer(500L, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment.11
                            @Override // io.reactivex.functions.Consumer
                            public void accept(Long l) throws Exception {
                                KeyOperateFragment.this.dismissLoadingDialog();
                            }
                        }));
                        showLoadingDialog("100%", true);
                        if (this.ki.getType() == 7) {
                            List<AngleKeyStep> list = this.angleKeySteps;
                            if (list != null && this.angleKeyCutIndex < list.size()) {
                                showTurningDialog();
                            } else {
                                this.angleKeyCutIndex = 0;
                                savaCutHistory();
                                saveCutNumber();
                                OperationManager.getInstance().sendOrder(Command.TurnOffSpindle(), OperateType.CLAMP_RESET);
                            }
                        } else if (this.ki.getType() == 6 && this.ki.getSpaceWidthStr().contains("-")) {
                            this.dimpleCutIndex++;
                            showDimpleOperationDialog();
                        } else if (this.ki.isAngleDimple()) {
                            this.dimpleCutIndex++;
                            showAngleDimpleTurnDialog(1);
                        } else {
                            hu162tChangeSide();
                            savaCutHistory();
                            saveCutNumber();
                        }
                        CutCountHelper.getInstance().addCutCount(this.ki);
                        return;
                    }
                    if (operateType == OperateType.MOVE_XYZ) {
                        dismissLoadingDialog();
                        return;
                    }
                    return;
                case 33:
                    dismissLoadingDialog();
                    this.angleKeyCutIndex = 0;
                    this.dimpleCutIndex = 0;
                    showErrorDialog(getContext(), (ErrorBean) eventCenter.getData());
                    return;
                case 34:
                    showLoadingDialog(getString(R.string.waitting));
                    return;
                default:
                    return;
            }
        }
        putDownDecoderRemindDialog(((Integer) eventCenter.getData()).intValue());
    }

    private void toothFindCode() {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.ki.getToothCodeArray().size(); i++) {
            List<String> list = this.ki.getToothCodeArray().get(i);
            for (int i2 = 0; i2 < list.size(); i2++) {
                sb.append(KeyDataUtils.getToothCodeRound(list.get(i2), this.ki.getDepthNameList().get(i)));
            }
            if (i != this.ki.getToothCodeArray().size() - 1) {
                sb.append("-");
            }
        }
        addDisposable(Observable.fromCallable(new Callable<List<BittingCode>>() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment.14
            @Override // java.util.concurrent.Callable
            public List<BittingCode> call() throws Exception {
                return new ToothCodeDaoManager(KeyOperateFragment.this.ki.getIcCard()).lackTooth(sb.toString().replace("?", "_"));
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<BittingCode>>() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment.12
            @Override // io.reactivex.functions.Consumer
            public void accept(List<BittingCode> list2) throws Exception {
                if (list2.size() > 0) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(KeyOperateFragment.this.getString(R.string.code));
                    sb2.append(":");
                    for (int i3 = 0; i3 < list2.size(); i3++) {
                        sb2.append(list2.get(i3).getCode());
                        if (i3 != list2.size() - 1) {
                            sb2.append(",");
                        }
                    }
                    KeyOperateFragment.this.tvCode.setText(sb2.toString());
                }
            }
        }, new Consumer<Throwable>() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment.13
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                Log.i(KeyOperateFragment.TAG, "throwable: " + th.getMessage());
            }
        }));
    }

    private void fix3KsKeyTooth() {
        addDisposable(Observable.fromCallable(new Callable<String>() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment.16
            @Override // java.util.concurrent.Callable
            public String call() throws Exception {
                String str = (String) KeyOperateFragment.this.toothCodeMap.get(1287);
                List<List<String>> toothCodeArray = KeyOperateFragment.this.ki.getToothCodeArray();
                int i = 0;
                List<String> list = toothCodeArray.get(0);
                List<String> list2 = toothCodeArray.get(1);
                if (!TextUtils.isEmpty(str)) {
                    KeyInfo keyInfo = KeyInfoDaoManager.getInstance().getBasicData(1287).toKeyInfo();
                    String[] split = str.split(";")[0].split(",");
                    int i2 = 0;
                    while (i2 < split.length) {
                        String str2 = split[i2];
                        String str3 = list.get(i2);
                        String str4 = list2.get(i2);
                        int depthByCode = KeyOperateFragment.this.ki.getDepthByCode(KeyOperateFragment.this.ki.getDepthList().get(i), KeyOperateFragment.this.ki.getDepthNameList().get(i), str3);
                        String[] strArr = split;
                        int width = (KeyOperateFragment.this.ki.getWidth() - KeyOperateFragment.this.ki.getGroove()) - KeyOperateFragment.this.ki.getDepthByCode(KeyOperateFragment.this.ki.getDepthList().get(1), KeyOperateFragment.this.ki.getDepthNameList().get(1), str4);
                        i = 0;
                        int depthByCode2 = keyInfo.getDepthByCode(keyInfo.getDepthList().get(0), keyInfo.getDepthNameList().get(0), str2);
                        if (Math.abs(depthByCode2 - depthByCode) > Math.abs(depthByCode2 - width)) {
                            list2.set(i2, list.get(i2));
                        } else {
                            list.set(i2, list2.get(i2));
                        }
                        i2++;
                        split = strArr;
                    }
                } else {
                    int i3 = 0;
                    while (i3 < list.size()) {
                        String str5 = list.get(i3);
                        String str6 = list2.get(i3);
                        String toothCodeDec = KeyOperateFragment.this.getToothCodeDec(str5);
                        String toothCodeDec2 = KeyOperateFragment.this.getToothCodeDec(str6);
                        double abs = Math.abs(Float.parseFloat(toothCodeDec) - 0.5d);
                        float parseFloat = Float.parseFloat(toothCodeDec2);
                        List<String> list3 = list;
                        if (abs > Math.abs(parseFloat - 0.5d)) {
                            list2.set(i3, list3.get(i3));
                        } else {
                            list3.set(i3, list2.get(i3));
                        }
                        i3++;
                        list = list3;
                    }
                }
                StringBuilder sb = new StringBuilder();
                for (int i4 = 0; i4 < toothCodeArray.size(); i4++) {
                    List<String> list4 = toothCodeArray.get(i4);
                    for (int i5 = 0; i5 < list4.size(); i5++) {
                        sb.append(list4.get(i5));
                        if (i5 == list4.size() - 1) {
                            sb.append(";");
                        } else {
                            sb.append(",");
                        }
                    }
                }
                return sb.toString();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment.15
            @Override // io.reactivex.functions.Consumer
            public void accept(String str) throws Exception {
                KeyOperateFragment.this.mKey.setToothCodeAndInvalidate(str);
                KeyOperateFragment.this.ki.setKeyToothCode(str);
                KeyOperateFragment.this.savaCutHistory();
            }
        }));
    }

    public String getToothCodeDec(String str) {
        return str.contains(FileUtil.FILE_EXTENSION_SEPARATOR) ? str.substring(str.indexOf(FileUtil.FILE_EXTENSION_SEPARATOR), str.indexOf(FileUtil.FILE_EXTENSION_SEPARATOR) + 2) : ".0";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDimpleOperationDialog() {
        int i = this.dimpleCutIndex;
        if (i == 0) {
            showDimpleKeyChangeCutterDialog();
            return;
        }
        if (i == 1) {
            showDimpleKeyTurnOverDialog();
            return;
        }
        if (i == 2) {
            showDimpleKeyChangeCutterDialog();
        } else if (i == 3) {
            showDimpleKeyTurnOverDialog();
        } else {
            this.dimpleCutIndex = 0;
        }
    }

    private void showDimpleKeyChangeCutterDialog() {
        AnglekeyTurningDialog anglekeyTurningDialog = new AnglekeyTurningDialog(getContext());
        if (this.dimpleCutIndex == 0) {
            anglekeyTurningDialog.setRemindMsg(getString(R.string.dimple_cut_remind_1));
        } else {
            anglekeyTurningDialog.setRemindMsg(getString(R.string.dimple_cut_remind_3));
        }
        if (this.dimpleCutIndex == 0) {
            anglekeyTurningDialog.setRemindImg(R.drawable.remind_cutter_dimple_2);
        } else {
            anglekeyTurningDialog.setRemindImg(R.drawable.remind_cutter_dimple_1);
        }
        anglekeyTurningDialog.setDialogBtnCallback(new AnglekeyTurningDialog.DialogBtnCallBack() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment.17
            @Override // com.kkkcut.e20j.ui.dialog.AnglekeyTurningDialog.DialogBtnCallBack
            public void onDialogButClick(int i) {
                if (i == 0) {
                    KeyOperateFragment.this.dimpleCutIndex = 0;
                    KeyOperateFragment.this.dismissLoadingDialog();
                    OperationManager.getInstance().sendOrder(Command.TurnOffSpindle(), OperateType.STOP);
                } else if (i == 1) {
                    KeyOperateFragment.access$608(KeyOperateFragment.this);
                    KeyOperateFragment.this.showDimpleOperationDialog();
                } else {
                    KeyOperateFragment keyOperateFragment = KeyOperateFragment.this;
                    keyOperateFragment.showLoadingDialog(keyOperateFragment.getString(R.string.cutting), true);
                    KeyOperateFragment.this.startCut();
                }
            }
        });
        anglekeyTurningDialog.show();
    }

    private void showDimpleKeyTurnOverDialog() {
        AnglekeyTurningDialog anglekeyTurningDialog = new AnglekeyTurningDialog(getContext());
        if (this.dimpleCutIndex == 1) {
            anglekeyTurningDialog.setRemindMsg(getString(R.string.dimple_cut_remind_2));
            anglekeyTurningDialog.setRemindImg(R.drawable.turn_over_dimple_1);
        } else {
            anglekeyTurningDialog.setRemindMsg(getString(R.string.dimple_cut_remind_4));
            anglekeyTurningDialog.setRemindImg(R.drawable.turn_over_dimple_2);
        }
        anglekeyTurningDialog.setDialogBtnCallback(new AnglekeyTurningDialog.DialogBtnCallBack() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment.18
            @Override // com.kkkcut.e20j.ui.dialog.AnglekeyTurningDialog.DialogBtnCallBack
            public void onDialogButClick(int i) {
                if (i == 0) {
                    KeyOperateFragment.this.dimpleCutIndex = 0;
                    KeyOperateFragment.this.dismissLoadingDialog();
                    OperationManager.getInstance().sendOrder(Command.TurnOffSpindle(), OperateType.STOP);
                } else if (i == 1) {
                    KeyOperateFragment.access$608(KeyOperateFragment.this);
                    KeyOperateFragment.this.showDimpleOperationDialog();
                } else {
                    KeyOperateFragment keyOperateFragment = KeyOperateFragment.this;
                    keyOperateFragment.showLoadingDialog(keyOperateFragment.getString(R.string.cutting), true);
                    KeyOperateFragment.this.startCut();
                }
            }
        });
        anglekeyTurningDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startCut() {
        String str;
        if (this.ki.isDimpleMotherSonKey()) {
            int i = this.dimpleCutIndex;
            if (i == 0) {
                this.dataParam.setCutDimpleSonOrMother(1);
            } else if (i == 1) {
                this.dataParam.setCutDimpleSonOrMother(1);
            } else if (i == 2) {
                this.dataParam.setCutDimpleSonOrMother(2);
            } else if (i == 3) {
                this.dataParam.setCutDimpleSonOrMother(2);
            }
        }
        if (this.doorIgnition && !TextUtils.isEmpty(this.ki.getReadBittingRule())) {
            if ("2".equals(this.ki.getReadBittingRule())) {
                str = this.mKey.getDoorIgnitionFrontDepthStr(OperateType.CUT, this.doorToIgnition);
            } else if (this.cutCount == 0) {
                str = this.mKey.getDoorIgnitionSideBDepthStr(OperateType.CUT, this.doorToIgnition);
            } else {
                str = this.mKey.getDoorIgnitionSideCDepthStr(OperateType.CUT, this.doorToIgnition);
            }
        } else if (TextUtils.isEmpty(this.ki.getReadBittingRule()) || !"3".equals(this.ki.getReadBittingRule())) {
            str = "";
        } else if (this.cutCount == 0) {
            str = this.mKey.getHu162tSideBDepthStr(OperateType.CUT);
        } else {
            str = this.mKey.getHu162tSideCDepthStr(OperateType.CUT);
        }
        this.dataParam.setToothCodeReal(str);
        CuttingMachine.getInstance().cut(this.dataParam);
        showLoadingDialog(getString(R.string.cutting), true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startDecode() {
        showLoadingDialog(getString(R.string.decoding), true);
        CuttingMachine.getInstance().decode(this.dataParam);
    }

    private List<AngleKeyStep> initAngleKeyCutSteps() {
        String keyToothCode = this.ki.getKeyToothCode();
        if (TextUtils.isEmpty(keyToothCode)) {
            return null;
        }
        String[] split = keyToothCode.split(";")[0].split(",");
        String[] split2 = this.ki.getSpaceStr().split(";")[0].split(",");
        TreeMap treeMap = new TreeMap();
        String findNoCutDepth = findNoCutDepth();
        for (int i = 0; i < split.length; i++) {
            if (!TextUtils.equals(findNoCutDepth, split[i])) {
                if (treeMap.containsKey(split[i])) {
                    treeMap.put(split[i], ((String) treeMap.get(split[i])) + split2[i] + ",");
                } else {
                    treeMap.put(split[i], split2[i] + ",");
                }
            }
        }
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : treeMap.entrySet()) {
            arrayList.add(new AngleKeyStep((String) entry.getKey(), (String) entry.getValue()));
        }
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new AngleKeyStep(ANGLE_KEY_INIT, ""));
        arrayList2.addAll(arrayList);
        if (TextUtils.equals(this.ki.getClampBean().getClampNum(), "S9") && (TextUtils.equals(this.ki.getClampBean().getClampSide(), "C") || TextUtils.equals(this.ki.getClampBean().getClampSide(), "D"))) {
            return arrayList2;
        }
        if (this.ki.getFace() == 4) {
            arrayList2.addAll(arrayList);
        }
        arrayList2.add(new AngleKeyStep(ANGLE_KEY_TURN_OVER, ""));
        arrayList2.addAll(arrayList);
        if (this.ki.getFace() == 4) {
            arrayList2.addAll(arrayList);
        }
        return arrayList2;
    }

    private String findNoCutDepth() {
        try {
            int indexOf = this.ki.getDepthList().get(0).indexOf(0);
            return indexOf >= 0 ? this.ki.getDepthNameList().get(0).get(indexOf) : "";
        } catch (Exception e) {
            ToastUtil.showToast(e.getMessage());
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void detectCutterHigh() {
        CuttingMachine.getInstance().cutLocationCutter(this.dataParam);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showTurningDialog() {
        int betaAnglekeyTurningImg;
        List<AngleKeyStep> list = this.angleKeySteps;
        if (list == null) {
            return;
        }
        if (this.angleKeyCutIndex >= list.size()) {
            this.angleKeyCutIndex = 0;
            dismissLoadingDialog();
            OperationManager.getInstance().sendOrder(Command.TurnOffSpindle(), OperateType.STOP);
            return;
        }
        final AngleKeyStep angleKeyStep = this.angleKeySteps.get(this.angleKeyCutIndex);
        if (TextUtils.equals(angleKeyStep.getTooth(), ANGLE_KEY_INIT)) {
            showAngleKeyInitDialog();
            return;
        }
        if (TextUtils.equals(angleKeyStep.getTooth(), ANGLE_KEY_TURN_OVER)) {
            showTurnOverDialog();
            return;
        }
        AnglekeyTurningDialog anglekeyTurningDialog = new AnglekeyTurningDialog(getContext());
        anglekeyTurningDialog.setRemindMsg(getString(R.string.turn_key_to_n, angleKeyStep.getTooth()));
        if (CuttingMachine.getInstance().isE9()) {
            betaAnglekeyTurningImg = getE9AnglekeyTurningImg();
        } else if (TextUtils.equals("S9", this.ki.getClampBean().getClampNum())) {
            betaAnglekeyTurningImg = getAbusAngleKeyTurningImg();
        } else {
            betaAnglekeyTurningImg = getBetaAnglekeyTurningImg();
        }
        anglekeyTurningDialog.setRemindImg(betaAnglekeyTurningImg);
        anglekeyTurningDialog.setDialogBtnCallback(new AnglekeyTurningDialog.DialogBtnCallBack() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment.19
            @Override // com.kkkcut.e20j.ui.dialog.AnglekeyTurningDialog.DialogBtnCallBack
            public void onDialogButClick(int i) {
                if (i == 0) {
                    KeyOperateFragment.this.angleKeyCutIndex = 0;
                    KeyOperateFragment.this.dismissLoadingDialog();
                    OperationManager.getInstance().sendOrder(Command.TurnOffSpindle(), OperateType.STOP);
                } else {
                    if (i == 1) {
                        KeyOperateFragment.access$1308(KeyOperateFragment.this);
                        KeyOperateFragment.this.showTurningDialog();
                        return;
                    }
                    KeyOperateFragment keyOperateFragment = KeyOperateFragment.this;
                    keyOperateFragment.showLoadingDialog(keyOperateFragment.getString(R.string.waitting), true);
                    KeyOperateFragment.access$1308(KeyOperateFragment.this);
                    KeyOperateFragment.this.dataParam.setAngleKeySpace(angleKeyStep.getSpace());
                    KeyOperateFragment.this.dataParam.setAngleKeyTooth(angleKeyStep.getTooth());
                    CuttingMachine.getInstance().cutNoLocation(KeyOperateFragment.this.dataParam);
                }
            }
        });
        anglekeyTurningDialog.show();
    }

    private int getAbusAngleKeyTurningImg() {
        String tooth = this.angleKeySteps.get(this.angleKeyCutIndex).getTooth();
        if (TextUtils.equals("1", tooth)) {
            return R.drawable.abuskey_1;
        }
        if (TextUtils.equals("2", tooth)) {
            return R.drawable.abuskey_2;
        }
        if (TextUtils.equals("3", tooth)) {
            return R.drawable.abuskey_3;
        }
        if (TextUtils.equals("4", tooth)) {
            return R.drawable.abuskey_4;
        }
        if (TextUtils.equals("5", tooth)) {
            return R.drawable.abuskey_5;
        }
        if (TextUtils.equals("6", tooth)) {
            return R.drawable.abuskey_6;
        }
        return 0;
    }

    private int getBetaAnglekeyTurningImg() {
        String tooth = this.angleKeySteps.get(this.angleKeyCutIndex).getTooth();
        int size = (this.angleKeySteps.size() - 2) / 4;
        int i = this.angleKeyCutIndex;
        if (i < size + 1 || (i > (size * 2) + 1 && i < (size * 3) + 2)) {
            if (TextUtils.equals("2", tooth)) {
                int i2 = this.angleKeyDepthCount;
                return i2 == 4 ? R.drawable.anglekey_a_4_2_left : i2 == 3 ? R.drawable.anglekey_a_3_2_left : 0;
            }
            if (TextUtils.equals("3", tooth)) {
                int i3 = this.angleKeyDepthCount;
                return i3 == 4 ? R.drawable.anglekey_a_4_3_left : i3 == 3 ? R.drawable.anglekey_a_3_3_left : 0;
            }
            if (TextUtils.equals("4", tooth) && this.angleKeyDepthCount == 4) {
                return R.drawable.anglekey_a_4_4_left;
            }
            return 0;
        }
        if (TextUtils.equals("2", tooth)) {
            int i4 = this.angleKeyDepthCount;
            return i4 == 4 ? R.drawable.anglekey_a_4_2_right : i4 == 3 ? R.drawable.anglekey_a_3_2_right : 0;
        }
        if (TextUtils.equals("3", tooth)) {
            int i5 = this.angleKeyDepthCount;
            return i5 == 4 ? R.drawable.anglekey_a_4_3_right : i5 == 3 ? R.drawable.anglekey_a_3_3_right : 0;
        }
        if (TextUtils.equals("4", tooth) && this.angleKeyDepthCount == 4) {
            return R.drawable.anglekey_a_4_4_right;
        }
        return 0;
    }

    private int getE9AnglekeyTurningImg() {
        String tooth = this.angleKeySteps.get(this.angleKeyCutIndex).getTooth();
        int size = (this.angleKeySteps.size() - 2) / 4;
        int i = this.angleKeyCutIndex;
        if (i < size + 1 || (i > (size * 2) + 1 && i < (size * 3) + 2)) {
            if (TextUtils.equals("2", tooth)) {
                int i2 = this.angleKeyDepthCount;
                return i2 == 4 ? R.drawable.tibbe_4_2_u : i2 == 3 ? R.drawable.tibbe_3_2_u : 0;
            }
            if (TextUtils.equals("3", tooth)) {
                int i3 = this.angleKeyDepthCount;
                return i3 == 4 ? R.drawable.tibbe_4_3_u : i3 == 3 ? R.drawable.tibbe_3_3_u : 0;
            }
            if (TextUtils.equals("4", tooth) && this.angleKeyDepthCount == 4) {
                return R.drawable.tibbe_4_4_u;
            }
            return 0;
        }
        if (TextUtils.equals("2", tooth)) {
            int i4 = this.angleKeyDepthCount;
            return i4 == 4 ? R.drawable.tibbe_4_2_d : i4 == 3 ? R.drawable.tibbe_3_2_d : 0;
        }
        if (TextUtils.equals("3", tooth)) {
            int i5 = this.angleKeyDepthCount;
            return i5 == 4 ? R.drawable.tibbe_4_3_d : i5 == 3 ? R.drawable.tibbe_3_3_d : 0;
        }
        if (TextUtils.equals("4", tooth) && this.angleKeyDepthCount == 4) {
            return R.drawable.tibbe_4_4_d;
        }
        return 0;
    }

    private void showAngleKeyInitDialog() {
        OperationManager.getInstance().sendOrder(Command.OpenDoorCuttingSettings(1));
        RemindDialog remindDialog = new RemindDialog(getContext());
        if (CuttingMachine.getInstance().isE9()) {
            remindDialog.setRemindImg(this.angleKeyDepthCount == 3 ? R.drawable.tibbe_3_1 : R.drawable.tibbe_4_1);
        } else if (TextUtils.equals("S9", this.ki.getClampBean().getClampNum())) {
            remindDialog.setRemindImg(R.drawable.abuskey_6);
        } else {
            remindDialog.setRemindImg(this.angleKeyDepthCount == 3 ? R.drawable.anglekey_3_1 : R.drawable.anglekey_4_1);
        }
        remindDialog.setCancleBtnVisibility(false);
        remindDialog.setRemindMsg(getString(R.string.please_install_the_key_according_to_the_picture));
        remindDialog.setDialogBtnCallback(new RemindDialog.DialogBtnCallBack() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment.20
            @Override // com.kkkcut.e20j.ui.dialog.RemindDialog.DialogBtnCallBack
            public void onDialogButClick(boolean z) {
                if (z) {
                    KeyOperateFragment keyOperateFragment = KeyOperateFragment.this;
                    keyOperateFragment.showLoadingDialog(keyOperateFragment.getString(R.string.cutting), true);
                    KeyOperateFragment.access$1308(KeyOperateFragment.this);
                    CuttingMachine.getInstance().cutLocation(KeyOperateFragment.this.dataParam);
                }
            }
        });
        remindDialog.show();
    }

    private void showTurnOverDialog() {
        RemindDialog remindDialog = new RemindDialog(getContext());
        if (CuttingMachine.getInstance().isE9()) {
            remindDialog.setRemindImg(R.drawable.tibbe_rotate);
        } else if (TextUtils.equals("S9", this.ki.getClampBean().getClampNum())) {
            remindDialog.setRemindImg(R.drawable.anglekey_abus_turnover);
        } else {
            remindDialog.setRemindImg(this.angleKeyDepthCount == 3 ? R.drawable.anglekey_3_1 : R.drawable.anglekey_4_1);
        }
        remindDialog.setCancleBtnVisibility(false);
        remindDialog.setRemindMsg(getString(R.string.turn_the_key_to_the_other_side));
        remindDialog.setDialogBtnCallback(new RemindDialog.DialogBtnCallBack() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment.21
            @Override // com.kkkcut.e20j.ui.dialog.RemindDialog.DialogBtnCallBack
            public void onDialogButClick(boolean z) {
                if (z) {
                    KeyOperateFragment.access$1308(KeyOperateFragment.this);
                    KeyOperateFragment keyOperateFragment = KeyOperateFragment.this;
                    keyOperateFragment.showLoadingDialog(keyOperateFragment.getString(R.string.cutting), true);
                    KeyOperateFragment.this.detectCutterHigh();
                }
            }
        });
        remindDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showAngleDimpleInitDialog(final int i) {
        this.dimpleCutIndex = 0;
        RemindDialog remindDialog = new RemindDialog(getContext());
        remindDialog.setRemindImg(R.drawable.remind_angle_dimple_turn_0);
        remindDialog.setCancleBtnVisibility(false);
        remindDialog.setRemindMsg(getString(R.string.please_install_the_key_according_to_the_picture));
        remindDialog.setDialogBtnCallback(new RemindDialog.DialogBtnCallBack() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment.22
            @Override // com.kkkcut.e20j.ui.dialog.RemindDialog.DialogBtnCallBack
            public void onDialogButClick(boolean z) {
                if (z) {
                    if (i == 0) {
                        KeyOperateFragment keyOperateFragment = KeyOperateFragment.this;
                        keyOperateFragment.showLoadingDialog(keyOperateFragment.getString(R.string.decoding), true);
                        CuttingMachine.getInstance().decodeLocation(KeyOperateFragment.this.dataParam);
                    } else {
                        KeyOperateFragment keyOperateFragment2 = KeyOperateFragment.this;
                        keyOperateFragment2.showLoadingDialog(keyOperateFragment2.getString(R.string.cutting), true);
                        CuttingMachine.getInstance().cutLocation(KeyOperateFragment.this.dataParam);
                    }
                }
            }
        });
        remindDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showAngleDimpleTurnDialog(final int i) {
        AnglekeyTurningDialog anglekeyTurningDialog = new AnglekeyTurningDialog(getContext());
        int i2 = this.dimpleCutIndex;
        if (i2 == 0) {
            anglekeyTurningDialog.setRemindMsg(getString(R.string.please_install_the_key_according_to_the_picture));
            anglekeyTurningDialog.setRemindImg(R.drawable.remind_angle_dimple_turn_1);
        } else {
            if (i2 != 1) {
                return;
            }
            anglekeyTurningDialog.setRemindMsg(getString(R.string.please_install_the_key_according_to_the_picture));
            anglekeyTurningDialog.setRemindImg(R.drawable.remind_angle_dimple_turn_2);
        }
        anglekeyTurningDialog.setDialogBtnCallback(new AnglekeyTurningDialog.DialogBtnCallBack() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment.23
            @Override // com.kkkcut.e20j.ui.dialog.AnglekeyTurningDialog.DialogBtnCallBack
            public void onDialogButClick(int i3) {
                if (i3 == 0) {
                    KeyOperateFragment.this.dimpleCutIndex = 0;
                    KeyOperateFragment.this.dismissLoadingDialog();
                    OperationManager.getInstance().sendOrder(Command.TurnOffSpindle(), OperateType.STOP);
                } else {
                    if (i3 != 1) {
                        KeyOperateFragment.this.dataParam.setAngleDimpleIndex(KeyOperateFragment.this.dimpleCutIndex);
                        KeyOperateFragment.this.dataParam.setAngleDimple(true);
                        if (i == 0) {
                            KeyOperateFragment keyOperateFragment = KeyOperateFragment.this;
                            keyOperateFragment.showLoadingDialog(keyOperateFragment.getString(R.string.decoding), true);
                            CuttingMachine.getInstance().decodeNoLocation(KeyOperateFragment.this.dataParam);
                            return;
                        } else {
                            KeyOperateFragment keyOperateFragment2 = KeyOperateFragment.this;
                            keyOperateFragment2.showLoadingDialog(keyOperateFragment2.getString(R.string.cutting), true);
                            CuttingMachine.getInstance().cutNoLocation(KeyOperateFragment.this.dataParam);
                            return;
                        }
                    }
                    KeyOperateFragment.access$608(KeyOperateFragment.this);
                    KeyOperateFragment.this.showAngleDimpleTurnDialog(i);
                }
            }
        });
        anglekeyTurningDialog.show();
    }

    private String reduceCount(int i, List<List<Integer>> list) {
        String str = "";
        for (int i2 = 0; i2 < list.size(); i2++) {
            for (int i3 = 0; i3 < list.get(i2).size(); i3++) {
                int i4 = i - 1;
                if (i3 == i4) {
                    str = str + list.get(i2).get(i3) + ";";
                } else if (i3 < i4) {
                    str = str + list.get(i2).get(i3) + ",";
                }
            }
        }
        return str;
    }

    @Override // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    public String setTitleStr() {
        return getString(R.string.operation);
    }

    public void showPlasticLocationDialog() {
        RemindDialog remindDialog = new RemindDialog(getContext());
        if (CuttingMachine.getInstance().isE9()) {
            remindDialog.setRemindImg(R.drawable.a9_laser_stop_4_e9);
        } else if ("B".equals(this.ki.getClampBean().getClampSide())) {
            remindDialog.setRemindImg(R.drawable.remind_s1_b_plastic);
        } else {
            remindDialog.setRemindImg(R.drawable.remind_s1_a_plastic);
        }
        remindDialog.setRemindMsg(getString(R.string.clear_clamp_and_install_key));
        remindDialog.setDialogBtnCallback(new RemindDialog.DialogBtnCallBack() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment.24
            @Override // com.kkkcut.e20j.ui.dialog.RemindDialog.DialogBtnCallBack
            public void onDialogButClick(boolean z) {
                if (z) {
                    KeyOperateFragment.this.startCut();
                }
            }
        });
        remindDialog.show();
    }

    public void putDownDecoderRemindDialog(final int i) {
        if (CuttingMachine.getInstance().isE9()) {
            RemindDialog remindDialog = new RemindDialog(getContext());
            remindDialog.setRemindImg(R.drawable.pull_decoder_down);
            remindDialog.setRemindMsg(getString(R.string.pull_the_decoder_down));
            remindDialog.setDialogBtnCallback(new RemindDialog.DialogBtnCallBack() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment.25
                @Override // com.kkkcut.e20j.ui.dialog.RemindDialog.DialogBtnCallBack
                public void onDialogButClick(boolean z) {
                    if (!z) {
                        if (KeyOperateFragment.this.angleKeyCutIndex > 0) {
                            KeyOperateFragment.access$1310(KeyOperateFragment.this);
                        }
                        KeyOperateFragment.this.dismissLoadingDialog();
                    } else if (i == 0) {
                        KeyOperateFragment.this.startDecode();
                    } else {
                        KeyOperateFragment.this.startCut();
                    }
                }
            });
            remindDialog.show();
        }
    }

    public void putUpDecoderRemindDialog() {
        if (CuttingMachine.getInstance().isE9()) {
            RemindDialog remindDialog = new RemindDialog(getContext());
            remindDialog.setRemindImg(R.drawable.push_decoder_up);
            remindDialog.setRemindMsg(getString(R.string.push_the_decoder_up));
            remindDialog.setDialogBtnCallback(new RemindDialog.DialogBtnCallBack() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment.26
                @Override // com.kkkcut.e20j.ui.dialog.RemindDialog.DialogBtnCallBack
                public void onDialogButClick(boolean z) {
                    if (z) {
                        CuttingMachine.getInstance().cutFromCutterLocation(KeyOperateFragment.this.dataParam);
                        KeyOperateFragment keyOperateFragment = KeyOperateFragment.this;
                        keyOperateFragment.showLoadingDialog(keyOperateFragment.getString(R.string.waitting), true);
                    } else {
                        if (KeyOperateFragment.this.angleKeyCutIndex > 0) {
                            KeyOperateFragment.access$1310(KeyOperateFragment.this);
                        }
                        KeyOperateFragment.this.dismissLoadingDialog();
                    }
                }
            });
            remindDialog.show();
        }
    }

    private void showClearClampRemind(final int i) {
        if (this.clearClampRemind == null) {
            this.clearClampRemind = new RemindDialog(getContext());
        }
        this.clearClampRemind.setCancleBtnVisibility(false);
        this.clearClampRemind.setRemindImg(ClampCreator.getClearClampImg(this.ki));
        this.clearClampRemind.setRemindMsg(getString(R.string.clean_the_groove_from_chips));
        this.clearClampRemind.setDialogBtnCallback(new RemindDialog.DialogBtnCallBack() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment.27
            @Override // com.kkkcut.e20j.ui.dialog.RemindDialog.DialogBtnCallBack
            public void onDialogButClick(boolean z) {
                if (z) {
                    if (i == 39) {
                        if (KeyOperateFragment.this.ki.isAngleDimple()) {
                            KeyOperateFragment.this.showAngleDimpleInitDialog(0);
                        } else {
                            KeyOperateFragment.this.startDecode();
                        }
                    }
                    if (i == 1) {
                        KeyOperateFragment.this.startCut();
                    }
                }
            }
        });
        if (this.clearClampRemind.isShowing()) {
            return;
        }
        this.clearClampRemind.show();
    }

    private int fixDepth(int i, int i2) {
        String[] split = this.ki.getDepthStr().split(";");
        if (i < split.length) {
            String[] split2 = split[i].split(",");
            int i3 = Integer.MAX_VALUE;
            int i4 = 0;
            for (int i5 = 0; i5 < split2.length; i5++) {
                int round = Math.round((i2 - Integer.parseInt(split2[i5])) * 10) / 10;
                if (Math.abs(round) < Math.abs(i3)) {
                    i4 = i5;
                    i3 = round;
                }
            }
            int parseInt = Integer.parseInt(split2[i4]);
            Random random = new Random();
            if (Math.abs(i3) > 30) {
                return i2;
            }
            if (Math.abs(i3) > 20) {
                float nextInt = ((random.nextInt(200) * 1.0f) / 100.0f) + 8.0f;
                float f = parseInt;
                return (int) (i3 > 0 ? f + nextInt : f - nextInt);
            }
            if (Math.abs(i3) > 15) {
                float nextInt2 = ((random.nextInt(200) * 1.0f) / 100.0f) + 6.0f;
                float f2 = parseInt;
                return (int) (i3 > 0 ? f2 + nextInt2 : f2 - nextInt2);
            }
            if (Math.abs(i3) > 10) {
                float nextInt3 = ((random.nextInt(100) * 1.0f) / 100.0f) + 5.0f;
                float f3 = parseInt;
                return (int) (i3 > 0 ? f3 + nextInt3 : f3 - nextInt3);
            }
            if (Math.abs(i3) > 7) {
                float nextInt4 = ((random.nextInt(100) * 1.0f) / 100.0f) + 4.0f;
                float f4 = parseInt;
                return (int) (i3 > 0 ? f4 + nextInt4 : f4 - nextInt4);
            }
        }
        return i2;
    }

    private void hu162tChangeSide() {
        String valueOf;
        if (TextUtils.isEmpty(this.ki.getReadBittingRule()) || !"3".equals(this.ki.getReadBittingRule())) {
            return;
        }
        if (!this.doorIgnition) {
            if (this.cutCount == 0) {
                this.cutCount = 1;
                String[] split = this.ki.getKeyToothCode().split(";")[0].split(",");
                String str = "";
                for (int i = 0; i < split.length; i++) {
                    String str2 = split[i];
                    String str3 = "1";
                    if (str2.contains("?")) {
                        str2 = "1";
                    }
                    if (split.length != 8) {
                        if (i < 3 && split.length == 9) {
                            float parseFloat = Float.parseFloat(str2);
                            if (parseFloat <= 4.0f) {
                                if (parseFloat >= 1.0f) {
                                    valueOf = String.valueOf(5.0d - parseFloat);
                                    str3 = valueOf;
                                }
                                str3 = "4";
                            }
                        } else if (i >= 4 || split.length != 10) {
                            str3 = "2.5";
                        } else {
                            float parseFloat2 = Float.parseFloat(str2);
                            if (parseFloat2 <= 4.0f) {
                                if (parseFloat2 >= 1.0f) {
                                    valueOf = String.valueOf(5.0d - parseFloat2);
                                    str3 = valueOf;
                                }
                                str3 = "4";
                            }
                        }
                    }
                    str = i == split.length - 1 ? str + str3 + ";" : str + str3 + ",";
                }
                this.ki.setKeyToothCode(str);
                this.mKey.setToothCodeAndInvalidate(str);
                Key key = this.mKey;
                if (key instanceof SingleOutGrooveKey) {
                    ((SingleOutGrooveKey) key).switchCSide();
                }
                this.tvSide.setText(R.string.c_side);
                return;
            }
            return;
        }
        if (this.cutCount == 0) {
            this.cutCount = 1;
            Key key2 = this.mKey;
            if (key2 instanceof SingleOutGrooveKey) {
                ((SingleOutGrooveKey) key2).switchCSide();
            }
            this.tvSide.setText(R.string.c_side);
        }
    }

    private void saveCutNumber() {
        int i = SPUtils.getInt(SpKeys.CUT_NUMBER, 200) - 1;
        if (i <= 0) {
            EventBus.getDefault().post(new EventCenter(36));
            SPUtils.put(SpKeys.CERTIFICATION_STATUS, 1);
        }
        SPUtils.put(SpKeys.CUT_NUMBER, i);
    }

    @Override // com.kkkcut.e20j.base.BaseFFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        if (this.ki.getType() == 7) {
            OperationManager.getInstance().sendOrder(Command.OpenDoorCuttingSettings(SPUtils.getBoolean(SpKeys.COVER, true) ? 1 : 0));
        }
        this.ki = null;
        super.onDestroy();
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment, androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
    }
}
