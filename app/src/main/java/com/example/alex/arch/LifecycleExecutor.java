package com.example.alex.arch;

public interface LifecycleExecutor {

    void startCalculation();

    void stopCalculation();

    boolean isRunning();
}
