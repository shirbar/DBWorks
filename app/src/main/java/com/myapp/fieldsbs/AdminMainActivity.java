package com.myapp.fieldsbs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class AdminMainActivity extends AppCompatActivity {

    TextView mainTxt;
    FirebaseUser user;
    private FirebaseAuth fAuth;
    FirebaseDatabase database;
    DatabaseReference userRef;
    static final String USERS = "Users";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        fAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }

        InitializeFields();


        database = FirebaseDatabase.getInstance();
        userRef = database.getReference(USERS);
        userRef.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String id = fAuth.getUid();
                    if (Objects.requireNonNull(ds.child("id").getValue()).toString().equals(id)) {
                        mainTxt.setText("Welcome Admin " + Objects.requireNonNull(ds.child("name").getValue()).toString() + "!");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void InitializeFields() {
        mainTxt = findViewById(R.id.tvMainText);
    }


    public void manageFieldsClick(View view) {
        startActivity(new Intent(getApplicationContext(), ManageFieldsActivity.class));
    }

    public void removeFieldsClick(View view) {
        startActivity(new Intent(getApplicationContext(), RemoveFieldsActivity.class));
    }


    public void logout(View view) {

        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }
}