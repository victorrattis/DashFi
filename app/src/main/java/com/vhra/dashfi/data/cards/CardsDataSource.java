package com.vhra.dashfi.data.cards;

import com.vhra.dashfi.domain.model.CardDetail;
import com.vhra.dashfi.utils.Callback;

import java.util.List;

public interface CardsDataSource {
    void getCards(Callback<List<CardDetail>> callback);
}
