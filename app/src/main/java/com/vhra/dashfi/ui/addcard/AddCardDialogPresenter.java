package com.vhra.dashfi.ui.addcard;

import com.vhra.dashfi.domain.UseCase;
import com.vhra.dashfi.domain.UseCaseHandler;
import com.vhra.dashfi.domain.model.CardDetail;

import static com.vhra.dashfi.domain.usecase.SaveValueUseCase.VALUE_SAVED_SUCCESSFULLY_STATE;

public class AddCardDialogPresenter {

    public interface View {
        void setPresenter(AddCardDialogPresenter presenter);
        void showTitle(String title);
        void showValue(String value);
        void showAddValueButton();
        void showSaveValueButton();
        void dismissView();
    }

    private View mView;
    private static final String STRING_EMPTY = "";

    private final CardDetail mCardDetail;
    private final UseCaseHandler mUseCaseHandler;
    private final UseCase<CardDetail, Integer> mSaveCardUseCase;

    public AddCardDialogPresenter(
            View view,
            CardDetail cardDetail,
            UseCaseHandler useCaseHandler,
            UseCase<CardDetail, Integer> saveCardUseCase) {
        mView = view;
        if (mView != null) mView.setPresenter(this);
        mCardDetail = cardDetail;
        mUseCaseHandler = useCaseHandler;
        mSaveCardUseCase = saveCardUseCase;
    }

    public void init() {
        if (mCardDetail == null) {
            startAddValueDialog();
        } else {
            startUpdateValueDialog();
        }
    }

    void onSaveValueClick(String title, String value) {
        CardDetail cardDetail = new CardDetailImpl(0, title, value);
        mUseCaseHandler.execute(mSaveCardUseCase, cardDetail, this::updateStateView);
    }

    private void updateStateView(final Integer state) {
        if (mView == null) return;

        if (isAddedValueStateValid(state)) {
            dismissView();
        }
    }

    private void dismissView() {
        mView.dismissView();
        mView = null;
    }

    private void startAddValueDialog() {
        mView.showTitle(STRING_EMPTY);
        mView.showValue(STRING_EMPTY);
        mView.showAddValueButton();
    }

    private void startUpdateValueDialog() {
        mView.showTitle(mCardDetail.getTitle());
        mView.showValue(String.valueOf(mCardDetail.getValue()));
        mView.showSaveValueButton();
    }

    private boolean isAddedValueStateValid(final Integer isValueAdded) {
        return VALUE_SAVED_SUCCESSFULLY_STATE == isValueAdded;
    }
}
