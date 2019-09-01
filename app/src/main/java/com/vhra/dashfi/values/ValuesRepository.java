package com.vhra.dashfi.values;

import android.os.Handler;

import com.vhra.dashfi.utils.Callback;
import com.vhra.dashfi.ValueDetail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ValuesRepository {
    private static final String LABEL_SCHEME = "label:";

    private ValuesDataSource mValuesDataSource;
    private static ValuesRepository sInstance;

    public static ValuesRepository createInstance(ValuesDataSource valuesDataSource) {
        sInstance = new ValuesRepository(valuesDataSource);
        return sInstance;
    }

    public static ValuesRepository getInstance() {
        return sInstance;
    }

    public ValuesRepository(ValuesDataSource valuesDataSource) {
        mValuesDataSource = valuesDataSource;
    }

    public void addValue(ValueDetail valueDetail) {
        mValuesDataSource.add(valueDetail);
    }

    public void getAllValues(Callback<List<ValueDetail>> callback) {
        Handler handler = new Handler();
        new Thread(() -> {
            handler.post(() -> {
                callback.onComplete(mValuesDataSource.getValues());
            });
        }).start();
    }

    public void sumValueForLabels(String labelsText, Callback<Double> callback) {
        Handler handler = new Handler();
        new Thread(() -> {
            if (hasMathOperator(labelsText)) {
                double sum = 1;
                String[] parts = splitForMathOperator(labelsText);
                if (parts.length > 0) {
                    sum = getValue(Arrays.asList(getLabels(parts[0])));
                    sum = calculate("-", sum, getValue(Arrays.asList(getLabels(parts[1]))));
                }

                final double result = sum;
                handler.post(() -> callback.onComplete(result));

            } else {
                List<String> labels = Arrays.asList(getLabels(labelsText));
                final double result = getValue(labels);
                handler.post(() -> callback.onComplete(result));
            }
        }).start();
    }

    public void getValuesForLabels(String labelsText, Callback<List<ValueDetail>> callback) {
        Handler handler = new Handler();
        new Thread(() -> {
            List<ValueDetail> valueDetails = mValuesDataSource.getValues();

            final List<ValueDetail> result = new ArrayList<>();
            List<String> labels = Arrays.asList(getLabels(labelsText));
            for (ValueDetail valueDetail : valueDetails) {
                if (valueDetail != null && containLabel(valueDetail.getLabels(), labels)) {
                    result.add(valueDetail);
                }
            }

            handler.post(() -> callback.onComplete(result));
        }).start();
    }

    public void updateValue(ValueDetail updatedValue, Callback<Void> callback) {
        Handler handler = new Handler();
        new Thread(() -> {
            mValuesDataSource.updateValue(updatedValue);
            handler.post(() -> callback.onComplete(null));
        }).start();
    }

    private double getValue(List<String> labels) {
        double result = 0;
        List<ValueDetail> valueDetails = mValuesDataSource.getValues();
        for (ValueDetail valueDetail : valueDetails) {
            if (valueDetail != null && containLabel(valueDetail.getLabels(), labels)) {
                result += valueDetail.getValue();
            }
        }
        return result;
    }

    private double calculate(String op, double valueA, double valueB) {
        if ("+".equals(op)) return valueA + valueB;
        else if ("-".equals(op)) return valueA - valueB;
        else if ("*".equals(op)) return valueA * valueB;
        else if ("/".equals(op)) return valueA / valueB;
        else return 0;
    }

    private String[] getLabels(String text) {
        return text.replace(LABEL_SCHEME, "").split(",");
    }

    private boolean hasMathOperator(String text) {
        return text.contains("-") ||
                text.contains("+") ||
                text.contains("*") ||
                text.contains("/");
    }

    private String[] splitForMathOperator(String text) {
        if (text.contains("-")) return text.split("-");
        else if (text.contains("+")) return text.split("/+");
        else if (text.contains("*")) return text.split("/*");
        else if (text.contains("/")) return text.split("//");
        return new String[] {};
    }

    private boolean containLabel(List<String> listA, List<String> listB) {
        return listA != null && listA.containsAll(listB);
    }
}
