package com.vhra.dashfi.ui.values;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vhra.dashfi.R;
import com.vhra.dashfi.domain.model.ValueDetail;
import com.vhra.dashfi.utils.OnItemClickListener;

public class ValueItemView extends RecyclerView.ViewHolder {
    private TextView mTitleText;
    private TextView mValueText;
    private TextView mLabelsText;

    private OnItemClickListener<ValueDetail> mOnItemClickListener;

    public ValueItemView(@NonNull View itemView) {
        super(itemView);
        mTitleText = itemView.findViewById(R.id.text_title);
        mValueText = itemView.findViewById(R.id.text_value);
        mLabelsText = itemView.findViewById(R.id.text_labels);
    }

    public static ValueItemView create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_value, parent, false);
        return new ValueItemView(view);
    }

    public void setOnItemClickListener(OnItemClickListener<ValueDetail>  listener) {
        mOnItemClickListener = listener;
    }

    public void updateData(ValueDetail valueDetail) {
        if (valueDetail == null) return;

        itemView.setOnClickListener(view -> {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemCkick(valueDetail);
            }
        });

        mTitleText.setText(valueDetail.getTitle());
        mValueText.setText(String.format("R$ %.2f", valueDetail.getValue()));
        mLabelsText.setText(valueDetail.getLabels().toString());
    }
}
