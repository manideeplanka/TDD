package com.rapidbizapps.tdd.ui.search;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by mlanka on 14/10/16.
 */

public class FindUsersActivityTest {
    @Rule
    public ActivityTestRule<FindUsersActivity> activityActivityTestRule = new ActivityTestRule<>(FindUsersActivity.class);

    //Given the app is launched
    //when the user does not interact with the view
    //then this text is displayed
    @Test
    public void findUsersActivity_OnLaunch_HintTextDisplayed() {
        onView(withText("Start typing to search")).check(matches(isDisplayed()));
    }


    //Given the find users activity is active
    //when the search term is entered
    //then results are displayed
    @Test
    public void findUsers_ReturnsCorrectlyFromService_DisplayResults() {

    }
}
