package com.rapidbizapps.tdd.data.remote.remote;

import android.util.Log;

import com.rapidbizapps.tdd.data.model.User;
import com.rapidbizapps.tdd.data.model.UserList;
import com.rapidbizapps.tdd.data.remote.UserService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by mlanka on 13/10/16.
 */

public class MockUserServiceImplementation implements UserService {
    List<User> userList = new ArrayList<>();
    User dummyUser1, dummyUser2;

    private static final String TAG = "MockUserServiceImplemen";

    public MockUserServiceImplementation() {
        dummyUser1 = new User();
        dummyUser1.setLogin("manideeplanka");
        dummyUser1.setAvatarUrl("http://www.nationalgeographic.com/photography/proof/2016/09/the-most-visual-science-textbook-you-ve-never-seen/#/clark-evolution-25.ngsversion.1475171232662.jpg");
        dummyUser1.setEmail("mlanka@rapidbizapps.com");

        userList.add(dummyUser1);

        dummyUser2 = new User();
        dummyUser2.setLogin("manideepl");
        dummyUser2.setAvatarUrl("http://www.nationalgeographic.com/photography/proof/2016/09/the-most-visual-science-textbook-you-ve-never-seen/#/clark-evolution-25.ngsversion.1475171232662.jpg");
        dummyUser2.setEmail("manideeplanka@rapidbizapps.com");

        userList.add(dummyUser2);
    }

    @Override
    public Observable<UserList> findUsers(@Query("q") String searchTerm) {
        Log.d(TAG, "findUsers: " + searchTerm);
        return Observable.just(new UserList(userList));
    }

    @Override
    public Observable<User> getUser(@Path("username") String username) {
        Log.d(TAG, "getUser: " + username);
        if (username.equals("manideeplanka")) return Observable.just(dummyUser1);
        else if (username.equals("manideepl")) return Observable.just(dummyUser2);
        return Observable.just(null);
    }
}
