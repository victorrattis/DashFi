package com.vhra.dashfi.data.value;

import com.vhra.dashfi.domain.model.IValuesRepository;
import com.vhra.dashfi.domain.model.ValueDetail;
import com.vhra.dashfi.utils.Callback;
import com.vhra.dashfi.utils.ILog;

import java.util.List;

public class ValuesRepository implements IValuesRepository {
    private static final String TAG = "ValuesRepository";

    private final ILog mLog;
    private final ValuesDataSource mValuesDataSource;

    public ValuesRepository(ValuesDataSource localValueDataSource, ILog log) {
        mValuesDataSource = localValueDataSource;
        mLog = log;
    }

    @Override
    public void saveValue(ValueDetail valueDetail, Callback<Boolean> callback) {
        if (mValuesDataSource == null) {
            mLog.e(TAG, "Values Data Source is null!");
            callback.onComplete(false);
            return;
        }

        mValuesDataSource.saveValue(valueDetail,
                savedValueDetail -> callback.onComplete(savedValueDetail != null));
    }

    @Override
    public void getAllValues(Callback<List<? extends ValueDetail>> callback) {
        if (mValuesDataSource == null) {
            mLog.e(TAG, "Values Data Source is null!");
            callback.onComplete(null);
            return;
        }

        mValuesDataSource.getAllValues(callback);
    }
}
