package edu.uw.aad.mzm.sample.animation;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Margaret 5/8/2015
 * This sample app demos:
 * 1. Drawable Animation
 * 2. View Animation
 * 3. Property Animation
 */
public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private ImageView mDroidImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Button for going to DrawableAnimationActivity.java
        findViewById(R.id.buttonDrawableAnim).setOnClickListener(this);
        findViewById(R.id.buttonViewAnim).setOnClickListener(this);
        findViewById(R.id.buttonPropertyAnim).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.buttonDrawableAnim:
                intent.setClass(this, DrawableAnimationActivity.class);
                break;
            case R.id.buttonViewAnim:
                intent.setClass(this, ViewAnimationActivity.class);
                break;
            case R.id.buttonPropertyAnim:
                intent.setClass(this, PropertyAnimationActivity.class);
                break;
        }

        startActivity(intent);

    }
}
