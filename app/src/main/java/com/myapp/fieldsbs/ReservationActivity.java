package com.myapp.fieldsbs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReservationActivity extends AppCompatActivity {

    ListView ActivityList;
    Button BackBtn;
    DatabaseReference ActivityRef;
    FirebaseAuth fAtuth;
    String UserRef;
    ArrayList<String> ShowList;
    String date, fieldId, fieldname, hour, type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        ActivityList = (findViewById(R.id.listActivity));
        BackBtn = (findViewById(R.id.goBackBtn2));
        ShowList = new ArrayList<>();

        fAtuth = FirebaseAuth.getInstance();
        UserRef = fAtuth.getUid();

        ActivityRef = FirebaseDatabase.getInstance().getReference().child("Users").child(UserRef).child("Activities");

        ActivityRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    type = ds.child("Type").getValue().toString();
                    hour = ds.child("Hour").getValue().toString();
                    fieldname = ds.child("Field name").getValue().toString();
                    fieldId = ds.child("Field ID").getValue().toString();
                    date = ds.child("Date").getValue().toString();
                    ShowList.add("|פעילות:       " + type + "\n| שם:   " + fieldname + "\n| מספר מגרש:  " + fieldId + ":תאריך| \n" + date + "\n| שעה:   " + hour);
                }

                ListAdapter listAdapter = new ArrayAdapter<>(ReservationActivity.this, android.R.layout.simple_list_item_1, ShowList);
                ActivityList.setAdapter(listAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }

        });

        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }
}