package me.maxwin.view;

import android.widget.Scroller;

/**
 * Created by song on 9/3/14.
 */
public class XScroller extends Scroller{



    public XScroller(android.content.Context context) {
        super(context);
    }

    public XScroller(android.content.Context context, android.view.animation.Interpolator interpolator) {
        super(context,interpolator);
    /* compiled code */ }

    public XScroller(android.content.Context context, android.view.animation.Interpolator interpolator, boolean flywheel) {
        super(context, interpolator, flywheel);
     /* compiled code */ }
    public void startScroll(int startX, int startY, int dx, int dy) {
        super.startScroll(startX, startY, dx, dy);
     /* compiled code */ }

    protected void onScrollChanged(int x,int y, int oldX, int oldY){

    }

    interface XScrollerListener{

    }
}
