package com.example.repeatmusicplayer;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText et_path ;
    private SeekBar sb ;


    private MyMusicService.MyBinder binder ;

    private MyConn conn ;
    private Intent intent ;
    private Thread mThread ;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            switch(msg.what){
                case 100:
                    int currentps = (int)msg.obj ;
                    sb.setProgress(currentps);
                break;
                default:
                    break ;
            }
        }
    } ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initlaView() ;
        conn = new MyConn() ;
        intent = new Intent(this, MyMusicService.class) ;
        bindService(intent, conn,BIND_AUTO_CREATE) ;
    }


    class MyConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (MyMusicService.MyBinder)service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    private void initlaView() {
        et_path = (EditText)findViewById(R.id.et_path) ;
        sb = (SeekBar)findViewById(R.id.seekBar) ;
        findViewById(R.id.tv_pause).setOnClickListener(this);
        findViewById(R.id.tv_play).setOnClickListener(this);
        findViewById(R.id.tv_replay).setOnClickListener(this);
        findViewById(R.id.tv_stop).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String pathway = et_path.getText().toString().trim() ;
        String path = "/storage/emulated/0/Music/mp3.mp3" ;

        if (!pathway.equals(""))
            path = pathway ;

        switch(v.getId()){
            case R.id.tv_pause:
                binder.pauses();
            break ;
            case R.id.tv_play:
                if (!path.equals("")) {
                    sbIntla();
                    binder.plays(path);
                    UpdateProgress();
                }else{
                    Toast.makeText(this,"歌曲路径不能为空",Toast.LENGTH_LONG).show();
                }
                break ;
            case R.id.tv_replay:
                binder.replays();
                break ;
            case R.id.tv_stop:
                binder.stops();
                break ;
                default:
                    break;

        }
    }

    private void UpdateProgress() {
        mThread = new Thread(){
          public void run(){
              while(!interrupted()){
                  int currps = binder.getCurrentPositions() ;
                  Log.i("UpdateProgres",currps+"长度") ;
                  //获得一个对象？
                  Message ms = Message.obtain() ;
                  ms.obj = currps ;
                  ms.what = 100 ;
                  handler.sendMessage(ms) ;
              }
          }
        };

        mThread.start();
    }

    private void sbIntla() {
        sb.setMax(binder.getMusicLengths());
    }

    @Override
    protected void onDestroy() {
        if (mThread != null){
            mThread.interrupt();
        }
        unbindService(conn);
        super.onDestroy();
    }
}
















