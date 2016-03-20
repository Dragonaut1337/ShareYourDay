package com.mobile.andrew.shareyourday.model;

import android.os.Bundle;

import com.mobile.andrew.shareyourday.utility.DateConverter;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by andre on 07/03/2016.
 */
public class Entry implements Serializable {

    public static final String ID = "id";
    public static final String ENTRY_TITLE = "entryTitle";
    public static final String IMAGE_RESOURCE = "imageResource";
    public static final String DESCRIPTION = "description";
    public static final String DATE = "date";

    private long id;
    private String entryTitle;
    private int imageResource;
    private String description;
    private Date date;

    public Entry() {
    }

    public Entry(long id, String entryTitle, int imageResource, String description, Date date) {
        this.id = id;
        this.entryTitle = entryTitle;
        this.imageResource = imageResource;
        this.description = description;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEntryTitle() {
        return entryTitle;
    }

    public void setEntryTitle(String entryTitle) {
        this.entryTitle = entryTitle;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Entry(Bundle bundle) {
        if (bundle != null) {
            this.entryTitle = bundle.getString(ENTRY_TITLE);
            this.imageResource = bundle.getInt(IMAGE_RESOURCE);
            this.description = bundle.getString(DESCRIPTION);
            this.date = DateConverter.convertStringToDate(bundle.getString(DATE));
        }
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putLong(ID, this.id);
        bundle.putString(ENTRY_TITLE, entryTitle);
        bundle.putInt(IMAGE_RESOURCE, imageResource);
        bundle.putString(DESCRIPTION, description);
        bundle.putString(DATE, DateConverter.convertDateToString(date));

        return bundle;
    }

    @Override
    public String toString() {
        return entryTitle;
    }
}
