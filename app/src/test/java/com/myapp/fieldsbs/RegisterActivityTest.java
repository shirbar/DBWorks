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
}