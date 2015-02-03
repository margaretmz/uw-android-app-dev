package edu.uw.aad.mzm.sample.samplelist;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * A fancy list that uses a custom ArrayAdapter
 */
public class FancyListActivity extends ActionBarActivity {

    /**
     * A custom object to hold the Android API data
     */
    public class AndroidAPI {
        public String apiName;
        public String apiLevel;

        public AndroidAPI(String apiName, String apiLevel) {
            this.apiName = apiName;
            this.apiLevel = apiLevel;
        }

    }

    private class AndroidAPIAdapter extends ArrayAdapter<AndroidAPI> {

        private ArrayList<AndroidAPI> mAndroidAPIs;

        private AndroidAPIAdapter(Context context, int resource, ArrayList<AndroidAPI> androidAPIs) {
            super(context, resource, androidAPIs);
            this.mAndroidAPIs = androidAPIs;
        }

        /**
         * Override getView to use the custom list item in ListView
         * @param position
         * @param convertView
         * @param parent
         * @return
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if(view == null) {
                LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.fancy_list_item, null);
            }
            AndroidAPI androidAPI = mAndroidAPIs.get(position);

            if(androidAPI !=null) {
                TextView textViewAPIName = (TextView)view.findViewById(R.id.textViewAPIName);
                TextView textViewAPILevel = (TextView)view.findViewById(R.id.textViewAPIlevel);

                if(textViewAPIName!=null) {
                    textViewAPIName.setText(androidAPI.apiName);
                }
                if(textViewAPILevel!=null) {
                    textViewAPILevel.setText(androidAPI.apiLevel);
                }
            }

            return view;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fancy_list);

        ArrayList<AndroidAPI> mAndroidAPIs = new ArrayList<AndroidAPI>();

        mAndroidAPIs.add(new AndroidAPI("Lollipop", "API 21"));
        mAndroidAPIs.add(new AndroidAPI("Kitkat", "API 19"));
        mAndroidAPIs.add(new AndroidAPI("Jelly Bean", "API 16-18"));
        mAndroidAPIs.add(new AndroidAPI("Ice Cream Sandwich", "API 14-15"));
        mAndroidAPIs.add(new AndroidAPI("Honeycomb", "API 11-13"));
        mAndroidAPIs.add(new AndroidAPI("Gingerbread", "API 9-10"));
        mAndroidAPIs.add(new AndroidAPI("Froyo", "API 8"));
        mAndroidAPIs.add(new AndroidAPI("Éclair", "API 5-7"));
        mAndroidAPIs.add(new AndroidAPI("Donut", "API 4"));
        mAndroidAPIs.add(new AndroidAPI("Cupcake", "API 3"));

        ListView listView  = (ListView)findViewById(R.id.listViewFancy);

        AndroidAPIAdapter androidAPIAdapter = new AndroidAPIAdapter(this, R.layout.fancy_list_item, mAndroidAPIs);

        listView.setAdapter(androidAPIAdapter);
    }

}
