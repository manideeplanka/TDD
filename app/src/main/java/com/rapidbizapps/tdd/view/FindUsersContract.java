package com.rapidbizapps.tdd.view;

import com.rapidbizapps.tdd.model.User;
import com.rapidbizapps.tdd.view.base.MvpPresenter;
import com.rapidbizapps.tdd.view.base.MvpView;

import java.util.List;

/**
 * Created by mlanka on 30/9/16.
 */
public interface FindUsersContract {

    interface View extends MvpView {
        void showSearchResults(List<User> users);

        void showError(String errorMessage);

        void showProgress();

        void dismissProgress();
    }

    interface Presenter extends MvpPresenter<View> {
        void find(String searchTerm);
    }
}

