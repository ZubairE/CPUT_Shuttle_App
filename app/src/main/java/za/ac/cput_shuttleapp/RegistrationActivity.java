package za.ac.cput_shuttleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegistrationActivity extends AppCompatActivity {

  private EditText eRegNumber;
  private EditText eRegPassword;
  private Button eRegister;
  private Button eCancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        eRegNumber = findViewById(R.id.etNumber);
        eRegPassword = findViewById(R.id.etRegPassword);
        eRegister = findViewById(R.id.btnRegstrate);
        eCancel = findViewById(R.id.btnCancels);

        eRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String regStudentnumber = eRegNumber.getText().toString();
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