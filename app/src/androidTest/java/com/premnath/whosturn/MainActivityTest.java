package com.premnath.whosturn;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.app.AlertDialog;
import android.widget.TextView;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static android.support.test.espresso.matcher.RootMatchers.*;
import static android.support.test.espresso.Espresso.onView;

/**
 * Created by premnath on 12/12/17.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule mainActivityTestRule = new ActivityTestRule(MainActivity.class);

    @Test
    public void verifyViewElementsPresence(){
        onView(withText("Activities")).check(matches(isDisplayed()));
        onView(withText(R.string.dustbin)).check(matches(isDisplayed()));
        onView(withText(R.string.utensils)).check(matches(isDisplayed()));
        onView(withText(R.string.livingroom)).check(matches(isDisplayed()));
        onView(withText(R.string.bathroom)).check(matches(isDisplayed()));
        onView(withText(R.string.members)).check(matches(isDisplayed()));
    }

    @Test
    public void verifyClickDoneButton(){
        onView(withText(R.string.dustbin))
                .perform(click());

        AlertDialog dialog = MainActivity.getLastDialog();
        String before = ((TextView) dialog.findViewById(android.R.id.message)).getText().toString();

        onView(withText("Done"))
                .inRoot(isDialog())
                .perform(click());

        String after = ((TextView) dialog.findViewById(android.R.id.message)).getText().toString();

        onView(withText(R.string.dustbin))
                .perform(click());

        Assert.assertEquals("Members should not be same",before,after);
    }

    @Test
    public void verifyClickNotYetButton(){
        onView(withText(R.string.dustbin))
                .perform(click());

        AlertDialog dialog = MainActivity.getLastDialog();
        String before = ((TextView) dialog.findViewById(android.R.id.message)).getText().toString();

        onView(withText("Not Yet"))
                .inRoot(isDialog())
                .perform(click());

        onView(withText(R.string.dustbin))
                .perform(click());

        String after = ((TextView) dialog.findViewById(android.R.id.message)).getText().toString();

        Assert.assertEquals("Members should be same",before,after);
    }

    @Test
    public void verifyMembersActivityLaunchOnClick(){
        onView(withId(R.id.btn_members))
                .perform(click());

        onView(withId(R.id.members_list))
                .check(matches(isDisplayed()));
    }

    @Test
    public void verifyTaskOwnersAcrossTasksAfterCompletion(){

        onView(withText(R.string.utensils))
                .perform(click());

        AlertDialog dialog = MainActivity.getLastDialog();

        String task2_owner_before = ((TextView) dialog.findViewById(android.R.id.message)).getText().toString();
        clickBtn("Not Yet");

        //Performing Task 1
        onView(withText(R.string.dustbin))
                .perform(click());
        clickBtn("Done");

        onView(withText(R.string.utensils))
                .perform(click());

        String task2_owner_after = ((TextView) dialog.findViewById(android.R.id.message)).getText().toString();

        Assert.assertEquals("Changes in one task is changing the state of other tasks",task2_owner_before,task2_owner_after);
    }

    public static void clickBtn(String action){
        onView(withText(action))
                .inRoot(isDialog())
                .perform(click());
    }

}