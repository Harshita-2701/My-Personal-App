package com.harshik.mypersonalapp.mainApp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.harshik.mypersonalapp.R;

public class WelcomeScreenFirstTime extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_screen_first_time);

        // Find views in the layout
        Button one_time_button = findViewById(R.id.one_time_button);

        // Set click listener for the "Next" button
        one_time_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add your logic for what should happen when the "Next" button is clicked
            }
        });
    }

}