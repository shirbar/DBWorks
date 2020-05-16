package com.myapp.fieldsbs;

import androidx.test.core.app.ActivityScenario;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4ClassRunner.class)
public class MainActivityTest {

    @Test
    public void test_activity_inView() {
        ActivityScenario activityScenario = ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.main)).check(matches(isDisplayed()));
    }

    @Test
    public void test_AviliableBtn_TurnirsBtn_MyReserveBtn_visibility() {
        ActivityScenario activityScenario = ActivityScenario.launch(MainActivity.class);

        onView(withId(R.id.AviliableBtn)).check(matches(withText(R.string.aviliable_and_close_fields)));
        onView(withId(R.id.TurnirsBtn)).check(matches(withText(R.string.turnirs_and_trainings)));
        onView(withId(R.id.MyReserveBtn)).check(matches(withText(R.string.my_activity)));
    }
}