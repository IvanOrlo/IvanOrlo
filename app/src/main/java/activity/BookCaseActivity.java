package activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mylibrary.R;
import com.example.mylibrary.objects.Book;

import java.util.ArrayList;

import forserver.ResponseExample;
import forserver.SendBook;
import forserver.ServerHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookCaseActivity extends AppCompatActivity {
    RecyclerView addedBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_case);
        addedBooks = findViewById(R.id.addedBooks);
        getSupportActionBar().hide();
        Intent i = getIntent();

        BookTable connection = new BookTable(this);
        BookCaseTable connection2 = new BookCaseTable(this);
        ArrayList<Book> yourBookTable = connection.selectMy();
        LinearLayoutManager layoutManager= new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        addedBooks.setLayoutManager(layoutManager);
        BookAdapter adapter = new BookAdapter(this,yourBookTable);
        addedBooks.setAdapter(adapter);
//отправляем книгу на сервер
        adapter.setClickListener(new BookAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Book bookToSave = new Book("","",0,connection.selectMy().get(position).getId(),"");
                bookToSave.setName(adapter.getItem(position).getName());
                bookToSave.setAuthor(adapter.getItem(position).getAuthor());
                bookToSave.setDate(adapter.getItem(position).getDate());
                bookToSave.setLocation(adapter.getItem(position).getLocation());
                bookToSave.setId(adapter.getItem(position).getId());
                SendBook c = ServerHelper.getRetrofit().create(SendBook.class);
                Call<ResponseExample> call = c.saveChosenBook(bookToSave);
                call.enqueue(new Callback<ResponseExample>() {
                    @Override
                    public void onResponse(Call<ResponseExample> call, Response<ResponseExample> response) {
                        ResponseExample re = response.body();
                        if (response.code() == 200){
                            //Toast.makeText(getBaseContext(), re.getToken(), Toast.LENGTH_SHORT).show();
                        }
                        else {
                            System.out.println("bad code");
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseExample> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

                connection2.delete(adapter.getItem(position).getId());
                yourBookTable.clear();
                yourBookTable.addAll(connection.selectMy());
                adapter.notifyDataSetChanged();
                Toast.makeText(getBaseContext(), "Ваш заказ принят, ожидайте уведомления от библиотеки", Toast.LENGTH_SHORT).show();

            }
        });



    }
}