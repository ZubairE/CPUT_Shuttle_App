package za.ac.cput_shuttleapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.List;

import static za.ac.cput_shuttleapp.R.drawable.logo;

public class disabledStudentBooking extends AppCompatActivity {
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;

    Button buttonBack;
    Button buttonNext;
    Button buttonConfirm;
    EditText txtDisability;
    EditText txtFrom;
    EditText txtTo;
    EditText txtDate;
    EditText txtTime;

    private long backPressTime;
    private Toast backToast;

    @Override
    public void onBackPressed() {

        if (backPressTime + 2000 > System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
        return;
    }else{
           backToast = Toast.makeText(getBaseContext(),"Press back again to exit",Toast.LENGTH_SHORT);
           backToast.show();
        }
        backPressTime = System.currentTimeMillis();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disabled_student_booking);
        openHelper = new Database(this);

        txtDisability = findViewById(R.id.editTextTextMultiLine);
        txtFrom = findViewById(R.id.editTextTextMultiLine2);
        txtTo = findViewById(R.id.editTextTextMultiLine3);
        txtDate = findViewById(R.id.editTextTextMultiLine4);
        txtTime = findViewById(R.id.editTextTextMultiLine5);
        buttonBack = findViewById(R.id.bck);
        buttonConfirm = findViewById(R.id.cnfrm);
        buttonNext = findViewById(R.id.nxt);

        buttonConfirm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String dis = txtDisability.getText().toString();
                String frm = txtFrom.getText().toString();
                String to = txtTo.getText().toString();
                String date = txtDate.getText().toString();
                String time = txtTime.getText().toString();
                db = openHelper.getWritableDatabase();

                if (TextUtils.isEmpty(txtDisability.getText().toString()) || TextUtils.isEmpty(txtFrom.getText().toString()) ||
                        TextUtils.isEmpty(txtTo.getText().toString()) || TextUtils.isEmpty(txtDate.getText().toString()) ||
                        TextUtils.isEmpty(txtTime.getText().toString())) {
                    Toast.makeText(disabledStudentBooking.this, "Complete all fields", Toast.LENGTH_LONG).show();

                } else if (txtDisability.getText().toString().equals(dis) && (txtFrom.getText().toString().equals(frm) &&
                        (txtTo.getText().toString().equals(to) && (txtDate.getText().toString().equals(date) && (txtTime.getText().toString().equals(time)))))) {
                    addBooking(dis, frm, to, date, time);
                    Toast.makeText(disabledStudentBooking.this, "Booking has been made", Toast.LENGTH_LONG).show();


                }
            }
        });

        buttonNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);

            }
        });


        buttonBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });

    }

    public void addBooking(String disability, String from, String to, String date, String time) {
        ContentValues cv = new ContentValues();
        cv.put(Database.COL2, disability);
        cv.put(Database.COL3, from);
        cv.put(Database.COL4, to);
        cv.put(Database.COL5, date);
        cv.put(Database.COL6, time);
        long id = db.insert(Database.TABLE, null, cv);

    }

    public void back() {
        Intent back = new Intent(this,StudentBooking.class);
        startActivity(back);
    }
}








