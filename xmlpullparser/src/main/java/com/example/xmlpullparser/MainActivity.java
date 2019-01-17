package com.example.xmlpullparser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_city,tv_weather,tv_temperature,tv_wind,tv_pm,tv_bj,tv_sh,tv_jl ;
    private ImageView img ;
    private String temperture,weather,name,pm,wind ;
    private Map<String,String> map ;
    private List<Map<String,String>> list ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化数据
        initializal() ;
        //获取map里的信息并展示
        getMap(1,R.drawable.sun) ;
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_bj:
                getMap(2,R.drawable.cloud);
                break;
            case R.id.tv_jl:
                getMap(1,R.drawable.cloud_sun);
                break;
            case R.id.tv_sh:
                getMap(0,R.drawable.sun);
                break;
        }
    }
    private void getMap(int number,int iconNumber){
        Map<String,String> wMap = list.get(number) ;
        temperture = wMap.get("temp") ;
        name = wMap.get("name") ;
        pm = wMap.get("pm") ;
        weather = wMap.get("weather") ;
        wind = wMap.get("wind") ;

        tv_city.setText(name);
        tv_weather.setText(weather);
        tv_pm.setText(pm);
        tv_wind.setText(wind);
        tv_temperature.setText(temperture);

        img.setImageResource(iconNumber);
    }
    private void initializal() {
        tv_city = (TextView)findViewById(R.id.tv_city) ;
        tv_weather = (TextView)findViewById(R.id.tv_weather) ;
        tv_temperature = (TextView)findViewById(R.id.tv_temperature) ;
        tv_wind = (TextView)findViewById(R.id.wind) ;
        tv_pm = (TextView)findViewById(R.id.tv_pm) ;
        tv_sh = (TextView)findViewById(R.id.tv_sh) ;
        tv_bj = (TextView)findViewById(R.id.tv_bj) ;
        tv_jl = (TextView)findViewById(R.id.tv_jl) ;

        img = (ImageView)findViewById(R.id.img_weather) ;

        tv_bj.setOnClickListener(this);
        tv_sh.setOnClickListener(this);
        tv_jl.setOnClickListener(this);

        try{
            Log.e("error","这么菜") ;
            List<WeatherInfo> infos = WeatherService.getWeatherInfos(MainActivity.class.getClassLoader()
            .getResourceAsStream("weather.xml")) ; //类加载的时候的资源流的名字Inputstream
            Log.e("error","这么恐怖") ;
            //循环读取infos中的每一条数据
            list = new ArrayList<Map<String, String>>() ;
            for (WeatherInfo info : infos){
                map = new HashMap<String, String>() ;
                map.put("temp",info.getTemperature()) ;
                map.put("name",info.getName()) ;
                map.put("weather",info.getWeather()) ;
                map.put("pm",info.getPm()) ;
                map.put("wind",info.getWind()) ;
                list.add(map) ;
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.e("error","这么恐怖吗") ;
            Toast.makeText(this,"解析失败",Toast.LENGTH_SHORT).show(); ;
        }

    }

}
