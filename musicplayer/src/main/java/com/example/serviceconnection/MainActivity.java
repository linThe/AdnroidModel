package com.example.serviceconnection;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_path ;
    private Intent intent ;
    private myConn conn ;
    MusiceService.MyBinder  binder ;
    private SeekBar mSeekBar ;
    private Thread mThread ;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg){
            switch(msg.what){
                case 100:
                    int currentPosition = (Integer) msg.obj ;
                    mSeekBar.setProgress(currentPosition);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initlaView() ;
        conn = new myConn() ;
        intent = new Intent(this,MusiceService.class) ;
        bindService(intent,conn,BIND_AUTO_CREATE) ;

    }

    private void initlaView() {
        et_path = (EditText)findViewById(R.id.et_path) ;
        findViewById(R.id.tv_play).setOnClickListener(this);
        findViewById(R.id.tv_pause).setOnClickListener(this);
        findViewById(R.id.tv_stop).setOnClickListener(this);
        findViewById(R.id.tv_replay).setOnClickListener(this);
        mSeekBar = (SeekBar)findViewById(R.id.seekBar) ;

    }

    private void initSeekBar(){
        int musicWidth = binder.getMusicWidth() ;
        mSeekBar.setMax(musicWidth);
    }

    private void UpdateProgress(){
        mThread = new Thread(){
          public void run(){
            while(!interrupted()){
                int currentPosition = binder.getCurrentPosition() ;
                Message message = Message.obtain() ;
                message.obj = currentPosition ;
                message.what = 100 ;
                handler.sendMessage(message) ;
            }
          }
        };
        mThread.start();
    }

    private class myConn implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (MusiceService.MyBinder)service ;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    /*
    * play 只播放开始
    * pause 暂停后要用pause再开启
    * replay 重来
    * stop 关掉要play才开始
    * */
    @Override
    public void onClick(View v) {
        String pathway = et_path.getText().toString().trim() ;
        String path = "/storage/emulated/0/Music/mp3.mp3" ;
        switch (v.getId()){
            case R.id.tv_play :
                if (!TextUtils.isEmpty(path)){
                    binder.plays(path);
                    initSeekBar();
                    UpdateProgress();
                }else{
                    Toast.makeText(this,"找不到",Toast.LENGTH_LONG).show();
                }
            break;
            case R.id.tv_pause :
                binder.pausers();
                break;
            case R.id.tv_replay :
               binder.replays(pathway);
               break;
            case R.id.tv_stop :
                mThread.interrupt();

                if (mThread.isInterrupted()){
                    binder.stops();
                }else{
                    Toast.makeText(this,"找不到",Toast.LENGTH_LONG).show();
                }
                break;
                default:
                    break;

        }
    }

    protected void  onDestroy(){
        if (mThread != null){
            if (!mThread.isInterrupted())
                mThread.interrupt();
        }
        unbindService(conn);
        super.onDestroy();
    }

}
