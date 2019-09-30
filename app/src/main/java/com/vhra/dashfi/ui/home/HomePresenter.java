package com.vhra.dashfi.ui.home;

import com.vhra.dashfi.domain.model.CardDetail;
import com.vhra.dashfi.domain.model.ValueDetail;

class HomePresenter {

    public interface View {
        void setPresenter(HomePresenter presenter);
        void showDefaultDashBoard();
        void showAddValueDialog(final ValueDetail valueDetail);
        void showAddCardDialog(final CardDetail cardDetail);
        void showAllValues();
    }

    private final View mView;

    HomePresenter(View view) {
        mView = view;
        if (mView != null) mView.setPresenter(this);
    }

    public void init() {
        mView.showDefaultDashBoard();
    }

    void onAddValueOptionClick() {
        mView.showAddValueDialog(null);
    }

    void onAllValuesOptionClick() {
        mView.showAllValues();
    }

    void onDefaultDashboardOptionClick() {
        mView.showDefaultDashBoard();
    }

    public void onAddCardOptionClick() {
        mView.showAddCardDialog(null);
    }

    void showValueUpdateDialog(final ValueDetail valueDetail) {
        mView.showAddValueDialog(valueDetail);
    }
}
