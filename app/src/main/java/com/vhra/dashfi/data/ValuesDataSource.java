package com.vhra.dashfi.data;

import com.vhra.dashfi.ValueDetail;
import com.vhra.dashfi.utils.Callback;

import java.util.List;

public interface ValuesDataSource {
    void saveValue(ValueDetail valueDetail, Callback<ValueDetail> callback);
    void getAllValues(Callback<List<? extends ValueDetail>> callback);
}
