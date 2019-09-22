package com.vhra.dashfi.data;

import com.vhra.dashfi.ValueDetail;
import com.vhra.dashfi.utils.Callback;
import com.vhra.dashfi.utils.ILog;

public class ValuesRepository {
    private static final String TAG = "ValuesRepository";

    private ILog mLog;
    private ValuesDataSource mValuesDataSource;

    public ValuesRepository(ValuesDataSource localValueDataSource, ILog log) {
        mValuesDataSource = localValueDataSource;
        mLog = log;
    }

    public void saveValue(ValueDetail valueDetail, Callback<Boolean> callback) {
        if (mValuesDataSource == null) {
            mLog.e(TAG, "Values Data Source is null!");
            callback.onComplete(false);
            return;
        }

        mValuesDataSource.saveValue(valueDetail,
                (savedValueDetail) -> callback.onComplete(savedValueDetail != null));
    }
}
