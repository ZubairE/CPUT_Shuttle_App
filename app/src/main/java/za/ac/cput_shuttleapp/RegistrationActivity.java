package za.ac.cput_shuttleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {
    SQLiteOpenHelper db_OpenHelper; //Calls the database class
    SQLiteDatabase myDB; //Database
    EditText txtFirstName;
    EditText txtLastName;
    EditText studNumber;
    EditText password;
    EditText confirmPW;
    Button btnRegister;
    Button btnBack;

    long sn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        db_OpenHelper = new RegistrationDatabase(this);

        txtFirstName = findViewById(R.id.firstname);
        txtLastName = findViewById(R.id.lastname);
        studNumber = findViewById(R.id.stuNum);
        password = findViewById(R.id.pw);
        confirmPW = findViewById(R.id.cpw);
        btnRegister = findViewById(R.id.register);
        btnBack = findViewById(R.id.back);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = txtFirstName.getText().toString();
                String surname = txtLastName.getText().toString();
                String stuNum = studNumber.getText().toString();//Not suppose to be a string value
                String userPw = password.getText().toString();
                String confirmUserPw = confirmPW.getText().toString();
                myDB = db_OpenHelper.getWritableDatabase();//Take out if it not work

                if (TextUtils.isEmpty(txtFirstName.getText().toString()) || TextUtils.isEmpty(txtLastName.getText().toString()) ||
                        TextUtils.isEmpty(studNumber.getText().toString()) || TextUtils.isEmpty(password.getText().toString()) ||
                        TextUtils.isEmpty(confirmPW.getText().toString())) {
                    Toast.makeText(RegistrationActivity.this, "Please complete all details", Toast.LENGTH_LONG).show();
                //Remove else-if statement if it is not working or cause problems.
                }else if(txtFirstName.getText().toString().equals(name)||txtLastName.getText().toString().equals(surname)||
                        studNumber.getText().toString().equals(stuNum)||password.getText().toString().equals(userPw)||
                        confirmPW.getText().toString().equals(confirmUserPw)){
                        insertData(name,surname,stuNum,userPw,confirmUserPw);//Take out if not working.Added on 2nd August 2021
                        Toast.makeText(RegistrationActivity.this,"Registration successfully",Toast.LENGTH_LONG).show();

                    //Still need code as soon as database is completed
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
    public void insertData(String studName,String studSurname,String studNumber,String password,String studConfrmPw){
    //This method was added on the 30th of July 2021. Must take it out if it not works.
        ContentValues cv = new ContentValues();
        cv.put(RegistrationDatabase.COLUMN_2,studName);
        cv.put(RegistrationDatabase.COLUMN_3,studSurname);
        cv.put(RegistrationDatabase.COLUMN_4,studNumber);
        cv.put(RegistrationDatabase.COLUMN_5,password);
        cv.put(RegistrationDatabase.COLUMN_6,studConfrmPw);
        long id = myDB.insert(RegistrationDatabase.DB_TABLE_NAME,null, cv);
        //Continue here.
    }
}


