package com.vhra.dashfi.ui.addvalue;

import android.text.TextUtils;

import com.vhra.dashfi.ValueDetail;
import com.vhra.dashfi.domain.UseCase;
import com.vhra.dashfi.domain.UseCaseHandler;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AddValuePresenter {
    public interface View {
        void setPresenter(AddValuePresenter presenter);
        void dismissView();
        void showValueAdded();
        void showAddValueError();
        void showTitle(String title);
        void showValue(String value);
        void showLabels(String labels);
        void showAddValueButton();
        void showSaveValueButton();
    }

    private static final int NOT_SET_ITEM_ID = 0;
    private static final double DEFAULT_VALUE = 0.0;
    private static final String STRING_EMPTY = "";
    private static final String LABELS_DIVIDER_CHARACTER = ",";

    private View mView;
    private UseCaseHandler mUseCaseHandler;
    private UseCase<ValueDetail, Boolean> mAddValueUseCase;
    private ValueDetail mValueDetail;

    public AddValuePresenter(
            View view,
            ValueDetail valueDetail,
            UseCaseHandler useCaseHandler,
            UseCase<ValueDetail, Boolean> addValueUseCase) {
        mView = view;
        if (mView != null) mView.setPresenter(this);
        mUseCaseHandler = useCaseHandler;
        mAddValueUseCase = addValueUseCase;
        mValueDetail = valueDetail;
    }

    void init() {
        if (mValueDetail == null) {
            startAddValueDialog();
        } else {
            startUpdateValueDialog();
        }
    }

    void onSaveValueClick(String title, String valueText, String labelsText) {
        ValueDetail valueDetail = new ValueDetailImpl(
                mValueDetail != null ? mValueDetail.getId() : NOT_SET_ITEM_ID,
                title,
                convertToDouble(valueText, DEFAULT_VALUE),
                splitLabels(labelsText));

        mUseCaseHandler.execute(mAddValueUseCase, valueDetail, this::updateViewWithOnValuedAdded);
    }

    private void startAddValueDialog() {
        mView.showTitle(STRING_EMPTY);
        mView.showValue(STRING_EMPTY);
        mView.showLabels(STRING_EMPTY);
        mView.showAddValueButton();
    }

    private void startUpdateValueDialog() {
        mView.showTitle(mValueDetail.getTitle());
        mView.showValue(String.valueOf(mValueDetail.getValue()));
        mView.showLabels(TextUtils.join(LABELS_DIVIDER_CHARACTER, mValueDetail.getLabels()));
        mView.showSaveValueButton();
    }

    private void updateViewWithOnValuedAdded(final boolean isValueAdded) {
        if (mView == null) return;

        if (isValueAdded) {
            mView.showValueAdded();
            dismissView();
        } else {
            mView.showAddValueError();
        }
    }

    private void dismissView() {
        mView.dismissView();
        mView = null;
    }

    private List<String> splitLabels(final String labelsText) {
        return labelsText != null ?
                Arrays.asList(labelsText.split(LABELS_DIVIDER_CHARACTER)) :
                Collections.emptyList();
    }

    private double convertToDouble(final String text, final double defaultValue) {
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
