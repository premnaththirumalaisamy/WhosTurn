package premnath.com.sampleandroidapp;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by premnath on 06/12/17.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MembersActivityTest {

    @Rule
    public ActivityTestRule membersActivityTest = new ActivityTestRule(Members.class);

    @Test
    public void verifyAddMembersButtonTextContent(){
        onView(withText("Add Members")).check(matches(isDisplayed()));
    }
}
