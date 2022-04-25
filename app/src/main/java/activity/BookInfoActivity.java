package activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.mylibrary.R;
import com.example.mylibrary.objects.Book;

import java.util.ArrayList;

public class BookInfoActivity extends AppCompatActivity {
    TextView title, authorT, statusT, locationT;
    Button addCase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);
        getSupportActionBar().hide();
        title = findViewById(R.id.title);
        authorT = findViewById(R.id.authorT);
        statusT = findViewById(R.id.status);
        locationT = findViewById(R.id.locationT);
        addCase = findViewById(R.id.addCase);
        Intent i = getIntent();
        title.setText(this.getIntent().getStringExtra("title"));
        authorT.setText(this.getIntent().getStringExtra("author"));
        //statusT.setText(this.getIntent().getIntExtra("date",0)+"");
        locationT.setText(this.getIntent().getStringExtra("location"));
        ArrayList<Book> yourcase = new ArrayList<>();

    }
}