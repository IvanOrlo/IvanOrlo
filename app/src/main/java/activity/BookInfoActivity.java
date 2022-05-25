package activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mylibrary.R;
import com.example.mylibrary.objects.Book;

import java.util.ArrayList;

public class BookInfoActivity extends AppCompatActivity {
    TextView title, authorT, statusT, locationT;
    Button addCase;
    ImageView freeIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);
        getSupportActionBar().hide();
        title = findViewById(R.id.title);
        authorT = findViewById(R.id.authorT);
        locationT = findViewById(R.id.locationT);
        addCase = findViewById(R.id.addCase);
        statusT = findViewById(R.id.statusT);
        freeIcon = findViewById(R.id.freeIcon);
        Intent i = getIntent();

        Book p = new Book(i.getStringExtra("title"),
                i.getStringExtra("author"),
                i.getIntExtra("date",0),
                i.getIntExtra("id",0),
                i.getStringExtra("location"));
        p.setFree(i.getStringExtra("isFree"));

        title.setText(p.getName());
        authorT.setText(this.getIntent().getStringExtra("author"));
        locationT.setText(this.getIntent().getStringExtra("location"));

        //свободна ли книга?
        BookTable connection = new BookTable(this);
        ArrayList<Book> yourBookTable = connection.selectMy();
        for (Book a:
             yourBookTable) {
            if(a.getId()==p.getId()){
                statusT.setText("книга занята");
                freeIcon.setImageResource(R.drawable.notfree);
                addCase.setClickable(false);
                addCase.setVisibility(View.INVISIBLE);
            }

        }
        

        //добавить в кейс
        addCase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statusT.setText("книга добавлена в кейс");
                freeIcon.setImageResource(R.drawable.notfree);

                p.setFree("my");
                new BookCaseTable(getBaseContext()).insert(p);
            }
        });
    }
}