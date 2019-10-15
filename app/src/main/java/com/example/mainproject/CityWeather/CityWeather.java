package com.example.mainproject;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CityWeather {

    @SerializedName("coord")
    @Expose
    private com.example.mainproject.Coord coord;
    @SerializedName("weather")
    @Expose
    private List<com.example.mainproject.Weather> weather = null;
    @SerializedName("base")
    @Expose
    private String base;
    @SerializedName("main")
    @Expose
    private com.example.mainproject.Main main;
    @SerializedName("visibility")
    @Expose
    private Integer visibility;
    @SerializedName("wind")
    @Expose
    private com.example.mainproject.Wind wind;
    @SerializedName("rain")
    @Expose
    private com.example.mainproject.Rain rain;
    @SerializedName("clouds")
    @Expose
    private com.example.mainproject.Clouds clouds;
    @SerializedName("dt")
    @Expose
    private Integer dt;
    @SerializedName("sys")
    @Expose
    private com.example.mainproject.Sys sys;
    @SerializedName("timezone")
    @Expose
    private Integer timezone;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("cod")
    @Expose
    private Integer cod;

    public com.example.mainproject.Coord getCoord() {
        return coord;
    }

    public void setCoord(com.example.mainproject.Coord coord) {
        this.coord = coord;
    }

    public List<com.example.mainproject.Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<com.example.mainproject.Weather> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public com.example.mainproject.Main getMain() {
        return main;
    }

    public void setMain(com.example.mainproject.Main main) {
        this.main = main;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    public com.example.mainproject.Wind getWind() {
        return wind;
    }

    public void setWind(com.example.mainproject.Wind wind) {
        this.wind = wind;
    }

    public com.example.mainproject.Rain getRain() {
        return rain;
    }

    public void setRain(com.example.mainproject.Rain rain) {
        this.rain = rain;
    }

    public com.example.mainproject.Clouds getClouds() {
        return clouds;
    }

    public void setClouds(com.example.mainproject.Clouds clouds) {
        this.clouds = clouds;
    }

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public com.example.mainproject.Sys getSys() {
        return sys;
    }

    public void setSys(com.example.mainproject.Sys sys) {
        this.sys = sys;
    }

    public Integer getTimezone() {
        return timezone;
    }

    public void setTimezone(Integer timezone) {
        this.timezone = timezone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

}
