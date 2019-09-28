package com.vhra.dashfi;

public class MessageEvent {
    private final String mAction;

    public MessageEvent(String action) {
        mAction = action;
    }

    public String getAction() {
        return mAction;
    }
}
