package com.rapidbizapps.tdd.ui.search;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.view.KeyEvent;

import com.rapidbizapps.tdd.R;
import com.rapidbizapps.tdd.data.remote.remote.MockUserServiceImplementation;

import org.junit.Rule;
import org.junit.Test;

import rx.Observable;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

/**
 * Created by mlanka on 14/10/16.
 */

public class FindUsersActivityTest {
    @Rule
    public ActivityTestRule<FindUsersActivity> activityActivityTestRule = new ActivityTestRule<>(FindUsersActivity.class);


    @Test
    public void findUsersActivity_OnLaunch_HintTextDisplayed() {
        //Given the app is launched
        //when the user does not interact with the view
        //then this text is displayed
        onView(withText("Start typing to search")).check(matches(isDisplayed()));
    }


    @Test
    public void findUsers_ReturnsCorrectlyFromService_DisplayResults() {
        //Given the find users activity is active

        //when the search term is entered
        onView(allOf(withId(R.id.menu_find_users), withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE))).perform(click());
        onView(withId(R.id.search_src_text)).perform(typeText("manideepl"), pressKey(KeyEvent.KEYCODE_ENTER));

        //then results are displayed
        onView(withText("Start typing to search")).check(matches(not(isDisplayed())));
        onView(withText("Manideep Lanka")).check(matches(isDisplayed()));
        onView(withText("mlanka@rapidbizapps.com")).check(matches(isDisplayed()));
        onView(withText("M L")).check(matches(isDisplayed()));
        onView(withText("manideep.dec@gmail.com")).check(matches(isDisplayed()));

        // TODO: 14/10/16 how to verify the images?
    }


    @Test
    public void findUsers_ServiceCallFails_ReturnError() {
        String errorMessage = "error! error! error!";

        MockUserServiceImplementation.setDummyResult(Observable.error(new Exception(errorMessage)));

        //when the search term is entered
        onView(allOf(withId(R.id.menu_find_users), withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE))).perform(click());
        onView(withId(R.id.search_src_text)).perform(typeText("manideepl"), pressKey(KeyEvent.KEYCODE_ENTER));

        onView(withText(errorMessage)).check(matches(isDisplayed()));
    }
}
