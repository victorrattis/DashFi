package com.vhra.dashfi.domain.model;

import com.vhra.dashfi.ui.dashboard.card.list.CardItem;

import java.util.List;

public interface CardDetail {
    int getId();
    String getTitle();
    String getValue();
    int getType();
    List<CardItem> getItems();
}
