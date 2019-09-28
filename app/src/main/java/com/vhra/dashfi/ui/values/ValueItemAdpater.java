package com.vhra.dashfi.ui.values;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vhra.dashfi.domain.model.ValueDetail;
import com.vhra.dashfi.utils.OnItemClickListener;

import java.util.List;

public class ValueItemAdpater extends RecyclerView.Adapter<ValueItemView> {
    private List<? extends ValueDetail> mValueDetailList;
    private OnItemClickListener<ValueDetail> mOnItemClickListener;

    @NonNull
    @Override
    public ValueItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ValueItemView.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ValueItemView holder, int position) {
        holder.setOnItemClickListener(mOnItemClickListener);
        holder.updateData(getItem(position));
    }

    @Override
    public int getItemCount() {
        return mValueDetailList != null ? mValueDetailList.size() : 0;
    }

    public void setOnItemClickListener(OnItemClickListener<ValueDetail>  listener) {
        mOnItemClickListener = listener;
    }

    public void replaceData(List<? extends ValueDetail> valueDetails) {
        mValueDetailList = valueDetails;
        notifyDataSetChanged();
    }

    private ValueDetail getItem(int position) {
        return mValueDetailList != null ? mValueDetailList.get(position) : null;
    }
}
