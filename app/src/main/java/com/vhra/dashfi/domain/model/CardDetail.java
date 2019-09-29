package com.vhra.dashfi.domain.model;

import com.vhra.dashfi.ui.dashboard.card.CardItem;

import java.util.List;

public interface CardDetail {
    String getTitle();
    String getValue();
    int getType();
    List<CardItem> getItems();
}
