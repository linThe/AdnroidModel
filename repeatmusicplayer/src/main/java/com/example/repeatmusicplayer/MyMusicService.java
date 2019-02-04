package com.example.repeatmusicplayer;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;

public class MyMusicService extends Service {
    private static final String  TAG  = "MyMusicService" ;
    private MediaPlayer mp ;
    class MyBinder extends Binder {

        public void plays(String path){
            play(path) ;
        }

        public void pauses(){
            pause() ;
        }

        public void replays(){
            replay() ;
        }

        public void stops(){
            stop() ;
        }

        public  int getMusicLengths(){
            return getMusicLength();
        }

        public int getCurrentPositions(){
            return getCurrentPosition();
        }
    }

    public  int getMusicLength(){
        if (mp != null) {
            return mp.getDuration();
        }
        return 0 ;
    }

    public int getCurrentPosition(){
        if (mp != null) {
            return mp.getCurrentPosition();
        }
        return 0 ;
    }

    private void stop() {
        if (mp != null){
            Log.i(TAG,"停止播放") ;
            mp.stop();
            mp.release();
            mp = null ;
        }else{
            Log.i(TAG,"已经停止播放") ;
        }
    }


    private void replay() {
        if (mp != null){
            Log.i(TAG,"重新播放") ;
            mp.seekTo(0);
            try {
                mp.prepare();
            }catch (IOException e){
                e.printStackTrace();
            }
            mp.start();
        }
    }


    private void pause() {
        if (mp != null ){
            Log.i(TAG,"暂停播放") ;
            if (mp.isPlaying()) {
                mp.pause();
            }else {
                mp.start();
            }
        }
    }

    private void play(String path) {
        if (mp == null){
            Log.i(TAG,"开始播放") ;
            mp = new MediaPlayer() ;
            mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
            try {
                mp.setDataSource(path);
                mp.prepare();
            }catch(IOException e){
                Log.e(TAG,"没有找到文件") ;
            }
            mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    //准备监听，开始
                    mp.start();
                }
            });

        }else{
            int cur = mp.getCurrentPosition() ;
            mp.seekTo(cur);
            try{
                mp.prepare();
            }catch(IOException e){
                e.printStackTrace();
            }
            mp.start();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    @Override
    public void onDestroy() {
        stop();
        super.onDestroy();
    }
}
