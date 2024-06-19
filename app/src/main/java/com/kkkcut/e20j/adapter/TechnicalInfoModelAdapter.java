package com.kkkcut.e20j.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.kkkcut.e20j.DbBean.technical.DataModel;
import com.kkkcut.e20j.us.R;
import java.util.List;

/* loaded from: classes.dex */
public class TechnicalInfoModelAdapter extends RecyclerView.Adapter<TechnicalInfoModelAdapter.ViewHolder> {
    private OnKeySelectItemClickListener listener;
    protected Context mContext;
    protected List<DataModel> mDatas;
    protected LayoutInflater mInflater;

    /* loaded from: classes.dex */
    public interface OnKeySelectItemClickListener {
        void onItemClick(int i);
    }

    public TechnicalInfoModelAdapter(Context context) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
    }

    public void setDatas(List<DataModel> list) {
        this.mDatas = list;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(this.mInflater.inflate(R.layout.item_technical_info, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        viewHolder.tvContent.setText(this.mDatas.get(i).getModelName());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.kkkcut.e20j.adapter.TechnicalInfoModelAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TechnicalInfoModelAdapter.this.m24x37b73c9(i, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onBindViewHolder$0$com-kkkcut-e20j-adapter-TechnicalInfoModelAdapter */
    public /* synthetic */ void m24x37b73c9(int i, View view) {
        OnKeySelectItemClickListener onKeySelectItemClickListener = this.listener;
        if (onKeySelectItemClickListener != null) {
            onKeySelectItemClickListener.onItemClick(i);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<DataModel> list = this.mDatas;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    /* loaded from: classes.dex */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvContent;

        public ViewHolder(View view) {
            super(view);
            this.tvContent = (TextView) view.findViewById(R.id.tv_content);
        }
    }

    public void setOnKeySelectItemClickListener(OnKeySelectItemClickListener onKeySelectItemClickListener) {
        this.listener = onKeySelectItemClickListener;
    }
}