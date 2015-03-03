package edu.uw.aad.mzm.sample.navdrawer;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Created by Margaret on 2/27/2015
 *
 * This demos how to create the Navigation Drawer, and a Toolbar as top ActionBar
 */
public class MainActivity extends ActionBarActivity {

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private RelativeLayout mDrawerRL;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private String[] mNavDrawerItems = {"Item 1", "Item 2", "Item 3", "Item 4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        if(mToolbar!=null) {
            mToolbar.setTitle(getString(R.string.app_name));
            // Set up the Toolbar as the ActionBar
            setSupportActionBar(mToolbar);
            mToolbar.setLogo(R.mipmap.ic_launcher);
        }

        initDrawer();
    }


    private void initView() {

        // toolbar
        mToolbar = (Toolbar)findViewById(R.id.toolbar);

        // DrawerLayout
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);

        // RelativeLayout container that holds the Nav drawer menu
        mDrawerRL = (RelativeLayout)findViewById(R.id.drawerRL);

        // Nav drawer ListView
        mDrawerList = (ListView)findViewById(R.id.drawerList);

        // Set the adapter for drawer list
        mDrawerList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mNavDrawerItems));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            selectItem(position);
        }
    }

    /**
     * Handle drawer item selection
     */
    private void selectItem(int position) {

        Toast.makeText(MainActivity.this, "Selected " + mNavDrawerItems[position], Toast.LENGTH_LONG).show();

        // Highlight the selected drawer item
        mDrawerList.setItemChecked(position, true);

        // Close drawer
        mDrawerLayout.closeDrawer(mDrawerRL);

    }

    /**
     * Initialize the navigation drawer
     */
    private void initDrawer() {

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
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
