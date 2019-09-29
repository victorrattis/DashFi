package com.vhra.dashfi.data.cards;

import com.vhra.dashfi.domain.model.CardDetail;
import com.vhra.dashfi.ui.dashboard.card.list.CardItem;
import com.vhra.dashfi.utils.Callback;

import java.util.ArrayList;
import java.util.List;

public class MockCardsDataSource implements CardsDataSource {
    private final List<CardDetail> mCardDetails;

    public MockCardsDataSource() {
        mCardDetails = new ArrayList<>();
        mCardDetails.add(new CardDetail() {
            @Override
            public String getTitle() {
                return "Saldo";
            }

            @Override
            public String getValue() {
                return "label:ganho,2019-gastos,2019";
            }

            @Override
            public int getType() {
                return 0;
            }

            @Override
            public List<CardItem> getItems() {
                return null;
            }
        });
        mCardDetails.add(new CardDetail() {
            @Override
            public String getTitle() {
                return "Ganho de Agosto/2019";
            }

            @Override
            public String getValue() {
                return "label:ganho,2019";
            }

            @Override
            public int getType() {
                return 0;
            }

            @Override
            public List<CardItem> getItems() {
                return null;
            }
        });
        mCardDetails.add(new CardDetail() {
            @Override
            public String getTitle() {
                return "Gastos de Agosto/2019";
            }

            @Override
            public String getValue() {
                return "label:gastos,2019";
            }

            @Override
            public int getType() {
                return 0;
            }

            @Override
            public List<CardItem> getItems() {
                return null;
            }
        });

        List<CardItem> cardItems = new ArrayList<>();
        cardItems.add(new CardItem() {
            @Override
            public String getTitle() {
                return "Gastos/2019";
            }

            @Override
            public String getValue() {
                return "label:gastos,2019";
            }

            @Override
            public String getType() {
                return "one";
            }
        });
        cardItems.add(new CardItem() {
            @Override
            public String getTitle() {
                return "Item 1";
            }

            @Override
            public String getValue() {
                return "label:gastos,2019";
            }

            @Override
            public String getType() {
                return "list";
            }
        });
        mCardDetails.add(new CardDetail() {
            @Override
            public String getTitle() {
                return "Gastos de Agosto/2019";
            }

            @Override
            public String getValue() {
                return "";//"label:gastos,2019";
            }

            @Override
            public int getType() {
                return 1;
            }

            @Override
            public List<CardItem> getItems() {
                return cardItems;
            }
        });
    }

    @Override
    public void getCards(Callback<List<CardDetail>> callback) {
        callback.onComplete(mCardDetails);
    }
}
