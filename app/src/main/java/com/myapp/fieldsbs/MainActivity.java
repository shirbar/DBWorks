package com.myapp.fieldsbs;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button turnirsBtn = findViewById(R.id.TurnirsBtn);
        Button aviliableBtn = findViewById(R.id.AviliableBtn);
        Button logOutBtn = findViewById(R.id.logoutBtn);
        Button myReserveBtn = findViewById(R.id.MyReserveBtn);
        Button searchAddressBtn = findViewById(R.id.searchAddressBtn);

        turnirsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Turnir();
            }
        });

        aviliableBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            aviliable();
            }
        });

        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               logOut();
            }
        });

        myReserveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reservations();
            }
        });

        searchAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addressSearch();
            }
        });

    }

    private void logOut() {
        Intent i = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(i);
    }

    private void aviliable() {
        Intent i = new Intent(MainActivity.this,AviliableActivity.class);
        i.putExtra("command", "normal");
        i.putExtra("neighborhood", "none");
        startActivity(i);
    }


    private void Turnir() {
        Intent i = new Intent(MainActivity.this,TurnirsActivity.class);
        startActivity(i);
    }

    private void Reservations() {
            Intent i = new Intent(MainActivity.this, ReservationActivity.class);
            startActivity(i);
    }

    private void addressSearch() {
        Intent i = new Intent(MainActivity.this, AddressSelectActivity.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }

}
