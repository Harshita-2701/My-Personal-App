package com.harshik.mypersonalapp.mainApp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import static com.harshik.mypersonalapp.contants.MainAppConstants.*;

import com.harshik.mypersonalapp.R;

import java.util.Map;

public class ProfileMainApp extends AppCompatActivity {

    private ImageView profile_Pic_ImageView, gender_Icon_ImageView;
    private TextView name_TextView, gender_TextView, age_TextView;
    private Button done_Button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_main_app);

        // Initialize views
        profile_Pic_ImageView = findViewById(R.id.profile_Pic_ImageView);
        name_TextView = findViewById(R.id.name_TextView);
        gender_Icon_ImageView = findViewById(R.id.gender_Icon_ImageView);
        gender_TextView = findViewById(R.id.gender_TextView);
        age_TextView = findViewById(R.id.age_TextView);
        done_Button = findViewById(R.id.done_Button);

        DatabaseHelperMainApp dbHelper = new DatabaseHelperMainApp(this);
        Map<String, String> output = dbHelper.getProfileDetails();

        String name = output.get(NAME);
        String gender = output.get(GENDER);
        String age = output.get(AGE);

        // Display user details
        name_TextView.setText(name);
        gender_TextView.setText(gender);
        age_TextView.setText(age);

        // Dynamically set profile pic based on gender
        int drawableId = (gender.equalsIgnoreCase("male")) ? R.drawable.icon_male :
                (gender.equalsIgnoreCase("female")) ? R.drawable.icon_female :
                        R.drawable.icon_other;

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), drawableId);
        profile_Pic_ImageView.setImageBitmap(bitmap);
        gender_Icon_ImageView.setImageBitmap(bitmap);

        // Set onClickListener for Done button
        done_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to the main app_dashboard screen
                //todo rename the return back screen name
                Intent intent = new Intent(ProfileMainApp.this, MainAppFrontScreen.class);
                startActivity(intent);
                finish(); // Close the current activity
            }
        });
    }

}