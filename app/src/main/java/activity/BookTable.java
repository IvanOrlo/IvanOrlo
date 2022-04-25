package activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.mylibrary.objects.Book;

import java.util.ArrayList;

public class BookTable {
    private final Context context;
    private DbConnection connection;
    public BookTable(Context context) {
        this.context = context;
        connection = new DbConnection(context);
    }
    public void insert(Book book){
        ContentValues cv = new ContentValues();
        cv.put("name",book.getName());
        cv.put("author",book.getAuthor());
        cv.put("date",book.getDate());
        cv.put("location",book.getLocation());
        connection.getWritableDatabase().insert("books", null, cv);
    }
    public void delete(){
        connection.getWritableDatabase().delete("books",null,null);
    }
    public void delete(int id){
        String[] args = {id+""};
        connection.getWritableDatabase().delete("books","id = ?"+ id,args);
    }



    public ArrayList<Book> setAll() {
        Cursor cursor = connection.getReadableDatabase().query("books",null,null,null,null,null,null);
        ArrayList<Book> list = new ArrayList<>();
        cursor.moveToFirst();
        if(!cursor.isAfterLast()){
            do{
                Book b =new Book("","",0,0,"");
                b.setId(cursor.getInt(0));
                b.setName(cursor.getString(1));
                b.setAuthor(cursor.getString(2));
                b.setDate(cursor.getInt(3));
                b.setLocation(cursor.getString(4));
                list.add(b);
            }while(cursor.moveToNext());
        }
        return list;
    }
    public ArrayList<Book> selectByName(String search) {
        Cursor cursor = connection.getReadableDatabase().query("books", null, "name like ?",  new String[]{"%" + search + "%"}, null, null, null);
        ArrayList<Book> list = new ArrayList<>();
        cursor.moveToFirst();
        if(!cursor.isAfterLast()){
            do{
                Book b =new Book("","",0,0,"");
                b.setId(cursor.getInt(0));
                b.setName(cursor.getString(1));
                b.setAuthor(cursor.getString(2));
                b.setDate(cursor.getInt(3));
                b.setLocation(cursor.getString(4));
                list.add(b);
            }while(cursor.moveToNext());
        }
        return list;
    }
    
}
