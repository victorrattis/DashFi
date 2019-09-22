package com.vhra.dashfi.data;

import com.vhra.dashfi.ValueDetail;
import com.vhra.dashfi.utils.Callback;

public interface ValuesDataSource {
    void saveValue(ValueDetail valueDetail, Callback<ValueDetail> callback);
}
