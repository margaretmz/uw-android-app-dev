package edu.uw.aad.mzm.sample.dragdrop;

import android.content.ClipData;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;


/**
 * Created by Margaret on 5/4/2015
 *
 * This sample app demos how to drag and drop a ImageView
 */
public class MainActivity extends ActionBarActivity {

    private ImageView imageViewFrom;
    private ImageView imageViewTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageViewFrom =  (ImageView)findViewById(R.id.imageViewFrom);
        imageViewTo = (ImageView)findViewById(R.id.imageViewTo);

        imageViewFrom.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData dragData = ClipData.newPlainText("icon", "ic_android");
                // Create a drag shadow
                View.DragShadowBuilder dragShadow = new View.DragShadowBuilder(imageViewFrom);
                // Start dragging
                v.startDrag(dragData, dragShadow, null, 0);
                // Hide the view that is being dragged from
                v.setVisibility(View.INVISIBLE);
                return true;
            }
        });

        imageViewTo.setOnDragListener(new TouchDragEventListener());
    }

    protected class TouchDragEventListener implements View.OnDragListener {


        @Override
        public boolean onDrag(View v, DragEvent event) {

            switch (event.getAction()) {
                case DragEvent.ACTION_DROP:
                    // Set the android icon on the destination ImageView
                    imageViewTo.setImageResource(R.drawable.ic_android);
                    // Force ImageView to redraw
                    imageViewTo.invalidate();
                default:
                    return true;
            }
        }
    }

}
