package edu.uw.aad.mzm.sample.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Margaret on 5/4/15.
 *
 * This sample app demos how to create a custom View with some attributes defined in xml
 */
public class CustomView extends View {

    private int circleColor;
    private Paint mCirclePaint;                         // Paint for drawing
    private Point mCenter;                              // Center of the Custom View
    private final static float mRadius = 100;            // Radius for the circle

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mCirclePaint = new Paint();
        mCirclePaint.setStyle(Paint.Style.FILL);
//        mCirclePaint.setColor(Color.BLUE);
        mCenter = new Point();

        if(attrs!=null) {
            // Retrieve all the attributes set in xml
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CustomView, 0, 0);
            // Get the circle color
            circleColor = a.getInteger(R.styleable.CustomView_circleColor, 0);
            a.recycle();
        }

        // Set the paint color
        mCirclePaint.setColor(circleColor);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // If there is a size change
        if ((w!=oldw) || (h!=oldh)) {
            // Set center point x to be half of View width
            mCenter.x = w/2;
            // Set center point y to be half of the View height
            mCenter.y = h/2;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Draw a circle at the center of the view
        canvas.drawCircle(mCenter.x,                // X-coordinate of circle center
                            mCenter.y,              // Y-coordinate of circle center
                            mRadius,                // Circle radius
                            mCirclePaint);          // Circle Paint
    }
}
