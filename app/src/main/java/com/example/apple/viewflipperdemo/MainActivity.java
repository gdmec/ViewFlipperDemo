package com.example.apple.viewflipperdemo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity implements  GestureDetector.OnGestureListener{
    private ViewFlipper viewFlipper;
    private int[] images ={R.drawable.icon1,R.drawable.icon2,R.drawable.icon3,R.drawable.icon4,R.drawable.icon5};
    private GestureDetector gestureDetector;
    private Animation lInAnim,lOutAnim,rInAnim,rOutAnim;
    private Activity activity;
    private final static  int FLING_MIN_DISTANCE=100;
    private final static int FLIN_MIN_VELOCITY=200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
        viewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);
        gestureDetector = new GestureDetector(this,this);
        lInAnim = AnimationUtils.loadAnimation(activity,R.anim.push_left_in);
        lOutAnim = AnimationUtils.loadAnimation(activity,R.anim.push_left_out);
        rInAnim = AnimationUtils.loadAnimation(activity,R.anim.push_right_in);
        rOutAnim = AnimationUtils.loadAnimation(activity,R.anim.push_right_out);
        for (int i = 0; i <5 ; i++) {
            ImageView iv = new ImageView(this);
            iv.setImageResource(images[i]);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(iv,i,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(1000);
        viewFlipper.startFlipping();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        viewFlipper.stopFlipping();
        viewFlipper.setAutoStart(false);

        return gestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        if(motionEvent1.getX()-motionEvent.getX()>FLING_MIN_DISTANCE
                &&Math.abs(v)>FLIN_MIN_VELOCITY){
            viewFlipper.setInAnimation(lInAnim);
            viewFlipper.setOutAnimation(rOutAnim);
            viewFlipper.showPrevious();
        }else if(motionEvent.getX()-motionEvent1.getX()>FLING_MIN_DISTANCE
                &&Math.abs(v)>FLIN_MIN_VELOCITY){
            viewFlipper.setInAnimation(rInAnim);
            viewFlipper.setOutAnimation(lOutAnim);
            viewFlipper.showNext();
        }
        return false;
    }
}
