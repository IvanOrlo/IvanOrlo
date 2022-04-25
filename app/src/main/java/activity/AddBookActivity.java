package activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mylibrary.R;
import com.example.mylibrary.objects.Book;
import com.example.mylibrary.objects.Library;

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
        lName = findViewById(R.id.lName);
        lGener = findViewById(R.id.gener);
        addAuthor = findViewById(R.id.addAuthor);
        addLib = findViewById(R.id.add2);
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
               Book p = new Book("","",0,0,"");
                p.setName(t);
                p.setAuthor(a);
                p.setDate(d);
                p.setLocation(l);
                BookTable bookTable = new BookTable(getBaseContext());
                bookTable.insert(p);



                Intent intent = new Intent(AddBookActivity.this, PersonalAreaActivity.class);
                startActivity(intent);

            }
        });
        addLib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Library library = new Library("","","");
                library.setName(lName.getText().toString());
                library.setAdress(lAdress.getText().toString());
                LibraryTable libraryTable = new LibraryTable(getBaseContext());
                libraryTable.insert(library);
            }
        });
    }
}