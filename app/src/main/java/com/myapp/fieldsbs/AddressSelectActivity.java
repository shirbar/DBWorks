package com.myapp.fieldsbs;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public class AddressSelectActivity extends AppCompatActivity {


    DatabaseReference addressRef;
    ArrayList<String> neighborhoodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_select);

        Button nextBtn = findViewById(R.id.nextBtn);
        Button backBtn = findViewById(R.id.backBtn2);
        final Spinner addressSpinner = findViewById(R.id.addressSpinner);
        neighborhoodList = new ArrayList<>();

        addressRef = FirebaseDatabase.getInstance().getReference().child("Streets");
        addressRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (!ds.child("neighborhood").getValue().toString().equals("") && !neighborhoodList.contains(ds.child("neighborhood").getValue().toString())) {
                        neighborhoodList.add(ds.child("neighborhood").getValue().toString());
                    }
                }

                //still need to edit the above to get read of unappropriated places to live in.


                Collections.sort(neighborhoodList, String.CASE_INSENSITIVE_ORDER);
                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(AddressSelectActivity.this, R.layout.customize_viewlist, neighborhoodList);
                spinnerArrayAdapter.setDropDownViewResource(R.layout.customize_viewlist);
                addressSpinner.setAdapter(spinnerArrayAdapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String neighborhood = neighborhoodList.get(addressSpinner.getSelectedItemPosition());
                System.out.println(neighborhood);
                Intent i = new Intent(AddressSelectActivity.this, AviliableActivity.class);
                i.putExtra("command", "address");
                i.putExtra("neighborhood", neighborhood);
                startActivity(i);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddressSelectActivity.this, MainActivity.class));
            }
        });

    }



}
