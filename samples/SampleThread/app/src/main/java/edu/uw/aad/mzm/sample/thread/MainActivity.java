package edu.uw.aad.mzm.sample.thread;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Margaret ton 3/9/2015
 * This sample app shows 3 ways to communicate from non UI thread back to UI:
 * 1. use Activity.runOnUiThread(Runnable)
 * 2. use View.post(Runnable)
 * 3. use a Handler
 */
public class MainActivity extends ActionBarActivity {

    private TextView mTextView;
    private ProgressDialog mProgressDialog;
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mTextView.setText("TextView updated by Handler");
            dismissProgressDialog();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView)findViewById(R.id.textView);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("Please wait...");
        mProgressDialog.setMessage("Updating text...");

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show a Progress Dialog
                mProgressDialog.show();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {

                        // Simulate a slow network
                        try {
                            Thread.sleep(10000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        // This doesn't work, can't update UI from a non-UI thread
//                        mTextView.setText("Let's try to set text from the new thread");

//                        updateUIWithRunOnUiThread();
//                        updateUIWithPost();
                        updateUIWithHandler();

                    }
                }; // end runnable

                Thread thread = new Thread(runnable);
                thread.start();

            } // end onclick
        });
    }

    /**
     * Use Activity.runOnUiThread() to update UI
     */
    private void updateUIWithRunOnUiThread() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTextView.setText("TextView updated by runOnUiThread");
                dismissProgressDialog();
            }
        });
    }

    /**
     * Use View.post to update UI
     */
    private void updateUIWithPost() {
        mTextView.post(new Runnable() {
            @Override
            public void run() {
                mTextView.setText("TextView updated by View.post(Runnable)");
                dismissProgressDialog();
            }
        });
    }

    /**
     * Use a Handler to update UI
     */
    private void updateUIWithHandler() {
        mHandler.sendEmptyMessage(0);
    }

    /**
     * Dismiss Progress Dialog if showing
     */
    private void dismissProgressDialog() {
        if(mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
