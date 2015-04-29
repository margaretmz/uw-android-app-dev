package edu.uw.aad.mzm.sample.provider;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.uw.aad.mzm.sample.provider.data.AndroidContract;

/**
 *
 * This Fragment shows the details of an Android version
 *
 * A simple {@link Fragment} subclass.
 * /
 *
/** Activities that contain this fragment must implement the*/
/*  {@link DetailFragment.OnFragmentInteractionListener} */
/**interface to handle interaction events.*/

public class DetailFragment extends Fragment {

    private Uri mUri;
    private String version;
    private String name;
    private String versionNo;

//    private OnFragmentInteractionListener mListener;

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if(bundle!=null) {
            mUri = bundle.getParcelable("uri"); // get URI
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        Cursor c = getActivity().getContentResolver().query(mUri,
                AndroidContract.Version.PROJECTION,
                null,
                null,
                null);
        if(c.moveToFirst()) {
            name = c.getString(c.getColumnIndexOrThrow(AndroidContract.Version.CODE_NAME));
            versionNo = c.getString(c.getColumnIndexOrThrow(AndroidContract.Version.VERSION_NO));
        }
        //close the cursor
        c.close();

        if(name!=null && !TextUtils.isEmpty(name)) {
            ((TextView)view.findViewById(R.id.textViewCodeName)).setText(name);
            ((TextView)view.findViewById(R.id.textViewVersionNo)).setText(versionNo);
        }

        return view;
    }
/*
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    *//**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     *//*
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }*/

}
