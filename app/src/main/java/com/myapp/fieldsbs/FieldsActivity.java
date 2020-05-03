package com.myapp.fieldsbs;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class FieldsActivity extends AppCompatActivity {


    String key, id, type, date, name, userName;
    TextView dateTxt, fieldTxt, userSelect;
    DatePickerDialog.OnDateSetListener mDateListener;
    DatabaseReference managementRef, rootRef;
    Button backBtn, assignBtn;
    ListView myList;
    ArrayList<String> showList, idList, hoursList, statusList, typeList, nameList;




    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fields);

        managementRef = FirebaseDatabase.getInstance().getReference().child("Management");
        rootRef = FirebaseDatabase.getInstance().getReference();
        dateTxt = findViewById(R.id.dateTxt);
        fieldTxt = findViewById(R.id.fieldName);
        myList = findViewById(R.id.listView);
        userSelect = findViewById(R.id.hoursSelect);
        backBtn = findViewById(R.id.backBtn);
        assignBtn = findViewById(R.id.assignBtn);
        showList = new ArrayList<>();
        idList = new ArrayList<>();
        hoursList = new ArrayList<>();
        statusList = new ArrayList<>();
        typeList = new ArrayList<>();
        nameList = new ArrayList<>();

        //getting the data from the previous intent with getStringExtra
        id = getIntent().getStringExtra("id");
        key = getIntent().getStringExtra("key");
        type = getIntent().getStringExtra("type");
        name = getIntent().getStringExtra("name");
        userName = getIntent().getStringExtra("userName");
        fieldTxt.setText( "שם המגרש: " + name);


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
            }
        };


        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,long arg3) {
                view.setSelected(true);
                userSelect.setText(hoursList.get(position));

                //name = nameList.get(position);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FieldsActivity.this, FootballActivity.class));
            }
        });

        assignBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                managementRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String check = dataSnapshot.child(key).child(date).child(userSelect.getText().toString()).child("status").getValue().toString();
                        if (check.equals("תפוס")){
                            Toast.makeText(FieldsActivity.this, "השעה הזאת כבר תפוסה, בחר שעה אחרת.", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            assign();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }
        });
    }

    public void checkEmptyDate(){
        managementRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(key).hasChild(date)){
                    System.out.println("inside has child");

                }
                else{
                    System.out.println("it does not have childs");
                    //if its empty we will make the date in the database with empty hours.
                    for (int i = 7; i < 24; i++){
                        if (i < 9){
                            managementRef.child(key).child(date).child("0" + i + ":00-0" + (i + 1) + ":00").child("id").setValue("ריק");
                            managementRef.child(key).child(date).child("0" + i + ":00-0" + (i + 1) + ":00").child("status").setValue("פנוי");
                            managementRef.child(key).child(date).child("0" + i + ":00-0" + (i + 1) + ":00").child("type").setValue("ריק");
                            managementRef.child(key).child(date).child("0" + i + ":00-0" + (i + 1) + ":00").child("name").setValue("ריק");
                        }
                        else if (i == 9){
                            managementRef.child(key).child(date).child("0" + i + ":00-" + (i + 1) + ":00").child("id").setValue("ריק");
                            managementRef.child(key).child(date).child("0" + i + ":00-" + (i + 1) + ":00").child("status").setValue("פנוי");
                            managementRef.child(key).child(date).child("0" + i + ":00-" + (i + 1) + ":00").child("type").setValue("ריק");
                            managementRef.child(key).child(date).child("0" + i + ":00-" + (i + 1) + ":00").child("name").setValue("ריק");
                        }
                        else{
                            managementRef.child(key).child(date).child(i + ":00-" + (i + 1) + ":00").child("id").setValue("ריק");
                            managementRef.child(key).child(date).child(i + ":00-" + (i + 1) + ":00").child("status").setValue("פנוי");
                            managementRef.child(key).child(date).child(i + ":00-" + (i + 1) + ":00").child("type").setValue("ריק");
                            managementRef.child(key).child(date).child(i + ":00-" + (i + 1) + ":00").child("name").setValue("ריק");
                        }
                    }
                }
                setView();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }


    public void setView(){
        System.out.println("entering set view");

        managementRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                clearLists();
                System.out.println("inside set view");
                for(DataSnapshot ds : dataSnapshot.child(key).child(date).getChildren()) {
                    hoursList.add(Objects.requireNonNull(ds.getKey()));
                    statusList.add(Objects.requireNonNull(ds.child("status").getValue()).toString());
                    nameList.add(Objects.requireNonNull(ds.child("name").getValue()).toString());
                    typeList.add(Objects.requireNonNull(ds.child("type").getValue()).toString());
                    idList.add(Objects.requireNonNull(ds.child("id").getValue()).toString());
                }
                for (int i = 0; i < statusList.size(); i++){
                    if (statusList.get(i).equals("פנוי")){
                        System.out.println("available");
                        showList.add("|שעות:  " + hoursList.get(i) + "\n|זמינות: " + statusList.get(i));
                    }
                    else{
                        System.out.println("not available");
                        showList.add("|שעות:            " + hoursList.get(i) + "\n|זמינות:          " + statusList.get(i) + "\n|סוג:               " + typeList.get(i) + "\n|שם הנרשם: " + nameList.get(i));
                        System.out.println(showList.get(i));
                    }
                }
                showView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void showView(){
        ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, showList);
        myList.setAdapter(listAdapter);
    }

    public void assign(){
        managementRef.child(key).child(date).child(userSelect.getText().toString()).child("id").setValue(id);
        managementRef.child(key).child(date).child(userSelect.getText().toString()).child("name").setValue(userName);
        managementRef.child(key).child(date).child(userSelect.getText().toString()).child("status").setValue("תפוס");
        managementRef.child(key).child(date).child(userSelect.getText().toString()).child("type").setValue(type);
        setView();
        Toast.makeText(FieldsActivity.this, "נרשמת בההצלחה.", Toast.LENGTH_SHORT).show();
    }

    public void clearLists(){
        showList.clear();
        idList.clear();
        hoursList.clear();
        statusList.clear();
        typeList.clear();
        nameList.clear();
    }
}
