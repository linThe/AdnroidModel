package com.example.contentresolver;

import android.os.Environment;
import android.util.Xml;
import android.widget.Toast;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class SmsUtils {
    public static void backUpSms(List<SmsInfo> listSms, MainActivity mainActivity) {
        try{
            XmlSerializer serria = Xml.newSerializer() ;
            File file = new File(Environment.getExternalStorageDirectory(),"smsInfos") ;
            FileOutputStream fos =  new FileOutputStream(file) ;
            serria.setOutput(fos,"utf-8");

            //大地母亲
            serria.startDocument("utf-8",true);
            int id = 0 ;
            //母亲孕育的根
            serria.startTag(null,"smss") ;
            for (SmsInfo value : listSms ) {
                id ++ ;
                //根繁衍的第 id 个父亲
                serria.startTag(null,"sms") ;
                serria.attribute(null,"id",id+"" ) ;
                //父亲抚养未成年的孩子
                serria.startTag(null,"body") ;
                serria.text(value.getBody()) ;
                serria.endTag(null,"body");

                serria.startTag(null,"address") ;
                serria.text(value.getAddress()) ;
                serria.endTag(null,"address") ;

                serria.startTag(null,"type") ;
                serria.text(value.getType()+ "") ;
                serria.endTag(null,"type") ;

                serria.startTag(null,"date") ;
                serria.text(value.getDate()+"") ;
                serria.endTag(null,"date") ;

                serria.endTag(null,"sms") ;
            }
            serria.endTag(null,"smss") ;
            serria.endDocument();
            fos.close();
            Toast.makeText(mainActivity,"备份成功",Toast.LENGTH_LONG).show() ;

        }catch(Exception e){
            Toast.makeText(mainActivity,"备份失败",Toast.LENGTH_LONG).show();
        }
    }
}
