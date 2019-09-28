package com.vhra.dashfi.ui.values;

import com.vhra.dashfi.ui.home.HomeActivity;
import com.vhra.dashfi.domain.model.ValueDetail;
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

    private final View mView;
    private final UseCaseHandler mUseCaseHandler;
    private final UseCase<Void, List<? extends ValueDetail>> mGetValuesUseCase;
    private final HomeActivity.OpenAddValueView mOpenAddValueView;

    public ValuesPresenter(
            View view,
            UseCaseHandler useCaseHandler,
            UseCase<Void, List<? extends ValueDetail>> getValuesUseCase,
            HomeActivity.OpenAddValueView openAddValueView) {
        mView = view;
        if (mView != null) view.setPresenter(this);
        mUseCaseHandler = useCaseHandler;
        mGetValuesUseCase = getValuesUseCase;
        mOpenAddValueView = openAddValueView;
    }

    public void init() {
        mView.showLoading();
        mUseCaseHandler.execute(mGetValuesUseCase, null, this::showValuesOnView);
    }

    void onUpdateValueDetail(final ValueDetail valueDetail) {
        mOpenAddValueView.open(valueDetail);
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
