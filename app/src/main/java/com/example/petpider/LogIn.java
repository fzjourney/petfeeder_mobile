package com.example.petpider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LogIn extends AppCompatActivity {

    private Button logIn;
    private Button signUp;
    private EditText email;
    private EditText password;
    private String email_auth;
    private String password_auth;
    private String name_auth;
    private String date_auth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        email = findViewById(R.id.email_login);
        password = findViewById(R.id.password_login);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Intent intent = getIntent();
            email_auth = intent.getStringExtra("email");
            password_auth = intent.getStringExtra("password");
            name_auth = intent.getStringExtra("username");
            date_auth = intent.getStringExtra("birth");
        }

        signUp = (Button) findViewById(R.id.signupbtn);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogIn.this, SignIn.class);
                startActivity(intent);
            }
        });

        logIn = (Button) findViewById(R.id.loginbtn);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().equals(email_auth) && password.getText().toString().equals(password_auth)){
                    // correct
                    Toast.makeText(LogIn.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LogIn.this, HomeActivity.class);
                    Intent intent2 = new Intent(LogIn.this, ProfilePageActivity.class);
                    intent2.putExtra("email", email_auth); // Replace "username" with your key
                    intent2.putExtra("password", password_auth); // Replace "password" with your key
                    intent2.putExtra("username", name_auth);
                    intent2.putExtra("birth", date_auth);
                    startActivity(intent);
                }
                else{
                    // Incorrect credentials
                    Toast.makeText(LogIn.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}