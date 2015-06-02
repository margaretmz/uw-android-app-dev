package edu.uw.aad.mzm.sample.media;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Margaret on 6/1/2015
 */
public class MainActivity extends ActionBarActivity implements
        View.OnClickListener{


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout ll = (LinearLayout) this.findViewById(R.id.mainLinearLayout);
        int childCount = ll.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ll.getChildAt(i).setOnClickListener(this);
        }


    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent();
        switch (v.getId()) {

            case R.id.buttonPlayAudioActivity:
                intent.setClass(this, AudioPlayActivity.class);
                break;
            case R.id.buttonRecordAudioActivity:
                intent.setClass(this, AudioRecordActivity.class);
                break;
            case R.id.buttonPlayVideoActivity:
                intent.setClass(this, VideoPlayActivity.class);
                break;
            case R.id.buttonRecordVideoActivity:
                intent.setClass(this, VideoRecordActivity.class);
                break;

        }

        startActivity(intent);

    }

}
