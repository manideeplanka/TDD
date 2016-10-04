package com.rapidbizapps.tdd.view;


import com.rapidbizapps.tdd.data.UserRepository;
import com.rapidbizapps.tdd.model.User;
import com.rapidbizapps.tdd.model.UserList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.schedulers.Schedulers;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by mlanka on 3/10/16.
 */
public class FindUsersPresenterTest {

    @Mock
    UserRepository mUserRepository;
    @Mock
    FindUsersContract.View view;

    FindUsersPresenter mFindUsersPresenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mFindUsersPresenter = new FindUsersPresenter(mUserRepository, Schedulers.immediate(), Schedulers.immediate());
        mFindUsersPresenter.attachView(view);
    }

    @Test
    public void findUsers_ValidSearchTerm_ReturnsResults() {
        UserList userList = getDummyUserList();

        when(mUserRepository.searchUsers(anyString())).thenReturn(Observable.just(userList.getItems()));

        mFindUsersPresenter.find("manideep");

        verify(view).showProgress();
        verify(view).dismissProgress();
        verify(view).showSearchResults(userList.getItems());
        verify(view, never()).showError(anyString());

    }

    private UserList getDummyUserList() {
        List<User> users = new ArrayList<>();
        users.add(user1Details());
        users.add(user2Details());

        return new UserList(users);
    }

    private User user2Details() {
        User user = new User();
        user.setName("Manideep Lanka");
        user.setBio("Bio of ML");
        return user;
    }

    private User user1Details() {
        User user = new User();
        user.setName("Manideep Lanka Jr");
        user.setBio("Bio of ML Jr");
        return user;
    }
}
