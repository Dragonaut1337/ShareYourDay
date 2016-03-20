package com.mobile.andrew.shareyourday.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.andrew.shareyourday.R;

/**
 * Created by andre on 07/03/2016.
 */
public class AboutFragment extends Fragment {

    private int pageNumber;

    public AboutFragment() {
    }

    public static Fragment newInstance(int pageNumber) {
        AboutFragment fragment = new AboutFragment();
        Bundle args = new Bundle();
        args.putInt("pageNumber", pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments().getInt("pageNumber", 2);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.about_fragment, container, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.about_imageview);
        imageView.setImageResource(R.drawable.hyperhero);
        TextView linkTextView = (TextView) view.findViewById(R.id.hyperhero_link);

        linkTextView.setText(Html.fromHtml("<a href=\"http://www.hyperhero.com\">www.hyperhero.com</a>"));
        linkTextView.setMovementMethod(LinkMovementMethod.getInstance());

        return view;
    }

}
