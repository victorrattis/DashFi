package com.vhra.dashfi.domain;

import com.vhra.dashfi.utils.Callback;
import com.vhra.dashfi.utils.CallbackHandler;

public class UseCaseHandler {
    private UseCaseScheduler mUseCaseScheduler;

    public UseCaseHandler(UseCaseScheduler useCaseScheduler) {
        mUseCaseScheduler = useCaseScheduler;
    }

    public <Input, Output> void execute(
            UseCase<Input, Output> useCase, Input input, Callback<Output> callback) {
        if (useCase == null) {
            return;
        }

        final Callback<Output> callbackCaller = CallbackHandler.getCallbackHandler(callback);
        mUseCaseScheduler.execute(() -> useCase.execute(input, callbackCaller));
    }
}
