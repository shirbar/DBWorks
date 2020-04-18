package com.myapp.fieldsbs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText usertxt, emailtxt, password, phone;
    Button SignUpBtn, LogInBtn;
    DatabaseReference myDbRef;
    FirebaseAuth fireBaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDbRef= FirebaseDatabase.getInstance().getReference("Users");
        fireBaseAuth = FirebaseAuth.getInstance();

        usertxt = (EditText) findViewById(R.id.NameTXT);
        emailtxt= (EditText) findViewById(R.id.EmailTXT);
        password= (EditText) findViewById(R.id.PasswordTXT);
        SignUpBtn = (Button) findViewById(R.id.SignUpBtn);
        LogInBtn = (Button) findViewById(R.id.LogInBtn) ;


        SignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });

        LogInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(),LoginActivity.class);
                startActivity(i);
            }
        });

    }

    private void addUser() {
        String name = usertxt.getText().toString().trim();
        String email = emailtxt.getText().toString().trim();
        String pwd = password.getText().toString().trim();

        if(!TextUtils.isEmpty(name))
        {
            String id= myDbRef.push().getKey();
            User user= new User (name, email, pwd, id);
            myDbRef.child(id).setValue(user);

            fireBaseAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "User Created.", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Error! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            });

            Toast.makeText(this, "User Added", Toast.LENGTH_SHORT).show();
        }

        else {
            Toast.makeText(this,"Enter a name!", Toast.LENGTH_LONG).show();
        }

    }
}
