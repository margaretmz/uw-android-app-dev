package edu.uw.aad.mzm.sample.viewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * A {@link android.support.v4.app.FragmentPagerAdapter} provides a fragment for each tab
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
	
	public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        Fragment fragment = new SectionFragment();
        Bundle args = new Bundle();
        args.putInt(SectionFragment.ARG_SECTION_NUMBER, i + 1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
    	
//        return "Section " + (position + 1);
    	switch(position) {
    	case 0:
    		return "Colors";
		case 1:
    		return "Numbers";
		case 2:
    		return "Holidays";
    	}
    	return null;
    }
}