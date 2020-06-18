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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Activity_One extends AppCompatActivity {
    Button btn_ok;
    TextView tv_nhietdo;
    SeekBar seekBar_nhietdo;
    TextView tvNhietDoSeekBar;

    private String ketqua = "";
    private String[] nhietdo = {"10", "20", "30", "40", "50"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__one);

        btn_ok = (Button) findViewById(R.id.id_OK);
        tv_nhietdo = (TextView) findViewById(R.id.id_nhietdo);
        seekBar_nhietdo = (SeekBar) findViewById(R.id.seekbar);
        tvNhietDoSeekBar = (TextView) findViewById(R.id.tv_nhietdo_seekbar);

        Bundle bundle = getIntent().getExtras();
        final String nhietdohientai = bundle.getString("data");
        final String keyAccount = bundle.getString("keyAccount");
        final String city = bundle.getString("city");

        tv_nhietdo.setText(nhietdohientai + " °C");

        final FirebaseDatabase database = FirebaseDatabase.getInstance();


        seekBar_nhietdo.setProgress(25);
        tvNhietDoSeekBar.setText(25+"");
        seekBar_nhietdo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ketqua = progress+"";
                tvNhietDoSeekBar.setText(ketqua);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

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

                    Intent intent = new Intent(Activity_One.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}
