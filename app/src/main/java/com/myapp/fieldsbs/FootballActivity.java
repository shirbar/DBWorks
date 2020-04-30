package com.myapp.fieldsbs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.Objects;

public class FootballActivity extends AppCompatActivity {

    EditText userSelect;
    Button btn;
    DatabaseReference fieldRef;
    FirebaseAuth fAuth;
    String id;
    ArrayList<String> nameList, typeList, lightList, neighborhoodList, streetList, showList;
    ListView myList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_football);
        myList = findViewById(R.id.listView);

        userSelect = findViewById(R.id.fieldName);
        btn = findViewById(R.id.btn1);
        fAuth = FirebaseAuth.getInstance();


        nameList = new ArrayList<>();
        typeList = new ArrayList<>();
        lightList = new ArrayList<>();
        neighborhoodList = new ArrayList<>();
        streetList = new ArrayList<>();
        showList = new ArrayList<>();

        id= fAuth.getUid();

        fieldRef = FirebaseDatabase.getInstance().getReference().child("Fields");
        fieldRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i = 0;

                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    if(Objects.requireNonNull(ds.child("Activity").getValue()).toString().equals("פתוח ללא הגבלה")
                    || Objects.requireNonNull(ds.child("Activity").getValue()).toString().equals("פעיל")
                    || Objects.requireNonNull(ds.child("Activity").getValue()).toString().equals("כן")
                    || Objects.requireNonNull(ds.child("Activity").getValue()).toString().equals("")) {

                        if (Objects.requireNonNull(ds.child("Type").getValue()).toString().equals("")
                        || Objects.requireNonNull(ds.child("street").getValue()).toString().equals("")
                        || Objects.requireNonNull(ds.child("neighborho").getValue()).toString().equals("")){
                            i++;
                        }
                        else if (Objects.requireNonNull(ds.child("SportType").getValue()).toString().contains("כדורגל")
                        || Objects.requireNonNull(ds.child("SportType").getValue()).toString().contains("קטרגל")
                        || Objects.requireNonNull(ds.child("SportType").getValue()).toString().contains("קט רגל")
                        || Objects.requireNonNull(ds.child("Type").getValue()).toString().contains("כדורגל")
                        || Objects.requireNonNull(ds.child("Type").getValue()).toString().contains("ספורט משולב")
                        || Objects.requireNonNull(ds.child("Type").getValue()).toString().contains("מגרש משולב")
                        || Objects.requireNonNull(ds.child("Type").getValue()).toString().contains("אצטדיון")
                        || Objects.requireNonNull(ds.child("Type").getValue()).toString().contains("מיני פיץ")
                        || Objects.requireNonNull(ds.child("Type").getValue()).toString().contains("קט רגל")){


                            typeList.add(Objects.requireNonNull(ds.child("Type").getValue()).toString());
                            nameList.add(Objects.requireNonNull(ds.child("Name").getValue()).toString());
                            neighborhoodList.add(Objects.requireNonNull(ds.child("neighborho").getValue()).toString());
                            streetList.add(Objects.requireNonNull(ds.child("street").getValue()).toString());
                            lightList.add(Objects.requireNonNull(ds.child("lighting").getValue()).toString());
                        }
                    }
                }
                //Check
                System.out.println("we've got " + i + " fields.");

                setView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,long arg3) {
                view.setSelected(true);
                userSelect.setText(nameList.get(position));

            }
        });

    }

    void setView(){
        for (int i = 0; i < nameList.size(); i++){
            if (nameList.get(i).equals("")){
                nameList.set(i, typeList.get(i));
                showList.add("| שם:       " + typeList.get(i) + "\n| תיאור:   " + typeList.get(i) + "\n| שכונה:  " + neighborhoodList.get(i) + "\n| רחוב:    " + streetList.get(i) + "\n| תאורה: " + "קיימת תאורה");

            }
            else if (lightList.get(i).equals("כן")){
                showList.add("| שם:       " + nameList.get(i) + "\n| תיאור:   " + typeList.get(i) + "\n| שכונה:  " + neighborhoodList.get(i) + "\n| רחוב:    " + streetList.get(i) + "\n| תאורה: " + "קיימת תאורה");
            }
            else if (lightList.get(i).equals("לא") || lightList.get(i).equals("")){
                showList.add("| שם:       " + nameList.get(i) + "\n| תיאור:   " + typeList.get(i) + "\n| שכונה:  " + neighborhoodList.get(i) + "\n| רחוב:    " + streetList.get(i) + "\n| תאורה: " + "אין תאורה");
            }
            else{
                showList.add("| שם:       " + nameList.get(i) + "\n| תיאור:   " + typeList.get(i) + "\n| שכונה:  " + neighborhoodList.get(i) + "\n| רחוב:    " + streetList.get(i) + "\n| תאורה: " + lightList.get(i));
            }
        }
        ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, showList);
        myList.setAdapter(listAdapter);
    }













}
