package com.myapp.fieldsbs;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
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
import java.util.Calendar;
import java.util.HashMap;
import java.util.Objects;

public class GymAreasActivity extends AppCompatActivity {

    boolean today = false, alreadySigned = false;
    String key, id, type, date, fieldName, userName, UserId;
    TextView dateTxt, fieldTxt, userSelect;
    DatePickerDialog.OnDateSetListener mDateListener;
    DatabaseReference managementRef, usersRef;
    Button backBtn, startGroupTrainingBtn, assignPlayerBtn;
    ListView myList;
    ArrayList<String> showList, hoursList, statusList, typeList, numOfPlayersList, creatorList, usersList;
    FirebaseAuth fAuth;
    //ArrayList<ArrayList<String>> namesList;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym_areas);
        managementRef = FirebaseDatabase.getInstance().getReference().child("Management");
        usersRef = FirebaseDatabase.getInstance().getReference().child("Users");
        dateTxt = findViewById(R.id.dateTxt);
        fieldTxt = findViewById(R.id.fieldName);
        myList = findViewById(R.id.listView);
        userSelect = findViewById(R.id.hoursSelect);
        backBtn = findViewById(R.id.backBtn);
        startGroupTrainingBtn = findViewById(R.id.fullAssignBtn);
        assignPlayerBtn = findViewById(R.id.assignMyselfBtn);

        fAuth = FirebaseAuth.getInstance();
        UserId = fAuth.getUid();

        showList = new ArrayList<>();
        hoursList = new ArrayList<>();
        statusList = new ArrayList<>();
        typeList = new ArrayList<>();
        creatorList = new ArrayList<>();
        numOfPlayersList = new ArrayList<>();
        usersList = new ArrayList<>();

        //getting the data from the previous intent with getStringExtra
        id = getIntent().getStringExtra("id");
        key = getIntent().getStringExtra("key");
        type = getIntent().getStringExtra("type");
        fieldName = getIntent().getStringExtra("fieldName");
        userName = getIntent().getStringExtra("userName");
        fieldTxt.setText( "שם האזור: " + fieldName);

