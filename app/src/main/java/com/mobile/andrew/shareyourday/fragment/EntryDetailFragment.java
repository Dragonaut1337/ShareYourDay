package com.mobile.andrew.shareyourday.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.andrew.shareyourday.R;
import com.mobile.andrew.shareyourday.activity.AddEntryActivity;
import com.mobile.andrew.shareyourday.activity.EditEntryActivity;
import com.mobile.andrew.shareyourday.model.Entry;
import com.mobile.andrew.shareyourday.utility.DateConverter;
import com.mobile.andrew.shareyourday.utility.IntentValues;

import java.util.Date;

/**
 * Created by andre on 07/03/2016.
 */
public class EntryDetailFragment extends Fragment {

    public static final String ENTRY = "entry";

    private String title;
    private int imageResource;
    private String description;
    private Date date;
    private Entry entry;
    private Button editEntryButton;
    private TextView titleTextView;
    private ImageView imageView;
    private TextView descriptionTextView;
    private TextView dateTextView;


    public EntryDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            if((Entry) getArguments().getSerializable(ENTRY) != null){
                entry = (Entry) getArguments().getSerializable(ENTRY);
                title = entry.getEntryTitle();
                imageResource = entry.getImageResource();
                description = entry.getDescription();
                date = entry.getDate();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.entrydetail_fragment, container, false);

        titleTextView = (TextView) view.findViewById(R.id.entry_detail_title);
        imageView = (ImageView) view.findViewById(R.id.entry_detail_imageview);
        descriptionTextView = (TextView) view.findViewById(R.id.entry_detail_description);
        dateTextView = (TextView) view.findViewById(R.id.entry_detail_date);
        editEntryButton = (Button) view.findViewById(R.id.edit_entry_button);

        if (entry == null) {
            editEntryButton.setVisibility(View.INVISIBLE);
        }


        if (entry != null) {
            editEntryButton.setVisibility(View.VISIBLE);

            editEntryButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (getArguments() != null) {

                        Bundle bundle = new Bundle();
                        bundle.putSerializable(ENTRY, entry);
                        Intent intent = new Intent(getActivity(), EditEntryActivity.class);
                        intent.putExtras(bundle);
                        getParentFragment().startActivityForResult(intent, IntentValues.EDIT_ENTRY_INTENT);
                    }
                }
            });
        }


        if (title != null) {
            titleTextView.setText(title);
        }
        if (imageView != null) {
            imageView.setImageResource(imageResource);
        }

        if (description != null) {
            descriptionTextView.setText(description);
        }

        if (date != null) {
            dateTextView.setText(DateConverter.convertDateToString(date));
        }

        return view;
    }

    public static EntryDetailFragment newInstance(Entry entry) {
        EntryDetailFragment fragment = new EntryDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ENTRY, entry);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == IntentValues.EDIT_ENTRY_INTENT) {
            if (resultCode == Activity.RESULT_OK) {

                Bundle bundle = data.getExtras();
                Entry entry = (Entry) bundle.getSerializable(ENTRY);

                if (entry.getEntryTitle() != null) {
                    titleTextView.setText(entry.getEntryTitle());
                }

                if (entry.getImageResource() != -1) {
                    imageView.setImageResource(entry.getImageResource());
                }

                if (entry.getDescription() != null) {
                    descriptionTextView.setText(entry.getDescription());
                }

                if (entry.getDate() != null) {
                    dateTextView.setText(DateConverter.convertDateToString(entry.getDate()));
                }
            }
        }
    }
}
