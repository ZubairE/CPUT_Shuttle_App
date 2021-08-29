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

    private static final String DATABASE_NAME = "myDatabase";
    private static final String TABLE_NAME = "disabledBooking";
    private static final int DATABASE_Version = 1;
    private static final String BOOKING_ID = "booking_Id";
    private static final String DISABILITY = "disability";
    private static final String FROM = "From";
    private static final String To = "To";
    private static final String DATE = "date";
    private static final String TIME = "time";
    private static final String CREATE_TABLE = "CREATE" + TABLE_NAME +
            "(" + DISABILITY + "VARCHAR(255)," + FROM + "VARCHAR(255)," + To + "VARCHAR(255)," + DATE + "TEXT," + TIME + "TEXT);";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private Context context;

    public Database(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_Version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + "("
                + BOOKING_ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                + DISABILITY + "STRING,"
                + FROM + "STRING,"
                + To + "STRING, "
                + DATE + "TEXT,"
                + TIME + "TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);

    }


    public void insertData(String DISABILITY, String FROM, String To, String DATE, String TIME) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DISABILITY, DISABILITY);
        values.put(FROM, FROM);
        values.put(To, To);
        values.put(DATE, DATE);
        values.put(TIME, TIME);
        db.insert("disabledBooking ", null, values);


    }

    public String getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] colunms = {BOOKING_ID, DISABILITY, FROM, To, DATE, TIME};
        Cursor cursor = db.query(TABLE_NAME, colunms, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            int booking_Id = cursor.getInt(cursor.getColumnIndex(BOOKING_ID));
            String disability = cursor.getString(cursor.getColumnIndex(DISABILITY));
            String From = cursor.getString(cursor.getColumnIndex(FROM));
            String TO = cursor.getString(cursor.getColumnIndex(To));
            String date = cursor.getString(cursor.getColumnIndex(DATE));
            String time = cursor.getString(cursor.getColumnIndex(TIME));
        }
        return buffer.toString();
    }

    public Integer delete(Integer booking_Id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("DisabledBooking", "booking_Id =?", new String[]{Integer.toString(booking_Id)});
    }

    public boolean updateBooking(Integer booking_Id, String DISABILITY, String FROM, String To, String DATE, String TIME) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BOOKING_ID, BOOKING_ID);
        values.put(DISABILITY, DISABILITY);
        values.put(FROM, FROM);
        values.put(To, To);
        values.put(DATE, DATE);
        values.put(TIME, TIME);
        db.update("disabledBooking ", values, "Booking_Id = ?", new String[]{Integer.toString(booking_Id)});
        return true;

    }
    public int numberOfRows(){
        SQLiteDatabase db = this.getWritableDatabase();
        int numRows= (int ) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        return numRows;
    }
public  Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor c=db.rawQuery("select* from " + TABLE_NAME, null);
        return c;

            }
        }





