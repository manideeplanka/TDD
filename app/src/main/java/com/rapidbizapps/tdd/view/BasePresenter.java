package com.rapidbizapps.tdd.view;

import android.view.View;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by mlanka on 30/9/16.
 */

class BasePresenter<T extends MvpView> implements MvpPresenter<T> {

    CompositeSubscription compositeSubscription = new CompositeSubscription();
    private T view;

    public void attachView(T view) {
        this.view = view;
    }

    public T getView() {
        return view;
    }

    public void detachView() {
        compositeSubscription.clear();
        view = null;
    }

    protected void addSubscription(Subscription subscription) {
        compositeSubscription.add(subscription);
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new ViewNotAttachedException();
    }

    public boolean isViewAttached() {
        return view != null;
    }

    static class ViewNotAttachedException extends RuntimeException {
        public ViewNotAttachedException() {
            super("Please call attachView(MvpView) before requesting any data from the presenter");
        }
    }
}
