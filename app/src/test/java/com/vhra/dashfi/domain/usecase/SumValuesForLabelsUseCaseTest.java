package com.vhra.dashfi.domain.usecase;

import com.vhra.dashfi.domain.model.IValuesRepository;
import com.vhra.dashfi.domain.model.ValueDetail;
import com.vhra.dashfi.utils.Callback;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.vhra.dashfi.TestUtils.createValueDetail;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

public class SumValuesForLabelsUseCaseTest {
    @Test
    public void should_returnNull_when_passingRepositoryNull() {
        SumValuesForLabelsUseCase sumValuesForLabelsUseCase =
                new SumValuesForLabelsUseCase(null, null);
        sumValuesForLabelsUseCase.execute("0.0", Assert::assertNull);
    }

    @Test
    public void should_return10_when_passingValue10() {
        IValuesRepository valuesRepository = mock(IValuesRepository.class);
        SumValuesForLabelsUseCase sumValuesForLabelsUseCase =
                new SumValuesForLabelsUseCase(valuesRepository, null);
        sumValuesForLabelsUseCase.execute("10.0", value -> {
            assertEquals(10.0, value, 0.001);
        });
    }

    @Test
    public void should_returnNull_when_passingNoDoubleValue() {
        IValuesRepository valuesRepository = mock(IValuesRepository.class);
        SumValuesForLabelsUseCase sumValuesForLabelsUseCase =
                new SumValuesForLabelsUseCase(valuesRepository, null);
        sumValuesForLabelsUseCase.execute("asd", Assert::assertNull);
    }

    // label: ---

    @Test
    public void should_return0_when_repositoryReturnsNull() {
        IValuesRepository valuesRepository = mock(IValuesRepository.class);
        doAnswer((Answer<Void>) invocation -> {
            Callback<List<? extends ValueDetail>> callback = invocation.getArgument(0);
            callback.onComplete(null);
    return null;
        }).when(valuesRepository).getAllValues(any());

        SumValuesForLabelsUseCase sumValuesForLabelsUseCase =
                new SumValuesForLabelsUseCase(valuesRepository, null);
        sumValuesForLabelsUseCase.execute("label:", value -> assertEquals(0.0, value, 0.001));
    }

    @Test
    public void should_return0_when_passLabelFormatWithoutValues() {
        IValuesRepository valuesRepository = mock(IValuesRepository.class);
        doAnswer((Answer<Void>) invocation -> {
            List<ValueDetail> result = new ArrayList<>();
            result.add(createValueDetail("item 1", 13.0, Collections.singletonList("gastos")));

            Callback<List<? extends ValueDetail>> callback = invocation.getArgument(0);
            callback.onComplete(result);
            return null;
        }).when(valuesRepository).getAllValues(any());

        SumValuesForLabelsUseCase sumValuesForLabelsUseCase =
                new SumValuesForLabelsUseCase(valuesRepository, null);
        sumValuesForLabelsUseCase.execute("label:", value -> assertEquals(0.0, value, 0.001));
    }

    @Test
    public void should_returnOnlyValue_when_MatchWithOne() {
        IValuesRepository valuesRepository = mock(IValuesRepository.class);
        doAnswer((Answer<Void>) invocation -> {
            List<ValueDetail> result = new ArrayList<>();
            result.add(createValueDetail("item 1", 13.0, Collections.singletonList("gastos")));

            Callback<List<? extends ValueDetail>> callback = invocation.getArgument(0);
            callback.onComplete(result);
            return null;
        }).when(valuesRepository).getAllValues(any());

        String valueText = "label:gastos";
        SumValuesForLabelsUseCase sumValuesForLabelsUseCase =
                new SumValuesForLabelsUseCase(valuesRepository, null);
        sumValuesForLabelsUseCase.execute(valueText, value -> assertEquals(13.0, value, 0.001));
    }

