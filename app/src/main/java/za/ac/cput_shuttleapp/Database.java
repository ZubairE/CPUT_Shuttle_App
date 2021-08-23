package za.ac.cput_shuttleapp;

import android.app.DatePickerDialog;
import android.app.DownloadManager;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.format.Time;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.annotation.Nullable;


public class Database extends SQLiteOpenHelper{

 private static final String DATABASE_NAME="myDatabase";
 private static final String TABLE_NAME="disabledBooking";
 private static final int DATABASE_Version=1;
 private static final String DISABILITY="disability";
 private static final String FROM="From";
 private static final String To="To";
 private static final String DATE_PICKER_DIALOG="date";
 private static final String TIME_PICKER="time";
 private static final String CREATE_TABLE = "CREATE"+TABLE_NAME+
         "("+DISABILITY+"VARCHAR(255),"+FROM+"VARCHAR(255),"+To+"VARCHAR(255),"+DATE_PICKER_DIALOG+"TEXT,"+TIME_PICKER+"TEXT);";
 private static  final String DROP_TABLE="DROP TABLE IF EXISTS "+TABLE_NAME;
 private Context context;

 public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
      db.execSQL(CREATE_TABLE) ;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
     db.execSQL(DROP_TABLE);

    }
}

public void insertData(String disability, String From, )







}
