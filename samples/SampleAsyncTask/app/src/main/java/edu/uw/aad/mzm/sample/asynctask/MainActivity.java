package edu.uw.aad.mzm.sample.asynctask;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Margaret on 3/8/2015
 * This sample app shows how to download an image from the internet using AsyncTask
 */
public class MainActivity extends ActionBarActivity {
    private ImageView imageView;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView)findViewById(R.id.imageView);
        url = "http://developer.android.com/images/brand/Android_Robot_100.png";
        findViewById(R.id.buttonDownload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a ImageDownloadTask object, pass the activity context and ImageView through constructor
                ImageDownloadTask imageDownloadTask = new ImageDownloadTask(MainActivity.this, imageView);
                // Execute the AsyncTask and pass in the url
                imageDownloadTask.execute(url);
            }
        });
    }
}
