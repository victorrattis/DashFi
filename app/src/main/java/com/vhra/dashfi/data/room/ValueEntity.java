package com.vhra.dashfi.data.room;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.vhra.dashfi.ValueDetail;

import java.util.List;

@Entity(tableName = "value")
public class ValueEntity implements ValueDetail {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private double value;
    private List<String> labels;

    public ValueEntity() {}

    @Ignore
    public ValueEntity(int id, String title, double value, List<String> labels) {
        this.id = id;
        this.title = title;
        this.value = value;
        this.labels = labels;
    }

    @Override
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public double getValue() {
        return this.value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public List<String> getLabels() {
        return this.labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }
}
