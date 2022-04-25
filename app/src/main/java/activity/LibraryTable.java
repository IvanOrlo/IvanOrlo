package activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.mylibrary.objects.Library;

import java.util.ArrayList;

public class LibraryTable {
    private final Context context;
    private DbConnection connection;

    public LibraryTable(Context context) {
        this.context = context;
        connection = new DbConnection(context);
    }
    public void insert(Library library){
        ContentValues cv = new ContentValues();
        cv.put("name",library.getName());
        cv.put("adress",library.getAdress());
        //cv.put("city",library.getCity());
        connection.getWritableDatabase().insert("librarys", null, cv);
    }
    public void delete(){
        connection.getWritableDatabase().delete("librarys",null,null);
    }
    public void delete(int id){
        String[] args = {id+""};
        connection.getWritableDatabase().delete("librarys","id = ?"+ id,args);
    }



    public ArrayList<Library> setAll() {
        Cursor cursor = connection.getReadableDatabase().query("librarys",null,null,null,null,null,null);
        ArrayList<Library> list = new ArrayList<>();
        cursor.moveToFirst();
        if(!cursor.isAfterLast()){
            do{
                Library library = new Library("","","");
                library.setName(cursor.getString(0));
                library.setAdress(cursor.getString(1));
                //library.setCity(cursor.getString(2));
                list.add(library);
            }while(cursor.moveToNext());
        }
        return list;
    }
}
