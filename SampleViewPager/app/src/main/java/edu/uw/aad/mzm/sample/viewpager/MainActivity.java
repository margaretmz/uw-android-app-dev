package edu.uw.aad.mzm.sample.viewpager;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by Margaret on 2/4/2015
 * This is a sample app demo how to create a ViewPager in Android
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
