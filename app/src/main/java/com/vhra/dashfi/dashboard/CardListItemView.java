package com.vhra.dashfi.dashboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vhra.dashfi.R;

public class CardListItemView extends RecyclerView.ViewHolder {
    private TextView mTitleText;
    private TextView mValueText;

    public CardListItemView(@NonNull View itemView) {
        super(itemView);

        mTitleText = itemView.findViewById(R.id.text_title);
        mValueText = itemView.findViewById(R.id.text_value);
    }

    public static CardListItemView create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card_item, parent, false);
        return new CardListItemView(view);
    }

    public void updateValues(CardItem cardItem) {
        if (cardItem == null) {
            return;
        }

        mTitleText.setText(cardItem.getTitle());
        mValueText.setText(cardItem.getValue());
    }
}
