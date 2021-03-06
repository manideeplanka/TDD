package com.rapidbizapps.tdd.ui;


import com.rapidbizapps.tdd.data.UserRepository;
import com.rapidbizapps.tdd.data.model.User;
import com.rapidbizapps.tdd.data.model.UserList;
import com.rapidbizapps.tdd.ui.base.BasePresenter;
import com.rapidbizapps.tdd.ui.search.FindUsersContract;
import com.rapidbizapps.tdd.ui.search.FindUsersPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.schedulers.Schedulers;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.never;
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


    /**
     * positive scenario when finding users
     */
    @Test
    public void findUsers_ValidSearchTerm_ReturnsResults() {
        UserList userList = getDummyUserList();

        when(mUserRepository.searchUsers(anyString())).thenReturn(Observable.just(userList.getItems()));

        mFindUsersPresenter.find("manideep");

        verify(view).showProgress();
        verify(view).showSearchResults(userList.getItems());
        verify(view, never()).showError(anyString());

    }

    /**
     * negative scenario when finding users
     */
    @Test
    public void findUsers_UserRepositoryError_ShowError() {
        String errorMessage = "ERROR ERROR ERROR";
        //given
        // TODO: 6/10/16 cannot resolve method!! :(
        when(mUserRepository.searchUsers(anyString())).thenReturn(Observable.error(new Throwable(errorMessage))); //user repository throws an error

        //when
        mFindUsersPresenter.find("manideep"); //find users

        //then
        verify(view).showProgress();
        verify(view).dismissProgress();
        verify(view, never()).showSearchResults(anyList());
        verify(view).showError(errorMessage); //error message is shown
    }

    /**
     * scenario - view not attached
     */
    @Test(expected = BasePresenter.ViewNotAttachedException.class)
    public void findUsers_ViewNotAttached_ThrowException() {
        //given
        mFindUsersPresenter.detachView(); //view not attached

        //when
        mFindUsersPresenter.find("manideep"); //find users

        //then
        //no methods called, exception thrown
        verify(view, never()).showProgress();

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
