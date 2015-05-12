package edu.uw.aad.mzm.sample.viewflipper;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;


public class MainActivity extends ActionBarActivity {

    private ImageView mImageView;
    private Button mButtonNext;
    private Button mButtonPrevious;
    private ViewFlipper mViewFlipper;

    private static final int[] images = { R.drawable.apple, R.drawable.grape, R.drawable.pear };
    private int current = 0;

    private GestureDetector mGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = (ImageView) findViewById(R.id.imageview);
        mButtonNext = (Button) findViewById(R.id.next);
        mButtonPrevious = (Button) findViewById(R.id.previous);

        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next();
            }
        });

        mButtonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previous();
            }

        });

        mGestureDetector = new GestureDetector(this, new SwipeGesture());

        mViewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean value = mGestureDetector.onTouchEvent(event);
        return value;
    }

    /**
     * Show the previous image
     */
    private void previous() {
        current = (--current+images.length)%images.length;
        mImageView.setImageResource(images[current]);
        Animation animationSlideInLeft = AnimationUtils.loadAnimation(this, R.anim.slide_in_left);
        mViewFlipper.setInAnimation(animationSlideInLeft);
        mViewFlipper.showPrevious();
    }

    /**
     * Show the next image
     */
    private void next() {
        current = (++current)%images.length;
        mImageView.setImageResource(images[current]);
        Animation animationSlideInRight = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);
        mViewFlipper.setInAnimation(animationSlideInRight);
        mViewFlipper.showNext();
    }

    class SwipeGesture extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {
            // If fling from left to right
            if(velocityX>0 && Math.abs(velocityX)>Math.abs(velocityY)) {
                previous();
                return true;
                // If fling from right to left
            } else if(Math.abs(velocityX)>Math.abs(velocityY)) {
                next();
                return true;
            }

            return false;
        }
    }
}
