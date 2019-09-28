package com.vhra.dashfi.values;

import com.vhra.dashfi.domain.model.ValueDetail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MockValuesDataSource2 implements ValuesDataSource {
    private static List<ValueDetail> mValueDetailList;

    public MockValuesDataSource2() {
        init();
    }

    public List<ValueDetail> getValues() {
        return mValueDetailList;
    }

    @Override
    public void add(ValueDetail valueDetail) {
        mValueDetailList.add(valueDetail);
    }

    @Override
    public void updateValue(ValueDetail updatedValue) {
        ValueDetail valueDetail = mValueDetailList.get(updatedValue.getId());
        if (valueDetail != null) {
            mValueDetailList.set(valueDetail.getId(), updatedValue);
        }
    }

    private void init() {
        if (mValueDetailList != null) return;

        mValueDetailList = new ArrayList<>();
        createValue(0, "Item 1", 50, "gastos", "agosto", "2019");
        createValue(1, "Item 2", 120, "gastos", "agosto", "2019");
        createValue(2, "Item 3", 650, "ganho", "agosto", "2019");
        createValue(3, "Item 4", 53, "gastos", "agosto", "2019");
        createValue(4, "Item 5", 70, "gastos", "agosto", "2019", "alimentação");
        createValue(5, "Item 6", 51, "gastos", "agosto", "2019", "alimentação");
    }

    private void createValue(int id, String title, double value, String... a) {
        mValueDetailList.add(new ValueDetail() {
            @Override
            public int getId() {
                return id;
            }

            @Override
            public String getTitle() {
                return title;
            }

            @Override
            public double getValue() {
                return value;
            }

            @Override
            public List<String> getLabels() {
                return Arrays.asList(a);
            }
        });
    }
}
