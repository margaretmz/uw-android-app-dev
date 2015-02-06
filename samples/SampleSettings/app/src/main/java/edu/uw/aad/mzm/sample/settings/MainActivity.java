package edu.uw.aad.mzm.sample.settings;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

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
