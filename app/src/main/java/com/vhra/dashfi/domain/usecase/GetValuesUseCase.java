package com.vhra.dashfi.domain.usecase;

import com.vhra.dashfi.ValueDetail;
import com.vhra.dashfi.data.ValuesRepository;
import com.vhra.dashfi.domain.UseCase;
import com.vhra.dashfi.utils.Callback;
import com.vhra.dashfi.utils.ILog;

import java.util.List;

public class GetValuesUseCase implements UseCase<Void, List<? extends ValueDetail>> {
    private static final String TAG = "GetValuesUseCase";

    private ILog mLog;
    private ValuesRepository mValuesRepository;

    public GetValuesUseCase(ValuesRepository valuesRepository, ILog log) {
        mLog = log;
        mValuesRepository = valuesRepository;
    }

    @Override
    public void execute(Void input, Callback<List<? extends ValueDetail>> callback) {
        if (mValuesRepository == null) {
            mLog.e(TAG, "Values repository is null");
            callback.onComplete(null);
            return;
        }

        mValuesRepository.getAllValues(callback);
    }
}
