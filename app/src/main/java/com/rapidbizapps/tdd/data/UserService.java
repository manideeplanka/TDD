package com.rapidbizapps.tdd.data;

import com.rapidbizapps.tdd.model.User;
import com.rapidbizapps.tdd.model.UserList;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by mlanka on 23-08-2016.
 */
public interface UserService {

    @GET("search/users/per_page=2") //limiting results to 2 per page
    Observable<UserList> findUsers(@Query("q") String searchTerm);

    @GET("users/{username}")
    Observable<User> getUser(@Path("username") String username);
}
