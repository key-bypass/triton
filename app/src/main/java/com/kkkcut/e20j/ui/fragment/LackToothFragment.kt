package com.kkkcut.e20j.ui.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kkkcut.e20j.adapter.LackToothAdapter2;
import com.kkkcut.e20j.adapter.ToothKeyboardRvAdapter;
import com.kkkcut.e20j.androidquick.autolayout.utils.AutoUtils;
import com.kkkcut.e20j.androidquick.network.RetrofitManager;
import com.kkkcut.e20j.androidquick.tool.FileUtil;
import com.kkkcut.e20j.androidquick.tool.NetUtil;
import com.kkkcut.e20j.androidquick.tool.SPUtils;
import com.kkkcut.e20j.androidquick.tool.ToastUtil;
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter;
import com.kkkcut.e20j.bean.CodeAndTooth;
import com.kkkcut.e20j.bean.eventbus.InputFinishBean;
import com.kkkcut.e20j.bean.gsonBean.LackToothRes;
import com.kkkcut.e20j.dao.ToothCodeDaoManager;
import com.kkkcut.e20j.net.Apis;
import com.kkkcut.e20j.net.TUitls;
import com.kkkcut.e20j.ui.dialog.MultiToothSelectDialog;
import com.kkkcut.e20j.us.R;
import com.kkkcut.e20j.utils.GetUUID;
import com.kkkcut.e20j.utils.UnifiedErrorUtil;
import com.cutting.machine.bean.KeyInfo;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.greenrobot.eventbus.EventBus;

/* loaded from: classes.dex */
public class LackToothFragment extends BaseBackFragment implements BaseQuickAdapter.OnItemClickListener {
    public static final String Bitings = "bitings";
    public static final String KEY_ID = "keyID";
    public static final String KEY_INFO = "keyInfo";
    public static final String SERIES = "series";
    public static final String TOOTHCODE_Lack = "toothcode_lack";
    public static final String ToothCode_Honda_A = "toothcode_honda_a";
    public static final String ToothCode_Honda_B = "toothcode_honda_b";
    private int index;
    LackToothAdapter2 lackToothAdapter;
    ToothCodeDaoManager toothCodeDaoManager;

    LinearLayout llToothcodeContainer;

    RecyclerView rvKeyboard;

    RecyclerView rvToothList;

    private ToothKeyboardRvAdapter toothKeyboardRvAdapter;

