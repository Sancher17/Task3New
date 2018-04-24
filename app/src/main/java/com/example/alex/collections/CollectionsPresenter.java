package com.example.alex.collections;


import com.example.alex.arch.PresenterBase;
import com.example.alex.constants.Constants;
import com.example.alex.dataCollections.executor.ExecutorCollection;
import com.example.alex.dataCollections.executor.ExecutorCollectionCallback;
import com.example.alex.arch.LifecycleExecutor;
import com.example.alex.utils.Logger;

public class CollectionsPresenter extends PresenterBase<CollectionsContract.View> implements CollectionsContract.Presenter, ExecutorCollectionCallback {

    private static Logger LOGGER = new Logger(CollectionsPresenter.class);

    private LifecycleExecutor executor;

    private static CollectionsPresenter instance;

    public static CollectionsPresenter getInstance() {
        if (instance == null) {
            return instance = new CollectionsPresenter();
        }
        return instance;
    }

    public void attachView(CollectionsContract.View mvpView) {
        super.attachView(mvpView);
        LOGGER.log("attachView // getView() " + String.valueOf(getView()));

    }

    public void detachView() {
        super.detachView();
        LOGGER.log("detachView // getView() " + String.valueOf(getView()));

    }


    @Override
    public void calculate() {
        executor = new ExecutorCollection(this);
        if (Constants.COUNT_OF_OPERATIONS_COLLECTIONS == 21){
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
    public void responseHideProgress(int position) {
        LOGGER.log("responseHideProgress " + position + " / " +  String.valueOf(getView()));
        getView().hideProgressBar(position);
        checkCountOfOperations();
    }

    public void calculationStopped(){
        getView().showCalculationStopped();
    }

    void checkCountOfOperations() {
        if (Constants.COUNT_OF_OPERATIONS_COLLECTIONS == 0) {
            getView().showCalculationFinished();
            Constants.COUNT_OF_OPERATIONS_COLLECTIONS = 21;
        }
    }


}