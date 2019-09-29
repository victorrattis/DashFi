package com.vhra.dashfi.data.cards;

import android.content.Context;

import com.vhra.dashfi.data.room.AppDatabase;
import com.vhra.dashfi.data.room.card.CardEntity;
import com.vhra.dashfi.domain.model.CardDetail;
import com.vhra.dashfi.utils.Callback;
import com.vhra.dashfi.utils.ILog;

import java.util.List;
import java.util.concurrent.Executor;

public class CardsLocalDataSource implements CardsDataSource {

    private AppDatabase mAppDatabase;
    private final Executor mIoExecutor;
    private final ILog mLog;

    public CardsLocalDataSource(Context context, Executor ioExecutor, ILog log) {
        mIoExecutor = ioExecutor;
        mLog = log;

        // Currently, IOExecutor is using a thread due to concurrent problems which the
        // mAppDataBase is not loaded before of using it.
        final Context appContext = context.getApplicationContext();
        mIoExecutor.execute(() -> mAppDatabase = AppDatabase.getInstance(appContext));
    }

    @Override
    public void getCards(Callback<List<? extends CardDetail>> callback) {
        mIoExecutor.execute(() -> callback.onComplete(mAppDatabase.cardDao().getCards()));
    }

    @Override
    public void saveCard(CardDetail cardDetail, Callback<Boolean> callback) {
        mIoExecutor.execute(() -> {
            try {
                mAppDatabase.cardDao().insertCard(convertToValueEntity(cardDetail));
                callback.onComplete(true);
            } catch (Exception e) {
                e.printStackTrace();
                callback.onComplete(false);
            }
        });
    }

    private CardEntity convertToValueEntity(final CardDetail cardDetail) {
        return new CardEntity(
                cardDetail.getId(),
                cardDetail.getTitle(),
                cardDetail.getValue(),
                cardDetail.getType());
    }
}
