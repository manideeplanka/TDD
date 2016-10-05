package com.rapidbizapps.tdd.ui.search;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rapidbizapps.tdd.R;
import com.rapidbizapps.tdd.data.local.ServiceHelper;
import com.rapidbizapps.tdd.data.model.User;
import com.rapidbizapps.tdd.ui.search.FindUsersContract;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FindUsersActivity extends AppCompatActivity implements FindUsersContract.View {

    private static final String LOG_TAG = "FindUsersActivity";

    private FindUsersContract.Presenter mFindUsersPresenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_users);

        mFindUsersPresenter = new FindUsersPresenter(ServiceHelper.getUserRepository(), AndroidSchedulers.mainThread(), Schedulers.io());
        mFindUsersPresenter.attachView(this);

    }

    @Override
    public void showSearchResults(List<User> users) {

    }

    @Override
    public void showError(String errorMessage) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dismissProgress() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFindUsersPresenter.detachView();
    }
}
