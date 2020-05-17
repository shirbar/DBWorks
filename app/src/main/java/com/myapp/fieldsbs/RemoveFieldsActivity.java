package com.myapp.fieldsbs;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

public class RemoveFieldsActivity extends AppCompatActivity {

    TextView userSelect;
    Button removeBtn, backBtn, footballBtn, basketballBtn, gymBtn;
    DatabaseReference fieldRef, rootRef;
    FirebaseAuth fAuth;
    ListView myList;//, chooseFieldViewlist;
    Spinner mySpinner;
    ArrayList<String> fieldsList, filteredFieldsList, keyList, filteredKeysList, nameList, typeList, showList;
    String name, key, id;

   // ArrayAdapter<String> showListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_fields);

        myList = (ListView) findViewById(R.id.Hen_listView);
        userSelect = findViewById(R.id.textView3);

        System.out.println("check - 1 !!!!!!");

        keyList = new ArrayList<>();
        nameList = new ArrayList<>();
        typeList = new ArrayList<>();
        fieldsList = new ArrayList<>();
        filteredFieldsList = new ArrayList<>();
        filteredKeysList = new ArrayList<>();
        showList = new ArrayList<>();

        //showListAdapter = new ArrayAdapter<String>(this, android.R.layout.activity_list_item);





        System.out.println("check - 2 !!!!!!");
/**/
        rootRef =FirebaseDatabase.getInstance().getReference();
        fAuth = FirebaseAuth.getInstance();
        key = "0";

        backBtn = findViewById(R.id.goBackBtn);
        footballBtn = findViewById(R.id.btnFootball);
        basketballBtn = findViewById(R.id.btnBasketball);
        gymBtn = findViewById(R.id.btnGym);
        removeBtn = findViewById(R.id.button13);

        //mySpinner = (Spinner) findViewById(R.id.spinner2);

        //id = fAuth.getUid();

        fieldRef = FirebaseDatabase.getInstance().getReference().child("Fields");
        fieldRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    keyList.add(Objects.requireNonNull(ds.getKey()));
                    typeList.add(Objects.requireNonNull(ds.child("Type").getValue()).toString());
                    fieldsList.add(Objects.requireNonNull(ds.child("Name").getValue()).toString());


                }
                System.out.println("check - 3!!!!!!");

                // Field filtering
                filteredFieldsList = fieldsList;
                filteredKeysList = keyList;

                //ArrayAdapter<String> listViewAdapter = new ArrayAdapter<>(RemoveFieldsActivity.this, android.R.layout.simple_list_item_1, filteredFieldsList);
                //myList.setAdapter(showListAdapter);

                System.out.println("check - 4 !!!!!!");
                /*
                // Spinner initialize (fields select)
                filteredFieldsList = fieldsList;
                filteredKeysList = keyList;
                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(RemoveFieldsActivity.this, android.R.layout.simple_list_item_activated_1, filteredFieldsList);
                //selected item will look like a spinner set from XML
                spinnerArrayAdapter.setListViewResource(android.R.layout.simple_list_item_activated_1);
                chooseFieldViewlist.setAdapter(spinnerArrayAdapter);
                //setView();
                spinnerArrayAdapter.setDropDownViewResource();
   */
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });


        footballBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                // TODO: filter to Football
                filterFields("כדורגל");
            }
        });

        basketballBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                // TODO: filter to Basketball
                filterFields("כדורסל");
            }
        });

        gymBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                // TODO: filter to GYM
                filterFields("כושר");
            }
        });






        System.out.println("check - 5 !!!!!!");
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,long arg3) {
                key = filteredKeysList.get(position);
                userSelect.setTextColor(getColor(R.color.userSelect_color));
                name = fieldsList.get(position);
                userSelect.setText(name);//fieldsList.get(position));
                view.setSelected(true);
            }
        });

        System.out.println("check - 6  !!!!!!");

        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userSelect.getText().toString().equals("בחר את המגרש")){
                    Toast.makeText(RemoveFieldsActivity.this, "בבקשה תבחר מגרש מתוך הרשימה.", Toast.LENGTH_SHORT).show();
                    System.out.println("check - 6.5 !!!!!!");
                }
                else{
                    fieldRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            fieldRef.child(key).child("Activity").setValue("לא פעיל");
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
                    System.out.println("check - 6.85  !!!!!!");
                }
            }
        });

        System.out.println("check - 7 !!!!!!");
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AdminMainActivity.class);
                startActivity(intent);
            }
        });


    }



    public void backClick (View view){
        startActivity(new Intent(getApplicationContext(), AdminMainActivity.class));
    }



    void setView(){
        ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, filteredFieldsList);
        myList.setAdapter(listAdapter);
    }
    public void filterFields(String type)
    {
        // TODO: FILTER
        filteredFieldsList = new ArrayList<>();
        filteredKeysList = new ArrayList<>();
        for (int currFieldId = 0; currFieldId < fieldsList.size(); currFieldId++){
            // Check if this field the same type as requested
            if(currFieldId < typeList.size())
            {
                if(typeList.get(currFieldId).contains(type) || ((type=="כדורסל" || type=="כדורגל")&&typeList.get(currFieldId).contains("משולב"))) {
                    filteredFieldsList.add(fieldsList.get(currFieldId));
                    filteredKeysList.add(keyList.get(currFieldId));
                }
            }
        }
        setView();
    }


}
