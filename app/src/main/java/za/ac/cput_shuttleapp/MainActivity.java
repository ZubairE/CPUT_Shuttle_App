package za.ac.cput_shuttleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText eRegNumber;
    private EditText eRegPassword;
    private EditText eRegID;
    private Button eRegister;
    private Button eCancel;
    private Button eAdd;
    private Button eView;
    private Button eUpdate;
    DatabaseHandling Db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Db= new DatabaseHandling(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();



        setContentView(R.layout.activity_registration);

        eRegNumber = findViewById(R.id.etNumber);
        eRegPassword = findViewById(R.id.etRegPassword);
        eRegID = findViewById(R.id.etID);
        eRegister = findViewById(R.id.btnRegstrate);
        eCancel = findViewById(R.id.btnCancels);
        eAdd = findViewById(R.id. btnAdd);
        eView = findViewById(R.id. btnView);
        eUpdate = findViewById(R.id. btnUpdate);

        eRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String regStudentNumber = eRegNumber.getText().toString();
                String regPassword = eRegPassword.getText().toString();
                String regID = eRegID.getText().toString();
            }
        });

        eCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


            eAdd.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            boolean Isinserted= Db.insertData(eRegNumber.getText().toString(),
                                    eRegPassword.getText().toString());
                            if (Isinserted==true)
                            {
                                Toast.makeText(MainActivity.this, "Data inserted", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(MainActivity.this, "Data not inserted", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }


            );


            eView.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Cursor res= Db.getAllData();
                            if(res.getCount()==0){
                                showMessage("Error","Nothing found");
                                return;
                            }

                            StringBuffer buffer= new StringBuffer();
                            while(res.moveToNext()) {
                                buffer.append("ID:"+res.getString(0)+"\n");
                                buffer.append("Number:"+res.getString(1)+"\n");
                                buffer.append("Password:"+res.getString(2)+"\n");

                            }
                            showMessage("Data",buffer.toString());


                        }
                    }

            );


            eUpdate.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            boolean IsUpdate= Db.updateData(eRegID.getText().toString(), eRegNumber.getText().toString(),eRegPassword.getText().toString());
                            if(IsUpdate==true)
                            {
                                Toast.makeText(MainActivity.this, "Data updated", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(MainActivity.this, "Data not updated", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
            );







        }

    private void showMessage(String title,String Message){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();


    }

}