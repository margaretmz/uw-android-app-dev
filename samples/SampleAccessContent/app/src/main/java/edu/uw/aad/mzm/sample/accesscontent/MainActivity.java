package edu.uw.aad.mzm.sample.accesscontent;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

/**
 * Created by Margaret on 2/23/2015
 * This sample app demos how to create access contact Provider in Android
 *
 * 1. Request READ_CONTRACTS permission in AndroidManifest.xml
 * 2. Use ContentResolver to query the Contacts Provider
 *
 * Note: preferably we use a CursorLoader to access Content Providers
 */
public class MainActivity extends ActionBarActivity {

    private SimpleCursorAdapter mAdapter;

    public static final String[] PROJECTION = new String[] {
            ContactsContract.Contacts._ID,
            ContactsContract.CommonDataKinds.Email.DISPLAY_NAME_PRIMARY,        // Contact Name
            ContactsContract.CommonDataKinds.Email.DATA,};                      // Contact Email address

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ContentResolver contentResolver = this.getContentResolver();
        Cursor cursor = contentResolver.query(
                ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                PROJECTION,
                null,
                null,
                null);

        // Setup our mapping from the cursor result to the display field
        String[] from = {ContactsContract.CommonDataKinds.Email.DISPLAY_NAME_PRIMARY, ContactsContract.CommonDataKinds.Email.DATA};
        int[] to = { android.R.id.text1, android.R.id.text2 };

        // Create a simple cursor adapter, use SimpleCursorAdapter directly for demo purpose only
        // This constructor has been deprecated since we should use a CursorLoader
        mAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor, from, to);

        ListView listViewContacts = (ListView)findViewById(R.id.listViewContacts);
        listViewContacts.setAdapter(mAdapter);
    }

}
