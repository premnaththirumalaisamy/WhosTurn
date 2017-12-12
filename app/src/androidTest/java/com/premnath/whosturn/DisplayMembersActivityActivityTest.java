package com.premnath.whosturn;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by premnath on 06/12/17.
 */

@RunWith(AndroidJUnit4.class)
public class DisplayMembersActivityActivityTest {

    @Rule
    public ActivityTestRule membersActivityTest = new ActivityTestRule(DisplayMembersActivity.class);

    @Test
    public void verifyAddMembersButtonTextContent(){
        onView(withText("Add DisplayMembersActivity")).check(matches(isDisplayed()));
    }
}
