
package kg.geektech.weatherapp.data.models;

import com.google.gson.annotations.SerializedName;

public class Sys {

    private Integer type;
    @SerializedName("id")
    private Integer idSys;
    private String country;
    private Integer sunrise;
    private Integer sunset;

    public Sys(Integer type, Integer id, String country, Integer sunrise, Integer sunset) {
        this.type = type;
        this.idSys = id;
        this.country = country;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    public Sys() {
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIdSys() {
        return idSys;
    }

    public void setIdSys(Integer idSys) {
        this.idSys = idSys;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getSunrise() {
        return sunrise;
    }

    public void setSunrise(Integer sunrise) {
        this.sunrise = sunrise;
    }

    public Integer getSunset() {
        return sunset;
    }

    public void setSunset(Integer sunset) {
        this.sunset = sunset;
    }

}
