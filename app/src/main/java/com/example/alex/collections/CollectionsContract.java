package com.example.alex.collections;

import com.example.alex.arch.MvpPresenter;
import com.example.alex.arch.MvpView;


public interface CollectionsContract {

    interface View extends MvpView {

        void showErrorEmptyNumber();

        void showCalculationFinished();

        void showCalculationIsStillWorking();

        void updateAdapter();

        void updateItemAdapter(int position);

        void showProgressBar(int position);

        void hideProgressBar(int position);

        void showCalculationStarted();

        void showCalculationStopped();

        void stopAllProgressBars();

        void showWait();

        void showCalculationNotStarted();


    }

    interface Presenter extends MvpPresenter<View> {

        void calculate();

        void stopСalculation();

    }

}
