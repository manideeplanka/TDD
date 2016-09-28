package com.rapidbizapps.tdd.data;

import com.rapidbizapps.tdd.model.User;
import com.rapidbizapps.tdd.model.UserList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by mlanka on 29-08-2016.
 */
public class UserRepositoryImplementationTest {

    private static final String USER_LOGIN_MANIDEEP_LANKA = "manideeplanka";
    private static final String USER_LOGIN_2_MANIDEEPL = "manideepl";
    UserRepository mUserRepository;

    @Mock
    UserService mUserService;

    //methods annotated this way ensure they run before any unit tests
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mUserRepository = new UserRepositoryImplementation(mUserService);
    }

    @Test
    public void findUsers_200OkResponse_InvokesCorrectApiCalls() {
        //Given
        when(mUserService.findUsers(anyString())).thenReturn(Observable.just(githubUserList()));
        when(mUserService.getUser(anyString())).thenReturn(Observable.just(user1FullDetails()), Observable.just(user2FullDetails()));

        //When
        TestSubscriber<List<User>> subscriber = new TestSubscriber<>();
        mUserRepository.searchUsers(USER_LOGIN_MANIDEEP_LANKA).subscribe(subscriber);

        //Then
        subscriber.awaitTerminalEvent();
        subscriber.assertNoErrors();

        List<List<User>> onNextEvents = subscriber.getOnNextEvents();
        List<User> users = onNextEvents.get(0);
        Assert.assertEquals(USER_LOGIN_MANIDEEP_LANKA, users.get(0).getLogin());
        Assert.assertEquals(USER_LOGIN_2_MANIDEEPL, users.get(1).getLogin());
        verify(mUserService).findUsers(USER_LOGIN_MANIDEEP_LANKA);
        verify(mUserService).getUser(USER_LOGIN_MANIDEEP_LANKA);
        verify(mUserService).getUser(USER_LOGIN_2_MANIDEEPL);
    }

    private UserList githubUserList() {
        User user = new User();
        user.setLogin(USER_LOGIN_MANIDEEP_LANKA);

        User user2 = new User();
        user2.setLogin(USER_LOGIN_2_MANIDEEPL);

        List<User> githubUsers = new ArrayList<>();
        githubUsers.add(user);
        githubUsers.add(user2);
        UserList usersList = new UserList(githubUsers);
        usersList.setItems(githubUsers);
        return usersList;
    }

    private User user1FullDetails() {
        User user = new User();
        user.setLogin(USER_LOGIN_MANIDEEP_LANKA);
        user.setName("Rigs Franks");
        user.setAvatarUrl("avatar_url");
        user.setBio("Bio1");
        return user;
    }

    private User user2FullDetails() {
        User user = new User();
        user.setLogin(USER_LOGIN_2_MANIDEEPL);
        user.setName("Rebecca Franks");
        user.setAvatarUrl("avatar_url2");
        user.setBio("Bio2");
        return user;
    }
}