package activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mylibrary.R;
import com.example.mylibrary.objects.Book;

import java.util.ArrayList;

public class PersonalAreaActivity extends AppCompatActivity {
    SharedPreferences prefs;
    ImageView avatar, searchGo;
    TextView userName;
    EditText search;
    RecyclerView findedbooks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_area);
        getSupportActionBar().hide();
        avatar = findViewById(R.id.avatarButt);
        userName = findViewById(R.id.userName);
        search = findViewById(R.id.search);
        searchGo = findViewById(R.id.searchGo);
        prefs = getSharedPreferences("com.example.app", Context.MODE_PRIVATE);

       userName.setText(prefs.getString("name", ""));
//пробуем достать параметры книг
        BookTable connection = new BookTable(this);
        ArrayList<Book> books = connection.setAll();

//показываем список найденых книг
        LinearLayoutManager layoutManager= new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        findedbooks = (RecyclerView) findViewById(R.id.findedbooks);
        findedbooks.setLayoutManager(layoutManager);
        ArrayList<Book> booksList = new ArrayList<>();
        for (Book book:books
             ) {
            booksList.add(book);
        }
        BookAdapter adapter = new BookAdapter(getBaseContext(), booksList);
        findedbooks.setAdapter(adapter);
// тыкаем и смотрим инфу книги
        adapter.setClickListener(new BookAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Intent intent = new Intent(getBaseContext(), BookInfoActivity.class);
                Book book = adapter.getItem(position);
                intent.putExtra("title",book.getName());
                intent.putExtra("author",book.getAuthor());
                intent.putExtra("date",book.getDate());
                intent.putExtra("location",book.getLocation());
                startActivity(intent);
            }
        });
        searchGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}