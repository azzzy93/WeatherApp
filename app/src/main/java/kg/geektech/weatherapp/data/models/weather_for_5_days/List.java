
package kg.geektech.weatherapp.data.models.weather_for_5_days;

import androidx.room.TypeConverters;

import com.google.gson.annotations.SerializedName;

import kg.geektech.weatherapp.data.local.room.convertors.weather5.CloudsConvertor;
import kg.geektech.weatherapp.data.local.room.convertors.weather5.MainConvertor;
import kg.geektech.weatherapp.data.local.room.convertors.weather5.SysConvertor;
import kg.geektech.weatherapp.data.local.room.convertors.weather5.WeatherConverter;
import kg.geektech.weatherapp.data.local.room.convertors.weather5.WindConvertor;

public class List {

    private Integer dt;
    @TypeConverters({MainConvertor.class})
    private Main main;
    @TypeConverters({WeatherConverter.class})
    private java.util.List<Weather> weather = null;
    @TypeConverters({CloudsConvertor.class})
    private Clouds clouds;
    @TypeConverters({WindConvertor.class})
    private Wind wind;
    private Integer visibility;
    private Double pop;
    @TypeConverters({SysConvertor.class})
    private Sys sys;
    @SerializedName("dt_txt")
    private String dtTxt;

    public List() {
    }

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public java.util.List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(java.util.List<Weather> weather) {
        this.weather = weather;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    public Double getPop() {
        return pop;
    }

    public void setPop(Double pop) {
        this.pop = pop;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public String getDtTxt() {
        return dtTxt;
    }

    public void setDtTxt(String dtTxt) {
        this.dtTxt = dtTxt;
    }

}
