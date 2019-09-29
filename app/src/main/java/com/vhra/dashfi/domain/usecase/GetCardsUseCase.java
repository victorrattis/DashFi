package com.vhra.dashfi.domain.usecase;

import com.vhra.dashfi.domain.UseCase;
import com.vhra.dashfi.domain.model.CardDetail;
import com.vhra.dashfi.domain.model.ICardsRepository;
import com.vhra.dashfi.utils.Callback;
import com.vhra.dashfi.utils.ILog;

import java.util.List;

public class GetCardsUseCase implements UseCase<Void, List<? extends CardDetail>> {
    private static final String TAG  = "GetCardsUseCase";

    private final ICardsRepository mCardsRepository;
    private final ILog mLog;

    public GetCardsUseCase(ICardsRepository cardsRepository, ILog log) {
        mCardsRepository = cardsRepository;
        mLog = log;
    }

    @Override
    public void execute(Void input, Callback<List<? extends CardDetail>> callback) {
        if (mCardsRepository == null) {
            mLog.e(TAG, "Error executing use case: Cards repository is null");
            callback.onComplete(null);
            return;
        }

        mCardsRepository.getCards(callback::onComplete);
    }
}
