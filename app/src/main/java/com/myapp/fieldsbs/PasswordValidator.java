package com.myapp.fieldsbs;

import android.text.Editable;
import android.text.TextWatcher;

import java.util.regex.Pattern;

public class PasswordValidator implements TextWatcher{

    public static final Pattern PASSWORD_PATTERN = Pattern.compile(
            "^(?=.*[a-zA-Z0-9])(?=\\S+$).{6,}$"
    );

    private boolean mIsValid = false;
    public boolean isValid() {
        return mIsValid;
    }

    public static boolean isValidPassword(CharSequence password) {
        return password != null && PASSWORD_PATTERN.matcher(password).matches();
    }

    @Override
    final public void afterTextChanged(Editable editableText) {
        mIsValid = isValidPassword(editableText);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

}