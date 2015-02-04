package edu.uw.aad.mzm.sample.fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author margaret
 * February 22, 2014
 * Sample Code that demonstrates the concept of fragment & actionbar 
 */
public class MainActivity extends ActionBarActivity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        Button buttonDynamicFragment = (Button)findViewById(R.id.buttonShowHideFragActivity);
		buttonDynamicFragment.setOnClickListener(this);

        findViewById(R.id.buttonNewActivity).setOnClickListener(this);
		
		// add word of day fragment by code
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		WordofDayFragment fragment = new WordofDayFragment();
		fragmentTransaction.add(R.id.fragment_container, fragment);
		fragmentTransaction.commit();
	}
	
	/**
	 * onClick for each button
	 */
	@Override
	public void onClick(View v) {
		Intent i = new Intent();

		switch(v.getId()){
            // go to an activity that demos how to add a fragment dynamically
			case R.id.buttonShowHideFragActivity:
				i.setClass(this, ShowHideFragmentActivity.class);
				startActivity(i);
				break;
            // go to an activity that was created during class
            case R.id.buttonNewActivity:
                i.setClass(this, NewActivity.class);
                startActivity(i);
                break;
		}
		
	}

}
