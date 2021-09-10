package za.ac.cput_shuttleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ShuttleTimes extends AppCompatActivity {

    // Array of strings...
    ListView simpleList;
    String timeList[] = {"7:30 am", "8:00 am", "8:30 am", "9:00 am", "10:00 am", "11:00 am",
            "11:30 am","12:30 pm", "14:00 pm","15:00 pm","16:00 pm","17:00 pm"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shuttle_times);
        simpleList = (ListView)findViewById(R.id.simpleListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_shuttle_times, R.id.textView, timeList);
        simpleList.setAdapter(arrayAdapter);

    }
}