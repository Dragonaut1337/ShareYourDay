package com.mobile.andrew.shareyourday.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.andrew.shareyourday.R;
import com.mobile.andrew.shareyourday.model.Entry;
import com.mobile.andrew.shareyourday.utility.DateConverter;

import java.util.Date;

/**
 * Created by andre on 07/03/2016.
 */
public class EntryDetailFragment extends Fragment {

    private static final String ENTRY = "entry";

    private String title;
    private int imageResource;
    private String description;
    private Date date;
    private Entry entry;

    public EntryDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            entry = (Entry) getArguments().getSerializable(ENTRY);
            title = entry.getEntryTitle();
            imageResource = entry.getImageResource();
            description = entry.getDescription();
            date = entry.getDate();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.entrydetail_fragment, container, false);

        TextView titleTextView = (TextView) view.findViewById(R.id.entry_detail_title);
        ImageView imageView = (ImageView) view.findViewById(R.id.entry_detail_imageview);
        TextView descriptionTextView = (TextView) view.findViewById(R.id.entry_detail_description);
        TextView dateTextView = (TextView) view.findViewById(R.id.entry_detail_date);

        if(title != null){
            titleTextView.setText(title);
        }
        if(imageView != null){
            imageView.setImageResource(imageResource);
        }

        if(description != null){
            descriptionTextView.setText(description);
        }

        if(date != null){
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
}
