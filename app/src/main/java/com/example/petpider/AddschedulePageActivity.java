package com.example.petpider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddschedulePageActivity extends AppCompatActivity {

    private NumberPicker npHour, npMinute, npAmPm;
    private RadioGroup rgDay;
    private Button btnSave;
    private FirebaseDatabase database;
    private String chosenDayAbv = "";

    private EditText titleInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.addschedule_page);
        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
        initUI();

        rgDay.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton radioButton = findViewById(checkedId);
            chosenDayAbv = radioButton.getText().toString();
        });

        titleInput = findViewById(R.id.title_input);
        Button btnClose = (Button) findViewById(R.id.close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddschedulePageActivity.this, SchedulePageActivity.class);
                startActivity(intent);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hour;
                String min;
                if(npAmPm.getValue() == 0){
                    if(npHour.getValue() == 12 ){
                        npHour.setValue(0);
                    }
                }
                else{
                    if(npHour.getValue() > 12){
                        npHour.setValue(npHour.getValue() + 12);
                    }
                }
                hour = String.valueOf(npHour.getValue());
                min = String.valueOf(npMinute.getValue());

                String time = hour + ":" + min + " " + (npAmPm.getValue() == 0 ? "AM" : "PM");
                String title = titleInput.getText().toString();
                SchedulePageActivity.scheduleArray.add(new Schedule(time, title, chosenDayAbv));

                ApiService apiService = ApiClient.getClient().create(ApiService.class);
                apiService.feedSchedule(hour, min).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Schedule saved.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Schedule failed.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Connection Failure.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void initUI() {
        npHour = findViewById(R.id.np_hour_schedule);
        npMinute = findViewById(R.id.np_minute_schedule);
        npAmPm = findViewById(R.id.np_am_pm_schedule);
        rgDay = findViewById(R.id.rg_day_schedule);
        btnSave = findViewById(R.id.save);
        numberPickerConfig();
    }

    private void numberPickerConfig() {
        npHour.setMinValue(1);
        npHour.setMaxValue(12);

        npMinute.setMinValue(0);
        npMinute.setMaxValue(59);

        npAmPm.setMinValue(0);
        npAmPm.setMaxValue(1);
        npAmPm.setDisplayedValues(new String[]{"AM", "PM"});

        npHour.setFormatter(value -> String.format(Locale.US, "%02d", value));
        npMinute.setFormatter(value -> String.format(Locale.US, "%02d", value));
    }

}