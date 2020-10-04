package com.dsassignment.sleepscheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleExpandableListAdapter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.Buffer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button ConfigurationButton=findViewById(R.id.setUpButton);
        Button ScheduleButton=findViewById(R.id.ScheduleButton);
        Button MealButton=findViewById(R.id.MealButton);

        final Intent mealSchedule=new Intent(MainActivity.this, MealPlanner.class);
        MealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(mealSchedule);
            }
        });


        dataStored data=new dataStored();
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
        try{
            BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(new File(getFilesDir()+File.separator+"day.txt")));
            String DateCurrent=new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

            Date current=sdf.parse(DateCurrent);
            Date initial=sdf.parse(data.getDate());
            String temp=data.getDate();
            Log.d("test", Boolean.toString(TextUtils.isEmpty(temp)));
            Long diff=current.getTime()-initial.getTime();
            long day= TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            Log.d("test",Long.toString(day));
            bufferedWriter.write(Long.toString(day));
            bufferedWriter.close();
        }catch (IOException e){
            Log.d("IOException", e.getMessage());
        }catch (ParseException e){
            Log.d("ParseException",e.getMessage());
        }

    }
}