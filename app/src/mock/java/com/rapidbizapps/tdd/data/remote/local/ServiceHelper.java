package com.rapidbizapps.tdd.data.remote.local;

import com.rapidbizapps.tdd.data.UserRepository;
import com.rapidbizapps.tdd.data.UserRepositoryImplementation;
import com.rapidbizapps.tdd.data.remote.UserService;
import com.rapidbizapps.tdd.data.remote.remote.MockUserServiceImplementation;

/**
 * Created by mlanka on 13/10/16.
 */

public class ServiceHelper {
    static UserService mUserService;

    public static UserRepository getUserRepository() {
        return new UserRepositoryImplementation(getUserService());
    }

    static UserService getUserService() {
        if (mUserService == null)
            mUserService = new MockUserServiceImplementation();

        return mUserService;
    }
}
