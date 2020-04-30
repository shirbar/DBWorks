package com.myapp.fieldsbs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class FootballActivity extends AppCompatActivity {

    TextView a,b,c;
    Button btn;
    DatabaseReference reff;
    FirebaseAuth fAuth;
    public String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_football);

        a = findViewById(R.id.text1);
        b = findViewById(R.id.text2);
        c = findViewById(R.id.text3);
        btn = findViewById(R.id.btn1);
        fAuth = FirebaseAuth.getInstance();
        id= fAuth.getUid();

        reff = FirebaseDatabase.getInstance().getReference().child("Fields").child("0");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String name = dataSnapshot.child("Type").getValue().toString();
                String email = dataSnapshot.child("condition").getValue().toString();
                String phone = dataSnapshot.child("lighting").getValue().toString();
                a.setText(name);
                b.setText(email);
                c.setText(phone);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }













}
