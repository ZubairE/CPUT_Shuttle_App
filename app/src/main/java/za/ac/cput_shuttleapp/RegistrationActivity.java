package za.ac.cput_shuttleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {
    SQLiteOpenHelper db_OpenHelper; //Calls the database class
    SQLiteDatabase myDB; //Database

    EditText txtFirstName;
    EditText txtLastName;
    EditText studNumber;
    EditText password;
    CheckBox chk;
    EditText cellNo;
    Button btnRegister;
    Button btnBack;

    //Take out if not working
    public static final Pattern PASSWORD_PATTERN = Pattern.compile("^" +
            "(?=.*[0-9])" +             //The pw requires at least one digit
            "(?=.*[a-z])" +             //The pw requires at least one small letter
            "(?=.*[A-Z])" +             //The pw requires at least one capital letter
            "(?=.*[@#$%^&+=!_])" +        //The pw requires at least one special character
            "(?=\\S+$)" +               //No white spaces allowed
            ".{8,15}" +                   //The password requires at least 8 characters
            "$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        db_OpenHelper = new RegistrationDatabase(this);

        txtFirstName = findViewById(R.id.firstname);
        txtLastName = findViewById(R.id.lastname);
        studNumber = findViewById(R.id.stuNum);
        password = findViewById(R.id.pw);
        chk = findViewById(R.id.chk);
        cellNo = findViewById(R.id.cpw);
        btnRegister = findViewById(R.id.register);
        btnBack = findViewById(R.id.back);

        chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                        password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

                }else{
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDB = db_OpenHelper.getWritableDatabase();//Take out if it not work
                String name = txtFirstName.getText().toString();
                String surname = txtLastName.getText().toString();
                String stuNum = studNumber.getText().toString();//Not suppose to be a string value
                String userPw = password.getText().toString();
                String cellNum = cellNo.getText().toString();


                if (TextUtils.isEmpty(txtFirstName.getText().toString()) || TextUtils.isEmpty(txtLastName.getText().toString()) ||
                        TextUtils.isEmpty(studNumber.getText().toString()) || TextUtils.isEmpty(password.getText().toString()) ||
                        TextUtils.isEmpty(cellNo.getText().toString())) {
                    Toast.makeText(RegistrationActivity.this, "Please complete all details", Toast.LENGTH_LONG).show();

                } else if (!PASSWORD_PATTERN.matcher(userPw).matches()) {
                    password.setError("Password must be at least 8 characters long.Requires at least one digit, one lowercase letter, one uppercase, and one special character");

                } else if (txtFirstName.getText().toString().equals(name) || txtLastName.getText().toString().equals(surname) ||
                        studNumber.getText().toString().equals(stuNum) || password.getText().toString().equals(userPw) ||
                        cellNo.getText().toString().equals(cellNum)) {
                    insertData(name,surname,stuNum,userPw,cellNum);
                    Toast.makeText(RegistrationActivity.this, "Registration Success", Toast.LENGTH_LONG).show();
                    login();
                    //Toast.makeText(RegistrationActivity.this,"Password does not match",Toast.LENGTH_LONG).show();

                }
            }
        });

        btnBack = findViewById(R.id.back);
        btnBack.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                goBack();
            }
        });
    }

    public void goBack() {
        Intent back = new Intent(this, Login.class);
        startActivity(back);
    }

    public void timetable() {
        Intent timetable = new Intent(this, Timetable.class);
        startActivity(timetable);
    }

    public void login() {
        Intent loginPage = new Intent(this, Login.class);
        startActivity(loginPage);
    }
    //public 'void' insertData is the actual method.
    public void insertData(String studName, String studSurname, String studNumber, String password, String cellNumber) {
        //This method was added on the 30th of July 2021. Must take it out if it not works.
        ContentValues cv = new ContentValues();
        cv.put(RegistrationDatabase.COLUMN_2, studName);
        cv.put(RegistrationDatabase.COLUMN_3, studSurname);
        cv.put(RegistrationDatabase.COLUMN_4, studNumber);
        cv.put(RegistrationDatabase.COLUMN_5, password);
        cv.put(RegistrationDatabase.COLUMN_6, cellNumber);
        long id = myDB.insert(RegistrationDatabase.DB_TABLE_NAME, null, cv);
        //Continue here.
        //Take this statement out if not working.Added on 6th August 2021

    }

        }










