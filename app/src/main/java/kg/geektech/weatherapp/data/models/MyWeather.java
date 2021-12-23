
package kg.geektech.weatherapp.data.models;


import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity
public class MyWeather {

    @PrimaryKey(autoGenerate = true)
    private long idRoom;

    @Embedded
    private Coord coord;
    private List<Weather> weather = null;
    private String base;
    @Embedded
    private Main main;
    private Integer visibility;
    @Embedded
    private Wind wind;
    @Embedded
    private Clouds clouds;
    private Integer dt;
    @Embedded
    private Sys sys;
    private Integer timezone;
    @SerializedName("id")
    private Integer idMyWeather;
    private String name;
    private Integer cod;

    public MyWeather(long idRoom, Coord coord, List<Weather> weather, String base, Main main,
                     Integer visibility, Wind wind, Clouds clouds, Integer dt, Sys sys, Integer timezone,
                     Integer idMyWeather, String name, Integer cod) {
        this.idRoom = idRoom;
        this.coord = coord;
        this.weather = weather;
        this.base = base;
        this.main = main;
        this.visibility = visibility;
        this.wind = wind;
        this.clouds = clouds;
        this.dt = dt;
        this.sys = sys;
        this.timezone = timezone;
        this.idMyWeather = idMyWeather;
        this.name = name;
        this.cod = cod;
    }

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

    public Integer getIdMyWeather() {
        return idMyWeather;
    }

    public void setIdMyWeather(Integer idMyWeather) {
        this.idMyWeather = idMyWeather;
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
