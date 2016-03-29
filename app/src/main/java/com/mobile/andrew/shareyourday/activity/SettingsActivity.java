package com.mobile.andrew.shareyourday.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.mobile.andrew.shareyourday.R;
import com.mobile.andrew.shareyourday.fragment.SettingsFragment;

public class SettingsActivity extends AppCompatActivity {

    private Spinner colorSpinner;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        colorSpinner = (Spinner) findViewById(R.id.colorspinner);
        saveButton = (Button) findViewById(R.id.settings_save_button);
        ArrayAdapter statusAdapter = ArrayAdapter.createFromResource(this,
                R.array.colorthemes, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Set the adapter to the colorSpinner
        colorSpinner.setAdapter(statusAdapter);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selection = (String) colorSpinner.getSelectedItem();

                Intent resultIntent = new Intent();
                resultIntent.putExtra(SettingsFragment.COLOR_SELECTION, selection);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
