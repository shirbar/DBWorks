package com.myapp.fieldsbs;

import android.text.Editable;
import android.text.TextWatcher;

import java.util.regex.Pattern;

public class PhoneValidator implements TextWatcher {

    public static final Pattern PHONE_PATTERN = Pattern.compile(
            "^[+]?[0-9]{10,13}$"
    );

    private boolean mIsValid = false;
    public boolean isValid() {
        return mIsValid;
    }

    public static boolean isValidPhone(CharSequence phone) {
        return phone != null && PHONE_PATTERN.matcher(phone).matches();
    }

    @Override
    final public void afterTextChanged(Editable editableText) {
        mIsValid = isValidPhone(editableText);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

}

