package edu.uw.aad.mzm.sample.animation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class ViewAnimationActivity extends ActionBarActivity
         implements Animation.AnimationListener{

    private ImageView mImageView;
    private Animation animation1;
    private Animation animation2;
    private boolean isBackOfCardShowing = true;
    private Button mButtonFlip;
    private Bitmap bitmapFront;
    private Bitmap bitmapBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animation);

        bitmapFront = BitmapFactory.decodeResource(getResources(), R.drawable.card_front);
        bitmapBack = BitmapFactory.decodeResource(getResources(), R.drawable.card_back);

        mImageView = (ImageView)findViewById(R.id.imageViewAndroid1);
        animation1 = AnimationUtils.loadAnimation(this, R.anim.to_middle);
        animation1.setAnimationListener(this);
        animation2 = AnimationUtils.loadAnimation(this, R.anim.from_middle);
        animation2.setAnimationListener(this);

        mButtonFlip = (Button)findViewById(R.id.buttonFlip);

        mButtonFlip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setEnabled(false);
                mImageView.clearAnimation();
                mImageView.setAnimation(animation1);
                mImageView.startAnimation(animation1);

            }
        });

    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

        if (animation==animation1) {
            // If back of the card is showing
            if (isBackOfCardShowing) {
                // Set card image to front
                mImageView.setImageBitmap(bitmapFront);
            } else {
                // Otherwise set card image to back
                mImageView.setImageBitmap(bitmapBack);
            }
            mImageView.clearAnimation();
            mImageView.setAnimation(animation2);
            mImageView.startAnimation(animation2);
        } else {
            isBackOfCardShowing=!isBackOfCardShowing;
            mButtonFlip.setEnabled(true);
        }

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

}
