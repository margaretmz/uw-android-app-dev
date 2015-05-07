package edu.uw.aad.mzm.sample.gesture;

import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.Toast;
import java.util.ArrayList;


public class CustomGesturesActivity extends ActionBarActivity
        implements GestureOverlayView.OnGesturePerformedListener {

    private GestureLibrary mLibrary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_gestures);
        // Build the gesture library from raw resource
        mLibrary = GestureLibraries.fromRawResource(this, R.raw.gestures);
        if (!mLibrary.load()) {
            finish();
        }

        // Find the GestureOverlayView
        GestureOverlayView gestures = (GestureOverlayView) findViewById(R.id.gestures);
        // Add OnGesturePerformedListener
        gestures.addOnGesturePerformedListener(this);
    }

    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
        ArrayList<Prediction> predictions = mLibrary.recognize(gesture);

        // We want at least one prediction
        if (predictions.size() > 0) {
            Prediction prediction = predictions.get(0);
            // We want at least some confidence in the result
            if (prediction.score > 1.0) {
                // Show the gesture
                Toast.makeText(this, prediction.name, Toast.LENGTH_SHORT).show();
            }
        }
    }

}
