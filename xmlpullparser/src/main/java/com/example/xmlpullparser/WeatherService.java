package com.example.xmlpullparser;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class WeatherService {
    //返回天气信息
    public static List<WeatherInfo> getWeatherInfos(InputStream is) throws Exception{
        //得到pull解析器
        XmlPullParser parser = Xml.newPullParser() ;
        //初始化解析器，第一个参数代表包含xml的数据
        parser.setInput(is,"utf-8");
        List<WeatherInfo> weatherInfos = null ;
        WeatherInfo weatherInfo = null ;
        //得到当前事件类型
        int type = parser.getEventType() ;
        //END_DOCUMENT 文档结束事件
        while(type != XmlPullParser.END_DOCUMENT){
            switch(type){
                case XmlPullParser.START_TAG :
                    //解析到全局开始的标签infos根节点,就是在documnent里面最外面的标签
                    if ("infos".equals(parser.getName())){
                        weatherInfos = new ArrayList<WeatherInfo>() ;
                    }else if("city".equals(parser.getName())){
                        weatherInfo = new WeatherInfo() ;
                        String idStr = parser.getAttributeValue(0) ;  //第一个属性值是id
                        weatherInfo.setId(Integer.parseInt(idStr));
                    }else if("temperture".equals(parser.getName())){
                        weatherInfo.setTemperature(parser.nextText());
                    }else if("weather".equals(parser.getName())){
                        weatherInfo.setWeather(parser.nextText());

                    }else if("name".equals(parser.getName())){
                        weatherInfo.setName(parser.nextText());

                    }else if("pm".equals(parser.getName())){
                        weatherInfo.setWind(parser.nextText());

                    }else if("wind".equals(parser.getName())){
                        weatherInfo.setWind(parser.nextText());

                    }
                    break ;
                case XmlPullParser.END_TAG:   //处理完一个城市就添加到list集合，因此list 有3个对象
                    if ("city".equals(parser.getName())){
                        weatherInfos.add(weatherInfo) ;
                        weatherInfo = null ;
                    }

                    break;
            }
            //只要不解析到文档末尾就继续下一个循环
            type = parser.next() ;
        }
        return weatherInfos ;
    }
}
