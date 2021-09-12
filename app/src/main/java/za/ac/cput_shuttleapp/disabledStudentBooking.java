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

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static za.ac.cput_shuttleapp.R.drawable.logo;

public class disabledStudentBooking extends AppCompatActivity {
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;

    Button buttonBack;
    Button buttonNext;
    Button buttonConfirm;
    Spinner txtDisability;
    Spinner txtFrom;
    Spinner txtTo;
    Spinner txtDate;
    Spinner txtTime;

    int counter = 30;

    private long backPressTime;
    private Toast backToast;

    Calendar c= Calendar.getInstance();
    String currentDate = DateFormat.getDateInstance().format(c.getTime());

    List<String> bDisability = Arrays.asList("--nothing selected--","Blind","Paralyzed","Deaf","Mute","Lost Leg","Lost Arm","Other");
    List<String> bDisabilityFrom = Arrays.asList("--nothing selected--","Orchard Residence","District Six");
    List<String> bDisabilityTo = Arrays.asList("--nothing selected--","District Six","Orchard Residence");
    List<String> bDisabilityDate = Arrays.asList(currentDate);
    List<String> bDisabilityTime = Arrays.asList("--nothing selected--","7:00","8:00","9:00","10:00","11:00","12:00","13:00","14:00","15:00");


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

        txtDisability = findViewById(R.id.spinnerDisability);
        ArrayAdapter S_disability = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item,bDisability);
        S_disability.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        txtDisability.setAdapter(S_disability);

        txtFrom = findViewById(R.id.spinnerDisabilityFrom);
        ArrayAdapter S_from = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item,bDisabilityFrom);
        S_from.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        txtFrom.setAdapter(S_from);

        txtTo = findViewById(R.id.spinnerDisabilityTo);
        ArrayAdapter S_to = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item,bDisabilityTo);
        S_to.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        txtTo.setAdapter(S_to);

        txtDate = findViewById(R.id.spinnerDisabilityDate);
        ArrayAdapter S_date = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item,bDisabilityDate);
        S_date.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        txtDate.setAdapter(S_date);

        txtTime = findViewById(R.id.spinnerDisabilityTime);
        ArrayAdapter S_time = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item,bDisabilityTime);
        S_time.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        txtTime.setAdapter(S_time);

        buttonBack = findViewById(R.id.bck);
        buttonConfirm = findViewById(R.id.cnfrm);
        buttonNext = findViewById(R.id.nxt);

        buttonConfirm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String dis = txtDisability.getSelectedItem().toString();
                String frm = txtFrom.getSelectedItem().toString();
                String to = txtTo.getSelectedItem().toString();
                String date = txtDate.getSelectedItem().toString();
                String time = txtTime.getSelectedItem().toString();
                db = openHelper.getWritableDatabase();

                if(txtDisability.getSelectedItem().toString().equals(bDisability.get(0)) || (txtFrom.getSelectedItem().toString().equals(bDisabilityFrom.get(0))||
                        (txtTo.getSelectedItem().toString().equals(bDisabilityTo.get(0)) ||(txtTime.getSelectedItem().toString().equals(bDisabilityTime.get(0)))))) {
                    Toast.makeText(disabledStudentBooking.this, "No booking has been made.Please complete all fields", Toast.LENGTH_LONG).show();

                }else if(txtFrom.getSelectedItem().toString().equals(bDisabilityFrom.get(1)) && (txtTo.getSelectedItem().toString().equals(bDisabilityTo.get(2))) ||
                        (txtFrom.getSelectedItem().toString().equals(bDisabilityFrom.get(2)) && (txtTo.getSelectedItem().toString().equals(bDisabilityTo.get(1))))){
                    Toast.makeText(disabledStudentBooking.this,"Starting point cannot be the same as destination",Toast.LENGTH_LONG).show();

                } else if (txtDisability.getSelectedItem().toString().equals(dis) && (txtFrom.getSelectedItem().toString().equals(frm) &&
                        (txtTo.getSelectedItem().toString().equals(to) && (txtDate.getSelectedItem().toString().equals(date) && (txtTime.getSelectedItem().toString().equals(time)))))) {
                    addBooking(dis, frm, to, date, time);
                    int dec = counter--;
                    Toast.makeText(disabledStudentBooking.this, "Booking has been made.Seats available: " + dec, Toast.LENGTH_LONG).show();

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








