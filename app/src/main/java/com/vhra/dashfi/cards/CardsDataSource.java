package com.vhra.dashfi.cards;

import com.vhra.dashfi.CardDetail;

import java.util.List;

public interface CardsDataSource {
    List<CardDetail> getCards();
}
