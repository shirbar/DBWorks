package com.myapp.fieldsbs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class RegisterActivity extends AppCompatActivity {

    EditText mFullName, mEmail, mPassword, mPhone;
    Button registerBtn;
    TextView loginTxt;
    ProgressBar progressBar;
    DatabaseReference myDbRef;
    FirebaseAuth fireBaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        myDbRef= FirebaseDatabase.getInstance().getReference("Users");
        fireBaseAuth = FirebaseAuth.getInstance();


        InitializeFields();

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUser();
            }
        });

        loginTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    private void InitializeFields() {
        mFullName = findViewById(R.id.fullName);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mPhone = findViewById(R.id.phone);
        registerBtn = findViewById(R.id.registerBtn);
        loginTxt = findViewById(R.id.loginTxt);
        mFullName = findViewById(R.id.fullName);
        progressBar = findViewById(R.id.progressBar);

    }

    public boolean test(){
        return true;
    }

    public void addUser() {
        String fullName = mFullName.getText().toString().trim();
        String phone = mPhone.getText().toString().trim();
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
        if(password.length() < 6){
            mPassword.setError("Password must contain at least 6 characters.");
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        fireBaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    String fullName = mFullName.getText().toString().trim();
                    String phone = mPhone.getText().toString().trim();
                    String email = mEmail.getText().toString().trim();
                    String id= fireBaseAuth.getUid();
                    User user= new User (fullName, email, id, phone, "false");
                    myDbRef.child(id).setValue(user);
                    Toast.makeText(RegisterActivity.this, "User Created.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }
                else{
                    Toast.makeText(RegisterActivity.this, "Error! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

    }

}
