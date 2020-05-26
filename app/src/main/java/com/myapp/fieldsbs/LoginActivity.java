package com.myapp.fieldsbs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;


public class LoginActivity extends AppCompatActivity {

    private EditText mEmail, mPassword;
    boolean loginStatusForTest, isAdmin;
    Button loginBtn;
    TextView registerTxt;
    private EmailValidator mEmailValidator;
    private PasswordValidator mPasswordValidator;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    FirebaseDatabase database;
    DatabaseReference userRef;
    static final String USERS = "Users";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fAuth = FirebaseAuth.getInstance();
        InitializeFields();

        mEmailValidator = new EmailValidator();
        mEmail.addTextChangedListener(mEmailValidator);
        mPasswordValidator = new PasswordValidator();
        mPassword.addTextChangedListener(mPasswordValidator);
    }

    public void loginClick(View view){
        String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();

        if (!mEmailValidator.isValid()){
            mEmail.setError("Email is not valid.");
            return;
        }
        else if (!mPasswordValidator.isValid()){
            mPassword.setError("Password is not valid. (must contain 6 characters or digits)");
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        loginAction(email, password, false);
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

    public boolean loginAction(String email, String password, boolean flag) {
        loginStatusForTest = false;
        if (!flag){
            fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        database = FirebaseDatabase.getInstance();
                        userRef = database.getReference(USERS);
                        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                String id= fAuth.getUid();
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(LoginActivity.this, "Logged in Successfully.", Toast.LENGTH_SHORT).show();
                                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                                    if (Objects.requireNonNull(ds.child("id").getValue()).toString().equals(id)){
                                        if(Objects.requireNonNull(ds.child("isAdmin").getValue()).toString().equals("true")) {
                                            loginStatusForTest = true;
                                            isAdmin = true;
                                            redirectAdmin();
                                        }
                                        else{
                                            loginStatusForTest = true;
                                            isAdmin = false;
                                            redirectUser(false);
                                        }
                                    }
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                            }
                        });
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Error! " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        loginStatusForTest = false;
                    }
                }
            });
        }
        else{
             if (EmailValidator.isValidEmail(email) && PasswordValidator.isValidPassword(password)){
                 loginStatusForTest = true;
                 return redirectUser(true);
             }
        }
        if (loginStatusForTest){
            if (isAdmin){
                return redirectAdmin();
            }
            return redirectUser(false);
        }
        else{
            return false;
        }
    }

    public boolean redirectUser(boolean flag) {
        if (!flag) {
            if (loginStatusForTest) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                return true;
            } else {
                return false;
            }
        } else {
            if (loginStatusForTest) {
                return true;
            } else {
                return false;
            }
        }
    }
    public boolean redirectAdmin(){
        startActivity(new Intent(getApplicationContext(), AdminMainActivity.class));
        return true;
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(LoginActivity.this, "You cannot go back.", Toast.LENGTH_SHORT).show();
    }
}
