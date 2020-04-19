package com.myapp.fieldsbs;

import android.text.Editable;
import android.text.TextWatcher;

import java.util.regex.Pattern;

public class FullNameValidator implements TextWatcher {

    private static final Pattern FULLNAME_PATTERN = Pattern.compile(
            "^[a-zA-z\\s]{3,}$"
    );
    private boolean mIsValid = false;

    boolean isValid() {
        return mIsValid;
    }

    static boolean isValidFullName(CharSequence fullName) {
        return fullName != null && FULLNAME_PATTERN.matcher(fullName).matches();
    }

    @Override
    final public void afterTextChanged(Editable editableText) {
        mIsValid = isValidFullName(editableText);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }
}
