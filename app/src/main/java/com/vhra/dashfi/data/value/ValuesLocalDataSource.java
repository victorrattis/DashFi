package com.vhra.dashfi.data.value;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LifecycleOwner;

import com.vhra.dashfi.data.room.AppDatabase;
import com.vhra.dashfi.data.room.value.ValueEntity;
import com.vhra.dashfi.domain.model.ValueDetail;
import com.vhra.dashfi.utils.Callback;
import com.vhra.dashfi.utils.ILog;

import java.util.List;
import java.util.concurrent.Executor;

public class ValuesLocalDataSource implements ValuesDataSource {
    private static final String TAG = "ValuesLocalDataSource";

    private AppDatabase mAppDatabase;
    private final Executor mIoExecutor;
    private final ILog mLog;

    private final Handler mUiHandler;

    public ValuesLocalDataSource(Context context, Executor ioExecutor, ILog log) {
        mLog = log;
        mIoExecutor = ioExecutor;
        mUiHandler = new Handler(Looper.getMainLooper());

        // Currently, IOExecutor is using a thread due to concurrent problems which the
        // mAppDataBase is not loaded before of using it.
        final Context appContext = context.getApplicationContext();
        mIoExecutor.execute(() -> mAppDatabase = AppDatabase.getInstance(appContext));
    }

    @Override
    public void saveValue(ValueDetail valueDetail, Callback<ValueDetail> callback) {
        mIoExecutor.execute(() -> {
            try {
                ValueEntity valueEntity = convertToValueEntity(valueDetail);
                mAppDatabase.valueDao().insertValue(valueEntity);
                callback.onComplete(valueEntity);
            } catch (Exception e) {
                mLog.e(TAG, "Error saving value");
                callback.onComplete(null);
            }
        });
    }

    @Override
    public void getAllValues(Callback<List<? extends ValueDetail>> callback) {
        mIoExecutor.execute(() -> callback.onComplete(mAppDatabase.valueDao().getAllValues()));
    }

    @Override
    public void getLiveAllValues(
            LifecycleOwner lifecycleOwner, Callback<List<? extends ValueDetail>> callback) {
        mUiHandler.post(() -> {
            try {
                mAppDatabase.valueDao()
                        .getLiveAllValues()
                        .observe(lifecycleOwner, callback::onComplete);
            } catch (Exception e) {
                mLog.e(TAG, "Error get all values", e);
                callback.onComplete(null);
            }
        });
    }

    private ValueEntity convertToValueEntity(ValueDetail valueDetail) {
        return new ValueEntity(
                valueDetail.getId(),
                valueDetail.getTitle(),
                valueDetail.getValue(),
                valueDetail.getLabels());
    }
}
