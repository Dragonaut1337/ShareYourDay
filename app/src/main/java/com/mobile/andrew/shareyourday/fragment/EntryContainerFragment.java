package com.mobile.andrew.shareyourday.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.mobile.andrew.shareyourday.R;

import java.util.List;

/**
 * Created by andre on 08/03/2016.
 */
public class EntryContainerFragment extends Fragment {

    public EntryContainerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Fragment entryListFragment = new EntryListFragment();
        Fragment entryDetailFragment = new EntryDetailFragment();

        getChildFragmentManager().beginTransaction()
                .add(R.id.entry_list, entryListFragment).add(R.id.entry_detail, entryDetailFragment)
                .commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.entrycontainer_fragment, container, false);
        return view;
    }

    public static Fragment newInstance(int pageNumber) {
        EntryContainerFragment fragment = new EntryContainerFragment();
        Bundle args = new Bundle();
        args.putInt("pageNumber", pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Override this method in the activity that hosts the Fragment and call super
        // in order to receive the result inside onActivityResult from the fragment.
        super.onActivityResult(requestCode, resultCode, data);
        List<Fragment> list = getChildFragmentManager().getFragments();

        for (int i = 0; i < list.size(); i++) {

            if (list.get(i).getClass() == EntryListFragment.class) {
                Fragment fragment = list.get(i);
                fragment.onActivityResult(requestCode, resultCode, data);
            }

            if (list.get(i).getClass() == EntryDetailFragment.class) {

                Fragment fragment = list.get(i);
                fragment.onActivityResult(requestCode, resultCode, data);
            }
        }
    }
}
