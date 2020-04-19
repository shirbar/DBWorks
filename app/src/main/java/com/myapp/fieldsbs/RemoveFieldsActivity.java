package com.myapp.fieldsbs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RemoveFieldsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_fields);
    }


    public void backClick(View view){
        startActivity(new Intent(getApplicationContext(), AdminMainActivity.class));
    }

}