package siddhant.curositylabs.moneymanager;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;

/* SQLiteOpenHelper class is somehow supposed to help in creating databases;
 * no idea how's that gonna work out  */

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "HandleMoneydb";
    public static final String TABLE_NAME = "DailyExpenses";


    /* What does this constructor do? Best guess, it somehow initializes database (O.o) */

    public DatabaseHandler(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    /* onCreate is supposed to run only once */
    @Override

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table " + TABLE_NAME +
                        " (id integer primary key autoincrement not null, item text,cost integer,category text,date text)"
        );


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public boolean insertData(String item, Integer cost,String category,String date)
    {
        // id should auto increment
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("item", item);
        contentValues.put("cost", cost);
        contentValues.put("category", category);
        contentValues.put("date", date);
        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + TABLE_NAME, null );
        return res;
    }





}
