package com.example.admin.myapplication_test;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Activity_Two extends AppCompatActivity {
    Button OK;
    TextView nhietdohientai;
    TextView nhietdothietlap;
    String nd_thietlap;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__two);

        OK = (Button) findViewById(R.id.btn_ok);
        nhietdohientai = (TextView) findViewById(R.id.id_nhietdo);
        nhietdothietlap = (TextView) findViewById(R.id.id_nhietdothichhop);

        final Bundle bundle = getIntent().getExtras();
        final String nhietdohientai_get = bundle.getString("data");
        final String keyAccount = bundle.getString("keyAccount");
        final String city = bundle.getString("city");


        nhietdohientai.setText(bundle.getString("data") + " ℃");
        nd_thietlap = "25" + " ℃";
        nhietdothietlap.setText(nd_thietlap);


        OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd hh:mm:ss");
                dateFormat.setLenient(false);
                Date today = new Date();
                String s = dateFormat.format(today);

                DatabaseReference myRef = database.getReference("Account").child(keyAccount);
                DataSave dataSave = new DataSave();
                dataSave.setNhietdohientai(nhietdohientai_get);
                dataSave.setNhietdothietlap(nd_thietlap);
                dataSave.setVitrihientai(city);
                myRef.child("data").child(s).setValue(dataSave);


                Intent intent = new Intent(Activity_Two.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
