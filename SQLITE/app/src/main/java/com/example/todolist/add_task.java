package com.example.todolist;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class add_task extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        DatePicker dp = findViewById(R.id.dpDeadline);
        Calendar c = Calendar.getInstance();
        dp.init(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), null);
        Button add_Button = (Button) findViewById(R.id.addButton);
        add_Button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                try {
                    onClickAddTask(view);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                saveDetails();
            }
        });
        }
    public void onClickAddTask(View v) throws ParseException {
        String n, des;
        Date dl;
        int d;
        n = ((EditText) findViewById(R.id.editTextName)).getText().toString();
        des = ((EditText) findViewById(R.id.editTextTextMultiLine)).getText().toString();
        DatePicker dp = (DatePicker) findViewById(R.id.dpDeadline);
        d = Integer.valueOf(((EditText) findViewById(R.id.editTextDuration)).getText().toString());
        String dateText = String.valueOf(dp.getDayOfMonth()) + "/" +
                        String.valueOf(dp.getMonth() + 1) + "/" +
                        String.valueOf(dp.getYear());
        Task t = new Task (n, new SimpleDateFormat("dd/MM/yyyy").parse(dateText), d, des);
        MainActivity.taskList.add(t);
        Toast.makeText(getApplicationContext(),  "A task is just created", Toast.LENGTH_LONG).show();
    }

    private void saveDetails(){
        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
        EditText nameTxt = (EditText) findViewById(R.id.editTextName);
        DatePicker deadlinePicker = findViewById(R.id.dpDeadline);;
        EditText durationTxt = (EditText) findViewById(R.id.editTextDuration);
        EditText descriptionTxt = (EditText) findViewById(R.id.editTextTextMultiLine);

        String name = nameTxt.getText().toString();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, deadlinePicker.getYear());
        calendar.set(Calendar.MONTH, deadlinePicker.getMonth());
        calendar.set(Calendar.DAY_OF_MONTH, deadlinePicker.getDayOfMonth());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(calendar.getTime());
        int duration = Integer.parseInt(durationTxt.getText().toString());
        String description = descriptionTxt.getText().toString();
        long taskId = dbHelper.insertDetails(name, formattedDate, duration, description);
        Toast.makeText(this, "Task has been created with id: " + taskId, Toast.LENGTH_LONG).show();
    }
}