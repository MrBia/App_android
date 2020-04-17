package com.example.admin.myapplication_test;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main_Start extends AppCompatActivity{
    EditText userName;
    EditText passWord;
    Button dangNhap;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__start);

        userName = (EditText) findViewById(R.id.username);
        passWord = (EditText) findViewById(R.id.password);
        dangNhap = (Button) findViewById(R.id.dangnhap);

        final ArrayList<User> users = null;
        dangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            getValue(new DataUser() {
                @Override
                public void getValue(ArrayList<User> users) {
                    for(User user : users){
                        if(user.getUserName().equals(userName.getText().toString()) &&
                        user.getPassWord().equals(passWord.getText().toString())){
                            Intent intent = new Intent(Main_Start.this, MainActivity.class);
                            intent.putExtra("keyAccount", user.getKey());
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(Main_Start.this, "Sai tai khoan", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });
            }
        });
    }

    public void getValue(final DataUser dataUser){
        DatabaseReference all = database.getReference();
        final Query allUser = all.child("Account");

        allUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<User> users = new ArrayList<>();
                for(DataSnapshot item : dataSnapshot.getChildren()){
                    User user = item.getValue(User.class);
                    users.add(user);
                }
                dataUser.getValue(users);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.weather, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.change_city){
            showInputDialog();
        }
        return false;
    }

    private void showInputDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Change city");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setPositiveButton("Go", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                changeCity(input.getText().toString());
            }
        });
        builder.show();
    }

    public void changeCity(String city){
        WeatherFragment wf = (WeatherFragment)getSupportFragmentManager()
                .findFragmentById(R.id.container);
        wf.changeCity(city, 0.0, 0.0);
        new CityPreference(this).setCity(city);
    }
}
