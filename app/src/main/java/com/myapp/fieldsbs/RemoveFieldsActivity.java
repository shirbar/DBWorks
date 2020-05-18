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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class RemoveFieldsActivity extends AppCompatActivity {

    TextView userSelect;
    Button removeBtn, backBtn, footballBtn, basketballBtn, gymBtn;
    DatabaseReference fieldRef, rootRef;
    FirebaseAuth fAuth;
    ListView myList;//, chooseFieldViewlist;
    ArrayList<String> fieldsList, filteredFieldsList, keyList, filteredKeysList, typeList;
    String name, key, id, selectedType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_fields);

        myList = findViewById(R.id.Hen_listView);
        userSelect = findViewById(R.id.textView3);

        System.out.println("check - 1 !!!!!!");

        keyList = new ArrayList<>();
        typeList = new ArrayList<>();
        fieldsList = new ArrayList<>();
        filteredFieldsList = new ArrayList<>();
        filteredKeysList = new ArrayList<>();
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
        fieldRef = FirebaseDatabase.getInstance().getReference().child("Fields");

        //mySpinner = (Spinner) findViewById(R.id.spinner2);

        //id = fAuth.getUid();

        getFields();


        footballBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                selectedType = "כדורגל";
                userSelect.setText("");
                filterFields("כדורגל");
            }
        });

        basketballBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                selectedType = "כדורסל";
                userSelect.setText("");
                filterFields("כדורסל");
            }
        });

        gymBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                selectedType = "כושר";
                userSelect.setText("");
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
                name = filteredFieldsList.get(position);
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
                            setEmptyView();
                            getFields();
                            userSelect.setText("");
                            Toast.makeText(RemoveFieldsActivity.this, "המגרש הפך ללא פעיל.", Toast.LENGTH_SHORT).show();

                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
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


    public void getFields(){
        fieldRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                keyList.clear();
                typeList.clear();
                fieldsList.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (!Objects.requireNonNull(ds.child("Activity").getValue()).toString().equals("לא פעיל")) {
                        keyList.add(Objects.requireNonNull(ds.getKey()));
                        typeList.add(Objects.requireNonNull(ds.child("Type").getValue()).toString());
                        fieldsList.add(Objects.requireNonNull(ds.child("Name").getValue()).toString());
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void backClick (View view){
        startActivity(new Intent(getApplicationContext(), AdminMainActivity.class));
    }

    public void filterFields(String type)
    {
        clearList();
        for (int currFieldId = 0; currFieldId < fieldsList.size(); currFieldId++){
            // Check if this field the same type as requested

            if(typeList.get(currFieldId).contains(type) || ((type.equals("כדורסל") || type.equals("כדורגל"))&&typeList.get(currFieldId).contains("משולב"))) {
                filteredFieldsList.add(fieldsList.get(currFieldId));
                filteredKeysList.add(keyList.get(currFieldId));
            }
        }
        setView();
    }


    void setView(){
        ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, filteredFieldsList);
        myList.setAdapter(listAdapter);
    }

    void setEmptyView(){
        ArrayList<String> emptyList = new ArrayList<>();
        ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, emptyList);
        myList.setAdapter(listAdapter);
    }

    public void clearList(){
        filteredFieldsList.clear();
        filteredKeysList.clear();
    }

}
