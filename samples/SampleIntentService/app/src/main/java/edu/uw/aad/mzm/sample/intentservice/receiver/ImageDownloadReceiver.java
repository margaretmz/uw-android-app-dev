package edu.uw.aad.mzm.sample.intentservice.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Margaret on 3/9/2015.
 */
public class ImageDownloadReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        // Get image file location
        String fileLocation = intent.getExtras().getString("file_location");

        // Make a toast with image location
        Toast.makeText(context, "Image download to " + fileLocation, Toast.LENGTH_LONG).show();
    }
}
