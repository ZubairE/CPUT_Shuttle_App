package za.ac.cput_shuttleapp;
//Breyton Ernstzen - 217203027

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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {
    SQLiteOpenHelper db_OpenHelper; //Calls the database class
    SQLiteDatabase myDB; //Database

    EditText txtFirstName;
    EditText txtLastName;
    EditText studNumber;
    EditText password;
    TextView txtLink;//Take out if not working
    CheckBox chk;
    EditText cellNo;
    Button btnRegister;
    Button btnBack;

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
        db_OpenHelper = new DataBaseHelper(this);

        txtFirstName = findViewById(R.id.firstname);
        txtLastName = findViewById(R.id.lastname);
        studNumber = findViewById(R.id.stuNum);
        password = findViewById(R.id.pw);
        chk = findViewById(R.id.chk);
        cellNo = findViewById(R.id.cpw);
        btnRegister = findViewById(R.id.register);
        btnBack = findViewById(R.id.back);
        txtLink = findViewById(R.id.linkDeregister);//Take out if not working


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = txtFirstName.getText().toString();
                String surname = txtLastName.getText().toString();
                String stuNum = studNumber.getText().toString();
                String userPw = password.getText().toString();
                String cellNum = cellNo.getText().toString();
                myDB = db_OpenHelper.getWritableDatabase();
                Cursor myCursor = myDB.rawQuery("SELECT * FROM " + DataBaseHelper.MY_DB_TABLE_NAME+ " WHERE " + DataBaseHelper.COLUMN_4+ "= ?",new String[]{stuNum});

                //Requires that all fields must be completed
                if (TextUtils.isEmpty(txtFirstName.getText().toString()) || TextUtils.isEmpty(txtLastName.getText().toString()) ||
                        TextUtils.isEmpty(studNumber.getText().toString()) || TextUtils.isEmpty(password.getText().toString()) ||
                        TextUtils.isEmpty(cellNo.getText().toString())) {
                    Toast.makeText(RegistrationActivity.this, "Please complete all details", Toast.LENGTH_LONG).show();

                    //Checks if user exist
                }else if(myCursor.getCount() > 0){
                    Toast.makeText(RegistrationActivity.this,"Student number already in use",Toast.LENGTH_LONG).show();
                    //*************************

                }else if(PASSWORD_PATTERN.matcher(userPw).matches()) {
                    insertData(name, surname, stuNum, userPw, cellNum);
                    Toast.makeText(RegistrationActivity.this, "Registration Success", Toast.LENGTH_LONG).show();
                    login();

                }else if(!PASSWORD_PATTERN.matcher(userPw).matches()) {
                    password.setError("Password must be at least 8 characters long.Requires at least one digit, one lowercase letter, one uppercase, and one special character");


                  /*} else if (txtFirstName.getText().toString().equals(name) || txtLastName.getText().toString().equals(surname) ||
                        studNumber.getText().toString().equals(stuNum) || password.getText().toString().equals(userPw) ||
                        cellNo.getText().toString().equals(cellNum)) {
                    insertData(name, surname, stuNum, userPw, cellNum);
                    Toast.makeText(RegistrationActivity.this, "Registration Success", Toast.LENGTH_LONG).show();
                    //timetable();
                    login();*/

                }
            }
        });

        btnBack = findViewById(R.id.back);
        btnBack.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                goBack();
            }
        });

        chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

                } else {
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

        txtLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent toDeRegistration = new Intent(RegistrationActivity.this, DeRegister.class);
                startActivity(toDeRegistration);
                //deRegister();
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
    //Take out this method if it is not working or cause probles
    /*public void deRegister() {
        Intent toDeRegistration = new Intent(this, DeRegistration.class);
        startActivity(toDeRegistration);*/

        //Fix this
    //}

    //public 'void' insertData is the actual method.
    public void insertData(String name, String surname, String stuNum, String userPw, String cellNum) {
        //This method was added on the 30th of July 2021. Must take it out if it not works.
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.COLUMN_2, name);
        contentValues.put(DataBaseHelper.COLUMN_3, surname);
        contentValues.put(DataBaseHelper.COLUMN_4, stuNum);
        contentValues.put(DataBaseHelper.COLUMN_5, userPw);
        contentValues.put(DataBaseHelper.COLUMN_6, cellNum);
        long id = myDB.insert(DataBaseHelper.MY_DB_TABLE_NAME, null, contentValues);
        }

}











