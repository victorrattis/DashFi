package com.vhra.dashfi.utils;

public interface ILog {
    void d(String tag, String log);
    void e(String tag, String log);
    void e(String tag, String log, Throwable e);
}
