package com.example.todolist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {


    public static ArrayList<Task> taskList = new ArrayList<Task>();
    private DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DatabaseHelper(this);
    }

    public void onClickAdd(View v) {
        Intent i = new Intent(getApplicationContext(), add_task.class);
        startActivity(i);
    }

    protected void onStart() {
        super.onStart();
        ListView lv = findViewById(R.id.listViewTask);
        TaskAdapter adapter = new TaskAdapter( this,taskList);
        lv.setAdapter(adapter);
    }

    public class TaskAdapter extends ArrayAdapter<Task> {

        public TaskAdapter(Context context, ArrayList<Task> tasks) {
            super(context, 0, tasks);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Task t = getItem(position);
            if (convertView == null)
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.task_item, parent, false);
            TextView tvTaskName = (TextView) convertView.findViewById(R.id.Text_name);
            TextView tvDeadline = (TextView) convertView.findViewById(R.id.text_deadline);
            TextView tvDuration = (TextView) convertView.findViewById(R.id.text_date);
            TextView tvDescriptions = (TextView) convertView.findViewById(R.id.text_Description);
            tvTaskName.setText(t.name);
            tvDeadline.setText(t.deadline.toString().substring(0, 10));
            tvDuration.setText(String.valueOf(t.duration));
            tvDescriptions.setText(String.valueOf(t.descriptions));
            return convertView;
        }

    }
}