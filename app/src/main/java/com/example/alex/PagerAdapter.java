package com.example.alex;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.alex.collections.CollectionsFragment;
import com.example.alex.maps.MapsFragment;


public class PagerAdapter extends FragmentPagerAdapter {


    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {


        switch (position) {
            case 0:
                return CollectionsFragment.newInstance();
            case 1:
                return MapsFragment.newInstance();
            default:
                return CollectionsFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}