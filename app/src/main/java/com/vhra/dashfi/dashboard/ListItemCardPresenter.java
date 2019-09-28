package com.vhra.dashfi.dashboard;

import android.text.TextUtils;

import com.vhra.dashfi.CardDetail;
import com.vhra.dashfi.domain.model.ValueDetail;
import com.vhra.dashfi.values.ValuesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListItemCardPresenter {
    public interface View {
        void setPresenter(ListItemCardPresenter presenter);
        void showTitle(String title);
        void showValue(String value);
        void hideValue();
        void showItems(List<CardItem> items);
    }

    private static final String LABEL_SCHEME = "label:";

    private final View mView;
    private final ValuesRepository mValuesRepository;

    public ListItemCardPresenter(View view, ValuesRepository valuesRepository) {
        mView = view;
        mValuesRepository = valuesRepository;
        mView.setPresenter(this);
    }

    void init(CardDetail cardDetail) {
        mView.showTitle(cardDetail.getTitle());

        String valueText = cardDetail.getValue();
        if (hasLabelScheme(valueText)) {
            mValuesRepository.sumValueForLabels(valueText, this::showValue);
        } else if (TextUtils.isEmpty(valueText)) {
            mView.hideValue();
        } else {
            mView.showValue(valueText);
        }

        List<CardItem> result = new ArrayList<>();
        for (CardItem cardItem : cardDetail.getItems()) {
            if ("list".equals(cardItem.getType())) {
                mValuesRepository.getValuesForLabels(cardItem.getValue(), data -> {
                    for (ValueDetail valueDetail : data) {
                        result.add(new CardItem() {
                            @Override
                            public String getTitle() {
                                return valueDetail.getTitle();
                            }

                            @Override
                            public String getValue() {
                                return String.format(Locale.ROOT, "R$ %.2f", (valueDetail.getValue()));
                            }

                            @Override
                            public String getType() {
                                return "one";
                            }
                        });
                    }
                    mView.showItems(result);
                });
            } else if ("one".equals(cardItem.getType())) {
                mValuesRepository.sumValueForLabels(cardItem.getValue(), value -> {
                    result.add(new CardItem() {
                        @Override
                        public String getTitle() {
                            return cardItem.getTitle();
                        }

                        @Override
                        public String getValue() {
                            return String.format(Locale.ROOT, "R$ %.2f", (value));
                        }

                        @Override
                        public String getType() {
                            return "one";
                        }
                    });
                    mView.showItems(result);
                });
            }
        }
    }

    void onTitleViewClick() {
        // TODO: Implement it!
    }

    void onValueViewClick() {
        // TODO: Implement it!
    }

    private void showValue(double value) {
        mView.showValue(String.format(Locale.ROOT, "R$ %.2f", value));
    }

    private boolean hasLabelScheme(String text) {
        return text != null && text.contains(LABEL_SCHEME);
    }
}
