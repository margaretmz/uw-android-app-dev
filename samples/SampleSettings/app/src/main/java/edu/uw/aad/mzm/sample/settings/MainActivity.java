package edu.uw.aad.mzm.sample.settings;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by margaret on 2/6/2015
 * This sample app shows you how to create a Settings screen
 *
 * 1. Create folder /xml under values
 * 2. Create a xml file called settings.xml with root PreferenceScreen (create strings in Strings.xml)
 * 3. Create SettingsFragment that extends from PreferenceFragment
 * 4. Create SettingsActivity to host SettingsFragment
 * 5. Add menu item (menu_main.xml & MainActivity.java) to access the Settings screen
 * 6. Update MainActivity.java to reflect what users has chosen on the Settings screen
 *
 */
public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        String country = prefs.getString("country", "");
        String city = prefs.getString("city", "");
        boolean getNotification = prefs.getBoolean("notification", true);

        TextView textViewNotification = (TextView)findViewById(R.id.textViewNotification);
        TextView textViewCountry = (TextView)findViewById(R.id.textViewCountry);
        TextView textViewCity = (TextView)findViewById(R.id.textViewCity);

        // Notification choice
        if(getNotification) {
            textViewNotification.setText("Yes");
        } else {
            textViewNotification.setText("No");
        }

        // Country
        textViewCountry.setText(country);

        // City
        textViewCity.setText(city);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent =  new Intent(this,SettingsActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
