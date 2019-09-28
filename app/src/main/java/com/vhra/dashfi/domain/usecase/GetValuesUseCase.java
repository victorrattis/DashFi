package com.vhra.dashfi.domain.usecase;

import com.vhra.dashfi.domain.model.IValuesRepository;
import com.vhra.dashfi.domain.model.ValueDetail;
import com.vhra.dashfi.domain.UseCase;
import com.vhra.dashfi.utils.Callback;
import com.vhra.dashfi.utils.ILog;

import java.util.List;

public class GetValuesUseCase implements UseCase<Void, List<? extends ValueDetail>> {
    private static final String TAG = "GetValuesUseCase";

    private final ILog mLog;
    private final IValuesRepository mValuesRepository;

    public GetValuesUseCase(IValuesRepository valuesRepository, ILog log) {
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
