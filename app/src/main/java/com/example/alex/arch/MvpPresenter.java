package com.example.alex.arch;


public interface MvpPresenter <V extends MvpView>  {

    void attachView(V mvpView);

    void detachView();

}
