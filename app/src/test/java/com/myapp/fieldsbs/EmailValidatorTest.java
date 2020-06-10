package com.myapp.fieldsbs;

import org.junit.Test;

import static org.junit.Assert.*;

public class EmailValidatorTest {

    @Test
    public void emailValidator_SimpleEmailCheck_ReturnsTrue() {
        assertTrue(EmailValidator.isValidEmail("test@gmail.com"));
    }

    @Test
    public void emailValidator_SubDomainCheck_ReturnsTrue() {
        assertTrue(EmailValidator.isValidEmail("test@walla.co.il"));
    }

    @Test
    public void emailValidator_EmptyEmailCheck_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail(""));
    }

    @Test
    public void emailValidator_WithoutComCheck_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail("test@gmail"));
    }

    @Test
    public void emailValidator_WithoutUsernameCheck_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail("@gmail.com"));
    }

    @Test
    public void emailValidator_WithExtraCharactersCheck_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail("test@@gmail.com"));
    }
/*
    @Test
    public void emailValidator_EndingWithDot_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail("test@gmail."));
    }

    @Test
    public void emailValidator_WithTwoDots_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail("test@gmail..com"));
    }

    @Test
    public void emailValidator_UkDomainCheck_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail("test@gmail.co.uk"));
    }

    @Test
    public void emailValidator_OrgDomainCheck_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail("test@gmail.org"));
    }
*/
}