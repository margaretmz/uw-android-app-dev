package edu.uw.aad.mzm.sample.media;

import android.widget.MediaController;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.VideoView;
import java.io.File;

public class VideoPlayActivity extends ActionBarActivity {

    private VideoView mVideoView;
    private MediaController mMediaController;
    private File mVideoFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);

        // Create a video file (located at root of external storage)
        mVideoFile = new File(Environment.getExternalStorageDirectory(), "VIDEO0063.mp4");

        if(mVideoFile.exists()) {

            playVideo();

        }
    }

    /**
     * Play video with VideView
     */
    private void playVideo() {

        mVideoView = (VideoView)findViewById(R.id.videoView);
        mVideoView.setVideoPath(mVideoFile.getAbsolutePath());

        mMediaController = new MediaController(this);
        mMediaController.setMediaPlayer(mVideoView);

        mVideoView.setMediaController(mMediaController);
        mVideoView.requestFocus();
        mVideoView.start();
    }

}