        dateTxt.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int thisYear = cal.get(Calendar.YEAR);
                int thisMonth = cal.get(Calendar.MONTH);
                int thisDay = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        GymAreasActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateListener, thisYear, thisMonth, thisDay);
                Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                Calendar cal = Calendar.getInstance();
                if (year < cal.get(Calendar.YEAR) || (year == cal.get(Calendar.YEAR) && month < cal.get(Calendar.MONTH)) || (year == cal.get(Calendar.YEAR) && month == cal.get(Calendar.MONTH)) && day < cal.get(Calendar.DAY_OF_MONTH)){
                    Toast.makeText(GymAreasActivity.this, "התאריך המבוקש כבר עבר.", Toast.LENGTH_SHORT).show();
                }
                else{
                    today = year == cal.get(Calendar.YEAR) && month == cal.get(Calendar.MONTH) && day == cal.get(Calendar.DAY_OF_MONTH);
                    month = month + 1;
                    date = day + "-" + month + "-" + year;
                    dateTxt.setText(date);
                    checkEmptyDate();
                }
            }
        };


        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,long arg3) {
                view.setSelected(true);
                Calendar cal = Calendar.getInstance();
                String hour_selected = hoursList.get(position).substring(0, 2);
                if ((today) && (Integer.parseInt(hour_selected) < cal.get(Calendar.HOUR_OF_DAY)+3)){
                    Toast.makeText(GymAreasActivity.this, "השעה המבוקשת כבר עברה.", Toast.LENGTH_SHORT).show();
                }
                else{
                    userSelect.setTextColor(getColor(R.color.userSelect_color));
                    userSelect.setText(hoursList.get(position));
                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GymAreasActivity.this, GymActivity.class));
            }
        });

        startGroupTrainingBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (userSelect.getText().toString().equals("בחר שעות")){
                    Toast.makeText(GymAreasActivity.this, "בבקשה תבחר תאריך ואז שעה פנויה מתוך הרשימה.", Toast.LENGTH_SHORT).show();
                }
                else {
                    managementRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String check = Objects.requireNonNull(dataSnapshot.child(key).child(date).child(userSelect.getText().toString()).child("status").getValue()).toString();
                            if (check.equals("מתקיים")) {
                                Toast.makeText(GymAreasActivity.this, "השעה הזאת כבר תפוסה, בחר שעה אחרת.", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                fullAssign();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
                }
            }
        });

        assignPlayerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userSelect.getText().toString().equals("בחר שעות")){
                    Toast.makeText(GymAreasActivity.this, "בבקשה תבחר תאריך ואז שעה פנויה מתוך הרשימה.", Toast.LENGTH_SHORT).show();
                }
                else{
                    managementRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String checkStatus = Objects.requireNonNull(dataSnapshot.child(key).child(date).child(userSelect.getText().toString()).child("status").getValue()).toString();
                            if (checkStatus.equals("פנוי")){
                                assignPlayer();
                            }
                            else {
                                    for (DataSnapshot ds : dataSnapshot.child(key).child(date).child(userSelect.getText().toString()).child("participantList").getChildren()) {
                                        if (Objects.equals(ds.getKey(), id)){
                                            alreadySigned = true;
                                        }
                                    }
                                    if (!alreadySigned){
                                        if (Objects.requireNonNull(dataSnapshot.child(key).child(date).child(userSelect.getText().toString()).child("numofPlayers").getValue()).toString().equals("10")){
                                            Toast.makeText(GymAreasActivity.this, "אינך יכול להצטרף מכיוון שמספר המשתתפים מקסימלי.", Toast.LENGTH_SHORT).show();
                                        }
                                        else{
                                            addPlayer();
                                        }
                                    }
                                    else{
                                        Toast.makeText(GymAreasActivity.this, "כבר נרשמת לאימון בשעה הזאת.", Toast.LENGTH_SHORT).show();
                                        alreadySigned = false;
                                    }
                                }
                            }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
                }
            }
        });

    }

    public void checkEmptyDate(){
        managementRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.child(key).hasChild(date)) {
                    //if its empty we will make the date in the database with empty hours.
                    for (int i = 7; i < 24; i++){
                        if (i < 9){
                            managementRef.child(key).child(date).child("0" + i + ":00-0" + (i + 1) + ":00").child("status").setValue("פנוי");
                            managementRef.child(key).child(date).child("0" + i + ":00-0" + (i + 1) + ":00").child("numofPlayers").setValue("0");
                            managementRef.child(key).child(date).child("0" + i + ":00-0" + (i + 1) + ":00").child("type").setValue("ריק");
                            managementRef.child(key).child(date).child("0" + i + ":00-0" + (i + 1) + ":00").child("creator").setValue("ריק");
                            //managementRef.child(key).child(date).child("0" + i + ":00-0" + (i + 1) + ":00").child("namesList").child("0").setValue("ריק");
                        }
                        else if (i == 9){
                            managementRef.child(key).child(date).child("0" + i + ":00-" + (i + 1) + ":00").child("status").setValue("פנוי");
                            managementRef.child(key).child(date).child("0" + i + ":00-" + (i + 1) + ":00").child("numofPlayers").setValue("0");
                            managementRef.child(key).child(date).child("0" + i + ":00-" + (i + 1) + ":00").child("type").setValue("ריק");
                            managementRef.child(key).child(date).child("0" + i + ":00-" + (i + 1) + ":00").child("creator").setValue("ריק");
                            //managementRef.child(key).child(date).child("0" + i + ":00-" + (i + 1) + ":00").child("namesList").child("0").setValue("ריק");
                        }
                        else{
                            managementRef.child(key).child(date).child(i + ":00-" + (i + 1) + ":00").child("status").setValue("פנוי");
                            managementRef.child(key).child(date).child(i + ":00-" + (i + 1) + ":00").child("numofPlayers").setValue("0");
                            managementRef.child(key).child(date).child(i + ":00-" + (i + 1) + ":00").child("type").setValue("ריק");
                            managementRef.child(key).child(date).child(i + ":00-" + (i + 1) + ":00").child("creator").setValue("ריק");
                            //managementRef.child(key).child(date).child(i + ":00-" + (i + 1) + ":00").child("namesList").child("0").setValue("ריק");
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
        managementRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                clearLists();
                for(DataSnapshot ds : dataSnapshot.child(key).child(date).getChildren()) {
                    hoursList.add(Objects.requireNonNull(ds.getKey()));
                    statusList.add(Objects.requireNonNull(ds.child("status").getValue()).toString());
                    numOfPlayersList.add(Objects.requireNonNull(ds.child("numofPlayers").getValue()).toString());
                    typeList.add(Objects.requireNonNull(ds.child("type").getValue()).toString());
                    creatorList.add(Objects.requireNonNull(ds.child("creator").getValue()).toString());
                    /*
                    int numberofPlayers = Integer.valueOf(Objects.requireNonNull(ds.child("numofPlayers").getValue()).toString());
                    for (int i = 0; i < numberofPlayers; i++)
                        namesList[j].add(Objects.requireNonNull(ds.child("namesList").child(String.valueOf(i)).getValue()).toString());
                     */
                }
                for (int i = 0; i < statusList.size(); i++){
                    if (statusList.get(i).equals("פנוי")){
                        showList.add("|שעות:  " + hoursList.get(i) + "\n|זמינות: " + statusList.get(i));
                    }
                    else{
                        showList.add("|שעות:                     " + hoursList.get(i) + "\n|זמינות:                   " + statusList.get(i) + "\n|מספר משתתפים: " + numOfPlayersList.get(i) + "/10" + "\n|סוג:                        " + typeList.get(i) + "\n|שם האחראי:         " + creatorList.get(i));
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
        ListAdapter listAdapter = new ArrayAdapter<>(this, R.layout.customize_viewlist, showList);
        myList.setAdapter(listAdapter);
    }

    public void assignPlayer(){
        managementRef.child(key).child(date).child(userSelect.getText().toString()).child("creator").setValue(userName);
        managementRef.child(key).child(date).child(userSelect.getText().toString()).child("status").setValue("מתקיים");
        managementRef.child(key).child(date).child(userSelect.getText().toString()).child("numofPlayers").setValue("1");
        managementRef.child(key).child(date).child(userSelect.getText().toString()).child("type").setValue(type);
        managementRef.child(key).child(date).child(userSelect.getText().toString()).child("participantList").child(id).child("name").setValue(userName);

        Assign_To_My_Activities();
    }

    public void addPlayer(){
        managementRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int playersNum = Integer.parseInt(Objects.requireNonNull(dataSnapshot.child(key).child(date).child(userSelect.getText().toString()).child("numofPlayers").getValue()).toString());
                playersNum ++;
                managementRef.child(key).child(date).child(userSelect.getText().toString()).child("numofPlayers").setValue(String.valueOf(playersNum));
                managementRef.child(key).child(date).child(userSelect.getText().toString()).child("participantList").child(id).child("name").setValue(userName);

                Assign_To_My_Activities();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void fullAssign(){
        managementRef.child(key).child(date).child(userSelect.getText().toString()).child("creator").setValue(userName);
        managementRef.child(key).child(date).child(userSelect.getText().toString()).child("status").setValue("מתקיים");
        managementRef.child(key).child(date).child(userSelect.getText().toString()).child("numofPlayers").setValue("1");
        managementRef.child(key).child(date).child(userSelect.getText().toString()).child("type").setValue("אימון קבוצתי");
        managementRef.child(key).child(date).child(userSelect.getText().toString()).child("participantList").child(id).child("name").setValue(userName);

        Assign_To_My_Activities();

    }

    public void clearLists(){
        showList.clear();
        hoursList.clear();
        statusList.clear();
        typeList.clear();
        creatorList.clear();
        numOfPlayersList.clear();
    }

    private void Assign_To_My_Activities() {
        HashMap<String, Object> Activity = new HashMap<>();

        Activity.put("Field name",fieldName);
        Activity.put("Field ID",key);
        Activity.put("Type",type);
        Activity.put("Date", date);
        Activity.put("Hour",userSelect.getText().toString());

        usersRef.child(UserId).child("Activities").push().setValue(Activity).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(GymAreasActivity.this, "הפעילות נוספה בהצלחה", Toast.LENGTH_SHORT).show();
                    setView();
                } else {
                    Toast.makeText(GymAreasActivity.this, "הפעילות לא התווספה בהצלחה- אנא נסה שנית", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}