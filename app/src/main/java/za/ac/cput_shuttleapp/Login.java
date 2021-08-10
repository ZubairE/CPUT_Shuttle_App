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

    //Student Number and password used to login.
    //This is just for testing purposes
    //long validStudentNumber = 217203027;
    //String validPassword = "Breyton";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        openHelper = new RegistrationDatabase(this);
        db = openHelper.getReadableDatabase();

        studentNumber = findViewById(R.id.StudentNumber);
        studentPassword = findViewById(R.id.Password);
        chk3 = findViewById(R.id.chk3);
        btnLogin = findViewById(R.id.login);
        btnRegister = findViewById(R.id.register);

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

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stuNum = studentNumber.getText().toString();
                String userPw = studentPassword.getText().toString();
                //Take out this code statement if it it not working or mess up the functionality
                cursor = db.rawQuery("SELECT * FROM "+RegistrationDatabase.DB_TABLE_NAME+ " WHERE " + RegistrationDatabase.COLUMN_4+ "=? AND " + RegistrationDatabase.COLUMN_5+ "=?", new String[]{stuNum, userPw});

                if (TextUtils.isEmpty(studentNumber.getText().toString()) || TextUtils.isEmpty(studentPassword.getText().toString())) {
                    Toast.makeText(Login.this, "Fields Cannot Be Empty", Toast.LENGTH_LONG).show();
                } else if (cursor != null && cursor.getCount() > 0){
                    cursor.moveToNext();//take out if not work
                    Toast.makeText(Login.this, "Welcome to CPUT-Shuttle App!", Toast.LENGTH_LONG).show();
                    timetable();//Opens the timetable page after user entered login details

                } else {
                    Toast.makeText(Login.this, "Student does not exist.Register Instead?", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    //This is just a dummy link activity. Takes user to next page when login was success
    public void mainActivity() {
        Intent open = new Intent(this, MainActivity.class);
        startActivity(open);

    }

    public void timetable() {
        Intent timetable = new Intent(this, Timetable.class);
        startActivity(timetable);
    }
}
