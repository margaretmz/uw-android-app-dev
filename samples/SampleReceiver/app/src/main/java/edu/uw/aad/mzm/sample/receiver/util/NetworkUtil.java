package edu.uw.aad.mzm.sample.receiver.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Margaret on 2/27/2015.
 */
public class NetworkUtil {

    public static String getConnectionStatus(Context context) {

        String networkStatus = "";

        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if(activeNetwork!=null && activeNetwork.isConnected()) {
            if(activeNetwork.getType()==ConnectivityManager.TYPE_WIFI) {
                networkStatus = "Connected to Wi-fi";
            }
            if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                networkStatus = "Connected to mobile";
            }
        } else {
            networkStatus = "Disconnected";
        }

        return networkStatus;
    }
}
