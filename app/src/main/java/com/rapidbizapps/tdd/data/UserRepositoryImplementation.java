package com.rapidbizapps.tdd.data;

import android.util.Log;

import com.rapidbizapps.tdd.data.model.User;
import com.rapidbizapps.tdd.data.model.UserList;
import com.rapidbizapps.tdd.data.remote.UserService;

import java.io.IOException;
import java.util.List;

import rx.Observable;
import rx.functions.Func0;
import rx.functions.Func1;

import static rx.Observable.from;

/**
 * Created by mlanka on 23-08-2016.
 */
public class UserRepositoryImplementation implements UserRepository {
    private static final String TAG = "UserRepositoryImplement";

    private UserService mUserService;

    public UserRepositoryImplementation(UserService userRestService) {
        this.mUserService = userRestService;
    }

    @Override
    public Observable<List<User>> searchUsers(final String searchTerm) {
        Log.d(TAG, "searchUsers: " + searchTerm);
        return Observable.defer(
                () -> mUserService.findUsers(searchTerm)
                        .flatMap(usersList -> Observable.from(usersList.getItems())
                                .flatMap(user -> mUserService.getUser(user.getLogin()))
                                .toList())
        ).retryWhen(observable -> observable.flatMap(o -> {
            if (o instanceof IOException)
                return Observable.just(null);
            return Observable.error(o);

        }));
    }

}


