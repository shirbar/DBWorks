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
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.Objects;

public class FootballActivity extends AppCompatActivity {

    TextView userSelect;
    Button nextBtn, backBtn;
    DatabaseReference fieldRef, usersRef;
    FirebaseAuth fAuth;
    String id, key, name, userName, command, neighborhood;
    ArrayList<String> keyList, nameList, typeList, lightList, neighborhoodList, streetList, showList;
    ListView myList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_football);
        myList = findViewById(R.id.listView);

        userSelect = findViewById(R.id.fieldName);
        nextBtn = findViewById(R.id.nextBtn);
        backBtn = findViewById(R.id.goBackBtn);
        fAuth = FirebaseAuth.getInstance();

        keyList = new ArrayList<>();
        nameList = new ArrayList<>();
        typeList = new ArrayList<>();
        lightList = new ArrayList<>();
        neighborhoodList = new ArrayList<>();
        streetList = new ArrayList<>();
        showList = new ArrayList<>();

        id= fAuth.getUid();
        usersRef = FirebaseDatabase.getInstance().getReference().child("Users");

        //get the type of the command (normal, or by address)
        command = getIntent().getStringExtra("command");
        neighborhood = getIntent().getStringExtra("neighborhood");

        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userName = Objects.requireNonNull(dataSnapshot.child(id).child("name").getValue()).toString();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        fieldRef = FirebaseDatabase.getInstance().getReference().child("Fields");
        fieldRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    if(Objects.requireNonNull(ds.child("Activity").getValue()).toString().equals("פתוח ללא הגבלה")
                    || Objects.requireNonNull(ds.child("Activity").getValue()).toString().equals("פעיל")
                    || Objects.requireNonNull(ds.child("Activity").getValue()).toString().equals("כן")
                    || Objects.requireNonNull(ds.child("Activity").getValue()).toString().equals("")) {
                        if (!Objects.requireNonNull(ds.child("Type").getValue()).toString().equals("")
                                && !Objects.requireNonNull(ds.child("street").getValue()).toString().equals("")
                                && !Objects.requireNonNull(ds.child("neighborho").getValue()).toString().equals("")) {
                            if (Objects.requireNonNull(ds.child("SportType").getValue()).toString().contains("כדורגל")
                                    || Objects.requireNonNull(ds.child("SportType").getValue()).toString().contains("קטרגל")
                                    || Objects.requireNonNull(ds.child("SportType").getValue()).toString().contains("קט רגל")
                                    || Objects.requireNonNull(ds.child("Type").getValue()).toString().contains("כדורגל")
                                    || Objects.requireNonNull(ds.child("Type").getValue()).toString().contains("ספורט משולב")
                                    || Objects.requireNonNull(ds.child("Type").getValue()).toString().contains("מגרש משולב")
                                    || Objects.requireNonNull(ds.child("Type").getValue()).toString().contains("אצטדיון")
                                    || Objects.requireNonNull(ds.child("Type").getValue()).toString().contains("מיני פיץ")
                                    || Objects.requireNonNull(ds.child("Type").getValue()).toString().contains("קט רגל")){
                                if (command.equals("address")){
                                    AddressHelper helper = new AddressHelper();
                                    helper.setValues();
                                    ArrayList<String> myNeighborhoods = helper.dictionary.get(neighborhood);
                                    for (int i = 0; i < myNeighborhoods.size(); i++){
                                        if (Objects.requireNonNull(ds.child("neighborho").getValue()).toString().equals(myNeighborhoods.get(i))) {
                                            keyList.add(Objects.requireNonNull(ds.getKey()));
                                            typeList.add(Objects.requireNonNull(ds.child("Type").getValue()).toString());
                                            nameList.add(Objects.requireNonNull(ds.child("Name").getValue()).toString());
                                            neighborhoodList.add(Objects.requireNonNull(ds.child("neighborho").getValue()).toString());
                                            streetList.add(Objects.requireNonNull(ds.child("street").getValue()).toString());
                                            lightList.add(Objects.requireNonNull(ds.child("lighting").getValue()).toString());
                                        }
                                    }
                                }
                                else{
                                    keyList.add(Objects.requireNonNull(ds.getKey()));
                                    typeList.add(Objects.requireNonNull(ds.child("Type").getValue()).toString());
                                    nameList.add(Objects.requireNonNull(ds.child("Name").getValue()).toString());
                                    neighborhoodList.add(Objects.requireNonNull(ds.child("neighborho").getValue()).toString());
                                    streetList.add(Objects.requireNonNull(ds.child("street").getValue()).toString());
                                    lightList.add(Objects.requireNonNull(ds.child("lighting").getValue()).toString());
                                }
                            }
                        }
                    }
                }
                setView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,long arg3) {
                userSelect.setTextColor(getColor(R.color.userSelect_color));
                userSelect.setText(nameList.get(position));
                key = keyList.get(position);
                name = nameList.get(position);

            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userSelect.getText().toString().equals("בחר מגרש")){
                    Toast.makeText(FootballActivity.this, "בבקשה תבחר מגרש מתוך הרשימה.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(getBaseContext(), FieldsActivity.class);
                    intent.putExtra("key", key);
                    intent.putExtra("type", "כדורגל");
                    intent.putExtra("id", id);
                    intent.putExtra("fieldName", name);
                    intent.putExtra("userName", userName);
                    intent.putExtra("command", command);
                    intent.putExtra("neighborhood", neighborhood);
                    startActivity(intent);
                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FootballActivity.this, AviliableActivity.class);
                intent.putExtra("command", command);
                if (command.equals("address"))
                    intent.putExtra("neighborhood", neighborhood);
                else
                    intent.putExtra("neighborhood", "none");
                startActivity(intent);
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
        ListAdapter listAdapter = new ArrayAdapter<>(this, R.layout.customize_viewlist, showList);
        myList.setAdapter(listAdapter);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(FootballActivity.this, AviliableActivity.class);
        intent.putExtra("command", command);
        if (command.equals("address"))
            intent.putExtra("neighborhood", neighborhood);
        else
            intent.putExtra("neighborhood", "none");
        startActivity(intent);
    }
}
