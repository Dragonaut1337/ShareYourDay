package com.mobile.andrew.shareyourday.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mobile.andrew.shareyourday.R;
import com.mobile.andrew.shareyourday.fragment.EntryDetailFragment;
import com.mobile.andrew.shareyourday.model.Entry;
import com.mobile.andrew.shareyourday.utility.DataSource;
import com.mobile.andrew.shareyourday.utility.DateConverter;

import java.util.Date;

public class EditEntryActivity extends AppCompatActivity {

    private DataSource dataSource;
    private Entry entry;
    private EditText titleInput;
    private EditText descriptionInput;
    private  EditText dateInput;
    private Button editButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_entry);

        dataSource = new DataSource(this);

        titleInput = (EditText) findViewById(R.id.edit_input_title);
        descriptionInput = (EditText) findViewById(R.id.edit_input_description);
        dateInput = (EditText) findViewById(R.id.edit_input_date);
        editButton = (Button) findViewById(R.id.edit_button_save);

        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            entry = (Entry) bundle.getSerializable(EntryDetailFragment.ENTRY);
            titleInput.setText(entry.getEntryTitle());
            descriptionInput.setText(entry.getDescription());
            dateInput.setText(DateConverter.convertDateToString(entry.getDate()));

            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    entry.setEntryTitle(titleInput.getText().toString());
                    entry.setDescription(descriptionInput.getText().toString());
                    entry.setDate(DateConverter.convertStringToDate(dateInput.getText().toString()));

                    String date = dateInput.getText().toString();
                    if (DateConverter.convertStringToDate(date) == null) {
                        date = DateConverter.convertDateToString(new Date());
                    }

                    entry.setDate(DateConverter.convertStringToDate(date));

                    Long entryId = entry.getId();
                    dataSource.updateEntry(entry);

                    Bundle bundle = new Bundle();
                    bundle.putSerializable(EntryDetailFragment.ENTRY, entry);

                    Intent resultIntent = new Intent();
                    resultIntent.putExtras(bundle);
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();
                }
            });
        }
    }
}
