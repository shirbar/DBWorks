package com.myapp.fieldsbs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.NameList;

import java.util.ArrayList;
import java.util.Objects;

public class FootballActivity extends AppCompatActivity {

    TextView a,b,c;
    Button btn;
    DatabaseReference fieldRef;
    FirebaseAuth fAuth;
    String id;
    int flag = 0;
    ArrayList<String> nameList, typeList, lightList, neighborhoodList, streetList, showList;
    ListView myList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_football);
        myList = findViewById(R.id.listView);


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
                    if(Objects.requireNonNull(ds.child("Activity").getValue().toString().equals("פתוח ללא הגבלה"))
                    || Objects.requireNonNull(ds.child("Activity").getValue().toString().equals("פעיל"))
                    || Objects.requireNonNull(ds.child("Activity").getValue().toString().equals("כן"))
                    || Objects.requireNonNull(ds.child("Activity").getValue().toString().equals(""))
                    ){
                        if (Objects.requireNonNull(ds.child("Type")).equals(" ")
                        || Objects.requireNonNull(ds.child("street").equals(" "))
                        || Objects.requireNonNull(ds.child("neighborho")).equals(" ")){
                            i++;
                        }
                        else if (Objects.requireNonNull(ds.child("SportType").getValue().toString().contains("כדורגל"))
                        || Objects.requireNonNull(ds.child("SportType").getValue().toString().contains("קטרגל"))
                        || Objects.requireNonNull(ds.child("SportType").getValue().toString().contains("קט רגל"))
                        || Objects.requireNonNull(ds.child("Type").getValue().toString().contains("כדורגל"))
                        || Objects.requireNonNull(ds.child("Type").getValue().toString().contains("ספורט משולב"))
                        || Objects.requireNonNull(ds.child("Type").getValue().toString().contains("מגרש משולב"))
                        || Objects.requireNonNull(ds.child("Type").getValue().toString().contains("אצטדיון"))
                        || Objects.requireNonNull(ds.child("Type").getValue().toString().contains("מיני פיץ"))
                        || Objects.requireNonNull(ds.child("Type").getValue().toString().contains("קט רגל"))
                        ){



                            nameList.add(ds.child("Name").getValue().toString());
                            typeList.add(ds.child("Type").getValue().toString());
                            lightList.add(ds.child("lighting").getValue().toString());
                            neighborhoodList.add(ds.child("neighborho").getValue().toString());
                            streetList.add(ds.child("street").getValue().toString());

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





    }

    void setView(){
        for (int i = 0; i < nameList.size(); i++){
            if (lightList.get(i).equals("כן")){
                showList.add(typeList.get(i) + "\n| שם: " + nameList.get(i) + "\n| שכונה: " + neighborhoodList.get(i) + "\n| רחוב: " + streetList.get(i) + "\n| " + "קיימת תאורה");
            }
            else if (lightList.get(i).equals("לא") || lightList.get(i).equals("")){
                showList.add(typeList.get(i) + "\n| שם: " + nameList.get(i) + "\n| שכונה: " + neighborhoodList.get(i) + "\n| רחוב: " + streetList.get(i) + "\n| " + "אין תאורה");
            }
            else{
                showList.add(typeList.get(i) + "\n| שם: " + nameList.get(i) + "\n| שכונה: " + neighborhoodList.get(i) + "\n| רחוב: " + streetList.get(i) + "\n| " + lightList.get(i));
            }
        }
        ListAdapter listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, showList);
        myList.setAdapter(listAdapter);
    }













}
