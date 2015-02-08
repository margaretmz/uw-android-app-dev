package edu.uw.aad.mzm.sample.viewpager;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by Margaret on 2/4/2015
 * This is a sample app demos how to create a ViewPager in Android
 * 1. Create SectionFragment
 * 2. Create SectionsPagerAdapter extends from FragmentPagerAdapter
 * 3. In activity_main.xml add ViewPager and PagerTabStrip
 * 4. In MainActivity.java set up the ViewPager and the FragmentPagerAdapter
 */
public class MainActivity extends ActionBarActivity {

    private SectionsPagerAdapter mAppSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the adapter that will return a fragment for each ViewPager section
        mAppSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager
        mViewPager = (ViewPager) findViewById(R.id.pager);
        // Attach the adapter to ViewPager
        mViewPager.setAdapter(mAppSectionsPagerAdapter);
    }
}
