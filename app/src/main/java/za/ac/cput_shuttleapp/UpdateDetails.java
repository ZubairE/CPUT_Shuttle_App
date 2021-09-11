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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class UpdateDetails extends AppCompatActivity {
    Button btnUpdate;
    Button btnCancel;
    SQLiteDatabase db;
    SQLiteOpenHelper myDB_Helper;
    EditText name;
    EditText surname;
    EditText cell;
    EditText stuNo;
    EditText newPassword;
    CheckBox chkPw;

    public static final Pattern PASSWORD_PATTERN = Pattern.compile("^" +
            "(?=.*[0-9])" +             //The pw requires at least one digit
            "(?=.*[a-z])" +             //The pw requires at least one small letter
            "(?=.*[A-Z])" +             //The pw requires at least one capital letter
            "(?=.*[@#$%^&+=!_])" +        //The pw requires at least one special character
            "(?=\\S+$)" +               //No white spaces allowed
            ".{8,15}" +                   //The password requires at least 8 characters
            "$");

    public static final Pattern CELL_PATTERN = Pattern.compile("^" +
            "(?=.*[0-9])" +
            "(?=\\S+$)" +
            ".{10,10}" +
            "$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details);
        myDB_Helper = new DataBaseHelper(this);

        chkPw = findViewById(R.id.newChk);
        btnUpdate = findViewById(R.id.updte);
        btnCancel = findViewById(R.id.cnclUpdate);
        name = findViewById(R.id.updateName);
        surname = findViewById(R.id.updateLastname);
        newPassword = findViewById(R.id.newPw);
        cell = findViewById(R.id.updateCell);
        stuNo = findViewById(R.id.stuNumber);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname = name.getText().toString();
                String lname = surname.getText().toString();
                String cellNo = cell.getText().toString();
                String newPw = newPassword.getText().toString();
                String studNo = stuNo.getText().toString();
                db = myDB_Helper.getWritableDatabase();
                Cursor myCursor = db.rawQuery("SELECT * FROM " + DataBaseHelper.MY_DB_TABLE_NAME + " WHERE " + DataBaseHelper.COLUMN_4 + "= ?", new String[]{studNo});

                if(TextUtils.isEmpty(name.getText().toString())||TextUtils.isEmpty(surname.getText().toString())||
                        TextUtils.isEmpty(cell.getText().toString())||TextUtils.isEmpty(stuNo.getText().toString())||
                        TextUtils.isEmpty(newPassword.getText().toString())) {
                    Toast.makeText(UpdateDetails.this, "Complete All Details", Toast.LENGTH_LONG).show();

                }else if(myCursor != null && myCursor.getCount() > 0 && CELL_PATTERN.matcher(cellNo).matches() &&
                        PASSWORD_PATTERN.matcher(newPw).matches()) {
                    myCursor.moveToNext();
                    Toast.makeText(UpdateDetails.this, "Details Updated", Toast.LENGTH_LONG).show();
                    updateStudent(fname, lname, studNo, newPw,cellNo);

                }else if(!CELL_PATTERN.matcher(cellNo).matches()) {
                    cell.setError("Number must contain 10 digits and starts with '0'");

                }else if(!PASSWORD_PATTERN.matcher(newPw).matches()){
                    newPassword.setError("Password must be at least 8 characters long.Requires at least one digit, one lowercase letter, one uppercase, and one special character");

                }else{
                    Toast.makeText(UpdateDetails.this,"Invalid Student Number", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });

        //Toggle password visibility(show/hide password)
        chkPw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    newPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

                } else {
                    newPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
        }
    public void cancel() {
        Intent cancel = new Intent(this, RegistrationActivity.class);
        startActivity(cancel);
        //finish();

    }

    public Integer updateStudent(String name,String surname,String studentNo,String pw, String cell){
        //db = myDB_Helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DataBaseHelper.COLUMN_2,name);
        cv.put(DataBaseHelper.COLUMN_3,surname);
        cv.put(DataBaseHelper.COLUMN_4,studentNo);
        cv.put(DataBaseHelper.COLUMN_5, pw);
        cv.put(DataBaseHelper.COLUMN_6,cell);
        return db.update(DataBaseHelper.MY_DB_TABLE_NAME,cv,DataBaseHelper.COLUMN_4+ "= ?",new String[]{studentNo});


    }
}