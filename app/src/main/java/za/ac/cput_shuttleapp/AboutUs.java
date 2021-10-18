package za.ac.cput_shuttleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AboutUs extends AppCompatActivity {
    //Variables for buttons used
    Button btnAUBck;
    Button btnAUExt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        //Declaring the variables
        btnAUBck = findViewById(R.id.AboutUsBack);
        btnAUExt = findViewById(R.id.AboutUsExit);

        //Onclick listener for back button
        //Takes user to previous page
        btnAUBck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutUs.this, disabledStudentBooking.class);
                startActivity(intent);
            }
        });

        //Onclick listener for the exit button
        //Exit the mobile application
        btnAUExt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });
    }
}