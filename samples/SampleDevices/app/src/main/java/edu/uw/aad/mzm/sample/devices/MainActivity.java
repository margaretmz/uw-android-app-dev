package edu.uw.aad.mzm.sample.devices;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Locale;

/**
 * Created by Margaret on 4/13/2015
 * This sample app demos how to support multiple Android devices
 */
public class MainActivity extends ActionBarActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.textViewDeviceInfo);

        StringBuilder sb = new StringBuilder();

        // 1. Get the Device Information
        sb.append("Manufacturer: " + Build.MANUFACTURER);
        sb.append("\nModel: " + Build.MODEL);
        sb.append("\nAPI Level: " + Build.VERSION.SDK_INT);

        // 2. Check to see if we are running in an emulator
        boolean isEmulator = false;
        if(Build.PRODUCT.equalsIgnoreCase("google_sdk")||
                Build.MODEL.contains("Emulator") ||
                Build.MODEL.contains("Android SDK"))  {
            isEmulator = true;
        }
        sb.append("\nIs in emulator: " + isEmulator);

        // 3. Get the Language
        Locale defaultLocale = Locale.getDefault();
        sb.append("\nLocale: " + defaultLocale.getDisplayLanguage());

        // 4. Determine if Facebook is installed
        try {
            getPackageManager().getPackageInfo("com.facebook.katana", 0);
            sb.append("\nFacebook is installed");
        } catch (PackageManager.NameNotFoundException e) {
            sb.append("\nFacebook is not installed");
        }


        // 5. Check to see if we have a camera
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String cameraPackageName = intent.resolveActivity(getPackageManager()).getPackageName();
        try {
            getPackageManager().getPackageInfo(cameraPackageName, PackageManager.GET_META_DATA);
            sb.append("\nA camera is available");
        } catch (PackageManager.NameNotFoundException e) {
            sb.append("\nA camera is not available");
        }


        // Display information in TextView
        mTextView.setText(sb.toString());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
