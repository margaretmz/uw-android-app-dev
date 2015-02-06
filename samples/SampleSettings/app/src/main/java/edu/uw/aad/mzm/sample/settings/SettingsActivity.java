package edu.uw.aad.mzm.sample.settings;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * This activity hosts {@link edu.uw.aad.mzm.sample.settings.SettingsFragment}
 */

public class SettingsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Display SettingsFragment as the main content
        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new SettingsFragment())
                .commit();
    }

}
