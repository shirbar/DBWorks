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
public class BasketballActivityTest {

    @Test
    public void test_activity_inView() {
        ActivityScenario.launch(BasketballActivity.class);
        onView(withId(R.id.basketballLayout)).check(matches(isDisplayed()));
    }

    @Test
    public void test_title_and_userSelect_displayed() {
        ActivityScenario.launch(BasketballActivity.class);
        onView(withId(R.id.ListTitle)).check(matches(isDisplayed()));
        onView(withId(R.id.fieldName)).check(matches(isDisplayed()));
    }

    @Test
    public void test_title_and_userSelect_with_text() {
        ActivityScenario.launch(BasketballActivity.class);
        onView(withId(R.id.ListTitle)).check(matches(withText(R.string.basketFieldsList)));
        onView(withId(R.id.fieldName)).check(matches(withText(R.string.selectAfield)));
    }

    @Test
    public void test_backBtn_navigate_to_availableLayout_properly() {
        ActivityScenario.launch(BasketballActivity.class);
        onView(withId(R.id.goBackBtn)).perform(click());
        onView(withId(R.id.availableLayout)).check(matches(isDisplayed()));
    }

    @Test
    public void test_NextBtn_wait_for_selection() {
        ActivityScenario.launch(BasketballActivity.class);
        onView(withId(R.id.nextBtn)).perform(click());
        onView(withId(R.id.basketballLayout)).check(matches(isDisplayed()));
    }

    @Test
    public void test_backPressed_to_availableLayout() {
        ActivityScenario.launch(BasketballActivity.class);
        pressBack();
        onView(withId(R.id.availableLayout)).check(matches(isDisplayed()));
    }

    @Test
    public void test_imageView_displayed() {
        ActivityScenario.launch(BasketballActivity.class);
        onView(withId(R.id.listView)).check(matches(isDisplayed()));
    }
}