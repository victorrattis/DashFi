package com.vhra.dashfi.ui.addvalue;

import com.vhra.dashfi.ValueDetail;

import java.util.List;

class ValueDetailImpl implements ValueDetail {
    private int mId;
    private String mTitle;
    private double mValue;
    private List<String> mLabels;

    ValueDetailImpl(int id, String title, double value, List<String> labels) {
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
