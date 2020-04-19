package com.myapp.fieldsbs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;


public class RegisterActivity extends AppCompatActivity {

    EditText mFullName, mEmail, mPassword, mPhone;
    Button registerBtn;
    TextView loginTxt;
    ProgressBar progressBar;
    DatabaseReference myDbRef;
    FirebaseAuth fireBaseAuth;
    private FullNameValidator mFullNameValidator;
    private EmailValidator mEmailValidator;
    private PasswordValidator mPasswordValidator;
    private PhoneValidator mPhoneValidator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        myDbRef= FirebaseDatabase.getInstance().getReference("Users");
        fireBaseAuth = FirebaseAuth.getInstance();


        InitializeFields();

        mFullNameValidator = new FullNameValidator();
        mFullName.addTextChangedListener(mFullNameValidator);
        mEmailValidator = new EmailValidator();
        mEmail.addTextChangedListener(mEmailValidator);
        mPasswordValidator = new PasswordValidator();
        mPassword.addTextChangedListener(mPasswordValidator);
        mPhoneValidator = new PhoneValidator();
        mPhone.addTextChangedListener(mPhoneValidator);

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


    public void addUser() {
        String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();

        if (!mFullNameValidator.isValid()){
            mFullName.setError("Full Name is not valid. (must contain of letters ONLY and at least 3 characters)");
            return;
        }
        else if (!mEmailValidator.isValid()){
            mEmail.setError("Email is not valid.");
            return;
        }
        else if (!mPasswordValidator.isValid()){
            mPassword.setError("Password is not valid. (must contain 6 characters or digits)");
            return;
        }
        else if (!mPhoneValidator.isValid()){
            mPhone.setError("Phone is not valid. (must contain 10-13 digits and numbers only.");
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
                    assert id != null;
                    myDbRef.child(id).setValue(user);
                    Toast.makeText(RegisterActivity.this, "User Created.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }
                else{
                    Toast.makeText(RegisterActivity.this, "Error! " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

    }

}
