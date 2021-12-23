package kg.geektech.weatherapp.data.repository;

import androidx.lifecycle.MutableLiveData;

import javax.inject.Inject;

import kg.geektech.weatherapp.common.Resource;
import kg.geektech.weatherapp.data.local.room.AppDatabase;
import kg.geektech.weatherapp.data.local.room.MainWeather5Dao;
import kg.geektech.weatherapp.data.local.room.MyWeatherDao;
import kg.geektech.weatherapp.data.models.MyWeather;
import kg.geektech.weatherapp.data.models.weather_for_5_days.MainWeather5;
import kg.geektech.weatherapp.data.remote.WeatherApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {
    private WeatherApi api;
    private MyWeatherDao myWeatherDao;
    private MainWeather5Dao mainWeather5Dao;

    @Inject
    public MainRepository(WeatherApi api, MyWeatherDao myWeatherDao, MainWeather5Dao mainWeather5Dao) {
        this.api = api;
        this.myWeatherDao = myWeatherDao;
        this.mainWeather5Dao = mainWeather5Dao;
    }

    public MutableLiveData<Resource<MyWeather>> getWeather(String cityName) {

        MutableLiveData<Resource<MyWeather>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading());

        api.getWeather(cityName, "f4af812615b15d2cb4ba8658893087f7", "metric")
                .enqueue(new Callback<MyWeather>() {
                    @Override
                    public void onResponse(Call<MyWeather> call, Response<MyWeather> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            liveData.setValue(Resource.success(response.body()));

                            if (myWeatherDao.getMyWeather() == null) {
                                myWeatherDao.insert(response.body());
                            } else {
                                myWeatherDao.update(response.body());
                            }

                        } else {
                            liveData.setValue(Resource.error(null, response.message()));
                        }
                    }

                    @Override
                    public void onFailure(Call<MyWeather> call, Throwable t) {
                        liveData.setValue(Resource.error(null, t.getLocalizedMessage()));
                    }
                });
        return liveData;
    }

    public MutableLiveData<Resource<MainWeather5>> getWeather5(String cityName) {
        MutableLiveData<Resource<MainWeather5>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading());

        api.getWeather5(cityName, "f4af812615b15d2cb4ba8658893087f7", "metric")
                .enqueue(new Callback<MainWeather5>() {
                    @Override
                    public void onResponse(Call<MainWeather5> call, Response<MainWeather5> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            liveData.setValue(Resource.success(response.body()));

                            if (mainWeather5Dao.getMainWeather5() == null) {
                                mainWeather5Dao.insert(response.body());
                            } else {
                                mainWeather5Dao.update(response.body());
                            }

                        } else {
                            liveData.setValue(Resource.error(null, response.message()));
                        }
                    }

                    @Override
                    public void onFailure(Call<MainWeather5> call, Throwable t) {
                        liveData.setValue(Resource.error(null, t.getLocalizedMessage()));
                    }
                });
        return liveData;
    }
}
