package za.ac.cput_shuttleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

  private EditText eRegNumber;
  private EditText eRegPassword;
  private EditText eRegID;
  private Button eRegister;
  private Button eCancel;
  private Button eAdd;
    private Button eView;
    private Button eUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

            }
        });

        eCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }
}



    //DatabaseHandling Db;
    //private EditText eRegNumber;
//    private EditText eRegPassword;
 //   private Button eRegister;
  //  private Button eCancel;
  //  private Button eAdd;
  //  private Button eView;
 //   private Button eUpdate;
//    Button btnAddData;
 //   Button btnView;
 //   Button btnUpdate;



//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
 //       super.onCreate(savedInstanceState);
 //       Db= new DatabaseHandling(this);
 //       requestWindowFeature(Window.FEATURE_NO_TITLE);
       /* this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_registration);
*/

   /*     eRegNumber = findViewById(R.id.etNumber);
        eRegPassword = findViewById(R.id.etRegPassword);
        eRegister = findViewById(R.id.btnRegstrate);
        eCancel = findViewById(R.id.btnCancels);
        eAdd = findViewById(R.id. btnAdd);
        eView = findViewById(R.id. btnView);
        eUpdate = findViewById(R.id. btnUpdate);

        AddData();
        viewAll();
        UpdateData();




    }

    private void UpdateData() {
    }

    private void viewAll() {
    }

    private void AddData() {
        btnAddData.setOnClickListener(
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
    }*/