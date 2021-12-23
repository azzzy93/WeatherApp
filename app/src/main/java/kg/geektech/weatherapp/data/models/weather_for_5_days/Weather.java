
package kg.geektech.weatherapp.data.models.weather_for_5_days;

import com.google.gson.annotations.SerializedName;

public class Weather {

    @SerializedName("id")
    private Integer idWeather;
    private String main;
    private String description;
    private String icon;

    public Weather(Integer id, String main, String description, String icon) {
        this.idWeather = id;
        this.main = main;
        this.description = description;
        this.icon = icon;
    }

    public Weather() {
    }

    public Integer getIdWeather() {
        return idWeather;
    }

    public void setIdWeather(Integer idWeather) {
        this.idWeather = idWeather;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}
