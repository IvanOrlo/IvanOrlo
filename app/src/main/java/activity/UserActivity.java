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

import forserver.AuthBody;
import forserver.CompleteResponce;
import forserver.GetUserId;
import forserver.ServerHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserActivity extends AppCompatActivity {
    TextView uin,uif,uiid,uin2;
    ImageView goToBC;
    Button libMode;
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
        libMode = findViewById(R.id.libMode);
        uin.setText("ваше имя: "+prefs.getString("name", "") + " "+prefs.getString("lastName", ""));
        uin2.setText(prefs.getString("name", ""));
        goToBC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, BookCaseActivity.class);
                startActivity(intent);

            }
        });
        libMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, AddBookActivity.class);
                startActivity(intent);
            }
        });
        //показываем id юзера
        Retrofit r = ServerHelper.getRetrofit();
        GetUserId t = r.create(GetUserId.class);
        Call<CompleteResponce> call = t.complete(new AuthBody(prefs.getString("name", ""),prefs.getString("password", "")));
        call.enqueue(new Callback<CompleteResponce>() {
            @Override
            public void onResponse(Call<CompleteResponce> call, Response<CompleteResponce> response) {
                CompleteResponce cr = response.body();
                System.out.println(response.code());
                try {
                    uif.setText("ваш id: "+cr.getId());
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<CompleteResponce> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}