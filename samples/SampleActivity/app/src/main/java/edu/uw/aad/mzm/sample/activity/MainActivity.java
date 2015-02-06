 package edu.uw.aad.mzm.sample.activity;

 import android.os.Bundle;
 import android.support.v7.app.ActionBarActivity;
 import android.util.Log;
 import android.view.Menu;
 import android.view.MenuItem;

 /**
  * Created by Margaret on 1/12/2015
  * This sample app shows you how to log Android Activity lifecycle
  */
public class MainActivity extends ActionBarActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "onStart() is called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop() is called");
    }

    @Override
	protected void onResume()
	{
		super.onResume();
        Log.d(LOG_TAG, "onResume() is called");
	}

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause() is called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy() is called");
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
