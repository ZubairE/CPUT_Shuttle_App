package za.ac.cput_shuttleapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHandling extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "ShuttleBus.db";
    public static final String TABLE_NAME = "Bus_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NUMBER";
    public static final String COL_3 = "PASSWORD";

    public DatabaseHandling(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase mdb = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase mdb) {
        mdb.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,NUMBER INTEGER, PASSWORD TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase mdb, int oldVersion, int newVersion) {
        mdb.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(mdb);
    }

    public boolean insertData(String StudentNumber, String Password) {
        SQLiteDatabase mdb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,  StudentNumber);
        contentValues.put(COL_3, Password);
        long result = mdb.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getAllData()
    {
        SQLiteDatabase mdb = this.getWritableDatabase();
        Cursor res= mdb.rawQuery("select * from "+ TABLE_NAME,null);
        return res;
    }

    public boolean updateData(String ID,String StudentNumber,String Password )
    {
        SQLiteDatabase mdb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, ID);
        contentValues.put(COL_2, StudentNumber);
        contentValues.put(COL_3, Password);

        mdb.update(TABLE_NAME,contentValues,"ID=?",new String[] {ID});
        return true;
    }








}
