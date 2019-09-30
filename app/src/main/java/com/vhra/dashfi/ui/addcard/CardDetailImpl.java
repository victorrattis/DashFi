package com.vhra.dashfi.ui.addcard;

import com.vhra.dashfi.domain.model.CardDetail;
import com.vhra.dashfi.ui.dashboard.card.list.CardItem;

import java.util.List;

public class CardDetailImpl implements CardDetail {
    private final int mId;
    private final String mTitle;
    private final String mValue;

    public CardDetailImpl(int id, String title, String value) {
        mId = id;
        mTitle = title;
        mValue = value;
    }

    @Override
    public int getId() {
        return mId;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public String getValue() {
        return mValue;
    }

    @Override
    public int getType() {
        return 0;
    }

    @Override
    public List<CardItem> getItems() {
        return null;
    }
}
