package edu.uw.aad.mzm.sample.samplelist;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * A simple list that uses Android default list item layout
 */
public class SimpleListActivity extends ListActivity {

//    private String[] androidAPIsShortList = {"ICS", "Jelly Bean", "Kitkat", "Lollipop"};
    private String[] mAndroidAPIs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_list);

        // get the string array resources
        mAndroidAPIs = getResources().getStringArray(R.array.androidapis);

//        ListView listView = (ListView) findViewById(R.id.listViewSimple);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,               // Context
                                            android.R.layout.simple_list_item_1,    // default Android layout for the list item
                mAndroidAPIs);                           // data to show in the list
//        listView.setAdapter(adapter);
        this.setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Toast.makeText(this, "You clicked on " + mAndroidAPIs[position], Toast.LENGTH_SHORT).show();
    }
}