    @Test
    public void should_returnSumValues_when_MatchWithOnes() {
        IValuesRepository valuesRepository = mock(IValuesRepository.class);
        doAnswer((Answer<Void>) invocation -> {
            List<ValueDetail> result = new ArrayList<>();
            result.add(createValueDetail("item 1", 13.0, Collections.singletonList("gastos")));
            result.add(createValueDetail("item 2", 7.0, Collections.singletonList("gastos")));
            result.add(createValueDetail("item 3", 100.0, Collections.singletonList("receita")));
            result.add(createValueDetail("item 4", 32.0, Collections.singletonList("gastos")));

            Callback<List<? extends ValueDetail>> callback = invocation.getArgument(0);
            callback.onComplete(result);
            return null;
        }).when(valuesRepository).getAllValues(any());

        String valueText = "label:gastos";
        SumValuesForLabelsUseCase sumValuesForLabelsUseCase =
                new SumValuesForLabelsUseCase(valuesRepository, null);
        sumValuesForLabelsUseCase.execute(valueText, value -> assertEquals(52.0, value, 0.001));
    }

    @Test
    public void should_return0_when_noMatch() {
        IValuesRepository valuesRepository = mock(IValuesRepository.class);
        doAnswer((Answer<Void>) invocation -> {
            List<ValueDetail> result = new ArrayList<>();
            result.add(createValueDetail("item 1", 13.0, Collections.singletonList("gastos")));
            result.add(createValueDetail("item 2", 7.0, Collections.singletonList("gastos")));
            result.add(createValueDetail("item 3", 32.0, Collections.singletonList("gastos")));

            Callback<List<? extends ValueDetail>> callback = invocation.getArgument(0);
            callback.onComplete(result);
            return null;
        }).when(valuesRepository).getAllValues(any());

        String valueText = "label:receita";
        SumValuesForLabelsUseCase sumValuesForLabelsUseCase =
                new SumValuesForLabelsUseCase(valuesRepository, null);
        sumValuesForLabelsUseCase.execute(valueText, value -> assertEquals(0.0, value, 0.001));
    }

    @Test
    public void should_returnValue_when_matchMoreThanOneLabel() {
        IValuesRepository valuesRepository = mock(IValuesRepository.class);
        doAnswer((Answer<Void>) invocation -> {
            List<ValueDetail> result = new ArrayList<>();
            result.add(createValueDetail("item 1", 13.0, Arrays.asList("gastos", "2019")));
            result.add(createValueDetail("item 2", 7.0, Collections.singletonList("gastos")));
            result.add(createValueDetail("item 3", 32.0, Collections.singletonList("gastos")));

            Callback<List<? extends ValueDetail>> callback = invocation.getArgument(0);
            callback.onComplete(result);
            return null;
        }).when(valuesRepository).getAllValues(any());

        String valueText = "label:gastos,2019";
        SumValuesForLabelsUseCase sumValuesForLabelsUseCase =
                new SumValuesForLabelsUseCase(valuesRepository, null);
        sumValuesForLabelsUseCase.execute(valueText, value -> assertEquals(13.0, value, 0.001));
    }

    // Expressions --

    @Test
    public void should_subtractLabels() {
        IValuesRepository valuesRepository = mock(IValuesRepository.class);
        doAnswer((Answer<Void>) invocation -> {
            List<ValueDetail> result = new ArrayList<>();
            result.add(createValueDetail("item 1", 13.0, Collections.singletonList("gastos")));
            result.add(createValueDetail("item 2", 7.0, Collections.singletonList("gastos")));
            result.add(createValueDetail("item 3", 100.0, Collections.singletonList("receita")));
            result.add(createValueDetail("item 4", 32.0, Collections.singletonList("gastos")));

            Callback<List<? extends ValueDetail>> callback = invocation.getArgument(0);
            callback.onComplete(result);
            return null;
        }).when(valuesRepository).getAllValues(any());

        String valueText = "label:receita-gastos";
        SumValuesForLabelsUseCase sumValuesForLabelsUseCase =
                new SumValuesForLabelsUseCase(valuesRepository, null);
        sumValuesForLabelsUseCase.execute(valueText, value -> assertEquals(48.0, value, 0.001));
    }

