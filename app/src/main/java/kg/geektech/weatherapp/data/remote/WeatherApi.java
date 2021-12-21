package kg.geektech.weatherapp.data.remote;

import kg.geektech.weatherapp.data.models.MyWeather;
import kg.geektech.weatherapp.data.models.weather_for_5_days.MainWeather5;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET("data/2.5/weather")
    Call<MyWeather> getWeather(@Query("q") String cityName,
                               @Query("appid") String appId,
                               @Query("units") String units);

    @GET("data/2.5/forecast")
    Call<MainWeather5> getWeather5(@Query("q") String cityName,
                                   @Query("appid") String appId,
                                   @Query("units") String units);
}