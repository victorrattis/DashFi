package com.vhra.dashfi.values;

import com.vhra.dashfi.ValueDetail;

import java.util.List;

public interface ValuesDataSource {
    List<ValueDetail> getValues();
    void add(ValueDetail valueDetail);
    void updateValue(ValueDetail updatedValue);
}
