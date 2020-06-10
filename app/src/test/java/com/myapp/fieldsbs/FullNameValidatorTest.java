package com.myapp.fieldsbs;

import org.junit.Test;

import static org.junit.Assert.*;

public class FullNameValidatorTest {

    @Test
    public void fullNameValidator_FirstAndLastNameCheck_ReturnsTrue() {
        assertTrue(FullNameValidator.isValidFullName("Moshe Moshe"));
    }

    @Test
    public void fullNameValidator_FirstNameOnlyCheck_ReturnsTrue() {
        assertTrue(FullNameValidator.isValidFullName("Moshe"));
    }

    @Test
    public void fullNameValidator_EmptyCheck_ReturnsFalse() {
        assertFalse(FullNameValidator.isValidFullName(""));
    }

    @Test
    public void fullNameValidator_DigitsCheck_ReturnsFalse() {
        assertFalse(FullNameValidator.isValidFullName("1315 65416"));
    }

    @Test
    public void fullNameValidator_SpecialSymbolsCheck_ReturnsFalse() {
        assertFalse(FullNameValidator.isValidFullName("mosh~e Mo!^#she"));
    }

    @Test
    public void fullNameValidator_LessThanThreeCharactersCheck_ReturnsFalse() {
        assertFalse(FullNameValidator.isValidFullName("mo"));
    }

    @Test
    public void fullNameValidator_LettersWithDigitsCheck_ReturnsFalse() {
        assertFalse(FullNameValidator.isValidFullName("Arth3r Mosh3e"));
    }

}