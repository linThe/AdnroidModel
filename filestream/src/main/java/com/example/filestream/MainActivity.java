package com.example.filestream;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener{
    private TextView tv_save ;
    private Button btn ,btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initia();
    }

    private void initia() {
        tv_save = (TextView)findViewById(R.id.tv_save) ;
        btn = (Button)findViewById(R.id.btn) ;
        btn2 = (Button)findViewById(R.id.btn2) ;

        btn.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn:  //创建并保存文件（文件，内容，通道，start）

            String state = Environment.getExternalStorageState();  //查看是否能获取
            if (state.equals(Environment.MEDIA_MOUNTED)) {
                File SDPath = Environment.getExternalStorageDirectory();
                File file = new File(SDPath, "data.txt");  //  新建文件File（path，name）  容器
                String data = tv_save.getText().toString().trim();       //输出的内容  物质
                FileOutputStream fos;  //文件输出流对象
                try {
                    fos = new FileOutputStream(file);  //输出流指向file内存 管道
                    fos.write(data.getBytes());     //将data转换成字节并写入内存   开
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.i("提示", "以输出内容");
                Log.w("提示", SDPath.toString());

            }
            break ;
            case  R.id.btn2 :
                String stateIn = Environment.getExternalStorageState() ; //环境。获取外部存储状态
                if (stateIn.equals(Environment.MEDIA_MOUNTED)){     //判断SD卡是否存在,并且是否具有读写权限
                    File SDPathIn = Environment.getExternalStorageDirectory();
                    File file = new File(SDPathIn,"data.txt") ;
                    FileInputStream fis  ;
                    Log.w("提示","新娘家到") ;
                    try {
                        fis = new FileInputStream(file);
                        BufferedReader br = new BufferedReader(new InputStreamReader(fis)) ;
                        String str = br.readLine();
                        tv_save.setText(str);
                        System.out.println(str);
                        fis.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    Log.w("hello","666") ;
                }
                break;

        }
    }
}
