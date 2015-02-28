package edu.uw.aad.mzm.sample.receiver.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import edu.uw.aad.mzm.sample.receiver.util.NetworkUtil;

/**
 * Created by Margaret on 2/27/2015.
 */
public class NetworkConnectionStatusReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String networkStatus = NetworkUtil.getConnectionStatus(context);
        Toast.makeText(context, "Network status: - " + networkStatus, Toast.LENGTH_LONG).show();
    }
}
