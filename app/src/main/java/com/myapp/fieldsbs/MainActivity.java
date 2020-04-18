package com.myapp.fieldsbs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    TextView mainTxt;
    FirebaseUser user;
    private FirebaseAuth fAuth;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference dbRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fAuth = FirebaseAuth.getInstance();

        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null){
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }

        InitializeFields();

        String name = user.getEmail();
        String name2 = user.getProviderId();
        mainTxt.setText("Welcome " + name);

    }

    private void InitializeFields() {
        mainTxt = findViewById(R.id.tvMainText);
    }



    public void manageFieldsClick(View view){
        startActivity(new Intent(getApplicationContext(), ManageFieldsActivity.class));
    }

    public void removeFieldsClick(View view){
        startActivity(new Intent(getApplicationContext(), RemoveFieldsActivity.class));
    }


    public void logout(View view){

        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();


    }

/*
    @Override
    public void onBackPressed() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("hasBackPressed",true);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }
*/


}
