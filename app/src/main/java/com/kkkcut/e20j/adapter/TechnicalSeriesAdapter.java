package com.kkkcut.e20j.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.kkkcut.e20j.DbBean.technical.DataModelSeries;
import com.kkkcut.e20j.us.R;
import java.util.List;

/* loaded from: classes.dex */
public class TechnicalSeriesAdapter extends RecyclerView.Adapter<com.kkkcut.e20j.adapter.TechnicalSeriesAdapter.ViewHolder> {
    private OnItemClickListener itemClickListener;
    protected Context mContext;
    protected List<DataModelSeries> mDatas;
    protected LayoutInflater mInflater;

    /* loaded from: classes.dex */
    public interface OnItemClickListener {
        void onItemClick(View view, int i, String str);
    }

    public TechnicalSeriesAdapter(Context context) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
    }

    public void setDatas(List<DataModelSeries> list) {
        this.mDatas = list;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(this.mInflater.inflate(R.layout.item_technical_info, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        final DataModelSeries dataModelSeries = this.mDatas.get(i);
        viewHolder.tvContent.setText(dataModelSeries.getSeriesName());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.kkkcut.e20j.adapter.TechnicalSeriesAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TechnicalSeriesAdapter.this.m25x5b5d18e3(viewHolder, dataModelSeries, view);
            }
        });
        if (getItemCount() == 1) {
            viewHolder.container.performClick();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onBindViewHolder$0$com-kkkcut-e20j-adapter-TechnicalSeriesAdapter */
    public /* synthetic */ void m25x5b5d18e3(ViewHolder viewHolder, DataModelSeries dataModelSeries, View view) {
        OnItemClickListener onItemClickListener = this.itemClickListener;
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(viewHolder.itemView, dataModelSeries.getModelSeriesID(), dataModelSeries.getSeriesName());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<DataModelSeries> list = this.mDatas;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    /* loaded from: classes.dex */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout container;
        TextView tvContent;

        public ViewHolder(View view) {
            super(view);
            this.tvContent = (TextView) view.findViewById(R.id.tv_content);
            this.container = (LinearLayout) view.findViewById(R.id.container);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.itemClickListener = onItemClickListener;
    }
}
