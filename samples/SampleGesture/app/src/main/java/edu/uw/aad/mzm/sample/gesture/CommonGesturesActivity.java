package edu.uw.aad.mzm.sample.gesture;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;


public class CommonGesturesActivity extends ActionBarActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private static final String LOG_TAG = CommonGesturesActivity.class.getSimpleName();
    private View mTouchView;
    private GestureDetector mGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_gestures);

        mGestureDetector = new GestureDetector(this, this);
        mTouchView = findViewById(R.id.touchView);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        Log.d(LOG_TAG, "onDown(): " + e.toString());
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.d(LOG_TAG, "onShowPress(): " + e.toString());
        Toast.makeText(this, "Long Press", Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.d(LOG_TAG, "onSingleTapUp): " + e.toString());
        Toast.makeText(this, "Single Tap", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

        Log.d(LOG_TAG, "onScroll: " + e1.toString() + e2.toString());
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {

        Log.d(LOG_TAG, "onLongPress: " + e.toString());
        Toast.makeText(this, "Long Press", Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        Log.d(LOG_TAG, "onFling: " + e1.toString() + e2.toString());
        Toast.makeText(this, "Fling", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        Log.d(LOG_TAG, "onSingleTapConfirmed: " + e.toString());
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Log.d(LOG_TAG, "onDoubleTap: " + e.toString());
        Toast.makeText(this, "Double Tap", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {

        Log.d(LOG_TAG, "onDoubleTapEvent: " + e.toString());
        return true;
    }
}
