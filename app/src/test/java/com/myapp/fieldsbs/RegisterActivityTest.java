package com.myapp.fieldsbs;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class RegisterActivityTest {
    @Mock
    Context mMockContext;

    @Test
    public void registerAction_correct() {
        RegisterActivity myObject = new RegisterActivity();
        boolean result = myObject.registerAction("kfir152@gmail.com", "123456", "kfir ra", "05091123516", true);
        assertTrue(result);
    }
    @Test
    public void registerAction_not_correct() {
        RegisterActivity myObject = new RegisterActivity();
        boolean result = myObject.registerAction("kfir@@gmail.com", "12345678", "kfir", "0512351", true);
        assertFalse(result);
    }

    @Test
    public void registerAction_short_password() {
        RegisterActivity myObject = new RegisterActivity();
        boolean result = myObject.registerAction("kfir@gmail.com", "123", "kfir", "0512351651", true);
        assertFalse(result);
    }

    @Test
    public void registerAction_invalid_email() {
        RegisterActivity myObject = new RegisterActivity();
        boolean result = myObject.registerAction("kfir@gmail", "123456", "kfir", "0512345451", true);
        assertFalse(result);
    }

    @Test
    public void registerAction_invalid_phone_number() {
        RegisterActivity myObject = new RegisterActivity();
        boolean result = myObject.registerAction("kfir@gmail.com", "123456", "kfir", "0503", true);
        assertFalse(result);
    }

    @Test
    public void registerAction_invalid_full_name() {
        RegisterActivity myObject = new RegisterActivity();
        boolean result = myObject.registerAction("kfir@gmail.com", "123456", "k", "0512345515", true);
        assertFalse(result);
    }

}