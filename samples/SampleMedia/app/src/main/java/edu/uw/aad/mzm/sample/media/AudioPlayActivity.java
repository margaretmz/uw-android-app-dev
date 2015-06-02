package edu.uw.aad.mzm.sample.media;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.media.SoundPool;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import java.io.IOException;
import java.util.HashMap;


public class AudioPlayActivity extends ActionBarActivity implements
        View.OnClickListener, SoundPool.OnLoadCompleteListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private SoundPool mSoundPool;

    private int mMixID;
    private int mAK47ID;

    private HashMap<Integer, SoundResource> mSoundResources = new HashMap<Integer, SoundResource>();

    private AudioManager mAudioManager;
    private MediaPlayer mMediaPlayer;
    private Ringtone mRingtone;

    private class SoundResource {

        public SoundResource(int id, boolean loaded, float volume) {
            this.id = id;
            this.loaded = loaded;
            this.volume = volume;
        }

        public boolean loaded;
        public int id;
        public float volume;
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_play);

        LinearLayout ll = (LinearLayout) this.findViewById(R.id.buttonLinearLayout);
        int childCount = ll.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ll.getChildAt(i).setOnClickListener(this);
        }

        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        mSoundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        mSoundPool.setOnLoadCompleteListener(this);

        AssetManager am = this.getAssets();
        AssetFileDescriptor afd;
        try {
            afd = am.openFd("street.wav");
            mMixID = mSoundPool.load(afd, 1);

            afd = am.openFd("ak47.mp3");
            mAK47ID = mSoundPool.load(afd, 1);

        } catch (IOException e) {
            e.printStackTrace();
        }

        mMediaPlayer = new MediaPlayer();

    }

    @Override
    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {

        mSoundResources.put(sampleId, new SoundResource(sampleId, true, 1f));
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.playAssetOgg :
                playAssetOgg();
                break;

            case R.id.playRawOgg:
                playRawOgg();
                break;

            case R.id.playHit :
                playHit();
                break;

            case R.id.playRingtone :
                playRingtone();
                break;

        }

    }

    private void playHit() {

        SoundResource sr = mSoundResources.get(mAK47ID);
        if (sr == null) {
            Log.e(TAG, "playHit() Could not find SoundResource for ID: " + mAK47ID);
            return;
        }

        if (sr.loaded) {
            mSoundPool.play(sr.id, 0.5f, 0.5f, 1, 0, 1f);
        }
    }

    /**
     * Play an audio .ogg file from /assets/
     */
    private void playAssetOgg() {

        if (mMediaPlayer.isPlaying()) {
            Log.w(TAG, "playAssetOgg() MediaPlayer already playing");
            mMediaPlayer.stop();
            return;
        }

        mMediaPlayer.reset();

        AssetFileDescriptor afd;
        try {
            afd = getAssets().openFd("ocean.ogg");
            mMediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(),afd.getLength());
            mMediaPlayer.prepare();
            mMediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Play an audio .ogg file from /res/raw/
     */
    private void playRawOgg() {

        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.bread);
        mediaPlayer.start();
    }

    /**
     * Play ringtone
     */
    private void playRingtone() {

        // Get an available ringtone
        Uri alarm = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarm == null) {
            alarm = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            if (alarm == null) {
                alarm = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
            }
        }

        // There wasn't a ringtone available
        if (alarm == null)
            return;

        mRingtone = RingtoneManager.getRingtone(this, alarm);

        if (mRingtone != null)
            mRingtone.play();
    }
}
