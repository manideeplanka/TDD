package com.rapidbizapps.tdd.ui.search;

import com.rapidbizapps.tdd.data.UserRepository;
import com.rapidbizapps.tdd.data.model.User;
import com.rapidbizapps.tdd.ui.base.BasePresenter;

import java.util.List;

import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by mlanka on 30/9/16.
 */
public class FindUsersPresenter extends BasePresenter<FindUsersContract.View> implements FindUsersContract.Presenter {
    UserRepository mUserRepository;
    Scheduler mainScheduler, ioScheduler;
    private static final String LOG_TAG = "FindUsersPresenter";

    public FindUsersPresenter(UserRepository userRepository, Scheduler mainScheduler, Scheduler ioScheduler) {
        this.mainScheduler = mainScheduler;
        this.ioScheduler = ioScheduler;
        this.mUserRepository = userRepository;
    }

    @Override
    public void find(String searchTerm) {
        checkViewAttached();
        FindUsersContract.View view = getView();
        getView().showProgress();
        Subscription subscription = mUserRepository.searchUsers(searchTerm).subscribeOn(ioScheduler).observeOn(mainScheduler).subscribe(new Subscriber<List<User>>() {
            @Override
            public void onCompleted() {
//                view.dismissProgress();
            }

            @Override
            public void onError(Throwable e) {
                view.dismissProgress();
                view.showError(e.getMessage());
            }

            @Override
            public void onNext(List<User> users) {
                view.dismissProgress();
                view.showSearchResults(users);
            }
        });

        addSubscription(subscription);


    }

}
