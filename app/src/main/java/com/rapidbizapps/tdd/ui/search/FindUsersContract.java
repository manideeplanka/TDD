package com.rapidbizapps.tdd.ui.search;

import com.rapidbizapps.tdd.data.model.User;

import java.util.List;

import com.rapidbizapps.tdd.ui.base.MvpPresenter;
import com.rapidbizapps.tdd.ui.base.MvpView;


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

