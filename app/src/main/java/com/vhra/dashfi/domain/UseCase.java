package com.vhra.dashfi.domain;

import com.vhra.dashfi.utils.Callback;

public interface UseCase<Input, Output> {
    void execute(Input input, Callback<Output> callback);
}