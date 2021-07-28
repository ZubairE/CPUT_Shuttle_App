package za.ac.cput_shuttleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {
    EditText txtFirstName;
    EditText txtLastName;
    EditText studNumber;
    EditText password;
    EditText confirmPW;
    Button btnRegister;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

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

                if (TextUtils.isEmpty(txtFirstName.getText().toString()) || TextUtils.isEmpty(txtLastName.getText().toString()) ||
                        TextUtils.isEmpty(studNumber.getText().toString()) || TextUtils.isEmpty(password.getText().toString()) ||
                        TextUtils.isEmpty(confirmPW.getText().toString())) {
                    Toast.makeText(RegistrationActivity.this, "Please complete all details", Toast.LENGTH_LONG).show();
                }//Still need code as soon as database is completed

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
}


