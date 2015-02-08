package edu.uw.aad.mzm.sample.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;

/**
 * A simple {@link android.app.Fragment} subclass
 * shows word of day, a random word from the study list
 * demonstrates how fragment can be added by code
 */


public class WordofDayFragment extends Fragment {

	public WordofDayFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_wordofday, container, false);
		
		TextView wordOfDay = (TextView)view.findViewById(R.id.wordofday_content);
		
		// get a list of holiday words
		// for demo purpose: data is hard-coded in string.xml
		String[] words = getResources().getStringArray(R.array.holidays);
		
		// generate a random number from the word list
		int numOfWords =words.length;		
		Random random = new Random();
		int i  = random.nextInt(numOfWords);
		
		// display this one random word
		wordOfDay.setText(words[i]);
		
		return view;
	}

}
