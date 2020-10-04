package com.dsassignment.sleepscheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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

        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(getFilesDir()+File.separator+"MyFile.txt")));
            dataStored data=new dataStored();
            data.setDate(bufferedReader.readLine());
            data.setTime(bufferedReader.readLine());
            data.setAge(Integer.parseInt(bufferedReader.readLine()));
            bufferedReader.close();
            Log.d("test",data.toString());
        }catch(IOException e){
            Log.d("IOException",e.getMessage());
        }

    }
}