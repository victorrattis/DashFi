package com.vhra.dashfi.ui.dashboard.card.simple;

import com.vhra.dashfi.domain.UseCase;
import com.vhra.dashfi.domain.UseCaseHandler;
import com.vhra.dashfi.domain.model.CardDetail;

import java.util.Locale;

public class CardSimplePresenter {
    public interface View {
        void setPresenter(CardSimplePresenter presenter);
        void showTitle(String title);
        void showValue(String value);
    }


    private final View mView;
    private final UseCaseHandler mUseCaseHandler;
    private final UseCase<String, Double> mSumValuesUseCase;

    public CardSimplePresenter(
            View view,
            UseCaseHandler useCaseHandler,
            UseCase<String, Double> sumValuesUseCase) {
        mView = view;
        mUseCaseHandler = useCaseHandler;
        mSumValuesUseCase = sumValuesUseCase;
        mView.setPresenter(this);
    }

    public void init(CardDetail cardDetail) {
        mView.showTitle(cardDetail.getTitle());

        String valueText = cardDetail.getValue();
        mUseCaseHandler.execute(mSumValuesUseCase, valueText, value -> {
            if (value == null) {
                showValue(0.0);
            } else {
                showValue(value);
            }
        });
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
}
