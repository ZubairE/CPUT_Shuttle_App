package za.ac.cput_shuttleapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Student.db";
    public static final String TABLE_NAME = "Student_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "DEPARTURE";
    public static final String COL_3 = "DESTINATION";
    public static final String COL_4 = "TIME";
    public static final String COL_5 = "DATE";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,DEPARTURE TEXT, DESTINSTION TEXT,TIME TEXT, DATE TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String Departure,String Destination, String Time, String Date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, Departure);
        contentValues.put(COL_3, Destination);
        contentValues.put(COL_4, Time);
        contentValues.put(COL_5, Date);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }
    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from "+ TABLE_NAME,null);
    }
    public boolean updateData(String Departure,String Destination,String Time,String Date )
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, Departure);
        contentValues.put(COL_3, Destination);
        contentValues.put(COL_4, Time);
        contentValues.put(COL_5, Date);
        db.update(TABLE_NAME,contentValues,"Departure=?",new String[] {Departure});
        return true;
    }
    public Integer deleteData(String DEPARTURE){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"DEPARTURE=?",new String[] {DEPARTURE});

    }
}
