package activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mylibrary.R;

public class StartActivity extends AppCompatActivity {
    TextView hello;
    Button reg, sign;
    Animation emersion;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reg = findViewById(R.id.reg);
        sign = findViewById(R.id.sign);
        image = findViewById(R.id.logotype);
        emersion = AnimationUtils.loadAnimation(this, R.anim.anim);
        image.startAnimation(emersion);
    reg.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(StartActivity.this, RegistrationActivity.class);
            startActivity(intent);
        }
    });
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, SignActivity.class);
                startActivity(intent);
            }
        });
    }
}