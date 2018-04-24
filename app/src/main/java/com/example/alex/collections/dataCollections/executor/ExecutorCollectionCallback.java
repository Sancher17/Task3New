package com.example.alex.collections.dataCollections.executor;


public interface ExecutorCollectionCallback {

    void responseHideProgress(int position);

    void responseShowProgress(int position);

    void responseCalculationStopped();
}
