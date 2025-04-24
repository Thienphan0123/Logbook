package com.example.unitconverter;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;


public class MainActivity extends AppCompatActivity {
    EditText output;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        output = findViewById(R.id.value2);
    }

    public void onConvertClick(View v) {


        Spinner sp1 = (Spinner) findViewById(R.id.unit1);
        String choice1 = sp1.getSelectedItem().toString();
        Spinner sp2 = (Spinner) findViewById(R.id.unit2);
        String choice2 = sp2.getSelectedItem().toString();
        EditText ed1 = (EditText) findViewById(R.id.value1);
        double value1 = Double.parseDouble(ed1.getText().toString());
        double value2 = 0;
        switch (choice1) {
            case "Meter":
                switch (choice2) {
                    case "Meter":
                        value2 = value1;
                        break;
                    case "Millimeter":
                        value2 = value1 * 1000;
                        break;
                    case "Mile":
                        value2 = value1 * 0.000621371192;
                        break;
                    case "Foot":
                        value2 = value1 * 3.2808399;
                        break;
                }
                break;
            case "Millimeter":
                switch (choice2) {
                    case "Meter": value2 = value1 / 1000;
                    break;
                    case "Millimeter": value2 = value1;
                    break;
                    case "Mile": value2 = value1 * 6.2137e-7;
                    break;
                    case "Foot": value2 = value1 * 0.00328084;
                    break;
                }
                break;

            case "Mile":
                switch (choice2) {
                    case "Meter": value2 = value1 * 1609.34;
                    break;
                    case "Millimeter": value2 = value1 * 1.609e+6;
                    break;
                    case "Mile": value2 = value1;
                    break;
                    case "Foot": value2 = value1 * 5280;
                    break;
                }
                break;

            case "Foot":
                switch (choice2) {
                    case "Meter": value2 = value1 * 0.3048;
                    break;
                    case "Millimeter": value2 = value1 * 304.8;
                    break;
                    case "Mile": value2 = value1 * 0.000189394;
                    break;
                    case "Foot": value2 = value1;
                    break;
                }
                break;

        }
        output.setText(String.valueOf(value2));
    }}