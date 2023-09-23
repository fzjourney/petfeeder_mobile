package com.example.petpider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;


public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private Animation scaleAnimation;
    private FragmentManager fragmentManager;
    private Fragment activeFragment;

    private Fragment homeFragment;
    private Fragment scheduleFragment;
    private Fragment historyFragment;
    private Fragment profileFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener navItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.menu_home:
                            selectedFragment = homeFragment;
                            break;
                        case R.id.menu_schedule:
                            Intent intent2 = new Intent(HomeActivity.this, SchedulePageActivity.class);
                            startActivity(intent2);
                            return true;
                        case R.id.menu_history:
                            Intent intent = new Intent(HomeActivity.this, FeedPageActivity.class);
                            startActivity(intent);
                            return true;
                        case R.id.menu_profile:
                            // Start the ProfilePageActivity
                            Intent intent1 = new Intent(HomeActivity.this, ProfilePageActivity.class);
                            startActivity(intent1);
                            return true;
                    }

                    if (selectedFragment != null) {
                        fragmentManager.beginTransaction()
                                .hide(activeFragment)
                                .show(selectedFragment)
                                .commit();
                        activeFragment = selectedFragment;
                    }

                    return true;
                }
            };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        homeFragment = new HomeFragment();
        scheduleFragment = new ScheduleFragment();
        historyFragment = new HistoryFragment();
        profileFragment = new ProfileFragment();

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navItemSelectedListener);

        fragmentManager.beginTransaction()
                .add(R.id.fragment_container, profileFragment, "4")
                .hide(profileFragment)
                .add(R.id.fragment_container, historyFragment, "3")
                .hide(historyFragment)
                .add(R.id.fragment_container, scheduleFragment, "2")
                .hide(scheduleFragment)
                .add(R.id.fragment_container, homeFragment, "1")
                .commit();

        activeFragment = homeFragment;

        Button btnFeed = findViewById(R.id.btn_feed);
        btnFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, FeedPageActivity.class);
                startActivity(intent);
            }
        });

        ImageButton historyButton = findViewById(R.id.historyButton);
        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, FeedPageActivity.class);
                startActivity(intent);
            }
        });

        ImageButton scheduleButton = findViewById(R.id.scheduleButton);
        scheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SchedulePageActivity.class);
                startActivity(intent);
            }
        });
    }
    public void goToAnotherPage(View view) {
        // Start the HomeActivity
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }


}

