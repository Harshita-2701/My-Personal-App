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

        // Save details to the database (you need to implement your own database logic)
        saveToDatabase(name, getSelectedGender(), age);

        // Add your logic for what should happen after saving the details

        Toast.makeText(this, "Details saved successfully.", Toast.LENGTH_SHORT).show();
    }

    private String getSelectedGender() {
        RadioButton selectedRadioButton = findViewById(gender_radioGroup.getCheckedRadioButtonId());
        return selectedRadioButton.getText().toString();
    }

    private void saveToDatabase(String name, String gender, int age) {
        // Implement your database logic here (SQLite, Room, etc.)
        // For simplicity, this example assumes you have a SQLite database helper class

        // DatabaseHelper dbHelper = new DatabaseHelper(this);
        // SQLiteDatabase db = dbHelper.getWritableDatabase();

        // ContentValues values = new ContentValues();
        // values.put("name", name);
        // values.put("gender", gender);
        // values.put("age", age);

        // db.insert("user_details", null, values);

        // Don't forget to close the database after use
        // db.close();

    }
}