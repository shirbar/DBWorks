package com.myapp.fieldsbs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ManageFieldsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_fields);
    }


    public void soccerFieldsClick(View view){
        //startActivity(new Intent(getApplicationContext(), SoccerFieldsActivity.class));
    }

    public void basketballFieldsClick(View view){
        //startActivity(new Intent(getApplicationContext(), BasketballFieldsActivity.class));
    }

    public void gymFieldsClick(View view){
        //startActivity(new Intent(getApplicationContext(), GymFieldsActivity.class));
    }

    public void backClick(View view){
        startActivity(new Intent(getApplicationContext(), AdminMainActivity.class));
    }
}
