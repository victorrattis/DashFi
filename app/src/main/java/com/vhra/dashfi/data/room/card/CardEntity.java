package com.vhra.dashfi.data.room.card;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.vhra.dashfi.domain.model.CardDetail;
import com.vhra.dashfi.ui.dashboard.card.list.CardItem;

import java.util.List;

@Entity(tableName = "card")
public class CardEntity implements CardDetail {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    private int mId;

    @ColumnInfo(name="title")
    private final String mTitle;

    @ColumnInfo(name="value")
    private final String mValue;

    @ColumnInfo(name="type")
    private final int mType;

    public CardEntity(int id, String title, String value, int type) {
        mId = id;
        mTitle = title;
        mValue = value;
        mType = type;
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
        return mType;
    }

    @Override
    public List<CardItem> getItems() {
        return null;
    }
}
