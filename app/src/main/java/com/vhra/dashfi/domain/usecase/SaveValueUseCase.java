package com.vhra.dashfi.domain.usecase;

import androidx.annotation.IntDef;

import com.vhra.dashfi.domain.model.IValuesRepository;
import com.vhra.dashfi.domain.model.ValueDetail;
import com.vhra.dashfi.domain.UseCase;
import com.vhra.dashfi.utils.Callback;

public class SaveValueUseCase implements UseCase<ValueDetail, Integer> {
    @IntDef({
            VALUE_SAVED_SUCCESSFULLY_STATE,
            UNSAVED_ERROR_VALUE_NULL_STATE,
            UNSAVED_ERROR_EMPTY_TITLE_STATE,
            UNSAVED_VALUE_STATE,
            UNSAVED_ERROR_REPOSITORY_NULL})
    public @interface SaveValueState {}
    public static final int VALUE_SAVED_SUCCESSFULLY_STATE = 0;
    public static final int UNSAVED_ERROR_VALUE_NULL_STATE = -1;
    public static final int UNSAVED_ERROR_EMPTY_TITLE_STATE = -2;
    public static final int UNSAVED_ERROR_REPOSITORY_NULL = -3;
    public static final int UNSAVED_VALUE_STATE = -4;

    private IValuesRepository mValuesRepository;

    public SaveValueUseCase(IValuesRepository valuesRepository) {
        mValuesRepository = valuesRepository;
    }

    @Override
    public void execute(ValueDetail valueDetail, Callback<Integer> callback) {
        if (mValuesRepository == null) {
            callback.onComplete(UNSAVED_ERROR_REPOSITORY_NULL);
            return;
        }

        @SaveValueState int isValueValid = validateValueDetail(valueDetail);
        if (isValueValid == VALUE_SAVED_SUCCESSFULLY_STATE) {
            mValuesRepository.saveValue(valueDetail, saved -> callback.onComplete(saved ?
                    VALUE_SAVED_SUCCESSFULLY_STATE : UNSAVED_VALUE_STATE));
        } else {
            callback.onComplete(isValueValid);
        }
    }

    private @SaveValueState
    int validateValueDetail(final ValueDetail valueDetail) {
        if (valueDetail == null){
            return UNSAVED_ERROR_VALUE_NULL_STATE;

        } else if (isTextEmpty(valueDetail.getTitle())) {
            return UNSAVED_ERROR_EMPTY_TITLE_STATE;

        } else {
            return VALUE_SAVED_SUCCESSFULLY_STATE;
        }
    }

    private boolean isTextEmpty(final String title) {
        return title == null || title.isEmpty();
    }
}
