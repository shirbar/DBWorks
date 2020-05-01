package com.myapp.fieldsbs;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class FieldsActivity extends AppCompatActivity {

    String key, id, type, date;
    TextView dateTxt, hoursTxt, fieldTxt;
    DatePickerDialog.OnDateSetListener mDateListener;
    DatabaseReference dbRef;
    DatabaseReference editRef;
    ListView myList;
    ArrayList<String> showList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fields);

        dbRef = FirebaseDatabase.getInstance().getReference();
        editRef = FirebaseDatabase.getInstance().getReference();
        dateTxt = findViewById(R.id.dateTxt);
        hoursTxt = findViewById(R.id.hoursTxt);
        fieldTxt = findViewById(R.id.fieldName);
        myList = findViewById(R.id.listView);
        showList = new ArrayList<>();




        //getting the data from the previous intent with getStringExtra
        id = getIntent().getStringExtra("id");
        key = getIntent().getStringExtra("key");
        type = getIntent().getStringExtra("type");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("Fields").child(key).child("Name").getValue().toString();
                fieldTxt.setText( "שם המגרש: " + name);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });




        dateTxt.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        FieldsActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateListener, year, month, day);
                Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                date = day + "-" + month + "-" + year;
                dateTxt.setText(date);
                checkEmptyDate();
                setView();
            }
        };

    }

    public void checkEmptyDate(){
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("Management").child(key).hasChild(date)){
                    //it enters here too many times for some reason after the "it does not have"
                    System.out.println("it has");
                }
                else{
                    System.out.println("it does not have");
                    //if its empty we will make the date in the database with empty hours.
                    for (int i = 7; i < 24; i++){
                        if (i < 9){
                            editRef.child("Management").child(key).child(date).child("0" + String.valueOf(i) + ":00-0" + String.valueOf(i+1) + ":00").child("id").setValue("empty");
                        }
                        else if (i == 9){
                            editRef.child("Management").child(key).child(date).child("0" + String.valueOf(i) + ":00-" + String.valueOf(i+1) + ":00").child("id").setValue("empty");
                        }
                        else{
                            editRef.child("Management").child(key).child(date).child(String.valueOf(i) + ":00-" + String.valueOf(i+1) + ":00").child("id").setValue("empty");
                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }


    public void setView(){
        //need to fix the view.


        for (int i = 7; i < 24; i++){
            //showList.add()
        }
        //ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, showList);
        //myList.setAdapter(listAdapter);


    }
}