    TextView tvMulti;
    private ArrayList<List<String>> allDepthNames = new ArrayList<>();
    private int textColorDefault = -1;
    private int textColorSelect = Color.parseColor("#ff205f");
    private Map<Integer, String> multiToothMap = new HashMap();

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_lacktooth;
    }

    public static LackToothFragment newInstance(int i, KeyInfo keyInfo, String str, String str2, String str3, String str4, ArrayList<CodeAndTooth> arrayList) {
        Bundle bundle = new Bundle();
        bundle.putInt("keyID", i);
        bundle.putParcelable(KEY_INFO, keyInfo);
        bundle.putString(ToothCode_Honda_A, str);
        bundle.putString(ToothCode_Honda_B, str2);
        bundle.putString("series", str3);
        bundle.putString(TOOTHCODE_Lack, str4);
        bundle.putParcelableArrayList(Bitings, arrayList);
        LackToothFragment lackToothFragment = new LackToothFragment();
        lackToothFragment.setArguments(bundle);
        return lackToothFragment;
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        initKeyboard();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(1);
        this.rvToothList.setLayoutManager(linearLayoutManager);
        LackToothAdapter2 lackToothAdapter2 = new LackToothAdapter2(R.layout.item_lacktooth, R.layout.item_lacktooth_title, getArguments().getParcelableArrayList(Bitings));
        this.lackToothAdapter = lackToothAdapter2;
        lackToothAdapter2.setOnItemClickListener(this);
        this.rvToothList.setAdapter(this.lackToothAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), 1);
        dividerItemDecoration.setDrawable(new ColorDrawable(Color.parseColor("#717178")));
        this.rvToothList.addItemDecoration(dividerItemDecoration);
        this.toothCodeDaoManager = new ToothCodeDaoManager(getArguments().getInt("keyID"));
    }

    private void initKeyboard() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 5);
        gridLayoutManager.setOrientation(1);
        this.rvKeyboard.setLayoutManager(gridLayoutManager);
        ToothKeyboardRvAdapter toothKeyboardRvAdapter = new ToothKeyboardRvAdapter();
        this.toothKeyboardRvAdapter = toothKeyboardRvAdapter;
        this.rvKeyboard.setAdapter(toothKeyboardRvAdapter);
        this.toothKeyboardRvAdapter.setOnItemClickListener(this);
        KeyInfo keyInfo = getArguments() != null ? (KeyInfo) getArguments().getParcelable(KEY_INFO) : null;
        String[] strArr = new String[0];
        if (keyInfo != null) {
            strArr = keyInfo.getDepthName().split(";");
        }
        for (String str : strArr) {
            ArrayList arrayList = new ArrayList();
            for (String str2 : str.split(",")) {
                arrayList.add(str2);
            }
            this.allDepthNames.add(fillData(arrayList));
        }
        this.toothKeyboardRvAdapter.setNewData(this.allDepthNames.get(0));
        String string = getArguments().getString(TOOTHCODE_Lack);
        ArrayList<String[]> arrayList2 = new ArrayList<>();
        if (TextUtils.isEmpty(string)) {
            if (keyInfo.getIcCard() == 1480 || keyInfo.getIcCard() == 601480) {
                ArrayList<String[]> toothCodeRoundArray = getToothCodeRoundArray(getArguments().getString(ToothCode_Honda_A));
                ArrayList<String[]> toothCodeRoundArray2 = getToothCodeRoundArray(getArguments().getString(ToothCode_Honda_B));
                if (toothCodeRoundArray.size() > 0 && toothCodeRoundArray2.size() > 0) {
                    String[] strArr2 = toothCodeRoundArray.get(0);
                    String[] strArr3 = toothCodeRoundArray2.get(0);
                    if (strArr3.length > 3) {
                        strArr3 = (String[]) Arrays.copyOfRange(strArr3, 0, 3);
                    }
                    arrayList2.add(strArr2);
                    arrayList2.add(strArr3);
                }
            } else {
                arrayList2 = getToothCodeRoundArray(keyInfo.getKeyToothCode());
            }
        } else {
            for (String str3 : string.split("-")) {
                String replace = str3.replace("_", "?");
                String[] strArr4 = new String[replace.length()];
                char[] charArray = replace.toCharArray();
                for (int i = 0; i < charArray.length; i++) {
                    strArr4[i] = String.valueOf(charArray[i]);
                }
                arrayList2.add(strArr4);
            }
        }
        for (int i2 = 0; i2 < arrayList2.size(); i2++) {
            String[] strArr5 = arrayList2.get(i2);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(AutoUtils.getPercentWidthSize(20), -2);
            for (int i3 = 0; i3 < strArr5.length; i3++) {
                if (i2 > 0 && i3 == 0) {
                    this.llToothcodeContainer.addView(getTextView("-"), layoutParams);
                }
                TextView textView = getTextView(strArr5[i3]);
                if (i2 == 0 && i3 == 0) {
                    textView.setTextColor(getContext().getResources().getColor(R.color.color_ff205f));
                }
                this.llToothcodeContainer.addView(textView, layoutParams);
            }
        }
    }

    public ArrayList<String[]> getToothCodeRoundArray(String str) {
        ArrayList<String[]> arrayList = new ArrayList<>();
        KeyInfo keyInfo = getArguments() != null ? (KeyInfo) getArguments().getParcelable(KEY_INFO) : null;
        if (TextUtils.isEmpty(str)) {
            Iterator<List<Integer>> it = keyInfo.getSpaceList().iterator();
            while (it.hasNext()) {
                int size = it.next().size();
                if (keyInfo.getIcCard() == 1480 || keyInfo.getIcCard() == 601480) {
                    size = 4;
                }
                String[] strArr = new String[size];
                for (int i = 0; i < size; i++) {
                    strArr[i] = "?";
                }
                arrayList.add(strArr);
            }
            return arrayList;
        }
        List<List<String>> depthNameList = keyInfo.getDepthNameList();
        String[] split = str.split(";");
        for (int i2 = 0; i2 < split.length; i2++) {
            String[] split2 = split[i2].split(",");
            String[] strArr2 = new String[split2.length];
            for (int i3 = 0; i3 < split2.length; i3++) {
                strArr2[i3] = getToothCodeRound(split2[i3].trim(), depthNameList.get(i2));
            }
            arrayList.add(strArr2);
        }
        return arrayList;
    }

    String getToothCodeRound(String str, List<String> list) {
        if (!str.contains(FileUtil.FILE_EXTENSION_SEPARATOR)) {
            return str;
        }
        String str2 = str.split("\\.")[0];
        float parseFloat = Float.parseFloat("0." + str.split("\\.")[1]);
        int indexOf = list.indexOf(str2);
        if (parseFloat < 0.5f) {
            return indexOf == -1 ? "?" : list.get(indexOf);
        }
        if (indexOf == -1) {
            return list.get(0);
        }
        if (indexOf == list.size() - 1) {
            return list.get(list.size() - 1);
        }
        return list.get(indexOf + 1);
    }

    private TextView getTextView(String str) {
        TextView textView = new TextView(getContext());
        textView.setTextSize(AutoUtils.getPercentHeightSize(24));
        textView.setTextColor(this.textColorDefault);
        textView.setText(str);
        textView.setGravity(17);
        return textView;
    }

    private void inputCode(String str) {
        ((TextView) this.llToothcodeContainer.getChildAt(this.index)).setText(str);
        moveToNext();
    }

    private void moveToNext() {
        TextView textView;
        ((TextView) this.llToothcodeContainer.getChildAt(this.index)).setTextColor(this.textColorDefault);
        if (this.index < this.llToothcodeContainer.getChildCount() - 1) {
            int i = this.index + 1;
            this.index = i;
            textView = (TextView) this.llToothcodeContainer.getChildAt(i);
            if (textView.getText().toString().trim().equals("-")) {
                int i2 = this.index + 1;
                this.index = i2;
                textView = (TextView) this.llToothcodeContainer.getChildAt(i2);
            }
            textView.setTextColor(this.textColorSelect);
        } else {
            this.index = 0;
            textView = (TextView) this.llToothcodeContainer.getChildAt(0);
            textView.setTextColor(this.textColorSelect);
        }
        if (textView.getText().toString().trim().equals("#")) {
            this.tvMulti.setText(this.multiToothMap.get(Integer.valueOf(this.index)));
        } else {
            this.tvMulti.setText("");
        }
    }

    private void moveToLast() {
        int i = this.index;
        if (i > 0) {
            ((TextView) this.llToothcodeContainer.getChildAt(i)).setTextColor(this.textColorDefault);
            int i2 = this.index - 1;
            this.index = i2;
            TextView textView = (TextView) this.llToothcodeContainer.getChildAt(i2);
            if (textView.getText().toString().trim().equals("-")) {
                int i3 = this.index - 1;
                this.index = i3;
                textView = (TextView) this.llToothcodeContainer.getChildAt(i3);
            }
            textView.setTextColor(this.textColorSelect);
            if (textView.getText().toString().trim().equals("#")) {
                this.tvMulti.setText(this.multiToothMap.get(Integer.valueOf(this.index)));
            } else {
                this.tvMulti.setText("");
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Object obj = baseQuickAdapter.getData().get(i);
        if (obj instanceof String) {
            String str = (String) obj;
            if (">".equals(str)) {
                moveToNext();
                return;
            }
            if ("<".equals(str)) {
                moveToLast();
                return;
            } else if (getString(R.string.multi).equals(str)) {
                showMultiInputDialog();
                return;
            } else {
                inputCode(str);
                return;
            }
        }
        List<CodeAndTooth> data = baseQuickAdapter.getData();
        for (CodeAndTooth codeAndTooth : data) {
            if (codeAndTooth.t != null) {
                ((LackToothRes.DataBean) codeAndTooth.t).setChecked(false);
            }
        }
        CodeAndTooth codeAndTooth2 = (CodeAndTooth) data.get(i);
        if (codeAndTooth2.t != null) {
            ((LackToothRes.DataBean) codeAndTooth2.t).setChecked(true);
        }
        LackToothRes.DataBean dataBean = (LackToothRes.DataBean) codeAndTooth2.t;
        if (dataBean == null) {
            return;
        }
        char[] charArray = dataBean.getBitting().toCharArray();
        String str2 = "";
        for (int i2 = 0; i2 < charArray.length; i2++) {
            if (i2 == charArray.length - 1) {
                str2 = str2 + charArray[i2] + ";";
            } else if ("-".contains(String.valueOf(charArray[i2]))) {
                str2 = str2.substring(0, str2.lastIndexOf(",")) + ";";
            } else {
                str2 = str2 + charArray[i2] + ",";
            }
        }
        EventBus.getDefault().post(new EventCenter(2, new InputFinishBean(str2, getBittingString(), (ArrayList<CodeAndTooth>) data)));
        onBack();
    }

    private void showMultiInputDialog() {
        new MultiToothSelectDialog(getContext(), ((KeyInfo) getArguments().getParcelable(KEY_INFO)).getDepthName()).show();
    }

    @Override // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    public String setTitleStr() {
        return getString(R.string.lacktooth);
    }

    private List<String> fillData(List<String> list) {
        ArrayList arrayList = new ArrayList(list);
        arrayList.add("?");
        arrayList.add("<");
        arrayList.add(">");
        arrayList.add(getString(R.string.multi));
        return arrayList;
    }

    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_search /* 2131361972 */:
                search();
                return;
            case R.id.bt_search_offline /* 2131361973 */:
                fuzzyQueryBitting(getBittingString());
                return;
            default:
                return;
        }
    }

    private void fuzzyQueryBitting(final String str) {
        showLoadingDialog(getString(R.string.waitting));
        addDisposable(Observable.fromCallable(() -> {
            return LackToothFragment.this.toothCodeDaoManager.lackToothMulti(str, LackToothFragment.this.multiToothMap);
        }).map(list -> {
                ArrayList arrayList = new ArrayList();
                if (list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        LackToothRes.DataBean dataBean = new LackToothRes.DataBean();
                        dataBean.setBitting(list.get(i).getBitting());
                        dataBean.setCode(list.get(i).getCode());
                        arrayList.add(new CodeAndTooth(dataBean));
                    }
                    return arrayList;
                }
                throw new Exception(LackToothFragment.this.getString(R.string.no_data_was_found));
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(list -> {
                LackToothFragment.this.dismissLoadingDialog();
                LackToothAdapter2 lackToothAdapter2 = new LackToothAdapter2(R.layout.item_lacktooth, R.layout.item_lacktooth_title, list);
                lackToothAdapter2.setOnItemClickListener(LackToothFragment.this);
                LackToothFragment.this.rvToothList.setAdapter(lackToothAdapter2);

        }, th -> {
                LackToothFragment.this.dismissLoadingDialog();
                ToastUtil.showToast(R.string.no_data_was_found);

        }));
    }

    private void search() {
        if (!NetUtil.isNetworkConnected(getContext())) {
            ToastUtil.showToast(R.string.network_unavailable);
            return;
        }
        showLoadingDialog(getString(R.string.waitting));
        String str = "";
        for (int i = 0; i < this.llToothcodeContainer.getChildCount(); i++) {
            String charSequence = ((TextView) this.llToothcodeContainer.getChildAt(i)).getText().toString();
            str = TextUtils.equals(charSequence, "#") ? str + "[" + this.multiToothMap.get(Integer.valueOf(i)) + "]" : str + charSequence;
        }
        KeyInfo keyInfo = getArguments().getParcelable(KEY_INFO);
        if (keyInfo.getIcCard() == 1480 || keyInfo.getIcCard() == 601480) {
            str = str + "*";
        }
        addDisposable(RetrofitManager.getInstance().createApi(Apis.class).lackTooth(TUitls.lackToothParam(str.replace("?", "*"), getArguments().getInt("keyID"), GetUUID.getUUID(), SPUtils.getString("series"), getArguments().getString("series"))).map(lackToothRes -> {
            List<List<LackToothRes.DataBean>> data = lackToothRes.getData();
            if ("0".equals(lackToothRes.getCode())) {
                if (data != null && data.size() > 0) {
                    ArrayList arrayList = new ArrayList();
                    int i2 = 0;
                    while (i2 < data.size()) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(LackToothFragment.this.getString(R.string.key));
                        sb.append(" ");
                        int i3 = i2 + 1;
                        sb.append(i3);
                        arrayList.add(new CodeAndTooth(true, sb.toString()));
                        for (int i4 = 0; i4 < data.get(i2).size(); i4++) {
                            arrayList.add(new CodeAndTooth(data.get(i2).get(i4)));
                        }
                        i2 = i3;
                    }
                    return arrayList;
                }
                throw new Exception(LackToothFragment.this.getString(R.string.no_data_was_found));
            }
            throw new Exception(LackToothFragment.this.getString(R.string.no_data_was_found));

        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe( obj -> {
                LackToothFragment.this.m34lambda$search$0$comkkkcute20juifragmentLackToothFragment((List) obj);

        }, new Consumer<Throwable>() { // from class: com.kkkcut.e20j.ui.fragment.LackToothFragment.5
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                LackToothFragment.this.dismissLoadingDialog();
                ToastUtil.showToast(UnifiedErrorUtil.unifiedError(th).getMessage());
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$search$0$com-kkkcut-e20j-ui-fragment-LackToothFragment, reason: not valid java name */
    public /* synthetic */ void m34lambda$search$0$comkkkcute20juifragmentLackToothFragment(List list) throws Exception {
        dismissLoadingDialog();
        LackToothAdapter2 lackToothAdapter2 = new LackToothAdapter2(R.layout.item_lacktooth, R.layout.item_lacktooth_title, list);
        lackToothAdapter2.setOnItemClickListener(this);
        this.rvToothList.setAdapter(lackToothAdapter2);
    }

    private String getBittingString() {
        String str = "";
        for (int i = 0; i < this.llToothcodeContainer.getChildCount(); i++) {
            str = str + ((TextView) this.llToothcodeContainer.getChildAt(i)).getText().toString().trim();
        }
        String replace = str.replace("?", "_");
        KeyInfo keyInfo = (KeyInfo) getArguments().getParcelable(KEY_INFO);
        if (keyInfo.getIcCard() != 1480 && keyInfo.getIcCard() != 601480) {
            return replace;
        }
        return replace + "_";
    }

    @Override // com.kkkcut.e20j.base.BaseFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void onEventComing(EventCenter eventCenter) {
        if (eventCenter.getEventCode() == 58) {
            String str = (String) eventCenter.getData();
            if (str.length() == 1) {
                inputCode(str);
            } else {
                this.multiToothMap.put(Integer.valueOf(this.index), str);
                inputCode("#");
            }
        }
    }
}
