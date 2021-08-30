package za.ac.cput_shuttleapp;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

public class StudentBooking extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editFrom, editTo, editTime, editDate;
    Button btnAddData;
    Button btnViewAll;
    Button btnUpdate;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_booking);

        myDb = new DatabaseHelper(this);
        editFrom = (EditText) findViewById(R.id.editFrom);
        editTo = (EditText) findViewById(R.id.editTo);
        editTime = (EditText) findViewById(R.id.editTime);
        editDate = (EditText) findViewById(R.id.editDate);
        btnAddData = (Button) findViewById(R.id.button_save);

        btnDelete = (Button) findViewById(R.id.button_delete);
        AddData();
        UpdateData();
        DeleteData();

    }

    public void DeleteData() {
        btnDelete.setOnClickListener(
                v -> {
                    Integer deletedRows = myDb.deleteData(editFrom.getText().toString());
                    if (deletedRows > 0) {
                        Toast.makeText(StudentBooking.this, "Data deleted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(StudentBooking.this, "Data not deleted", Toast.LENGTH_SHORT).show();
                    }
                }
        );

    }

    public void UpdateData() {

        btnUpdate.setOnClickListener(
                v -> {
                    boolean IsUpdate = myDb.updateData(editFrom.getText().toString(), editTo.getText().toString(), editTime.getText().toString(), editDate.getText().toString());
                    if (IsUpdate) {
                        Toast.makeText(StudentBooking.this, "Data updated", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(StudentBooking.this, "Data not updated", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    public void AddData() {
        btnAddData.setOnClickListener(
                v -> {
                    boolean Isinserted = myDb.insertData(editFrom.getText().toString(),
                            editTo.getText().toString(),
                            editTime.getText().toString(),editDate.getText().toString());
                    if (Isinserted) {
                        Toast.makeText(StudentBooking.this, "Data inserted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(StudentBooking.this, "Data not inserted", Toast.LENGTH_SHORT).show();
                    }
                }


        );
    }

    }
