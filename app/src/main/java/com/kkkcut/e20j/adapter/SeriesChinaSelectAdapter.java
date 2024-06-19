package com.kkkcut.e20j.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.kkkcut.e20j.DbBean.ModelSeries;
import com.kkkcut.e20j.DbBean.china.ModelYearChina;
import com.kkkcut.e20j.androidquick.autolayout.utils.AutoUtils;
import com.kkkcut.e20j.us.R;
import java.util.List;

/* loaded from: classes.dex */
public class SeriesChinaSelectAdapter extends RecyclerView.Adapter<SeriesChinaSelectAdapter.ViewHolder> {
    private OnItemChildClickListener itemChildClickListener;
    private OnItemClickListener itemClickListener;
    protected Context mContext;
    protected List<ModelYearChina> mDatas;
    protected LayoutInflater mInflater;

    /* loaded from: classes.dex */
    public interface OnItemChildClickListener {
        void onItemChildClick(String str, ModelSeries modelSeries);
    }

    /* loaded from: classes.dex */
    public interface OnItemClickListener {
        void onItemClick(View view, int i, String str);
    }

    public SeriesChinaSelectAdapter(Context context) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
    }

    public List<ModelYearChina> getDatas() {
        return this.mDatas;
    }

    public void setDatas(List<ModelYearChina> list) {
        this.mDatas = list;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(this.mInflater.inflate(R.layout.item_years, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        int childCount = viewHolder.llContainer.getChildCount();
        if (childCount > 1) {
            viewHolder.llContainer.removeViews(1, childCount - 1);
        }
        final ModelYearChina modelYearChina = this.mDatas.get(i);
        String fromYear = modelYearChina.getFromYear();
        String toYear = modelYearChina.getToYear();
        if (yearIsBlank(fromYear)) {
            fromYear = "0";
        }
        if (yearIsBlank(toYear)) {
            toYear = "0";
        }
        final String str = fromYear + "~" + toYear;
        viewHolder.tvYearInfo.setText(str);
        viewHolder.llYears.setOnClickListener(new View.OnClickListener() { // from class: com.kkkcut.e20j.adapter.SeriesChinaSelectAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SeriesChinaSelectAdapter.this.m21xf1b8f3db(viewHolder, modelYearChina, str, view);
            }
        });
        if (getItemCount() == 1) {
            viewHolder.llYears.performClick();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onBindViewHolder$0$com-kkkcut-e20j-adapter-SeriesChinaSelectAdapter */
    public /* synthetic */ void m21xf1b8f3db(ViewHolder viewHolder, ModelYearChina modelYearChina, String str, View view) {
        OnItemClickListener onItemClickListener = this.itemClickListener;
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(viewHolder.itemView, modelYearChina.getId(), str);
        }
    }

    private boolean yearIsBlank(String str) {
        return TextUtils.isEmpty(str) || "0".equals(str);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<ModelYearChina> list = this.mDatas;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    /* loaded from: classes.dex */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView arrow;
        LinearLayout llContainer;
        LinearLayout llYears;
        TextView tvYearInfo;

        public ViewHolder(View view) {
            super(view);
            this.tvYearInfo = (TextView) view.findViewById(R.id.tv_year_info);
            this.arrow = (ImageView) view.findViewById(R.id.iv_arrow);
            this.llYears = (LinearLayout) view.findViewById(R.id.ll_years);
            this.llContainer = (LinearLayout) view.findViewById(R.id.ll_space_a);
            AutoUtils.auto(view);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.itemClickListener = onItemClickListener;
    }

    public void setOnItemChildClickListener(OnItemChildClickListener onItemChildClickListener) {
        this.itemChildClickListener = onItemChildClickListener;
    }
}