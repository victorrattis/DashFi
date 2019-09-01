package com.vhra.dashfi.cards;

import com.vhra.dashfi.CardDetail;
import com.vhra.dashfi.dashboard.CardItem;

import java.util.ArrayList;
import java.util.List;

public class MockCardsDataSource2 implements CardsDataSource {
    private List<CardDetail> mCardDetails;

    public MockCardsDataSource2() {
        mCardDetails = new ArrayList<>();

        mCardDetails.add(createCardDetail(
                "Saldo de Agosto/2019", "label:agosto,receita,2019-agosto,gastos,2019", 0, null));

        List<CardItem> cardItems = new ArrayList<>();
        cardItems.add(
                createCardItem("Ganhos de Agosto/2019", "label:agosto,receita,2019", "one"));
        cardItems.add(
                createCardItem("Mastercard Free", "label:agosto,gastos,mastercard,2019", "one"));
        cardItems.add(createCardItem("Nubank", "label:agosto,gastos,nubank,2019", "one"));
        mCardDetails.add(createCardDetail(
                "Gastos de Agosto/2019", "", /*"label:agosto,receita,2019-agosto,gastos,2019",*/ 1, cardItems));

        List<CardItem> categoryCardItems = new ArrayList<>();
        categoryCardItems.add(createCardItem("Alimentação", "label:alimentação", "one"));
        categoryCardItems.add(createCardItem("Transporte", "label:agosto,gastos,transporte,2019", "one"));
        categoryCardItems.add(createCardItem("Casa Geral", "label:agosto,gastos,casa_geral,2019", "one"));
        categoryCardItems.add(createCardItem("Transporte", "label:agosto,gastos,transporte,2019", "one"));
        categoryCardItems.add(createCardItem("Medicamento", "label:agosto,gastos,medicamento,2019", "one"));
        categoryCardItems.add(createCardItem("Meu Uber", "label:agosto,gastos,uber_eu,2019", "one"));
        categoryCardItems.add(createCardItem("Meu PC", "label:agosto,gastos,meupc,2019", "one"));
        categoryCardItems.add(createCardItem("Almoço", "label:agosto,gastos,almoço,2019", "one"));
        categoryCardItems.add(createCardItem("Inglês", "label:agosto,gastos,inglês,2019", "one"));
        mCardDetails.add(createCardDetail("Gategorias", "", 1, categoryCardItems));

        List<CardItem> freeCardItems = new ArrayList<>();
        freeCardItems.add(createCardItem("", "label:agosto,gastos,mastercard,2019", "list"));
        mCardDetails.add(createCardDetail(
                "MasterCard Free Agosto/2019", "label:agosto,gastos,mastercard,2019", 1, freeCardItems));

        List<CardItem> nubankCardItems = new ArrayList<>();
        nubankCardItems.add(createCardItem("", "label:agosto,gastos,nubank,2019", "list"));
        mCardDetails.add(createCardDetail(
                "Nubank Agosto/2019", "label:agosto,gastos,nubank,2019", 1, nubankCardItems));

        List<CardItem> eduardoCardItems = new ArrayList<>();
        eduardoCardItems.add(createCardItem("", "label:agosto,gastos,vinicius,2019", "list"));
        mCardDetails.add(createCardDetail(
                "Gasto de Vinicius Agosto/2019", "label:agosto,gastos,vinicius,2019", 1, eduardoCardItems));

    }

    @Override
    public List<CardDetail> getCards() {
        return mCardDetails;
    }

    private CardDetail createCardDetail(
            String title, String value, int type, List<CardItem> cardItemList) {
        return new CardDetail() {
            @Override
            public String getTitle() {
                return title;
            }

            @Override
            public String getValue() {
                return value;
            }

            @Override
            public int getType() {
                return type;
            }

            @Override
            public List<CardItem> getItems() {
                return cardItemList;
            }
        };
    }

    private CardItem createCardItem(String title, String value, String type) {
        return new CardItem() {
            @Override
            public String getTitle() {
                return title;
            }

            @Override
            public String getValue() {
                return value;
            }

            @Override
            public String getType() {
                return type;
            }
        };
    }
}
