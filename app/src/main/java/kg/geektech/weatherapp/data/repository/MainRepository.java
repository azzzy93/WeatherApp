package kg.geektech.weatherapp.data.repository;

import androidx.lifecycle.MutableLiveData;
import javax.inject.Inject;

import kg.geektech.weatherapp.common.Resource;
import kg.geektech.weatherapp.data.models.MyWeather;
import kg.geektech.weatherapp.data.remote.WeatherApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {
    private WeatherApi api;

    @Inject
    public MainRepository(WeatherApi api) {
        this.api = api;
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
}
