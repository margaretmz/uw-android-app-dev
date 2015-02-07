package edu.uw.aad.mzm.sample.actionbar;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class AboutActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Show the up affordance in the action bar
        // Click on the back button will take you up the hierarchy (back to the parent)
        // In this case parent is MainActivity.java, as defined in AndroidManifest.xml
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}
