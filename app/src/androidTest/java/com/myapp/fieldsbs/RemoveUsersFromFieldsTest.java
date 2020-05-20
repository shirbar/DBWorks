package com.myapp.fieldsbs;

import android.widget.DatePicker;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4ClassRunner.class)
public class RemoveUsersFromFieldsTest {

    @Test
    public void test_remove_users_from_football_field() {
        ActivityScenario.launch(LoginActivity.class);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.email)).perform(replaceText("admin@gmail.com"), closeSoftKeyboard());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.password)).perform(replaceText("123456"));
        onView(withId(R.id.loginBtn)).perform(click());
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.adminMainLayout)).check(matches(isDisplayed()));
        onView(withId(R.id.manageFieldsBtn)).perform(click());
        onView(withId(R.id.manageFieldsLayout)).check(matches(isDisplayed()));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.btnFootball)).perform(click());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.dateTxtt)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2020, 8, 10));
        onView(withId(android.R.id.button1)).perform(click());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.listView)).perform(click());
        onView(withId(R.id.btnRemove)).perform(click());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.manageFieldsLayout)).check(matches(isDisplayed()));
    }

    @Test
    public void test_remove_users_from_basketball_field() {
        ActivityScenario.launch(LoginActivity.class);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.email)).perform(replaceText("admin@gmail.com"), closeSoftKeyboard());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.password)).perform(replaceText("123456"));
        onView(withId(R.id.loginBtn)).perform(click());
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.adminMainLayout)).check(matches(isDisplayed()));
        onView(withId(R.id.manageFieldsBtn)).perform(click());
        onView(withId(R.id.manageFieldsLayout)).check(matches(isDisplayed()));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.btnBasketball)).perform(click());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.dateTxtt)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2020, 8, 10));
        onView(withId(android.R.id.button1)).perform(click());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.listView)).perform(click());
        onView(withId(R.id.btnRemove)).perform(click());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.manageFieldsLayout)).check(matches(isDisplayed()));
    }

    @Test
    public void test_remove_users_from_gym_areas() {
        ActivityScenario.launch(LoginActivity.class);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.email)).perform(replaceText("admin@gmail.com"), closeSoftKeyboard());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.password)).perform(replaceText("123456"));
        onView(withId(R.id.loginBtn)).perform(click());
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.adminMainLayout)).check(matches(isDisplayed()));
        onView(withId(R.id.manageFieldsBtn)).perform(click());
        onView(withId(R.id.manageFieldsLayout)).check(matches(isDisplayed()));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.btnGym)).perform(click());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.dateTxtt)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2020, 8, 10));
        onView(withId(android.R.id.button1)).perform(click());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.listView)).perform(click());
        onView(withId(R.id.btnRemove)).perform(click());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.manageFieldsLayout)).check(matches(isDisplayed()));
    }
}