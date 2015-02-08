package edu.uw.aad.mzm.sample.toolbar;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by margaret on 2/6/2015
 *
 * This sample app shows you how to create a toolbar as an ActionBar
 *
 * 1. Add ToolBar directly in layout xml (or create a separate layout file, then use include)
 * 2. Remove actionbar - in styles.xml  <style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar">
 * 3. Replace actionBar with toolBar in code
 * 4. Change the main.xml root to be a LinearLayout and below tool create another container (with margins) to hold the main content
 * 5. Configure the ActionBar menu in menu_main.xml if needed
 */
public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        // Replace actionBar with the toolBar
        setSupportActionBar(toolbar);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
