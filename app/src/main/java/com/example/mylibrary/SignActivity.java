package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignActivity extends AppCompatActivity {
    SharedPreferences prefs;
    EditText name;
    EditText editTextTextPassword;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        getSupportActionBar().hide();
        name = findViewById(R.id.name2);
        next = findViewById(R.id.next2);
        editTextTextPassword = findViewById(R.id.editTextTextPassword3);
        prefs = getSharedPreferences("com.example.app", Context.MODE_PRIVATE);
        if (prefs.getString("name", "") == null) return;
        name.setText(prefs.getString("name", ""));
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!name.getText().equals(prefs.getString("name","")) && editTextTextPassword.getText()
                        .toString().equals(prefs.getString("password",""))){
                    Intent intent = new Intent(SignActivity.this, PersonalArea.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Ваше имя или пароль не совпадают", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}