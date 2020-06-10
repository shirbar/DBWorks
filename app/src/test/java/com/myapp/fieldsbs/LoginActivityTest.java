package com.myapp.fieldsbs;

import android.content.Context;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class LoginActivityTest {
    @Mock
    Context mMockContext;

    @Test
    public void loginAction_correct() {
        LoginActivity myObject = new LoginActivity();
        boolean result = myObject.loginAction("kfir@gmail.com", "123456", true);
        assertTrue(result);
    }

    @Test
    public void loginAction_not_correct() {
        LoginActivity myObject = new LoginActivity();
        boolean result = myObject.loginAction("kfir@gmail.com", "13219", true);
        assertFalse(result);
    }

    @Test
    public void loginAction_invalid_email() {
        LoginActivity myObject = new LoginActivity();
        boolean result = myObject.loginAction("asdasdasd", "1321915", true);
        assertFalse(result);
    }

    @Test
    public void loginAction_invalid_password() {
        LoginActivity myObject = new LoginActivity();
        boolean result = myObject.loginAction("kfir@gmail.com", "12", true);
        assertFalse(result);
    }

}