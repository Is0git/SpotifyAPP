package com.android.spotifyapp.ui.listeners;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.fragment.app.FragmentManager;

import com.android.spotifyapp.ui.fragment.YoutubeFragment;

public class OnSwipeListener {
    public static boolean onSwipe(View v, MotionEvent event, FragmentManager fragmentManager, YoutubeFragment youtubeFragment) {
        float prevX = 0; float  prevY = 0;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                prevX = event.getX();
                prevY = event.getY();

                return true;
            case MotionEvent.ACTION_UP:
                float newX = event.getX();
                float newY = event.getY();


                //Calculates where we swiped

                if (Math.abs(newX - prevX) > Math.abs(newY - prevY)) {
                    //LEFT - RiGHT Direction

                    if( newX > prevX) {
                        //RIGHT

                       fragmentManager.beginTransaction().remove(youtubeFragment).commitAllowingStateLoss();
                        v.setX(prevX);
                    } else {
                        //LEFT

                        fragmentManager.beginTransaction().remove(youtubeFragment).commitAllowingStateLoss();
                        v.setX(prevX);

                    }
                } else {
                    // UP-DOWN Direction
                    if (newY > prevY) {
                        //DOWN
                        Log.i("TOUCH INFO", "Down");
                    } else {
                        //UP
                        Log.i("TOUCH INFO", "Up");
                    }
                }

                break;

                //Follows touch
            case MotionEvent.ACTION_MOVE:
                v.setX(event.getX());
                break;
        }
        return false;
    }
}
