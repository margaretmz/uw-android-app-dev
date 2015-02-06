package edu.uw.aad.mzm.sample.popup;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

/**
 * Created by Margaret on 1/26/2015
 * This sample app shows you how to create
 * - a Toast
 * - a PopupWindow
 * - an AlertDialog
 */
public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private Button mButtonShowToast;
    private Button mButtonOpenPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Button for showing a Toast
        mButtonShowToast = (Button)findViewById(R.id.buttonToast);
        // Button for showing a Popupwindow
        mButtonOpenPopup = (Button)findViewById(R.id.buttonPopupwindow);
        // set OnClickListener for mButtonShowToast
        mButtonShowToast.setOnClickListener(this);
        // set OnClickListener for mButtonOpenPopup
        mButtonOpenPopup.setOnClickListener(this);
        // find the View by buttonDialog id and set OnClickListener directly on it
        findViewById(R.id.buttonDialog).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonToast:
                showToast();
                break;
            case R.id.buttonPopupwindow:
                showPopup();
                break;
            case R.id.buttonDialog:
                showDialog();
                break;
        }
    }

    /**
     * Show a Toast
     */
    private void showToast() {
        Toast.makeText(this, "this is a toast!", Toast.LENGTH_LONG).show();
    }

    /**
     * Show a popupwindow
     * 1. Create an xml file for the popup
     * 2. Create an layout inflator
     * 3. Inflate the popup View
     * 4. Create the Popupwindow
     * 5. Show the Popupwindow
     */
    private void showPopup() {
        // create an layout inflater
        LayoutInflater inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // inflate the popup View
        View popupView = inflater.inflate(R.layout.popup, (ViewGroup)findViewById(R.id.llPopup));
        // create the popupwindow
        final PopupWindow popupWindow = new PopupWindow(popupView, 300, 300);
        Button buttonClosePopup = (Button)popupView.findViewById(R.id.buttonClosePopup);
        buttonClosePopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        // Anchor it to the button
        popupWindow.showAsDropDown(mButtonOpenPopup);

    }

    /**
     * Show a dialog with Yes and No Buttons
     * Note: on devices with ICS or higher, button sequence is: Negative, Neutral & Positive
     * 1. Use AlertDialog.Builder to construct the dialog
     * 2. Create the AlertDialog
     * 3. Show the dialog
     */
    private void showDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Usage Terms");
        builder.setMessage("Clicking on \"Yes\" means you accept all the terms and conditions...");
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "You clicked on yes", Toast.LENGTH_LONG).show();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
