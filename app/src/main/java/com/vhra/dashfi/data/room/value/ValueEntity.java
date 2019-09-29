package com.vhra.dashfi.data.room.value;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.vhra.dashfi.domain.model.ValueDetail;

import java.util.List;

@Entity(tableName = "value")
public class ValueEntity implements ValueDetail {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    private int mId;

    @ColumnInfo(name="title")
    private final String mTitle;

    @ColumnInfo(name="value")
    private final double mValue;

    @ColumnInfo(name="labels")
    private final List<String> mLabels;

    public ValueEntity(int id, String title, double value, List<String> labels) {
        mId = id;
        mTitle = title;
        mValue = value;
        mLabels = labels;
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
    public double getValue() {
        return mValue;
    }

    @Override
    public List<String> getLabels() {
        return mLabels;
    }

}
