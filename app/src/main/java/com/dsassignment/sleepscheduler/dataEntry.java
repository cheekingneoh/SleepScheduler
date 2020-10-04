package com.dsassignment.sleepscheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class dataEntry extends AppCompatActivity {

    private int mYear, mMonth, mDay, mHour, mMinute;
    private EditText date,time,age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);
        date=findViewById(R.id.dateInput);
        time=findViewById(R.id.timeInput);
        age=findViewById(R.id.ageInput);

        date.setShowSoftInputOnFocus(false);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(dataEntry.this, new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                date.setText(dayOfMonth + " / " + (monthOfYear + 1) + " / " + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        time.setShowSoftInputOnFocus(false);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Current Time
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(dataEntry.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                time.setText(hourOfDay + " : " + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });

        Button submit=findViewById(R.id.submitButton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
    }

    public void validate(){
        String dateIn=date.getText().toString();
        String timeIn=time.getText().toString();
        int ageIn=Integer.parseInt(age.getText().toString());
        Log.d("maybe problem here", Integer.toString(ageIn));
        if(TextUtils.isEmpty(dateIn)){
            date.setError("Required to be filled in");
            date.requestFocus();
        }else if(TextUtils.isEmpty(timeIn)){
            time.setError("Required to be filled in");
            time.requestFocus();
        }else if(TextUtils.isEmpty(Integer.toString(ageIn))){
            age.setError("Required to be filled in");
            age.requestFocus();
        }else{
            try{
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(getFilesDir()+File.separator+"MyFile.txt")));
                bufferedWriter.write(dateIn);
                bufferedWriter.newLine();
                bufferedWriter.write(timeIn);
                bufferedWriter.newLine();
                bufferedWriter.write(Integer.toString(ageIn));
                bufferedWriter.newLine();
                bufferedWriter.close();
            }catch(IOException e){
                Log.d("IOEException",e.getMessage());
            }

            dataStored data=new dataStored();
            data.setAge(ageIn);
            data.setDate(dateIn);
            data.setTime(timeIn);

            Intent mainPage=new Intent(dataEntry.this,MainActivity.class);
            startActivity(mainPage);
        }
    }


}