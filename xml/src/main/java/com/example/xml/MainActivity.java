package com.example.xml;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.Toast;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<PersonInfo> userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("疑问", "XML序列化的对象是什么样子的数据？");
        Log.e("疑问", "int 和 integer？");
        Log.e("疑问", "什么情况下该用什么容器(list\\arra\\..)？");
        Log.e("疑问", "list.set()");
        Log.e("疑问", "namespace?");


        //创建数据
        userData = new ArrayList<PersonInfo>();
        for (int i = 0; i < 100; i++) {
            userData.add(new PersonInfo("林" + i, 100 - i, 1 + i));
        }
        findViewById(R.id.xlmlBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serializer();
            }
        });
    }

    //将person对象保存为XML
    public void serializer() {
        XmlSerializer serializer = Xml.newSerializer();  //用Xml对象创建Serializer对象
        File filePath = Environment.getExternalStorageDirectory();
        File file = new File(filePath, "XMLSerializer.xml");

        try {
            FileOutputStream fos = new FileOutputStream(file);
            serializer.setOutput(fos, "utf-8");
            serializer.startDocument("utf-8", true);
            serializer.startTag(null, "Persons");
            int count = 0;
            for (PersonInfo person : userData ){  //userData -> person

                serializer.startTag(null, "name");
                serializer.attribute(null, "id", count+"");
                serializer.text(person.getName()) ;
                serializer.endTag(null,"name") ;

                serializer.startTag(null, "age");
                serializer.text(person.getAge()+"") ;
                serializer.endTag(null,"age") ;

                serializer.startTag(null, "score");
                serializer.text(person.getScore()+"") ;
                serializer.endTag(null,"score") ;

                count++ ;
            }
            serializer.endTag(null, "Persons");
            serializer.endDocument();
            serializer.flush();
            fos.close() ;
            Toast.makeText(this,"yes，寻劣化成功",Toast.LENGTH_SHORT).show();
            Log.w("提示","yes") ;
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this,"no,序列化失败",Toast.LENGTH_SHORT).show();
            Log.w("提示","yes") ;
        }

    }
}
































