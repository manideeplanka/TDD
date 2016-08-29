package com.rapidbizapps.tdd.data;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;


/**
 * Created by mlanka on 29-08-2016.
 */
public class UserRepositoryImplementationTest {

    UserRepository mUserRepository;

    @Mock
    UserService mUserService;

    //methods annotated this way ensure they run before any unit tests
    @Before
    void setup() {
        MockitoAnnotations.initMocks(this);
        mUserRepository = new UserRepositoryImplementation(mUserService);
    }
}