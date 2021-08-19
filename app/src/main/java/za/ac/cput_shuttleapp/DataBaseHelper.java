package za.ac.cput_shuttleapp;
//Breyton Ernszten - 217203027
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    //Creates the database table
    public static final String DB_NAME = "student_Registration.db";//database file
    public static final String MY_DB_TABLE_NAME = "studenDetails";//Table name
    public static final String COLUMN_1 = "ID";//Attribute
    public static final String COLUMN_2 = "First_Name";//Attribute
    public static final String COLUMN_3 = "Last_Name";//Attribute
    public static final String COLUMN_4 = "Student_Number";//Attribute
    public static final String COLUMN_5 = "Password";//Attribute
    public static final String COLUMN_6 = "Cell_No";//Attribute

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null,  1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Executes SQL statement that will create the table
        db.execSQL("CREATE TABLE " + MY_DB_TABLE_NAME + " (ID INTEGER, First_Name TEXT, Last_Name TEXT, Student_Number TEXT PRIMARY KEY, Password TEXT, Cell_No TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drops old table and replace with updated version of the table
        db.execSQL("DROP TABLE IF EXISTS " + MY_DB_TABLE_NAME);
        onCreate(db);
    }

}
