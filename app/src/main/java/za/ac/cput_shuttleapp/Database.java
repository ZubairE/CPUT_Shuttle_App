package za.ac.cput_shuttleapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.format.Time;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="Disabled student booking";
    public static final String TABLE_NAME= "Disabled student booking";
    public static final String COL_1 ="BUS_REGISTRATION";
    public static final String COL_2="DRIVER_NAME";
    public static final String COL_3= "FROM";
    public static final String COL_4="TO";
    public static final String COL_5="TIME_ARRIVAL";
    public static final String COL_6="TIME_LEAVING";

    public Database(Context context, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, version);
        SQLiteDatabase db=this.getWriteableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
db.execSQL("create table " +TABLE_MAME +"(BUS_REGISTRATION DOUBLE PRIMARY KEY , DRIVER_NAME TEXT, FROM TEXT, TO TEXT, TIME_ARRIVAL TIME,TiME_LEAVING TIME)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
db.execSQL("DROP TABLE IF EXISTS "+TABLE_MAME);
onCreate(db);
    }
    public boolean insertData (double Bus_Registration, String Driver_Name, String To, String From, Time Time_Arrival, Time Time_Leaving){
       SQLiteDatabase db=this.getWriteableDatabase();
        ContentValues contentValues = new ContentValues() ;
        ContentValues.put(COL_1, Bus_Registration);
        ContentValues.put(COL_2, Driver_Name);
        ContentValues.put(COL_3, From);
        ContentValues.put(COL_4, To );
        ContentValues.put(COL_5, Time_Arrival);
        ContentValues.put(COL_6, Time_Leaving);
        long result =db.insert(TABLE_NAME, ContentValues);


    }

}
