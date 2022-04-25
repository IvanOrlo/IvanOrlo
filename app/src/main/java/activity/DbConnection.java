package activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mylibrary.objects.Book;

public class DbConnection extends SQLiteOpenHelper {

        public static final String DB_NAME = "myaapDB";
        public static final int VERSION = 15;
        public DbConnection(Context context){
            super(context, DB_NAME,null,VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            String s = "create table books(" +
                    "id integer primary key autoincrement," +
                    "name text not null,"+
                    "author text not null," +
                    "date int not null,"+
                    "location text not null"+
                    ");";
            db.execSQL(s);

            //тестовая книжка
            Book p = new Book("","",0,0,"");
            p.setName("Евгений Онегин");
            p.setAuthor("Н.В. Гоголь");
            p.setDate(1830);
            p.setLocation("0");
            addBook(p, db);

            Book p1 = new Book("","",0,0,"");
            p1.setName("Руслан и Людмила");
            p1.setAuthor("А.С. Пушкин");
            p1.setDate(1820);
            p1.setLocation("1");
            addBook(p1,db);

            Book p2 = new Book("","",0,0,"");
            p2.setName("Java для начиниающих");
            p2.setAuthor("C418");
            p2.setDate(2018);
            p2.setLocation("2");
            addBook(p2,db);

            Book p3 = new Book("","",0,0,"");
            p3.setName("Незнайка на луне");
            p3.setAuthor("Н.В. Носов");
            p3.setDate(1965);
            p3.setLocation("3");
            addBook(p3,db);
        }


        public void addBook(Book book, SQLiteDatabase db){
            ContentValues cv = new ContentValues();
            cv.put("name",book.getName());
            cv.put("author",book.getAuthor());
            cv.put("date",book.getDate());
            cv.put("location",book.getLocation());
            db.insert("books", null, cv);
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            String s = "DROP table if exists books;";
            db.execSQL(s);
            onCreate(db);
        }

}
