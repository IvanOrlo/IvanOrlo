package activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mylibrary.R;
import com.example.mylibrary.objects.User;

import forserver.AuthBody;
import forserver.AuthServ;
import forserver.ResponseExample;
import forserver.ServerHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {
    SharedPreferences prefs;
    EditText name;
    EditText lastName;
    EditText editTextTextPassword;
    EditText editTextTextPassword2;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration2);
        name = findViewById(R.id.name);
        lastName = findViewById(R.id.lastName);
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
               prefs.edit().putString("lastName", lastName.getText().toString()).apply();
               prefs.edit().putString("password", editTextTextPassword.getText().toString()).apply();
                    User user = new User();
                  user.setUserName(name.toString());
                  user.setPassword(Integer.parseInt(editTextTextPassword.getText().toString()));
                AuthServ c = ServerHelper.getRetrofit().create(AuthServ.class);
                Call<ResponseExample> call = c.authByBody(new AuthBody(name.getText().toString(),editTextTextPassword.getText().toString()));
                call.enqueue(new Callback<ResponseExample>() {
                    @Override
                    public void onResponse(Call<ResponseExample> call, Response<ResponseExample> response) {
                        ResponseExample re = response.body();
                        if (response.code() == 200){
                            Toast.makeText(getBaseContext(), re.getToken(), Toast.LENGTH_SHORT).show();
                        }
                        System.out.println("bad code");

                    }

                    @Override
                    public void onFailure(Call<ResponseExample> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

                Intent intent = new Intent(RegistrationActivity.this, PersonalAreaActivity.class);
                startActivity(intent);

            }
        });
    }
}