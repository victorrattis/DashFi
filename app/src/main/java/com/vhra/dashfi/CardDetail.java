package com.vhra.dashfi;

import com.vhra.dashfi.dashboard.CardItem;

import java.util.List;

public interface CardDetail {
    String getTitle();
    String getValue();
    int getType();
    List<CardItem> getItems();
}
