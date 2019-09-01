package com.vhra.dashfi.dashboard;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vhra.dashfi.CardDetail;

public abstract class CardView extends RecyclerView.ViewHolder {
    public CardView(@NonNull View itemView) {
        super(itemView);
    }

    public abstract void updateValues(CardDetail cardDetail);
}
