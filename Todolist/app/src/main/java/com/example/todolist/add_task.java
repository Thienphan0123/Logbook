package com.example.todolist;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
}