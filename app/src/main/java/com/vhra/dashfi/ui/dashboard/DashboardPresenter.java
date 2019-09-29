package com.vhra.dashfi.ui.dashboard;

import com.vhra.dashfi.domain.UseCase;
import com.vhra.dashfi.domain.UseCaseHandler;
import com.vhra.dashfi.domain.model.CardDetail;

import java.util.List;

public class DashboardPresenter {
    public interface View {
        void setPresenter(DashboardPresenter presenter);
        void showCards(List<? extends CardDetail> cards);
        void showLoading();
        void hideLoading();
        void showNoCards();
    }

    private final View mView;
    private final UseCaseHandler mUseCaseHandler;
    private final UseCase<Void, List<? extends CardDetail>> mGetCardsUseCase;

    public DashboardPresenter(
            final View view,
            UseCaseHandler useCaseHandler,
            UseCase<Void, List<? extends CardDetail>> getCardsUseCase) {
        mView = view;
        if (mView != null) mView.setPresenter(this);
        mUseCaseHandler = useCaseHandler;
        mGetCardsUseCase = getCardsUseCase;
    }

    public void init() {
        if (mGetCardsUseCase != null) {
            mView.showLoading();
            mUseCaseHandler.execute(mGetCardsUseCase, null, this::showCardsOnView);
        } else {
            mView.showNoCards();
        }
    }

    private void showCardsOnView(final List<? extends CardDetail> cards) {
        if (cards != null && !cards.isEmpty()) {
            mView.showCards(cards);
        } else {
            mView.showNoCards();
        }
        mView.hideLoading();
    }
}
