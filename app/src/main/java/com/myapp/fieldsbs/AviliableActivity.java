package com.myapp.fieldsbs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AviliableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aviliable);

        Button footballBtn = findViewById(R.id.btnFootball);
        Button basketballBtn = findViewById(R.id.basketballBtn);
        Button gymBtn = findViewById(R.id.gymBtn);
        Button backBtn = findViewById(R.id.backBtn);

        footballBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("doing football");
                Intent i =  new Intent(AviliableActivity.this, FootballActivity.class);
                startActivity(i);
            }
        });

        basketballBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =  new Intent(AviliableActivity.this, BasketballActivity.class);
                startActivity(i);
            }
        });


        gymBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =  new Intent(AviliableActivity.this, GymActivity.class);
                startActivity(i);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(AviliableActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(AviliableActivity.this, MainActivity.class));
    }

}
