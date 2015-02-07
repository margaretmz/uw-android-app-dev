package edu.uw.aad.mzm.sample.samplelist;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * A pretty list that uses my own list item layout
 */
public class PrettyListActivity extends ActionBarActivity {

//    private String[] androidAPIsShortList = {"ICS", "Jelly Bean", "Kitkat", "Lollipop"};
    private String[] androidAPIs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pretty_list);

        androidAPIs = getResources().getStringArray(R.array.androidapis);

        ListView listView = (ListView) findViewById(R.id.listViewPretty);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,            // Context
                                            R.layout.pretty_list_item,      // layout for the list item, use a default Android layout
                                            androidAPIs);                   // data to show in the list
        listView.setAdapter(adapter);
    }
}
