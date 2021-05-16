package za.ac.cput_shuttleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText studentNumber;
    EditText studentPassword;
    Button btnLogin;
    Button btnRegister;

    long validStudentNumber = 217203027;
    String validPassword = "Breyton";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        studentNumber = findViewById(R.id.StudentNumber);
        studentPassword = findViewById(R.id.Password);
        btnLogin = findViewById(R.id.login);
        btnRegister = findViewById(R.id.register);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(studentNumber.getText().toString()) || TextUtils.isEmpty(studentPassword.getText().toString())){
                    Toast.makeText(Login.this, "Fields Cannot Be Empty", Toast.LENGTH_LONG).show();
                }else if(studentNumber.getText().toString().equals(validStudentNumber) || studentPassword.getText().toString().equals(validPassword)){
                        Toast.makeText(Login.this, "Welcome to CPUT-Shuttle App!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(Login.this, "Student does not exist.Register Instead?",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}