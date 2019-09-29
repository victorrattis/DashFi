package com.vhra.dashfi.domain.usecase;

import com.vhra.dashfi.domain.UseCase;
import com.vhra.dashfi.domain.model.IValuesRepository;
import com.vhra.dashfi.domain.model.ValueDetail;
import com.vhra.dashfi.utils.Callback;
import com.vhra.dashfi.utils.ILog;

import java.util.Arrays;
import java.util.List;

public class SumValuesForLabelsUseCase implements UseCase<String, Double> {
    private static final String TAG = "SumValuesForLabelsUseCase";
    private static final String LABEL_SCHEME = "label:";

    private static final Double DEFAULT_VALUE = 0.0;

    private static final String ADDITION_KEY = "+";
    private static final String SUBTRACT_KEY = "-";
    private static final String DIVIDER_KEY = "/";
    private static final String MULTIPLE_KEY = "*";

    private static final String LABEL_DIVIDER = ",";

    private final IValuesRepository mValuesRepository;
    private final ILog mLog;

    public SumValuesForLabelsUseCase(IValuesRepository valuesRepository, ILog log) {
        mValuesRepository = valuesRepository;
        mLog = log;
    }

    @Override
    public void execute(String valueText, Callback<Double> callback) {
        if (mValuesRepository == null) {
            loge("Error executing use case: values repository is null!");
            callback.onComplete(null);
            return;
        }

        if (hasLabelScheme(valueText)) {
            final String expressionText = valueText.replace(LABEL_SCHEME, "");
            mValuesRepository.getAllValues(
                    values -> callback.onComplete(calculateValue(expressionText, values)));
        } else {
            callback.onComplete(parseDouble(valueText));
        }
    }

    private Double calculateValue(final String valueText, List<? extends ValueDetail> values) {
        if (values == null || values.isEmpty()) return DEFAULT_VALUE;
        return hasMathOperator(valueText) ?
                calculateLabelExpression(valueText, values) : sumLabelValues(valueText, values);
    }

    private Double sumLabelValues(final String valueText, List<? extends ValueDetail> values) {
        List<String> labels = Arrays.asList(getLabels(valueText));
        return getValue(values, labels);
    }

    private Double calculateLabelExpression(
            final String valueText, List<? extends ValueDetail> values) {
        String[] parts = splitForMathOperator(valueText);
        if (parts.length == 2) {
            double valueA = getValue(parts[0], values);
            double valueB = getValue(parts[1], values);
            return calculate(getOperator(valueText), valueA, valueB);
        }
        return 0.0;
    }

    private double getValue(final String value, List<? extends ValueDetail> values) {
        Double result = parseDouble(value);
        if (result != null) {
            return result;
        }
        return getValue(values, Arrays.asList(getLabels(value)));
    }

    private Double parseDouble(final String valueText) {
        try {
            return Double.parseDouble(valueText);
        } catch (Exception e) {
            return null;
        }
    }

    private boolean hasLabelScheme(String text) {
        return text != null && text.contains(LABEL_SCHEME);
    }

    private double getValue(List<? extends ValueDetail> values, List<String> labels) {
        double result = 0;
        for (ValueDetail valueDetail : values) {
            if (valueDetail != null && containLabel(valueDetail.getLabels(), labels)) {
                result += valueDetail.getValue();
            }
        }
        return result;
    }

    private double calculate(String op, double valueA, double valueB) {
        if (ADDITION_KEY.equals(op)) return valueA + valueB;
        else if (SUBTRACT_KEY.equals(op)) return valueA - valueB;
        else if (MULTIPLE_KEY.equals(op)) return valueA * valueB;
        else if (DIVIDER_KEY.equals(op)) return valueA / valueB;
        else return valueA;
    }

    private String[] getLabels(String text) {
        return text.split(LABEL_DIVIDER);
    }

    private boolean hasMathOperator(String text) {
        return text.contains(SUBTRACT_KEY) ||
                text.contains(ADDITION_KEY) ||
                text.contains(MULTIPLE_KEY) ||
                text.contains(DIVIDER_KEY);
    }

    private String getOperator(final String expressionText) {
        if (expressionText.contains(SUBTRACT_KEY)) return SUBTRACT_KEY;
        else if (expressionText.contains(ADDITION_KEY)) return ADDITION_KEY;
        else if (expressionText.contains(MULTIPLE_KEY)) return MULTIPLE_KEY;
        else if (expressionText.contains(DIVIDER_KEY)) return DIVIDER_KEY;
        else return "";
    }

    private String[] splitForMathOperator(String text) {
        if (text.contains(SUBTRACT_KEY)) return text.split(SUBTRACT_KEY);
        else if (text.contains(ADDITION_KEY)) return text.split("\\+");
        else if (text.contains(MULTIPLE_KEY)) return text.split("\\*");
        else if (text.contains(DIVIDER_KEY)) return text.split("/");
        return new String[] {};
    }

    private boolean containLabel(List<String> listA, List<String> listB) {
        return listA != null && listA.containsAll(listB);
    }

    private void loge(final String text) {
        if (mLog != null) mLog.e(TAG, text);
    }
}
