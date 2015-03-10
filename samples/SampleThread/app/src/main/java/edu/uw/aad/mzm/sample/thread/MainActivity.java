package edu.uw.aad.mzm.sample.thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;



public class MainActivity extends ActionBarActivity {

    private TextView mTextView;

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mTextView.setText("New text by Handler");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView)findViewById(R.id.textView);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {

                        // Simulate a slow network
                        try {
                            Thread.sleep(10000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        // This doesn't work, can't update UI from here.
//                        mTextView.setText("Let's try to set text from the new thread");

/*                        mTextView.post(new Runnable() {
                            @Override
                            public void run() {

                                mTextView.setText("New Text by View.post(Runable)");

                            }
                        });*/

                        updateUIwithrunOnUiThread();

//                        updateUIwithHandler();

                    }
                }; // end runnable

                Thread thread = new Thread(runnable);
                thread.start();

            } // end onclick
        });
    }

    private void updateUIwithHandler() {
        mHandler.sendEmptyMessage(0);
    }

    /**
     * Use runOnUiThread() to update UI
     */
    private void updateUIwithrunOnUiThread() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTextView.setText("Text updated by runOnUiThread");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
