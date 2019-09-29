package com.vhra.dashfi.data.cards;

import com.vhra.dashfi.domain.model.CardDetail;
import com.vhra.dashfi.utils.Callback;

import java.util.List;

public interface CardsDataSource {
    void getCards(Callback<List<? extends CardDetail>> callback);
    void saveCard(CardDetail cardDetail, Callback<Boolean> callback);
}
