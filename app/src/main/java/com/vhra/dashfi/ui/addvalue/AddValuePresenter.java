package com.vhra.dashfi.ui.addvalue;

import android.text.TextUtils;

import com.vhra.dashfi.domain.model.ValueDetail;
import com.vhra.dashfi.domain.UseCase;
import com.vhra.dashfi.domain.UseCaseHandler;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.vhra.dashfi.domain.usecase.SaveValueUseCase.UNSAVED_ERROR_EMPTY_TITLE_STATE;
import static com.vhra.dashfi.domain.usecase.SaveValueUseCase.VALUE_SAVED_SUCCESSFULLY_STATE;

public class AddValuePresenter {
    public interface View {
        void setPresenter(AddValuePresenter presenter);
        void dismissView();
        void showValueAdded();
        void showAddValueError();
        void showAddValueErrorWithoutTitle();
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
    private final UseCaseHandler mUseCaseHandler;
    private final UseCase<ValueDetail, Integer> mAddValueUseCase;
    private final ValueDetail mValueDetail;

    public AddValuePresenter(
            View view,
            ValueDetail valueDetail,
            UseCaseHandler useCaseHandler,
            UseCase<ValueDetail, Integer> addValueUseCase) {
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

    private void updateViewWithOnValuedAdded(final Integer addedValueState) {
        if (mView == null) return;

        if (isAddedValueStateValid(addedValueState)) {
            mView.showValueAdded();
            dismissView();
        } else {
            showAddValueError(addedValueState);
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

    private void showAddValueError(final Integer valueState) {
        if (valueState == UNSAVED_ERROR_EMPTY_TITLE_STATE) {
            mView.showAddValueErrorWithoutTitle();
        } else {
            mView.showAddValueError();
        }
    }

    private boolean isAddedValueStateValid(final Integer isValueAdded) {
        return VALUE_SAVED_SUCCESSFULLY_STATE == isValueAdded;
    }
}
