package com.mobile.andrew.shareyourday.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mobile.andrew.shareyourday.R;
import com.mobile.andrew.shareyourday.model.Entry;
import com.mobile.andrew.shareyourday.utility.DataSource;
import com.mobile.andrew.shareyourday.utility.DateConverter;

import java.util.Date;

public class AddEntryActivity extends AppCompatActivity {

    private DataSource dataSource;
    private EditText titleInput;
    private EditText descriptionInput;
    private EditText dateInput;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);

        dataSource = new DataSource(this);

        titleInput = (EditText) findViewById(R.id.add_input_title);
        descriptionInput = (EditText) findViewById(R.id.add_input_description);
        dateInput = (EditText) findViewById(R.id.add_input_date);
        addButton = (Button) findViewById(R.id.add_button_save);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = titleInput.getText().toString();
                int imageResource = R.drawable.princess_flower; //Default value!
                String description = descriptionInput.getText().toString();
                String date = dateInput.getText().toString();

                if (DateConverter.convertStringToDate(date) == null) {
                    date = DateConverter.convertDateToString(new Date());
                }

                long entryId = dataSource.createEntry(title, imageResource, description, date);
                Intent resultIntent = new Intent();
                resultIntent.putExtra(Entry.ID, entryId);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
