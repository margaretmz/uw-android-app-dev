package edu.uw.aad.mzm.sample.intentservice.ui;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import edu.uw.aad.mzm.sample.intentservice.R;
import edu.uw.aad.mzm.sample.intentservice.receiver.ImageDownloadReceiver;
import edu.uw.aad.mzm.sample.intentservice.service.ImageDownloadIntentService;

/**
 * Created by Margaret on 3/8/2015
 *
 * This app shows how to use an IntentService to download a image from the Internet
 *
 * 1. Create an IntentService, which downloads the image
 * 2. In AndroidManifest.xml:
 *  - include permission for accessing the Internet
 *  - include permission for writing to external storage
 *  - register the service
 * 3. Create a BroadcastReceiver
 * 4. Register(unregister) the BroadcastReceiver in activity
 */
public class MainActivity extends ActionBarActivity {

    private IntentFilter mIntentFilter;
    private ImageDownloadReceiver mReceiver;
    private String mUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIntentFilter = new IntentFilter(ImageDownloadIntentService.ACTION_DOWNLOAD_COMPLETE);
        mReceiver = new ImageDownloadReceiver();
        mUrl = "http://developer.android.com/images/brand/Android_Robot_100.png";

        findViewById(R.id.buttonDownload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ImageDownloadIntentService.class);
                intent.setData(Uri.parse(mUrl));
                startService(intent);
            }
        });

    }

    /**
     * Register the receiver onResume()
     */
    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, mIntentFilter);
    }

    /**
     * Unregister the receiver onPause()
     */
    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
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
