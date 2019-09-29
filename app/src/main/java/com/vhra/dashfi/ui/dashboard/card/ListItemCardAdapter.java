package com.vhra.dashfi.ui.dashboard.card;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListItemCardAdapter extends RecyclerView.Adapter<CardListItemView> {
    private List<CardItem> mCardItemList;

    @NonNull
    @Override
    public CardListItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return CardListItemView.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull CardListItemView holder, int position) {
        holder.updateValues(getItem(position));
    }

    @Override
    public int getItemCount() {
        return mCardItemList != null ? mCardItemList.size() : 0;
    }

    public void replaceData(List<CardItem> cardDetailList) {
        mCardItemList = cardDetailList;
        notifyDataSetChanged();
    }


    private CardItem getItem(int position) {
        return mCardItemList != null ? mCardItemList.get(position) : null;
    }
}
