package com.example.admin.myapplication_test;

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
        nhietdohientai = (TextView) findViewById(R.id.nhietdohientai);
        nhietdothietlap = (TextView) findViewById(R.id.nhietdothichhopchoban);

        final Bundle bundle = getIntent().getExtras();

        nd_thietlap = "25";

        OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd hh:mm:ss");
                dateFormat.setLenient(false);
                Date today = new Date();
                String s = dateFormat.format(today);

                DatabaseReference myRef = database.getReference("Account").child(bundle.getString("keyAccount"));
                DataSave dataSave = new DataSave();
                dataSave.setNhietdohientai(bundle.getString("data"));
                dataSave.setNhietdothietlap(nd_thietlap);
                dataSave.setVitrihientai(bundle.getString("city"));
                myRef.child("data").child(s).setValue(dataSave);
            }
        });
    }

}
