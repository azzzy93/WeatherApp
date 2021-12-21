
package kg.geektech.weatherapp.data.models.weather_for_5_days;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Main {

    private Integer temp;
    @SerializedName("feels_like")
    @Expose
    private Integer feelsLike;
    @SerializedName("temp_min")
    @Expose
    private Integer tempMin;
    @SerializedName("temp_max")
    @Expose
    private Integer tempMax;
    private Integer pressure;
    @SerializedName("sea_level")
    @Expose
    private Integer seaLevel;
    @SerializedName("grnd_level")
    @Expose
    private Integer grndLevel;
    private Integer humidity;
    @SerializedName("temp_kf")
    @Expose
    private Integer tempKf;

    public Integer getTemp() {
        return temp;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    public Integer getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(Integer feelsLike) {
        this.feelsLike = feelsLike;
    }

    public Integer getTempMin() {
        return tempMin;
    }

    public void setTempMin(Integer tempMin) {
        this.tempMin = tempMin;
    }

    public Integer getTempMax() {
        return tempMax;
    }

    public void setTempMax(Integer tempMax) {
        this.tempMax = tempMax;
    }

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    public Integer getSeaLevel() {
        return seaLevel;
    }

    public void setSeaLevel(Integer seaLevel) {
        this.seaLevel = seaLevel;
    }

    public Integer getGrndLevel() {
        return grndLevel;
    }

    public void setGrndLevel(Integer grndLevel) {
        this.grndLevel = grndLevel;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Integer getTempKf() {
        return tempKf;
    }

    public void setTempKf(Integer tempKf) {
        this.tempKf = tempKf;
    }

}
