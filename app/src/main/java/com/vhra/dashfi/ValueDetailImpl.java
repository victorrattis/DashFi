package com.vhra.dashfi;

import java.util.Arrays;
import java.util.List;

public class ValueDetailImpl implements ValueDetail {
    private int mId;
    private String mTitle;
    private double mValue;
    private List<String> mLabels;

    public ValueDetailImpl(String title, double value, List<String> labels) {
        mTitle = title;
        mValue = value;
        mLabels = labels;
    }

    public ValueDetailImpl(ValueDetail valueDetail) {
        mTitle = valueDetail.getTitle();
        mValue = valueDetail.getValue();
        mLabels = valueDetail.getLabels();
    }

    public ValueDetailImpl(int id, String title, String value, String labels) {
        mId = id;
        mTitle = title;
        mValue = Double.parseDouble(value);
        mLabels = Arrays.asList(labels.split(","));
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

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setValue(double value) {
        mValue = value;
    }

    public void setLabels(List<String> labels) {
        mLabels = labels;
    }
}
