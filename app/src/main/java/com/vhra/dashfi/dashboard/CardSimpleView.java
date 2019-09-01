package com.vhra.dashfi.dashboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.vhra.dashfi.CardDetail;
import com.vhra.dashfi.R;

class CardSimpleView extends CardView implements CardSimplePresenter.View {
    private TextView mTitleText;
    private TextView mValueText;

    private CardSimplePresenter mPresenter;

    protected CardSimpleView(@NonNull View itemView) {
        super(itemView);

        mTitleText = itemView.findViewById(R.id.text_title);
        mValueText = itemView.findViewById(R.id.text_value);
    }

    static CardSimpleView create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card, parent, false);
        return new CardSimpleView(view);
    }

    @Override
    public void updateValues(CardDetail cardDetail) {
        if (cardDetail == null) {
            return;
        }

        if (mPresenter != null) mPresenter.init(cardDetail);

        mTitleText.setOnClickListener((view) -> {
            if (mPresenter != null) mPresenter.onTitleViewClick();
        });

        mValueText.setOnClickListener((view) -> {
            if (mPresenter != null) mPresenter.onValueViewClick();
        });
    }

    @Override
    public void setPresenter(CardSimplePresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showTitle(String title) {
        mTitleText.setText(title);
    }

    @Override
    public void showValue(String value) {
        mValueText.setText(value);
    }
}
