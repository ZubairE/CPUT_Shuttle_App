package za.ac.cput_shuttleapp;

import android.app.DatePickerDialog;
import android.app.DownloadManager;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.FloatRange;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class Database extends SQLiteOpenHelper {

    public static final String NAME = "DisabeledStudent.db";
    public static final String TABLE = "Disabled";
    public static final String COL1 = "ID";
    public static final String COL2 = "DISABILITY";
    public static final String COL3 = "START";
    public static final String COL4 = "DESTINATION";
    public static final String COL5 = "DATE";
    public static final String COL6 = "TIME";

    public Database(Context context){super (context,NAME,null,1);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE + " (ID INTEGER,DISABILITY TEXT,START TEXT,DESTINATION TEXT,DATE TEXT,TIME TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }
}






