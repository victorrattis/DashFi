package com.vhra.dashfi.domain.model;

import com.vhra.dashfi.utils.Callback;

import java.util.List;

public interface IValuesRepository {
    void saveValue(ValueDetail valueDetail, Callback<Boolean> callback);
    void getAllValues(Callback<List<? extends ValueDetail>> callback);
}
