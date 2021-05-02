package za.ac.cput_shuttleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {
    EditText studentNumber,studentPassword;
    Button btnLogin,btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        studentNumber = findViewById(R.id.editTextPhone);
        studentPassword = findViewById(R.id.editTextTextPassword);
        btnLogin = findViewById(R.id.login);
        btnRegister = findViewById(R.id.register);
    }
}