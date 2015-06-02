package edu.uw.aad.mzm.sample.media;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import java.io.File;


public class VideoRecordActivity extends ActionBarActivity {

    private static final int CAPTURE_VIDEO = 0;
    private File mVideoFile;
    private Uri mFileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_record);

        mVideoFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES),
                                "testVideo.mp4");
        
        findViewById(R.id.buttonRecordVideo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordVideo();
            }
        });
    }

    private void recordVideo() {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        mFileUri = Uri.fromFile(mVideoFile);

        //Designate where to put the video file
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mFileUri);
        //Set the video quality
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);

        try {
            startActivityForResult(intent, CAPTURE_VIDEO);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "No app found to record video", Toast.LENGTH_LONG).show();
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //If we successfully captured the video
        if(requestCode == CAPTURE_VIDEO && resultCode == RESULT_OK) {

            //Launch an app to view the video
            Intent intent = new Intent(Intent.ACTION_VIEW).setDataAndType(mFileUri, "video/mp4");
            startActivity(intent);
        }

        finish();
    }
}
