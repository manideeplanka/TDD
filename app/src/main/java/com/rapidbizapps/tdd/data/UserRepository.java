package com.rapidbizapps.tdd.data;

import com.rapidbizapps.tdd.model.User;

import java.util.List;

import rx.Observable;

/**
 * Created by mlanka on 23-08-2016.
 */
public interface UserRepository {

    Observable<List<User>> searchUsers(String searchTerm);
}
