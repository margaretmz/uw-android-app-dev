package edu.uw.aad.mzm.sample.animation;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class PropertyAnimationActivity extends ActionBarActivity implements View.OnClickListener {

    private ImageView mImageViewAndroid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animation);

        mImageViewAndroid = (ImageView)findViewById(R.id.imageViewAndroid);

        int[] buttonIds = {R.id.buttonGrow,
                            R.id.buttonShrink,
                            R.id.buttonFadeIn,
                            R.id.buttonFadeOut,
                            R.id.buttonRotateX,
                            R.id.buttonRotateY};

        for(int id: buttonIds) {
            Button button = (Button)findViewById(id);
            button.setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonFadeIn:
                // Fade in the image
                mImageViewAndroid.animate().alpha(1);
                break;
            case R.id.buttonFadeOut:
                // Fade out the image
                mImageViewAndroid.animate().setDuration(2000).alpha(0);
                break;
            case R.id.buttonGrow:
                // Grow the image
                AnimatorSet growAnimation = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.grow);
                growAnimation.setTarget(mImageViewAndroid);
                growAnimation.start();
                break;
            case R.id.buttonShrink:
                // Shrink the image
                AnimatorSet shrinkAnimation = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.shrink);
                shrinkAnimation.setTarget(mImageViewAndroid);
                shrinkAnimation.start();
                break;
            case R.id.buttonRotateX:
                mImageViewAndroid.animate().setDuration(3000).rotationXBy(720);
                break;
            case R.id.buttonRotateY:
                mImageViewAndroid.animate().setDuration(1000).rotationYBy(720);
                break;

        }
    }
}
