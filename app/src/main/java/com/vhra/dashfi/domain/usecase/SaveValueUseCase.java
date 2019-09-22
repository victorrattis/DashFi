package com.vhra.dashfi.domain.usecase;

import com.vhra.dashfi.ValueDetail;
import com.vhra.dashfi.data.ValuesRepository;
import com.vhra.dashfi.domain.UseCase;
import com.vhra.dashfi.utils.Callback;
import com.vhra.dashfi.utils.ILog;

public class SaveValueUseCase implements UseCase<ValueDetail, Boolean> {
    private static final String TAG = "SaveValueUseCase";

    private ILog mLog;
    private ValuesRepository mValuesRepository;

    public SaveValueUseCase(ValuesRepository valuesRepository, ILog log) {
        mValuesRepository = valuesRepository;
        mLog = log;
    }

    @Override
    public void execute(ValueDetail valueDetail, Callback<Boolean> callback) {
        if (mValuesRepository == null) {
            mLog.e(TAG, "Values repository is null");
            callback.onComplete(false);
            return;
        }

        if (isValueValid(valueDetail)) {
            mValuesRepository.saveValue(valueDetail, callback);
        } else {
            mLog.e(TAG, "value is not valid!");
            callback.onComplete(false);
        }
    }

    private boolean isValueValid(ValueDetail valueDetail) {
        return valueDetail != null && isTitleValid(valueDetail.getTitle());
    }

    private boolean isTitleValid(final String title) {
        return title != null && !title.isEmpty();
    }
}
