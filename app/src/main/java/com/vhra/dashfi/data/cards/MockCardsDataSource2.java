package com.vhra.dashfi.data.cards;

import com.vhra.dashfi.domain.model.CardDetail;
import com.vhra.dashfi.ui.dashboard.card.list.CardItem;
import com.vhra.dashfi.utils.Callback;

import java.util.ArrayList;
import java.util.List;

public class MockCardsDataSource2 implements CardsDataSource {
    private final List<CardDetail> mCardDetails;

    public MockCardsDataSource2() {
        mCardDetails = new ArrayList<>();

//        mCardDetails.add(createCardDetail(
//                "Saldo de Agosto/2019", "label:agosto,receita,2019-agosto,gastos,2019", 0, null));

        mCardDetails.add(createCardDetail(
                "Receita de Agosto/2019", "label:agosto,receita,2019", 0, null));

        mCardDetails.add(createCardDetail(
                "Gastos de Agosto/2019", "label:gastos", 0, null));

        List<CardItem> cardItems = new ArrayList<>();
        cardItems.add(createCardItem("Mastercard Free", "label:agosto,gastos,mastercard,2019", "one"));
        cardItems.add(createCardItem("Nubank", "label:agosto,gastos,nubank,2019", "one"));
        cardItems.add(createCardItem("Claro", "label:agosto,gastos,claro,2019", "one"));
        cardItems.add(createCardItem("Vivo", "label:agosto,gastos,vivo,2019", "one"));
        cardItems.add(createCardItem("Unibratec", "label:agosto,gastos,unibratec,2019", "one"));
        cardItems.add(createCardItem("Aluguel do AP", "label:agosto,gastos,aluguel,2019", "one"));
        mCardDetails.add(createCardDetail("Gastos de Agosto/2019", "label:agosto,gastos,2019", 1, cardItems));

        List<CardItem> categoryCardItems = new ArrayList<>();
        categoryCardItems.add(createCardItem("Alimentação", "label:alimentação", "one"));
        categoryCardItems.add(createCardItem("Transporte", "label:agosto,gastos,transporte,2019", "one"));
        categoryCardItems.add(createCardItem("Casa Geral", "label:agosto,gastos,casa_geral,2019", "one"));
        categoryCardItems.add(createCardItem("Medicamento", "label:agosto,gastos,medicamento,2019", "one"));
        categoryCardItems.add(createCardItem("Meu Uber", "label:agosto,gastos,uber_eu,2019", "one"));
        categoryCardItems.add(createCardItem("Meu PC", "label:agosto,gastos,meupc,2019", "one"));
        categoryCardItems.add(createCardItem("Almoço", "label:agosto,gastos,almoço,2019", "one"));
        categoryCardItems.add(createCardItem("Inglês", "label:agosto,gastos,inglês,2019", "one"));
        mCardDetails.add(createCardDetail("Categorias", "", 1, categoryCardItems));

        List<CardItem> freeCardItems = new ArrayList<>();
        freeCardItems.add(createCardItem("", "label:agosto,gastos,mastercard,2019", "list"));
        mCardDetails.add(createCardDetail("MasterCard Free Agosto/2019", "label:agosto,gastos,mastercard,2019", 1, freeCardItems));

        List<CardItem> nubankCardItems = new ArrayList<>();
        nubankCardItems.add(createCardItem("", "label:agosto,gastos,nubank,2019", "list"));
        mCardDetails.add(createCardDetail("Nubank Agosto/2019", "label:agosto,gastos,nubank,2019", 1, nubankCardItems));

        List<CardItem> eduardoCardItems = new ArrayList<>();
        eduardoCardItems.add(createCardItem("", "label:agosto,gastos,vinicius,2019", "list"));
        mCardDetails.add(createCardDetail("Gasto de Vinicius Agosto/2019", "label:agosto,gastos,vinicius,2019", 1, eduardoCardItems));

    }

    @Override
    public void getCards(Callback<List<? extends CardDetail>> callback) {
        callback.onComplete(mCardDetails);
    }

    @Override
    public void saveCard(CardDetail cardDetail, Callback<Boolean> callback) {
        mCardDetails.add(cardDetail);
        callback.onComplete(true);
    }

    private CardDetail createCardDetail(
            String title, String value, int type, List<CardItem> cardItemList) {
        return new CardDetail() {
            @Override
            public int getId() {
                return 0;
            }

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
