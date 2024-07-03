package com.kkkcut.e20j.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kkkcut.e20j.adapter.ToothKeyboardRvAdapter;
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter;
import com.kkkcut.e20j.us.R;

import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

/* loaded from: classes.dex */
public class MultiToothSelectDialog extends Dialog implements View.OnClickListener, BaseQuickAdapter.OnItemClickListener {
    private ArrayList<List<String>> allDepthNames;
    private String depthName;
    private RecyclerView rvKeyboard;
    private ToothKeyboardRvAdapter toothKeyboardRvAdapter;

    @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
    }

    public MultiToothSelectDialog(Context context, String str) {
        super(context);
        this.allDepthNames = new ArrayList<>();
        this.depthName = str;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_multi_tooth);
        this.rvKeyboard = (RecyclerView) findViewById(R.id.rv_keyboard);
        initKeyboard();
        ((Button) findViewById(R.id.ll_confirm)).setOnClickListener(this);
        setCancelable(false);
    }

    private void initKeyboard() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 5);
        gridLayoutManager.setOrientation(1);
        this.rvKeyboard.setLayoutManager(gridLayoutManager);
        ToothKeyboardRvAdapter toothKeyboardRvAdapter = new ToothKeyboardRvAdapter(R.layout.item_input_multi_number);
        this.toothKeyboardRvAdapter = toothKeyboardRvAdapter;
        this.rvKeyboard.setAdapter(toothKeyboardRvAdapter);
        this.toothKeyboardRvAdapter.bindToRecyclerView(this.rvKeyboard);
        this.toothKeyboardRvAdapter.setOnItemClickListener(this);
        for (String str : this.depthName.split(";")) {
            ArrayList arrayList = new ArrayList();
            for (String str2 : str.split(",")) {
                arrayList.add(str2);
            }
            this.allDepthNames.add(fillData(arrayList));
        }
        this.toothKeyboardRvAdapter.setNewData(this.allDepthNames.get(0));
    }

    private List<String> fillData(List<String> list) {
        return new ArrayList(list);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.toothKeyboardRvAdapter.getItemCount(); i++) {
            CheckBox checkBox = (CheckBox) this.toothKeyboardRvAdapter.getViewByPosition(i, R.id.bt_number);
            if (checkBox.isChecked()) {
                sb.append(checkBox.getText());
                sb.append(",");
            }
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
            EventBus.getDefault().post(new EventCenter(58, sb.toString()));
        }
        dismiss();
    }
}
