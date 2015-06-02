package edu.uw.aad.mzm.sample.media;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * This activity creates audio a file
 */
public class AudioRecordActivity extends ActionBarActivity implements View.OnClickListener {

    private static final int CAPTURE_AUDIO = 0;

    private static final String TAG = AudioRecordActivity.class.getSimpleName();

    private MediaRecorder mMediaRecorder;
    private static String mFileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_record);

        findViewById(R.id.button1RecordAudio).setOnClickListener(this);
        findViewById(R.id.startAudioRecord).setOnClickListener(this);
        findViewById(R.id.stopAudioRecord).setOnClickListener(this);
        findViewById(R.id.playAudioRecord).setOnClickListener(this);

        mMediaRecorder = new MediaRecorder();
    }

    private void recordAudio() {

        Intent intent =new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);

        try {
            startActivityForResult(intent, CAPTURE_AUDIO);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "No app found to record audio", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAPTURE_AUDIO && resultCode == RESULT_OK) {
            Toast.makeText(this, "Finished recording audio", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button1RecordAudio:
                recordAudio();
                break;

            case R.id.playAudioRecord:
                playRecording();
                break;

            case R.id.startAudioRecord:
                startRecording();
                break;

            case R.id.stopAudioRecord:
                stopRecording();
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mMediaRecorder != null)
            mMediaRecorder.release();
    }

    private void playRecording() {

        File playFile = new File(mFileName);
        FileInputStream fis;
        if (playFile.exists()) {

            try {

                fis = new FileInputStream(playFile);

                MediaPlayer mp = new MediaPlayer();
                mp.setDataSource(fis.getFD());
                mp.prepare();
                mp.start();

                fis.close();

            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else {
            Log.e(TAG, "playRecording() File does not exist: " + mFileName);
        }
    }

    private void startRecording() {

        mMediaRecorder.reset();

        String fileName = UUID.randomUUID().toString().substring(0, 6).concat(".3gp");
        mFileName = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + fileName;

        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
        mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mMediaRecorder.setOutputFile(mFileName);
        mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mMediaRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mMediaRecorder.start();
    }

    private void stopRecording() {

        if (mMediaRecorder == null)
            return;

        mMediaRecorder.stop();
        mMediaRecorder = null;
    }

}
