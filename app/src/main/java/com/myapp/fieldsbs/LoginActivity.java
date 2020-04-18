package com.myapp.fieldsbs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LoginActivity extends AppCompatActivity {

    EditText mEmail, mPassword;
    Button loginBtn;
    TextView registerTxt;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    FirebaseDatabase database;
    DatabaseReference userRef;
    static final String USERS = "Users";


    /*public LoginActivity(Context mMockContext) {

    }*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        fAuth = FirebaseAuth.getInstance();
        InitializeFields();


    }

    private void InitializeFields() {
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);
        registerTxt = findViewById(R.id.registerTxt);
        progressBar = findViewById(R.id.progressBar);
    }


    public void registerPageClick(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void loginClick(View view){
        String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            mEmail.setError("Email is Required.");
            return;
        }
        if (TextUtils.isEmpty(password)){
            mPassword.setError("Email is Required.");
            return;
        }
        progressBar.setVisibility(View.VISIBLE);


        loginAction(email, password);
    }


    public boolean test(){
        return true;
    }



    public void loginAction(String email, String password) {
        fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {


                    database = FirebaseDatabase.getInstance();
                    userRef = database.getReference(USERS);
                    userRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String id= fAuth.getUid();

                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(LoginActivity.this, "Logged in Successfully.", Toast.LENGTH_SHORT).show();

                            for(DataSnapshot ds : dataSnapshot.getChildren()) {

                                if (ds.child("id").getValue().toString().equals(id)){
                                    if(ds.child("isAdmin").getValue().toString().equals("true")) {
                                        redirectAdmin();
                                    }
                                    else{
                                        redirectUser();
                                    }
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                } else {
                    Toast.makeText(LoginActivity.this, "Error! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);

                }
            }
        });
    }

    public void redirectUser(){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
    public void redirectAdmin(){
        startActivity(new Intent(getApplicationContext(), AdminMainActivity.class));
    }




}
