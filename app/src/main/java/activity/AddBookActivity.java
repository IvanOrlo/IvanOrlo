package activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mylibrary.R;
import com.example.mylibrary.objects.Book;

import forserver.ResponseExample;
import forserver.SendBook;
import forserver.ServerHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddBookActivity extends AppCompatActivity{
    EditText nameBook,author,date,locationT,lName,lAdress,lGener;
    Button add, addLib,addAuthor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        getSupportActionBar().hide();
        nameBook = findViewById(R.id.nameBook);
        author = findViewById(R.id.author);
        date = findViewById(R.id.date);
        add = findViewById(R.id.add);
        locationT = findViewById(R.id.lAdress);

        lGener = findViewById(R.id.gener);

        //добавляем книгу
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t;
                String a;
                int d;
                String l;
               try {
                    t = nameBook.getText().toString();
                    a = author.getText().toString();
                    d = Integer.parseInt(date.getText().toString());
                    l = locationT.getText().toString();
                } catch (Exception e) {
                    Log.e("error", e.getMessage());
                    e.printStackTrace();

                    return;
                }
               Book bookToSave = new Book("","",0,0,"");
                bookToSave.setName(t);
                bookToSave.setAuthor(a);
                bookToSave.setDate(d);
                bookToSave.setLocation(l);
                BookTable bookTable = new BookTable(getBaseContext());
                bookTable.insert(bookToSave);

                SendBook c = ServerHelper.getRetrofit().create(SendBook.class);
                Call<ResponseExample> call = c.saveBook(bookToSave);
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


                    Intent intent = new Intent(AddBookActivity.this, PersonalAreaActivity.class);
                startActivity(intent);

            }
        });
    }
}