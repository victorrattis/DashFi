package com.vhra.dashfi.data.value.room;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LifecycleOwner;
import androidx.room.Room;

import com.vhra.dashfi.domain.model.ValueDetail;
import com.vhra.dashfi.data.value.ValuesDataSource;
import com.vhra.dashfi.utils.Callback;
import com.vhra.dashfi.utils.ILog;

import java.util.List;
import java.util.concurrent.Executor;

public class ValuesLocalDataSource implements ValuesDataSource {
    private static final String TAG = "ValuesLocalDataSource";
    private static final String DATABASE_NAME = "dashfi-db";

    private AppDatabase mAppDatabase;
    private final Executor mIoExecutor;
    private final ILog mLog;

    private final Handler mUiHandler;

    public ValuesLocalDataSource(Context context, Executor ioExecutor, ILog log) {
        mLog = log;
        mIoExecutor = ioExecutor;
        final Context appContext = context.getApplicationContext();
        // Currently, IOExecutor is using a thread due to concurrent problems which the
        // mAppDataBase is not loaded before of using it.
        mIoExecutor.execute(() -> initDatabase(appContext));


        mUiHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void saveValue(ValueDetail valueDetail, Callback<ValueDetail> callback) {
        mIoExecutor.execute(() -> {
            try {
                ValueEntity valueEntity = convertToValueEntity(valueDetail);
                mAppDatabase.valueDao().insertUsers(valueEntity);
                callback.onComplete(valueEntity);
            } catch (Exception e) {
                mLog.e(TAG, "Error saving value");
                callback.onComplete(null);
            }
        });
    }

    @Override
    public void getAllValues(Callback<List<? extends ValueDetail>> callback) {
        mIoExecutor.execute(() -> {
            callback.onComplete(mAppDatabase.valueDao().getAllValues());
        });
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

    private void initDatabase(Context context) {
        mAppDatabase = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        mLog.d(TAG, "initialize Room database!");
    }
}
