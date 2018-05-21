package com.example.alex.utils;

import android.util.Log;

//@Obfuscate
public class Logger {

    private String logTag;

    private boolean isLogging = true;
//    private boolean isLogging = false;

    public Logger(Class c) {
        logTag = c.getSimpleName();
    }

    public void log(String message) {
        if (isLogging)
            Log.d("LOGGER / " +logTag, message);
    }

    public void error(Throwable message) {
        if (isLogging)
            Log.e(logTag, message.toString());
    }
}
