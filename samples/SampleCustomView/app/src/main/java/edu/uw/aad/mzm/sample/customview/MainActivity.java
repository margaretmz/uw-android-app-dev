package edu.uw.aad.mzm.sample.customview;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

/**
 * Created by Margaret ton 5/4/2015
 *
 * This app demos how to create a Custom View
 *
 * 1. Create a class extends from View
 * 2. Add the View to activity_main.xml with fully qualified name
 */
public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}
