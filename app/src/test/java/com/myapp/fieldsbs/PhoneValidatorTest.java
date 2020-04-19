package com.myapp.fieldsbs;

import org.junit.Test;

import static org.junit.Assert.*;

public class PhoneValidatorTest {

    @Test
    public void phoneValidator_TenDigitCheck_ReturnsTrue() {
        assertTrue(PhoneValidator.isValidPhone("0509118795"));
    }
    @Test
    public void phoneValidator_PlusSignWithCountryCodeCheck_ReturnsTrue() {
        assertTrue(PhoneValidator.isValidPhone("+972509118795"));
    }
    @Test
    public void phoneValidator_EmptyPhoneCheck_ReturnsFalse() {
        assertFalse(PhoneValidator.isValidPhone(""));
    }
    @Test
    public void phoneValidator_InsertLettersCheck_ReturnsFalse() {
        assertFalse(PhoneValidator.isValidPhone("safasfxa"));
    }
    @Test
    public void phoneValidator_ShortThanTenDigitsCheck_ReturnsFalse() {
        assertFalse(PhoneValidator.isValidPhone("123456789"));
    }
    @Test
    public void phoneValidator_LongerThanThirteenDigitsCheck_ReturnsFalse() {
        assertFalse(PhoneValidator.isValidPhone("12345678912345678"));
    }
}