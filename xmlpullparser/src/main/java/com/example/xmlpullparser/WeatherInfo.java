package com.example.xmlpullparser;

public class WeatherInfo {
    private int id ;
    private  String name ;
    private String weather ;
    private String temperature ;
    private String pm ;
    private String wind ;


    @Override
    public String toString() {
        return "WeatherInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weather='" + weather + '\'' +
                ", temperature='" + temperature + '\'' +
                ", pm='" + pm + '\'' +
                ", wind='" + wind + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getPm() {
        return pm;
    }

    public void setPm(String pm) {
        this.pm = pm;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }
}
