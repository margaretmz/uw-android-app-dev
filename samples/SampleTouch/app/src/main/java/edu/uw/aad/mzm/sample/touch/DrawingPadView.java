package edu.uw.aad.mzm.sample.touch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Margaret on 5/3/15.
 *
 * This app demos how to use (single) Touch
 *
 * Canvas - where and what (rectangle, path etc) to draw
 * Paint - how to draw: color, line width etc.
 */
public class DrawingPadView extends View {

//    private Canvas mCanvas;
    private Paint mDrawingPaint;        // Paint used for drawing
    private Paint mBorderPaint;         // Paint used for border
    private Path mPath;

    public DrawingPadView(Context context, AttributeSet attrs) {
        super(context, attrs);

//        mCanvas = new Canvas();             // Used to define where and what to draw
        mDrawingPaint = new Paint();
        mBorderPaint = new Paint();
        mPath = new Path();

        // Set up the Paint for drawing
        mDrawingPaint.setAntiAlias(true);                                  // Smooth out the line
        mDrawingPaint.setStrokeWidth(15);                                  // Set the line width
        mDrawingPaint.setColor(getResources().getColor(R.color.indigo));   // Set the paint color
        mDrawingPaint.setStyle(Paint.Style.STROKE);                        // Set the style to solid line
        mDrawingPaint.setStrokeJoin(Paint.Join.ROUND);                     // Set line end to be round

        // Set up the Paint for the border
        mBorderPaint.setColor(getResources().getColor(R.color.purple));
        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setStrokeWidth(20);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPath(mPath, mDrawingPaint);                             // Draw path with paint

        // Draw border for the drawing pad
        canvas.drawRect(0,                  // The left side of the rectangle to be drawn
                        0,                  // The top side of the rectangle to be drawn
                        getWidth(),         // The right side of the rectangle to be drawn
                        getHeight(),        // The bottom side of the rectangle to be drawn
                        mBorderPaint);      // The Paint used for drawing the border
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(x,y);
                return true;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(x,y);
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                return false;
        }

        invalidate();       // Force the View to redraw
        return true;
    }

    public void erase() {
        mPath.reset();      // Clear the path
        invalidate();       // Force the View to redraw

    }
}
