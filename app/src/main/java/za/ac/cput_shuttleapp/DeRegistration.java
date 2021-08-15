package za.ac.cput_shuttleapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DeRegistration extends AppCompatActivity {
    SQLiteDatabase db;
    SQLiteOpenHelper myDB_Helper;
    Button deRstr;
    Button back;
    EditText stdNum;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        deRstr = findViewById(R.id.deRegister);
        back = findViewById(R.id.back);
        stdNum = findViewById(R.id.stuNum);

        deRstr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stuNum = stdNum.getText().toString();
                db = myDB_Helper.getWritableDatabase();

                if(TextUtils.isEmpty(stdNum.getText().toString())){
                    Toast.makeText(DeRegistration.this,"Please complete the details",Toast.LENGTH_LONG).show();
                    //Needs code
                }else if(stdNum.getText().toString().equals(stuNum)){
                        deletStudent(stuNum);
                        Toast.makeText(DeRegistration.this, "De-Registration Success",Toast.LENGTH_LONG).show();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

        }
    public void goBack(){
        Intent back = new Intent(this, RegistrationActivity.class);
        startActivity(back);
    }
    public void deletStudent(String studentNumber){
       // db = myDB_Helper.getWritableDatabase();
        db.delete(DataBaseHelper.MY_DB_TABLE_NAME, DataBaseHelper.COLUMN_4,new String[]{studentNumber});
    }
}
