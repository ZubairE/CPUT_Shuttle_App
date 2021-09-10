package za.ac.cput_shuttleapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StudentBooking extends AppCompatActivity {
    SQLiteDatabase myDb;
    SQLiteOpenHelper oh;

    EditText editFrom;
    EditText editTo;
    EditText editTime;
    EditText editDate;
    Button btnAddData;
    Button btnDelete;
    Button btnNext;

    //Take out if not working
    int counter = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_booking);
        oh = new BookingDatabase(this);

        editFrom = findViewById(R.id.editFrom);
        editTo = findViewById(R.id.editTo);
        editTime = findViewById(R.id.editTime);
        editDate = findViewById(R.id.editDate);
        btnAddData = findViewById(R.id.button_save);
        btnDelete = findViewById(R.id.button_delete);
        btnNext = findViewById(R.id.button_exit);


        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dep = editFrom.getText().toString();
                String des = editTo.getText().toString();
                String time = editTime.getText().toString();
                String date = editDate.getText().toString();
                myDb = oh.getWritableDatabase();

                if(TextUtils.isEmpty(editFrom.getText().toString())||TextUtils.isEmpty(editTo.getText().toString())||
                        TextUtils.isEmpty(editTime.getText().toString())||TextUtils.isEmpty(editDate.getText().toString())) {
                    Toast.makeText(StudentBooking.this, "Fields Incomplete", Toast.LENGTH_LONG).show();

                }else if(editFrom.getText().toString().equals(dep)&&((editTo.getText().toString().equals(des)&&(editTime.getText().toString().equals(time)
                        &&(editDate.getText().toString().equals(date) && (counter != 0)))))){
                    insertDetails(dep,des,time,date);
                   int dec = counter--;
                    Toast.makeText(StudentBooking.this,"Booking has been made.Seats available: " + dec,Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(StudentBooking.this,"Booking failed",Toast.LENGTH_LONG).show();
                }

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next();
            }
        });

    }

    public void insertDetails(String departure,String destination,String time,String date){
        ContentValues contentValues = new ContentValues();
        contentValues.put(BookingDatabase.COL_2, departure);
        contentValues.put(BookingDatabase.COL_3, destination);
        contentValues.put(BookingDatabase.COL_4, time);
        contentValues.put(BookingDatabase.COL_5, date);
        long id = myDb.insert(BookingDatabase.TABLE_NAME, null, contentValues);

    }

    public void back(){
        Intent back = new Intent(this,Timetable.class);
        startActivity(back);
    }

    public void next(){
        Intent next = new Intent(this,disabledStudentBooking.class);
        startActivity(next);
    }

}
