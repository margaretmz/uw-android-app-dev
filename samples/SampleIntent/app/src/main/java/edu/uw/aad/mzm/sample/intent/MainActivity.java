package edu.uw.aad.mzm.sample.intent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Margaret on 3/1/2015
 * This sample app shows a couple of use cases of Android (implicit) intent
 */
public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.buttonCallPhone).setOnClickListener(this);
        findViewById(R.id.buttonViewURL).setOnClickListener(this);
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
        if (id == R.id.action_share) {
            shareText();
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Share plain text
     */
    private void shareText() {

        Intent intent = new Intent(Intent.ACTION_SEND);
//        intent.setType("vnd.android-dir/mms-sms");                  // sms
//        intent.setType("text/plain");                             // plain text
        intent.setType("text/html");                              // html
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "What is an Intent?");                    // set email subject
        intent.putExtra(android.content.Intent.EXTRA_TEXT, "Today we're are learning about intent..."); // set email body

        try {
            // Bring up a list of apps that can handle the intent
            startActivity(Intent.createChooser(intent, "Share with"));
        } catch (android.content.ActivityNotFoundException e) {
            // Tell user no app can handle the intent
            Toast.makeText(getApplicationContext(), "No app can be used to share", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent();

        switch (v.getId()) {
            case R.id.buttonCallPhone:
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:425-1234-5678"));
                break;
            case R.id.buttonViewURL:
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.google.com"));
                break;
            default:
                break;
        }

        startActivity(intent);

    }
}
