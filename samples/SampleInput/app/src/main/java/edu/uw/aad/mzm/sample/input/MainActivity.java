package edu.uw.aad.mzm.sample.input;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {

    private EditText mEditTextFirstMame;
    private EditText mEditTextLastName;
    private EditText mEditTextPhone;
    private static final String PHONE_NUMBER = "phone_no";
    private static final String LAST_NAME = "last_name";
    private static final String FIRST_NAME = "first_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditTextFirstMame = (EditText)findViewById(R.id.editTextFirstName);
        mEditTextLastName = (EditText)findViewById(R.id.editTextFirstName);
        mEditTextPhone = (EditText)findViewById(R.id.editTextPhone);

        // Check whether we're recreating a previously destroyed instance
        if (savedInstanceState != null) {
            // Restore value of phone number from saved state
            savedInstanceState.getString(PHONE_NUMBER);
        } else {
            // Probably initialize members with default values for a new instance
        }

        boolean allowEmail = false;
        CheckBox checkBox = (CheckBox)findViewById(R.id.checkBox1);
        if(checkBox.isChecked()) {
            allowEmail = true;
        }
        findViewById(R.id.buttonOK).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("firstname", mEditTextFirstMame.getText().toString());
                intent.putExtra("phoneno", mEditTextPhone.getText().toString());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Put the phone# in outState
        outState.putString(PHONE_NUMBER, mEditTextPhone.getText().toString());
        outState.putString(FIRST_NAME, mEditTextFirstMame.getText().toString());
        outState.putString(LAST_NAME, mEditTextLastName.getText().toString());
    }

    // Called after onStart()
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.getString(PHONE_NUMBER);
        savedInstanceState.getString(FIRST_NAME);
        savedInstanceState.getString(LAST_NAME);
    }

}
