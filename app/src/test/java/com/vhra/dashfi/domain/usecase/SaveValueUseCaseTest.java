package com.vhra.dashfi.domain.usecase;

import com.vhra.dashfi.data.value.ValuesRepository;
import com.vhra.dashfi.domain.model.ValueDetail;
import com.vhra.dashfi.utils.Callback;

import org.junit.Test;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;

import static com.vhra.dashfi.TestUtils.createValueDetail;
import static com.vhra.dashfi.domain.usecase.SaveValueUseCase.UNSAVED_ERROR_EMPTY_TITLE_STATE;
import static com.vhra.dashfi.domain.usecase.SaveValueUseCase.UNSAVED_ERROR_REPOSITORY_NULL;
import static com.vhra.dashfi.domain.usecase.SaveValueUseCase.UNSAVED_ERROR_VALUE_NULL_STATE;
import static com.vhra.dashfi.domain.usecase.SaveValueUseCase.UNSAVED_VALUE_STATE;
import static com.vhra.dashfi.domain.usecase.SaveValueUseCase.VALUE_SAVED_SUCCESSFULLY_STATE;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

public class SaveValueUseCaseTest {

    @Test
    public void should_returnRepositoryNullError_when_passRepositoryNull() {
        SaveValueUseCase saveValueUseCase = new SaveValueUseCase(null);

        ValueDetail valueDetail = createValueDetail("", 0, null);
        saveValueUseCase.execute(valueDetail,
                result -> assertEquals(UNSAVED_ERROR_REPOSITORY_NULL, result.intValue()));
    }

    @Test
    public void should_returnValueNullError_when_passValueDetailNull() {
        ValuesRepository valuesRepository = mock(ValuesRepository.class);
        SaveValueUseCase saveValueUseCase = new SaveValueUseCase(valuesRepository);

        saveValueUseCase.execute(null,
                result -> assertEquals(UNSAVED_ERROR_VALUE_NULL_STATE, result.intValue()));
    }

    @Test
    public void should_returnErrorEmptyTitle_when_passValueDetailEmptyTitle() {
        ValuesRepository valuesRepository = mock(ValuesRepository.class);
        SaveValueUseCase saveValueUseCase = new SaveValueUseCase(valuesRepository);

        ValueDetail valueDetail = createValueDetail("", 10, null);
        saveValueUseCase.execute(valueDetail,
                result -> assertEquals(UNSAVED_ERROR_EMPTY_TITLE_STATE, result.intValue()));
    }

    @Test
    public void should_saveValue_when_passValidValueDetail() {
        ValuesRepository valuesRepository = mock(ValuesRepository.class);
        doAnswer((Answer<Void>) invocation -> {
            Callback<Boolean> callback = invocation.getArgument(1);
            callback.onComplete(true);
            return null;
        }).when(valuesRepository).saveValue(any(), any());

        SaveValueUseCase saveValueUseCase = new SaveValueUseCase(valuesRepository);

        ValueDetail valueDetail = createValueDetail("value title", 10, null);
        saveValueUseCase.execute(valueDetail,
                result -> assertEquals(VALUE_SAVED_SUCCESSFULLY_STATE, result.intValue()));
    }

    @Test
    public void should_returnUnsaved_when_repositoryCannotSaved() {
        ValuesRepository valuesRepository = mock(ValuesRepository.class);
        doAnswer((Answer<Void>) invocation -> {
            Callback<Boolean> callback = invocation.getArgument(1);
            callback.onComplete(false);
            return null;
        }).when(valuesRepository).saveValue(any(), any());

        SaveValueUseCase saveValueUseCase = new SaveValueUseCase(valuesRepository);

        ValueDetail valueDetail = createValueDetail("value title", 0, new ArrayList<>());
        saveValueUseCase.execute(valueDetail,
                result -> assertEquals(UNSAVED_VALUE_STATE, result.intValue()));
    }
}