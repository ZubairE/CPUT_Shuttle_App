package za.ac.cput_shuttleapp;
//Breyton Ernszten - 217203027
import androidx.appcompat.app.AppCompatActivity;

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

public class DeRegister extends AppCompatActivity {
    SQLiteDatabase db;
    SQLiteOpenHelper myDB_Helper;
    DataBaseHelper dbh;
    Button deRstr;
    Button back;
    EditText stdNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_de_register);
        myDB_Helper = new DataBaseHelper(this);

        deRstr = findViewById(R.id.deRegister);
        back = findViewById(R.id.goBack);
        stdNum = findViewById(R.id.sn);

        deRstr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stuNum = stdNum.getText().toString();

                //If user enters nothing and press the button a pop-up message should appear.
                if(TextUtils.isEmpty(stdNum.getText().toString())) {
                    Toast.makeText(DeRegister.this, "Please complete the details", Toast.LENGTH_LONG).show();

                //If student enters student number.It removes his/her data from the database
                }else if(stdNum.getText().toString().equals(stuNum)) {
                    deletStudent(stuNum);
                    Toast.makeText(DeRegister.this, "De-Registration Success", Toast.LENGTH_LONG).show();

                }else{
                    stdNum.setError("Student number does not exist");
                }
            }
        });

        //This method calls the "goBack()" method and takes user back to registration page
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

    }

    //Takes student back to registration page
    public void goBack(){
        Intent back = new Intent(this, RegistrationActivity.class);
        startActivity(back);

    }
    //Deletes student record from the table
    public Integer deletStudent(String student_Number){
        //String stuNum = stdNum.getText().toString();
        db = myDB_Helper.getWritableDatabase();
        return db.delete(DataBaseHelper.MY_DB_TABLE_NAME,DataBaseHelper.COLUMN_4+ "= ?", new String[]{student_Number});


    }

    }
