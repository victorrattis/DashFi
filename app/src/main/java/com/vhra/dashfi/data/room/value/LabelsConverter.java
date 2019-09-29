package com.vhra.dashfi.data.room.value;

import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.List;

public class LabelsConverter {
    private static final String COMMA = ",";


    @TypeConverter
    public String writingStringFromList(final List<String> list) {
        StringBuilder result = new StringBuilder();
        for (String i : list) {
            result.append(COMMA).append(i);
        }
        return result.toString();
    }

    @TypeConverter
    public List<String> gettingListFromString(final String text) {
        List<String> list = new ArrayList<>();
        String[] array = text.split(COMMA);
        for (String s : array) {
            if (!s.isEmpty()) {
                list.add(s);
            }
        }
        return list;
    }
}
