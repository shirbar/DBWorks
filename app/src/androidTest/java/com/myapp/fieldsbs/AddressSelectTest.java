package com.myapp.fieldsbs;

import android.widget.DatePicker;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.anything;

@RunWith(AndroidJUnit4ClassRunner.class)
public class AddressSelectTest {
    @Test
    public void test_address_assign_to_a_football_field() {
        ActivityScenario.launch(LoginActivity.class);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.email)).perform(replaceText("kfir@gmail.com"), closeSoftKeyboard());
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
        onView(withId(R.id.mainLayout)).check(matches(isDisplayed()));
        onView(withId(R.id.searchAddressBtn)).perform(click());
        onView(withId(R.id.addressLayout)).check(matches(isDisplayed()));
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.nextBtn)).perform(click());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.availableLayout)).check(matches(isDisplayed()));
        onView(withId(R.id.btnFootball)).perform(click());
        onView(withId(R.id.footballLayout)).check(matches(isDisplayed()));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.listView)).perform(click());
        onView(withId(R.id.nextBtn)).perform(click());
        onView(withId(R.id.fieldsLayout)).check(matches(isDisplayed()));
        onView(withId(R.id.dateTxt)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2020, 9, 15));
        onView(withId(android.R.id.button1)).perform(click());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.listView)).perform(click());
        onView(withId(R.id.assignMyselfBtn)).perform(click());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.fieldsLayout)).check(matches(isDisplayed()));
    }

    @Test
    public void test_address_assign_to_a_basket_field() {
        ActivityScenario.launch(LoginActivity.class);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.email)).perform(replaceText("kfir@gmail.com"), closeSoftKeyboard());
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
        onView(withId(R.id.mainLayout)).check(matches(isDisplayed()));
        onView(withId(R.id.searchAddressBtn)).perform(click());
        onView(withId(R.id.addressLayout)).check(matches(isDisplayed()));
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.nextBtn)).perform(click());
        onView(withId(R.id.availableLayout)).check(matches(isDisplayed()));
        onView(withId(R.id.basketballBtn)).perform(click());
        onView(withId(R.id.basketballLayout)).check(matches(isDisplayed()));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.listView)).perform(click());
        onView(withId(R.id.nextBtn)).perform(click());
        onView(withId(R.id.fieldsLayout)).check(matches(isDisplayed()));
        onView(withId(R.id.dateTxt)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2020, 9, 15));
        onView(withId(android.R.id.button1)).perform(click());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.listView)).perform(click());
        onView(withId(R.id.assignMyselfBtn)).perform(click());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.fieldsLayout)).check(matches(isDisplayed()));
    }

    @Test
    public void test_address_assign_to_a_gym_field() {
        ActivityScenario.launch(LoginActivity.class);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.email)).perform(replaceText("kfir@gmail.com"), closeSoftKeyboard());
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
        onView(withId(R.id.mainLayout)).check(matches(isDisplayed()));
        onView(withId(R.id.searchAddressBtn)).perform(click());
        onView(withId(R.id.addressLayout)).check(matches(isDisplayed()));
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.nextBtn)).perform(click());
        onView(withId(R.id.availableLayout)).check(matches(isDisplayed()));
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.gymBtn)).perform(click());
        onView(withId(R.id.gymLayout)).check(matches(isDisplayed()));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
        onView(withId(R.id.nextBtn)).perform(click());
        onView(withId(R.id.gymAreasLayout)).check(matches(isDisplayed()));
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.dateTxt)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2020, 8, 12));
        onView(withId(android.R.id.button1)).perform(click());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.listView)).perform(click());
        onView(withId(R.id.assignMyselfBtn)).perform(click());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.gymAreasLayout)).check(matches(isDisplayed()));
    }

}