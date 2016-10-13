package com.rapidbizapps.tdd.data.remote;

import com.rapidbizapps.tdd.data.model.User;
import com.rapidbizapps.tdd.data.model.UserList;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by mlanka on 23-08-2016.
 */
public interface UserService {
    @Headers("Accept: application/vnd.github.v3+json")
    @GET("search/users")
    Observable<UserList> findUsers(@Query("q") String searchTerm);

    @Headers("Accept: application/vnd.github.v3+json")
    @GET("users/{username}")
    Observable<User> getUser(@Path("username") String username);
}



