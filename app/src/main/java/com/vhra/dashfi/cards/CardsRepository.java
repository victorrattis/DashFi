package com.vhra.dashfi.cards;

import com.vhra.dashfi.CardDetail;

import java.util.List;

public class CardsRepository {
    private final CardsDataSource mCardsDataSource;

    public CardsRepository(CardsDataSource cardsDataSource) {
        mCardsDataSource = cardsDataSource;
    }

    public List<CardDetail> getCards() {
        return mCardsDataSource.getCards();
    }
}
