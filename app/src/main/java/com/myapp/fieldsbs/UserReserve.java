package com.myapp.fieldsbs;

/*
This class describes an entity or reservation that been made by a user.
Every reservation and assignment of user to activity in a field - should create new ActivityClass entity.
 */

import androidx.appcompat.app.AppCompatActivity;

public class UserReserve extends AppCompatActivity {
    String keyField, idField, typeField, dateField, fieldName;

    public UserReserve(String keyField, String idField, String typeField, String dateField, String fieldName) {
        this.keyField = keyField;
        this.idField = idField;
        this.typeField = typeField;
        this.dateField = dateField;
        this.fieldName = fieldName;
    }

    public String getKeyField() {
        return keyField;
    }

    public String getIdField() {
        return idField;
    }

    public String getTypeField() {
        return typeField;
    }

    public String getDateField() {
        return dateField;
    }

    public String getFieldName() {
        return fieldName;
    }
}
