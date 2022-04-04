package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class PersonalArea extends AppCompatActivity {
    SharedPreferences prefs;
    Animation emersion;
    ImageButton avatar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_area);
        getSupportActionBar().hide();
        avatar = findViewById(R.id.avatarButt);
        emersion = AnimationUtils.loadAnimation(this, R.anim.anim);
        prefs = getSharedPreferences("com.example.app", Context.MODE_PRIVATE);

    }
}