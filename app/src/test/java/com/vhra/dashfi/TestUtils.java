package com.vhra.dashfi;

import com.vhra.dashfi.domain.model.ValueDetail;

import java.util.List;

public class TestUtils {

    public static ValueDetail createValueDetail(String title, double value, List<String> labels) {
        return new ValueDetail() {
            @Override
            public int getId() {
                return 0;
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
                return labels;
            }
        };
    }
}
