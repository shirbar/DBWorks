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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.Objects;

public class ReservationActivity extends AppCompatActivity {

    ListView ActivityList;
    Button BackBtn, RemoveBtn;
    DatabaseReference ActivityRef, managementRef, usersRef;
    FirebaseAuth fAtuth;
    String UserRef, userName;
    ArrayList<String> ShowList, FieldIdList,DateList, HourList, ActivityIdList;
    String date, fieldId, fieldname, hour, type, ActivityId;
    int Position1 = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        ActivityList = (findViewById(R.id.listActivity));
        BackBtn = (findViewById(R.id.goBackBtn2));
        RemoveBtn = (findViewById(R.id.RemoveBtn));


        ShowList = new ArrayList<>();
        FieldIdList = new ArrayList<>();
        DateList = new ArrayList<>();
        HourList = new ArrayList<>();
        ActivityIdList = new ArrayList<>();

        fAtuth = FirebaseAuth.getInstance();
        UserRef = fAtuth.getUid();
        ActivityRef = FirebaseDatabase.getInstance().getReference().child("Users").child(UserRef).child("Activities");
        getUserName();

        setView();

        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        ActivityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
                view.setSelected(true);
                date = DateList.get(position);
                hour = HourList.get(position);
                fieldId = FieldIdList.get(position);
                if (fieldId != null)
                    Position1 = position;

            }
        });

        RemoveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if the user did not select anything
                if (Position1 == -1){
                    Toast.makeText(ReservationActivity.this, "לא בחרת באף פעילות.", Toast.LENGTH_SHORT).show();
                }
                else{
                    DeleteActivity();
                    //clearing the lists
                    clearLists();
                    setView();
                    Position1 = -1;
                }
            }
        });
    }

    private void DeleteActivity() {

        //Delete from user activity in fire base:
        fAtuth = FirebaseAuth.getInstance();
        UserRef = fAtuth.getUid();
        ActivityRef = FirebaseDatabase.getInstance().getReference().child("Users").child(UserRef).child("Activities");
        ActivityRef.child(ActivityIdList.get(Position1)).removeValue();

        //Delete from Management the place this user holds.
        managementRef = FirebaseDatabase.getInstance().getReference().child("Management").child(fieldId).child(date).child(hour);
        managementRef.child("participantList").child(UserRef).removeValue();

        //checking if there is more than 1 players
        managementRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String numOfPlayers = Objects.requireNonNull(dataSnapshot.child("numofPlayers").getValue()).toString();
                String type = Objects.requireNonNull(dataSnapshot.child("type").getValue()).toString();

                //if it's football or basketball field
                if (type.equals("כדורגל") || type.equals("כדורסל"))
                {
                    //if it's full (that person reserved the whole field)
                    if (numOfPlayers.equals("מלא")){
                        managementRef.child("creator").setValue("ריק");
                        managementRef.child("numofPlayers").setValue("0");
                        managementRef.child("status").setValue("פנוי");
                        managementRef.child("type").setValue("ריק");
                    }
                    //if he's the only one that assigned to this field
                    else if (numOfPlayers.equals("1")){
                        managementRef.child("creator").setValue("ריק");
                        managementRef.child("numofPlayers").setValue("0");
                        managementRef.child("status").setValue("פנוי");
                        managementRef.child("type").setValue("ריק");
                    }
                    //if there are more people assigned to this field.
                    else{
                        int newPlayersNumber = Integer.parseInt(numOfPlayers) - 1;
                        managementRef.child("numofPlayers").setValue(String.valueOf(newPlayersNumber));
                        String creator = Objects.requireNonNull(dataSnapshot.child("creator").getValue()).toString();
                        //if its the same person as the creator
                        if (creator.equals(userName)){
                            String newCreator;
                            for (DataSnapshot data : dataSnapshot.child("participantList").getChildren()){
                                newCreator = Objects.requireNonNull(data.child("name").getValue()).toString();
                                managementRef.child("creator").setValue(newCreator);
                                break;
                            }
                        }
                    }
                }
                //if it's a gym field
                else{
                    //if he is the owner of the group field
                    if (type.equals("אימון קבוצתי")){
                        managementRef.child("participantList").removeValue();
                        managementRef.child("creator").setValue("ריק");
                        managementRef.child("numofPlayers").setValue("0");
                        managementRef.child("status").setValue("פנוי");
                        managementRef.child("type").setValue("ריק");
                    }
                    //if he's the only one that assigned to this field
                    else if (numOfPlayers.equals("1")){
                        managementRef.child("creator").setValue("ריק");
                        managementRef.child("numofPlayers").setValue("0");
                        managementRef.child("status").setValue("פנוי");
                        managementRef.child("type").setValue("ריק");
                    }
                    //if there are more people assigned to this field.
                    else{
                        int newPlayersNumber = Integer.parseInt(numOfPlayers) - 1;
                        managementRef.child("numofPlayers").setValue(String.valueOf(newPlayersNumber));
                        String creator = Objects.requireNonNull(dataSnapshot.child("creator").getValue()).toString();
                        //if its the same person as the creator
                        if (creator.equals(userName)){
                            String newCreator;
                            for (DataSnapshot data : dataSnapshot.child("participantList").getChildren()){
                                newCreator = Objects.requireNonNull(data.child("name").getValue()).toString();
                                managementRef.child("creator").setValue(newCreator);
                                break;
                            }
                        }
                    }
                }
                Toast.makeText(ReservationActivity.this, "הפעילות נמחקה בהצלחה.", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void setView(){
        ActivityRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    ActivityId = ds.getKey();
                    type = Objects.requireNonNull(ds.child("Type").getValue()).toString();
                    hour = Objects.requireNonNull(ds.child("Hour").getValue()).toString();
                    fieldname = Objects.requireNonNull(ds.child("Field name").getValue()).toString();
                    fieldId = Objects.requireNonNull(ds.child("Field ID").getValue()).toString();
                    date = Objects.requireNonNull(ds.child("Date").getValue()).toString();
                    FieldIdList.add(fieldId);
                    DateList.add(date);
                    HourList.add(hour);
                    ActivityIdList.add(ActivityId);
                    
                    ShowList.add("| פעילות:           " + type + "\n| שם:                 " + fieldname + "\n| מספר מגרש:  " + fieldId + "\n| תאריך:            " + date + "\n| שעה:               " + hour);
                }

                ListAdapter listAdapter = new ArrayAdapter<>(ReservationActivity.this, R.layout.customize_viewlist, ShowList);
                ActivityList.setAdapter(listAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void clearLists() {
        ShowList.clear();
        FieldIdList.clear();
        DateList.clear();
        HourList.clear();
        ActivityIdList.clear();
    }

    private void getUserName() {
        usersRef = FirebaseDatabase.getInstance().getReference().child("Users").child(UserRef);
        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userName = Objects.requireNonNull(dataSnapshot.child("name").getValue()).toString();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}