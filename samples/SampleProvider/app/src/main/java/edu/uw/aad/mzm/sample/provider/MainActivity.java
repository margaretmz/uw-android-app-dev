package edu.uw.aad.mzm.sample.provider;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

/**
 * Created by Margaret on 4/21/2015
 *
 * 1. Create a new project with blank activity with fragment
 * 2. Copy over the Contract & DbHelper classes from SampleDatabase (Note we no longer need the AndroidVersion object class)
 * 3. Create a ContentProvider class
 * 4. Add Content Authority, Path, Content URI, Content Type (for single & multiple items)
 * 5. Add URIMatcher
 * 6. Implement the query(), insert(), update(), delete(), getTYpe() methods in Content Provider
 * 7. Register Content Provider in AndroidManifest.xml
 * 8. Update fragment classes to use CursorLoader to load data from ContentProvider
 *
 */
public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MainFragment())
                    .commit();
        }
    }

}
