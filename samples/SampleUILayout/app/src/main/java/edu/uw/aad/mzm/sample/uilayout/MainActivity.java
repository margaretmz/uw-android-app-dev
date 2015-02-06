package edu.uw.aad.mzm.sample.uilayout;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Margaret on 1/12/1015
 * This sample app shows you
 * - TextView, ImageView, Button, ImageButton
 * - how to use Android layout containers (LinearLayout, RelativeLayout & FrameLayout)
 * - how to position the Views in the layouts
 */
public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView1 = (TextView)findViewById(R.id.textview1);
        // setting TextView text by code
        textView1.setText(this.getResources().getString(R.string.new_text));
        // setting TextView visibility by code
//        textView1.setVisibility(View.INVISIBLE);

        // buttons
        Button button1 = (Button)findViewById(R.id.button1);
        Button button2 = (Button)findViewById(R.id.button2);
        ImageButton imageButton = (ImageButton)findViewById(R.id.imageButton);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        imageButton.setOnClickListener(this);

       /* button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Button is clicked!", Toast.LENGTH_SHORT).show();
            }
        });*/

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                Toast.makeText(MainActivity.this, "Button 1 is clicked!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button2:
                Toast.makeText(MainActivity.this, "Button 2 is clicked!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.imageButton:
                Toast.makeText(MainActivity.this, "An imagebutton to play", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
