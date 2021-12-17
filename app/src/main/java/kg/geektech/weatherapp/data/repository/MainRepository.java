package kg.geektech.weatherapp.data.repository;

import androidx.lifecycle.MutableLiveData;

import kg.geektech.weatherapp.App;
import kg.geektech.weatherapp.common.Resource;
import kg.geektech.weatherapp.data.models.MyWeather;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {

    public MutableLiveData<Resource<MyWeather>> getWeather(){

        MutableLiveData<Resource<MyWeather>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading());

        App.api.getWeather("Bishkek", "f4af812615b15d2cb4ba8658893087f7", "metric")
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
