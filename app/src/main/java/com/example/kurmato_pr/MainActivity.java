package com.example.kurmato_pr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // on below line we are
                // creating a new intent
                Intent intent = new Intent(MainActivity.this, HomePage.class);

                // on below line we are
                // starting a new activity.
                 startActivity(intent);

                // on the below line we are finishing
                // our current activity.
                finish();
            }
        }, 4000);
    }
}