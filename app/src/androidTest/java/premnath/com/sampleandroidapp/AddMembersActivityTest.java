package premnath.com.sampleandroidapp;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.matcher.RootMatchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

/**
 * Created by premnath on 08/12/17.
 */

@RunWith(AndroidJUnit4.class)
public class AddMembersActivityTest {

    @Rule
    public ActivityTestRule addMembersActivity = new ActivityTestRule(AddMembersActivity.class);

    @Test
    public void verifyElementPresence(){
        onView(withText("Name")).check(matches(isDisplayed()));
        onView(withText("Add Member")).check(matches(isDisplayed()));
        onView(withId(R.id.member_name)).check(matches(isDisplayed()));
        onView(withId(R.id.add_btn)).check(matches(isDisplayed()));
    }

    @Test
    public void verifyMemberAddition(){
        String randomUser = "Member " + System.currentTimeMillis();
        onView(withId(R.id.member_name))
                .perform(clearText());
        onView(withId(R.id.member_name))
                .perform(typeText(randomUser),closeSoftKeyboard());
        onView(withId(R.id.add_btn))
                .perform(click());

        onView(withText("Successfully added " + randomUser)).
                inRoot(withDecorView(not(is(addMembersActivity.getActivity().getWindow().getDecorView())))).
                check(matches(isDisplayed()));

        Intent intent = new Intent(addMembersActivity.getActivity(),Members.class);
        addMembersActivity.getActivity().startActivity(intent);
        onView(withText(randomUser)).check(matches(isDisplayed()));
    }
}
