package com.vhra.dashfi.domain.model;

import com.vhra.dashfi.utils.Callback;

import java.util.List;

public interface ICardsRepository {
    void getCards(Callback<List<? extends CardDetail>> callback);
    void saveCard(CardDetail cardDetail, Callback<Boolean> callback);
}
