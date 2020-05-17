package com.myapp.fieldsbs;

import androidx.test.core.app.ActivityScenario;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4ClassRunner.class)
public class ConnectionToFirebaseTest {

    @Test
    public void test_activity_inView() {
        ActivityScenario.launch(LoginActivity.class);
        onView(withId(R.id.loginLayout)).check(matches(isDisplayed()));
    }

    @Test
    public void test_user_successful_connection(){
        ActivityScenario.launch(LoginActivity.class);
        onView(withId(R.id.email)).perform(replaceText("kfir@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(replaceText("123456"));
        onView(withId(R.id.loginBtn)).perform(click());
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.mainLayout)).check(matches(isDisplayed()));
    }

    @Test
    public void test_user_failed_connection(){
        ActivityScenario.launch(LoginActivity.class);
        onView(withId(R.id.email)).perform(replaceText("kfir@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(replaceText("56156156"));
        onView(withId(R.id.loginBtn)).perform(click());
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.loginLayout)).check(matches(isDisplayed()));
    }

    @Test
    public void test_admin_successful_connection(){
        ActivityScenario.launch(LoginActivity.class);
        onView(withId(R.id.email)).perform(replaceText("admin@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(replaceText("123456"));
        onView(withId(R.id.loginBtn)).perform(click());
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.adminMainLayout)).check(matches(isDisplayed()));
    }

    @Test
    public void test_admin_failed_connection(){
        ActivityScenario.launch(LoginActivity.class);
        onView(withId(R.id.email)).perform(replaceText("admin@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(replaceText("7879898"));
        onView(withId(R.id.loginBtn)).perform(click());
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.loginLayout)).check(matches(isDisplayed()));
    }
}