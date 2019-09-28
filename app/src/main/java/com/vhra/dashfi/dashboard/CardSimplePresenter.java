package com.vhra.dashfi.dashboard;

import com.vhra.dashfi.CardDetail;
import com.vhra.dashfi.values.ValuesRepository;

import java.util.Locale;

public class CardSimplePresenter {
    public interface View {
        void setPresenter(CardSimplePresenter presenter);
        void showTitle(String title);
        void showValue(String value);
    }

    private static final String LABEL_SCHEME = "label:";

    private final View mView;
    private final ValuesRepository mValuesRepository;

    public CardSimplePresenter(View view, ValuesRepository valuesRepository) {
        mView = view;
        mValuesRepository = valuesRepository;
        mView.setPresenter(this);
    }

    void init(CardDetail cardDetail) {
        mView.showTitle(cardDetail.getTitle());

        String valueText = cardDetail.getValue();
        if (hasLabelScheme(valueText)) {
            mValuesRepository.sumValueForLabels(valueText, this::showValue);
        } else {
            mView.showValue(valueText);
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
