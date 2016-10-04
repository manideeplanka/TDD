package com.rapidbizapps.tdd.view.base;

/**
 * Created by mlanka on 29/9/16.
 */
public interface MvpPresenter<V extends MvpView> {

    void attachView(V view);

    void detachView();
}