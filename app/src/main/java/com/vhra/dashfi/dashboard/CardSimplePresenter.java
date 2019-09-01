package com.vhra.dashfi.dashboard;

import com.vhra.dashfi.CardDetail;
import com.vhra.dashfi.values.ValuesRepository;

public class CardSimplePresenter {
    public interface View {
        void setPresenter(CardSimplePresenter presenter);
        void showTitle(String title);
        void showValue(String value);
    }

    private static final String LABEL_SCHEME = "label:";

    private View mView;
    private ValuesRepository mValuesRepository;

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

    public void onTitleViewClick() {

    }

    public void onValueViewClick() {

    }

    private void showValue(double value) {
        mView.showValue(String.format("R$ %.2f", value));
    }

    private boolean hasLabelScheme(String text) {
        return text != null && text.contains(LABEL_SCHEME);
    }
}
