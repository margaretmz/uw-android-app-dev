package edu.uw.aad.mzm.sample.animation;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


public class DrawableAnimationActivity extends ActionBarActivity {

    private ImageView mDroidImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable_animation);
        mDroidImage = (ImageView)findViewById(R.id.imageViewDroid);

        findViewById(R.id.buttonChangeShoe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeMyShoes();
            }
        });
    }

    private void changeMyShoes() {

        AnimationDrawable animationDrawable = (AnimationDrawable)mDroidImage.getBackground();
        if(animationDrawable.isRunning()) animationDrawable.stop();
        animationDrawable.start();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_drawable_animation, menu);
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
