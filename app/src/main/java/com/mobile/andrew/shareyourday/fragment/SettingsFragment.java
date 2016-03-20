package com.mobile.andrew.shareyourday.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mobile.andrew.shareyourday.R;
import com.mobile.andrew.shareyourday.activity.AddEntryActivity;
import com.mobile.andrew.shareyourday.activity.SettingsActivity;
import com.mobile.andrew.shareyourday.model.Entry;

/**
 * Created by andre on 07/03/2016.
 */
public class SettingsFragment extends Fragment {

    public static final String COLOR_SELECTION = "colorselection";
    public static final int EDIT_SETTINGS_INTENT = 1;
    private int pageNumber;
    Button editSettingsButton;
    TextView selectionTextView;

    public SettingsFragment() {
    }

    public static Fragment newInstance(int pageNumber){
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putInt("pageNumber", pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments().getInt("pageNumber", 1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings_fragment, container, false);

        editSettingsButton = (Button) view.findViewById(R.id.edit_settings_button);
        selectionTextView = (TextView) view.findViewById(R.id.colortheme_selection_label);


        editSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettingsActivity.class);
                startActivityForResult(intent, EDIT_SETTINGS_INTENT);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == EDIT_SETTINGS_INTENT) {
            if (resultCode == Activity.RESULT_OK) {

                String selection = data.getStringExtra(COLOR_SELECTION);
                if (!selection.equals("Selection")) {
                    selectionTextView.setText(selection);
                }
            }
        }
    }
}
