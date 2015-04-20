package edu.uw.aad.mzm.sample.masterdetail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import edu.uw.aad.mzm.sample.masterdetail.data.Content;

/**
 * A fragment representing a single AndroidVersion detail screen.
 * This fragment is either contained
 * in a {@link MainActivity} in two-pane mode (on tablets) or
 * in a {@link VersionDetailActivity} on handsets.
 */
public class VersionDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The AndroidVersion this fragment is presenting.
     */
    private Content.AndroidVersion mAndroidVersion;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public VersionDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the sample content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mAndroidVersion = Content.VERSION_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        // Show the Android Version details
        if (mAndroidVersion != null) {
            ((TextView) rootView.findViewById(R.id.textViewCodeName)).setText("Code Name: " + mAndroidVersion.codeName);
            ((TextView) rootView.findViewById(R.id.textViewVersionNo)).setText("Version No: " + mAndroidVersion.versionNo);
            ((TextView) rootView.findViewById(R.id.textViewApiLevel)).setText("API Level: " + mAndroidVersion.apiLevel);
            ((TextView) rootView.findViewById(R.id.textViewReleaseDate)).setText("Release Date: " + mAndroidVersion.releaseDate);
            ((TextView) rootView.findViewById(R.id.textViewFeatures)).setText("Features: " + mAndroidVersion.features);
        }

        return rootView;
    }
}
