package za.ac.cput_shuttleapp;
/*Breyton Ernstzen - 217203027*/

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    Cursor cursor;

    EditText studentNumber;
    EditText studentPassword;
    CheckBox chk3;
    Button btnLogin;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        openHelper = new DataBaseHelper(this);
        db = openHelper.getReadableDatabase();

        studentNumber = findViewById(R.id.StudentNumber);
        studentPassword = findViewById(R.id.Password);
        chk3 = findViewById(R.id.chk3);
        btnLogin = findViewById(R.id.login);
        btnRegister = findViewById(R.id.register);

        //Takes user to the registartion page. When button is clicked
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

        //Login button
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stuNum = studentNumber.getText().toString();
                String userPw = studentPassword.getText().toString();
                cursor = db.rawQuery("SELECT * FROM "+DataBaseHelper.MY_DB_TABLE_NAME+ " WHERE " + DataBaseHelper.COLUMN_4+ "=? AND " + DataBaseHelper.COLUMN_5+ "=?", new String[]{stuNum, userPw});

                //If user leave's a field empty, a pop-up message will display that indicates that the user should complete all fields
                if (TextUtils.isEmpty(studentNumber.getText().toString()) || TextUtils.isEmpty(studentPassword.getText().toString())) {
                    Toast.makeText(Login.this, "Fields Cannot Be Empty", Toast.LENGTH_LONG).show();

                //Goes through database and checks if user exists
                } else if (cursor != null && cursor.getCount() > 0){
                    cursor.moveToNext();//take out if not work
                    Toast.makeText(Login.this, "Welcome to CPUT-Shuttle App!", Toast.LENGTH_LONG).show();
                    timetable();//Opens the timetable page after user entered login details

                //If user does not exist
                } else {
                    Toast.makeText(Login.this, "Student does not exist.Register Instead?", Toast.LENGTH_LONG).show();
                }
            }
        });

        //Toggle password visibility(show/hide) password
        chk3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    studentPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

                } else {
                    studentPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
    }

    //Takes user to timetable page
    public void timetable() {
        Intent timetable = new Intent(this, Timetable.class);
        startActivity(timetable);
        finish();
    }
}
