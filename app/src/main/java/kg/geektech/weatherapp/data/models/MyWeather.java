
package kg.geektech.weatherapp.data.models;


import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.List;

import kg.geektech.weatherapp.data.local.room.convertors.weather.CloudsConvertor;
import kg.geektech.weatherapp.data.local.room.convertors.weather.CoordConvertor;
import kg.geektech.weatherapp.data.local.room.convertors.weather.MainConvertor;
import kg.geektech.weatherapp.data.local.room.convertors.weather.SysConvertor;
import kg.geektech.weatherapp.data.local.room.convertors.weather.WeatherConvertor;
import kg.geektech.weatherapp.data.local.room.convertors.weather.WindConvertor;

@Entity
public class MyWeather {

    @PrimaryKey
    private long idRoom;
    @TypeConverters({CoordConvertor.class})
    private Coord coord;
    @TypeConverters({WeatherConvertor.class})
    private List<Weather> weather = null;
    private String base;
    @TypeConverters({MainConvertor.class})
    private Main main;
    private Integer visibility;
    @TypeConverters({WindConvertor.class})
    private Wind wind;
    @TypeConverters({CloudsConvertor.class})
    private Clouds clouds;
    private Integer dt;
    @TypeConverters({SysConvertor.class})
    private Sys sys;
    private Integer timezone;
    private Integer id;
    private String name;
    private Integer cod;

    public MyWeather() {
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
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

    public long getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(long idRoom) {
        this.idRoom = idRoom;
    }
}
