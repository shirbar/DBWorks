package com.myapp.fieldsbs;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4ClassRunner.class)
public class LoginActivityTest {

    @Test
    public void test_activity_inView() {
        ActivityScenario.launch(LoginActivity.class);
        onView(withId(R.id.loginLayout)).check(matches(isDisplayed()));
    }

    @Test
    public void test_titleAndSubtitle_visible() {
        ActivityScenario.launch(LoginActivity.class);
        onView(withId(R.id.subTitle_txt)).check(matches(isDisplayed()));
    }

    @Test
    public void test_invisibility_progressbar() {
        ActivityScenario.launch(LoginActivity.class);
        onView(withId(R.id.progressBar)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));
    }

    @Test
    public void test_title_displayed() {
        ActivityScenario.launch(LoginActivity.class);
        onView(withId(R.id.subTitle_txt)).check(matches(withText(R.string.loginPage)));
    }

    @Test
    public void test_emailAndPasswordPlainText_visible() {
        ActivityScenario.launch(LoginActivity.class);
        onView(withId(R.id.email)).check(matches(isDisplayed()));
        onView(withId(R.id.password)).check(matches(isDisplayed()));
    }

    @Test
    public void test_backPressed_notLeavingLoginActivity() {
        ActivityScenario.launch(LoginActivity.class);
        pressBack();
        onView(withId(R.id.loginLayout)).check(matches(isDisplayed()));
    }

    @Test
    public void test_does_not_have_an_account_text_navigate_properly() {
        ActivityScenario.launch(LoginActivity.class);
        onView(withId(R.id.registerTxt)).perform(click());
        onView(withId(R.id.registerLayout)).check(matches(isDisplayed()));
    }
}