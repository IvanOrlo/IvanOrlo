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

public class RegistrationActivity extends AppCompatActivity {
    SharedPreferences prefs;
    EditText name;
    EditText email;
    EditText editTextTextPassword;
    EditText editTextTextPassword2;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration2);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        prefs = getSharedPreferences("com.example.app", Context.MODE_PRIVATE);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);
        editTextTextPassword2 = findViewById(R.id.editTextTextPassword2);
        next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editTextTextPassword.getText().toString().equals(editTextTextPassword2.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "пароли не совпадают", Toast.LENGTH_SHORT).show();
                    return;
                }
               prefs.edit().putString("name", name.getText().toString()).apply();
               prefs.edit().putString("password", editTextTextPassword.getText().toString()).apply();
                    User user = new User();
                  user.setUserName(name.toString());
                  user.setPassword(editTextTextPassword.getText().toString());
                Intent intent = new Intent(RegistrationActivity.this, PersonalArea.class);
                startActivity(intent);

            }
        });
    }
}