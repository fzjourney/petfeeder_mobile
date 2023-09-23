package com.example.petpider;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class ProfilePageActivity extends AppCompatActivity {

    private TextInputEditText edtNameRegister;
    private TextInputEditText edtEmailRegister;
    private TextInputEditText edtGenderRegister;
    private TextInputEditText edtPhoneRegister;

    private String email_auth;
    private String password_auth;
    private String name_auth;
    private String date_auth;

    private static final String PREFS_NAME = "UserData";
    private static final String NAME_KEY = "name";
    private static final String EMAIL_KEY = "email";
    private static final String BIRTH_DATE_KEY = "birthDate";
    private static final String PHONE_NUMBER_KEY = "phoneNumber";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.profile_page);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Intent intent = getIntent();
            email_auth = intent.getStringExtra("email");
            password_auth = intent.getStringExtra("password");
            name_auth = intent.getStringExtra("username");
            date_auth = intent.getStringExtra("birth");
        }

        edtNameRegister = findViewById(R.id.edt_name_register);
        edtEmailRegister = findViewById(R.id.edt_email_register);
        edtGenderRegister = findViewById(R.id.edt_gender_register);
        edtPhoneRegister = findViewById(R.id.edt_phone_register);

        // Retrieve saved data from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String savedName = sharedPreferences.getString(NAME_KEY, "");
        String savedEmail = sharedPreferences.getString(EMAIL_KEY, "");
        String savedBirthDate = sharedPreferences.getString(BIRTH_DATE_KEY, "");
        String savedPhoneNumber = sharedPreferences.getString(PHONE_NUMBER_KEY, "");

        edtNameRegister.setText(savedName);
        edtEmailRegister.setText(savedEmail);
        edtGenderRegister.setText(savedBirthDate);
        edtPhoneRegister.setText(savedPhoneNumber);
    }

    public void showDatePicker(View view) {
        // Get the current date
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create a DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                // Update the text of the TextInputEditText with the selected date
                String selectedDate = String.format("%02d/%02d/%d", selectedDay, selectedMonth + 1, selectedYear);
                edtGenderRegister.setText(selectedDate);
            }
        }, year, month, day);

        // Show the DatePickerDialog
        datePickerDialog.show();
    }

    public void saveData(View view) {
        // Get the entered data from the fields
        String name = edtNameRegister.getText().toString();
        String email = edtEmailRegister.getText().toString();
        String birthDate = edtGenderRegister.getText().toString();
        String phoneNumber = edtPhoneRegister.getText().toString();

        // Save the data to SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(NAME_KEY, name);
        editor.putString(EMAIL_KEY, email);
        editor.putString(BIRTH_DATE_KEY, birthDate);
        editor.putString(PHONE_NUMBER_KEY, phoneNumber);
        editor.apply();

        Toast.makeText(this, "Profile Updated", Toast.LENGTH_SHORT).show();
    }

    public void goToAnotherPage(View view) {
        // Start the HomeActivity
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Retrieve saved data from SharedPreferences and update the fields
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String savedBirthDate = sharedPreferences.getString(BIRTH_DATE_KEY, "");
        edtGenderRegister.setText(savedBirthDate);
    }
}
