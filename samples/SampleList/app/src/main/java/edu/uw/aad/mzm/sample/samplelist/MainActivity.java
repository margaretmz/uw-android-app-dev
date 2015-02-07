package edu.uw.aad.mzm.sample.samplelist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

/**
 * Created by Margaret on 2/2/2015
 * This sample app demos how to create lists in Android
 */
public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.buttonSimpleList).setOnClickListener(this);
        findViewById(R.id.buttonPrettyList).setOnClickListener(this);
        findViewById(R.id.buttonFancyList).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent();

        switch (v.getId()) {
            case R.id.buttonSimpleList:
                intent.setClass(this, SimpleListActivity.class);
                break;
            case R.id.buttonPrettyList:
                intent.setClass(this, PrettyListActivity.class);
                break;
            case R.id.buttonFancyList:
                intent.setClass(this, FancyListActivity.class);
                break;
        }

        startActivity(intent);
    }
}
