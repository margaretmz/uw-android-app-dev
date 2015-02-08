package edu.uw.aad.mzm.sample.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * 
 * Demo how you find a fragment by id, show/hide it
 * Note this extends Activity, not ActionBarActivity
 * Note on this screen, Fragment, FragmentManager etc. are not from the support library
 *
 */
public class ShowHideFragmentActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_showhidefrag);

		// The content view embeds the answer key as a fragment
		// now retrieve the fragment and attach their "hide" button.
		FragmentManager fm = getFragmentManager();
		addShowHideListener(R.id.buttonShowHide, fm.findFragmentById(R.id.fragment_answerkey));
	}

	void addShowHideListener(int buttonId, final Fragment fragment) {
		final Button button = (Button) findViewById(buttonId);
		button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				ft.setCustomAnimations(android.R.animator.fade_in,
						android.R.animator.fade_out);
				if (fragment.isHidden()) {
					ft.show(fragment);
					button.setText("Hide Answer Key");
				} else {
					ft.hide(fragment);
					button.setText("Show Answer Key");
				}
				ft.commit();
			}
		});
	}
	/**
	 * a fragment that shows the answer key on {@link ShowHideFragmentActivity}
	 *
	 */
	public static class AnswerKeyFragment extends Fragment {

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			return inflater.inflate(R.layout.fragment_answerkey, container,
					false);
			
		}

	}
}
