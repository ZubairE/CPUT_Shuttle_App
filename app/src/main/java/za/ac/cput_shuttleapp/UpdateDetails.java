package za.ac.cput_shuttleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateDetails extends AppCompatActivity {
    Button btnUpdate;
    Button btnCancel;
    SQLiteDatabase db;
    SQLiteOpenHelper myDB_Helper;
    EditText name;
    EditText surname;
    EditText cell;
    EditText stuNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details);
        myDB_Helper = new DataBaseHelper(this);

        btnUpdate = findViewById(R.id.updte);
        btnCancel = findViewById(R.id.cnclUpdate);
        name = findViewById(R.id.updateName);
        surname = findViewById(R.id.updateLastname);
        cell = findViewById(R.id.updateCell);
        stuNo = findViewById(R.id.stuNumber);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname = name.getText().toString();
                String lname = surname.getText().toString();
                String cellNo = cell.getText().toString();
                String studNo = stuNo.getText().toString();
                db = myDB_Helper.getWritableDatabase();
                Cursor myCursor = db.rawQuery("SELECT * FROM " + DataBaseHelper.MY_DB_TABLE_NAME + " WHERE " + DataBaseHelper.COLUMN_4 + "= ?", new String[]{studNo});

                if(TextUtils.isEmpty(name.getText().toString())||TextUtils.isEmpty(surname.getText().toString())||
                        TextUtils.isEmpty(cell.getText().toString())||TextUtils.isEmpty(stuNo.getText().toString())) {
                    Toast.makeText(UpdateDetails.this, "Complete All Details", Toast.LENGTH_LONG).show();

                }else if(myCursor != null && myCursor.getCount() > 0){
                    myCursor.moveToNext();
                    Toast.makeText(UpdateDetails.this,"Details Updated",Toast.LENGTH_LONG).show();
                    updateStudent(fname,lname,studNo,cellNo);

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
        }
    public void cancel() {
        Intent cancel = new Intent(this, RegistrationActivity.class);
        startActivity(cancel);
        //finish();

    }

    public Integer updateStudent(String name,String surname,String studentNo,String cell){
        //db = myDB_Helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DataBaseHelper.COLUMN_2,name);
        cv.put(DataBaseHelper.COLUMN_3,surname);
        cv.put(DataBaseHelper.COLUMN_4,studentNo);
        cv.put(DataBaseHelper.COLUMN_6,cell);
        return db.update(DataBaseHelper.MY_DB_TABLE_NAME,cv,DataBaseHelper.COLUMN_4+ "= ?",new String[]{studentNo});


    }
}