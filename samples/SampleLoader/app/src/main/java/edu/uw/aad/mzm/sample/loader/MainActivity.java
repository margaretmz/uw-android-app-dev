package edu.uw.aad.mzm.sample.loader;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.SimpleCursorAdapter;


/**
 * Created by Margaret on 4/27/2015
 * Sample App that demos how to load Contacts Content Provider with a CursorLoader
 *
 * 1. Create a class extends from ListActivity
 * 2. Use a SimpleCursorAdapter for the list
 * 3. Implement LoaderManager.LoaderCallbacks<Cursor>
 * 4. Initialize loader
 * 5. Implement onCreateLoader(), on LoadFinished() and onLoaderReset()
 */
public class MainActivity extends ListActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private SimpleCursorAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // The data from Android Contacts Content Provider
        String[] from = {ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.CommonDataKinds.Email.DATA};
        // UI fields of list item in the ListView, use Android default
        int[] to = {android.R.id.text1, android.R.id.text2};

        mAdapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2,        // Android default list item layout
                null,
                from,
                to,
                0
                );

        setListAdapter(mAdapter);

        // Initialized Loader for the background query
        getLoaderManager().initLoader(0, null, this);
    }


    /**
     * Create CursorLoader against Content Provider,
     * & Start the background query
     * @param id
     * @param args
     * @return
     */
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        String[] projection = new String[] {
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Email.DATA
        };

        String selection = "((" + ContactsContract.Contacts.DISPLAY_NAME + " NOTNULL) AND ("
                + ContactsContract.Contacts.DISPLAY_NAME + " != '' ))";

        // Returns a new CursorLoader
        return new CursorLoader(this,
                ContactsContract.CommonDataKinds.Email.CONTENT_URI, // make sure the set the correct Content URI
                projection,
                selection,
                null,
                ContactsContract.Contacts.DISPLAY_NAME   // sort on display name
                );
    }

    /**
     * Background query complete
     * @param loader
     * @param data
     */
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        // Move query results to adapter and refresh UI
        mAdapter.swapCursor(data);

    }

    /**
     * Invoked when the CursorLoader is being reset.
     * Called if the data in Content Provider changes and the Cursor becomes stale or invalid
     * @param loader
     */
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        // Remove the adapter's reference to the cursor to prevent memory leak
        mAdapter.swapCursor(null);

    }
}
