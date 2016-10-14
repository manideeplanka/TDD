package com.rapidbizapps.tdd.ui.search;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.rapidbizapps.tdd.R;
import com.rapidbizapps.tdd.data.model.User;
import com.rapidbizapps.tdd.data.remote.local.ServiceHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.view.View.GONE;

public class FindUsersActivity extends AppCompatActivity implements FindUsersContract.View {

    private static final String LOG_TAG = "FindUsersActivity";

    private FindUsersContract.Presenter mFindUsersPresenter;

    @BindView(R.id.finding_users_progress)
    ProgressBar mFindingUsers_pg;

    @BindView(R.id.users_rv)
    RecyclerView mUsers_rv;

    UsersAdapter mUsersAdapter;
    SearchView searchView;

    @BindView(R.id.textView)
    TextView textView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_users);
        ButterKnife.bind(this);

        mFindUsersPresenter = new FindUsersPresenter(ServiceHelper.getUserRepository(), AndroidSchedulers.mainThread(), Schedulers.io());
        mFindUsersPresenter.attachView(this);

        mUsersAdapter = new UsersAdapter(this, null);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mUsers_rv.setLayoutManager(layoutManager);
        mUsers_rv.setAdapter(mUsersAdapter);


        mFindingUsers_pg.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.menu_user_search, menu);
        final MenuItem menuItem = menu.findItem(R.id.menu_find_users);

        searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mFindUsersPresenter.find(query);
                menuItem.collapseActionView();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        menuItem.expandActionView();
        return true;

    }

    @Override
    public void showSearchResults(List<User> users) {
        mUsers_rv.setVisibility(View.VISIBLE);
        mUsersAdapter.setItems(users);
        mFindingUsers_pg.setVisibility(GONE);
    }

    @Override
    public void showError(String errorMessage) {
        mFindingUsers_pg.setVisibility(GONE);
        mUsers_rv.setVisibility(GONE);
        Log.e(LOG_TAG, "showError: " + errorMessage);
    }

    @Override
    public void showProgress() {
        mFindingUsers_pg.setVisibility(View.VISIBLE);
        mUsers_rv.setVisibility(GONE);
    }

    @Override
    public void dismissProgress() {
        mFindingUsers_pg.setVisibility(GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFindUsersPresenter.detachView();
    }
}
