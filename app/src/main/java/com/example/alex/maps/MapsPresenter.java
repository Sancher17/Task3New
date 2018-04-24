package com.example.alex.maps;


import com.example.alex.arch.LifecycleExecutor;
import com.example.alex.arch.PresenterBase;
import com.example.alex.constants.Constants;

import com.example.alex.maps.dataMaps.executor.ExecutorMaps;
import com.example.alex.maps.dataMaps.executor.ExecutorMapsCallback;
import com.example.alex.utils.Logger;

public class MapsPresenter extends PresenterBase<MapsContract.View> implements MapsContract.Presenter, ExecutorMapsCallback {

    private static Logger LOGGER = new Logger(MapsPresenter.class);

    private LifecycleExecutor executor;

    public void attachView(MapsContract.View mvpView) {
        super.attachView(mvpView);
        LOGGER.log("attachView // getView() " + String.valueOf(getView()));

    }

    public void detachView() {
        super.detachView();
        LOGGER.log("detachView // getView() " + String.valueOf(getView()));

    }


    @Override
    public void calculate() {
        executor = new ExecutorMaps(this);
        if (Constants.COUNT_OF_OPERATIONS_MAPS == 9){
            executor.startCalculation();
            getView().showCalculationStarted();
        }else {
            getView().showCalculationIsStillWorking();
        }
    }

    @Override
    public void stopСalculation() {
        LOGGER.log("stopСalculation");
        if (executor != null && !executor.isRunning()) {
            executor.stopCalculation();
            getView().stopAllProgressBars();
            getView().updateAdapter();
            getView().showWait();
        }else {
            getView().showCalculationNotStarted();
        }

    }

    @Override
    public void responseShowProgress(int position) {
        LOGGER.log("response " + position);
        getView().showProgressBar(position);
    }

    @Override
    public void responseCalculationStopped() {
        getView().showCalculationStopped();
    }

    @Override
    public void responseHideProgress(int position) {
        LOGGER.log("responseHideProgress " + position + " / " +  String.valueOf(getView()));
        getView().hideProgressBar(position);
        checkCountOfOperations();
    }

    void checkCountOfOperations() {
        if (Constants.COUNT_OF_OPERATIONS_MAPS == 0) {
            getView().showCalculationFinished();
            Constants.COUNT_OF_OPERATIONS_MAPS = 9;
        }
    }


}
