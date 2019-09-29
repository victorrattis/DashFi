package com.vhra.dashfi.domain.model;

import com.vhra.dashfi.utils.Callback;

import java.util.List;

public interface ICardsRepository {
    void getCards(Callback<List<CardDetail>> callback);
}