    @Test
    public void should_subtractLabels_when_oneNotExists() {
        IValuesRepository valuesRepository = mock(IValuesRepository.class);
        doAnswer((Answer<Void>) invocation -> {
            List<ValueDetail> result = new ArrayList<>();
            result.add(createValueDetail("item 1", 13.0, Collections.singletonList("gastos")));
            result.add(createValueDetail("item 2", 7.0, Collections.singletonList("gastos")));
            result.add(createValueDetail("item 4", 32.0, Collections.singletonList("gastos")));

            Callback<List<? extends ValueDetail>> callback = invocation.getArgument(0);
            callback.onComplete(result);
            return null;
        }).when(valuesRepository).getAllValues(any());

        String valueText = "label:receita-gastos";
        SumValuesForLabelsUseCase sumValuesForLabelsUseCase =
                new SumValuesForLabelsUseCase(valuesRepository, null);
        sumValuesForLabelsUseCase.execute(valueText, value -> assertEquals(-52.0, value, 0.001));
    }

    @Test
    public void should_subtractLabels_when_TwoNotExists() {
        IValuesRepository valuesRepository = mock(IValuesRepository.class);
        doAnswer((Answer<Void>) invocation -> {
            List<ValueDetail> result = new ArrayList<>();
            result.add(createValueDetail("item 1", 13.0, Collections.singletonList("gastos")));
            result.add(createValueDetail("item 2", 7.0, Collections.singletonList("gastos")));
            result.add(createValueDetail("item 4", 32.0, Collections.singletonList("gastos")));
            result.add(createValueDetail("item 3", 100.0, Collections.singletonList("receita")));

            Callback<List<? extends ValueDetail>> callback = invocation.getArgument(0);
            callback.onComplete(result);
            return null;
        }).when(valuesRepository).getAllValues(any());

        String valueText = "label:receita-gasto";
        SumValuesForLabelsUseCase sumValuesForLabelsUseCase =
                new SumValuesForLabelsUseCase(valuesRepository, null);
        sumValuesForLabelsUseCase.execute(valueText, value -> assertEquals(100, value, 0.001));
    }

    // addition

    @Test
    public void should_additionLabels() {
        IValuesRepository valuesRepository = mock(IValuesRepository.class);
        doAnswer((Answer<Void>) invocation -> {
            List<ValueDetail> result = new ArrayList<>();
            result.add(createValueDetail("item 1", 13.0, Collections.singletonList("gastos")));
            result.add(createValueDetail("item 2", 7.0, Collections.singletonList("gastos")));
            result.add(createValueDetail("item 4", 32.0, Collections.singletonList("gastos")));
            result.add(createValueDetail("item 3", 100.0, Collections.singletonList("receita")));

            Callback<List<? extends ValueDetail>> callback = invocation.getArgument(0);
            callback.onComplete(result);
            return null;
        }).when(valuesRepository).getAllValues(any());

        String valueText = "label:receita+gastos";
        SumValuesForLabelsUseCase sumValuesForLabelsUseCase =
                new SumValuesForLabelsUseCase(valuesRepository, null);
        sumValuesForLabelsUseCase.execute(valueText, value -> assertEquals(152.0, value, 0.001));
    }


    // Multiple

