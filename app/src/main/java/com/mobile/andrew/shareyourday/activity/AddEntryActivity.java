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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);

        dataSource = new DataSource(this);

        final EditText titleInput = (EditText) findViewById(R.id.add_input_title);
        final EditText descriptionInput = (EditText) findViewById(R.id.add_input_description);
        final EditText dateInput = (EditText) findViewById(R.id.add_input_date);
        Button addButton = (Button) findViewById(R.id.add_button_save);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = titleInput.getText().toString();
                int imageResource = R.drawable.princess_flower; //Default value!
                String description = descriptionInput.getText().toString();
                String date = dateInput.getText().toString();

                long entryId = dataSource.createEntry(title, imageResource, description, date);
                Intent resultIntent = new Intent();
                resultIntent.putExtra(Entry.ID, entryId);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
