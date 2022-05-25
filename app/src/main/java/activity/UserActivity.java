package activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mylibrary.R;

public class UserActivity extends AppCompatActivity {
    TextView uin,uif,uiid,uin2;
    ImageView goToBC;
    Button editUinfo;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        getSupportActionBar().hide();
        prefs = getSharedPreferences("com.example.app", Context.MODE_PRIVATE);
        uin = findViewById(R.id.userInfoName);
        uin2 = findViewById(R.id.userName2);
        uif = findViewById(R.id.userInfoFraction);
        goToBC = findViewById(R.id.imageView4);
        uiid = findViewById(R.id.userInfoId);
        uin.setText(prefs.getString("name", "") + " "+prefs.getString("lastName", ""));
        uin2.setText(prefs.getString("name", ""));
        goToBC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, BookCaseActivity.class);
                startActivity(intent);

            }
        });


    }
}