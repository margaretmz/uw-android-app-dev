package edu.uw.aad.mzm.sample.storage;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Margaret on 2/9/2015
 * Sample app that demos Android internal / external storage
 */
public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private EditText mEditTextDataInput;
    // File name for internal storage: read/write
    String mInternalFileName = "int_file.txt";
    // A file on the internal storage
    File mInternalFile;
    // File name for external storage: read/write
    String mExternalFileName = "ext_file.txt";
    // A file on the external storage
    File mExternalFile;

    private SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditTextDataInput = (EditText)findViewById(R.id.editText1);

        // Find the root container
        LinearLayout linearLayoutRoot = (LinearLayout)findViewById(R.id.linearLayoutRoot);

        // Set OnClickListener on all the child views of the root
        for (int i=0; i<linearLayoutRoot.getChildCount();i++) {
            linearLayoutRoot.getChildAt(i).setOnClickListener(this);
        }

        // Create a new file on internal storage
        mInternalFile = new File(this.getFilesDir(), mInternalFileName);

        // Create a new file on external storage, if external storage is available
        if(isExternalStorageAvailable()) {
            mExternalFile = new File(this.getExternalFilesDir(null), mExternalFileName);
        } else {
            Toast.makeText(this, "No external storage available!", Toast.LENGTH_SHORT).show();
        }

        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);

    }

    @Override
    public void onClick(View v) {
        String stringToFile = mEditTextDataInput.getText().toString().trim();
        String stringFromFile ="";
        switch (v.getId()) {
            case R.id.buttonInternalWrite:
                // Write to a file on internal storage
                writeToStorage(mInternalFile);
                makeAToast("Write to internal file: " + stringToFile);
                break;
            case R.id.buttonInternalRead:
                // Read from a file on internal storage
                stringFromFile = readFromStorage(mInternalFile);
                if(!stringFromFile.isEmpty()) {
                    makeAToast("Read from internal file: " + stringFromFile);
                }  else {
                    makeAToast(getResources().getString(R.string.nothing_to_read));
                }
                break;
            case R.id.buttonExternalWrite:
                // Write to a file on external storage
                writeToStorage(mExternalFile);
                makeAToast("Write to external file: " + stringToFile);
                break;
            case R.id.buttonExternalRead:
                // Read from a file on external storage
                stringFromFile = readFromStorage(mExternalFile);
                if(!stringFromFile.isEmpty()) {
                    makeAToast("Read from external file: " + stringFromFile);
                } else {
                    makeAToast(getResources().getString(R.string.nothing_to_read));
                }
                break;
            case R.id.buttonPrefsWrite:
                SharedPreferences.Editor editor = mPrefs.edit();
                editor.putString("input", stringToFile);
                editor.commit();
                break;
            case R.id.buttonPrefsRead:
                stringFromFile = mPrefs.getString("input", "");
                makeAToast("Read from SharedPreferences" + stringFromFile);
                break;
        }
    }

    /**
     * Writes to a file on Storage
     * @param file
     */
    private void writeToStorage(File file) {

        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(mEditTextDataInput.getText().toString().getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads a file from storage
     */
    private String readFromStorage(File file) {

        String outputData = "";

        try {
            FileInputStream inputStream = new FileInputStream(file);
            if (inputStream != null) {
                InputStreamReader isr = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(isr);
                String stringCurrentLine;
                while ((stringCurrentLine = bufferedReader.readLine()) != null) {
                    outputData = outputData + stringCurrentLine;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return outputData;
    }

     /**
     * Checks if external storage is available for read and write
     * @return
     */
     private boolean isExternalStorageAvailable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /**
     * Make a toast
     * @param text
     */
    private void makeAToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }
}
