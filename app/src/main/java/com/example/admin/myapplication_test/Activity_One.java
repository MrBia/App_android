package com.example.admin.myapplication_test;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Activity_One extends AppCompatActivity {
    Spinner spinner;
    Button btn_ok;
    TextView tv_nhietdo;

    private String ketqua = "";
    private String[] nhietdo = {"10", "20", "30", "40", "50"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__one);

        spinner = (Spinner) findViewById(R.id.id_Spinner);
        btn_ok = (Button) findViewById(R.id.id_OK);
        tv_nhietdo = (TextView) findViewById(R.id.id_nhietdo);

        Bundle bundle = getIntent().getExtras();
        final String nhietdohientai = bundle.getString("data");
        final String keyAccount = bundle.getString("keyAccount");
        final String city = bundle.getString("city");

        tv_nhietdo.setText(nhietdohientai);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, nhietdo);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ketqua = nhietdo[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ketqua = "";
            }
        });

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ketqua.equals("")) {
                    Toast.makeText(Activity_One.this, "Ban chua chon nhiet do", Toast.LENGTH_SHORT).show();
                }else{
                    // lay du lieu nhieu do de thiet lap cho ao
                    DateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd hh:mm:ss");
                    dateFormat.setLenient(false);
                    Date today = new Date();
                    String s = dateFormat.format(today);


                    DatabaseReference myRef = database.getReference("Account").child(keyAccount);
                    DataSave dataSave = new DataSave();
                    dataSave.setNhietdohientai(nhietdohientai);
                    dataSave.setNhietdothietlap(ketqua);
                    dataSave.setVitrihientai(city);
                    myRef.child("data").child(s).setValue(dataSave);
                    Toast.makeText(Activity_One.this, "ok chon roi", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
