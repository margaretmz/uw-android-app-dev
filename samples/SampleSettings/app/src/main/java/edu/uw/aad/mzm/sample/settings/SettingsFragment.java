package edu.uw.aad.mzm.sample.settings;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;

/**
 * A simple {@link Fragment} subclass.
 *
 * 1. Create a new Fragment that extends from PreferenceFragment
 * 2. Override onCreate() to load the UI from settings.xml (remove onCreateView() if using the Wizard to create Fragment)
 * 3. Use onSharedPreferenceChangedListener to listen for preference changes
 * 4. Clean-up (optional): Remove unused fragment_settings.xml
 */
public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from settings.xml
        addPreferencesFromResource(R.xml.settings);
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        Preference preference = findPreference(key);

        if(key.equals(getString(R.string.pref_key_country))) {
            // set preference summary with user choice
            preference.setSummary(sharedPreferences.getString(key, ""));
        }

        if(preference instanceof ListPreference) {
            ListPreference listPreference = (ListPreference) preference;
            // set preference summary with user choice (from list entries i.e. Settle)
            preference.setSummary(listPreference.getEntry());
        }
    }

    /**
     * Register OnSharedPreferenceChangeListener onResume()
     */
    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    /**
     * Unregister OnSharedPreferenceChangeListener onPause()
     */
    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

}