    @Test
    public void should_multiple_withLabelAndNumber() {
        IValuesRepository valuesRepository = mock(IValuesRepository.class);
        doAnswer((Answer<Void>) invocation -> {
            List<ValueDetail> result = new ArrayList<>();
            result.add(createValueDetail("item 1", 13.0, Collections.singletonList("gastos")));
            result.add(createValueDetail("item 2", 7.0, Collections.singletonList("gastos")));
            result.add(createValueDetail("item 4", 32.0, Collections.singletonList("gastos")));
            result.add(createValueDetail("item 3", 100.0, Arrays.asList("receita", "2019")));

            Callback<List<? extends ValueDetail>> callback = invocation.getArgument(0);
            callback.onComplete(result);
            return null;
        }).when(valuesRepository).getAllValues(any());

        String valueText = "label:receita,2019*1.1";
        SumValuesForLabelsUseCase sumValuesForLabelsUseCase =
                new SumValuesForLabelsUseCase(valuesRepository, null);
        sumValuesForLabelsUseCase.execute(valueText, value -> assertEquals(110, value, 0.001));
    }

    // using number in the expressions.

    @Test
    public void should_calculateExpressionWithFirstIsNumber() {
        IValuesRepository valuesRepository = mock(IValuesRepository.class);
        doAnswer((Answer<Void>) invocation -> {
            List<ValueDetail> result = new ArrayList<>();
            result.add(createValueDetail("item 1", 13.0, Collections.singletonList("gastos")));
            result.add(createValueDetail("item 2", 7.0, Collections.singletonList("gastos")));
            result.add(createValueDetail("item 4", 32.0, Collections.singletonList("gastos")));
            result.add(createValueDetail("item 3", 100.0, Collections.singletonList("receita")));

            Callback<List<? extends ValueDetail>> callback = invocation.getArgument(0);
            callback.onComplete(result);
            return null;
        }).when(valuesRepository).getAllValues(any());

        String valueText = "label:200+gastos";
        SumValuesForLabelsUseCase sumValuesForLabelsUseCase =
                new SumValuesForLabelsUseCase(valuesRepository, null);
        sumValuesForLabelsUseCase.execute(valueText, value -> assertEquals(252.0, value, 0.001));
    }

    @Test
    public void should_calculateExpressionWithSecondIsNumber() {
        IValuesRepository valuesRepository = mock(IValuesRepository.class);
        doAnswer((Answer<Void>) invocation -> {
            List<ValueDetail> result = new ArrayList<>();
            result.add(createValueDetail("item 1", 13.0, Collections.singletonList("gastos")));
            result.add(createValueDetail("item 2", 7.0, Collections.singletonList("gastos")));
            result.add(createValueDetail("item 4", 32.0, Collections.singletonList("gastos")));
            result.add(createValueDetail("item 3", 100.0, Collections.singletonList("receita")));

            Callback<List<? extends ValueDetail>> callback = invocation.getArgument(0);
            callback.onComplete(result);
            return null;
        }).when(valuesRepository).getAllValues(any());

        String valueText = "label:receita-200";
        SumValuesForLabelsUseCase sumValuesForLabelsUseCase =
                new SumValuesForLabelsUseCase(valuesRepository, null);
        sumValuesForLabelsUseCase.execute(valueText, value -> assertEquals(-100.0, value, 0.001));
    }

    @Test
    public void should_calculateExpressionWithBothAreNumber() {
        IValuesRepository valuesRepository = mock(IValuesRepository.class);
        doAnswer((Answer<Void>) invocation -> {
            List<ValueDetail> result = new ArrayList<>();
            result.add(createValueDetail("item 1", 13.0, Collections.singletonList("gastos")));
            result.add(createValueDetail("item 2", 7.0, Collections.singletonList("gastos")));
            result.add(createValueDetail("item 4", 32.0, Collections.singletonList("gastos")));
            result.add(createValueDetail("item 3", 100.0, Collections.singletonList("receita")));

            Callback<List<? extends ValueDetail>> callback = invocation.getArgument(0);
            callback.onComplete(result);
            return null;
        }).when(valuesRepository).getAllValues(any());

        String valueText = "label:200-100";
        SumValuesForLabelsUseCase sumValuesForLabelsUseCase =
                new SumValuesForLabelsUseCase(valuesRepository, null);
        sumValuesForLabelsUseCase.execute(valueText, value -> assertEquals(100.0, value, 0.001));
    }
}