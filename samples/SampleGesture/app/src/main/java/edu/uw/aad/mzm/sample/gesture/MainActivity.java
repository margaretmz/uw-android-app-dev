package edu.uw.aad.mzm.sample.gesture;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by Margaret on 5/4/2015
 *
 * This sample app demos
 * - the basic gestures using GestureDetector
 * - use custom gestures
 *
 * For the custom gestures:
 * - run Android emulator and use GestureBuilder app to record/save gestures
 * - pull the gestures file from the emulator
 * - move the gestures file to res/raw in this project
 * - build and run this sample app, you will then see your custom gestures
 */

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        findViewById(R.id.buttonCommonGestures).setOnClickListener(this);
        findViewById(R.id.buttonCustomGestures).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent();

        switch (v.getId()) {
            case R.id.buttonCommonGestures:
                intent.setClass(this, CommonGesturesActivity.class);
                break;
            case R.id.buttonCustomGestures:
                intent.setClass(this, CustomGesturesActivity.class);
                break;
        }

        startActivity(intent);

    }
}
