package com.vhra.dashfi.data.value;

import androidx.lifecycle.LifecycleOwner;

import com.vhra.dashfi.domain.model.ValueDetail;
import com.vhra.dashfi.utils.Callback;

import java.util.List;

public interface ValuesDataSource {
    void saveValue(ValueDetail valueDetail, Callback<ValueDetail> callback);
    void getAllValues(Callback<List<? extends ValueDetail>> callback);
    void getLiveAllValues(
            LifecycleOwner lifecycleOwner, Callback<List<? extends ValueDetail>> callback);
}
