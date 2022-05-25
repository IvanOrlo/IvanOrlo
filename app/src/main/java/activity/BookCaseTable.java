package activity;

import android.content.ContentValues;
import android.content.Context;

import com.example.mylibrary.objects.Book;

public class BookCaseTable {

    private final Context cont;
    private DbConnection connection;

    public BookCaseTable(Context context) {
        this.cont = context;
        connection = new DbConnection(context);
    }
    public void insert(Book book){
        ContentValues cv = new ContentValues();
        cv.put("id",book.getId());
        connection.getWritableDatabase().insert("bookcasetable", null, cv);
    }
    public void delete(){
        connection.getWritableDatabase().delete("bookcasetable",null,null);
    }
    public void delete(int id){
        String[] args = {id+""};
        connection.getWritableDatabase().delete("bookcasetable","id = ?",args);
    }


}
