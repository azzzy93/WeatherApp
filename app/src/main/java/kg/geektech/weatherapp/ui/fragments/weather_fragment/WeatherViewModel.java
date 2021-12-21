package kg.geektech.weatherapp.ui.fragments.weather_fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import kg.geektech.weatherapp.common.Resource;
import kg.geektech.weatherapp.data.models.MyWeather;
import kg.geektech.weatherapp.data.models.weather_for_5_days.List;
import kg.geektech.weatherapp.data.models.weather_for_5_days.MainWeather5;
import kg.geektech.weatherapp.data.repository.MainRepository;

@HiltViewModel
public class WeatherViewModel extends ViewModel {
    private MainRepository repository;

    public LiveData<Resource<MyWeather>> liveData;
    public LiveData<Resource<MainWeather5>> liveData5;

    @Inject
    public WeatherViewModel(MainRepository repository){
        this.repository = repository;
    }

    public void getWeathers(String cityName){
        liveData = repository.getWeather(cityName);
        liveData5 = repository.getWeather5(cityName);
    }
}
