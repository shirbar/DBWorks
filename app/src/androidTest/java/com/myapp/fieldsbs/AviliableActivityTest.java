package com.myapp.fieldsbs;

import androidx.test.core.app.ActivityScenario;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4ClassRunner.class)
public class AviliableActivityTest {

    @Test
    public void test_activity_inView() {
        ActivityScenario.launch(AviliableActivity.class);
        onView(withId(R.id.availableLayout)).check(matches(isDisplayed()));
    }

    @Test
    public void test_football_basketBall_gymAreas_buttons_visibility() {
        ActivityScenario.launch(AviliableActivity.class);
        onView(withId(R.id.btnFootball)).check(matches(isDisplayed()));
        onView(withId(R.id.basketballBtn)).check(matches(isDisplayed()));
        onView(withId(R.id.gymBtn)).check(matches(isDisplayed()));
    }

    @Test
    public void test_text_buttons_correctly() {
        ActivityScenario.launch(AviliableActivity.class);
        onView(withId(R.id.btnFootball)).check(matches(withText(R.string.footballFields)));
        onView(withId(R.id.basketballBtn)).check(matches(withText(R.string.basketballFields)));
        onView(withId(R.id.gymBtn)).check(matches(withText(R.string.gymAreas)));
    }

    @Test
    public void test_backButton_navigate_properly() {
        ActivityScenario.launch(AviliableActivity.class);
        onView(withId(R.id.backBtn)).perform(click());
        onView(withId(R.id.mainLayout)).check(matches(isDisplayed()));
    }
}