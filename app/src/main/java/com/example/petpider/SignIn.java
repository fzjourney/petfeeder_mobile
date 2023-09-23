package com.example.petpider;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class SignIn extends AppCompatActivity {

    private EditText signEmail;
    private EditText signPassword;
    private EditText signUsername;
    private EditText signDate;
    private Button signIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sign_in);

        signEmail = findViewById(R.id.email_signin);
        signPassword = findViewById(R.id.password_signin);
        signUsername = findViewById(R.id.username_signin);
        signDate = findViewById(R.id.date_signin);

        signIn = (Button) findViewById(R.id.signinbtn);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the entered data from the fields
                String name = signUsername.getText().toString();
                String email = signEmail.getText().toString();
                String birthDate = signDate.getText().toString();
                String password = signPassword.getText().toString();

                if(name.isEmpty() || email.isEmpty() || birthDate.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill in all fields!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(getApplicationContext(), "Sign Up Successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignIn.this, LogIn.class);
                intent.putExtra("email", email); // Replace "username" with your key
                intent.putExtra("password", password); // Replace "password" with your key
                intent.putExtra("username", name);
                intent.putExtra("birth", birthDate);
                startActivity(intent);
            }
        });
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
                signDate.setText(selectedDate);
            }
        }, year, month, day);

        // Show the DatePickerDialog
        datePickerDialog.show();
    }

}