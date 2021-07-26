package za.ac.cput_shuttleapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    //This is just a dummy activity take takes the user from the login page to this page
   // Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* btnBack = findViewById(R.id.back);

        btnBack.setOnClickListener(new View.OnClickListener(){

        public void onClick(View v){
                goBack();
        }
        });
        }
    public void goBack(){
        Intent back = new Intent(this, Login.class);
        startActivity(back);*/
    }
}