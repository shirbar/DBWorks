package com.myapp.fieldsbs;

import androidx.test.core.app.ActivityScenario;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4ClassRunner.class)
public class MainActivityTest {

    @Test
    public void test_activity_inView() {
        ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.mainLayout)).check(matches(isDisplayed()));
    }

    @Test
    public void test_AviliableBtn_TurnirsBtn_MyReserveBtn_visibility() {
        ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.AviliableBtn)).check(matches(withText(R.string.search_fields)));
        onView(withId(R.id.TurnirsBtn)).check(matches(withText(R.string.turnirs_and_trainings)));
        onView(withId(R.id.MyReserveBtn)).check(matches(withText(R.string.my_activity)));
    }

    @Test
    public void test_AviliableBtn_navigate_correctly() {
        ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.AviliableBtn)).perform(click());
        onView(withId(R.id.availableLayout)).check(matches(isDisplayed()));
    }

    @Test
    public void test_TurnirsBtn_navigate_correctly() {
        ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.TurnirsBtn)).perform(click());
        onView(withId(R.id.turnirsLayout)).check(matches(isDisplayed()));
    }


    @Test
    public void test_LogoutBtn_navigate_correctly() {
        ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.logoutBtn)).perform(click());
        onView(withId(R.id.loginLayout)).check(matches(isDisplayed()));
    }

    @Test
    public void test_backPressed_toLoginActivity() {
        ActivityScenario.launch(MainActivity.class);
        pressBack();
        onView(withId(R.id.loginLayout)).check(matches(isDisplayed()));
    }
}