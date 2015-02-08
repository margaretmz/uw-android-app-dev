package edu.uw.aad.mzm.sample.viewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * A fragment representing a section of the ViewPager
 */
public class SectionFragment extends Fragment {

	public static final String ARG_SECTION_NUMBER = "section_number";
	private String[] words;
	private ListView listview;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_section, container,
				false);
		
		TextView textview = ((TextView) rootView.findViewById(android.R.id.text1));
		
		Bundle args = getArguments();
		int sectionNumber = args.getInt(ARG_SECTION_NUMBER);

		// get the string array of words
		switch (sectionNumber) {
		// 1st section with colors
		case 1:
			textview.setText("Words of colors.");
			words = getResources().getStringArray(R.array.colors);
			break;
		// 2nd section with numbers
		case 2:
			textview.setText("Numbers from one to ten");
			words = getResources().getStringArray(R.array.numbers);
			break;
		// 3rd section with holidays	
		case 3:
			textview.setText("Chinese & US holidays");
			words = getResources().getStringArray(R.array.holidays);
			break;
		default:
			words = getResources().getStringArray(R.array.numbers);
		}

		listview = (ListView) rootView.findViewById(R.id.listViewWords);
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
				getActivity().getBaseContext(),
				android.R.layout.simple_list_item_1, words);

		listview.setAdapter(arrayAdapter);

		return rootView;
	}
}