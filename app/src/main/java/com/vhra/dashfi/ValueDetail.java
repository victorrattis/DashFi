package com.vhra.dashfi;

import java.util.List;

public interface ValueDetail {
    int getId();
    String getTitle();
    double getValue();
    List<String> getLabels();
}
