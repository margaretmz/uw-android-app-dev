package edu.uw.aad.mzm.sample.imageintent;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.File;

/**
 * Created by Margaret on 3/2/2015
 *
 * This sample app demos the usage of intent and startActivityForResult()
 */
public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private static final int REQUEST_CAPTURE_IMAGE = 1000;
    private static final int REQUEST_SELECT_IMAGE = 2000;

    private ImageView mImageView;       // ImageView for showing the image taken by camera, or image selected from gallery
    private String mImageFilePath;      // file path of the image
    private Uri mImageByCameraUri;      // Uri of the image taken by camera

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mImageView = (ImageView) findViewById(R.id.imageView);
        mImageView.setImageResource(R.drawable.blank);

        findViewById(R.id.buttonCamera).setOnClickListener(this);
        findViewById(R.id.buttonSelect).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonCamera:
                captureImage();
                break;
            case R.id.buttonSelect:
                selectImage();
                break;
        }
    }

    /**
     * Capture an image by launching a camera app on device
     */
    private void captureImage() {

        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        File photoFile = new File(dir, "sampleimage.jpg");
        mImageByCameraUri = Uri.fromFile(photoFile);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageByCameraUri);
        startActivityForResult(intent, REQUEST_CAPTURE_IMAGE);
    }

    /**
     * Select an image from gallery (by launching all apps that displays images from MediaStore)
     */
    private void selectImage() {
        Intent i = new Intent(Intent.ACTION_PICK,
        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

//        Intent i = new Intent(Intent.ACTION_PICK);
//        i.setType("image/*");
        startActivityForResult(i, REQUEST_SELECT_IMAGE);

    }

    @Override
    protected void onActivityResult(int requestCode,    // request code
                                    int resultCode,     // result code
                                    Intent intent) {    // intent returned from activity
        super.onActivityResult(requestCode, resultCode, intent);

        switch (requestCode) {
            case REQUEST_CAPTURE_IMAGE:
                if (resultCode == RESULT_OK) {
                    mImageFilePath = mImageByCameraUri.getPath();
                } else {
                    Toast.makeText(this, "Was not able to take photo with camera", Toast.LENGTH_LONG).show();
                }
                break;
            case REQUEST_SELECT_IMAGE:
                if (resultCode == RESULT_OK) {
                    // Get Uri of the selected image
                    Uri selectedImageUri = intent.getData();
                    // Get the projection - column to query from provider
                    String[] projection = {MediaStore.Images.Media.DATA};
                    // Get the cursor
                    Cursor cursor = getContentResolver().query(
                            selectedImageUri,
                            projection,
                            null,
                            null,
                            null);
                    if (cursor != null) {
                        cursor.moveToFirst();
                        // Get the image file path
                        mImageFilePath = cursor.getString(cursor.getColumnIndex(projection[0]));
                        // Remember to close the cursor
                        cursor.close();
                    }
                } else {
                    Toast.makeText(this, "No image selected", Toast.LENGTH_LONG).show();
                }
                break;
        }

        // Load image in UI
        if(mImageFilePath !=null) {
            mImageView.setImageBitmap(BitmapFactory.decodeFile(mImageFilePath));
        }
    }

}
