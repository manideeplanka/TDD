package com.rapidbizapps.tdd.data.local;

import com.rapidbizapps.tdd.data.UserRepository;
import com.rapidbizapps.tdd.data.UserRepositoryImplementation;
import com.rapidbizapps.tdd.data.remote.UserService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mlanka on 5/10/16.
 */

public class ServiceHelper {
    private static Retrofit retrofit;
    static String API_BASE_URL = "";
    private static OkHttpClient okHttpClient;
    private static UserService userService;

    public static UserRepository getUserRepository() {
        return new UserRepositoryImplementation(getUserService());
    }


    public static Retrofit getRetrofit() {
        if (retrofit != null) {
            retrofit = new Retrofit.Builder().addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getHttpClient())
                    .baseUrl(API_BASE_URL)
                    .build();
        }

        return retrofit;
    }

    public static OkHttpClient getHttpClient() {
        if (okHttpClient != null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

            okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build();
        }

        return okHttpClient;
    }

    public static UserService getUserService() {
        if (userService != null) {
            userService = getRetrofit().create(UserService.class);
        }

        return userService;
    }
}
