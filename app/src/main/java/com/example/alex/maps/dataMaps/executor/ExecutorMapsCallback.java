package com.example.alex.maps.dataMaps.executor;


public interface ExecutorMapsCallback {

    void responseHideProgress(int position);

    void responseShowProgress(int position);

    void responseCalculationStopped();
}
