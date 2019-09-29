package com.vhra.dashfi.ui.dashboard.card.simple;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.vhra.dashfi.domain.model.CardDetail;
import com.vhra.dashfi.R;
import com.vhra.dashfi.ui.dashboard.card.CardView;

public class CardSimpleView extends CardView
        implements CardSimplePresenter.View, View.OnClickListener {
    private final TextView mTitleText;
    private final TextView mValueText;

    private CardSimplePresenter mPresenter;

    private CardSimpleView(@NonNull View itemView) {
        super(itemView);

        mTitleText = itemView.findViewById(R.id.text_title);
        mValueText = itemView.findViewById(R.id.text_value);
    }

    public static CardSimpleView create(ViewGroup parent) {
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
        mTitleText.setOnClickListener(this);
        mValueText.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        if (mPresenter == null) return;

        switch (view.getId()) {
            case R.id.text_title:
                mPresenter.onTitleViewClick();
                break;
            case R.id.text_value:
                mPresenter.onValueViewClick();
                break;
        }
    }
}
