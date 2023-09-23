package com.example.petpider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import java.util.ArrayList;

public class SchedulePageActivity extends AppCompatActivity {

    Button btnAdd;
    RecyclerView scheduleView;
    ScheduleAdapter scheduleAdapter;

    public static ArrayList<Schedule> scheduleArray =  new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.schedule_page);

        scheduleView = findViewById(R.id.rv_schedule);
        scheduleView.setLayoutManager(new LinearLayoutManager(this));
        scheduleAdapter = new ScheduleAdapter(scheduleArray,this);
        scheduleView.setAdapter(scheduleAdapter);

        btnAdd = (Button) findViewById(R.id.add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SchedulePageActivity.this, AddschedulePageActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        scheduleAdapter = new ScheduleAdapter(scheduleArray,this);
        scheduleView.setAdapter(scheduleAdapter);
    }

    public void goToAnotherPage(View view) {
        // Start the HomeActivity
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}