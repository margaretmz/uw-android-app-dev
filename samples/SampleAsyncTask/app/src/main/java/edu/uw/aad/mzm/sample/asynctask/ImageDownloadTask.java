package edu.uw.aad.mzm.sample.asynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Margaret on 3/8/2015.
 *
 * 1. Create a class that extends AsyncTask
 * 2. Override doInBackground() to download an image from internet
 * 3. Start showing a progress dialog onPreExecute()
 * 4. Dismiss the progress dialog onPostExecute(), update UI (show the downloaded bitmap in UI)
 * 5. Don't forget to add internet permission to AndroidManifest.xml
 */
public class ImageDownloadTask extends AsyncTask<String, Void, Bitmap> {

    private static final String LOG_TAG = ImageDownloadTask.class.getSimpleName();
    private final Context mContext;
    private ProgressDialog mProgressDialog;
    private ImageView mImageView;

    public ImageDownloadTask(Context context, ImageView imageView) {
        mContext = context;
        mImageView = imageView;
    }

    /**
     * Show a progress dialog
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Show a progress dialog
        mProgressDialog = ProgressDialog.show(mContext, "Please wait...", "Downloading a image from the internet...");
    }

    /**
     * Download image in the background
     * @param params
     * @return
     */
    @Override
    protected Bitmap doInBackground(String... params) {
        InputStream inputStream = null;
        Bitmap bitmap = null;
        URL url = null;
        HttpURLConnection urlConnection = null;

        // Let's simulate a slow network so that we can see the progress bar
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Log.e(LOG_TAG, "Interrupted - " + e.getMessage());
        } catch (Exception e) {
            Log.e(LOG_TAG, "Exception - " + e.getMessage());
        }

        // Download image from network
        try {
            url = new URL(params[0]);
            // Open connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            int responseCode = urlConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inputStream = new BufferedInputStream(url.openStream());
                // Convert inputStream to bitmap
                bitmap = BitmapFactory.decodeStream(inputStream);
                inputStream.close();
            } else {
                Log.e(LOG_TAG, "Error downloading image. Server response code is: " + responseCode);
                return null;
            }
        } catch (Exception e) {
            Log.e(LOG_TAG, "Error downloading image: " + e.getMessage());
        } finally {
            // Close connection
            if(urlConnection !=null) {
                urlConnection.disconnect();
            }
        }

        return bitmap;
    }

    /**
     * Update the UI after the background task is done
     * @param bitmap
     */
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        // Dismiss progress dialog if it's showing
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }

        if (mImageView != null) {
            Toast.makeText(mContext, "Image download finished.", Toast.LENGTH_LONG).show();
            mImageView.setImageBitmap(bitmap);
        } else {
            Toast.makeText(mContext, "Error setting image", Toast.LENGTH_LONG).show();
        }
    }
}
