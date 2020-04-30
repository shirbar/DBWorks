package com.myapp.fieldsbs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AviliableActivity extends AppCompatActivity {

    private Button footballBtn, basketballBtn, tennisBtn, GymBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aviliable);

        footballBtn = (Button)findViewById(R.id.footBtn);
        basketballBtn = (Button)findViewById(R.id.BasketballBtn);
        tennisBtn = (Button)findViewById(R.id.TennisBtn);
        GymBtn = (Button)findViewById(R.id.gymBtn);

        footballBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                football();
            }
        });

        basketballBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basketball();
            }
        });

        tennisBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tennis();
            }
        });

        GymBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gym();
            }
        });

    }

    private void gym() {
        Intent i =  new Intent(AviliableActivity.this, GymActivity.class);
        startActivity(i);
    }

    private void tennis() {
        Intent i =  new Intent(AviliableActivity.this, TennisActivity.class);
        startActivity(i);
    }

    private void basketball() {
        Intent i =  new Intent(AviliableActivity.this, BasketballActivity.class);
        startActivity(i);
    }

    private void football() {
        Intent i =  new Intent(AviliableActivity.this, FootballActivity.class);
        startActivity(i);
    }


}
