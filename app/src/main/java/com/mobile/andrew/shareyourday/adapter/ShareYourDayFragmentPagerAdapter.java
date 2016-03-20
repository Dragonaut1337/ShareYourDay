package com.mobile.andrew.shareyourday.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mobile.andrew.shareyourday.fragment.AboutFragment;
import com.mobile.andrew.shareyourday.fragment.EntryContainerFragment;
import com.mobile.andrew.shareyourday.fragment.EntryListFragment;
import com.mobile.andrew.shareyourday.fragment.SettingsFragment;

/**
 * Created by andre on 06/03/2016.
 */
public class ShareYourDayFragmentPagerAdapter extends FragmentPagerAdapter{

    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] { "Entries", "Settings", "About" };
    private Context context;

    public ShareYourDayFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {

        switch(position){
            case 0:
                return EntryContainerFragment.newInstance(0);
            case 1:
                return SettingsFragment.newInstance(1);
            case 2:
                return AboutFragment.newInstance(2);
            default:
                return null;
        }

    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
