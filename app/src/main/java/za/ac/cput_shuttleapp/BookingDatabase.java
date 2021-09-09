package za.ac.cput_shuttleapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BookingDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Booking.db";
    public static final String TABLE_NAME = "Booking_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "DEPARTURE";
    public static final String COL_3 = "DESTINATION";
    public static final String COL_4 = "TIME";
    public static final String COL_5 = "DATE";

    public BookingDatabase(Context context) { super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER,DEPARTURE TEXT, DESTINATION TEXT,TIME TEXT, DATE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

}
