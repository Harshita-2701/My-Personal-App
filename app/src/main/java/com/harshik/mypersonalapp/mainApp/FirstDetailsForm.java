package com.harshik.mypersonalapp.mainApp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.harshik.mypersonalapp.R;

public class FirstDetailsForm extends AppCompatActivity {

    private EditText name_editText, age_editText;
    private RadioGroup gender_radioGroup;
    private CheckBox terms_checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_details_form);

        // Find views in the layout
        name_editText = findViewById(R.id.name_editText);
        age_editText = findViewById(R.id.age_editText);
        gender_radioGroup = findViewById(R.id.gender_radioGroup);
        terms_checkBox = findViewById(R.id.terms_checkBox);

        Button saveButton = findViewById(R.id.save_button);

        // Set click listener for the "Save" button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDetails();
            }
        });
    }

    private void saveDetails() {

        String name = name_editText.getText().toString().trim();
        String ageStr = age_editText.getText().toString().trim();

        // Validate inputs
        if (name.isEmpty() || gender_radioGroup.getCheckedRadioButtonId() == -1 || ageStr.isEmpty()) {
            Toast.makeText(this, "Please fill in all details.", Toast.LENGTH_SHORT).show();
            return;
        }

        int age = Integer.parseInt(ageStr);

        // Validate age
        if (age <= 5) {
            Toast.makeText(this, "Age must be greater than 5.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if terms and conditions are accepted
        if (!terms_checkBox.isChecked()) {
            Toast.makeText(this, "Please accept the terms and conditions.", Toast.LENGTH_SHORT).show();
            return;
        }

        DatabaseHelperMainApp dbHelper = new DatabaseHelperMainApp(this);
        Boolean output = dbHelper.insertUserDetails(name, getSelectedGender(), age);
        if(output){
            Toast.makeText(this, "Details saved successfully.", Toast.LENGTH_SHORT).show();
            //TODO MOVE TO APP DASHBOARD
        }else {
            Toast.makeText(this, "Failed. DETAILS", Toast.LENGTH_SHORT).show();
            //TODO new logic
        }

    }

    private String getSelectedGender() {
        RadioButton selectedRadioButton = findViewById(gender_radioGroup.getCheckedRadioButtonId());
        return selectedRadioButton.getText().toString();
    }
}