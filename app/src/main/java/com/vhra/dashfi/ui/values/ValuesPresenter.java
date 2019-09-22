package com.vhra.dashfi.ui.values;

import com.vhra.dashfi.ValueDetail;
import com.vhra.dashfi.domain.UseCase;
import com.vhra.dashfi.domain.UseCaseHandler;

import java.util.List;

public class ValuesPresenter {
    public interface View {
        void setPresenter(ValuesPresenter presenter);
        void showValues(List<? extends ValueDetail> values);
        void showEmptyValues();
        void showLoading();
        void hideLoading();
        void showErrorGettingValues();
    }

    private View mView;
    private UseCaseHandler mUseCaseHandler;
    private UseCase<Void, List<? extends ValueDetail>> mGetValuesUseCase;

    public ValuesPresenter(
            View view,
            UseCaseHandler useCaseHandler,
            UseCase<Void, List<? extends ValueDetail>> getValuesUseCase) {
        mView = view;
        if (mView != null) view.setPresenter(this);
        mUseCaseHandler = useCaseHandler;
        mGetValuesUseCase = getValuesUseCase;
    }

    public void init() {
        mView.showLoading();
        mUseCaseHandler.execute(mGetValuesUseCase, null, this::showValuesOnView);
    }

    public void onUpdateValueDetail() {

    }

    private void showValuesOnView(final List<? extends ValueDetail> valueDetailList) {
        if (mView != null) {
            if (valueDetailList != null) {
                mView.showValues(valueDetailList);
            } else {
                mView.showEmptyValues();
                mView.showErrorGettingValues();
            }

            mView.hideLoading();
        }
    }
}
