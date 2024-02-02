package com.harshik.mypersonalapp.mainApp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.harshik.mypersonalapp.R;

public class MainAppFrontScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_app_front_screen);

        String appName = getString(R.string.app_name);
        TextView appNameTextView = findViewById(R.id.app_name_textView);
        appNameTextView.setText(appName);

        new Handler().postDelayed(() -> {
            // Start the second screen activity
            Intent intent = new Intent(MainAppFrontScreen.this, WelcomeScreenFirstTime.class);
            startActivity(intent);
            finish(); // Finish the current activity to prevent going back
        }, 5000);
    }

}
