package com.vhra.dashfi.data.cards;

import com.vhra.dashfi.domain.model.CardDetail;
import com.vhra.dashfi.domain.model.ICardsRepository;
import com.vhra.dashfi.utils.Callback;

import java.util.List;

public class CardsRepository implements ICardsRepository {
    private final CardsDataSource mCardsDataSource;

    public CardsRepository(CardsDataSource cardsDataSource) {
        mCardsDataSource = cardsDataSource;
    }

    @Override
    public void getCards(Callback<List<? extends CardDetail>> callback) {
        if (mCardsDataSource != null) {
            mCardsDataSource.getCards(callback);
        } else {
            callback.onComplete(null);
        }
    }

    @Override
    public void saveCard(CardDetail cardDetail, Callback<Boolean> callback) {
        if (mCardsDataSource != null) {
            mCardsDataSource.saveCard(cardDetail, callback);
        } else {
            callback.onComplete(false);
        }
    }
}
