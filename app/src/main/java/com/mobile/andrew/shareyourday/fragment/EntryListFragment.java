package com.mobile.andrew.shareyourday.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.mobile.andrew.shareyourday.R;
import com.mobile.andrew.shareyourday.activity.AddEntryActivity;
import com.mobile.andrew.shareyourday.adapter.EntryArrayAdapter;
import com.mobile.andrew.shareyourday.model.Entry;
import com.mobile.andrew.shareyourday.utility.DataSource;
import com.mobile.andrew.shareyourday.utility.IntentValues;

import java.util.List;

/**
 * Created by andre on 06/03/2016.
 */
public class EntryListFragment extends ListFragment {

    private List<Entry> entryList;
    private Button addEntryButton;
    private DataSource dataSource;
    private EntryArrayAdapter adapter;

    public EntryListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dataSource = new DataSource(getContext());
        entryList = dataSource.getAllEntriesFromDatabase();
        adapter = new EntryArrayAdapter(getContext(), R.layout.entry_listitem, entryList);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Entry selectedEntry = (Entry) getListAdapter().getItem(position);
        EntryDetailFragment detailFragment = EntryDetailFragment.newInstance(selectedEntry);

        getFragmentManager().beginTransaction().replace(R.id.entry_detail, detailFragment).commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.entrylist_fragment, container, false);

        addEntryButton = (Button) view.findViewById(R.id.add_entry_button);
        ListView listView = (ListView) view.findViewById(android.R.id.list);
        registerForContextMenu(listView);

        addEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddEntryActivity.class);
                getParentFragment().startActivityForResult(intent, IntentValues.ADD_ENTRY_INTENT);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == IntentValues.ADD_ENTRY_INTENT) {
            if (resultCode == Activity.RESULT_OK) {

                long entryId = data.getLongExtra(Entry.ID, -1);
                if (entryId != -1) {
                    Entry assignment = dataSource.getAssignment(entryId);
                    adapter.add(assignment);
                    updateEntryListView();
                }
            }
        }

        if (requestCode == IntentValues.EDIT_ENTRY_INTENT) {
            if (resultCode == Activity.RESULT_OK) {
                updateEntryListView();
            }
        }
    }

    public void updateEntryListView() {
        entryList = dataSource.getAllEntriesFromDatabase();
        adapter = new EntryArrayAdapter(getContext(), R.layout.entry_listitem, entryList);
        setListAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Select The Action");
        menu.add(0, v.getId(), 0, "Delete");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if (item.getTitle() == "Delete") {
            Toast.makeText(getContext(), "Entry deleted", Toast.LENGTH_LONG).show();
            Entry entry = adapter.getItem(info.position);
            adapter.remove(entry);
            dataSource.deleteEntry(entry);
            updateEntryListView();

            List<Fragment> list = getParentFragment().getChildFragmentManager().getFragments();

            for (int i = 0; i < list.size(); i++) {

                if (list.get(i).getClass() == EntryDetailFragment.class) {

                    EntryDetailFragment fragment = (EntryDetailFragment) list.get(i);
                    fragment.clearEntryDetailFragment();
                }
            }
        } else {
            return false;
        }
        return true;
    }
}
