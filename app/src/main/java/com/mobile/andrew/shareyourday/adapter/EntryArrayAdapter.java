package com.mobile.andrew.shareyourday.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.andrew.shareyourday.R;
import com.mobile.andrew.shareyourday.model.Entry;
import com.mobile.andrew.shareyourday.utility.DateConverter;

import java.util.Date;
import java.util.List;

/**
 * Created by andre on 07/03/2016.
 */
public class EntryArrayAdapter extends ArrayAdapter<Entry>{

    private Context context;
    private List<Entry> objects;
    private LayoutInflater inflater;

    public EntryArrayAdapter(Context context, int resource, List<Entry> objects) {
        super(context, resource, objects);
        this.context = context;
        this.objects = objects;

        inflater = LayoutInflater.from(getContext());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        ViewHolder holder;

        if (row == null) {
            row = inflater.inflate(R.layout.entry_listitem, parent, false);
            holder = new ViewHolder(row);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        holder.populateRow(getItem(position));

        return row;
    }

    class ViewHolder{
        private ImageView image;
        private TextView title;
        private TextView dateTextView;


        //initialize the variables
        public ViewHolder(View view){
            image = (ImageView) view.findViewById(R.id.entry_listitem_imageview);
            title = (TextView) view.findViewById(R.id.entry_listitem_title);
            dateTextView = (TextView) view.findViewById(R.id.entry_listitem_date);
        }

        public void populateRow(Entry entry){
            title.setText(entry.getEntryTitle());
            image.setImageResource(entry.getImageResource());

            if(entry.getDate() != null){
                dateTextView.setText(DateConverter.convertDateToString(entry.getDate()));
            }
            else{
                dateTextView.setText(DateConverter.convertDateToString(new Date()));
            }


        }

    }
}
