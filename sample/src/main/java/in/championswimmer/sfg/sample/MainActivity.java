package in.championswimmer.sfg.sample;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.test.mock.MockPackageManager;
import android.util.Log;
import android.view.Display;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import in.championswimmer.sfg.lib.SimpleFingerGestures;


public class MainActivity extends Activity {

    public static final String TAG = "MainActivity";
    private static final int REQUEST_CODE_PERMISSION = 2;
    String[] mPermission = {
            Manifest.permission.READ_EXTERNAL_STORAGE};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView mv = (ImageView) findViewById(R.id.myview);
        final TextView grtv = (TextView) findViewById(R.id.gestureResultTextView);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        try {
            if (ActivityCompat.checkSelfPermission(this, mPermission[0])
                    != MockPackageManager.PERMISSION_GRANTED ){

                ActivityCompat.requestPermissions(this,
                        mPermission, REQUEST_CODE_PERMISSION);

                // If any permission aboe not allowed by user, this condition will execute every tim, else your else part will work
            }
        } catch (Exception e) {
            e.printStackTrace();
        }





        SimpleFingerGestures sfg = new SimpleFingerGestures();
        sfg.setDebug(true);
        sfg.setConsumeTouchEvents(true);
        grtv.setText("Swipe up with two fingers to play the Music");


        sfg.setOnFingerGestureListener(new SimpleFingerGestures.OnFingerGestureListener() {

            @Override
            public boolean onSwipeUp(int fingers, long gestureDuration, double gestureDistance) {
              if(fingers==2){
                grtv.setText("Swipe down with two fingers to stop the Music");

                Intent svc=new Intent(MainActivity.this, BackgroundSoundService.class);
                startService(svc);}
                return false;
            }

            @Override
            public boolean onSwipeDown(int fingers, long gestureDuration, double gestureDistance) {
                if(fingers==2){
                grtv.setText("Swipe up with two fingers to stop the Music");
                Intent svc=new Intent(MainActivity.this, BackgroundSoundService.class);
                stopService(svc);

            }  return false;}

            @Override
            public boolean onSwipeLeft(int fingers, long gestureDuration, double gestureDistance) {
              //  grtv.setText("You swiped " + fingers + " fingers  left " + gestureDuration + " milliseconds " + gestureDistance + " pixels far");
                return false;
            }

            @Override
            public boolean onSwipeRight(int fingers, long gestureDuration, double gestureDistance) {
              //  grtv.setText("You swiped " + fingers + " fingers  right " + gestureDuration + " milliseconds " + gestureDistance + " pixels far");
                return false;
            }

            @Override
            public boolean onPinch(int fingers, long gestureDuration, double gestureDistance) {
             //   grtv.setText("You pinched " + fingers + " fingers " + gestureDuration + " milliseconds " + gestureDistance + " pixels far");
                return false;
            }

            @Override
            public boolean onUnpinch(int fingers, long gestureDuration, double gestureDistance) {
               // grtv.setText("You unpinched " + fingers + "fingers"  + gestureDuration + " milliseconds " + gestureDistance + " pixels far");
                return false;
            }

            @Override
            public boolean onDoubleTap(int fingers) {
             //   grtv.setText("You double tapped");
                return false;
            }
        });


        mv.setOnTouchListener(sfg);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.e("Req Code", "" + requestCode);
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (grantResults.length == 1 &&
                    grantResults[0] == MockPackageManager.PERMISSION_GRANTED )
                    {

                // Success Stuff here

            }
        }

    }

}
