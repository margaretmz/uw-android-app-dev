package edu.uw.aad.mzm.sample.intentservice.service;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Margaret on 3/8/2015.
 *
 * 1 Create a class that extends IntentService
 * 2. Override onHandleIntent to download the image file
 */
public class ImageDownloadIntentService extends IntentService {

    private static final String LOG_TAG = ImageDownloadIntentService.class.getSimpleName();

    public static final String ACTION_DOWNLOAD_COMPLETE =
            "edu.uw.aad.mzm.sample.intentservice.DOWNLOAD_COMPLETE";

    // Make sure to have an empty constructor
    public ImageDownloadIntentService() {
        super(LOG_TAG);
    }

    public ImageDownloadIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Uri imageUri = intent.getData();
        String imageFileName = imageUri.getLastPathSegment();
        String imageURL = imageUri.toString();
        String fileLocation;

        try {
            // Create a File directory
            File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM + "/SampleIntentService");
            // Make the directories if needed
            dir.mkdirs();
            // Create the image file
            File imageFile = new File(dir, imageFileName);
            // Delete the image file if already exists
            if(imageFile.exists()) {
                imageFile.delete();
            }
            // Convert URL from String to URL object
            URL url = new URL(imageURL);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();

            FileOutputStream fileOutputStream = new FileOutputStream(imageFile.getPath());
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

            try {
                InputStream inputStream = httpURLConnection.getInputStream();
                byte[] buffer = new byte[1024];
                int length = 0;

                while((length=inputStream.read(buffer))>=0) {
                    bufferedOutputStream.write(buffer, 0, length);
                }

                bufferedOutputStream.flush();

            } finally {
                fileOutputStream.getFD().sync();
                bufferedOutputStream.close();
                fileLocation = imageFile.getAbsolutePath();
                httpURLConnection.disconnect();
            }

            notifyDownloadFinished(fileLocation);

        } catch (IOException e) {
            Log.e(LOG_TAG, "Error in downloading image file", e);
        }

    }

    /**
     * Send a local broadcast notifying the file download has been completed
     * @param fileLocation
     */
    private void notifyDownloadFinished(String fileLocation) {

        Intent intent = new Intent(ACTION_DOWNLOAD_COMPLETE);
        intent.putExtra("file_location", fileLocation);
        LocalBroadcastManager.getInstance(this)
                             .sendBroadcast(intent);
    }
}
