package com.example.admin.myapplication_test;

//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements myInterface{
    String key="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //key = getIntent().getStringExtra("keyAccount");
    }


    @Override
    public void onDataSelected(String data, String city) {
        key = getIntent().getStringExtra("keyAccount");
        Bundle bundle = new Bundle();
        bundle.putString("data", data);
        bundle.putString("keyAccount", key);
        bundle.putString("city", city);
        SelectFragment selectFragment = new SelectFragment();
        if(selectFragment.isInLayout() || selectFragment != null){//?
            selectFragment.setArguments(bundle);
        }
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.select_fragment, selectFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}