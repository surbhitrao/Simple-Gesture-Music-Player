package in.championswimmer.sfg.sample;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Dr Sudhir on 15-12-2017.
 */

public class BackgroundSoundService extends Service {
    private static final String TAG = null;

    MediaPlayer player;
    public IBinder onBind(Intent arg0) {

        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        String a=uriget();

        player = MediaPlayer.create(this, Uri.parse(a));

        player.setLooping(true); // Set looping
        player.setVolume(100,100);


    }
    @SuppressLint("WrongConstant")
    public int onStartCommand(Intent intent, int flags, int startId) {

        player.start();
        return 1;
    }




    public void onStart(Intent intent, int startId) {

        // TO DO
    }
    public IBinder onUnBind(Intent arg0) {
        // TO DO Auto-generated method
        return null;
    }

    public void onStop() {


    }
    public void onPause() {

    }
    @Override
    public void onDestroy() {
        player.stop();
        player.release();
    }

    @Override
    public void onLowMemory() {

    }

private String uriget(){


    Uri uri;
    Cursor cursor;


    int column_index_data, column_index_folder_name;
    ArrayList<String> listOfAllImages = new ArrayList<String>();
    String absolutePathOfImage = null;
    uri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

    final String orderBy = MediaStore.Audio.Media.DATE_ADDED;
    cursor = this.getApplicationContext().getContentResolver().query(uri, null, null, null, orderBy + " DESC");


    column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);

            while (cursor.moveToNext()) {
        absolutePathOfImage = cursor.getString(column_index_data);

        listOfAllImages.add(absolutePathOfImage);

    }


    Random random = new Random();
    int index = random.nextInt(20);


    return  listOfAllImages.get(index);}







}