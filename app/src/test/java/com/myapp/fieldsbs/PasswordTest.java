package com.myapp.fieldsbs;

import org.junit.Test;

import static org.junit.Assert.*;

public class PasswordTest {

    @Test
    public void passwordValidator_NumberAndLettersCheck_ReturnsTrue() {
        assertTrue(PasswordValidator.isValidPassword("Kfir123456"));
    }


    @Test
    public void emailValidator_NumbersOnlyCheck_ReturnsTrue() {
        assertTrue(PasswordValidator.isValidPassword("12345678"));
    }


    @Test
    public void emailValidator_LettersOnlyCheck_ReturnsTrue() {
        assertTrue(PasswordValidator.isValidPassword("abcdefgH"));
    }


    @Test
    public void emailValidator_WithDotCheck_ReturnsTrue() {
        assertTrue(PasswordValidator.isValidPassword("A153...."));
    }


    @Test
    public void emailValidator_ShortPasswordLessThanSixCharactersCheck_ReturnsFalse() {
        assertFalse(PasswordValidator.isValidPassword("A153"));
    }


    @Test
    public void emailValidator_EmptyPasswordCheck_ReturnsFalse() {
        assertFalse(PasswordValidator.isValidPassword(""));
    }

    @Test
    public void emailValidator_FiveLettersCheck_ReturnsFalse() {
        assertFalse(PasswordValidator.isValidPassword("ABCDE"));
    }

}