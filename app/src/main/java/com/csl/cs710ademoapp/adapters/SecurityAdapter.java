package com.csl.cs710ademoapp.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import com.csl.cs710ademoapp.fragments.AccessSecurityLockFragment;
import com.csl.cs710ademoapp.fragments.AccessSecurityKillFragment;

public class SecurityAdapter extends FragmentStatePagerAdapter {
    private final int NO_OF_TABS = 2;
    public Fragment fragment0, fragment1;

    @Override
    public Fragment getItem(int index) {
        Fragment fragment = null;
        switch (index) {
            case 0:
                fragment = new AccessSecurityLockFragment();
                fragment0 = fragment;
                break;
            case 1:
                fragment = new AccessSecurityKillFragment();
                fragment1 = fragment;
                break;
            default:
                fragment = null;
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return NO_OF_TABS;
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    public SecurityAdapter(FragmentManager fm) {
        super(fm);
    }
}
