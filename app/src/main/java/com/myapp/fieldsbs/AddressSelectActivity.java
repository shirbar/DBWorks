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
    ArrayList<String> neighborhoodList, badPlacesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_select);

        Button nextBtn = findViewById(R.id.nextBtn);
        Button backBtn = findViewById(R.id.backBtn2);
        final Spinner addressSpinner = findViewById(R.id.addressSpinner);
        neighborhoodList = new ArrayList<>();
        badPlacesList = new ArrayList<>();
        badPlacesList.add("א. תעשיה");
        badPlacesList.add("יער רמות");
        badPlacesList.add("נווה מנחם - עמק שרה");
        badPlacesList.add("מ. אזרחי");
        badPlacesList.add("ב - קריית גנים");
        badPlacesList.add("רמות - קריית הייטק");
        badPlacesList.add("עמק שרה");
        badPlacesList.add("כלניות");

        addressRef = FirebaseDatabase.getInstance().getReference().child("Streets");
        addressRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (!Objects.requireNonNull(ds.child("neighborhood").getValue()).toString().equals("") && !badPlacesList.contains(Objects.requireNonNull(ds.child("neighborhood").getValue()).toString()) && !neighborhoodList.contains(Objects.requireNonNull(ds.child("neighborhood").getValue()).toString())) {
                        neighborhoodList.add(Objects.requireNonNull(ds.child("neighborhood").getValue()).toString());
                    }
                }
                neighborhoodList.add("חצרים");
                neighborhoodList.add("נאות אילן");
                neighborhoodList.add("נאות לון");
                neighborhoodList.add("נחל בקע");

                Collections.sort(neighborhoodList, String.CASE_INSENSITIVE_ORDER);
                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(AddressSelectActivity.this, R.layout.customize_viewlist, neighborhoodList);
                spinnerArrayAdapter.setDropDownViewResource(R.layout.customize_viewlist);
                addressSpinner.setAdapter(spinnerArrayAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        //to see all of the neighborhoods of the fields.
/*
        addressRef = FirebaseDatabase.getInstance().getReference().child("Fields");
        addressRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (!ds.child("neighborho").getValue().toString().equals("") && !neighborhoodList.contains(ds.child("neighborho").getValue().toString())) {
                        neighborhoodList.add(ds.child("neighborho").getValue().toString());
                    }
                }

                Collections.sort(neighborhoodList, String.CASE_INSENSITIVE_ORDER);
                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(AddressSelectActivity.this, R.layout.customize_viewlist, neighborhoodList);
                spinnerArrayAdapter.setDropDownViewResource(R.layout.customize_viewlist);
                addressSpinner.setAdapter(spinnerArrayAdapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
 */

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String neighborhood = neighborhoodList.get(addressSpinner.getSelectedItemPosition());
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

    static boolean getNeighboors(String testi) {
        ArrayList<String> neighbrs = new ArrayList<>();
        neighbrs.add("א");
        neighbrs.add("ב");
        neighbrs.add("יא");
        neighbrs.add("נאות לון");
        neighbrs.add("רמות");
        neighbrs.add("עומר");

        boolean isExist;

        // Check if neighboor exist
        if (neighbrs.contains(testi)) {
            isExist = true;
        }
        else{
            // Means neighboor not found
            isExist = false;
        }

        // Ret
        return isExist;
    }
}
