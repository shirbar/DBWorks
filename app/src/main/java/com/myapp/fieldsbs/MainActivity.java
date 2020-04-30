package com.myapp.fieldsbs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private Button TurnirsBtn, AviliableBtn, logOutBtn;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

        TurnirsBtn = (Button)findViewById(R.id.TurnirsBtn);
        AviliableBtn = (Button)findViewById(R.id.AviliableBtn);
        logOutBtn = (Button)findViewById(R.id.LogOutBtn);


        TurnirsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Turnir();
            }
        });

        AviliableBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            aviliable();
            }
        });

        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               logOut();
            }
        });

    }

    private void logOut() {
        Intent i = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(i);
    }

    private void aviliable() {
        Intent i = new Intent(MainActivity.this,AviliableActivity.class);
        startActivity(i);
    }


    private void Turnir() {
        Intent i = new Intent(MainActivity.this,TurnirsActivity.class);
        startActivity(i);
    }
}
