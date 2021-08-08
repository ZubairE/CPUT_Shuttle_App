package za.ac.cput_shuttleapp;
//Breyton Ernstzen - 217203027
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class RegistrationDatabase extends SQLiteOpenHelper {
    public static final String DB_NAME = "studentRegistration.db";
    public static final String DB_TABLE_NAME = "studentInfo";
    public static final String COLUMN_1 = "ID";
    public static final String COLUMN_2 = "First_Name";
    public static final String COLUMN_3 = "Last_Name";
    public static final String COLUMN_4 = "Student_Number";
    public static final String COLUMN_5 = "Password";
    public static final String COLUMN_6 = "Confirm_Password";

    public RegistrationDatabase(@Nullable Context context) {
        super(context, DB_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DB_TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,First_Name TEXT,Last_Name TEXT,Student_Number long,Password TEXT,Confirm_Password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_NAME);
        onCreate(db);

    }

}
