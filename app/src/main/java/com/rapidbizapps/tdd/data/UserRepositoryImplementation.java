package com.rapidbizapps.tdd.data;

import com.rapidbizapps.tdd.data.model.User;
import com.rapidbizapps.tdd.data.remote.UserService;

import java.io.IOException;
import java.util.List;

import rx.Observable;

/**
 * Created by mlanka on 23-08-2016.
 */
public class UserRepositoryImplementation implements UserRepository {


    private UserService mUserService;

    public UserRepositoryImplementation(UserService userRestService) {
        this.mUserService = userRestService;
    }

    @Override
    public Observable<List<User>> searchUsers(final String searchTerm) {
        return Observable.defer(() -> mUserService.findUsers(searchTerm)
                .concatMap(usersList -> Observable.from(usersList.getItems())
                                                  .concatMap(user -> mUserService.getUser(user.getLogin())).toList()))
                .retryWhen(observable -> observable.flatMap(o -> {
                    if (o instanceof IOException) {
                        return Observable.just(null);
                    }
                    return Observable.error(o);
                }));
    }
}
