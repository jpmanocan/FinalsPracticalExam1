package com.manocan.johnphillipfinalspracticalexam;

import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity
{
    EditText firstName, lastName, examOne, examTwo;
    String fn, ln;
    String eo, et, addTwo, compute;
    TextView ave;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        examOne = findViewById(R.id.examOne);
        examTwo = findViewById(R.id.examTwo);
        ave = findViewById(R.id.average);
    }

    public void computeAverage (View v)
    {
        fn = firstName.getText().toString();
        ln = lastName.getText().toString();
        eo = examOne.getText().toString();
        et = examTwo.getText().toString();

        double average = Integer.parseInt(eo) + Integer.parseInt(et);
        average = average / 2;

        String stringAverage = Double.toString(average);

        File folder = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(folder, "SD.txt");
        String first = fn;
        String last = ln;
        FileOutputStream fos = null;
        try
        {
            fos = new FileOutputStream(file);
            fos.write(first.getBytes());
            fos.write(last.getBytes());
            fos.write(stringAverage.getBytes());
            Toast.makeText(this, "Your credentials are saved in your SD card!", Toast.LENGTH_SHORT);
        } catch (FileNotFoundException e)
        {
            Toast.makeText(this, "Error writing in SD card", Toast.LENGTH_LONG);
        } catch (IOException e)
        {
            Toast.makeText(this, "IO Exception", Toast.LENGTH_LONG);

        }

        SharedPreferences preference = getSharedPreferences("Data1", MODE_PRIVATE);
        String aver = preference.getString("stringAverage",null);
        ave.setText(stringAverage);

        /*
        //loading
        File fold = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        File files = new File(folder, "SD.txt");
        FileInputStream fin = null;
        int c;
        StringBuffer buffer = new StringBuffer();
        try {
            fin = new FileInputStream(file);
            while((c=fin.read()) != -1)
            {
                buffer.append((char) c);
            }

            ave.setText(buffer);

        } catch (Exception e) {
            Toast.makeText(this, "Error reading in SD card", Toast.LENGTH_LONG);
        }
        */
    }
}
